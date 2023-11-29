package pm.model;

import java.math.BigDecimal;



public class ConsumableBonus {
    protected int itemId;
    protected String attribute;
    protected BigDecimal bonusPercentage;
    protected long bonusCap;
    
  

	
    public ConsumableBonus(int itemId, String attribute, BigDecimal bonusPercentage, long bonusCap) {
        this.itemId = itemId;
        this.attribute = attribute;
        this.bonusPercentage = bonusPercentage;
        this.bonusCap = bonusCap;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
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
