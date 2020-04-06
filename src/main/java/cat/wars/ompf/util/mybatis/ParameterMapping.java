package cat.wars.ompf.util.mybatis;

import lombok.Getter;
import lombok.Setter;

/**
 * @program: oh-my-pf
 * @description:
 * @author: Wars
 * @created: 2020-04-06 01:02
 */
@Getter
@Setter
public class ParameterMapping {

  public ParameterMapping(String content) {
    this.content = content;
  }

  private String content;
}
