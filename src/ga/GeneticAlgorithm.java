package ga;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class GeneticAlgorithm {
	
	//Constants
	protected final double MINIMUM_SPACING_DISTANCE = 8.01;
	
	//Wind parameters
	protected WindFarmLayoutEvaluator evaluator;
	protected ArrayList<double[]> grid;
	
	//Ga parameters
	protected Population childPopulation;
	protected Population adultPopulation;
	protected Population parentPopulation;
	protected AdultSelection adultSelection;
	protected ParentSelection parentSelection;
	protected Crossover crossover;
	protected Mutation mutation;
	
	
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
	
	
	public void run(int generations, int simulation){
		System.out.println("Simulation: " + simulation);
		ArrayList<Double> averageFitness = new ArrayList<Double>();
		ArrayList<Double> bestFitness    = new ArrayList<Double>();
		ArrayList<Double> worstFitness   = new ArrayList<Double>();
		
		int run = 0;
		while (run < generations){
			System.out.println("Generation: " + run);
			
			//Calculate fitness
			childPopulation.evaluateParallell(evaluator, grid);
			averageFitness.add(childPopulation.calculateAverageFitness());
			bestFitness.add(childPopulation.calculateBestFitness());
			worstFitness.add(childPopulation.calculateWorstFitness());
			childPopulation.printPopulation("Child population");
			//writeResults("average", Double.toString(childPopulation.calculateAverageFitness()));
			
			System.out.print("Child population : ");
			childPopulation.printAverageFitness();
			//childPopulation.printBestFitness();
			
			//Adult Selection
			adultPopulation = adultSelection.select(childPopulation, adultPopulation);
			adultPopulation.printPopulation("Adult population");
			//System.out.print("Adult population : ");
			//adultPopulation.printAverageFitness();
			//adultPopulation.printBestFitness();
			
			//Parent Selection
			parentPopulation = parentSelection.select(adultPopulation);
			parentPopulation.printPopulation("Parent population");
			//System.out.print("Parent population: ");
			//parentPopulation.printAverageFitness();
			//parentPopulation.printBestFitness();
			
			//Child generations
			childPopulation = crossover.crossover(parentPopulation);
			childPopulation = mutation.flipMutation(childPopulation);
			childPopulation = mutation.inversionMutation(childPopulation);
			childPopulation = mutation.interchangeMutation(childPopulation);
			childPopulation = mutation.reversingMutation(childPopulation);
			childPopulation = mutation.elitism(childPopulation, parentPopulation);
			
			//Generations
			run += 1;
		}
		
		childPopulation.evaluateParallell(evaluator, grid);
		childPopulation.printAverageFitness();
		averageFitness.add(childPopulation.calculateAverageFitness());
		bestFitness.add(childPopulation.calculateBestFitness());
		worstFitness.add(childPopulation.calculateWorstFitness());
		String fileNameAverage = "average" + simulation;
		String fileNameBest    = "best" + simulation;
		String fileNameWorst   = "worst" + simulation;
		writeResults(fileNameAverage, averageFitness);
		writeResults(fileNameBest, bestFitness);
		writeResults(fileNameWorst, worstFitness);
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

	
	public Population getChildPopulation(){
		return this.childPopulation;
	}
	
	
	public void writeResults(String filename, ArrayList<Double> results){
        FileWriter output = null;
        try {
        	File file = new File(filename);
        	output = new FileWriter(file, true);
        	for (int i = 0; i < results.size(); i++){
        		output.write(Double.toString(results.get(i)) + ",");
        	}
		} catch (IOException exception) {
		  exception.printStackTrace();
		} 
        finally{
        	try{
        		output.close();
        	}catch(Exception exception){
        		exception.printStackTrace();
        	}
        	
        }
	}
	
	
}
