import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum FileOfInterest {
  CSV(".csv"),
  TXT(".txt"),
  JPG(".jpg"),
  JPEG(".jpeg"),
  PNG(".png"),
  GIF(".gif");

  private final String format;

  FileOfInterest(String format) {
    this.format = format;
  }

  public String getFormat() {
    return format;
  }

  public static List<String> imageFormats() {
    return Stream.of(values()).map(FileOfInterest::getFormat)
        .filter(format -> !List.of(".csv", ".txt").contains(format))
        .collect(Collectors.toList());
  }
}
