package ga;


import java.util.Random;


public class Individual implements Comparable<Individual>{

	
	private boolean[] genotype;
	private volatile double  fitness;
	
	
	public Individual(int size){
		this.genotype = new boolean[size];
		this.fitness  = Double.MIN_VALUE;
		Random random = new Random();
		for (int i = 0; i < size; i++){
			genotype[i] = random.nextBoolean();
		}
	}
	
	
	public Individual(boolean[] genotype){
		this.genotype = genotype;
		this.fitness  = Double.MIN_VALUE;
	}
	
	
	public Individual(boolean[] genotype, double fitness){
		this.genotype = genotype;
		this.fitness  = fitness;
	}
	
	
	public int size(){
		return genotype.length;
	}
	
	public void calculateFitness(){
		fitness = 0;
		for (int i = 0; i < genotype.length; i++){
			if (genotype[i]){
				fitness += 1;
			}
		}
	}
	
	
	public double getFitness(){
		return this.fitness;
	}
	
	
	public void setFitness(double fitness){
		this.fitness = fitness;
	}
	
	
	public boolean[] getGenotype(){
		return this.genotype;
	}
	
	
	public int getGeneAsInteger(int index){
		if (this.genotype[index])
			return 1;
		return 0;
	}
	
	
	public boolean getGene(int index){
		if (this.genotype[index])
			return true;
		else
			return false;
	}
	
	
	public int compareTo(Individual individual) {
		if (this.fitness > individual.fitness){
			return 1;
		}
		else if (this.fitness == individual.fitness){
			return 0;
		}
		else{
			return -1;
		}
	}
	
	
	public boolean[] copyGenotype(){
		boolean[] copy = new boolean[genotype.length];
		for (int i = 0; i < genotype.length; i++){
			if(genotype[i])
				copy[i] = true;
			else
				copy[i] = false;
		}
		return copy;
	}
	
	
	public Individual copy(){
		boolean[] newGenotype = this.copyGenotype();
		return new Individual(newGenotype, this.getFitness());
		
	}

	
}
