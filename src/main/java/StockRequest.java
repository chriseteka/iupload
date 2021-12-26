import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StockRequest {

  private final Product product;

    public StockRequest(Product product) {
        this.product = product;
    }

    public Product getProduct() {
    return product;
  }

  public StockRequest(StockRepresentation stockRepresentation) {
    this.product = new Product(stockRepresentation);
  }

  static class Product {

    private Long id;
    private String handle;
    private String title;
    private String body_html;
    private String vendor;
    private String product_type;
    private boolean published;
    private List<String> tags;
    private String status;
    private List<Variant> variants = new ArrayList<>();

      public Product(Variant variant) {
          this.getVariants().add(variant);
      }

      public List<Variant> getVariants() {
          return variants;
      }

      public void setVariants(List<Variant> variants) {
          this.variants = variants;
      }

      public void setHandle(String handle) {
          this.handle = handle;
      }

      public void setTitle(String title) {
          this.title = title;
      }

      public void setBody_html(String body_html) {
          this.body_html = body_html;
      }

      public void setVendor(String vendor) {
          this.vendor = vendor;
      }

      public void setProduct_type(String product_type) {
          this.product_type = product_type;
      }

      public void setPublished(boolean published) {
          this.published = published;
      }

      public void setTags(List<String> tags) {
          this.tags = tags;
      }

      public void setStatus(String status) {
          this.status = status;
      }

      //    private final List<Variant> variants;
    private List<Image> images;

      public Long getId() {
          return id;
}

      public String getHandle() {
      return handle;
    }

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


      public void setId(Long id) {
          this.id = id;
      }

      public List<Image> getImages() {
      return images;
    }

    Product(StockRepresentation stockRepresentation) {
      this.handle = stockRepresentation.getHandle().trim();
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
      this.images = stockRepresentation.getImages();
    }

    static String wrapWithStrongTag(String value) {
      return String.format("<Strong>%s</Strong>", value);
    }
  }

    static class Variant {

        private long id;
        private long product_id;
        private int inventory_quantity;
        private BigDecimal price;
        private String fulfillment_service;
        private String inventory_management;

        public Variant(Variant variant, StockRepresentation stockRepresentation) {
            this.inventory_quantity = Integer.parseInt(stockRepresentation.getVariantInventoryQty());
            this.price = new BigDecimal(stockRepresentation.getVariantPrice());
            this.product_id = variant.getProduct_id();
            this.inventory_management = "shopify";
            this.fulfillment_service = "manual";
            this.id = variant.getId();
        }

        public long getId() {
            return id;
        }

        public long getProduct_id() {
            return product_id;
        }

        public void setProduct_id(long product_id) {
            this.product_id = product_id;
        }

        public int getInventory_quantity() {
            return inventory_quantity;
        }

        public String getFulfillment_service() {
            return fulfillment_service;
        }

        public String getInventory_management() {
            return inventory_management;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setInventory_quantity(int inventory_quantity) {
            this.inventory_quantity = inventory_quantity;
        }

        public void setFulfillment_service(String fulfillment_service) {
            this.fulfillment_service = fulfillment_service;
        }

        public void setInventory_management(String inventory_management) {
            this.inventory_management = inventory_management;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }
    }
}
