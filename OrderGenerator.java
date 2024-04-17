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
		this.num = new int[numOfOrders];
		this.tOrder = new int[numOfOrders];
		this.tReq = new int[numOfOrders];
		this.nPp = new int[numOfOrders];
		this.nPc = new int[numOfOrders];
		this.nPs = new int[numOfOrders];
		this.nPm = new int[numOfOrders];
		this.nPf = new int[numOfOrders];

	}
	public int[]
	public static void main(String[] args) {
		int numOfOrders = Integer.parseInt(args[0]);
		OrderGenerator orders= new OrderGenerator(numOfOrders);
	}

}

