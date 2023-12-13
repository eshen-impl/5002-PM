package pm.model;
import java.util.Objects;

public class CharacterJob {
    private Character character;
    private Job job;
    private Long currentExp;
    private boolean isUnlocked;
    private boolean isCurrentJob;

    // Constructors

    public CharacterJob() {
    }

	public CharacterJob(Character character, Job job, Long currentExp, boolean isUnlocked, boolean isCurrentJob) {
		this.character = character;
		this.job = job;
		this.currentExp = currentExp;
		this.isUnlocked = isUnlocked;
		this.isCurrentJob = isCurrentJob;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Long getCurrentExp() {
		return currentExp;
	}

	public void setCurrentExp(Long currentExp) {
		this.currentExp = currentExp;
	}

	public boolean isUnlocked() {
		return isUnlocked;
	}

	public void setUnlocked(boolean isUnlocked) {
		this.isUnlocked = isUnlocked;
	}

	public boolean isCurrentJob() {
		return isCurrentJob;
	}

	public void setCurrentJob(boolean isCurrentJob) {
		this.isCurrentJob = isCurrentJob;
	}

	@Override
	public String toString() {
		return "CharacterJob [character=" + character + ", job=" + job + ", currentExp=" + currentExp + ", isUnlocked="
				+ isUnlocked + ", isCurrentJob=" + isCurrentJob + "]";
	}

	
   
}
