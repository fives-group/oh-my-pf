package cat.wars.ompf.builder.yml;

import cat.wars.ompf.model.Configuration;
import cat.wars.ompf.model.MapperStatement;
import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * @program: oh-my-pf
 * @description:
 * @author: Wars
 * @created: 2020-04-03 14:09
 */
@Log4j
public class YAMLConfigBuilder {

  private Configuration configuration;

  public YAMLConfigBuilder() {
    this.configuration = new Configuration();
  }

  public Configuration parseConfig(InputStream in) throws IOException {
    YamlMapping configMapping = Yaml.createYamlInput(in).readYamlMapping();
    // Parse datasource
    YamlMapping datasourceMapping = configMapping.yamlMapping("datasource");
    configuration.setDataSource(parseDataSource(datasourceMapping));
    // Parse mappers
    Map<String, Map<String, MapperStatement>> mapperMap =
        new YAMLMapperBuilder().parseMapper(configMapping);
    configuration.setMapperMap(mapperMap);
    return configuration;
  }

  private DataSource parseDataSource(YamlMapping datasourceMapping) {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setDriverClassName(datasourceMapping.string("driver-class-name"));
    dataSource.setJdbcUrl(datasourceMapping.string("url"));
    dataSource.setUsername(datasourceMapping.string("username"));
    dataSource.setPassword(datasourceMapping.string("password"));
    return dataSource;
  }
}
