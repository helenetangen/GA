package ga;


public class GeneticAlgorithm {
	
	
	private WindFarmLayoutEvaluator evaluator;
	private Population childPopulation;
	private Population adultPopulation;
	private Population parentPopulation;
	private AdultSelection adultSelection;
	private ParentSelection parentSelection;
	private Crossover crossover;
	private Mutation mutation;
	
	
	public GeneticAlgorithm(WindFarmLayoutEvaluator evaluator, int populationSize, int individualSize, AdultSelection adultSelection, ParentSelection parentSelection, Crossover crossover, double crossoverRate, double flipMutationRate, double inversionMutationRate, double interchangeMutationRate, double reversingMutationRate){
		this.evaluator = evaluator;
		this.childPopulation  = new Population(individualSize, populationSize);
		this.adultPopulation  = new Population();
		this.parentPopulation = new Population();
		this.adultSelection  = adultSelection;
		this.parentSelection = parentSelection;
		this.crossover = crossover;
		this.mutation  = new Mutation(flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
	}
	
	
	public void run(int generations){
		int run = 0;
		while (run < generations){
			
			//Calculate fitness
			childPopulation.calculateFitness();
			childPopulation.printPopulation("Child population");
			childPopulation.printAverageFitness();
			childPopulation.printBestFitness();
			
			//Adult Selection
			adultPopulation = adultSelection.select(childPopulation, adultPopulation);
			adultPopulation.printPopulation("Adult population");
			adultPopulation.printAverageFitness();
			adultPopulation.printBestFitness();
			
			//Parent Selection
			parentPopulation = parentSelection.select(adultPopulation);
			parentPopulation.printPopulation("Parent population");
			parentPopulation.printAverageFitness();
			parentPopulation.printBestFitness();
			
			//Child generations
			childPopulation = crossover.crossover(parentPopulation);
			childPopulation = mutation.flipMutation(childPopulation);
			childPopulation = mutation.inversionMutation(childPopulation);
			childPopulation = mutation.interchangeMutation(childPopulation);
			childPopulation = mutation.reversingMutation(childPopulation);
			
			//Generations
			run += 1;
		}
	}

	
}
