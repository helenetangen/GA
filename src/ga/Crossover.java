package ga;


import java.util.List;


public abstract class Crossover {
	
	
	protected double crossoverRate;
	
	
	public Crossover(double crossoverRate){
		this.crossoverRate = crossoverRate;
	}
	
	
	public abstract List<Individual> crossover(List<Individual> parentPopulation);
	

}
