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

  /**
   * Statement id
   */
  private String id;
  /**
   * Parameter type
   */
  private String parameterType;
  /**
   * SQL content
   */
  private String sql;
  /**
   * Result type
   */
  private String resultType;
}
