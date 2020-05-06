package cat.wars.ompf.session;

import java.util.List;

/**
 * @program: oh-my-pf
 * @description:
 * @author: Wars
 * @created: 2020-04-05 19:02
 */
public interface SQLSession {

  /**
   * Select all to list
   *
   * @param namespace Mapper namespace
   * @param statementId Mapper statement id
   * @param params Params list
   * @param <E> Result type
   * @return Result list
   */
  <E> List<E> selectAll(String namespace, String statementId, Object... params);

  /**
   * Select one
   *
   * @param namespace Mapper namespace
   * @param statementId Mapper statement id
   * @param params Params list
   * @param <E> Result type
   * @return Result
   */
  <E> E selectOne(String namespace, String statementId, Object... params);

  <T> T getMapper(Class<T> mapper);
}
