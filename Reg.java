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

	// public static void p1() {// ƥ��γ�
	// Pattern p = Pattern.compile(
	// "<a href='/home/Jz/show/id/(\\d{3})'>[\\u4e00-\\u9fa5]*</a></div><div
	// class=\"c_list\"><table class=\"zyz1\" border=\"0\" cellpadding=\"0\"
	// cellspacing=\"1\"><tr><td>(��ʼʱ�䣺\\d{4}��\\d{2}��\\d{2} \\d{2}ʱ\\d{2}��)</td>");
	// Matcher m = p.matcher(s);
	// while (m.find()) {
	// list.add(new Speechbean(m.group(1), m.group(2)));
	// }
	// printlist();
	// }

	public static void p2() {// ƥ����ѡ group1���� group2ʱ�� group3���� group4ǩ�� group5id
		Pattern p = Pattern.compile(
				"<tr><td>( *[\\u3000-\\u9fa5]* *)</td><td>(\\d*-\\d*-\\d* \\d*:\\d*)</td><td>([\\u4e00-\\u9fa5]{3})</td><td>([\\u4e00-\\u9fa5]{3})</td><td><a href='/Jz/show/id/(\\d{3})'");
		Matcher m = p.matcher(s);
		while (m.find()) {
			// public Speechedbean(String id, String stratTime, String name, String ended,
			// String sigined)
			list.add(new Speechedbean(m.group(5), m.group(2), m.group(1), m.group(3), m.group(4)));
		}
		Proceed.appear.append("���ݿ������!");
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
