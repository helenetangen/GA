package ga;


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
			if (population.get(i).getFitness() < bestFitness){
				bestFitness = population.get(i).getFitness();
			}
		}
		return bestFitness;
	}
	
	
	public double calculateWorstFitness(){
		double worstFitness = population.get(0).getFitness(); 
		for (int i = 0; i < population.size(); i++){
			if (population.get(i).getFitness() > worstFitness){
				worstFitness = population.get(i).getFitness();
			}
		}
		return worstFitness;
	}
	
	
	public void printAverageFitness(){
		System.out.println("Average fitness: " + this.calculateAverageFitness());
	}
	
	
	public void printBestFitness(){
		System.out.println("Best fitness: " + this.calculateBestFitness());

	}
	
	
	public void printWorstFitness(){
		System.out.println("Best fitness: " + this.calculateWorstFitness());

	}
	
	
	public int size(){
		return this.population.size();
	}
	
	
	public Individual get(int index){
		return this.population.get(index);
	}
	
	
	public void set(int index, Individual individual){
		this.population.set(index, individual);
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
	
	
	public void evaluate(WindFarmLayoutEvaluator evaluator, ArrayList<double[]> grid){
		for (int p = 0; p < population.size(); p++){
			int turbineCount = 0;
			for (int i = 0; i < population.get(0).size(); i++){
				if (population.get(p).getGene(i)){
					turbineCount++;
				}
			}
			
			double[][] layout = new double[turbineCount][2];
			int j = 0;
			for (int i = 0; i < grid.size(); i++){
				if (population.get(p).getGene(i)){
					layout[j][0] = grid.get(i)[0];
					layout[j][1] = grid.get(i)[1];
					j++;
				}
			}
			
			double energyCost;
			if (evaluator.checkConstraint(layout)){
				evaluator.evaluate(layout);
				energyCost = evaluator.getEnergyCost();
			}
			else{
				energyCost = Double.MAX_VALUE;
			}
			population.get(p).setFitness(energyCost);
		}
	}
	
	
	public void evaluateParallell(WindFarmLayoutEvaluator evaluator, ArrayList<double[]> grid){
		int start = 0;
		int end   = 0;
		int cores   = Runtime.getRuntime().availableProcessors();
		System.out.println("Number of cores: " + cores);
		int interval = population.size() / cores; // = 3
		Thread[] threads = new Thread[cores];
		int index = 0;
		for (int i = 0; i < cores; i++) {
			start = end;
			end   = end + interval;
			if (end > population.size()){
				end = population.size();
			}
			System.out.println("Start: " + start);
			System.out.println("End  : " + end);
			System.out.println("Making new thread");
			threads[i] = new Thread(new Evaluator(start, end, grid, this));
		}
		for (int i = 0; i < cores; i++){
			threads[i].start();
		}
		for (int i = 0; i < cores; i++){
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
