package pm.model;
import java.util.Objects;

public class Inventory {
    private Character characterId;
    private int slotId;
    private Customization customization;
    private Item itemId;
    private int quantity;

    // Constructors

    public Inventory() {
    }

	public Inventory(Character characterId, int slotId, Customization customization, Item itemId, int quantity) {

		this.characterId = characterId;
		this.slotId = slotId;
		this.customization = customization;
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public Character getCharacter() {
		return characterId;
	}

	public void setCharacter(Character characterId) {
		this.characterId = characterId;
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
		return itemId;
	}

	public void setItem(Item itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Inventory [character=" + characterId + ", slotId=" + slotId + ", customization=" + customization
				+ ", item=" + itemId + ", quantity=" + quantity + "]";
	}

  
}
