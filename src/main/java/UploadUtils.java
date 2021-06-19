import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class UploadUtils {

  private final Logger logger = Logger.getLogger("IUploaderLogger");
  private final List<StockRepresentation> stockRepresentations;
  private final File[] imageFiles;

  public UploadUtils(String imageDirectory, List<StockRepresentation> stockRepresentations) {
    this.stockRepresentations = stockRepresentations;
    this.imageFiles = new File(imageDirectory).listFiles((file, filName) -> FileOfInterest.imageFormats().stream().anyMatch(filName::endsWith));
  }

  public void uploadImageToSavedStock() {

    stockRepresentations.forEach(stockRepresentation -> {

      final String title = stockRepresentation.getTitle();
      logger.info(String.format("Now uploading stock with name: %s", title));

      try {
        if (postToStore(preparePostBody(stockRepresentation)))
          logger.info(String.format("Successful upload of stock with name: %s", title));
        else logger.info(String.format("Failed to upload stock with name: %s", title));
      } catch (Exception e) {
        e.printStackTrace();
        logger.info(String.format("Posting stock to store failed with reason: %s", e.getMessage()));
      }

    });

  }

  private String preparePostBody(StockRepresentation stockRepresentation) {

    final int stockIndex = stockRepresentations.indexOf(stockRepresentation) + 1;
    Arrays.stream(imageFiles)
        .filter(file -> file.getName().split("\\.")[0].endsWith(String.valueOf(stockIndex)))
        .map(this::prepareBase64EncodedImage)
        .filter(Objects::nonNull)
        .forEach(stockRepresentation::addImage);

    return new Gson().toJson(new StockRequest(stockRepresentation));
  }

  private boolean postToStore(String payload) throws IOException, AuthenticationException {

    CloseableHttpClient client = HttpClients.createDefault();
    HttpPost httpPost = new HttpPost(withHTTPSConnection(CredentialHolder.getINSTANCE().getStoreUrl() + AppConstants.PRODUCTS));
    StringEntity requestBody = new StringEntity(payload, ContentType.APPLICATION_JSON);
    httpPost.setEntity(requestBody);
    httpPost.setHeader("Accept", "application/json");
    httpPost.setHeader("Content-type", "application/json");
    UsernamePasswordCredentials authorization = new UsernamePasswordCredentials(
        CredentialHolder.getINSTANCE().getApiKey(),
        CredentialHolder.getINSTANCE().getPassword()
    );
    httpPost.addHeader(new BasicScheme().authenticate(authorization, httpPost, null));

    CloseableHttpResponse response = client.execute(httpPost);
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
    return String.format("https://%s", url.replaceAll("http://", ""));
  }
}
