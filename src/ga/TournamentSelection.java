package ga;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TournamentSelection implements ParentSelection{

	
	private int parentPopulationSize;
	private int tournamentSize;
	
	
	public TournamentSelection(int parentPopulationSize, int tournamentSize){
		this.parentPopulationSize = parentPopulationSize;
		this.tournamentSize = tournamentSize;
	}
	
	
	public Population select(Population adultPopulation) {
		List<Individual> parentPopulation = new ArrayList<Individual>();
		Random random = new Random();
		for (int i = 0; i < parentPopulationSize; i++){
			int bestIndex = -1;
			double bestFitness = Double.MIN_VALUE;
			for (int j = 0; j < tournamentSize; j++){
				int index = random.nextInt(adultPopulation.size());
				if (adultPopulation.get(index).getFitness() > bestFitness){
					bestFitness = adultPopulation.get(index).getFitness();
					bestIndex = index;
				}
			}
			parentPopulation.add(new Individual(adultPopulation.get(bestIndex).copyGenotype(), adultPopulation.get(bestIndex).getFitness()));
		}
		return new Population(parentPopulation);
	}

	
}
