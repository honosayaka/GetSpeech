package cn.hoNoSayaka.jt;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Proceed extends JFrame implements Runnable{
	JTextArea appear = null;
	public static void main(String[] args) {
		Proceed p = new Proceed();
		p.lunchFrame();
	}
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
		js.setBounds(10, 0, 480,180);
		js.setVerticalScrollBarPolicy( 
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		panel.setBounds(10, 0, 450,180);
		panel.add(js);
		this.add(panel);
		this.setVisible(true);
		appear.append("登陆成功"+"\n");
	}
	
	public void run(){
		System.out.println("start run");
		while(true) {
			try {
				appear.append(Logger.doGet("http://wxkq.niit.edu.cn/Jz/index")+"\n");
				Thread.sleep(6000);
			} catch (IOException | InterruptedException e) {
				appear.append(e.toString()+"\n");
			}
		}
	}
	
}
