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
	int[] pittaCounter;

	public OrderGenerator(int numOfOrders) {
		this.num = new int[numOfOrders];
		this.tOrder = new int[numOfOrders];
		this.tReq = new int[numOfOrders];
		this.nPp = new int[numOfOrders];
		this.nPc = new int[numOfOrders];
		this.nPs = new int[numOfOrders];
		this.nPm = new int[numOfOrders];
		this.nPf = new int[numOfOrders];
		this.pittaCounter= new int[numOfOrders];
	}
	
	public static int generateRandomNumber(int x, int y) {
        // Check if x is greater than y
        if (x > y) {
            // Swap values of x and y
            int temp = x;
            x = y;
            y = temp;
        }
        
        // Create a Random object
        Random rand = new Random();
        
        // Generate a random number between x and y (both inclusive)
        int randomNumber = rand.nextInt((y - x) + 1) + x;
        
        return randomNumber;
    }
	
	public   void addPitta(int x, int y, int num) {
		int random;
		
		for(int i=x;i<y;i++) {
			for(int j=0;j<num;j++) {
			random=generateRandomNumber(1,4);
			if(random==1) {
				this.nPp[i]++;
			}
			else if(random==2) {
				this.nPc[i]++;
			}
			else if(random==3) {
				this.nPs[i]++;
			}
			else if(random==4) {
				this.nPm[i]++;
			}
			this.pittaCounter[i]++;
			}
		}
		
	}
	
	public   void addMorePitta(int x, int y) {
		int random;
		int num;
		for(int i=x;i<y;i++) {
			num=generateRandomNumber(5,20);
			for(int j=0;j<num;j++) {
			random=generateRandomNumber(1,4);
			if(random==1) {
				this.nPp[i]++;
			}
			else if(random==2) {
				this.nPc[i]++;
			}
			else if(random==3) {
				this.nPs[i]++;
			}
			else if(random==4) {
				this.nPm[i]++;
			}
			this.pittaCounter[i]++;
			}
		}
	}
	
	public void addFries() {
	    int totalPittas = 0;
	    for (int count : pittaCounter)
	        totalPittas += count;

	    int friesForOnePitta = (int) (totalPittas * 0.6);
	    int friesForNoPitta = (int) (totalPittas * 0.35);



	    int pittasCovered = 0;
	    for (int i = 0; i < pittaCounter.length; i++) {
	        pittasCovered += pittaCounter[i];
	        if (pittasCovered <= friesForOnePitta) {
	            nPf[i] = pittaCounter[i];
	        } else if (pittasCovered <= friesForOnePitta + friesForNoPitta && pittasCovered >= friesForOnePitta) {
	            nPf[i] = 0;
	        } else {
	            nPf[i] = pittaCounter[i] * 2;
	        }
	    }
	}




	
	public void generateOrders(int numOfOrders) {
		int onePitta= (int)(numOfOrders *0.2);
		int twoPitta= (int)(numOfOrders *0.35 + onePitta);
		int threePitta=(int)(numOfOrders *0.1 + twoPitta);
		int fourPitta= (int)(numOfOrders *0.2 + threePitta);
		int fiveOrMorePitta= (int)(numOfOrders *0.15 + fourPitta);
		
		
	for(int i=0;i<numOfOrders;i++) {
			this.num[i]=i+1;

			
		}

		
	
	
	
		addPitta(0,onePitta,1);
	
	
		addPitta(onePitta,twoPitta,2);
	
	
		addPitta(twoPitta,threePitta,3);
	
	
		addPitta(threePitta,fourPitta,4);
		
		addMorePitta(fourPitta,fiveOrMorePitta);
	
		addFries();
	
		this.tOrder=fillTimeOrder(numOfOrders);
		this.tReq=fillDesiredDeliveryTimes(numOfOrders, this.pittaCounter, this.tOrder);
		
		sortOrders();
		
		writeOrders(this.num, this.tOrder, this.tReq, this.nPp, this.nPc, this.nPs, this.nPm, this.nPf);
	

	}
	
	public static int[] fillTimeOrder(int numOfOrders) {
	    Random rand = new Random();
	    int[] tOrder = new int[numOfOrders];
	    // Define the opening and closing times in minutes from 18:00 to 23:00
	    int openingTime = 0; // 18:00
	    int closingTime = 300; // 23:00
	    // Define the mean and standard deviation for Gaussian distribution
	    double mean = 180; // Mean time in minutes (21:00)
	    double stdDev = 60; // Standard deviation

	    for (int i = 0; i < tOrder.length; i++) {
	        // Generate a random value following Gaussian distribution
	        double gaussianTime = stdDev * rand.nextGaussian() + mean;
	        // Ensure the generated time is within the opening and closing hours
	        int orderTime = (int) Math.round(Math.max(openingTime, Math.min(closingTime, gaussianTime)));
	        tOrder[i] = orderTime;
	    }
	    return tOrder;
	}
	
	public static int[] fillDesiredDeliveryTimes(int numOfOrders, int[] pittaCounter, int[] tOrder) {
        Random rand = new Random();
        int[] tReq = new int[numOfOrders];
        int closingTime = 360; // Closing time in minutes (24:00)
        int meanDeliveryTime = 60; // Mean delivery time in minutes
        int stdDevDeliveryTime = 90; // Standard deviation

        for (int i = 0; i < tReq.length; i++) {
            int deliveryTime = (int) Math.round(meanDeliveryTime + stdDevDeliveryTime * rand.nextGaussian());

            if (pittaCounter[i] > 10) {
                deliveryTime += rand.nextInt(121) + 30; // Additional time between 30 minutes to 2 hours and 30 minutes
            } else {
                deliveryTime += rand.nextInt(151) + 30; // Additional time between 30 minutes to 3 hours and 30 minutes
            }

            // Ensure the delivery time does not exceed closing time
            int maxDeliveryTime = closingTime - tOrder[i];
            deliveryTime = Math.min(deliveryTime, maxDeliveryTime);

            // Ensure the delivery time is at least 30 minutes more than tOrder
            tReq[i] = tOrder[i] + Math.max(deliveryTime, 30);
        }
        return tReq;
    }


 

	
   public void sortOrders() {
    for (int i = 0; i < num.length - 1; i++) {
        for (int j = 0; j < num.length - i - 1; j++) {
            if (tOrder[j] > tOrder[j + 1]) {
                int tempO = tOrder[j];
                int tempR = tReq[j];
                int tempPp = nPp[j];
                int tempPc = nPc[j];
                int tempPs = nPs[j];
                int tempPm = nPm[j];
                int tempPf = nPf[j];

                tOrder[j] = tOrder[j + 1];
                tReq[j] = tReq[j + 1];
                nPp[j] = nPp[j + 1];
                nPc[j] = nPc[j + 1];
                nPs[j] = nPs[j + 1];
                nPm[j] = nPm[j + 1];
                nPf[j] = nPf[j + 1];

                tOrder[j + 1] = tempO;
                tReq[j + 1] = tempR;
                nPp[j + 1] = tempPp;
                nPc[j + 1] = tempPc;
                nPs[j + 1] = tempPs;
                nPm[j + 1] = tempPm;
                nPf[j + 1] = tempPf;
            }
        }
    }
}

    public static void writeOrders(int[] num, int[] tOrder, int[] tReq, int[] nPp, int[] nPc, int[] nPs, int[] nPm, int[] nPf) {
        try {
            PrintWriter writer = new PrintWriter("orders.txt");
            writer.println(num.length);
            writer.println("Num:" + "\t" + "tOrder:" + "\t" + "tReq:" + "\t"
                    + "nPp:" + "\t" + "nPc:" + "\t" + "nPs:" + "\t" + "nPm:" + "\t" + "nPf:");
            for (int i = 0; i < num.length; i++) {
                writer.println(num[i] + "\t" + tOrder[i] + "\t" + tReq[i] + "\t"
                        + nPp[i] + "\t" + nPc[i] + "\t" + nPs[i] + "\t" + nPm[i] + "\t" + nPf[i]);
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
		orders.generateOrders(numOfOrders);
		
		
	}

}
