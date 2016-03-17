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
			//AdultSelection adultSelection = new FullGenerationalReplacement();
			AdultSelection adultSelection = new OverProduction(adultPopulationSize);
			//AdultSelection adultSelection = new GenerationalMixing(adultPopulationSize);
			
			//Parent selection
			int tournamentSize = 4; 	
			ParentSelection parentSelection = new TournamentSelection(parentPopulationSize, tournamentSize);
			
			//Crossover method
			//Crossover crossover = new SinglePointCrossover(crossoverRate, elitism);
			//Crossover crossover = new TwoPointCrossover(crossoverRate, elitism);
			Crossover crossover   = new UniformCrossover(crossoverRate);
			
			int simulations = 1;
			for (int i = 0; i < simulations; i++){
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	

}
