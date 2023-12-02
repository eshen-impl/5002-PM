package pm.model;
import java.util.Objects;

public class Inventory {
    private Character character;
    private int slotId;
    private Customization customization;
    private Item item;
    private int quantity;

    // Constructors

    public Inventory() {
    }

	public Inventory(Character character, int slotId, Customization customization, Item item, int quantity) {

		this.character = character;
		this.slotId = slotId;
		this.customization = customization;
		this.item = item;
		this.quantity = quantity;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public int getSlotId() {
		return slotId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public Customization getCustomization() {
		return customization;
	}

	public void setCustomization(Customization customization) {
		this.customization = customization;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Inventory [character=" + character + ", slotId=" + slotId + ", customization=" + customization
				+ ", item=" + item + ", quantity=" + quantity + "]";
	}

  
}
