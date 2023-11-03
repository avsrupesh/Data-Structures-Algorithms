import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Application {
	static boolean exit = false;

	public static <T> void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String filename = "data.txt";
		Path path = Paths.get(filename);

		// reading no. of rows in the file
		int numLines = (int) Files.lines(path).count();

		// reading data from the file
		File myObj = new File(filename);
		Scanner myReader = new Scanner(myObj);	
		
		//unsorted linked list
		UnsortedList<T> ulList = new UnsortedList<T>();

		while (myReader.hasNextLine()) {
			String data = myReader.nextLine();

		 int d = Integer.parseInt(data.split(",")[1]);
		 int p = Integer.parseInt(data.split(",")[4]);
		 double f = (1-d*0.01)*p;
			// implementing purchase coupon method. Adds all coupon details to the Unsorted list (UL)

		 ulList.purchaseCoupon((T)new Coupon(data.split(",")[0], Integer.parseInt(data.split(",")[1]), Integer.parseInt(data.split(",")[2]),
				 "unused", data.split(",")[3], Integer.parseInt(data.split(",")[4]), Math.round(f*100.00)/100.00));			
		}
		
		// closing file connection
		myReader.close();
		

		while (!exit) {

			Scanner s1 = new Scanner(System.in);
			System.out.println("\nChoose an option... ");
			System.out.println("1: Purchase coupon");
			System.out.println("2: Search coupons");
			System.out.println("3: List coupons");
			System.out.println("4: Redeem coupon");
			System.out.println("5: Exit");
			
			int option = s1.nextInt();

			if (option == 1) {
				// displaying the information that was stored reading the input file
				System.out.println("Available coupon details are.... ");
				ulList.displayArrayData();

				// code to take manual input from user
				Scanner s = new Scanner(System.in);
				System.out.println("\nEnter the no. of coupon details you'd like to add (add at least 1): ");
				int count = s.nextInt();

				for (int i = 1; i <= count; i++) {
					Scanner sc = new Scanner(System.in);

					System.out.println("Coupon provider: ");
					String couponProvider = sc.nextLine();

					System.out.println("Coupon discount(in %): ");
					int couponDiscount = sc.nextInt();

					sc.nextLine();

					System.out.println("Coupon expiry period(in days): ");
					int expiryPeriod = Integer.parseInt(sc.nextLine());

					System.out.println("Product type/name: ");
					String productName = sc.nextLine();

					System.out.println("Product price(in $): ");
					int price = sc.nextInt();

					double finalPrice = (1 - (couponDiscount * 0.01)) * price;

					ulList.purchaseCoupon((T) new Coupon(couponProvider, couponDiscount, expiryPeriod, "unused",
							productName, price, finalPrice));
					System.out.println("\nDetails added!");

				}

				// displaying the updated information after considering the manual input
				System.out.println("\nUdated details are: ");
				ulList.displayArrayData();

			} else if (option == 2) {
				// #2 Search coupons

				System.out.println("\nSelect a product to display the applicable coupons: ");
				System.out.println("List of available products are: ");
				ulList.displayProductNames();

				Scanner sn = new Scanner(System.in);
				String product = sn.nextLine();

				// sorting the array based on product price to build BST
				ulList.masterSort(2);

				ulList.searchCoupons(product, ulList.getCouponArray());
			} else if (option == 3) {

				// #3 List Coupons
				System.out.println("\nSelect a criteria to list the details of coupons:");
				System.out.println("\nCoupon provider: 1, Coupon discount: 2, Expiry period: 3, Coupon status: 4,"
						+ " Product name: 5, Product price: 6, Final product price(incl discount): 7");
				Scanner sc = new Scanner(System.in);
				int criteria = sc.nextInt();

				ulList.masterSort(criteria);

				// displaying the sorted details
				ulList.displayArrayData();
			} else if(option==4) {
				System.out.println("\nSelect a product to display the applicable coupons: ");
				System.out.println("List of available products are: ");
				ulList.displayProductNames();
				
				Scanner s0 = new Scanner(System.in);
				String product1 = s0.nextLine();
				
				ulList.displayProductCoupons(product1);
				
				System.out.println("Enter the coupon you want to redeem: ");
				String couponName = s0.nextLine();
				ulList.redeemCoupon(couponName, product1);
				
				System.out.println("Updated coupon details: ");
				ulList.displayArrayData();
				
			} else if(option==5){
				exit = true;
				break;
			} else
				System.out.println("\ninvalid input..!");

		}
		 

	}

}
