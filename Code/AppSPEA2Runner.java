package org.nilay.thesis.optimization;

import java.io.FileNotFoundException;
import java.util.List;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2Builder;
import org.uma.jmetal.operator.CrossoverOperator;
import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.operator.SelectionOperator;
import org.uma.jmetal.operator.impl.crossover.IntegerSBXCrossover;
import org.uma.jmetal.operator.impl.mutation.IntegerPolynomialMutation;
import org.uma.jmetal.operator.impl.selection.BinaryTournamentSelection;
import org.uma.jmetal.problem.IntegerProblem;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.AbstractAlgorithmRunner;
import org.uma.jmetal.util.AlgorithmRunner;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.JMetalLogger;
import org.uma.jmetal.util.ProblemUtils;

public class AppSPEA2Runner extends AbstractAlgorithmRunner{
	public static void main(String[] args) throws JMetalException, FileNotFoundException {
		IntegerProblem problem;
	    Algorithm<List<IntegerSolution>> algorithm;
	    CrossoverOperator<IntegerSolution> crossover;
	    MutationOperator<IntegerSolution> mutation;
	    SelectionOperator<List<IntegerSolution>, IntegerSolution> selection;

	    String referenceParetoFront = "" ;

	    String problemName ;
	    if (args.length == 1) {
	      problemName = args[0];
	    } else if (args.length == 2) {
	      problemName = args[0] ;
	      referenceParetoFront = args[1] ;
	    } else {
	      problemName = "org.nilay.thesis.optimization.AppWithSmp";
	      referenceParetoFront = "C:/Tez/Data/abc.txt" ;
	      //referenceParetoFront = "" ;
	    }

	    problem = (IntegerProblem)ProblemUtils.<IntegerSolution> loadProblem(problemName);

	    double crossoverProbability = 0.9 ;
	    double crossoverDistributionIndex = 20.0 ;
	    crossover = new IntegerSBXCrossover(crossoverProbability, crossoverDistributionIndex) ;

	    double mutationProbability = 1.0 / problem.getNumberOfVariables() ;
	    //double mutationProbability = 1.0 / problem.getNumberOfVariables() ;
	    //double mutationDistributionIndex = 1.0 ;
	    double mutationDistributionIndex = 20.0 ;
	    mutation = new IntegerPolynomialMutation(mutationProbability, mutationDistributionIndex) ;
	    
	    selection = new BinaryTournamentSelection<IntegerSolution>() ;
	    
	    algorithm = new SPEA2Builder<IntegerSolution>(problem, crossover, mutation)
	        .setSelectionOperator(selection)
	        .setMaxIterations(15000)
	        .setPopulationSize(200)
	        .build() ;

	    AlgorithmRunner algorithmRunner = new AlgorithmRunner.Executor(algorithm).execute() ;

	    List<IntegerSolution> population = algorithm.getResult();
	    long computingTime = algorithmRunner.getComputingTime() ;

	    JMetalLogger.logger.info("Total execution time: " + computingTime + "ms");

	    printFinalSolutionSet(population);
	    if (!referenceParetoFront.equals("")) {
	      printQualityIndicators(population, referenceParetoFront) ;
	    }
	  }

}
