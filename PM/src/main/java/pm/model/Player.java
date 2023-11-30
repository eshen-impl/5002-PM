package pm.model;


public class Player {
	protected Integer accountId;
	protected String name;
	protected String emailAddress;
	protected Boolean isActive;
	
	public Player(Integer accountId, String name, String emailAddress, Boolean isActive) {
		this.accountId = accountId;
		this.name = name;
		this.emailAddress = emailAddress;
		this.isActive = isActive;
	}

	public Player(String name, String emailAddress, Boolean isActive) {
		this.name = name;
		this.emailAddress = emailAddress;
		this.isActive = isActive;
	}
	
	

	public Player(String name, String emailAddress) {
		this.name = name;
		this.emailAddress = emailAddress;
	}

	public Player(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public String toString() {
		return "Player [accountId=" + accountId + ", name=" + name + ", emailAddress=" + emailAddress + ", isActive="
				+ isActive + "]";
	}
	
	
	
}
