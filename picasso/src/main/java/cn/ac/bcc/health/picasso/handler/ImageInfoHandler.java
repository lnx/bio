package cn.ac.bcc.health.picasso.handler;

import spark.Request;
import spark.Response;
import cn.ac.bcc.health.picasso.annotation.Path;

@Path("/:hash/info")
public class ImageInfoHandler extends Handler {

	@Override
	protected Object get(Request request, Response response) {
		return "info is: " + request.params("hash");
	}

	@Override
	protected Object post(Request request, Response response) {
		return null;
	}

	@Override
	protected Object put(Request request, Response response) {
		return null;
	}

	@Override
	protected Object delete(Request request, Response response) {
		return null;
	}

}
