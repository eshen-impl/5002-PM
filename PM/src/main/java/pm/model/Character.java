package pm.model;


public class Character implements Comparable<Character>{
	protected Integer characterId;
	protected Player account;
	protected String characterFirstName;
	protected String characterLastName;
	
	public Character(Integer characterId, Player account, String characterFirstName, String characterLastName) {

		this.characterId = characterId;
		this.account = account;
		this.characterFirstName = characterFirstName;
		this.characterLastName = characterLastName;
	}

	public Character(Player account, String characterFirstName, String characterLastName) {
		this.account = account;
		this.characterFirstName = characterFirstName;
		this.characterLastName = characterLastName;
	}

	public Character(Integer characterId) {
		this.characterId = characterId;
	}

	public Integer getCharacterId() {
		return characterId;
	}

	public void setCharacterId(Integer characterId) {
		this.characterId = characterId;
	}

	public Player getAccount() {
		return account;
	}

	public void setAccount(Player account) {
		this.account = account;
	}

	public String getCharacterFirstName() {
		return characterFirstName;
	}

	public void setCharacterFirstName(String characterFirstName) {
		this.characterFirstName = characterFirstName;
	}

	public String getCharacterLastName() {
		return characterLastName;
	}

	public void setCharacterLastName(String characterLastName) {
		this.characterLastName = characterLastName;
	}

	@Override
	public String toString() {
		return "Character [characterId=" + characterId + ", account=" + account + ", characterFirstName="
				+ characterFirstName + ", characterLastName=" + characterLastName + "]";
	}
	
    @Override
    public int compareTo(Character otherCharacter) {
        // Assuming getFirstName returns a String
        return characterFirstName.compareTo(otherCharacter.characterFirstName);
    }

	
	
	
	
	
}
