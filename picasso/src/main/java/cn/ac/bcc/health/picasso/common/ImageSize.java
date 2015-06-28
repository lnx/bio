package cn.ac.bcc.health.picasso.common;

public enum ImageSize {

	LARGE(Config.Image.LARGE), MEDIUM(Config.Image.MEDIUM), SMALL(Config.Image.SMALL);

	public final int value;

	private ImageSize(int value) {
		this.value = value;
	}

}
