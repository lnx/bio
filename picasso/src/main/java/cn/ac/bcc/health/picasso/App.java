package cn.ac.bcc.health.picasso;

import spark.Spark;
import cn.ac.bcc.health.picasso.common.Config;
import cn.ac.bcc.health.picasso.handler.Handler;

public class App {

	public static void main(String[] args) {
		Spark.ipAddress(Config.Service.HOST);
		Spark.port(Config.Service.PORT);
		Handler.register();
	}

}
