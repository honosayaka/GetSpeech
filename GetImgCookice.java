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
		   		con.setRequestMethod("GET"); // ��Post��ʽ�ύ����Ĭ��get��ʽ  
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
