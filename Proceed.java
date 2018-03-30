package cn.hoNoSayaka.jt;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


@SuppressWarnings("serial")
public class Proceed extends JFrame implements Runnable{
	JTextArea appear = null;
	Logger log = null;
	
	public void lunchFrame(Logger log) {
		this.log = log;
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(((int)dimension.getWidth() - 500) / 2,
				((int)dimension.getHeight() - 200) / 2,500, 200);
		setTitle("抢课 --by hoNoSayaka");
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appear = new JTextArea();
		appear.setLineWrap(true);// 激活自动换行功能    
		appear.setWrapStyleWord(true);// 激活断行不断字功能   
		appear.setEditable(false);//禁止改动内容
		JPanel panel = new JPanel();
		JScrollPane js = new JScrollPane(appear);
		js.setBounds(0, 0, 490,180);
		js.setVerticalScrollBarPolicy( 
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.setBounds(0, 0, 500,180);
		panel.setLayout(null);
		panel.add(js);
		add(panel);
		setVisible(true);
		sysAppend("尝试开始,请确保正确登陆!"+"\n"+"尝试开始,请确保正确登陆!"+"\n"+"尝试开始,请确保正确登陆!"+"\n");
		appear.setCaretPosition(appear.getDocument().getLength());
	}
		public void sysAppend(String appString) {
			appear.append(appString);
		}
	int times = 1;
	
	public void run(){
		try {
			appear.append("start run"+"\n");
			appear.append("3秒后开始!"+"\n");
			appear.setCaretPosition(appear.getDocument().getLength());
			appear.append("3"+"\n");
			appear.setCaretPosition(appear.getDocument().getLength());
			Thread.sleep(1000);
			appear.append("2"+"\n");
			appear.setCaretPosition(appear.getDocument().getLength());
			Thread.sleep(1000);
			appear.append("1"+"\n");
			appear.setCaretPosition(appear.getDocument().getLength());
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while(true) {
			try {
				appear.append(log.doGet("http://wxkq.niit.edu.cn/jz/index")+"\n");
				sysAppend("这是第"+times+"次尝试获取讲座"+"\n");
				sysAppend("Get 完成"+"\n"+"当前cookies:"+log.getCookies()+"\n");
				appear.setCaretPosition(appear.getDocument().getLength());
				Thread.sleep(60000);
				times++;
			} catch (Exception e) {
				sysAppend(e.toString()+"\n");
			}
		}
	}
	
}
