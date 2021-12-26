import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FileUtils {

  private final String filePath;
  private final List<StockRepresentation> stockRepresentations;

  public static void createCredentialsFrom(String credentialPath) throws IOException {
    final List<String> credentials = Files.readAllLines(Paths.get(credentialPath)).stream()
        .filter(line -> line.trim().length() > 10 && !line.matches("\\s+"))
        .map(line -> line.split("=")[1])
        .collect(Collectors.toList());
    CredentialHolder.CREATE_INSTANCE(credentials.get(0), credentials.get(1), credentials.get(2));
  }

  public FileUtils(String filePath) throws IOException {
    this.filePath = filePath;
    final List<List<String>> extractedLines = readTemporaryFile(filePath);
    this.stockRepresentations = !extractedLines.isEmpty()
        ? extractedLines.stream().map(StockRepresentation::fromPartialLine).collect(Collectors.toList())
        : Collections.emptyList();
  }

  public List<StockRepresentation> getStockRepresentations() {
    return stockRepresentations;
  }

  private List<List<String>> readTemporaryFile(String filePath) throws IOException {

    //We assume the file that was uploaded has more than one line, with each token separated by a comma
    return Files.readAllLines(Paths.get(filePath)).stream()
        .filter(line -> line.trim().length() > 7 && !line.matches("\\s+")
                && List.of("Handle", "Type", "Brand").stream().noneMatch(line::contains))
        .map(line -> List.of(line.split(","))).collect(Collectors.toList());
  }

  public void generateStockCSVSheet() throws FileNotFoundException {

    if (!stockRepresentations.isEmpty()) {

      final String CSV_HEADERS = AppConstants.stockTemplate().stream().map(Pair::getColumnName).collect(Collectors.joining(AppConstants.DELIMITER));
      final List<String> stockRepresentationLines = stockRepresentations.stream()
          .map(StockRepresentation::toCommaSeparatedLine)
          .collect(Collectors.toList());

      File csvOutputFile = new File(filePath.split("\\.")[0] + "-converted.csv");
      try (PrintWriter printWriter = new PrintWriter(csvOutputFile)) {
        printWriter.println(CSV_HEADERS);
        stockRepresentationLines.forEach(printWriter::println);
      }
    }
  }
}
