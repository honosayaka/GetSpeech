package cn.hoNoSayaka.jt;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	Image verifyImg = null;
	Proceed start = null;
	
	public static void main(String[] args) {
		MainFrame f = new MainFrame();
		GetImgCookice g = new GetImgCookice("http://wxkq.niit.edu.cn/Login/verify");
		Logger.init(g.getCookie());
		f.lunchFrame(g.getImage());
	}
	public void lunchFrame(Image verifyImg) {
		this.verifyImg = verifyImg;
		JFrame jFrame = new JFrame("登陆界面");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		jFrame.setBounds(((int)dimension.getWidth() - 500) / 2,
				((int)dimension.getHeight() - 200) / 2,500, 200);
		jFrame.setResizable(false);
		jFrame.setLayout(null);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel label1 = new JLabel("姓名");
		label1.setBounds(10, 10, 100, 30);
		jFrame.add(label1);

		JLabel label2 = new JLabel("密码");
		label2.setBounds(10, 40, 100, 30);
		jFrame.add(label2);
		
		JLabel label3 = new JLabel("验证码");
		label3.setBounds(250, 10, 100, 30);
		jFrame.add(label3);
		
		JPanel panel = new ImagePanel();
	    panel.setBounds(310,40,100,50);
	    jFrame.add(panel);
	         
		JTextField text1 = new JTextField();
		text1.setBounds(50, 15, 130, 20);
		jFrame.add(text1);

		JTextField verify = new JTextField();
		verify.setBounds(310, 15, 130, 20);
		jFrame.add(verify);
		
		JPasswordField text2 = new JPasswordField();
		text2.setBounds(50, 45, 130, 20);
		jFrame.add(text2);

		JButton button = new JButton("登陆");
		button.setBounds(10, 75, 170, 40);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					Logger.doPost("http://wxkq.niit.edu.cn/home/login/chklogin",
							"username1="+text1.getText()+
						"&password1="+String.valueOf(text2.getPassword())+
						"&verify="+verify.getText());
					setVisible(false);
					start = new Proceed();
					start.lunchFrame();
					Thread t = new Thread(start);
					t.start();
			//		Logger.doGet("http://wxkq.niit.edu.cn/Jz/index");
					
				} catch (IOException e1) {
					start.appear.append(e1.toString()+"\n");
				}
			}
		});
		jFrame.add(button);

		jFrame.setVisible(true);
	}
	 class ImagePanel extends JPanel {
         public void paint(Graphics g) {
             super.paint(g);
             g.drawImage(verifyImg,0,0, null);
         }
     }
}
