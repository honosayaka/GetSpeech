package cn.hoNoSayaka.jt;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.imageio.ImageIO;

public class GetUtil {
	private String theCookie = null;
	private Image image = null;

	
	
	public void GetImgCookice(String uri) {
		try {
			URL url = new URL(uri);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET"); // 以Post方式提交表单，默认get方式
			con.connect();
			image = ImageIO.read(con.getInputStream());
			theCookie = con.getHeaderField("set-cookie");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getImage(String path) throws IOException {
		URL u = GetUtil.class.getClassLoader().getResource(path);
		return ImageIO.read(u);
	}

	public String getCookie() {
		return theCookie;

	}

	public Image getImage() {
		return image;
	}
}
