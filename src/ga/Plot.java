package ga;


import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;


import org.math.plot.Plot2DPanel;


public class Plot {
	
	
	public static final int NUMBER_OF_FILES = 10;
	public static final int GENERATIONS = 101;
	public static final int FRAME_WIDTH  = 500;
	public static final int FRAME_HEIGHT = 500;
	
	public double[] x;
	public double[] average;
	public double[] max;
	public double[] min;
	
	public String path = "results/adult selection/overproduction/";
	public String frameName = "Overproduction";
	
	
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
		for (int i = 0; i < GENERATIONS; i++){
			x[i] = i;
			average[i] = 0.0;
			max[i]     = 0.0;
			min[i]     = 0.0;
		}
		
		String filenameAverage;
		String filenameMax;
		String filenameMin;
		for (int i = 0; i < NUMBER_OF_FILES; i++){
			filenameAverage = path + "average" + i;
			filenameMax     = path + "worst" + i;
			filenameMin     = path + "best" + i;
			
			BufferedReader bufferedReaderAverage = new BufferedReader(new FileReader(filenameAverage));
			BufferedReader bufferedReaderMax     = new BufferedReader(new FileReader(filenameMax));
			BufferedReader bufferedReaderMin     = new BufferedReader(new FileReader(filenameMin));
			try {
			    StringBuilder stringBuilderAverage = new StringBuilder();
			    StringBuilder stringBuilderMax     = new StringBuilder();
			    StringBuilder stringBuilderMin     = new StringBuilder();
			    String lineAverage = bufferedReaderAverage.readLine();
			    String lineMax     = bufferedReaderMax.readLine();
			    String lineMin     = bufferedReaderMin.readLine();

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
			    
			    String stringAverage = stringBuilderAverage.toString();
			    String stringMax     = stringBuilderMax.toString();
			    String stringMin     = stringBuilderMin.toString();
			    String[] tableAverage = stringAverage.split(",");
			    String[] tableMax     = stringMax.split(",");
			    String[] tableMin     = stringMin.split(",");
			    for (int k  = 0; k <GENERATIONS; k++){
			    	if (i == 0){
			    		average[k] = Double.parseDouble(tableAverage[k]);
				    	max[k]     = Double.parseDouble(tableMax[k]);
				    	min[k]     = Double.parseDouble(tableMin[k]);
			    	}
			    	else{
			    		average[k] = (average[k] + Double.parseDouble(tableAverage[k])) / 2.0;
				    	max[k]     = (max[k] + Double.parseDouble(tableMax[k])) / 2.0;
				    	min[k]     = (min[k] + Double.parseDouble(tableMin[k])) / 2.0;
			    	}
			    }
			} finally {
				bufferedReaderAverage.close();
				bufferedReaderMax.close();
				bufferedReaderMin.close();
			}
			
		}
		JFrame frame = new JFrame(frameName);
		frame.setLayout(new BorderLayout());

		
		Plot2DPanel plot = new Plot2DPanel();
		plot.addLinePlot("Average", Color.BLUE, x, average);
		plot.addLinePlot("Maximum", Color.RED, x, max);
		plot.addLinePlot("Minimum", Color.GREEN, x, min);
		plot.setBounds(0, 0, 0, 100);
		frame.add(plot);

		
		frame.setVisible(true);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	

}
