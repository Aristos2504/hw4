package hw4;
public class OrderGenerator {
	int[] num;
	int[] tOrder;
	int[] tReq;
	int[] nPp;
	int[] nPc;
	int[] nPs;
	int[] nPm;
	int[] nPf;

	public OrderGenerator(int numOfOrders) {//int[] num, int[] tOrder, int[] tReq, int[] nPp, int[] nPc, int[] nPs, int[] nPm, int[] nPf) {
		this.num = new int[numOfOrders];//(length?)
		
		this.tOrder = tOrder;
		this.tReq = tReq;
		this.nPp = nPp;
		this.nPc = nPc;
		this.nPs = nPs;
		this.nPm = nPm;
		this.nPf = nPf;

	}
	

	public static void main(String[] args) {
		int numOfOrders = Integer.parseInt(args[0]);
		OrderGenerator orders= new OrderGenerator(numOfOrders);
	}

}

