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
			int adultPopulationSize  = 26;
			int parentPopulationSize = 52;
			int childPopulationSize  = 52;
			int generations = 10;
			
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
			int tournamentSize = 4; 	
			double epsilon = 0.1;
			ParentSelection parentSelection = new TournamentSelection(parentPopulationSize, tournamentSize, epsilon);
			//ParentSelection parentSelection = new RouletteWheel(parentPopulationSize);
			
			//Crossover method
			//Crossover crossover = new SinglePointCrossover(crossoverRate);
			//Crossover crossover = new TwoPointCrossover(crossoverRate);
			Crossover crossover   = new UniformCrossover(crossoverRate);
			
			
			
			//Island Model
			int demeCount         = 4;  //Number of Islands
			int migrationRate     = 2;  //Number of individuals that migrate
			int migrationInterval = 20; //Generations between migration

			

			int simulations1 = 10;
			for (int i = 0; i < simulations1; i++){
				windScenario = new WindScenario("Scenarios/05.xml");
				IslandModel ga = new IslandModel(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate, demeCount, migrationRate, migrationInterval);
				ga.run(generations,  i);
			}
			
			int simulations2 = 20;
			for (int i = simulations1; i < simulations2; i++){
				windScenario = new WindScenario("Scenarios/obs_00.xml");
				IslandModel ga = new IslandModel(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate, demeCount, migrationRate, migrationInterval);
				ga.run(generations,  i);
			}
			
			int simulations3 = 30;
			for (int i = simulations2; i < simulations3; i++){
				windScenario = new WindScenario("Scenarios/obs_05.xml");
				IslandModel ga = new IslandModel(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate, demeCount, migrationRate, migrationInterval);
				ga.run(generations,  i);
			}
			


			

			

			
			
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	

}
