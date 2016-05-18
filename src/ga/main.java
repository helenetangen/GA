package ga;
 
 
public class main {
     
     
    public static void main(String[] args){
        try{
            //Wind scenario
            WindScenario windScenario = new WindScenario("Scenarios/00.xml");
             
            //Evaluator
            KusiakLayoutEvaluator evaluator = new KusiakLayoutEvaluator();
            evaluator.initialize(windScenario);
             
            //Parameters
            int adultPopulationSize  = 100;
            int parentPopulationSize = 200;
            int childPopulationSize  = 200;
            int generations = 200;
             
            //Crossover
            double crossoverRate = 0.9;
            boolean elitism      = true;
             
            //Mutation
            double flipMutationRate        = 0.001;
            double inversionMutationRate   = 0.000001;
            double interchangeMutationRate = 0.000001;
             
            double reversingMutationRate   = 0.0;
             
            //Adult selection:
            //AdultSelection adultSelection = new FullGenerationalReplacement();
            AdultSelection adultSelection = new OverProduction(adultPopulationSize);
            //AdultSelection adultSelection = new GenerationalMixing(adultPopulationSize);
             
            //Parent selection
            int tournamentSize = 20;    
            double epsilon = 0.1;
            ParentSelection parentSelection = new TournamentSelection(parentPopulationSize, tournamentSize, epsilon);
            //ParentSelection parentSelection = new RouletteWheel(parentPopulationSize);
             
            //Crossover method
            Crossover crossover = new SinglePointCrossover(crossoverRate);
            //Crossover crossover = new TwoPointCrossover(crossoverRate);
            //Crossover crossover   = new UniformCrossover(crossoverRate);
             
  
            
            
            //Island Model Parameters
            windScenario            = new WindScenario("Scenarios/00.xml");
            evaluator               = new KusiakLayoutEvaluator();
            evaluator.initialize(windScenario);
            childPopulationSize     = 52;
            adultPopulationSize     = 26;
            parentPopulationSize    = 52;
            generations             = 10;
            crossoverRate           = 0.9;
            crossover               = new SinglePointCrossover(crossoverRate);
            flipMutationRate        = 0.001;
            inversionMutationRate   = 0.000001;
            interchangeMutationRate = 0.000001;
            adultSelection          = new OverProduction(adultPopulationSize);
            tournamentSize          = 5;    
            epsilon                 = 0.1;
            parentSelection         = new TournamentSelection(parentPopulationSize, tournamentSize, epsilon);
            int demeCount           = 4;
            int migrationRate       = 2;
            int migrationInterval   = 20;
            for (int i = 0; i < 5; i++){
            	IslandModel iga = new IslandModel(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate, demeCount, migrationRate, migrationInterval, windScenario);
            	iga.run(generations, i);
            }
            
            
            //Master Slave Model Parameters
            windScenario            = new WindScenario("Scenarios/00.xml");
            evaluator               = new KusiakLayoutEvaluator();
            evaluator.initialize(windScenario);
            childPopulationSize     = 200;
            adultPopulationSize     = 100;
            parentPopulationSize    = 200;
            generations             = 200;
            crossoverRate           = 0.9;
            crossover               = new SinglePointCrossover(crossoverRate);
            flipMutationRate        = 0.001;
            inversionMutationRate   = 0.000001;
            interchangeMutationRate = 0.000001;
            adultSelection          = new OverProduction(adultPopulationSize);
            tournamentSize          = 20;    
            epsilon                 = 0.1;
            parentSelection         = new TournamentSelection(parentPopulationSize, tournamentSize, epsilon);
            for(int i = 5; i < 10; i++){
            	GeneticAlgorithm ga = new GeneticAlgorithm(evaluator, childPopulationSize, adultSelection, parentSelection, crossover, crossoverRate, flipMutationRate, inversionMutationRate, interchangeMutationRate, reversingMutationRate, windScenario);
            	ga.run(generations, i);
            }
            
            
            //Cellular Model Parameters
            windScenario            = new WindScenario("Scenarios/00.xml");
            evaluator               = new KusiakLayoutEvaluator();
            evaluator.initialize(windScenario);
            childPopulationSize     = 225;
            adultPopulationSize     = 225;
            parentPopulationSize    = 225;
            generations             = 200;
            crossoverRate           = 0.9;
            crossover               = new SinglePointCrossover(crossoverRate);
            flipMutationRate        = 0.001;
            inversionMutationRate   = 0.000001;
            interchangeMutationRate = 0.000001;
            adultSelection          = new OverProduction(adultPopulationSize);
            //Hard coded tournament selection with tournament size 2.
            //tournamentSize          = 20;    
            //epsilon                 = 0.1;
            //parentSelection         = new TournamentSelection(parentPopulationSize, tournamentSize, epsilon);
 
             


             
             
        }catch(Exception exception){
            exception.printStackTrace();
        }
    }
     
 
}