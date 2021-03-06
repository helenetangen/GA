package copy;


public class main {
	
	
	public static void main(String[] args){
		
		//Parameters
		int adultPopulationSize  = 10;
		int parentPopulationSize = 10;
		int childPopulationSize  = 10;
		int individualSize = 20;
		int generations = 20;
		
		//Crossover
		double crossoverRate = 0.9;
		
		//Mutation
		double flipMutationRate        = 0.01;
		double inversionMutationRate   = 0.0;
		double interchangeMutationRate = 0.0;
		double reversingMutationRate   = 0.0;
		
		//Adult selection:
		AdultSelection adultSelection = new FullGenerationalReplacement();
		//AdultSelection adultSelection = new OverProduction(adultPopulationSize);
		//AdultSelection adultSelection = new GenerationalMixing(adultPopulationSize);
		
		//Parent selection
		int tournamentSize = 4; 	
		ParentSelection parentSelection = new TournamentSelection(parentPopulationSize, tournamentSize);
		
		//Crossover method
		//Crossover crossover = new SinglePointCrossover(crossoverRate);
		//Crossover crossover = new TwoPointCrossover(crossoverRate);
		Crossover crossover   = new UniformCrossover(crossoverRate);
		
		GeneticAlgorithm ga = new GeneticAlgorithm(childPopulationSize, individualSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
		ga.run(generations);
	}

}
