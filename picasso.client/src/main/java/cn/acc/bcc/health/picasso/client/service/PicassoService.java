package cn.acc.bcc.health.picasso.client.service;

import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;
import cn.acc.bcc.health.picasso.client.model.ImageMeta;

public interface PicassoService {

	@Multipart
	@PUT("/")
	ImageMeta upload(@Part("image") TypedFile image);

	@GET("/{hash}/meta")
	ImageMeta getImageMeta(@Path("hash") String hash);

}
