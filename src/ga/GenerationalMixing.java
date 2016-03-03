package ga;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GenerationalMixing implements AdultSelection{

	
	private int adultPopulationSize;
	
	
	public GenerationalMixing(int adultPopulationSize){
		this.adultPopulationSize = adultPopulationSize;
	}
	
	
	public List<Individual> select(List<Individual> childPopulation, List<Individual> adultPopulation) {
		adultPopulation.addAll(childPopulation);
		Collections.sort(adultPopulation);
		return GeneticAlgorithm.copy(adultPopulation.subList(0, adultPopulationSize));
	}

	
}
