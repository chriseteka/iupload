import java.util.List;
import java.util.stream.Collectors;

public class StockRequest {

  private final Product product;

  public Product getProduct() {
    return product;
  }

  public StockRequest(StockRepresentation stockRepresentation) {
    this.product = new Product(stockRepresentation);
  }

  static class Product {

    private final String title;
    private final String body_html;
    private final String vendor;
    private final String product_type;
    private final boolean published;
    private final List<String> tags;
    private final String status;
    private final List<Variant> variants;
    private final List<Image> images;

    public String getTitle() {
      return title;
    }

    public String getBody_html() {
      return body_html;
    }

    public String getVendor() {
      return vendor;
    }

    public String getProduct_type() {
      return product_type;
    }

    public boolean isPublished() {
      return published;
    }

    public List<String> getTags() {
      return tags;
    }

    public String getStatus() {
      return status;
    }

    public List<Variant> getVariants() {
      return variants;
    }

    public List<Image> getImages() {
      return images;
    }

    Product(StockRepresentation stockRepresentation) {
      this.title = stockRepresentation.getTitle().trim();
      this.body_html = wrapWithStrongTag(stockRepresentation.getTitle());
      this.vendor = stockRepresentation.getVendor().trim();
      this.product_type = stockRepresentation.getType().trim();
      this.published = Boolean.parseBoolean(stockRepresentation.getPublished().trim());
      this.tags = List.of(stockRepresentation.getTags().split(" "))
          .stream().map(String::trim)
          .filter(tag -> tag.length() > 0 && !tag.matches("\\s+"))
          .collect(Collectors.toList());
      this.status = stockRepresentation.getStatus().trim();
      this.variants = List.of(
          new StockRequest.Variant(stockRepresentation.getOption1Name().trim(),
            stockRepresentation.getVariantPrice().trim(), stockRepresentation.getVariantSKU().trim()),
          new StockRequest.Variant(stockRepresentation.getOption1Value().trim(),
              stockRepresentation.getVariantCompareAtPrice().trim(), stockRepresentation.getVariantSKU().trim())
      );
      this.images = stockRepresentation.getImages();
    }

    static String wrapWithStrongTag(String value) {
      return String.format("<Strong>%s</Strong>", value);
    }
  }

  static class Variant {
    private final String option1;
    private final String price;
    private final String sku;

    public Variant(String option1, String price, String sku) {
      this.option1 = option1;
      this.price = price;
      this.sku = sku;
    }

    public String getOption1() {
      return option1;
    }

    public String getPrice() {
      return price;
    }

    public String getSku() {
      return sku;
    }
  }
}
