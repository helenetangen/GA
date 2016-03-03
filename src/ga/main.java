package ga;


public class main {
	
	
	public static void main(String[] args){
		try{
			//Wind scenario
			WindScenario WindScenario = new WindScenario("../Scenarios/00.xml");
			
			//Evaluator
			KusiakLayoutEvaluator evaluator = new KusiakLayoutEvaluator();
			
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
			double inversionMutationRate   = 0.01;
			double interchangeMutationRate = 0.01;
			double reversingMutationRate   = 0.01;
			
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
			
			GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, individualSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
			ga.run(generations);
			
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}

}
