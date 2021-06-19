import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class IUploadApp {

  public static void main(String[] args) throws IOException {

    Logger logger = Logger.getLogger("IUploaderLogger");

    logger.info("<~~ === *** <==> IUpload is starting up <==> *** === ~~>");

    logger.info(String.format("Size of columns of interest: %s%n", AppConstants.indexOfInterest().size()));
    logger.info(String.format("Size of constants columns of interest: %s%n", AppConstants.constantIndexOfInterest().size()));
    logger.info(String.format("Size of columns of interest supplied by users: %s%n", AppConstants.indicesSuppliedByUsers().size()));

    /*
     * We expect that during startup a user should pass an argument to JVM which is the path to the file in the form:
     * "/Users/mac/ActiveEdge/intelliJ2021.1/iupload/src/main/resources/test.txt";
     * This jar file can therefore be started as: @{ java -jar iupload-1.0-SNAPSHOT.jar "/path-to-file.txt" true }@
     */
    final String path = args[0];
    final boolean doUpload = Boolean.parseBoolean(args[1]);

    //1. From the file passed, we will look at the entire directory for images which we will have to upload alongside
    final String directoryPath = path.substring(0, path.lastIndexOf("/"));

    FileUtils.createCredentialsFrom(String.format("%s/%s", directoryPath, "api.txt"));
    logger.info(String.format("Credentials for API external calls: %s", CredentialHolder.getINSTANCE().toString()));

    logger.info(String.format("A file location was passed, application will try to find the file in: %s%n", path));

    logger.info("Starting to read file found");
    final FileUtils fileUtils = new FileUtils(path);
    final List<StockRepresentation> stockRepresentations = fileUtils.getStockRepresentations();
    logger.info("Finished reading file and now ready to upload them to shop");

    if (doUpload) {
      logger.info("Starting to upload stock and their images");
      new UploadUtils(directoryPath, stockRepresentations).uploadImageToSavedStock();
      logger.info("Finished uploading stock to shop, will prepare to export them as CSV");
    }
    else logger.info("No upload was permitted hence will generate a CSV only");

    fileUtils.generateStockCSVSheet();
    logger.info("Finished reading file, uploading to shop and exported a corresponding CSV version of it");

    logger.info("<~~ === *** <==> IUpload is shutting down <==> *** === ~~>");
  }
}
