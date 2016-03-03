package ga;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class OverProduction implements AdultSelection{

	
	private int adultPopulationSize;
	
	
	public OverProduction(int adultPopulationSize){
		this.adultPopulationSize = adultPopulationSize;
	}
	
	
	public List<Individual> select(List<Individual> childPopulation, List<Individual> adultPopulation) {
		Collections.sort(childPopulation);
		return GeneticAlgorithm.copy(childPopulation.subList(0, adultPopulationSize));
	}

	
}
