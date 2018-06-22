package cn.hoNoSayaka.jt;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reg {

	static String s;
	private static List<Speechedbean> list;
	static {
		new JDBCConnect();
	//	s = init("d:/tirm2.txt");
		// System.out.println("string inited");
		list = new ArrayList<Speechedbean>();
	}
	public static void setS(String ss) {
		s = ss;
	}

	// public static void p1() {// 匹配课程
	// Pattern p = Pattern.compile(
	// "<a href='/home/Jz/show/id/(\\d{3})'>[\\u4e00-\\u9fa5]*</a></div><div
	// class=\"c_list\"><table class=\"zyz1\" border=\"0\" cellpadding=\"0\"
	// cellspacing=\"1\"><tr><td>(开始时间：\\d{4}年\\d{2}月\\d{2} \\d{2}时\\d{2}分)</td>");
	// Matcher m = p.matcher(s);
	// while (m.find()) {
	// list.add(new Speechbean(m.group(1), m.group(2)));
	// }
	// printlist();
	// }

	public static void p2() {// 匹配已选 group1名称 group2时间 group3结束 group4签走 group5id
		Pattern p = Pattern.compile(
				"<tr><td>( *[\\u3000-\\u9fa5]* *)</td><td>(\\d*-\\d*-\\d* \\d*:\\d*)</td><td>([\\u4e00-\\u9fa5]{3})</td><td>([\\u4e00-\\u9fa5]{3})</td><td><a href='/Jz/show/id/(\\d{3})'");
		Matcher m = p.matcher(s);
		while (m.find()) {
			// public Speechedbean(String id, String stratTime, String name, String ended,
			// String sigined)
			list.add(new Speechedbean(m.group(5), m.group(2), m.group(1), m.group(3), m.group(4)));
		}
		Proceed.appear.append("数据库操作中!");
		reglist();
	}

	public static String init(String file) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			System.out.println("file not found!");
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		String temp = "";
		try {
			while ((temp = br.readLine()) != null) {
				sb.append(temp.trim());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static void reglist() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (Speechedbean s : list) {
			s.regest();
		}
	}
	 
}
