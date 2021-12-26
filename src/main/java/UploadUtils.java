import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class UploadUtils {

  private final CloseableHttpClient client = HttpClients.createDefault();
  private final Logger logger = Logger.getLogger("IUploaderLogger");
  private final List<StockRepresentation> stockRepresentations;
  private static final Gson GSON = new Gson();
  private final File[] imageFiles;

  private final UsernamePasswordCredentials authorization = new UsernamePasswordCredentials(
          CredentialHolder.getINSTANCE().getApiKey(),
          CredentialHolder.getINSTANCE().getPassword()
  );

  public UploadUtils(String imageDirectory, List<StockRepresentation> stockRepresentations) {
    this.stockRepresentations = stockRepresentations;
    this.imageFiles = new File(imageDirectory).listFiles((file, filName) -> FileOfInterest.imageFormats().stream().anyMatch(filName::endsWith));
  }

  public void uploadImageToSavedStock() {

    stockRepresentations.forEach(stockRepresentation -> {

      final String title = stockRepresentation.getTitle();
      logger.info(String.format("Now uploading stock with name: %s", title));

      try {
        final StockRequest.Variant variant = postToStore(preparePostBody(stockRepresentation));
        if (variant != null) {
          final StockRequest stockRequest = new StockRequest(new StockRequest.Product(new StockRequest.Variant(variant, stockRepresentation)));

          if (updateStock(variant.getProduct_id(), GSON.toJson(stockRequest, StockRequest.class)))
            logger.info(String.format("Successful upload of stock with name: %s", title));
        }
        else logger.info(String.format("Failed to upload stock with name: %s", title));
      } catch (Exception e) {
        e.printStackTrace();
        logger.info(String.format("Posting stock to store failed with reason: %s", e.getMessage()));
      }

    });

  }

  private String preparePostBody(StockRepresentation stockRepresentation) {

    Arrays.stream(imageFiles)
        .filter(file -> file.getName().toUpperCase().startsWith(stockRepresentation.getTitle().toUpperCase()))
        .map(this::prepareBase64EncodedImage)
        .filter(Objects::nonNull)
        .forEach(stockRepresentation::addImage);

    return GSON.toJson(new StockRequest(stockRepresentation));
  }

  /**
   * Posts a stock to shopify and returns the id of the newly created stock
   * @param payload This is the json to be sent
   * @return The id of the newly created stock if it was successful, this is set to -1 if unsuccessful
   * @throws IOException This is an exception for a case where the response from the request cannot be read
   * @throws AuthenticationException This is an exception for a case where authentication failed before the post action
   */
  private StockRequest.Variant postToStore(String payload) throws IOException, AuthenticationException {

    HttpPost httpPost = new HttpPost(withHTTPSConnection(CredentialHolder.getINSTANCE().getStoreUrl() + AppConstants.PRODUCTS));
    StringEntity requestBody = new StringEntity(payload, ContentType.APPLICATION_JSON);
    httpPost.setEntity(requestBody);
    httpPost.setHeader("Accept", "application/json");
    httpPost.setHeader("Content-type", "application/json");
    httpPost.addHeader(new BasicScheme().authenticate(authorization, httpPost, null));

    CloseableHttpResponse response = client.execute(httpPost);
    final int statusCode = response.getStatusLine().getStatusCode();

    if (statusCode >= 200 && statusCode < 300) {
      String responseString = EntityUtils.toString(response.getEntity(), "UTF-8").replaceAll("tags", "tag");
      return GSON.fromJson(responseString, StockRequest.class).getProduct().getVariants().get(0);
    }

    client.close();
    return null;
  }

  /**
   * Updates a stocks variant given the product id amd variant object
   * @param productId Ths stock whose variant is to be updated
   * @param payload This is the json body to be sent
   * @return A boolean, TRUE if the request was successful and FALSE otherwise
   * @throws IOException This is an exception for a case where the response from the request cannot be read
   * @throws AuthenticationException This is an exception for a case where authentication failed before the post action
   */
  private boolean updateStock(Long productId, String payload) throws IOException, AuthenticationException {

    HttpPut httpPut = new HttpPut(withHTTPSConnection(CredentialHolder.getINSTANCE().getStoreUrl() + "/products/" + productId + ".json"));
    StringEntity requestBody = new StringEntity(payload, ContentType.APPLICATION_JSON);
    httpPut.setEntity(requestBody);
    httpPut.setHeader("Accept", "application/json");
    httpPut.setHeader("Content-type", "application/json");
    httpPut.addHeader(new BasicScheme().authenticate(authorization, httpPut, null));

    CloseableHttpResponse response = client.execute(httpPut);
    final int statusCode = response.getStatusLine().getStatusCode();
    client.close();

    return statusCode >= 200 && statusCode < 300;
  }

  private String prepareBase64EncodedImage(File imageFile) {

    try {
      byte[] fileContent = FileUtils.readFileToByteArray(imageFile);
      return Base64.getEncoder().encodeToString(fileContent);
    } catch (IOException e) {

      logger.info(String.format("Cannot read this image bytes with reason: %s", e.getMessage()));
      return null;
    }
  }

  private String withHTTPSConnection(String url) {
    if (url.startsWith("https://")) return url;
    return String.format("https://%s", url.replaceAll("http://", ""));
  }
}
