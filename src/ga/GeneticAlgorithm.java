package ga;


import java.util.List;
import java.util.ArrayList;


public class GeneticAlgorithm {
	
	
	private List<Individual> childPopulation;
	private List<Individual> adultPopulation;
	private List<Individual> parentPopulation;
	private AdultSelection adultSelection;
	private ParentSelection parentSelection;
	private Crossover crossover;
	private Mutation mutation;
	
	
	public GeneticAlgorithm(int populationSize, int individualSize, AdultSelection adultSelection, ParentSelection parentSelection, Crossover crossover, double crossoverRate, double flipMutationRate, double inversionMutationRate, double interchangeMutationRate, double reversingMutationRate){
		this.childPopulation  = new ArrayList<Individual>();
		this.adultPopulation  = new ArrayList<Individual>();
		this.parentPopulation = new ArrayList<Individual>();
		for (int i = 0; i < populationSize; i++){
			childPopulation.add(new Individual(individualSize));
		}
		this.adultSelection  = adultSelection;
		this.parentSelection = parentSelection;
		this.crossover = crossover;
		this.mutation  = new Mutation(flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
	}
	
	
	public void run(int generations){
		int run = 0;
		while (run < generations){
			
			//Calculate fitness
			this.calculateFitness();
			this.printPopulation(childPopulation, "Child population");
			this.printAverageFitness(childPopulation);
			this.printBestFitness(childPopulation);
			
			//Adult Selection
			this.adultPopulation = adultSelection.select(childPopulation, adultPopulation);
			this.printPopulation(adultPopulation, "Adult population");
			this.printAverageFitness(adultPopulation);
			this.printBestFitness(adultPopulation);
			
			//Parent Selection
			this.parentPopulation = parentSelection.select(adultPopulation);
			this.printPopulation(parentPopulation, "Parent population");
			this.printAverageFitness(parentPopulation);
			this.printBestFitness(parentPopulation);
			
			//Child generations
			this.childPopulation = crossover.crossover(parentPopulation);
			this.childPopulation = mutation.flipMutation(childPopulation);
			this.childPopulation = mutation.inversionMutation(childPopulation);
			this.childPopulation = mutation.interchangeMutation(childPopulation);
			this.childPopulation = mutation.reversingMutation(childPopulation);
			
			//Generations
			run += 1;
		}
	}
	
	
	public void calculateFitness(){
		for (int i = 0; i < this.childPopulation.size(); i++){
			this.childPopulation.get(i).calculateFitness();
		}
	}
	
	
	public void printPopulation(List<Individual> population, String populationName){
		System.out.println();
		System.out.println(populationName + ": ");
		for (int i = 0; i < population.size(); i++){
			System.out.print("Genotype: ");
			for (int j = 0; j < population.get(i).getGenotype().length; j++){
				System.out.print(population.get(i).getGeneAsInteger(j) + " ");
			}
			System.out.print("  Fitness: " + population.get(i).getFitness());
			System.out.println();
		}
		System.out.println();
	}
	
	
	public double calculateAverageFitness(List<Individual> population){
		double fitness = 0; 
		for (int i = 0; i < population.size(); i++){
			fitness += population.get(i).getFitness();
		}
		return fitness / population.size();
	}
	
	
	public double calculateBestFitness(List<Individual> population){
		double bestFitness = population.get(0).getFitness(); 
		for (int i = 0; i < population.size(); i++){
			if (population.get(i).getFitness() > bestFitness){
				bestFitness = population.get(i).getFitness();
			}
		}
		return bestFitness;
	}
	
	
	public void printAverageFitness(List<Individual> population){
		System.out.println("Average fitness: " + this.calculateAverageFitness(population));
	}
	
	
	public void printBestFitness(List<Individual> population){
		System.out.println("Best fitness: " + this.calculateBestFitness(population));

	}
	
	
	public static List<Individual> copy(List<Individual> population){
		List<Individual> copy = new ArrayList<Individual>();
		for (int i = 0; i < population.size(); i++){
			copy.add(new Individual(population.get(i).copyGenotype(), population.get(i).getFitness()));
		}
		return copy;
	}

	
}
