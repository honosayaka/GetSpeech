package cn.hoNoSayaka.jt;

public class Index {

	public static void main(String[] args) throws Exception {
		GetUtil gater = new GetUtil();
		gater.GetImgCookice("http://wxkq.niit.edu.cn/Login/verify");
		Logger log = new Logger(gater.getCookie(),JDBCConnect.selectID());
		MainFrame f = new MainFrame(gater.getImage(), gater.getImage("imgs/photo.jpg"), log);
		f.lunchFrame();
	}

}
