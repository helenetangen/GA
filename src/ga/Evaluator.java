package ga;


import java.util.ArrayList;


public class Evaluator implements Runnable{
	
	
	private KusiakLayoutEvaluator evaluator;
	private ArrayList<double[]> grid;
	private Population population;
	private int start; //inclusive
	private int end;   //exclusive

	
	public Evaluator(int start, int end, ArrayList<double[]> grid, Population population, WindScenario scenario){
		WindScenario windScenario;
		try {
			windScenario = scenario;
			this.evaluator = new KusiakLayoutEvaluator();
			this.evaluator.initialize(windScenario);
		} catch (Exception e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		this.population = population;
		this.grid       = grid;
		this.start      = start;
		this.end        = end;
	}
	
	
	public void run() {
		for (int p = start; p < end; p++){
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

	
}
