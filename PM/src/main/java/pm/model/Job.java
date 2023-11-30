package pm.model;


public class Job {
	protected Integer jobId;
	protected String jobName;
	protected Integer jobLevel;
	protected Integer minLevelExp;
	protected Integer maxLevelExp;
	
	public Job(Integer jobId, String jobName, Integer jobLevel, Integer minLevelExp, Integer maxLevelExp) {

		this.jobId = jobId;
		this.jobName = jobName;
		this.jobLevel = jobLevel;
		this.minLevelExp = minLevelExp;
		this.maxLevelExp = maxLevelExp;
	}
	
	public Job(String jobName, Integer jobLevel, Integer minLevelExp, Integer maxLevelExp) {

		this.jobName = jobName;
		this.jobLevel = jobLevel;
		this.minLevelExp = minLevelExp;
		this.maxLevelExp = maxLevelExp;
	}
	
	public Job(Integer jobId) {

		this.jobId = jobId;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public Integer getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(Integer jobLevel) {
		this.jobLevel = jobLevel;
	}

	public Integer getMinLevelExp() {
		return minLevelExp;
	}

	public void setMinLevelExp(Integer minLevelExp) {
		this.minLevelExp = minLevelExp;
	}

	public Integer getMaxLevelExp() {
		return maxLevelExp;
	}

	public void setMaxLevelExp(Integer maxLevelExp) {
		this.maxLevelExp = maxLevelExp;
	}

	@Override
	public String toString() {
		return "Job [jobId=" + jobId + ", jobName=" + jobName + ", jobLevel=" + jobLevel + ", minLevelExp="
				+ minLevelExp + ", maxLevelExp=" + maxLevelExp + "]";
	}
	
	
	
	
	
	
	
}
