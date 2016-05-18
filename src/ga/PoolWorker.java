package ga;
 
 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
 
 
public class PoolWorker implements Runnable{
     
     
    private Population resourcePool;
    private int start;
    private int end;
    private int generations;
    private int simulation;
    private int segmentSize;
    private Crossover crossover;
    private Mutation mutation;
    private WindFarmLayoutEvaluator evaluator;
    private ArrayList<double[]> grid;
    private int workerId;
    private ParentSelection parentSelection;
    private WindScenario scenario;
         
     
    public PoolWorker(Population resourcePool, int start, int end, int generations, int simulation, Crossover crossover, Mutation mutation, WindFarmLayoutEvaluator evaluator, List<double[]> grid, int workerId, ParentSelection parentSelection, WindScenario scenario){
        this.resourcePool    = resourcePool;
        this.start           = start;
        this.end             = end;
        this.generations     = generations;
        this.simulation      = simulation;
        this.segmentSize     = end - start;
        this.crossover       = crossover;
        this.mutation        = mutation;
        this.evaluator       = evaluator;
        this.workerId        = workerId;
        this.parentSelection = parentSelection;
        this.scenario        = scenario;
        this.grid            = new ArrayList<double[]>();
        for (int i = 0; i < grid.size(); i++){
            this.grid.add(grid.get(i));
        }
    }
 
     
    public void run() {
        Random random = new Random();
        ArrayList<Double> averageFitness   = new ArrayList<Double>();
        ArrayList<Double> bestFitness      = new ArrayList<Double>();
        ArrayList<Double> worstFitness     = new ArrayList<Double>();
        ArrayList<double[][]> bestLayouts  = new ArrayList<double[][]>();;
         
        for (int i = 0; i < generations; i++){
             
            //Printing who's running:
            System.out.println("Worker " + this.workerId + " is running, generation: " + i);
             
//          // Pick segmentSize random individuals from the resource pool.
//          System.out.println("Worker " + this.workerId + " is picking random parents.");
//          Population parentPopulation = new Population();
//          for (int j = 0; j < this.segmentSize; j++){
//              int position = start + random.nextInt(segmentSize);
//              parentPopulation.add(resourcePool.get(position).copy());
//          }
             
             
            System.out.println("Worker " + this.workerId + " is doing parent selection.");
            Population parentPopulation = this.parentSelection.select(resourcePool);
             
             
            // Do genetic operations.
            System.out.println("Worker " + this.workerId + " is doing genetic operations.");
            Population childPopulation = crossover.crossover(parentPopulation);
            childPopulation = mutation.flipMutation(childPopulation);
            childPopulation = mutation.inversionMutation(childPopulation);
            childPopulation = mutation.interchangeMutation(childPopulation);
            childPopulation = mutation.elitism(childPopulation, parentPopulation);
 
            //Evaluate child population 
            System.out.println("Worker " + this.workerId + " is evaluating.");
            childPopulation.evaluateParallell(evaluator, grid, scenario);
             
            //Collect results
            averageFitness.add(childPopulation.calculateAverageFitness());
            bestFitness.add(childPopulation.calculateBestFitness());
            worstFitness.add(childPopulation.calculateWorstFitness());
            bestLayouts.add(childPopulation.getBestLayout(grid));
             
            System.out.println("Worker " + this.workerId + "  average fitness: " + resourcePool.calculateAverageFitness());
             
            // Write back if better.
            System.out.println("Worker " + this.workerId + " is writing back.");
            for (int j = 0; j < childPopulation.size(); j++){
                if (childPopulation.get(j).getFitness() < resourcePool.get(start + j).getFitness()){
                    System.out.println("Worker " + this.workerId + " replaces " + resourcePool.get(start + j).getFitness() + " with "+ childPopulation.get(j).getFitness());
                    resourcePool.set(start + j, childPopulation.get(j).copy());
                     
                }
            }
        }
        String fileNameAverage = "worker" + this.workerId + "average" + simulation;
        String fileNameBest    = "worker" + this.workerId + "best" + simulation;
        String fileNameWorst   = "worker" + this.workerId + "worst" + simulation;
        String fileNameLayouts = "" + simulation;
        writeResults(fileNameAverage, averageFitness);
        writeResults(fileNameBest, bestFitness);
        writeResults(fileNameWorst, worstFitness);
        writeMoreResults(fileNameLayouts, bestLayouts, evaluator);
    }
     
     
    public void writeResults(String filename, ArrayList<Double> results){
        FileWriter output = null;
        try {
            File file = new File(filename);
            output = new FileWriter(file, true);
            for (int i = 0; i < results.size(); i++){
                output.write(Double.toString(results.get(i)) + ",");
            }
        } catch (IOException exception) {
          exception.printStackTrace();
        } 
        finally{
            try{
                output.close();
            }catch(Exception exception){
                exception.printStackTrace();
            }
             
        }
    }
     
     
 
    public void writeMoreResults(String filename, ArrayList<double[][]> layouts, WindFarmLayoutEvaluator evaluator){
         
         
        ArrayList<Double> totalPowerList  = new ArrayList<Double>();
        ArrayList<Double> efficiencyList  = new ArrayList<Double>();
        ArrayList<Double> turbineNrList   = new ArrayList<Double>();
        ArrayList<Double> totalCostList   = new ArrayList<Double>();
         
        for (int i = 0; i < layouts.size(); i++){
            double[] results = evaluator.evaluateEverything(layouts.get(i));
            totalPowerList.add(results[0]);
            efficiencyList.add(results[1]);
            turbineNrList.add(results[2]);
            totalCostList.add(results[3]);
        }
         
        String totalPower = "worker" + this.workerId + "totalPower" + filename;
        String efficiency = "worker" + this.workerId + "efficiency" + filename;
        String turbineNr  = "worker" + this.workerId + "turbineNr"  + filename;
        String totalCost  = "worker" + this.workerId + "totalCost"  + filename;
         
        FileWriter fwTotalPower = null;
        FileWriter fwEfficiency = null;
        FileWriter fwTurbineNr  = null;
        FileWriter fwTotalCost  = null;
        try {
            File fileTotalPower = new File(totalPower);
            File fileEfficiency = new File(efficiency);
            File fileTurbineNr  = new File(turbineNr);
            File fileTotalCost  = new File(totalCost);
             
         
            fwTotalPower = new FileWriter(fileTotalPower, true);
            fwEfficiency = new FileWriter(fileEfficiency, true);
            fwTurbineNr  = new FileWriter(fileTurbineNr, true);
            fwTotalCost  = new FileWriter(fileTotalCost, true);
             
            for (int i = 0; i < totalPowerList.size(); i++){
                fwTotalPower.write(Double.toString(totalPowerList.get(i)) + ",");
                fwEfficiency.write(Double.toString(efficiencyList.get(i)) + ",");
                fwTurbineNr.write(Double.toString(turbineNrList.get(i)) + ",");
                fwTotalCost.write(Double.toString(totalCostList.get(i)) + ",");
            }
        } catch (IOException exception) {
          exception.printStackTrace();
        } 
        finally{
            try{
                fwTotalPower.close();
                fwEfficiency.close();
                fwTurbineNr.close();
                fwTotalCost.close();
            }catch(Exception exception){
                exception.printStackTrace();
            }
             
        }
    }
 
}