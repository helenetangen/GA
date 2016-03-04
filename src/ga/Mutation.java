package ga;


import java.util.Random;


public class Mutation {
	
	
	private double flipMutationRate;
	private double inversionMutationRate;
	private double interchangeMutationRate;
	private double reversingMutationRate;
	
	
	public Mutation(double flipMutationRate, double inversionMutationRate, double interchangeMutationRate, double reversingMutationRate){
		this.flipMutationRate          = flipMutationRate;
		this.inversionMutationRate     = inversionMutationRate;
		this.interchangeMutationRate   = interchangeMutationRate;
		this.reversingMutationRate     = reversingMutationRate;
	}
	
	
	public Population flipMutation(Population childPopulation){
		Random random = new Random();
		for (int i = 0; i < childPopulation.size(); i++){
			for (int j = 0; j < childPopulation.get(0).getGenotype().length; j++){
				if (random.nextDouble() < flipMutationRate){
					childPopulation.get(i).getGenotype()[j] = !childPopulation.get(i).getGenotype()[j];
				}
			}
		}
		return childPopulation;
	}
	
	
	public Population interchangeMutation(Population childPopulation){
		Random random = new Random();
		for (int i = 0; i < childPopulation.size(); i++){
			if (random.nextDouble() < this.interchangeMutationRate){
				int positionOne = random.nextInt(childPopulation.get(0).getGenotype().length);
				int positionTwo = random.nextInt(childPopulation.get(0).getGenotype().length);
				boolean temporary = childPopulation.get(i).getGenotype()[positionOne];
				childPopulation.get(i).getGenotype()[positionOne] = childPopulation.get(i).getGenotype()[positionTwo];
				childPopulation.get(i).getGenotype()[positionTwo] = temporary;
			}
		}
		
		return childPopulation;
	}
	
	
	public Population inversionMutation(Population childPopulation){
		Random random = new Random();
		for (int i = 0; i < childPopulation.size(); i++){
			if (random.nextDouble() < inversionMutationRate){
				System.out.println("Child " + i + " is inversed.");
				int positionOne = random.nextInt(childPopulation.get(0).getGenotype().length);
				int positionTwo = random.nextInt(childPopulation.get(0).getGenotype().length);
				boolean temporary = childPopulation.get(i).getGene(positionOne);
				childPopulation.get(i).getGenotype()[positionOne] = childPopulation.get(i).getGenotype()[positionTwo];
				childPopulation.get(i).getGenotype()[positionTwo] = temporary;
			}
		}
		return childPopulation;
	}
	
	
	public Population reversingMutation(Population childPopulation){
		Random random = new Random();
		for (int i = 0; i < childPopulation.size(); i++){
			if (random.nextDouble() < reversingMutationRate){
				int position = random.nextInt(childPopulation.get(0).getGenotype().length);
				System.out.println("Child " + i + " reverse from position " + position);
				for (int j = position; j < childPopulation.get(0).getGenotype().length; j++){
					childPopulation.get(i).getGenotype()[j] = !childPopulation.get(i).getGenotype()[j];
				}
			}
		}
		return childPopulation;
	}
	

}
