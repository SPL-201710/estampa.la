package co.edu.uniandes.estampala.dto;

import java.util.LinkedList;
import java.util.List;

public class Module {
	
	private String name = null;
	private List<Feature> features = null;
	
	public Module(String name) {
		super();
		
		this.name = name;
		this.features = new LinkedList<Feature>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(List<Feature> features) {
		this.features = features;
	}
	
	public void addFeature(String feature, boolean active	) {
		this.features.add(new Feature(feature, active));
	}

	@Override
	public String toString() {
		return this.name;
	}
}
