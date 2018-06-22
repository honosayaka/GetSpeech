package cn.hoNoSayaka.jt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCConnect {
	static Connection conn;
	static PreparedStatement stat;
	static {
		new JDBCUtil();
//		clearDB();
		conn = getConnect();
		stat = getStatement();
	}
	
	public static PreparedStatement getStatement() {
		try {
			return conn.prepareStatement("insert into speech (id,startTime,name,ended,sigined) values (?,?,?,?,?);");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}
	
	public static String selected() {
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();//未开始的
		try {
			PreparedStatement stat = conn.prepareStatement("select * from speech;");
			ResultSet rs = stat.executeQuery();
			while(rs.next()) {
				if(!rs.getString(4).equals("已结束")) {
				//	sb.append();
					sb1.append(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+"\n");					
				}else {
				//	sb.append();
					sb2.append(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5)+"\n");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "\t\t未开始:\n\n"+sb1.toString()+"\t\t已结束:\n\n"+sb2.toString();
	}
	
	public static List<Speechbean> selectID() {//已经抢到,但是没有开始的课程
		try {
			PreparedStatement stat = conn.prepareStatement("select id,ended,startTime from speech;");
			ResultSet result = stat.executeQuery();
			List<Speechbean> list = new ArrayList<>();
			while(result.next()) {
				if(result.getString(2).equals("已结束")) {
					continue;
				}else {
					list.add(new Speechbean(result.getString(1), result.getString(3)));
				}
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Connection getConnect() {

		String sql = "jdbc:sqlite://e:/Program Files/honosayaka/sp.db";
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

	public static void insert(Speechedbean sp) {
		// id,startTime,name,ended,signed
		try {
			stat.setObject(1, sp.getId());
			stat.setObject(2, sp.getStratTime());
			stat.setObject(3, sp.getName());
			stat.setObject(4, sp.getEnded());
			stat.setObject(5, sp.getSigined());
			stat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	  }
	 

