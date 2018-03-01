package cn.hoNoSayaka.jt;



import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
public class Logger {

		public static String cookieVal = "";
		
		public static void init(String theCookie) {
			cookieVal = theCookie;
		}
		
		public static String doGet(String urlStr) throws IOException{
			URL url = new URL(urlStr);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Cookie", cookieVal);
			connection.connect();
			//ֱ��getInputStream()��������������������ͳ�ȥ
			BufferedReader br = new BufferedReader(new InputStreamReader
					(connection.getInputStream(),"utf-8"));
			cookieVal = connection.getHeaderField("set-cookie");
			StringBuilder sb = new StringBuilder();
			 while (br.readLine() != null) {
		            sb.append(br.readLine());
		            sb.append("\n");
		        }
		        br.close();
			br.close();
			connection.disconnect();
			return sb.toString();
		}
		
		public static void doPost(String urlString,
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
			dataout.writeBytes(URLEncoder.encode(parm, "utf-8")); 
			dataout.flush();
			dataout.close(); // ��Ҫ���׺��Բ��� (�ر���,�м�!)           
	        // ���ӷ�������,�����������Ӧ  (�����ӻ�ȡ������������װΪbufferedReader)
	/*        BufferedReader bf = new BufferedReader(new InputStreamReader
	        		(connection.getInputStream(),"utf-8"));
	        StringBuilder sb = new StringBuilder(); // �����洢��Ӧ����           
	        while (bf.readLine() != null) {
	            sb.append(bf.readLine());
	            sb.append("\n");
	        }
	        bf.close();
	         // ��������
	        System.out.println(sb.toString());   // ��Ҫ���׺��Բ��� (�ر���,�м�!)*/
	        cookieVal = connection.getHeaderField("set-cookie");
	        connection.disconnect();
		}
	}
