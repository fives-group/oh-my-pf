package cat.wars.ompf.session;

import cat.wars.ompf.builder.yml.YAMLConfigBuilder;
import cat.wars.ompf.model.Configuration;
import cat.wars.ompf.session.defaults.DefaultSQLSessionFactory;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: oh-my-pf
 * @description:
 * @author: Wars
 * @created: 2020-04-03 14:04
 */
@Log4j
public class SQLSessionFactoryBuilder {

  public SQLSessionFactory build(InputStream in) {
    Configuration configuration = null;
    try {
      configuration = new YAMLConfigBuilder().parseConfig(in);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
    return new DefaultSQLSessionFactory(configuration);
  }
}
