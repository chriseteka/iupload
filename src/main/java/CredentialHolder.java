public class CredentialHolder {

  private final String apiKey;
  private final String password;
  private final String storeUrl;

  public String getApiKey() {
    return apiKey;
  }

  public String getPassword() {
    return password;
  }

  public String getStoreUrl() {
    return storeUrl;
  }

  private static CredentialHolder INSTANCE;

  public CredentialHolder(String apiKey, String password, String storeUrl) {
    this.apiKey = apiKey;
    this.password = password;
    this.storeUrl = storeUrl;
  }

  public static void CREATE_INSTANCE(String apiKey, String password, String storeUrl) {
    if (INSTANCE == null) INSTANCE = new CredentialHolder(apiKey, password, storeUrl);
  }

  public static CredentialHolder getINSTANCE() {
    return INSTANCE;
  }

  @Override
  public String toString() {
    return "CredentialHolder{" +
        "apiKey='" + apiKey.substring(0, 10) + "*********" + '\'' +
        ", password='" + password.substring(0, 10) + "*********" + '\'' +
        ", storeUrl='" + storeUrl + '\'' +
        '}';
  }
}
