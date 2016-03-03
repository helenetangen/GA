package ga;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OverProduction implements AdultSelection{

	
	private int adultPopulationSize;
	
	
	public OverProduction(int adultPopulationSize){
		this.adultPopulationSize = adultPopulationSize;
	}
	
	
	public Population select(Population childPopulation, Population adultPopulation) {
		Collections.sort(childPopulation.getPopulation());
		return new Population(childPopulation.getPopulation().subList(0, adultPopulationSize)).copy();
	}

	
}
