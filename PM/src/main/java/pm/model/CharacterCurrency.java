import java.util.Objects;

public class CharacterCurrency {
    private int characterId;
    private String currencyName;
    private String amountOwned;
    private String weeklyAmountOwned;

    // Constructors

    public CharacterCurrency() {
    }

    public CharacterCurrency(int characterId, String currencyName, String amountOwned, String weeklyAmountOwned) {
        this.characterId = characterId;
        this.currencyName = currencyName;
        this.amountOwned = amountOwned;
        this.weeklyAmountOwned = weeklyAmountOwned;
    }

    // Getter and Setter methods

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getAmountOwned() {
        return amountOwned;
    }

    public void setAmountOwned(String amountOwned) {
        this.amountOwned = amountOwned;
    }

    public String getWeeklyAmountOwned() {
        return weeklyAmountOwned;
    }

    public void setWeeklyAmountOwned(String weeklyAmountOwned) {
        this.weeklyAmountOwned = weeklyAmountOwned;
    }

    // Other methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterCurrency that = (CharacterCurrency) o;
        return characterId == that.characterId &&
                Objects.equals(currencyName, that.currencyName) &&
                Objects.equals(amountOwned, that.amountOwned) &&
                Objects.equals(weeklyAmountOwned, that.weeklyAmountOwned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(characterId, currencyName, amountOwned, weeklyAmountOwned);
    }

    @Override
    public String toString() {
        return "CharacterCurrency{" +
                "characterId=" + characterId +
                ", currencyName='" + currencyName + '\'' +
                ", amountOwned='" + amountOwned + '\'' +
                ", weeklyAmountOwned='" + weeklyAmountOwned + '\'' +
                '}';
    }
}
