package cn.ac.bcc.health.picasso.handler;

import java.util.Set;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import cn.ac.bcc.health.picasso.App;
import cn.ac.bcc.health.picasso.annotation.Path;
import cn.ac.bcc.health.picasso.common.HttpMethod;

public abstract class Handler implements Route {

	private static final Logger logger = LoggerFactory.getLogger(Handler.class);

	public static void register() {
		Reflections reflections = new Reflections(App.class.getPackage().getName());
		Set<Class<? extends Handler>> subTypes = reflections.getSubTypesOf(Handler.class);
		for (Class<?> type : subTypes) {
			try {
				type.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public Handler() {
		final String path = getPath();
		Spark.get(path, this);
		Spark.post(path, this);
		Spark.put(path, this);
		Spark.delete(path, this);
	}

	public Object handle(Request request, Response response) throws Exception {
		switch (request.requestMethod()) {
		case HttpMethod.GET:
			return get(request, response);
		case HttpMethod.POST:
			return post(request, response);
		case HttpMethod.PUT:
			return put(request, response);
		case HttpMethod.DELETE:
			return delete(request, response);
		default:
			return null;
		}
	}

	protected abstract Object get(Request request, Response response);

	protected abstract Object post(Request request, Response response);

	protected abstract Object put(Request request, Response response);

	protected abstract Object delete(Request request, Response response);

	private String getPath() {
		String ret = "";
		Class<? extends Handler> cls = this.getClass();
		Path path = cls.getAnnotation(Path.class);
		if (path != null) {
			ret = path.value();
		} else {
			logger.error("You should define a Path for {}", this.getClass().getName());
			System.exit(-1);
		}
		return ret;
	}

}
