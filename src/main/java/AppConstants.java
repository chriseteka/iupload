import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class AppConstants {

  public static final String DELIMITER = "\t";
  public static final String PRODUCTS = "/products.json";

  public static List<Integer> constantIndexOfInterest() {
    return List.of(8,18,19,22,23,26,28,29,30,31,32,33,35,36,37,38,45,48);
  }
  public static List<Integer> indexOfInterest() {
    return List.of(1,2,4,5,6,7,8,9,14,15,16,18,19,20,21,22,23,26,28,29,30,31,32,33,34,35,36,37,38,45,48);
  }
  public static List<Integer> indicesSuppliedByUsers() {
    List<Integer> suppliedIndices = new ArrayList<>(indexOfInterest());
    suppliedIndices.removeAll(constantIndexOfInterest());
    return suppliedIndices;
  }
  public static List<Pair> stockTemplate() {
    return List.of(
      Pair.of("Handle", 1),
      Pair.of("Title", 2),
      Pair.of("Body (HTML)", 3),
      Pair.of("Vendor", 4),
      Pair.of("Type", 5),
      Pair.of("Tags", 6),
      Pair.of("Published", 7),
      Pair.of("Option1 Name", 8),
      Pair.of("Option1 Value", 9),
      Pair.of("Option2 Name", 10),
      Pair.of("Option2 Value", 11),
      Pair.of("Option3 Name", 12),
      Pair.of("Option3 Value", 13),
      Pair.of("Variant SKU", 14),
      Pair.of("Variant Grams", 15),
      Pair.of("Variant Inventory Tracker", 16),
      Pair.of("Variant Inventory Qty", 17),
      Pair.of("Variant Inventory Policy", 18),
      Pair.of("Variant Fulfillment Service", 19),
      Pair.of("Variant Price", 20),
      Pair.of("Variant Compare At Price", 21),
      Pair.of("Variant Requires Shipping", 22),
      Pair.of("Variant Taxable", 23),
      Pair.of("Variant Barcode", 24),
      Pair.of("Image Src", 25),
      Pair.of("Image Position", 26),
      Pair.of("Image Alt Text", 27),
      Pair.of("Gift Card", 28),
      Pair.of("SEO Title", 29),
      Pair.of("SEO Description", 30),
      Pair.of("Google Shopping / Google Product Category", 31),
      Pair.of("Google Shopping / Gender", 32),
      Pair.of("Google Shopping / Age Group", 33),
      Pair.of("Google Shopping / MPN", 34),
      Pair.of("Google Shopping / AdWords Grouping", 35),
      Pair.of("Google Shopping / AdWords Labels", 36),
      Pair.of("Google Shopping / Condition", 37),
      Pair.of("Google Shopping / Custom Product", 38),
      Pair.of("Google Shopping / Custom Label 0", 39),
      Pair.of("Google Shopping / Custom Label 1", 40),
      Pair.of("Google Shopping / Custom Label 2", 41),
      Pair.of("Google Shopping / Custom Label 3", 42),
      Pair.of("Google Shopping / Custom Label 4", 43),
      Pair.of("Variant Image", 44),
      Pair.of("Variant Weight Unit", 45),
      Pair.of("Variant Tax Code", 46),
      Pair.of("Cost per item", 47),
      Pair.of("Status", 48)
    );
  }
  public enum ConstantTypes {
    OTHERS,
    BOOL,
    ENUM,
    TITLE,
    HANDLE,
    CONCAT;

    public static String deduceValueFrom(String titleCumHandle, int indexNumber) {
      final String[] split = titleCumHandle.split("-");
      String title = split[0];
      String handle = split[1];
      switch (evaluateFromIndex(indexNumber)) {
        case OTHERS: {
          if (indexNumber == 26) return "1";
          if (indexNumber == 45) return "g";
          if (indexNumber == 18) return "deny";
          if (indexNumber == 19) return "manual";
        }
        case BOOL: return Stream.of(22, 23).anyMatch(i -> i == indexNumber) ? "TRUE" : "FALSE";
        case TITLE: return title;
        case HANDLE: return handle;
        //We may have to change this to pick from an Enum
        case ENUM: {
          if (indexNumber == 32) return "Unisex";
          if (indexNumber == 33) return  "Adult";
          if (indexNumber == 37) return "New";
          if (indexNumber == 48) return "active";
          if (indexNumber == 31) return "Apparel & Accessories > Clothing"; //Should be changed
          if (indexNumber == 36) return "cotton, pre-shrunk"; //Should be changed
        }
        case CONCAT: {
          if (indexNumber == 29) return String.format("Our Awesome %s", title);
          else return randomTextWith(title);
        }
        default: return "";
      }
    }

    static ConstantTypes evaluateFromIndex(int index) {
      if (index ==  8) return TITLE;
      else if (index ==  35) return HANDLE;
      else if (List.of(29, 30).contains(index)) return CONCAT;
      else if (List.of(22, 23, 28, 38).contains(index)) return BOOL;
      else if (List.of(18, 19, 26, 45).contains(index)) return OTHERS;
      else return ENUM;
    }
    static String randomTextWith(String title) {

      final List<String> descriptions = List.of(
          "is an awesome product exclusively for you.",
          "is a new arrival and has been subsidized.",
          "quality is guaranteed, as always and you know it.",
          "is almost running out of stock, hurry and get yours while it is still available.",
          "meets you need as we still hold our reputation for customer satisfaction"
      );
      final int descriptionListSize = descriptions.size();

      return new Random()
        .ints(descriptionListSize, 0, descriptionListSize)
        .mapToObj(descriptions::get)
        .findFirst()
        .map(description -> String.format("%s %s", title.trim(), description.trim()))
        .orElse(String.format("%s is simple a product you want to get.", title));

    }
  }
}
