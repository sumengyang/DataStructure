public class Price {
	// 类成员是Price实例
	final static Price INSTANCE = new Price(5.0);
	// 再定义一个类变量
	static double initPrice = 20;
	// 定义实例变量
	double currentPrice;

	public Price(double discount) {
		// 根据类变量计算实例变量
		currentPrice = initPrice - discount;
	}

	public static void main(String[] args) {
		// 通过Price的INSTANCE实例来访问currentPrice变量
		System.out.println(Price.INSTANCE.currentPrice);
		// 显式创建Price实例
		Price p = new Price(5.0);
		System.out.println(p.currentPrice);
	}
}