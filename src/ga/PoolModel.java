package ga;


import java.util.ArrayList;


public class PoolModel extends GeneticAlgorithm{

	
	private int numberOfWorkers;
	private int populationSize;
	private ArrayList<PoolWorker> workers;
	
	
	public PoolModel(WindFarmLayoutEvaluator evaluator, int populationSize,
			AdultSelection adultSelection, ParentSelection parentSelection,
			Crossover crossover, double crossoverRate, double flipMutationRate,
			double inversionMutationRate, double interchangeMutationRate,
			double reversingMutationRate, int numberOfWorkers) {
		super(evaluator, populationSize, adultSelection, parentSelection, crossover,
				crossoverRate, flipMutationRate, inversionMutationRate,
				interchangeMutationRate, reversingMutationRate);
		this.populationSize  = populationSize;
		this.numberOfWorkers = numberOfWorkers;
		this.workers = new ArrayList<PoolWorker>();
	}
	
	
	public void run(int generations, int simulation){
		int run = 0;
		int interval = numberOfWorkers / populationSize;
		while (run < generations){
			int start = 0; 
			int end   = interval;
			for (int i = 0; i < numberOfWorkers; i++){//TODO Fix indexes.
				workers.add(new PoolWorker(start, end, adultPopulation, crossover, evaluator, grid, mutation, generations));
				int oldEnd = end;
				start = end;
				end   = oldEnd + interval; 
			}
			for (int i = 0; i < numberOfWorkers; i++){
				Thread thread = new Thread(workers.get(i), "Thread" + i);
				thread.start();
			}
			generations += 1;
		}
	}
	

}
