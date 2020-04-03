package cat.wars.ompf.model;

import lombok.Getter;
import lombok.Setter;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @program: oh-my-pf
 * @description:
 * @author: Wars
 * @created: 2020-04-03 13:04
 */

@Getter
@Setter
public class Configuration {

	/**
	 * Datasource config
	 */
	private DataSource dataSource;

	/**
	 * key: Statement id
	 * value: {@link MapperStatement}
	 */
	private Map<String, MapperStatement> mapperStatementMap;
}
