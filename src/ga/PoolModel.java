package ga;


public class PoolModel extends GeneticAlgorithm{

	
	public PoolModel(WindFarmLayoutEvaluator evaluator, int populationSize,
			AdultSelection adultSelection, ParentSelection parentSelection,
			Crossover crossover, double crossoverRate, double flipMutationRate,
			double inversionMutationRate, double interchangeMutationRate,
			double reversingMutationRate) {
		super(evaluator, populationSize, adultSelection, parentSelection, crossover,
				crossoverRate, flipMutationRate, inversionMutationRate,
				interchangeMutationRate, reversingMutationRate);
	}
	
	
	public void run(int generations, int simulation){
		int run = 0;
		while (run < generations){
			
			generations += 1;
		}
	}
	

}
