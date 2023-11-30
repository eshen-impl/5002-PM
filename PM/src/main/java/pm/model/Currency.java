package pm.model;


public class Currency {
	protected String currencyName;
	protected Integer totalCap;
	protected Integer weeklyCap;
	protected Boolean discontinued;
	
	public Currency(String currencyName, Integer totalCap, Integer weeklyCap, Boolean discontinued) {
		this.currencyName = currencyName;
		this.totalCap = totalCap;
		this.weeklyCap = weeklyCap;
		this.discontinued = discontinued;
	}

	public Currency(String currencyName) {

		this.currencyName = currencyName;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public Integer getTotalCap() {
		return totalCap;
	}

	public void setTotalCap(Integer totalCap) {
		this.totalCap = totalCap;
	}

	public Integer getWeeklyCap() {
		return weeklyCap;
	}

	public void setWeeklyCap(Integer weeklyCap) {
		this.weeklyCap = weeklyCap;
	}

	public Boolean getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Boolean discontinued) {
		this.discontinued = discontinued;
	}

	@Override
	public String toString() {
		return "Currency [currencyName=" + currencyName + ", totalCap=" + totalCap + ", weeklyCap=" + weeklyCap
				+ ", discontinued=" + discontinued + "]";
	}
	
	
	
	
}
