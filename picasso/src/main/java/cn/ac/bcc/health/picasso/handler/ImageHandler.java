package cn.ac.bcc.health.picasso.handler;

import java.io.IOException;

import spark.Request;
import spark.Response;
import cn.ac.bcc.health.picasso.annotation.Path;
import cn.ac.bcc.health.picasso.common.ImageSize;
import cn.ac.bcc.health.picasso.core.Imager;

@Path("/:hash")
public class ImageHandler extends Handler {

	@Override
	protected Object get(Request request, Response response) {
		try {
			response.type("image/jpeg");
			String hash = request.params("hash");
			ImageSize size = getImageSize(request);
			if (size == null) {
				response.raw().getOutputStream().write(Imager.getImageBytes(hash));
			} else {
				response.raw().getOutputStream().write(Imager.getImageBytes(hash, size));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
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

	private ImageSize getImageSize(Request request) {
		String sizeParam = request.queryParams("size");
		for (ImageSize size : ImageSize.values()) {
			if (size.name().equalsIgnoreCase(sizeParam)) {
				return size;
			}
		}
		return null;
	}

}
