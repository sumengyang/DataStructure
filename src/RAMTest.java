class RAM {
	double price = 2.0;// ③--声明语句并赋值
	{
		System.out.println("before 语句块" + price);
		price = 3.4;// ①--非静态语句块
		System.out.println("after语句块" + price);
	}

	public RAM(double price) {
		System.out.println("before 构造函数" + this.price);
		this.price = price;// ②--构造函数
		System.out.println("after构造函数" + price);
	}
}

public class RAMTest {
	public static void main(String[] args) {
		RAM test = new RAM(5.0);
	}
}