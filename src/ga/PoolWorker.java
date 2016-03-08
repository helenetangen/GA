package ga;


import java.util.ArrayList;
import java.util.Random;


public class PoolWorker implements Runnable{
	
	
	private int start;
	private int end;
	private Population childPopulation;
	private Crossover crossover;
	private WindFarmLayoutEvaluator evaluator;
	private ArrayList<double[]> grid;
	private Mutation mutation;

	
	public PoolWorker(int start, int end, Population childPopulation, Crossover crossover, WindFarmLayoutEvaluator evaluator, ArrayList<double[]> grid, Mutation mutation){
		this.start = start;
		this.end   = end;
		this.childPopulation = childPopulation;
		this.crossover = crossover;
		this.evaluator = evaluator;
		this.grid = grid;
		this.mutation = mutation;
	}
	

	public void run() {
		Random random = new Random();
		Population parentPopulation = new Population();
		for (int i = 0; i < (end - start); i++){
			parentPopulation.add(childPopulation.get(random.nextInt(childPopulation.size())).copy());
		}
		Population newChildPopulation  = crossover.crossover(parentPopulation);
		newChildPopulation = mutation.flipMutation(newChildPopulation);
		newChildPopulation = mutation.flipMutation(newChildPopulation);
		newChildPopulation = mutation.inversionMutation(newChildPopulation);
		newChildPopulation = mutation.interchangeMutation(newChildPopulation);
		newChildPopulation = mutation.reversingMutation(newChildPopulation);
		newChildPopulation.evaluate(evaluator, grid);
		for (int i = start; i < end; i++){
			if (childPopulation.get(i).getFitness() > newChildPopulation.get(i).getFitness()){
				childPopulation.set(i, newChildPopulation.get(i).copy());
			}
		}
	}

	
}
