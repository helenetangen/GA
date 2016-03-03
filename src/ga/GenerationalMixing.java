package ga;


import java.util.Collections;
import java.util.List;


public class GenerationalMixing implements AdultSelection{

	
	private int adultPopulationSize;
	
	
	public GenerationalMixing(int adultPopulationSize){
		this.adultPopulationSize = adultPopulationSize;
	}
	
	
	public Population select(Population childPopulation, Population adultPopulation) {
		adultPopulation.addAll(childPopulation);
		Collections.sort(adultPopulation.getPopulation());
		List<Individual> subPopulation = adultPopulation.getPopulation().subList(0, adultPopulationSize);
		return new Population(subPopulation).copy();
	}

	
}
