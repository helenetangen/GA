package copy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class UniformCrossover extends Crossover{

	
	public UniformCrossover(double crossoverRate) {
		super(crossoverRate);
	}

	
	public Population crossover(Population parentPopulation) {
		Random random = new Random();
		List<Individual> childPopulation = new ArrayList<Individual>();
		for (int i = 0; i < parentPopulation.size(); i+=2){
			boolean[] childOne = new boolean[parentPopulation.get(0).getGenotype().length];
			boolean[] childTwo = new boolean[parentPopulation.get(0).getGenotype().length];
			Individual parentOne = parentPopulation.get(i);
			Individual parentTwo = parentPopulation.get(i + 1);
			if (random.nextDouble() < crossoverRate){
				for (int j = 0; j < parentPopulation.get(0).getGenotype().length; j++){
					if (random.nextDouble() < 0.5){
						childOne[j] = parentOne.getGene(j);
						childTwo[j] = parentTwo.getGene(j);
					}
					else{
						childOne[j] = parentTwo.getGene(j);
						childTwo[j] = parentOne.getGene(j);
					}
				}
				childPopulation.add(new Individual(childOne));
				childPopulation.add(new Individual(childTwo));
			}
			else{
				childPopulation.add(new Individual(parentOne.copyGenotype()));
				childPopulation.add(new Individual(parentTwo.copyGenotype()));
			}
		}
		return new Population(childPopulation);
	}
	

}
