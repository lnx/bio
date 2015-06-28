package cn.ac.bcc.health.picasso;

import spark.Spark;
import cn.ac.bcc.health.picasso.common.Config;
import cn.ac.bcc.health.picasso.common.FileUtil;
import cn.ac.bcc.health.picasso.handler.Handler;

public class App {

	public static void start() {
		checkDirs();
		initServer();
	}

	private static void checkDirs() {
		FileUtil.mkdir(Config.Image.DIRECTORY);
		FileUtil.mkdir(Config.Image.DIRECTORY_TMP);
	}

	private static void initServer() {
		Spark.ipAddress(Config.Service.HOST);
		Spark.port(Config.Service.PORT);
		Handler.register();
	}

	public static void main(String[] args) {
		start();
	}

}
