package core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JFrame;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;	
import org.eclipse.jgit.lib.Ref;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.MultiLayerPerceptron;
import org.neuroph.nnet.Perceptron;

import bin.org.neuroph.core.NeuralNetwork;
import core.Event;
import core.RepoIterator;
import core.Student;
import plm.core.lang.ProgrammingLanguage;
import plm.core.model.Game;
import plm.core.model.lesson.Exercise;
import plm.core.model.lesson.Exercise.WorldKind;
import plm.universe.World;




public class test {


	public static void main(String[] args) throws InvalidRemoteException, TransportException, GitAPIException, IOException {
		
		/*
		DataSet dataSet = new DataSet(4, 1);
		
		for(int i = 0; i < 40; i++){
		ArrayList<Double> input = new ArrayList<>();
		ArrayList<Double> desiredOutput = new ArrayList<>();
		input.add(0.0);
		input.add(0.0);
		input.add(0.0);
		input.add(0.0);
		desiredOutput.add(1.0);
		
		DataSetRow temp = new DataSetRow(input, desiredOutput);
		dataSet.addRow(temp);
		
		}
		
		int[] layerList = { 10, 10, 10 };
		MultiLayerPerceptron NN = new MultiLayerPerceptron(layerList);
	
	//	NN.learn(dataSet);
		
//		double[] input = {0.0,0.0,0.0,0.0};
//		NN.setInput(input);
//		System.out.println(NN.getOutput());
		
		// create new perceptron network 
		Perceptron neuralNetwork = new Perceptron(2, 1);
		NeuralNetwork test = new NeuralNetwork<>();
		
		// create training set 
		DataSet trainingSet = new  DataSet(2, 1); 
		// add training data to training set (logical OR function) 
		trainingSet.addRow (new DataSetRow (new double[]{0, 0}, new double[]{0})); 
		trainingSet.addRow (new DataSetRow (new double[]{0, 1}, new double[]{1})); 
		trainingSet.addRow (new DataSetRow (new double[]{1, 0}, new double[]{1})); 
		trainingSet.addRow (new DataSetRow (new double[]{1, 1}, new double[]{1})); 
		// learn the training set 
		neuralNetwork.learn(trainingSet); 
		// save the trained network into file 
		neuralNetwork.save("or_perceptron.nnet"); 
		
		
		
		


		//		LocalRepository.clone();	
		//		LocalRepository.fetch();

		RepoIterator ite = new RepoIterator();
		//ite.setCollectCode(true);
		//ite.setCollectError(true);
		ite.addCommitType(Event.Executed);
		ite.setFlagStudentByExo(true);
		ite.setCollectError(true);
		//ite.setCollectCode(true);
		//ite.addValidBranch("refs/remotes/origin/PLM195c1d47108db69f8abac9e56b1fa65a7000171a");
		//ite.addValidBranch("refs/remotes/origin/PLMb9c5556003558a5aa5b5a48239a456e8c0171f17");
		//ArrayList<Student> students = new ArrayList<Student>();




		List<Exercise> exos = new ArrayList<Exercise>();
		while(ite.hasNext()){
			Student temp = ite.next();
			if(temp != null && temp.getExos().size() > 0){

			}
			//if(temp != null)
			//students.add(temp);

		}
*/
	}
}