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

	public void sortOrders(int numOfOrders) {
		int tempO, tempN, tempR, tempPp, tempPc, tempPs, tempPm, tempPf;
		for (int i = 0; i < numOfOrders-1; i++) {
			if(tOrder[i]>tOrder[i+1]) {
				tempO=tOrder[i]; tempN=num[i]; tempR=tReq[i];tempPp=nPp[i]; tempPc=nPc[i];tempPs=nPs[i]; tempPm=nPm[i]; tempPf=nPf[i];
				tOrder[i]=tOrder[i+1]; num[i]=num[i+1]; tReq[i]=tReq[i+1]; nPp[i]=nPp[i+1]; nPc[i]=nPc[i+1]; nPs[i]=nPs[i+1]; nPm[i]=nPm[i+1];nPf[i]=nPf[i+1];
				tOrder[i+1]=tempO; num[i+1]=tempN; tReq[i+1]=tempR; nPp[i+1]=tempPp; nPc[i+1]=tempPc; nPs[i+1]=tempPs; nPm[i+1]=tempPm; nPf[i+1]=tempPf;
			}
		}
		
	}
	public static void main(String[] args) {
		int numOfOrders = Integer.parseInt(args[0]);
		OrderGenerator orders= new OrderGenerator(numOfOrders);
	}

}

