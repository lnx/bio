package cn.ac.bcc.health.picasso.common;

import java.io.File;

public class FileUtil {

	public static void mkdir(String path) {
		final File dir = new File(path);
		if (!dir.isDirectory()) {
			if (dir.isFile()) {
				dir.delete();
			}
			dir.mkdirs();
		}
	}

}
