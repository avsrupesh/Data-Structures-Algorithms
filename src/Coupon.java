
public class Coupon {

	public String couponProviderName;
	public int couponDiscount;
	public int couponExpiryPeriod;
	public String couponStatus;
	public String productName;
	public int productPrice;
	public double finalProductPrice;
	
	public String getCouponProviderName() {
		return couponProviderName;
	}

	public void setCouponProviderName(String couponProviderName) {
		this.couponProviderName = couponProviderName;
	}

	public int getCouponDiscount() {
		return couponDiscount;
	}

	public void setCouponDiscount(int couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	public int getCouponExpiryPeriod() {
		return couponExpiryPeriod;
	}

	public void setCouponExpiryPeriod(int couponExpiryPeriod) {
		this.couponExpiryPeriod = couponExpiryPeriod;
	}

	public String getCouponStatus() {
		return couponStatus;
	}

	public void setCouponStatus(String couponStatus) {
		this.couponStatus = couponStatus;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public double getFinalProductPrice() {
		return finalProductPrice;
	}

	public void setFinalProductPrice(double finalProductPrice) {
		this.finalProductPrice = finalProductPrice;
	}

	public Coupon(String couponProviderName, int couponDiscount, int couponExpiryPeriod, String couponStatus,
			String productName, int productPrice, double finalProductPrice) {
		super();
		this.couponProviderName = couponProviderName;
		this.couponDiscount = couponDiscount;
		this.couponExpiryPeriod = couponExpiryPeriod;
		this.couponStatus = couponStatus;
		this.productName = productName;
		this.productPrice = productPrice;
		this.finalProductPrice = finalProductPrice;
	}

	@Override
	public String toString() {
		return couponProviderName + "," + couponDiscount
				+ "," + couponExpiryPeriod + "," + couponStatus + ","
				+ productName + "," + productPrice+","+finalProductPrice;
	}
	
}
