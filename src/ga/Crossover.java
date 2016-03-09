package ga;


public abstract class Crossover {
	
	
	protected double crossoverRate;
	
	
	public Crossover(double crossoverRate){
		this.crossoverRate = crossoverRate;
	}
	
	
	public abstract Population crossover(Population parentPopulation);
	

}
