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
			int parentPopulationSize = 200;
			int childPopulationSize  = 200;
			int generations = 200;
			
			//Crossover
			double crossoverRate = 0.9;
			boolean elitism      = true;
			
			//Mutation
			double flipMutationRate        = 0.001;
			double inversionMutationRate   = 0.000001;
			double interchangeMutationRate = 0.000001;
			
			double reversingMutationRate   = 0.0;
			
			//Adult selection:
			//AdultSelection adultSelection = new FullGenerationalReplacement();
			AdultSelection adultSelection = new OverProduction(adultPopulationSize);
			//AdultSelection adultSelection = new GenerationalMixing(adultPopulationSize);
			
			//Parent selection
			int tournamentSize = 20; 	
			double epsilon = 0.1;
			ParentSelection parentSelection = new TournamentSelection(parentPopulationSize, tournamentSize, epsilon);
			//ParentSelection parentSelection = new RouletteWheel(parentPopulationSize);
			
			//Crossover method
			//Crossover crossover = new SinglePointCrossover(crossoverRate);
			//Crossover crossover = new TwoPointCrossover(crossoverRate);
			Crossover crossover   = new UniformCrossover(crossoverRate);
			
			//Epsilon: 0.05

			
			int simulations = 10;
			for (int i = 0; i < simulations; i++){
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			int simulations1 = 20;
			for (int i = simulations; i < simulations1; i++){
				windScenario = new WindScenario("Scenarios/05.xml");
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			int simulations2 = 30;
			for (int i = simulations1; i < simulations2; i++){
				windScenario = new WindScenario("Scenarios/obs_00.xml");
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			int simulations3 = 40;
			for (int i = simulations2; i < simulations3; i++){
				windScenario = new WindScenario("Scenarios/obs_05.xml");
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			


			

			

			
			
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	

}
