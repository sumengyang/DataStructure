class RAM {
	double price = 2.0;// ��--������䲢��ֵ
	{
		System.out.println("before ����" + price);
		price = 3.4;// ��--�Ǿ�̬����
		System.out.println("after����" + price);
	}

	public RAM(double price) {
		System.out.println("before ���캯��" + this.price);
		this.price = price;// ��--���캯��
		System.out.println("after���캯��" + price);
	}
}

public class RAMTest {
	public static void main(String[] args) {
		RAM test = new RAM(5.0);
	}
}