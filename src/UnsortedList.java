import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UnsortedList<T> extends LLNode<T> {

	protected LLNode<T> linkedList;
	protected LLNode<T> head = null;
	protected LLNode<T> tail = null;

	protected BSTNode<T> root;
	
	protected final int DEFCAP = 30;
	protected T[] couponArray;
	protected Coupon[] cArr;
	protected int numOfElements = 0;
	protected boolean couponFound = false;
	protected int countBinarySearch = 0, countLinearSearch = 1;

	public UnsortedList() {
		// TODO Auto-generated constructor stub
		linkedList = new LLNode<T>();
		couponArray = (T[]) new Object[DEFCAP];

	}

	public UnsortedList(int capacity) {
		// creating user input array size
		couponArray = (T[]) new Object[capacity];
	}

	// #1 purchase coupon method. Adding the input data to both linked list and the
	// array
	public void purchaseCoupon(T coupon) {
		LLNode<T> newNode = new LLNode<T>(coupon);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.setLink(newNode);
			tail = newNode;
		}

		if (isArrayFull())
			increaseArraySize();

		couponArray[numOfElements] = coupon;
		numOfElements++;
	}

	private void increaseArraySize() {
		// TODO Auto-generated method stub
		// method to increase the size of array
		T[] largerArray = (T[]) new Object[(couponArray.length) * 2];

		// copying the data of smaller array into the larger one
		for (int i = 0; i < numOfElements; i++)
			largerArray[i] = couponArray[i];

		couponArray = largerArray;
	}

	// method to sort the list

	public void masterSort(int i) {
		sortCouponArray(couponArray, 0, numOfElements - 1, i - 1);
	}

	public void sortCouponArray(T[] arr, int first, int last, int x) {
		// TODO Auto-generated method stub
		// implementing quick sort algorithm
		if (first >= last) {
			return;
		}

		//split function to trace the pivot element
		int pivot = splitCouponArray(arr, first, last, x);

		// sub array having elements less than pivot element
		sortCouponArray(arr, first, pivot - 1, x);

		// sub array having elements greater than pivot element
		sortCouponArray(arr, pivot + 1, last, x);

	}

	public int splitCouponArray(T[] arr, int first, int last, int x) {
		// choosing the last element as pivot
		T pivot = arr[last];
		T temp;

		// elments less than pivot will be on left side and the greater value elements are on right side
		int flag = first;

		for (int i = first; i < last; i++) {
			double y = 1;
			
			//criteria selected by the user to sort the coupons will be passed as 
			// argument x and checking it for comparing pivot with current element
			if (x == 0 || x == 3 || x == 4) {
				y = arr[i].toString().split(",")[x].compareTo(pivot.toString().split(",")[x]);
			} else if (x == 1 || x == 2 || x == 5) {
				y = Integer.parseInt(arr[i].toString().split(",")[x])
						- Integer.parseInt(pivot.toString().split(",")[x]);
			} else
				//for x=7
				y = (Double.valueOf(arr[i].toString().split(",")[x]) - Double.valueOf(pivot.toString().split(",")[x]));

			if (y <= 0) {
				temp = arr[i];
				arr[i] = arr[flag];
				arr[flag] = temp;
				flag++;
			}
		}

		// swapping the current element with pivot
		temp = arr[flag];
		arr[flag] = arr[last];
		arr[last] = temp;

		// index of the pivot
		return flag;
	}


	//to print the details stored in linked list
	public void displayListData() {
		LLNode<T> currNode = head;
		while (currNode != null) {
			System.out.println(currNode.getInfo());
			currNode = currNode.getLink();
		}

	}

	public boolean isArrayFull() {
		// TODO Auto-generated method stub
		return (numOfElements == couponArray.length);
	}

	public boolean isArrayEmpty() {
		// TODO Auto-generated method stub
		return (numOfElements == 0);
	}

	public int arraySize() {
		// TODO Auto-generated method stub
		return numOfElements;
	}

	//displaying the details stored in array in required format
	public void displayArrayData() {
		for (int i = 0; i < numOfElements; i++) {
			String s = "Cp provider: " + couponArray[i].toString().split(",")[0] + ", Cp. discount: "
					+ couponArray[i].toString().split(",")[1] + "%, Expiry: " + couponArray[i].toString().split(",")[2]
					+ "days, Status: " + couponArray[i].toString().split(",")[3] + ", Product: "
					+ couponArray[i].toString().split(",")[4] + ", Price: $" + couponArray[i].toString().split(",")[5]
					+ ", Final price: $" + couponArray[i].toString().split(",")[6];
			System.out.println(s);
		}
	}

	//method to display product names to enable user to select the product name
	public void displayProductNames() {

		ArrayList<String> strn = new ArrayList<>();
		for (int i = 0; i < numOfElements; i++) {
			strn.add(couponArray[i].toString().split(",")[4]);
		}
		Set<String> set = new LinkedHashSet<>();
		set.addAll(strn);

		//clearing the deplicate values
		strn.clear();
		strn.addAll(set);
		System.out.println(strn.toString());

	}

	//method to return the array having coupon details
	public T[] getCouponArray() {

		List<T> list = new ArrayList<T>();

		for (T t : couponArray) {
			if (t != null)
				list.add(t);
		}
		couponArray = (T[]) list.toArray(new Object[list.size()]);

		return couponArray;

	}

	//#2 search coupons method
	public void searchCoupons(String product, T[] cArray) {
		// TODO Auto-generated method stub

		int index = 0;
		T temp = null;
		
		// calling the method to build the BST
		root = buildBST(cArray, 0, cArray.length - 1);

		// code for linear search using linked list data

		LLNode<T> currNode = head;

		while (currNode != null && !couponFound) {
			if (currNode.getInfo().toString().split(",")[4].equalsIgnoreCase(product)) {
				temp = currNode.getInfo();
				couponFound = true;
				/*System.out.println("Coupon Details: " + currNode.getInfo().toString() + " --->found in iteration "
						+ countLinearSearch + " by LinearSearch");
				*/
				System.out.println("Cp provider: " + currNode.getInfo().toString().split(",")[0] + ", Cp. discount: "
					+ currNode.getInfo().toString().split(",")[1] + "%, Expiry: " + currNode.getInfo().toString().split(",")[2]
					+ "days, Status: " + currNode.getInfo().toString().split(",")[3] + ", Product: "
					+ currNode.getInfo().toString().split(",")[4] + ", Price: $" + currNode.getInfo().toString().split(",")[5]
					+ ", Final price: $" + currNode.getInfo().toString().split(",")[6]+ " --->found in iteration "
					+ countLinearSearch + " by LinearSearch");
			}
			currNode = currNode.getLink();
			countLinearSearch++;
		}

		//code for linear search using array
		/* (int i = 0; i < numOfElements; i++) { if(!foundCoupon) {
		 if (couponArray[i].toString().split(",")[4].equalsIgnoreCase(product)) {
		 System.out.println("Coupon Details: " + couponArray[i].toString() +
		 " ---found " + countLinearSearch + " by LinearSearch"); foundCoupon = true;
		 temp = couponArray[i]; } countLinearSearch++; } }
		 */
		
		// calling method to do BST searching
		nodeSearch(root, temp, product);

		if (!couponFound) {
			System.out.println("No Coupons found - " + countLinearSearch + "th by Linear Search and "
					+ countBinarySearch + "th by BST");
		}

	}

	public BSTNode<T> buildBST(T A[], int first, int last) {

		if (first > last)
			return null;

		if (first == last)
			return new BSTNode<T>(A[first]);
		// making the middle element as root
		int mid = (first + last) / 2;
		BSTNode<T> node = new BSTNode<T>(A[mid]);
		node.setLeftNode(buildBST(A, first, mid - 1));
		node.setRightNode(buildBST(A, mid + 1, last));

		return node;
	}

	public BSTNode<T> nodeSearch(BSTNode<T> root, T obj, String product) {

		//method for search in BST
		countBinarySearch++;

		// coupon found in the linear search being passed to BST to find exact coupon 
		// on the same product
		if (obj == null) {

			if (root == null)
				return root;

			//comparing the values of coupon discount 
			// note: I have chose coupon discount as criteria to build BST
			if (root.getInfo().toString().split(",")[1].compareTo(product) < 0)
				return nodeSearch(root.getRightNode(), obj, product);

			return nodeSearch(root.getLeftNode(), obj, product);

		} else {

			if (root == null || root.getInfo().toString().equalsIgnoreCase(obj.toString())) {
				System.out.println("Cp provider: " + root.getInfo().toString().split(",")[0] + ", Cp. discount: "
						+ root.getInfo().toString().split(",")[1] + "%, Expiry: " + root.getInfo().toString().split(",")[2]
								+ "days, Status: " + root.getInfo().toString().split(",")[3] + ", Product: "
								+ root.getInfo().toString().split(",")[4] + ", Price: $" + root.getInfo().toString().split(",")[5]
								+ ", Final price: $" + root.getInfo().toString().split(",")[6]+ " --->found in iteration "
								+ countBinarySearch + " by BST");
				couponFound = true;
				return root;
			}

			//comparing the values of coupon discount to call recursive methods
			if (Integer.parseInt(root.getInfo().toString().split(",")[1]) < Integer
					.parseInt(obj.toString().split(",")[1]))
				return nodeSearch(root.getRightNode(), obj, product);

			return nodeSearch(root.getLeftNode(), obj, product);
		}

	}
	
	public void preOrder(BSTNode<T> node) {
		// method to print the nodes of BST in preorder traversal
		// visits root -> left subtree -> right subtree
		if (node == null)
			return;
		System.out.print(node.getInfo() + "\n");
		preOrder(node.getLeftNode());
		preOrder(node.getRightNode());

	}

	public BSTNode<T> getRootNode() {
		return root;
	}
	
	//below function displays all applicable coupons for the product obtained from user as input
	public void displayProductCoupons(String product) {
		
		for (int i = 0; i < numOfElements; i++) {
			if (couponArray[i].toString().split(",")[4].equalsIgnoreCase(product)) {
				System.out.println("Coupon provider: " + couponArray[i].toString().split(",")[0]);
			}
		}
	}

	//method to redeem the coupon
	public void redeemCoupon(String coupon, String product) {
		for (int i = 0; i < numOfElements; i++) {
			if (couponArray[i].toString().split(",")[4].equalsIgnoreCase(product) && couponArray[i].toString().split(",")[0].equalsIgnoreCase(coupon)) {
				((Coupon)couponArray[i]).setCouponStatus("redeemed");
				System.out.println("\nSuccess!! Coupon "+((Coupon)couponArray[i]).getCouponProviderName()+" redeemed for "+((Coupon)couponArray[i]).getProductName());
			}	
			
	}
}
}
