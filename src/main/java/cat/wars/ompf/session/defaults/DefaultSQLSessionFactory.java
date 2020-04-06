package cat.wars.ompf.session.defaults;

import cat.wars.ompf.model.Configuration;
import cat.wars.ompf.session.SQLSession;
import cat.wars.ompf.session.SQLSessionFactory;

/**
 * @program: oh-my-pf
 * @description:
 * @author: Wars
 * @created: 2020-04-05 18:52
 */
public class DefaultSQLSessionFactory implements SQLSessionFactory {

  private final Configuration configuration;

  public DefaultSQLSessionFactory(Configuration configuration) {
    this.configuration = configuration;
  }

  @Override
  public SQLSession openSession() {
    return new DefaultSQLSession(configuration);
  }
}
