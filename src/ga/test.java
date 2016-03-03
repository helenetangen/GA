package ga;


import java.util.ArrayList;
import java.util.List;


public class test {
	
	
	public static void main(String[] args){
		List<Individual> list = new ArrayList<Individual>();
		for (int i = 0; i < 5; i++){
			list.add(new Individual(10));
		}
		
		test t = new test();
		t.printPopulation(list, "list");
		
		//List<Individual> list2 = list;
		//List<Individual> list2 = new ArrayList<Individual>(list);
		//List<Individual> list2 = new ArrayList<Individual>();
//		boolean[] bol = new boolean[10];
//		for (int i = 0; i < list.size(); i++){
//			//list2.add(new Individual(list.get(i).getGenotype()));
//			bol[i] = list.get(i).getGene(index);
//		}
		List<Individual> list2 = t.copy(list);
		
		
		
		t.printPopulation(list2, "list2");
		list.get(0).getGenotype()[0] = !list.get(0).getGenotype()[0];
		t.printPopulation(list, "list");
		t.printPopulation(list2, "list2");
	}
	
	
	public List<Individual> copy(List<Individual> population){
		List<Individual> copy = new ArrayList<Individual>();
		for (int i = 0; i < population.size(); i++){
			copy.add(new Individual(population.get(i).copyGenotype()));
		}
		return copy;
	}

	
	
	public void printPopulation(List<Individual> population, String populationName){
		System.out.println();
		System.out.println(populationName + ": ");
		for (int i = 0; i < population.size(); i++){
			System.out.print("Genotype: ");
			for (int j = 0; j < population.get(i).getGenotype().length; j++){
				System.out.print(population.get(i).getGeneAsInteger(j) + " ");
			}
			System.out.print("  Fitness: " + population.get(i).getFitness());
			System.out.println();
		}
		System.out.println();
	}
	
	
}
