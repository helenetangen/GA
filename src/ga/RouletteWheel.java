package ga;


import java.util.Random;


public class RouletteWheel implements ParentSelection{

	
	private int parentPopulationSize;
	
	
	public RouletteWheel(int parentPopulationSize){
		this.parentPopulationSize = parentPopulationSize;
	}
	
	
	public Population select(Population adultPopulation) {
		double[] scaledFitness = new double[adultPopulation.size()];
		double sum = 0;
		for (int i = 0; i < adultPopulation.size(); i++){
			sum += adultPopulation.get(i).getFitness();
		}
		for (int i = 0; i < adultPopulation.size(); i++){
			scaledFitness[i] = adultPopulation.get(i).getFitness() / sum;
		}
		
		Random random = new Random();
		Population childPopulation = new Population();
		for (int i = 0; i < parentPopulationSize; i++){
			double number = random.nextDouble();
			for (int j = 0; j < scaledFitness.length; j++){
				if (number < scaledFitness[j]){
					childPopulation.add(adultPopulation.get(j).copy());
				}
			}
		}
		return null;
	}

	
}
