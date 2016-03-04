package ga;

import java.util.ArrayList;

public class GeneticAlgorithm {
	
	//Constants
	private final double MINIMUM_SPACING_DISTANCE = 8.01;
	
	//Wind parameters
	private WindFarmLayoutEvaluator evaluator;
	private ArrayList<double[]> grid;
	
	//Ga parameters
	private Population childPopulation;
	private Population adultPopulation;
	private Population parentPopulation;
	private AdultSelection adultSelection;
	private ParentSelection parentSelection;
	private Crossover crossover;
	private Mutation mutation;
	
	
	public GeneticAlgorithm(WindFarmLayoutEvaluator evaluator, int populationSize, AdultSelection adultSelection, ParentSelection parentSelection, Crossover crossover, double crossoverRate, double flipMutationRate, double inversionMutationRate, double interchangeMutationRate, double reversingMutationRate){
		this.evaluator = evaluator;
		this.grid = new ArrayList<double[]>();
		this.makeGrid();
		
		//GA parameters
		this.childPopulation  = new Population(grid.size(), populationSize);
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
			childPopulation.evaluate(evaluator, grid);
			//childPopulation.printPopulation("Child population");
			
			System.out.print("Child population : ");
			childPopulation.printAverageFitness();
			//childPopulation.printBestFitness();
			
			//Adult Selection
			adultPopulation = adultSelection.select(childPopulation, adultPopulation);
			//adultPopulation.printPopulation("Adult population");
			System.out.print("Adult population : ");
			adultPopulation.printAverageFitness();
			//adultPopulation.printBestFitness();
			
			//Parent Selection
			parentPopulation = parentSelection.select(adultPopulation);
			//parentPopulation.printPopulation("Parent population");
			System.out.print("Parent population: ");
			parentPopulation.printAverageFitness();
			//parentPopulation.printBestFitness();
			
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
	
	
	public void makeGrid(){
		double interval = this.MINIMUM_SPACING_DISTANCE * evaluator.getTurbineRadius();
		for (double x = 0.0; x < evaluator.getFarmWidth(); x += interval){
			for (double y = 0.0; y < evaluator.getFarmHeight(); y += interval){
				boolean valid = true;
				for (int o = 0; o < evaluator.getObstacles().length; o++){
					double[] obstacle = evaluator.getObstacles()[o];
					if (x > obstacle[0] && y > obstacle[1] && x < obstacle[2] && y < obstacle[3]){
						valid = false;
						break;
					}
				}
				if (valid){
					double[] point = {x, y};
					grid.add(point);
				}
			}
		}
	}

	
}
