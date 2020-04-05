package cat.wars.ompf.model.enums;

/**
 * @program: oh-my-pf
 * @description:
 * @author: Wars
 * @created: 2020-04-05 17:55
 */
public enum MapperStatementTypeEnum {
  INSERT("insert"),
  DELETE("delete"),
  UPDATE("update"),
  SELECT("select");

  MapperStatementTypeEnum(String value) {
    this.value = value;
  }

  private String value;
}
