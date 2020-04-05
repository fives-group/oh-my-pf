package cat.wars.ompf.builder.yml;

import cat.wars.ompf.io.Resource;
import cat.wars.ompf.model.Configuration;
import cat.wars.ompf.model.MapperStatement;
import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class YAMLMapperBuilderTest {

  @Test
  void parseMapper() throws IOException {
    InputStream mapperStream = Resource.getResourceAsStream("SQLMapConfig.yml");
    YamlMapping mapperMapping = Yaml.createYamlInput(mapperStream).readYamlMapping();
    Map<String, Map<String, MapperStatement>> mapperMap =
        new YAMLMapperBuilder().parseMapper(mapperMapping);

    assertNotNull(mapperMap);
    Map<String, MapperStatement> mapperStatementMap = mapperMap.get("cat.wars.ompf.dao.UserMapper");
    assertNotNull(mapperStatementMap);
    MapperStatement mapperStatement = mapperStatementMap.get("findList");
    assertNotNull(mapperStatement);
    assertNotNull(mapperStatement.getId());
    assertNotNull(mapperStatement.getType());
    assertNotNull(mapperStatement.getParameterType());
    assertNotNull(mapperStatement.getSql());
    assertNotNull(mapperStatement.getResultType());
  }

  @Test
  void parseStatements() {}
}
