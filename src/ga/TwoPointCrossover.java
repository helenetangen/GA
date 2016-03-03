package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TwoPointCrossover extends Crossover{

	
	public TwoPointCrossover(double crossoverRate) {
		super(crossoverRate);
	}

	
	public Population crossover(Population parentPopulation){
		Random random = new Random();
		List<Individual> childPopulation = new ArrayList<Individual>();
		for (int i = 0; i < parentPopulation.size(); i+=2){
			
			boolean[] childOne = new boolean[parentPopulation.get(0).getGenotype().length];
			boolean[] childTwo = new boolean[parentPopulation.get(0).getGenotype().length];
			int crossoverPointOne = random.nextInt(parentPopulation.get(0).getGenotype().length);
			int crossoverPointTwo = random.nextInt(parentPopulation.get(0).getGenotype().length);
			if (crossoverPointOne > crossoverPointTwo){
				int temporary = crossoverPointOne;
				crossoverPointOne = crossoverPointTwo;
				crossoverPointTwo = temporary;
			}
			Individual parentOne = parentPopulation.get(i);
			Individual parentTwo = parentPopulation.get(i + 1);
			if (random.nextDouble() < crossoverRate){
				for (int j = 0; j < crossoverPointOne; j++){
					childOne[j] = parentOne.getGene(j);
					childTwo[j] = parentTwo.getGene(j);
				}
				for (int k = crossoverPointOne; k < crossoverPointTwo; k++){
					childOne[k] = parentTwo.getGene(k);
					childTwo[k] = parentOne.getGene(k);
				}
				for (int l = crossoverPointTwo; l < parentPopulation.get(0).getGenotype().length; l++){
					childOne[l] = parentOne.getGene(l);
					childTwo[l] = parentTwo.getGene(l);
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
