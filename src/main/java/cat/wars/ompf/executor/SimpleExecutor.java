package cat.wars.ompf.executor;

import cat.wars.ompf.model.Configuration;
import cat.wars.ompf.model.MapperStatement;
import cat.wars.ompf.util.mybatis.GenericTokenParser;
import cat.wars.ompf.util.mybatis.ParameterMapping;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: oh-my-pf
 * @description:
 * @author: Wars
 * @created: 2020-04-06 00:13
 */
public class SimpleExecutor implements Executor {

  @Override
  public <E> List<E> query(
      Configuration configuration, MapperStatement mapperStatement, Object... params)
      throws Exception {
    String sql = mapperStatement.getSql();
    // Parser
    GenericTokenParser parser = new GenericTokenParser("#{", "}");
    List<ParameterMapping> parameterMappings = parser.getParameterMappings();
    String parsed = parser.parse(sql);
    // Query init
    Connection connection = configuration.getDataSource().getConnection();
    PreparedStatement preparedStatement = connection.prepareStatement(parsed);
    // Parameter
    String parameterType = mapperStatement.getParameterType();
    Class<?> parameterClass;
    if (null != parameterType
        && !"".equals(parameterType)
        && null != (parameterClass = getClassType(parameterType))) {
      for (int i = 0; i < parameterMappings.size(); i++) {
        ParameterMapping parameterMapping = parameterMappings.get(i);
        Field field = parameterClass.getDeclaredField(parameterMapping.getContent());
        field.setAccessible(true);
        Object param = field.get(params[0]);
        if (null != param) preparedStatement.setObject(1 + i, param);
      }
    }

    // Executor and result
    Class<?> resultClass = getClassType(mapperStatement.getResultType());
    if (null == resultClass) throw new RuntimeException("Result class error");
    List<Object> resultList = new ArrayList<>();
    ResultSet resultSet = preparedStatement.executeQuery();
    while (resultSet.next()) {
      Object result = resultClass.getDeclaredConstructor().newInstance();
      ResultSetMetaData metaData = resultSet.getMetaData();
      for (int i = 1; i <= metaData.getColumnCount(); i++) {
        String columnName = metaData.getColumnName(i);
        Object columnValue = resultSet.getObject(columnName);

        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultClass);
        propertyDescriptor.getWriteMethod().invoke(result, columnValue);
      }
      resultList.add(result);
    }
    return (List<E>) resultList;
  }

  private Class<?> getClassType(String typeClassName) {
    try {
      return null == typeClassName ? null : Class.forName(typeClassName);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }
}
