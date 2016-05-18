package ga;
 
 
import java.util.ArrayList;
 
 
public class PoolModel extends GeneticAlgorithm{
 
     
    private Population resourcePool;
    private int numberOfWorkers;
    private ArrayList<Thread> workers;
 
     
    public PoolModel(WindFarmLayoutEvaluator evaluator, int populationSize,AdultSelection adultSelection, ParentSelection parentSelection,Crossover crossover, double crossoverRate, double flipMutationRate,double inversionMutationRate, double interchangeMutationRate,double reversingMutationRate, int numberOfWorkers, WindScenario scenario) {
        super(evaluator, populationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate,interchangeMutationRate, reversingMutationRate, scenario);
        this.resourcePool = childPopulation;
        System.out.println("Evaluating initial resource pool.");
        this.resourcePool.evaluateParallell(evaluator, grid, scenario);
        this.numberOfWorkers = numberOfWorkers;
    }
     
     
    public void run(int generations, int simulation){
        System.out.println("Running pool model");
         
        //Make workers
        this.workers = new ArrayList<Thread>();
        int start = 0; 
        int end   = resourcePool.size() / numberOfWorkers;
        for (int i = 0; i < this.numberOfWorkers; i++){
             
            this.workers.add(new Thread(new PoolWorker(resourcePool, start, end, generations, simulation, this.crossover, this.mutation, this.evaluator, this.grid, i, this.parentSelection, scenario)));
            int oldEnd = end;
            start = end;
            end   = oldEnd + (resourcePool.size() / numberOfWorkers);
        }
         
        //Start workers
        for (int i = 0; i < this.numberOfWorkers; i++){
            this.workers.get(i).start();
        }
         
        //Join workers
        for (int i = 0; i < this.numberOfWorkers; i++){
            try {
                this.workers.get(i).join();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
         
    }
     
 
}