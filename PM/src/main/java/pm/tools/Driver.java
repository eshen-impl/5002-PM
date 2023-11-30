package pm.tools;

import pm.dal.*;
import pm.model.*;
import pm.model.Character;

import java.sql.SQLException;

import java.util.List;





public class Driver {

	public static void main(String[] args) throws SQLException {
		// DAO instances.
		PlayerDao playerDao = PlayerDao.getInstance();
		CharacterDao characterDao = CharacterDao.getInstance();
		JobDao jobDao = JobDao.getInstance();
		CurrencyDao currencyDao = CurrencyDao.getInstance();
	
		// test PlayerDao
		Player newPlayer = new Player( "katy", "katy@sina.com", true);
		newPlayer = playerDao.create(newPlayer);
		int exPlayerId = 33;
		Player exPlayer = playerDao.getPlayerByID(exPlayerId);
		System.out.println("Reading player: " + exPlayer);
		
		// test CharacterDao
		Character newCharacter = new Character(newPlayer, "Patti", "Lulu");
		newCharacter = characterDao.create(newCharacter);
		int exCharacterId = 4;
		Character exCharacter = characterDao.getCharacterById(exCharacterId);
		System.out.println("Reading character: " + exCharacter);
		List<Character> characters = characterDao.getCharactersForPlayer(exPlayer);
		System.out.println("Looping characters:  " );
		for(Character c : characters) {
			System.out.println(c);
		}
		characterDao.delete(exCharacter);
		
		
		// test JobDao
		Job newJob = new Job( "Archer", 1, 0, 1200);
		newJob = jobDao.create(newJob);
		int exJobId = 6;
		Job exJob = jobDao.getJobByID(exJobId);
		System.out.println("Reading job: " + exJob);
		
		//test CurrencyDao
		Currency newCurrency = new Currency("Voucher", 1000000, 50000, false);
		newCurrency = currencyDao.create(newCurrency);
		String exCurrencyName = "Diamonds";
		Currency exCurrency = currencyDao.getCurrencyById(exCurrencyName);
		System.out.println("Reading currency: " + exCurrency);
		int newWeeklyCap = 240000;
		exCurrency = currencyDao.updateWeeklyCap(exCurrency, newWeeklyCap);
		exCurrency = currencyDao.getCurrencyById(exCurrencyName);
		System.out.println("Reading updated currency: " + exCurrency);
	}
	
	
	
}
