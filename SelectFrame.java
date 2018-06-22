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

public class SelectFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private static SelectFrame frame;
	static {
		frame = new SelectFrame();
	}

	public static SelectFrame getFrame() {
		return frame;
	}
	
	private SelectFrame() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = ((int) dimension.getWidth() - 500) / 2;
		int y = ((int) dimension.getHeight() - 200) / 2;
		setBounds(x, y, 500, 210);
		setTitle("抢课 --by hoNoSayaka");
		setResizable(false);
		setLayout(null);
		JTextArea appear = new JTextArea();
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
		JButton b = new JButton("关闭此窗口");
		b.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				
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
		appear.setText(JDBCConnect.selected());
	}

}
