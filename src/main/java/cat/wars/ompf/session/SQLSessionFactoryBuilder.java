package cat.wars.ompf.session;

import cat.wars.ompf.builder.yml.YAMLConfigBuilder;
import cat.wars.ompf.model.Configuration;

import java.io.InputStream;

/**
 * @program: oh-my-pf
 * @description:
 * @author: Wars
 * @created: 2020-04-03 14:04
 */
public class SQLSessionFactoryBuilder {

	public SQLSessionFactory build(InputStream in) {
		Configuration configuration = new YAMLConfigBuilder().parseConfig(in);

		return null;
	}
}
