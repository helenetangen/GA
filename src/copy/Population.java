package copy;


import java.util.ArrayList;
import java.util.List;


public class Population{
	
	
	private List<Individual> population;
	
	
	public Population(){
		this.population = new ArrayList<Individual>();
	}
	
	
	public Population(List<Individual> population){
		this.population = population;
	}
	
	
	public Population(int individualSize, int populationSize){
		this.population = new ArrayList<Individual>();
		for (int i = 0; i < populationSize; i++){
			this.population.add(new Individual(individualSize));
		}
	}
	
	
	public List<Individual> getPopulation(){
		return population;
	}
	
	
	public void addAll(Population list){
		population.addAll(list.population);
	}
	
	
	public void calculateFitness(){
		for (int i = 0; i < this.population.size(); i++){
			this.population.get(i).calculateFitness();
		}
	}
	
	
	public void printPopulation(String populationName){
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
	
	
	public double calculateAverageFitness(){
		double fitness = 0.0; 
		for (int i = 0; i < population.size(); i++){
			fitness += population.get(i).getFitness();
		}
		return fitness / population.size();
	}
	
	
	public double calculateBestFitness(){
		double bestFitness = population.get(0).getFitness(); 
		for (int i = 0; i < population.size(); i++){
			if (population.get(i).getFitness() > bestFitness){
				bestFitness = population.get(i).getFitness();
			}
		}
		return bestFitness;
	}
	
	
	public void printAverageFitness(){
		System.out.println("Average fitness: " + this.calculateAverageFitness());
	}
	
	
	public void printBestFitness(){
		System.out.println("Best fitness: " + this.calculateBestFitness());

	}
	
	
	public int size(){
		return this.population.size();
	}
	
	
	public Individual get(int index){
		return this.population.get(index);
	}
	
	
	public void add(Individual individual){
		this.population.add(individual);
	}
	
	
	public Population copy(){
		Population copy = new Population();
		for (int i = 0; i < population.size(); i++){
			copy.add(new Individual(population.get(i).copyGenotype(), population.get(i).getFitness()));
		}
		return copy;
	}
	

}
