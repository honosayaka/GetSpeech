package cn.hoNoSayaka.jt;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


@SuppressWarnings("all")
public class Proceed extends JFrame implements Runnable {
	static JTextArea appear = null;
	Logger log = null;

	public static void append(String s) {
		appear.append(s);
	}

	public void lunchFrame(Logger log) {
		this.log = log;
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = ((int) dimension.getWidth() - 500) / 2;
		int y = ((int) dimension.getHeight() - 200) / 2;
		setBounds(x, y, 500, 210);
		setTitle("抢课 --by hoNoSayaka");
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appear = new JTextArea();
		appear.setLineWrap(true);// 激活自动换行功能
		appear.setWrapStyleWord(true);// 激活断行不断字功能
		appear.setEditable(false);// 禁止改动内容
		JPanel panel = new JPanel();
		panel.setLocation(0, 0);
		panel.setSize(500, 140);
		JPanel panel2 = new JPanel();
		panel2.setBounds(0,140,490,50);
		JButton b = new JButton("查看已选");
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SelectFrame.getFrame();
				
			}
		});
		panel2.add(b);
		JScrollPane js = new JScrollPane(appear);
		js.setBounds(0, 0, 490, 140);
		js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.setLayout(null);
		panel.add(js);
		add(panel);
		add(panel2);
		setVisible(true);
		sysAppend("尝试开始,请确保正确登陆!" + "\n" + "尝试开始,请确保正确登陆!" + "\n" + "尝试开始,请确保正确登陆!" + "\n"+"即将进行数据库操作!\n");
		
		appear.setCaretPosition(appear.getDocument().getLength());
	}

	public void sysAppend(String appString) {
		appear.append(appString);
	}

	int times = 1;

	public void run() {
		try {
			appear.append("start run" + "\n");
			appear.append("3秒后开始!" + "\n");
			appear.setCaretPosition(appear.getDocument().getLength());
			appear.append("3" + "\n");
			appear.setCaretPosition(appear.getDocument().getLength());
			Thread.sleep(1000);
			appear.append("2" + "\n");
			appear.setCaretPosition(appear.getDocument().getLength());
			Thread.sleep(1000);
			appear.append("1" + "\n");
			appear.setCaretPosition(appear.getDocument().getLength());
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while (true) {
			if (Logger.isFull()) {
				break;
			}
			try {
				appear.append(log.doGet("http://wxkq.niit.edu.cn/jz/index") + "\n");
				sysAppend("这是第" + times + "次尝试获取讲座" + "\n");
				sysAppend("Get 完成" + "\n" + "当前cookies:" + log.getCookies() + "\n");
				appear.setCaretPosition(appear.getDocument().getLength());
				Thread.sleep(60000);
				times++;
			} catch (Exception e) {
				sysAppend(e.toString() + "\n");
			}
		}
		appear.append("得到课程已达最大数!");
	}

}
