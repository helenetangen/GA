package copy;


public class run {
	
	
	public static void main(String[] args){
		try{
			WindScenario WindScenario = new WindScenario("../Scenarios/00.xml");
			KusiakLayoutEvaluator evaluator = new KusiakLayoutEvaluator();
			
		}catch(Exception exception){
			exception.printStackTrace();
		}
	}
	

}
