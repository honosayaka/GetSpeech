package cn.hoNoSayaka.jt;

public class Index {
	
	public static void main(String[] args) {
		GetImgCookice g = new GetImgCookice("http://wxkq.niit.edu.cn/Login/verify");
		Logger.init(g.getCookie());
		MainFrame f = new MainFrame();
		f.lunchFrame(g.getImage());
	}

}
