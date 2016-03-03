package ga;


import java.util.List;


public interface AdultSelection {
	
	
	public List<Individual> select(List<Individual> childPopulation, List<Individual> adultPopulation);
	

}
