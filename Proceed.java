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
		setTitle("���� --by hoNoSayaka");
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		appear = new JTextArea();
		appear.setLineWrap(true);// �����Զ����й���    
		appear.setWrapStyleWord(true);// ������в����ֹ���   
		appear.setEditable(false);//��ֹ�Ķ�����
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
		sysAppend("���Կ�ʼ,��ȷ����ȷ��½!"+"\n"+"���Կ�ʼ,��ȷ����ȷ��½!"+"\n"+"���Կ�ʼ,��ȷ����ȷ��½!"+"\n");
		appear.setCaretPosition(appear.getDocument().getLength());
	}
		public void sysAppend(String appString) {
			appear.append(appString);
		}
	int times = 1;
	
	public void run(){
		try {
			appear.append("start run"+"\n");
			appear.append("3���ʼ!"+"\n");
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
				sysAppend("���ǵ�"+times+"�γ��Ի�ȡ����"+"\n");
				sysAppend("Get ���"+"\n"+"��ǰcookies:"+log.getCookies()+"\n");
				appear.setCaretPosition(appear.getDocument().getLength());
				Thread.sleep(60000);
				times++;
			} catch (Exception e) {
				sysAppend(e.toString()+"\n");
			}
		}
	}
	
}
