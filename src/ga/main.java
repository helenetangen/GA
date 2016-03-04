package ga;


public class main {
	
	
	public static void main(String[] args){
		try{
			//Wind scenario
			WindScenario windScenario = new WindScenario("Scenarios/00.xml");
			
			//Evaluator
			KusiakLayoutEvaluator evaluator = new KusiakLayoutEvaluator();
			evaluator.initialize(windScenario);
			
			//Parameters
			int adultPopulationSize  = 10;
			int parentPopulationSize = 10;
			int childPopulationSize  = 10;
			int individualSize = 20;
			int generations = 10;
			
			//Crossover
			double crossoverRate = 0.1;
			
			//Mutation
			double flipMutationRate        = 0.0;
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
			
			GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
			ga.run(generations);
			
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}

}
