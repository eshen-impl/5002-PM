package pm.model;
import java.util.Objects;

public class CharacterJob {
    private int characterId;
    private int jobId;
    private String currentExp;
    private int currentLevel;
    private boolean isUnlocked;
    private boolean isCurrentJob;

    // Constructors

    public CharacterJob() {
    }

    public CharacterJob(int characterId, int jobId, String currentExp, int currentLevel, boolean isUnlocked, boolean isCurrentJob) {
        this.characterId = characterId;
        this.jobId = jobId;
        this.currentExp = currentExp;
        this.currentLevel = currentLevel;
        this.isUnlocked = isUnlocked;
        this.isCurrentJob = isCurrentJob;
    }

    // Getter and Setter methods

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getCurrentExp() {
        return currentExp;
    }

    public void setCurrentExp(String currentExp) {
        this.currentExp = currentExp;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }

    public boolean isCurrentJob() {
        return isCurrentJob;
    }

    public void setCurrentJob(boolean currentJob) {
        isCurrentJob = currentJob;
    }

    // Other methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CharacterJob that = (CharacterJob) o;
        return characterId == that.characterId &&
                jobId == that.jobId &&
                currentLevel == that.currentLevel &&
                isUnlocked == that.isUnlocked &&
                isCurrentJob == that.isCurrentJob &&
                Objects.equals(currentExp, that.currentExp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(characterId, jobId, currentExp, currentLevel, isUnlocked, isCurrentJob);
    }

    @Override
    public String toString() {
        return "CharacterJob{" +
                "characterId=" + characterId +
                ", jobId=" + jobId +
                ", currentExp='" + currentExp + '\'' +
                ", currentLevel=" + currentLevel +
                ", isUnlocked=" + isUnlocked +
                ", isCurrentJob=" + isCurrentJob +
                '}';
    }
}
