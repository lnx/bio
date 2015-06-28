package cn.ac.bcc.health.picasso.common;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Config {

	private static Configuration CONFIG = null;

	static {
		try {
			CONFIG = new PropertiesConfiguration("config.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static class Service {

		public static String HOST = CONFIG.getString("service.host");
		public static int PORT = CONFIG.getInt("service.port");

	}

	public static class Image {

		public static String DIRECTORY = CONFIG.getString("image.directory");
		public static String DIRECTORY_TMP = CONFIG.getString("image.directory.tmp");
		public static int LARGE = CONFIG.getInt("image.large");
		public static int MEDIUM = CONFIG.getInt("image.medium");
		public static int SMALL = CONFIG.getInt("image.small");

	}

}
