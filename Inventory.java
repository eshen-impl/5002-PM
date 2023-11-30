import java.util.Objects;

public class Inventory {
    private int characterId;
    private int slotId;
    private int customizationId;
    private int itemId;
    private int quantity;
    private boolean isEquipped;

    // Constructors

    public Inventory() {
    }

    public Inventory(int characterId, int slotId, int customizationId, int itemId, int quantity, boolean isEquipped) {
        this.characterId = characterId;
        this.slotId = slotId;
        this.customizationId = customizationId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.isEquipped = isEquipped;
    }

    // Getter and Setter methods

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public int getCustomizationId() {
        return customizationId;
    }

    public void setCustomizationId(int customizationId) {
        this.customizationId = customizationId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isEquipped() {
        return isEquipped;
    }

    public void setEquipped(boolean equipped) {
        isEquipped = equipped;
    }

    // Other methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return characterId == inventory.characterId &&
                slotId == inventory.slotId &&
                customizationId == inventory.customizationId &&
                itemId == inventory.itemId &&
                quantity == inventory.quantity &&
                isEquipped == inventory.isEquipped;
    }

    @Override
    public int hashCode() {
        return Objects.hash(characterId, slotId, customizationId, itemId, quantity, isEquipped);
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "characterId=" + characterId +
                ", slotId=" + slotId +
                ", customizationId=" + customizationId +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                ", isEquipped=" + isEquipped +
                '}';
    }
}
