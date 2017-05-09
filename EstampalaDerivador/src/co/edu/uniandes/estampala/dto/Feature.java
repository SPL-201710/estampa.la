package co.edu.uniandes.estampala.dto;

public class Feature {
	
	private String  name = null;
	private boolean	active = false;
	
	public Feature(String name, boolean active) {
		super();
		this.name = name;
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}