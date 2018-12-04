package example1;

public class User {
	
	public static void main(String[] args) {
		F f = new C1();
//		f.f2();
		f.f1();
		
		// 静态方法不支持多态
		F.sf1();
		// 静态方法支持继承
		f.sf0();
	}
}
