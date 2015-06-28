package cn.ac.bcc.health.picasso.handler;

import java.io.File;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import spark.Request;
import spark.Response;
import spark.utils.IOUtils;
import cn.ac.bcc.health.picasso.annotation.Path;
import cn.ac.bcc.health.picasso.common.Config;
import cn.ac.bcc.health.picasso.core.Imager;

@Path("/")
public class UploadHandler extends Handler {

	@Override
	protected Object get(Request request, Response response) {
		return null;
	}

	@Override
	protected Object post(Request request, Response response) {
		return null;
	}

	@Override
	protected Object put(Request request, Response response) {
		response.type("application/json");
		try {
			DiskFileItemFactory disFileItemFactory = new DiskFileItemFactory();
			disFileItemFactory.setRepository(new File(Config.Image.DIRECTORY_TMP));
			ServletFileUpload servletFileUpload = new ServletFileUpload(disFileItemFactory);
			List<FileItem> items = servletFileUpload.parseRequest(request.raw());
			FileItem item = items.stream().filter(e -> "image".equals(e.getFieldName())).findFirst().get();
			String hash = Imager.save(IOUtils.toByteArray(item.getInputStream()));
			return Imager.getImageMeta(hash).toJson();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected Object delete(Request request, Response response) {
		return null;
	}

}
