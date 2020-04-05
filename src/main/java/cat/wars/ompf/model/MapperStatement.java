package cat.wars.ompf.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: oh-my-pf
 * @description:
 * @author: Wars
 * @created: 2020-04-03 12:57
 */
@Getter
@Setter
public class MapperStatement {

  public MapperStatement(String id, String type, String parameterType, String sql, String resultType) {
    this.id = id;
    this.type = type;
    this.parameterType = parameterType;
    this.sql = sql;
    this.resultType = resultType;
  }

  /** Statement id */
  private String id;
  /** Statement type */
  private String type;
  /** Parameter type */
  private String parameterType;
  /** SQL content */
  private String sql;
  /** Result type */
  private String resultType;
}
