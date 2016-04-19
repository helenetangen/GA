package ga;
 
 
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
 
public class IslandModel extends GeneticAlgorithm{
 
     
    private int demeSize;
    private int demeCount;
    private int migrationRate;
    private int migrationInterval;
    private List<GeneticAlgorithm> islands;
     
     
    public IslandModel(WindFarmLayoutEvaluator evaluator, int populationSize, AdultSelection adultSelection, ParentSelection parentSelection, Crossover crossover, double crossoverRate, double flipMutationRate,double inversionMutationRate, double interchangeMutationRate, double reversingMutationRate, int demeCount, int migrationRate, int migrationInterval) {
        super(evaluator, populationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate);
        this.demeCount = demeCount;
        this.demeSize  = populationSize / demeCount;
        this.migrationRate     = migrationRate;
        this.migrationInterval = migrationInterval;
        this.islands = new ArrayList<GeneticAlgorithm>();
        for (int i = 0; i < demeCount; i++){
            islands.add(new GeneticAlgorithm(evaluator, populationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate));
        }
    }
 
 
    public void run(int generations, int simulations){
        for (int i = 0; i < generations; i++){
            //Run Islands
        	for (int j = 0; j < islands.size(); j++){
                islands.get(j).run(migrationInterval, simulations, j, i);
            }
        	//Migrations
            for (int j = 0; j < islands.size(); j++){
                GeneticAlgorithm islandOne = islands.get(j);
                GeneticAlgorithm islandTwo = islands.get((j + 1)% islands.size());
                Collections.sort(islandOne.getChildPopulation().getPopulation());
                Collections.sort(islandTwo.getChildPopulation().getPopulation());
                for (int k = 0; k < migrationRate; k++){
                    int populationSize = islandOne.getChildPopulation().size();
                    islandTwo.getChildPopulation().set(populationSize - k - 1, islandOne.getChildPopulation().get(k).copy());
                }
            }
        }   
    }
     
     
}