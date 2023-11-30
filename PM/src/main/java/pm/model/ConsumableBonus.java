package pm.model;

import java.math.BigDecimal;



public class ConsumableBonus {
    protected Item item;
    protected String attribute;
    protected BigDecimal bonusPercentage;
    protected long bonusCap;

    public ConsumableBonus(Item item, String attribute, BigDecimal bonusPercentage, long bonusCap) {
        this.item = item;
        this.attribute = attribute;
        this.bonusPercentage = bonusPercentage;
        this.bonusCap = bonusCap;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public BigDecimal getBonusPercentage() {
        return bonusPercentage;
    }

    public void setBonusPercentage(BigDecimal bonusPercentage) {
        this.bonusPercentage = bonusPercentage;
    }

    public long getBonusCap() {
        return bonusCap;
    }

    public void setBonusCap(long bonusCap) {
        this.bonusCap = bonusCap;
    }
}
