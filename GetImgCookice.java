package cn.hoNoSayaka.jt;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.Image;

import javax.imageio.ImageIO;

public class GetImgCookice {
	
	protected static String theCookie = null; 
	protected static Image image = null;
	
	   public GetImgCookice(String uri) {
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
	   
	   public String getCookie() {
		  return  theCookie  ;
		 
	   }
	   public Image getImage() {
		   return image;
	   }

}
