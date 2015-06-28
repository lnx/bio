package cn.ac.bcc.health.picasso.core;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

import org.apache.commons.codec.digest.DigestUtils;
import org.imgscalr.Scalr;

import spark.utils.IOUtils;
import cn.ac.bcc.health.picasso.common.Config;
import cn.ac.bcc.health.picasso.common.ImageSize;
import cn.ac.bcc.health.picasso.model.ImageMeta;

public class Imager {

	public static String save(byte[] bytes) {
		String hash = DigestUtils.shaHex(bytes);
		File imageFile = getImageFile(hash);
		if (!imageFile.isFile()) {
			try (FileOutputStream fos = new FileOutputStream(imageFile)) {
				fos.write(bytes);
			} catch (IOException e) {
				e.printStackTrace();
			}
			save(bytes, hash, ImageSize.LARGE);
			save(bytes, hash, ImageSize.MEDIUM);
			save(bytes, hash, ImageSize.SMALL);
		}
		return hash;
	}

	public static ImageMeta getImageMeta(String hash) {
		File imageFile = getImageFile(hash);
		if (imageFile.isFile()) {
			try {
				BufferedImage bufferedImage = ImageIO.read(imageFile);
				String format = getImageFormat(imageFile);
				int width = bufferedImage.getWidth();
				int height = bufferedImage.getHeight();
				return new ImageMeta(hash, format, width, height);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static byte[] getImageBytes(String hash) {
		try {
			return IOUtils.toByteArray(new FileInputStream(getImageFile(hash)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] getImageBytes(String hash, ImageSize size) {
		try {
			return IOUtils.toByteArray(new FileInputStream(getImageFile(hash, size)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static File getImageFile(String hash) {
		return new File(Config.Image.DIRECTORY + File.separator + hash);
	}

	private static File getImageFile(String hash, ImageSize size) {
		return new File(Config.Image.DIRECTORY + File.separator + hash + "_" + size.name().toLowerCase());
	}

	private static void save(byte[] bytes, String hash, ImageSize size) {
		try {
			BufferedImage original = ImageIO.read(new ByteArrayInputStream(bytes));
			BufferedImage target = Scalr.resize(original, size.value);
			ImageIO.write(target, "jpg", getImageFile(hash, size));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getImageFormat(File imageFile) {
		try {
			Iterator<ImageReader> it = ImageIO.getImageReaders(ImageIO.createImageInputStream(imageFile));
			if (it.hasNext()) {
				return it.next().getFormatName();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
