package hw4;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
public class OrderGenerator {
	int[] num;
	int[] tOrder;
	int[] tReq;
	int[] nPp;
	int[] nPc;
	int[] nPs;
	int[] nPm;
	int[] nPf;

	public OrderGenerator(int numOfOrders) {
		this.num = new int[numOfOrders];
		this.tOrder =OrderGenerator.fillTimeOrder( );
		this.tReq =OrderGenerator.fillDesiredDeliveryTimes();
		this.nPp = new int[numOfOrders];
		this.nPc = new int[numOfOrders];
		this.nPs = new int[numOfOrders];
		this.nPm = new int[numOfOrders];
		this.nPf = new int[numOfOrders];

	static void fillTimeOrder(int[] tOrder) {
       		 Random rand = new Random();
       		 for (int i = 0; i < tOrder.length; i++) {
          		  double gaussianTime = 60 * rand.nextGaussian() + 180; // Χρόνος σε λεπτά με κανονική κατανομή
          		  tOrder[i] = (int) Math.round(gaussianTime); // Στρογγυλοποίηση στον πλησιέστερο ακέραιο
      			  }
   	 }
	static void fillDesiredDeliveryTimes(int[] tReq, int[] nPf) {
        	Random rand = new Random();
       		 for (int i = 0; i < tReq.length; i++) {
           		 int deliveryTime;
          		  if (nPf[i] > 10) {
               		 deliveryTime = 60 + rand.nextInt(121); // Τυχαίος αριθμός από 60 έως 180 λεπτά (1-3 ώρες)
           			 } else {
             			   deliveryTime = 30 + rand.nextInt(151); // Τυχαίος αριθμός από 30 έως 180 λεπτά (30 λεπτά - 3 ώρες)
           				 }
           		 tReq[i] = deliveryTime;
       			 }
 	   }
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
	 static void writeOrders(int[] num, int[] tOrder, int[] tReq, int[] nPp, int[] nPc, int[] nPs, int[] nPm, int[] nPf) {
	        try {
	            PrintWriter writer = new PrintWriter("orders.txt");
	            writer.println(num.length); 
	            for (int i = 0; i < num.length; i++) {
	                writer.println(num[i] + " " + tOrder[i] + " " + tReq[i] + " "
	                        + nPp[i] + " " + nPc[i] + " " + nPs[i] + " " + nPm[i] + " " + nPf[i]);
	            }
	            writer.close();
	        } catch (FileNotFoundException e) {
	            System.out.println("An error occurred while writing the orders file.");
	            e.printStackTrace();
	        }
	    }		

	public static void main(String[] args) {
		int numOfOrders = Integer.parseInt(args[0]);
		OrderGenerator orders= new OrderGenerator(numOfOrders);
	}

}

