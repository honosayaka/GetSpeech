package cn.hoNoSayaka.jt;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

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
	String[] config = new String[2];
	Image photo = null;
	Logger log = null;
	public MainFrame(Image verifyImg,Image photo,Logger log) {
		this.verifyImg = verifyImg;
		this.photo = photo;
		this.log = log;
	}

	public void lunchFrame() {
		setTitle("抢课登陆界面 --by hoNoSayaka");
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(((int)dimension.getWidth() - 500) / 2,
				((int)dimension.getHeight() - 200) / 2,500, 200);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel label1 = new JLabel("学号:");
		label1.setBounds(10, 10, 100, 30);
		add(label1);

		JLabel label2 = new JLabel("密码:");
		label2.setBounds(10, 40, 100, 30);
		add(label2);
		
		JLabel label3 = new JLabel("验证码:");
		label3.setBounds(250, 10, 100, 30);
		add(label3);
		
		JLabel label4 = new JLabel("by hoNoSayaka");
		label4.setBounds(400, 140, 100, 30);
		add(label4);
		
		JLabel label5 = new JLabel("点击访问http://honosayaka.github.io");
		label5.setBounds(0, 140, 250, 30);
		label5.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				try {
				 URI uri = new URI("http://honosayaka.github.io");  
					Desktop.getDesktop().browse(uri);
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});
		add(label5);
		
		JPanel panel = new ImagePanel(verifyImg,60,30);
	    panel.setBounds(380,40,100,50);
	    add(panel);
	    JPanel panels = null;
	    panels = new ImagePanel(photo,130,130);
	    panels.setBounds(230,60,130,130);
	    add(panels);
	         
		final JTextField text1 = new JTextField();
		text1.setBounds(50, 15, 130, 20);
		add(text1);

		final JTextField verify = new JTextField();
		verify.setBounds(310, 15, 130, 20);
		add(verify);
		
		final JPasswordField text2 = new JPasswordField();
		text2.setBounds(50, 45, 130, 20);
		add(text2);

		if(ifHasConfig()) {
			hasConfig();
			text1.setText(config[0]);
			text2.setText(config[1]);
		}
		JButton button = new JButton("登陆");
		button.setBounds(10, 75, 170, 40);
		setVisible(true);
		button.addActionListener(new ActionListener() {

			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				try {
					String p = log.doPost("http://wxkq.niit.edu.cn/home/login/chklogin",
							"username1="+text1.getText()+
						"&password1="+String.valueOf(text2.getPassword())+
						"&verify="+verify.getText());
					start = new Proceed();
					start.lunchFrame(log);
					start.appear.append(p);
					start.appear.setCaretPosition(start.appear.getDocument().getLength());
					start.appear.append("您有3秒检查是否正确登陆!"+"\n");
					start.appear.setCaretPosition(start.appear.getDocument().getLength());
					start.sysAppend("Post 完成"+"\n"+"当前cookies:"+
					log.getCookies()+"\n");
					start.appear.setCaretPosition(start.appear.getDocument().getLength());
					Thread t = new Thread(start);
					t.start();
					
				} catch (IOException e1) {
					start.sysAppend(e1.toString()+"\n");
				}
			}
		});
		add(button);
	}
	 class ImagePanel extends JPanel {
		 Image img = null;
		 int w;
		 int h;
		 public ImagePanel(Image img,int w,int h) {
			 this.img = img;
			 this.w = w;
			 this.h = h;
		 }
        public void paint(Graphics g) {
        	
            super.paint(g);
            g.drawImage(img,0,0, w,h,null);
       /*      try {
				g.drawImage(ImageIO.read(new File("imgs/photo.jpg")),100,50,null);
			} catch (IOException e) {
				e.printStackTrace();
			}*/
         }
     }
	 
	 public boolean ifHasConfig() {
		 return new File("D:/Speech.config").exists();
	 }
	 
	 public void hasConfig() {
		 BufferedReader bf = null;
		 try {
			bf = new BufferedReader(
					 new InputStreamReader(
							 new FileInputStream(
									 new File("D:/Speech.config"))));
		config[0] = bf.readLine();
		config[1] = bf.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
	  
}
