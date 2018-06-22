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
import java.util.List;

public class Logger {

	private String cookieVal = null;
	private String scode = null;
	private String id;
	private static int gettedTimes;
	private List<Speechbean> list;
	boolean isLogged = false;

	
	public Logger(String cookies,List<Speechbean> list) {
		cookieVal = cookies;
		this.list = list;
	}
	private boolean check(String idString) {
		for(Speechbean speech:list) {
			boolean b =  speech.getId().equals(idString);
			if(!b) {
				return true;//ȷʵû�����id
			}
		}
		return false;
	}
	
	public String getCookies() {
		return cookieVal;
	}

	public static boolean isFull() {
		return gettedTimes == 5;
	}

	public String doGet(String urlStr) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Cookie", cookieVal);
		connection.connect();
		// ֱ��getInputStream()��������������������ͳ�ȥ
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		// cookieVal = connection.getHeaderField("set-cookie");
		StringBuilder sb = new StringBuilder(), sb1 = new StringBuilder();
		String temp = null;
		while ((temp = br.readLine()) != null) {
			if (!isLogged) {
				return "��鿴�Ϸ�����,�Ƿ�ȷ����ȷ��½!";
			}
			if (temp.trim().startsWith("window.location")) {
				String[] tempArry = temp.split("=");
				scode = tempArry[1].substring(27, 59);// ��ȡscode
			}
			if (temp.trim().startsWith("<a href=\"/home/Jz/show/id")) {// ��ȡ�γ�id
				String[] tempArry = temp.split("=");
				id = tempArry[1].substring(18, 21);
			}
			if (temp.trim().startsWith("<td>ʣ�����")) {// ��ȡ�γ�����
				String[] tempArry = temp.split("��");
				int left = Integer.valueOf(tempArry[1].substring(0, 1));
				if (left != 0) {
					if (check(id)) {// ������id
						list.add(new Speechbean(id,""));
						return justGet("http://wxkq.niit.edu.cn/Jz/sign/id/" + id + "/s_code/" + scode);
					} else {
						continue;
					}

				}
			}
			sb.append(temp);
			sb.append("\n");
		}
		br.close();
		connection.disconnect();
		if (list.size() != 0) {
			for (Speechbean s : list) {
				sb1.append(s.getId() + ",");
			}
			return "��ʱû�м�⵽�γ�" + "\n" + "scodeΪ" + scode + "\n" + "��ǰ�������" + gettedTimes + "�ѻ�õĽ���idΪ"
					+ sb1.toString();
		}
		return "��ʱû�м�⵽�γ�" + "\n" + "scodeΪ" + scode + "\n" + "��ǰ�������" + gettedTimes;
	}

	public String justGet(String urlStr) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Cookie", cookieVal);
		connection.connect();
		// ֱ��getInputStream()��������������������ͳ�ȥ
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		// cookieVal = connection.getHeaderField("set-cookie");
		StringBuilder sb = new StringBuilder();
		String temp = null;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
			sb.append("\n");
		}
		br.close();
		connection.disconnect();
		gettedTimes++;
		return "\n" + "�õ���һ��" + "idΪ" + id + "\n" + "��ǰ�������" + gettedTimes;
	}
	
	public String onceGet(String urlStr) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Cookie", cookieVal);
		connection.connect();
		// ֱ��getInputStream()��������������������ͳ�ȥ
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		// cookieVal = connection.getHeaderField("set-cookie");
		StringBuilder sb = new StringBuilder();
		String temp = null;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
			sb.append("\n");
		}
		br.close();
		connection.disconnect();
		gettedTimes++;
		return sb.toString();
	}

	public String doPost(String urlString, String parm) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Cookie", cookieVal);
		connection.setDoOutput(true); // �������������Ϊtrue,Ĭ��false (post �����������ķ�ʽ��ʽ�Ĵ��ݲ���)
		connection.setDoInput(true); // ��������������Ϊtrue
		connection.setRequestMethod("POST"); // ��������ʽΪpost
		connection.setUseCaches(false); // post���󻺴���Ϊfalse
		connection.setInstanceFollowRedirects(true);
		//// ���ø�HttpURLConnectionʵ���Ƿ��Զ�ִ���ض���
		// ��������ͷ����ĸ������� (����Ϊ�������ݵ�����,����Ϊ����urlEncoded�������from����)
		// application/x-javascript text/xml->xml���� application/x-javascript->json����
		//// application/x-www-form-urlencoded->������
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.connect();

		// �������������,�����������������Я���Ĳ���,(�������Ϊ?���������)
		DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
		// String parm = "storeId=" + URLEncoder.encode("32", "utf-8");
		// //URLEncoder.encode()���� Ϊ�ַ������б���
		// String parm = "username1=1601043614&passward=123456&verify="
		// dataout.writeBytes(URLEncoder.encode(parm, "utf-8"));
		dataout.writeBytes(parm);
		dataout.flush();
		dataout.close(); // ��Ҫ���׺��Բ��� (�ر���,�м�!)
		// ���ӷ�������,�����������Ӧ (�����ӻ�ȡ������������װΪbufferedReader)
		BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		// cookieVal = connection.getHeaderField("set-cookie");
		String temp = null;
		StringBuilder sb = new StringBuilder(); // �����洢��Ӧ����
		while ((temp = bf.readLine()) != null) {
			if (temp.trim().startsWith("<title>��¼�ɹ���")) {
				isLogged = true;
				return "�Ѿ���⵽��½�ɹ���ʶ!" + "\n";
			}
			sb.append(temp);
			sb.append("\n");
		}
		bf.close();
		// ��������
		connection.disconnect();
		return sb.toString() + "���ܵ�½ʧ��!" + "\n"; // ��Ҫ���׺��Բ��� (�ر���,�м�!)*/
		// System.out.println(cookieVal);
	}
}
