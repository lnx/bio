package cn.acc.bcc.health.picasso.client;

import java.io.File;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.mime.TypedFile;
import cn.acc.bcc.health.picasso.client.common.ImageSize;
import cn.acc.bcc.health.picasso.client.model.ImageMeta;
import cn.acc.bcc.health.picasso.client.service.PicassoService;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PicassoClient {

	private final String url;

	private final Gson gson;

	private final RestAdapter restAdapter;

	private final PicassoService picassoService;

	public PicassoClient(String host, int port) {
		url = String.format("http://%s:%d", host, port);
		gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		restAdapter = new RestAdapter.Builder().setEndpoint(url).setConverter(new GsonConverter(gson)).build();
		picassoService = restAdapter.create(PicassoService.class);
	}

	public ImageMeta upload(File file) {
		return picassoService.upload(new TypedFile("multipart/form-data", file));
	}

	public String getImageUrl(String hash) {
		return String.format("%s/%s", url, hash);
	}

	public String getImageUrl(String hash, ImageSize size) {
		return String.format("%s/%s?size=%s", url, hash, size);
	}

	public ImageMeta getImageMeta(String hash) {
		return picassoService.getImageMeta(hash);
	}

	/**
	 * Usage
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		File imageFile = new File("/Users/long/pride.png");
		String hash = "415f60246e09aeb701da495ae8c82e636d10e1ae";

		PicassoClient pc = new PicassoClient("localhost", 5000);
		System.out.println(pc.upload(imageFile).toJson());
		System.out.println(pc.getImageUrl(hash));
		System.out.println(pc.getImageUrl(hash, ImageSize.large));
		System.out.println(pc.getImageMeta(hash).toJson());
	}

}
