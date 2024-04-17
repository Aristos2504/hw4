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
        this.nPp = new int[numOfOrders];
        this.nPc = new int[numOfOrders];
        this.nPs = new int[numOfOrders];
        this.nPm = new int[numOfOrders];
        this.nPf = new int[numOfOrders];
        this.tOrder = fillTimeOrder(numOfOrders);
        this.tReq = fillDesiredDeliveryTimes(numOfOrders, this.nPf);
    }

    public static int[] fillTimeOrder(int numOfOrders) {
        Random rand = new Random();
        int[] tOrder = new int[numOfOrders];
        for (int i = 0; i < tOrder.length; i++) {
            double gaussianTime = 60 * rand.nextGaussian() + 180;
            tOrder[i] = (int) Math.round(gaussianTime);
        }
        return tOrder;
    }

    public static int[] fillDesiredDeliveryTimes(int numOfOrders, int[] nPf) {
        Random rand = new Random();
        int[] tReq = new int[numOfOrders];
        for (int i = 0; i < tReq.length; i++) {
            int deliveryTime;
            if (nPf[i] > 10) {
                deliveryTime = 60 + rand.nextInt(121);
            } else {
                deliveryTime = 30 + rand.nextInt(151);
            }
            tReq[i] = deliveryTime;
        }
        return tReq;
    }

   public void sortOrders() {
    for (int i = 0; i < num.length - 1; i++) {
        for (int j = 0; j < num.length - i - 1; j++) {
            if (tOrder[j] > tOrder[j + 1]) {
                int tempO = tOrder[j];
                int tempN = num[j];
                int tempR = tReq[j];
                int tempPp = nPp[j];
                int tempPc = nPc[j];
                int tempPs = nPs[j];
                int tempPm = nPm[j];
                int tempPf = nPf[j];

                tOrder[j] = tOrder[j + 1];
                num[j] = num[j + 1];
                tReq[j] = tReq[j + 1];
                nPp[j] = nPp[j + 1];
                nPc[j] = nPc[j + 1];
                nPs[j] = nPs[j + 1];
                nPm[j] = nPm[j + 1];
                nPf[j] = nPf[j + 1];

                tOrder[j + 1] = tempO;
                num[j + 1] = tempN;
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
        OrderGenerator orders = new OrderGenerator(numOfOrders);
        orders.sortOrders();
        writeOrders(orders.num, orders.tOrder, orders.tReq, orders.nPp, orders.nPc, orders.nPs, orders.nPm, orders.nPf);
    }
}
