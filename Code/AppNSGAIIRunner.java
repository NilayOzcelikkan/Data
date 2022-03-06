package org.nilay.thesis.optimization;

import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.crossover.IntegerSBXCrossover;
import org.uma.jmetal.operator.impl.mutation.IntegerPolynomialMutation;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.problem.IntegerProblem;
import org.uma.jmetal.util.AbstractAlgorithmRunner;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.ProblemUtils;
import org.uma.jmetal.solution.IntegerSolution;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;


public class AppNSGAIIRunner extends AbstractAlgorithmRunner {

	public static void main(String[] args) throws Exception {
		IntegerProblem problem;
	    Algorithm<List<IntegerSolution>> algorithm;
	    CrossoverOperator<IntegerSolution> crossover;
	    MutationOperator<IntegerSolution> mutation;
	    SelectionOperator<List<IntegerSolution>, IntegerSolution> selection;
	    
	    String problemName ;
	    String referenceParetoFront = "" ;
	    if (args.length == 1) {
	      problemName = args[0];
	    } else if (args.length == 2) {
	      problemName = args[0];
	      referenceParetoFront = args[1];
	    } else {
	      problemName = "org.nilay.thesis.optimization.AppWithSmp";
	      referenceParetoFront = "C:/Tez/Data/abc.txt" ;
	    }

	    problem = (IntegerProblem) ProblemUtils.<IntegerSolution> loadProblem(problemName);

	    double crossoverProbability = 0.9;
	    double crossoverDistributionIndex = 20.0 ;
	    crossover = new IntegerSBXCrossover(crossoverProbability, crossoverDistributionIndex) ;

	    double mutationProbability = 1.0 / problem.getNumberOfVariables() ;
	    //double mutationDistributionIndex = 1.0 ;
	    double mutationDistributionIndex = 20.0 ;
	    mutation = new IntegerPolynomialMutation(mutationProbability, mutationDistributionIndex) ;

	    selection = new BinaryTournamentSelection<IntegerSolution>() ;
	    
	    //populationSize 100'dü, MaxEvaluations 25000'di.
	    algorithm = new NSGAIIBuilder<IntegerSolution>(problem, crossover, mutation)
	            .setSelectionOperator(selection)
	            .setMaxEvaluations(15000)
	            .setPopulationSize(100)
	            .build();

	    AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm)
	            .execute() ;

	    List<IntegerSolution> population = algorithm.getResult() ;
	    long computingTime = algorithmRunner.getComputingTime() ;

	    JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");
	    
	  /*  Iterator<IntegerSolution> iter = population.iterator(); 
		System.out.println("\nThe iterator values of population are: "); 
		while (iter.hasNext()) { 
			System.out.print(iter.next() + " "); 
		}*/
	    //JMetalLogger.logger.info("Population: " + population.);

	    printFinalSolutionSet(population);
	    if (!referenceParetoFront.equals("")) {
	    	printQualityIndicators(population, referenceParetoFront) ;
	    }
	}
/*	private void createResultFile() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Tez/Data/Population.txt"));
			writer.write("RESULTS"+"\r\n");
			
			writer.close();
		}catch (IOException e){
			e.printStackTrace();
		}		
	}*/
}
