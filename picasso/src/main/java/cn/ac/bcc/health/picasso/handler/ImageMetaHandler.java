package cn.ac.bcc.health.picasso.handler;

import spark.Request;
import spark.Response;
import cn.ac.bcc.health.picasso.annotation.Path;
import cn.ac.bcc.health.picasso.core.Imager;

@Path("/:hash/meta")
public class ImageMetaHandler extends Handler {

	@Override
	protected Object get(Request request, Response response) {
		response.type("application/json");
		return Imager.getImageMeta(request.params("hash")).toJson();
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
