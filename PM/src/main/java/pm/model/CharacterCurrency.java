package pm.model;
import java.util.Objects;

public class CharacterCurrency {
    private Character character;
    private Currency currency;
    private Integer amountOwned;
    private Integer weeklyAmountOwned;

    // Constructors

    public CharacterCurrency() {
    }

	public CharacterCurrency(Character character, Currency currency, Integer amountOwned, Integer weeklyAmountOwned) {
		this.character = character;
		this.currency = currency;
		this.amountOwned = amountOwned;
		this.weeklyAmountOwned = weeklyAmountOwned;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Integer getAmountOwned() {
		return amountOwned;
	}

	public void setAmountOwned(Integer amountOwned) {
		this.amountOwned = amountOwned;
	}

	public Integer getWeeklyAmountOwned() {
		return weeklyAmountOwned;
	}

	public void setWeeklyAmountOwned(Integer weeklyAmountOwned) {
		this.weeklyAmountOwned = weeklyAmountOwned;
	}

	@Override
	public String toString() {
		return "CharacterCurrency [character=" + character + ", currency=" + currency + ", amountOwned=" + amountOwned
				+ ", weeklyAmountOwned=" + weeklyAmountOwned + "]";
	}

    
    
    
}
