package milestone.model;

public class Customization {
	protected int customizationId;
	protected Item item;
	protected Color color;
	protected float condition;
	protected IsHighQuality isHighQuality;
	protected Character character;
	
	public enum IsHighQuality{
		High,Normal
	}

	public Customization(int customizationId, Item item, Color color, float condition, IsHighQuality isHighQUality,Character character) {
		
		this.customizationId = customizationId;
		this.item = item;
		this.color = color;
		this.condition = condition;
		this.isHighQuality = isHighQuality;
		this.character = character;
	}

	public Customization(Item item, Color color, float condition, IsHighQuality isHighQUality,Character character) {

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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
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

	public IsHighQuality getIsHighQuality() {
		return isHighQuality;
	}

	public void setIsHighQuality(IsHighQuality isHighQuality) {
		this.isHighQuality = isHighQuality;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}
	
	

}
