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

	public void lunchFrame() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(((int)dimension.getWidth() - 500) / 2,
				((int)dimension.getHeight() - 200) / 2,500, 200);
		this.setResizable(false);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appear = new JTextArea();
		appear.setLineWrap(true);// 激活自动换行功能    
		appear.setWrapStyleWord(true);// 激活断行不断字功能   
		JPanel panel = new JPanel();
		JScrollPane js = new JScrollPane(appear);
		js.setBounds(0, 0, 490,180);
		js.setVerticalScrollBarPolicy( 
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.setBounds(0, 0, 500,180);
		panel.setLayout(null);
		panel.add(js);
		this.add(panel);
		this.setVisible(true);
		appear.append("尝试开始,请确保正确登陆!"+"\n"+"尝试开始,请确保正确登陆!"+"\n"+"尝试开始,请确保正确登陆!"+"\n");
	}
	
	int times = 1;
	
	public void run(){
		appear.append("start run"+"\n");
		while(true) {
			try {
				appear.append("这是第"+times+"次尝试获取讲座"+"\n");
				appear.append(Logger.doGet("http://wxkq.niit.edu.cn/jz/index")+"\n");
				appear.append("Get 完成"+"\n"+"当前cookies:"+Logger.getCookies()+"\n");
				Thread.sleep(60000);
				times++;
			} catch (Exception e) {
				appear.append(e.toString()+"\n");
			}
		}
	}
	
}
