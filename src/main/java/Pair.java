public class Pair {

  private String columnName;
  private int columnIndex;

  private Pair(String columnName, int columnIndex) {
    this.columnName = columnName;
    this.columnIndex = columnIndex;
  }

  public static Pair of(String columnName, int columnIndex) {
    return new Pair(columnName, columnIndex);
  }

  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public int getColumnIndex() {
    return columnIndex;
  }

  public void setColumnIndex(int columnIndex) {
    this.columnIndex = columnIndex;
  }

  @Override
  public String toString() {
    return "StockStructure{" +
        "columnName='" + columnName + '\'' +
        ", columnIndex='" + columnIndex + '\'' +
        '}';
  }
}
