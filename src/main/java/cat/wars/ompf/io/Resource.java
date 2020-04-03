package cat.wars.ompf.io;

import java.io.InputStream;

/**
 * @program: oh-my-pf
 * @description: Reader configure file
 * @author: Wars
 * @created: 2020-04-02 01:20
 */
public class Resource {

	public static InputStream getResourceAsStream(String path){
		return ClassLoader.getSystemResourceAsStream(path);
	}
}
