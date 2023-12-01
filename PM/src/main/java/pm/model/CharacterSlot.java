package pm.model;
import java.util.Objects;

public class CharacterSlot {
    private int characterId;
    private String slotType;
    private int equippedItem;
    private int customization;

    // Constructors

    public CharacterSlot() {
    }

    public CharacterSlot(int characterId, String slotType, int equippedItem, int customization) {
        this.characterId = characterId;
        this.slotType = slotType;
        this.equippedItem = equippedItem;
        this.customization = customization;
    }

    // Getter and Setter methods

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getSlotType() {
        return slotType;
    }

    public void setSlotType(String slotType) {
        this.slotType = slotType;
    }

    public int getEquippedItem() {
        return equippedItem;
    }

    public void setEquippedItem(int equippedItem) {
        this.equippedItem = equippedItem;
    }

    public int getCustomization() {
        return customization;
    }

    public void setCustomization(int customization) {
        this.customization = customization;
    }

    // Other methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterSlot that = (CharacterSlot) o;
        return characterId == that.characterId &&
                equippedItem == that.equippedItem &&
                customization == that.customization &&
                Objects.equals(slotType, that.slotType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(characterId, slotType, equippedItem, customization);
    }

    @Override
    public String toString() {
        return "CharacterSlot{" +
                "characterId=" + characterId +
                ", slotType='" + slotType + '\'' +
                ", equippedItem=" + equippedItem +
                ", customization=" + customization +
                '}';
    }
}
