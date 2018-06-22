package cn.hoNoSayaka.jt;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class JDBCUtil {

	static {
		isDatabased();
	}

	public static void isDatabased() {
		File dir = new File("e:/Program Files/honosayaka");
		if (!dir.exists()) {
			dir.mkdirs();
			writeDataBase();
		}
	}

	public static void writeDataBase() {
		BufferedInputStream in = new BufferedInputStream(
				JDBCUtil.class.getClassLoader().getResourceAsStream("databases/sp.db"));
		byte[] buffer = new byte[1024];
		int len = 0;
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(new File("e:/Program Files/honosayaka/sp.db"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			while ((len = in.read(buffer)) != -1) {
				try {
					out.write(buffer, 0, len);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * public static void main(String[] args) { new JDBCUtil(); }
	 */
}
