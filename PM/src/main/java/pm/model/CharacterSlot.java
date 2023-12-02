package pm.model;
import java.util.Objects;

public class CharacterSlot {
    private Character character;
    private String slotType;
    private Equippable equippedItem;
    private Customization customization;

    // Constructors

    public CharacterSlot() {
    }

    public CharacterSlot(Character character, String slotType, Equippable equippedItem, Customization customization) {
        this.character = character;
        this.slotType = slotType;
        this.equippedItem = equippedItem;
        this.customization = customization;
    }

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public String getSlotType() {
		return slotType;
	}

	public void setSlotType(String slotType) {
		this.slotType = slotType;
	}

	public Equippable getEquippedItem() {
		return equippedItem;
	}

	public void setEquippedItem(Equippable equippedItem) {
		this.equippedItem = equippedItem;
	}

	public Customization getCustomization() {
		return customization;
	}

	public void setCustomization(Customization customization) {
		this.customization = customization;
	}

	@Override
	public String toString() {
		return "CharacterSlot [character=" + character + ", slotType=" + slotType + ", equippedItem=" + equippedItem
				+ ", customization=" + customization + "]";
	}

  
}
