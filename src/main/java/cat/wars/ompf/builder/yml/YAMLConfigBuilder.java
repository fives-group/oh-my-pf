package cat.wars.ompf.builder.yml;

import cat.wars.ompf.model.Configuration;
import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.amihaiemil.eoyaml.YamlSequence;
import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.io.InputStream;

/**
 * @program: oh-my-pf
 * @description:
 * @author: Wars
 * @created: 2020-04-03 14:09
 */
@Log4j
public class YAMLConfigBuilder {

  public Configuration parseConfig(InputStream in) {
    YamlMapping configMapping = null;
    try {
       configMapping = Yaml.createYamlInput(in).readYamlMapping();
    } catch (IOException e) {
      log.error(e.getMessage(), e);
      System.exit(-1);
    }

    YamlMapping datasourceMapping = configMapping.yamlMapping("datasource");
    YamlMapping mapperMapping = configMapping.yamlMapping("mapper");
    String mapperPrefix = mapperMapping.string("prefix");
    YamlSequence mapperList = mapperMapping.yamlSequence("resource");
    return null;
  }
}
