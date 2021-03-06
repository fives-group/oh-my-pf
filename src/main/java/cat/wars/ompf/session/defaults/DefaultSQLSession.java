package cat.wars.ompf.session.defaults;

import cat.wars.ompf.executor.SimpleExecutor;
import cat.wars.ompf.model.Configuration;
import cat.wars.ompf.model.MapperStatement;
import cat.wars.ompf.session.SQLSession;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @program: oh-my-pf
 * @description:
 * @author: Wars
 * @created: 2020-04-05 19:03
 */
public class DefaultSQLSession implements SQLSession {

  private final Configuration configuration;

  public DefaultSQLSession(Configuration configuration) {
    this.configuration = configuration;
  }

  @Override
  public <E> List<E> selectAll(String namespace, String statementId, Object... params) {
    MapperStatement mapperStatement = configuration.getMapperStatement(namespace, statementId);
    try {
      return new SimpleExecutor().query(configuration, mapperStatement, params);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public <T> T selectOne(String namespace, String statementId, Object... params) {
    List<T> resultList = selectAll(namespace, statementId, params);
    if (1 != resultList.size()) {
      throw new RuntimeException("Not found entry, or entry is not unique");
    }
    return resultList.get(0);
  }

  @Override
  public <T> T getMapper(Class<T> mapper) {
    Object proxy =
        Proxy.newProxyInstance(
            this.getClass().getClassLoader(),
            new Class[] {mapper},
            (o, method, objects) -> {
              if (method.getGenericReturnType() instanceof ParameterizedType) {
                return selectAll(method.getDeclaringClass().getName(), method.getName(), objects);
              }
              return selectOne(o.getClass().getPackageName(), method.getName(), objects);
            });
    return (T) proxy;
  }
}
