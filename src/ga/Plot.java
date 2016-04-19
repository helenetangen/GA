package ga;


import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;


import org.math.plot.Plot2DPanel;


public class Plot {
	
	
	public static final int NUMBER_OF_FILES = 1;
	public static final int GENERATIONS = 20;
	public static final int FRAME_WIDTH  = 500;
	public static final int FRAME_HEIGHT = 500;
	
	public double[] x;
	public double[] average;
	public double[] max;
	public double[] min;
	//New
	public double[] power;
	public double[] cost;
	public double[] efficiency;
	public double[] turbines;
	
	public String path = "results/testisland/";
	public String frameName = "Fitness";
	//New
	public String frameNamePower      = "Power";
	public String frameNameCost       = "Cost";
	public String frameNameEfficiency = "Efficiency";
	public String frameNameTurbines   = "Turbines";
	
	
	public static void main(String[] args){
		try {
			Plot plot = new Plot();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public Plot() throws IOException{
		x       = new double[GENERATIONS];
		average = new double[GENERATIONS];
		max     = new double[GENERATIONS];
		min     = new double[GENERATIONS];
		//New
		power      = new double[GENERATIONS];
		cost       = new double[GENERATIONS];
		efficiency = new double[GENERATIONS];
		turbines   = new double[GENERATIONS];
		
		for (int i = 0; i < GENERATIONS; i++){
			x[i] = i;
			average[i] = 0.0;
			max[i]     = 0.0;
			min[i]     = 0.0;
			//New
			power[i]      = 0.0;
			cost[i]       = 0.0;
			efficiency[i] = 0.0;
			turbines[i]   = 0.0;
		}
		
		String filenameAverage;
		String filenameMax;
		String filenameMin;
		//New
		String filePower;
		String fileCost;
		String fileEfficiency;
		String fileTurbines;
		
		for (int i = 0; i < NUMBER_OF_FILES; i++){
//			filenameAverage = path + "average" + i;
//			filenameMax     = path + "worst" + i;
//			filenameMin     = path + "best" + i;
//			//New
//			filePower      = path + "totalPower" + i;
//			fileCost       = path + "totalCost" + i;
//			fileEfficiency = path + "efficiency" + i;
//			fileTurbines   = path + "turbineNr" + i;
			
			filenameAverage = path + "average_island_0_simulation_" + i + "_round_10";
			filenameMax     = path + "worst_island_0_simulation_" + i + "_round_10";
			filenameMin     = path + "best_island_0_simulation_" + i + "_round_10";
			//New
			filePower      = path + "totalPowerisland_0_simulation_" + i + "_round_10";
			fileCost       = path + "totalCostisland_0_simulation_" + i + "_round_10";
			fileEfficiency = path + "efficiencyisland_0_simulation_" + i + "_round_10";
			fileTurbines   = path + "turbineNrisland_0_simulation_" + i + "_round_10";
			
			BufferedReader bufferedReaderAverage = new BufferedReader(new FileReader(filenameAverage));
			BufferedReader bufferedReaderMax     = new BufferedReader(new FileReader(filenameMax));
			BufferedReader bufferedReaderMin     = new BufferedReader(new FileReader(filenameMin));
			//New
			BufferedReader bufferedReaderPower      = new BufferedReader(new FileReader(filePower));
			BufferedReader bufferedReaderCost       = new BufferedReader(new FileReader(fileCost));
			BufferedReader bufferedReaderEfficiency = new BufferedReader(new FileReader(fileEfficiency));
			BufferedReader bufferedReaderTurbines   = new BufferedReader(new FileReader(fileTurbines));
			try {
			    StringBuilder stringBuilderAverage = new StringBuilder();
			    StringBuilder stringBuilderMax     = new StringBuilder();
			    StringBuilder stringBuilderMin     = new StringBuilder();
			    //New
			    StringBuilder stringBuilderPower          = new StringBuilder();
			    StringBuilder stringBuilderCost           = new StringBuilder();
			    StringBuilder stringBuilderEfficiency     = new StringBuilder();
			    StringBuilder stringBuilderTurbines       = new StringBuilder();
			    
			    String lineAverage = bufferedReaderAverage.readLine();
			    String lineMax     = bufferedReaderMax.readLine();
			    String lineMin     = bufferedReaderMin.readLine();
			    //New
			    String linePower          = bufferedReaderPower.readLine();
			    String lineCost           = bufferedReaderCost.readLine();
			    String lineEfficiency     = bufferedReaderEfficiency.readLine();
			    String lineTurbines       = bufferedReaderTurbines.readLine();

			    while (lineAverage != null) {
			        stringBuilderAverage.append(lineAverage);
			        lineAverage = bufferedReaderAverage.readLine();
			    }
			    while (lineMax != null) {
			        stringBuilderMax.append(lineMax);
			        lineMax = bufferedReaderMax.readLine();
			    }
			    while (lineMin != null) {
			        stringBuilderMin.append(lineMin);
			        lineMin = bufferedReaderMin.readLine();
			    }
			    while (linePower != null) {
			        stringBuilderPower.append(linePower);
			        linePower = bufferedReaderPower.readLine();
			    }
			    while (lineCost != null) {
			        stringBuilderCost.append(lineCost);
			        lineCost = bufferedReaderCost.readLine();
			    }
			    while (lineEfficiency != null) {
			        stringBuilderEfficiency.append(lineEfficiency);
			        lineEfficiency = bufferedReaderEfficiency.readLine();
			    }
			    while (lineTurbines != null) {
			        stringBuilderTurbines.append(lineTurbines);
			        lineTurbines = bufferedReaderTurbines.readLine();
			    }
			    
			    String stringAverage = stringBuilderAverage.toString();
			    String stringMax     = stringBuilderMax.toString();
			    String stringMin     = stringBuilderMin.toString();
			    //New
			    String stringPower      = stringBuilderPower.toString();
			    String stringCost       = stringBuilderCost.toString();
			    String stringEfficiency = stringBuilderEfficiency.toString();
			    String stringTurbines   = stringBuilderTurbines.toString();
			    
			    String[] tableAverage = stringAverage.split(",");
			    String[] tableMax     = stringMax.split(",");
			    String[] tableMin     = stringMin.split(",");
			    //New
			    String[] tablePower          = stringPower.split(",");
			    String[] tableCost           = stringCost.split(",");
			    String[] tableEfficiency     = stringEfficiency.split(",");
			    String[] tableTurbines       = stringTurbines.split(",");
			    
			    for (int k  = 0; k <GENERATIONS; k++){
			    	if (i == 0){
			    		average[k] = Double.parseDouble(tableAverage[k]);
				    	max[k]     = Double.parseDouble(tableMax[k]);
				    	min[k]     = Double.parseDouble(tableMin[k]);
				    	//New
				    	power[k]      = Double.parseDouble(tablePower[k]);
				    	cost[k]       = Double.parseDouble(tableCost[k]);
				    	efficiency[k] = Double.parseDouble(tableEfficiency[k]);
				    	turbines[k]   = Double.parseDouble(tableTurbines[k]);
			    	}
			    	else{
			    		average[k] = (average[k] + Double.parseDouble(tableAverage[k])) / 2.0;
				    	max[k]     = (max[k] + Double.parseDouble(tableMax[k])) / 2.0;
				    	min[k]     = (min[k] + Double.parseDouble(tableMin[k])) / 2.0;
				    	//New
				    	power[k]      = (power[k] + Double.parseDouble(tablePower[k])) /2.0;
				    	cost[k]       = (cost[k] + Double.parseDouble(tableCost[k])) /2.0;
				    	efficiency[k] = (efficiency[k] + Double.parseDouble(tableEfficiency[k])) /2.0;
				    	turbines[k]   = (turbines[k] + Double.parseDouble(tableTurbines[k])) /2.0;
			    	}
			    }
			} finally {
				bufferedReaderAverage.close();
				bufferedReaderMax.close();
				bufferedReaderMin.close();
				//New
				bufferedReaderPower.close();
				bufferedReaderCost.close();
				bufferedReaderEfficiency.close();
				bufferedReaderTurbines.close();
			}
			
		}
		//Fitness
		JFrame frame = new JFrame(frameName);
		frame.setLayout(new BorderLayout());
		Plot2DPanel plot = new Plot2DPanel();
		plot.addLinePlot("Average", Color.BLUE, x, average);
		plot.addLinePlot("Maximum", Color.RED, x, max);
		plot.addLinePlot("Minimum", Color.GREEN, x, min);
		frame.add(plot);
		frame.setVisible(true);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		//New
		//Power
		JFrame framePower = new JFrame(frameNamePower);
		framePower.setLayout(new BorderLayout());
		Plot2DPanel plotPower = new Plot2DPanel();
		plotPower.addLinePlot("Power", Color.BLUE, x, power);
		framePower.add(plotPower);
		framePower.setVisible(true);
		framePower.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		//Cost
		JFrame frameCost = new JFrame(frameNameCost);
		frameCost.setLayout(new BorderLayout());
		Plot2DPanel plotCost = new Plot2DPanel();
		plotCost.addLinePlot("Cost", Color.BLUE, x, cost);
		frameCost.add(plotCost);
		frameCost.setVisible(true);
		frameCost.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		//Efficiency
		JFrame frameEfficiency = new JFrame(frameNameEfficiency);
		frameEfficiency.setLayout(new BorderLayout());
		Plot2DPanel plotEfficiency = new Plot2DPanel();
		plotEfficiency.addLinePlot("Efficiency", Color.BLUE, x, efficiency);
		frameEfficiency.add(plotEfficiency);
		frameEfficiency.setVisible(true);
		frameEfficiency.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		//Turbines
		JFrame frameTurbines = new JFrame(frameNameTurbines);
		frameTurbines.setLayout(new BorderLayout());
		Plot2DPanel plotTurbines = new Plot2DPanel();
		plotTurbines.addLinePlot("Turbines", Color.BLUE, x, turbines);
		frameTurbines.add(plotTurbines);
		frameTurbines.setVisible(true);
		frameTurbines.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	

}
