package pm.model;

public class GearJob {
	protected Gear gear;
	protected Job job;
	public GearJob(Gear gear, Job job) {
		this.gear = gear;
		this.job = job;
	}
	
	public GearJob(Gear gear) {
		super();
		this.gear = gear;
	}

	public GearJob(Job job) {
		this.job = job;
	}

	public Gear getGear() {
		return gear;
	}
	public void setGear(Gear gear) {
		this.gear = gear;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "GearJob [gear=" + gear + ", job=" + job + "]";
	}
	
	

}
