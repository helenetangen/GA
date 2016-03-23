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
			
			
			//Crossover rate 0.0
			int simulations = 5;
			for (int i = 0; i < simulations; i++){
				crossoverRate = 0.0;
				crossover   = new UniformCrossover(crossoverRate);
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			//Crossover rate 0.2
			int simulations2 = 10;
			for (int i = simulations; i < simulations2; i++){
				crossoverRate = 0.2;
				crossover   = new UniformCrossover(crossoverRate);
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			//Crossover rate 0.6
			int simulations3  = 20;
			for (int i = simulations2; i < simulations3; i++){
				crossoverRate = 0.6;
				crossover   = new UniformCrossover(crossoverRate);
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			//Crossover rate 0.8
			int simulations4  = 30;
			for (int i = simulations3; i < simulations4; i++){
				crossoverRate = 0.8;
				crossover   = new UniformCrossover(crossoverRate);
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			//Crossover rate 1.0
			int simulations5  = 40;
			for (int i = simulations4; i < simulations5; i++){
				crossoverRate = 1.0;
				crossover   = new UniformCrossover(crossoverRate);
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			crossoverRate = 0.9;
			
			//Single Point Crossover
			int simulations6  = 45;
			for (int i = simulations5; i < simulations6; i++){
				crossover   = new SinglePointCrossover(crossoverRate);
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			//Two Point Crossover
			int simulations7  = 50;
			for (int i = simulations6; i < simulations7; i++){
				crossover   = new TwoPointCrossover(crossoverRate);
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			//Uniform Crossover
			int simulations8  = 55;
			for (int i = simulations7; i < simulations8; i++){
				crossover   = new UniformCrossover(crossoverRate);
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			crossover = new UniformCrossover(crossoverRate);
			
			//Roulette Wheel
			int simulations9  = 60;
			for (int i = simulations8; i < simulations9; i++){
				parentSelection = new RouletteWheel(parentPopulationSize);
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			//Tournament size 5
			int simulations10  = 65;
			for (int i = simulations9; i < simulations10; i++){
				tournamentSize = 5;
				parentSelection = new TournamentSelection(parentPopulationSize, tournamentSize);
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			//Tournament size 10
			int simulations11  = 70;
			for (int i = simulations10; i < simulations11; i++){
				tournamentSize = 10;
				parentSelection = new TournamentSelection(parentPopulationSize, tournamentSize);
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			//Tournament size 15
			int simulations12  = 75;
			for (int i = simulations11; i < simulations12; i++){
				tournamentSize = 15;
				parentSelection = new TournamentSelection(parentPopulationSize, tournamentSize);
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			//Tournament size 20
			int simulations13  = 80;
			for (int i = simulations12; i < simulations13; i++){
				tournamentSize = 20;
				parentSelection = new TournamentSelection(parentPopulationSize, tournamentSize);
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			//Tournament size 25
			int simulations14  = 85;
			for (int i = simulations13; i < simulations14; i++){
				tournamentSize = 25;
				parentSelection = new TournamentSelection(parentPopulationSize, tournamentSize);
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			//Crossover rate 0.4
			int simulations15  = 93;
			for (int i = simulations14; i < simulations15; i++){
				tournamentSize = 5;
				parentSelection = new TournamentSelection(parentPopulationSize, tournamentSize);
				crossoverRate = 0.4;
				crossover   = new UniformCrossover(crossoverRate);
				GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
				ga.run(generations,  i);
			}
			
			
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	

}
