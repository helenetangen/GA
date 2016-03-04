package copy;


public class FullGenerationalReplacement implements AdultSelection{


	public Population select(Population childPopulation, Population adultPopulation) {
		return childPopulation.copy();
	}

	
}
