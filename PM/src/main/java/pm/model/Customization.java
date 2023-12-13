package pm.model;

public class Customization {
	protected int customizationId;
	protected Item item;
	protected String color;

	
	protected float condition;

	protected Character character;
	protected String isHighQuality;


	public Customization(int customizationId, Item item, String color, float condition, String isHighQuality,Character character) {
		
		this.customizationId = customizationId;
		this.item = item;
		this.color = color;
		this.condition = condition;
		this.isHighQuality = isHighQuality;
		this.character = character;
	}

	public Customization(Item item, String color, float condition, String isHighQuality,Character character) {

		this.item = item;
		this.color = color;
		this.condition = condition;
		this.isHighQuality = isHighQuality;
		this.character = character;
	}

	public Customization(int customizationId) {

		this.customizationId = customizationId;
	}

	public int getCustomizationId() {
		return customizationId;
	}

	public void setCustomizationId(int customizationId) {
		this.customizationId = customizationId;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getCondition() {
		return condition;
	}

	public void setCondition(float condition) {
		this.condition = condition;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}
	public String getIsHighQuality() {
		return isHighQuality;
	}

	public void setIsHighQuality(String isHighQuality) {
		this.isHighQuality = isHighQuality;
	}

	@Override
	public String toString() {
		return "Customization [customizationId=" + customizationId + ", item=" + item + ", color=" + color
				+ ", condition=" + condition + ", character=" + character + ", isHighQuality=" + isHighQuality + "]";
	}
	

}
