package cat.wars.ompf.builder.yml;

import cat.wars.ompf.io.Resource;
import cat.wars.ompf.model.Configuration;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class YAMLConfigBuilderTest {

  @Test
  void parseConfig() throws IOException {
    Configuration configuration =
        new YAMLConfigBuilder().parseConfig(Resource.getResourceAsStream("SQLMapConfig.yml"));
    assertNotNull(configuration);
    assertNotNull(configuration.getDataSource());
    assertNotNull(configuration.getMapperMap());
  }
}
