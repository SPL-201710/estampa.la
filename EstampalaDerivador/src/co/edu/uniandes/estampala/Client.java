package co.edu.uniandes.estampala;

import co.edu.uniandes.estampala.services.DiverterService;

public class Client {
	
	private static DiverterService sDiverterService = null;
	
	public static DiverterService getDiverterService() {
		if(sDiverterService == null) {
			sDiverterService = new DiverterService();
		}		
		return sDiverterService;
	}
}