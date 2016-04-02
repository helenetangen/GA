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
			int adultPopulationSize  = 100;
			int parentPopulationSize = 100;
			int childPopulationSize  = 100;
			int generations = 100;
			
			//Crossover
			double crossoverRate = 0.9;
			boolean elitism      = true;
			
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
			int tournamentSize = 5; 	
			ParentSelection parentSelection = new TournamentSelection(parentPopulationSize, tournamentSize);
			//ParentSelection parentSelection = new RouletteWheel(parentPopulationSize);
			
			//Crossover method
			//Crossover crossover = new SinglePointCrossover(crossoverRate);
			//Crossover crossover = new TwoPointCrossover(crossoverRate);
			Crossover crossover   = new UniformCrossover(crossoverRate);
			
			
			//Mutation rate 0.0005
			int simulations = 5;
			for (int i = 0; i < simulations; i++){
				flipMutationRate = 0.0005;
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			//Mutation rate 0.0001
			int simulations2 = 10;
			for (int i = simulations; i < simulations2; i++){
				flipMutationRate = 0.0001;
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			//Mutation rate 0.00005
			int simulations3 = 10;
			for (int i = simulations2; i < simulations3; i++){
				flipMutationRate = 0.00005;
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			

			

			
			
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	

}
