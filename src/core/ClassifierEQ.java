package core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.util.TransferFunctionType;

public class ClassifierEQ {
	
	private double EQThreshold;
	private int predictAtNCommit = 4;
	private HashMap<Double, Integer> resultEQSuccess = new HashMap<Double,Integer>();
	private HashMap<Double, Integer> resultEQFail = new HashMap<Double, Integer>();
	public void learn(List<Exercise> exos){
		for(Exercise exo : exos){
			int firstSuccess = exo.firstCommitSuccessful(); 
			for(int i = 0 ; i < exo.getSize() - predictAtNCommit; i ++){
				double eq = CurrentEQ(exo, i);
				if(i + predictAtNCommit < firstSuccess)
					if(resultEQFail.get(eq) != null)
						resultEQFail.putIfAbsent(eq, 1);
					else
						resultEQFail.put(eq, resultEQFail.get(eq));

				else
					if(resultEQSuccess.get(eq) != null)
						resultEQSuccess.putIfAbsent(eq, 1);
					else
						resultEQSuccess.put(eq, resultEQSuccess.get(eq));
			}			
		}
		
		
		
		
	}
	
	public boolean predict (Exercise exo, int currentCommit){
		if(CurrentEQ(exo, currentCommit) > EQThreshold)
			return false;
		
		return true;
	}
	
	public static double CurrentEQ(Exercise exo, int currentCommit){
		exo.sort();

		String prevResult = null, prevError = null;
		int nbreEQ = 0, currentEQ;
		double sumEQ = 0;
		if(currentCommit > exo.getSize())
			return -2;

		for(int i = 0 ; i < currentCommit ; i++){
			Event e = exo.getEvent(i);
			if(e.getCommitType().equals(Event.Executed)){
				if(prevResult == null){
					prevResult = e.getResultCompil();
					prevError = e.getError();
					continue;
				}

				currentEQ = 0;
				if(prevResult.equals(Event.CompilError) && e.getResultCompil().equals(Event.CompilError)){
					currentEQ += 8;
					if(prevError.equals(e.getError()))
						currentEQ += 3;
				}
				sumEQ += currentEQ/11;
				nbreEQ ++;
				prevResult = e.getResultCompil();
				prevError = e.getError();
			}
		}

		if(nbreEQ > 0)
			return sumEQ/nbreEQ;
		else
			return -1;
	}


	public static void main(String[] args) {


	    // create training set (logical XOR function)
	    DataSet trainingSet = new DataSet(2, 1);
	    trainingSet.addRow(new DataSetRow(new double[]{0, 0}, new double[]{0}));
	    trainingSet.addRow(new DataSetRow(new double[]{0, 1}, new double[]{1}));
	    trainingSet.addRow(new DataSetRow(new double[]{1, 0}, new double[]{1}));
	    trainingSet.addRow(new DataSetRow(new double[]{1, 1}, new double[]{0}));

	    // create multi layer perceptron
	    MultiLayerPerceptron myMlPerceptron = new MultiLayerPerceptron(TransferFunctionType.TANH, 2, 3, 1);
	    // learn the training set
	    myMlPerceptron.learn(trainingSet);

	    // test perceptron
	    System.out.println("Testing trained neural network");
	    testNeuralNetwork(myMlPerceptron, trainingSet);

	    // save trained neural network
	    myMlPerceptron.save("myMlPerceptron.nnet");

	    // load saved neural network
	    NeuralNetwork loadedMlPerceptron = NeuralNetwork.createFromFile("myMlPerceptron.nnet");

	    // test loaded neural network
	    System.out.println("Testing loaded neural network");
	    testNeuralNetwork(loadedMlPerceptron, trainingSet);

	}

	public static void testNeuralNetwork(NeuralNetwork nnet, DataSet testSet) {

	    for(DataSetRow dataRow : testSet.getRows()) {

	        nnet.setInput(dataRow.getInput());
	        nnet.calculate();
	        double[ ] networkOutput = nnet.getOutput();
	        System.out.print("Input: " + Arrays.toString(dataRow.getInput()) );
	        System.out.println(" Output: " + Arrays.toString(networkOutput) ); 

	    }

	}
}
	
	

}
