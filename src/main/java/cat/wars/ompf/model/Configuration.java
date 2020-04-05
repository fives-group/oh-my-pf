package cat.wars.ompf.model;

import lombok.Getter;
import lombok.Setter;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @program: oh-my-pf
 * @description:
 * @author: Wars
 * @created: 2020-04-03 13:04
 */
@Getter
@Setter
public class Configuration {

  /** Datasource config */
  private DataSource dataSource;

  /** Mapper map */
  private Map<String, Map<String, MapperStatement>> mapperMap;
}
