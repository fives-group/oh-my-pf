package cat.wars.ompf.builder.yml;

import cat.wars.ompf.io.Resource;
import cat.wars.ompf.model.MapperStatement;
import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlSequence;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: oh-my-pf
 * @description:
 * @author: Wars
 * @created: 2020-04-03 14:09
 */
@Log4j
public class YAMLMapperBuilder {

  private Map<String, Map<String, MapperStatement>> mapperMap;

  public YAMLMapperBuilder() {
    mapperMap = new HashMap<>();
  }

  /**
   * Parse mapper map
   *
   * @param configMapping Yaml config mapping
   * @return Mapper map
   * @throws IOException Not found config file
   */
  public Map<String, Map<String, MapperStatement>> parseMapper(YamlMapping configMapping)
      throws IOException {
    YamlMapping mapperMapping = configMapping.yamlMapping("mapper");
    YamlSequence mapperList = mapperMapping.yamlSequence("resource");

    for (int i = 0; i < mapperList.size(); i++) { // Loop mapper resource
      String mapperPath = mapperList.string(i);
      InputStream mapperStream = Resource.getResourceAsStream(mapperPath);
      YamlMapping mapper = Yaml.createYamlInput(mapperStream).readYamlMapping();

      String namespace = mapper.string("namespace");
      YamlSequence mapperStatements = mapper.yamlSequence("statement");

      if (null != mapperStatements && 0 != mapperStatements.size()) { // Put mapper
        Map<String, MapperStatement> mapperStatementMap = parseStatements(mapperStatements);
        this.mapperMap.put(namespace, mapperStatementMap);
      }
    }
    return mapperMap;
  }

  /**
   * Parse select mapper statement map
   *
   * @param selectList Yaml statement list
   * @return Mapper statement map
   */
  public Map<String, MapperStatement> parseStatements(YamlSequence selectList) {
    Map<String, MapperStatement> mapperStatementMap = new HashMap<>();

    for (int i = 0; i < selectList.size(); i++) { // Loop selects
      YamlMapping selectItem = selectList.yamlMapping(i);
      // Pack statement
      String id = selectItem.string("id");
      String type = selectItem.string("type");
      String parameterType = selectItem.string("parameterType");
      String sql = selectItem.string("sql");
      String resultType = selectItem.string("resultType");
      MapperStatement mapperStatement =
          new MapperStatement(id, type, parameterType, sql, resultType);
      // Put mapper statement
      mapperStatementMap.put(id, mapperStatement);
    }
    return mapperStatementMap;
  }
}
