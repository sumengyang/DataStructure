public class Price {
	// ���Ա��Priceʵ��
	final static Price INSTANCE = new Price(5.0);
	// �ٶ���һ�������
	static double initPrice = 20;
	// ����ʵ������
	double currentPrice;

	public Price(double discount) {
		// �������������ʵ������
		currentPrice = initPrice - discount;
	}

	public static void main(String[] args) {
		// ͨ��Price��INSTANCEʵ��������currentPrice����
		System.out.println(Price.INSTANCE.currentPrice);
		// ��ʽ����Priceʵ��
		Price p = new Price(5.0);
		System.out.println(p.currentPrice);
	}
}