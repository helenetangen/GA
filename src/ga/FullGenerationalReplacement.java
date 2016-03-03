package ga;


import java.util.ArrayList;
import java.util.List;


public class FullGenerationalReplacement implements AdultSelection{


	public List<Individual> select(List<Individual> childPopulation, List<Individual> adultPopulation) {
		return GeneticAlgorithm.copy(childPopulation);
	}

	
}
