import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StockRepresentation {
  private final String Handle;
  private final String Title;
  private final String BodyHTML;
  private final String Vendor;
  private final String Type;
  private final String Tags;
  private final String Published;
  private final String Option1Name;
  private final String Option1Value;
  private final String Option2Name;
  private final String Option2Value;
  private final String Option3Name;
  private final String Option3Value;
  private final String VariantSKU;
  private final String VariantGrams;
  private final String VariantInventoryTracker;
  private final String VariantInventoryQty;
  private final String VariantInventoryPolicy;
  private final String VariantFulfillmentService;
  private final String VariantPrice;
  private final String VariantCompareAtPrice;
  private final String VariantRequiresShipping;
  private final String VariantTaxable;
  private final String VariantBarcode;
  private final String ImageSrc;
  private final String ImagePosition;
  private final String ImageAltText;
  private final String GiftCard;
  private final String SEOTitle;
  private final String SEODescription;
  private final String GoogleShoppingGoogleProductCategory;
  private final String GoogleShoppingGender;
  private final String GoogleShoppingAgeGroup;
  private final String GoogleShoppingMPN;
  private final String GoogleShoppingAdWordsGrouping;
  private final String GoogleShoppingAdWordsLabels;
  private final String GoogleShoppingCondition;
  private final String GoogleShoppingCustomProduct;
  private final String GoogleShoppingCustomLabel0;
  private final String GoogleShoppingCustomLabel1;
  private final String GoogleShoppingCustomLabel2;
  private final String GoogleShoppingCustomLabel3;
  private final String GoogleShoppingCustomLabel4;
  private final String VariantImage;
  private final String VariantWeightUnit;
  private final String VariantTaxCode;
  private final String CostPerItem;
  private final String Status;

  //This is not really in the object description, but we are adding it so we can pass in array of base64 image strings
  private final List<Image> images = new ArrayList<>();

  public void addImage(String base64Image) {
    images.add(new Image(base64Image));
  }

  public List<Image> getImages() {
    return images;
  }

  public StockRepresentation(String handle, String title, String vendor, String type, String tags, String published,
                             String option1Name, String option1Value, String variantSKU, String variantGrams,
                             String variantInventoryTracker, String variantInventoryPolicy, String variantFulfillmentService,
                             String variantPrice, String variantCompareAtPrice, String variantRequiresShipping,
                             String variantTaxable, String imagePosition, String giftCard,
                             String SEOTitle, String SEODescription, String googleShoppingGoogleProductCategory,
                             String googleShoppingGender, String googleShoppingAgeGroup, String googleShoppingMPN,
                             String googleShoppingAdWordsGrouping, String googleShoppingAdWordsLabels,
                             String googleShoppingCondition, String googleShoppingCustomProduct, String variantWeightUnit,
                             String status) {
    Handle = handle;
    Title = title;
    Vendor = vendor;
    Type = type;
    Tags = tags;
    Published = published;
    Option1Name = option1Name;
    Option1Value = option1Value;
    VariantSKU = variantSKU;
    VariantGrams = variantGrams;
    VariantInventoryTracker = variantInventoryTracker;
    VariantInventoryPolicy = variantInventoryPolicy;
    VariantFulfillmentService = variantFulfillmentService;
    VariantPrice = variantPrice;
    VariantCompareAtPrice = variantCompareAtPrice;
    VariantRequiresShipping = variantRequiresShipping;
    VariantTaxable = variantTaxable;
    ImagePosition = imagePosition;
    GiftCard = giftCard;
    this.SEOTitle = SEOTitle;
    this.SEODescription = SEODescription;
    GoogleShoppingGoogleProductCategory = googleShoppingGoogleProductCategory;
    GoogleShoppingGender = googleShoppingGender;
    GoogleShoppingAgeGroup = googleShoppingAgeGroup;
    GoogleShoppingMPN = googleShoppingMPN;
    GoogleShoppingAdWordsGrouping = googleShoppingAdWordsGrouping;
    GoogleShoppingAdWordsLabels = googleShoppingAdWordsLabels;
    GoogleShoppingCondition = googleShoppingCondition;
    GoogleShoppingCustomProduct = googleShoppingCustomProduct;
    VariantWeightUnit = variantWeightUnit;
    Status = status;
    this.BodyHTML = "";
    this.Option2Name = "";
    this.Option2Value = "";
    this.Option3Name = "";
    this.Option3Value = "";
    this.VariantInventoryQty = "";
    this.VariantBarcode = "";
    this.ImageSrc = "";
    this.ImageAltText = "";
    this.GoogleShoppingCustomLabel0 = "";
    this.GoogleShoppingCustomLabel1 = "";
    this.GoogleShoppingCustomLabel2 = "";
    this.GoogleShoppingCustomLabel3 = "";
    this.GoogleShoppingCustomLabel4 = "";
    this.VariantImage = "";
    this.VariantTaxCode = "";
    this.CostPerItem = "";
  }

  public String getHandle() {
    return Handle;
  }

  public String getTitle() {
    return Title;
  }

  public String getBodyHTML() {
    return BodyHTML;
  }

  public String getVendor() {
    return Vendor;
  }

  public String getType() {
    return Type;
  }

  public String getTags() {
    return Tags;
  }

  public String getPublished() {
    return Published;
  }

  public String getOption1Name() {
    return Option1Name;
  }

  public String getOption1Value() {
    return Option1Value;
  }

  public String getOption2Name() {
    return Option2Name;
  }

  public String getOption2Value() {
    return Option2Value;
  }

  public String getOption3Name() {
    return Option3Name;
  }

  public String getOption3Value() {
    return Option3Value;
  }

  public String getVariantSKU() {
    return VariantSKU;
  }

  public String getVariantGrams() {
    return VariantGrams;
  }

  public String getVariantInventoryTracker() {
    return VariantInventoryTracker;
  }

  public String getVariantInventoryQty() {
    return VariantInventoryQty;
  }

  public String getVariantInventoryPolicy() {
    return VariantInventoryPolicy;
  }

  public String getVariantFulfillmentService() {
    return VariantFulfillmentService;
  }

  public String getVariantPrice() {
    return VariantPrice;
  }

  public String getVariantCompareAtPrice() {
    return VariantCompareAtPrice;
  }

  public String getVariantRequiresShipping() {
    return VariantRequiresShipping;
  }

  public String getVariantTaxable() {
    return VariantTaxable;
  }

  public String getVariantBarcode() {
    return VariantBarcode;
  }

  public String getImageSrc() {
    return ImageSrc;
  }

  public String getImagePosition() {
    return ImagePosition;
  }

  public String getImageAltText() {
    return ImageAltText;
  }

  public String getGiftCard() {
    return GiftCard;
  }

  public String getSEOTitle() {
    return SEOTitle;
  }

  public String getSEODescription() {
    return SEODescription;
  }

  public String getGoogleShoppingGoogleProductCategory() {
    return GoogleShoppingGoogleProductCategory;
  }

  public String getGoogleShoppingGender() {
    return GoogleShoppingGender;
  }

  public String getGoogleShoppingAgeGroup() {
    return GoogleShoppingAgeGroup;
  }

  public String getGoogleShoppingMPN() {
    return GoogleShoppingMPN;
  }

  public String getGoogleShoppingAdWordsGrouping() {
    return GoogleShoppingAdWordsGrouping;
  }

  public String getGoogleShoppingAdWordsLabels() {
    return GoogleShoppingAdWordsLabels;
  }

  public String getGoogleShoppingCondition() {
    return GoogleShoppingCondition;
  }

  public String getGoogleShoppingCustomProduct() {
    return GoogleShoppingCustomProduct;
  }

  public String getGoogleShoppingCustomLabel0() {
    return GoogleShoppingCustomLabel0;
  }

  public String getGoogleShoppingCustomLabel1() {
    return GoogleShoppingCustomLabel1;
  }

  public String getGoogleShoppingCustomLabel2() {
    return GoogleShoppingCustomLabel2;
  }

  public String getGoogleShoppingCustomLabel3() {
    return GoogleShoppingCustomLabel3;
  }

  public String getGoogleShoppingCustomLabel4() {
    return GoogleShoppingCustomLabel4;
  }

  public String getVariantImage() {
    return VariantImage;
  }

  public String getVariantWeightUnit() {
    return VariantWeightUnit;
  }

  public String getVariantTaxCode() {
    return VariantTaxCode;
  }

  public String getCostPerItem() {
    return CostPerItem;
  }

  public String getStatus() {
    return Status;
  }

  public String toCommaSeparatedLine() {
    return String.join(
        AppConstants.DELIMITER,
        this.getHandle(),
        this.getTitle(),
        this.getBodyHTML(),
        this.getVendor(),
        this.getType(),
        this.getTags(),
        this.getPublished(),
        this.getOption1Name(),
        this.getOption1Value(),
        this.getOption2Name(),
        this.getOption2Value(),
        this.getOption3Name(),
        this.getOption3Value(),
        this.getVariantSKU(),
        this.getVariantGrams(),
        this.getVariantInventoryTracker(),
        this.getVariantInventoryQty(),
        this.getVariantInventoryPolicy(),
        this.getVariantFulfillmentService(),
        this.getVariantPrice(),
        this.getVariantCompareAtPrice(),
        this.getVariantRequiresShipping(),
        this.getVariantTaxable(),
        this.getVariantBarcode(),
        this.getImageSrc(),
        this.getImagePosition(),
        this.getImageAltText(),
        this.getGiftCard(),
        this.getSEOTitle(),
        this.getSEODescription(),
        this.getGoogleShoppingGoogleProductCategory(),
        this.getGoogleShoppingGender(),
        this.getGoogleShoppingAgeGroup(),
        this.getGoogleShoppingMPN(),
        this.getGoogleShoppingAdWordsGrouping(),
        this.getGoogleShoppingAdWordsLabels(),
        this.getGoogleShoppingCondition(),
        this.getGoogleShoppingCustomProduct(),
        this.getGoogleShoppingCustomLabel0(),
        this.getGoogleShoppingCustomLabel1(),
        this.getGoogleShoppingCustomLabel2(),
        this.getGoogleShoppingCustomLabel3(),
        this.getGoogleShoppingCustomLabel4(),
        this.getVariantImage(),
        this.getVariantWeightUnit(),
        this.getVariantTaxCode(),
        this.getCostPerItem(),
        this.getStatus()
    );
  }

  public static StockRepresentation fromPartialLine(List<String> token) {
    final String title = Optional.ofNullable(token.get(1)).orElse("");
    return new StockRepresentation(
        normalizeThenReturn(title, token, getObjectIndex(0)),
        normalizeThenReturn(title, token, getObjectIndex(1)),
        normalizeThenReturn(title, token, getObjectIndex(2)),
        normalizeThenReturn(title, token, getObjectIndex(3)),
        normalizeThenReturn(title, token, getObjectIndex(4)),
        normalizeThenReturn(title, token, getObjectIndex(5)),
        normalizeThenReturn(title, token, getObjectIndex(6)),
        normalizeThenReturn(title, token, getObjectIndex(7)),
        normalizeThenReturn(title, token, getObjectIndex(8)),
        normalizeThenReturn(title, token, getObjectIndex(9)),
        normalizeThenReturn(title, token, getObjectIndex(10)),
        normalizeThenReturn(title, token, getObjectIndex(11)),
        normalizeThenReturn(title, token, getObjectIndex(12)),
        normalizeThenReturn(title, token, getObjectIndex(13)),
        normalizeThenReturn(title, token, getObjectIndex(14)),
        normalizeThenReturn(title, token, getObjectIndex(15)),
        normalizeThenReturn(title, token, getObjectIndex(16)),
        normalizeThenReturn(title, token, getObjectIndex(17)),
        normalizeThenReturn(title, token, getObjectIndex(18)),
        normalizeThenReturn(title, token, getObjectIndex(19)),
        normalizeThenReturn(title, token, getObjectIndex(20)),
        normalizeThenReturn(title, token, getObjectIndex(21)),
        normalizeThenReturn(title, token, getObjectIndex(22)),
        normalizeThenReturn(title, token, getObjectIndex(23)),
        normalizeThenReturn(title, token, getObjectIndex(24)),
        normalizeThenReturn(title, token, getObjectIndex(25)),
        normalizeThenReturn(title, token, getObjectIndex(26)),
        normalizeThenReturn(title, token, getObjectIndex(27)),
        normalizeThenReturn(title, token, getObjectIndex(28)),
        normalizeThenReturn(title, token, getObjectIndex(29)),
        normalizeThenReturn(title, token, getObjectIndex(30))
    );
  }

  private static String normalizeThenReturn(String title, List<String> tokens, int objectIndex) {

    if (AppConstants.constantIndexOfInterest().contains(objectIndex))
      return AppConstants.ConstantTypes.deduceValueFrom(title, objectIndex).trim();
    else return Optional.ofNullable(tokens.get(AppConstants.indicesSuppliedByUsers().indexOf(objectIndex))).orElse("");
  }

  private static int getObjectIndex(int position) {
    return AppConstants.indexOfInterest().get(position);
  }

}
