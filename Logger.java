package cn.hoNoSayaka.jt;


/**
 * test
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class Logger {

		public static String cookieVal = null;
		
		public static void init(String theCookie) {
			cookieVal = theCookie;
		}
		
		public static String getCookies(){
			return cookieVal;
		}
		public static String doGet(String urlStr) throws IOException{
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Cookie",cookieVal);
			connection.connect();
			//ֱ��getInputStream()��������������������ͳ�ȥ
			BufferedReader br = new BufferedReader(new InputStreamReader
					(connection.getInputStream(),"utf-8"));
		//	cookieVal = connection.getHeaderField("set-cookie");
			StringBuilder sb = new StringBuilder();
			String temp = null;
			 while ((temp=br.readLine()) != null) {
		            sb.append(temp);
		            sb.append("\n");
		        }
			br.close();
			connection.disconnect();
			return sb.toString();
		}
		
		public static String doPost(String urlString,
				String parm) throws IOException{
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Cookie", cookieVal);
			connection.setDoOutput(true); //�������������Ϊtrue,Ĭ��false (post �����������ķ�ʽ��ʽ�Ĵ��ݲ���)
			connection.setDoInput(true); // ��������������Ϊtrue
			connection.setRequestMethod("POST"); // ��������ʽΪpost
			connection.setUseCaches(false); // post���󻺴���Ϊfalse
			connection.setInstanceFollowRedirects(true); //// ���ø�HttpURLConnectionʵ���Ƿ��Զ�ִ���ض���
			// ��������ͷ����ĸ������� (����Ϊ�������ݵ�����,����Ϊ����urlEncoded�������from����)
	        // application/x-javascript text/xml->xml���� application/x-javascript->json���� application/x-www-form-urlencoded->������
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.connect();
			
			// �������������,�����������������Я���Ĳ���,(�������Ϊ?���������)
			DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
	       // String parm = "storeId=" + URLEncoder.encode("32", "utf-8"); //URLEncoder.encode()����  Ϊ�ַ������б���           
	  //      String parm = "username1=1601043614&passward=123456&verify="
		//	dataout.writeBytes(URLEncoder.encode(parm, "utf-8")); 
			dataout.writeBytes(parm); 
			dataout.flush();
			dataout.close(); // ��Ҫ���׺��Բ��� (�ر���,�м�!)           
	        // ���ӷ�������,�����������Ӧ  (�����ӻ�ȡ������������װΪbufferedReader)
	        BufferedReader bf = new BufferedReader(new InputStreamReader
	        		(connection.getInputStream(),"utf-8"));
	 //       cookieVal = connection.getHeaderField("set-cookie");
	        String temp = null;
	        StringBuilder sb = new StringBuilder(); // �����洢��Ӧ����           
	        while ((temp=bf.readLine()) != null) {
	            sb.append(temp);
	            sb.append("\n");
	        }
	        bf.close();
	         // ��������
	        connection.disconnect();
	        return sb.toString();   // ��Ҫ���׺��Բ��� (�ر���,�м�!)*/
	 //       System.out.println(cookieVal);
		}
	}
