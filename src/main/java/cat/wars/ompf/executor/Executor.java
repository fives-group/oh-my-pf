package cat.wars.ompf.executor;

import cat.wars.ompf.model.Configuration;
import cat.wars.ompf.model.MapperStatement;

import java.util.List;

/**
 * @program: oh-my-pf
 * @description:
 * @author: Wars
 * @created: 2020-04-06 00:12
 */
public interface Executor {

  <E> List<E> query(Configuration configuration, MapperStatement mapperStatement, Object... params)
      throws Exception;
}
