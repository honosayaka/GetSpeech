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
				return true;//确实没有这个id
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
		// 直到getInputStream()方法调用请求才真正发送出去
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		// cookieVal = connection.getHeaderField("set-cookie");
		StringBuilder sb = new StringBuilder(), sb1 = new StringBuilder();
		String temp = null;
		while ((temp = br.readLine()) != null) {
			if (!isLogged) {
				return "请查看上方数据,是否确保正确登陆!";
			}
			if (temp.trim().startsWith("window.location")) {
				String[] tempArry = temp.split("=");
				scode = tempArry[1].substring(27, 59);// 获取scode
			}
			if (temp.trim().startsWith("<a href=\"/home/Jz/show/id")) {// 获取课程id
				String[] tempArry = temp.split("=");
				id = tempArry[1].substring(18, 21);
			}
			if (temp.trim().startsWith("<td>剩余名额：")) {// 获取课程名额
				String[] tempArry = temp.split("：");
				int left = Integer.valueOf(tempArry[1].substring(0, 1));
				if (left != 0) {
					if (check(id)) {// 不存在id
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
			return "暂时没有检测到课程" + "\n" + "scode为" + scode + "\n" + "当前共获得了" + gettedTimes + "已获得的讲座id为"
					+ sb1.toString();
		}
		return "暂时没有检测到课程" + "\n" + "scode为" + scode + "\n" + "当前共获得了" + gettedTimes;
	}

	public String justGet(String urlStr) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Cookie", cookieVal);
		connection.connect();
		// 直到getInputStream()方法调用请求才真正发送出去
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
		return "\n" + "得到了一门" + "id为" + id + "\n" + "当前共获得了" + gettedTimes;
	}
	
	public String onceGet(String urlStr) throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Cookie", cookieVal);
		connection.connect();
		// 直到getInputStream()方法调用请求才真正发送出去
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
		connection.setDoOutput(true); // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
		connection.setDoInput(true); // 设置连接输入流为true
		connection.setRequestMethod("POST"); // 设置请求方式为post
		connection.setUseCaches(false); // post请求缓存设为false
		connection.setInstanceFollowRedirects(true);
		//// 设置该HttpURLConnection实例是否自动执行重定向
		// 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
		// application/x-javascript text/xml->xml数据 application/x-javascript->json对象
		//// application/x-www-form-urlencoded->表单数据
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.connect();

		// 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
		DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
		// String parm = "storeId=" + URLEncoder.encode("32", "utf-8");
		// //URLEncoder.encode()方法 为字符串进行编码
		// String parm = "username1=1601043614&passward=123456&verify="
		// dataout.writeBytes(URLEncoder.encode(parm, "utf-8"));
		dataout.writeBytes(parm);
		dataout.flush();
		dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)
		// 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
		BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
		// cookieVal = connection.getHeaderField("set-cookie");
		String temp = null;
		StringBuilder sb = new StringBuilder(); // 用来存储响应数据
		while ((temp = bf.readLine()) != null) {
			if (temp.trim().startsWith("<title>登录成功！")) {
				isLogged = true;
				return "已经检测到登陆成功标识!" + "\n";
			}
			sb.append(temp);
			sb.append("\n");
		}
		bf.close();
		// 销毁连接
		connection.disconnect();
		return sb.toString() + "可能登陆失败!" + "\n"; // 重要且易忽略步骤 (关闭流,切记!)*/
		// System.out.println(cookieVal);
	}
}
