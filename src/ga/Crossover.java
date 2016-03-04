package ga;


public abstract class Crossover {
	
	
	protected double crossoverRate;
	protected boolean elitism;
	
	
	public Crossover(double crossoverRate, boolean elitism){
		this.crossoverRate = crossoverRate;
		this.elitism = elitism;
	}
	
	
	public abstract Population crossover(Population parentPopulation);
	

}
