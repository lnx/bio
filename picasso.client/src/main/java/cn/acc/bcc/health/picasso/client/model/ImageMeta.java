package cn.acc.bcc.health.picasso.client.model;

import com.google.gson.Gson;

public class ImageMeta {

	private final String hash;
	private final String format;
	private final int width;
	private final int height;

	public ImageMeta(String hash, String format, int width, int height) {
		this.hash = hash;
		this.format = format;
		this.width = width;
		this.height = height;
	}

	public String getHash() {
		return hash;
	}

	public String getFormat() {
		return format;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}

}
