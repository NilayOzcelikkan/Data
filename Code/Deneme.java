package org.nilay.thesis.optimization;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.algorithm.multiobjective.spea2.SPEA2Builder;
import org.uma.jmetal.operator.impl.crossover.IntegerSBXCrossover;
import org.uma.jmetal.operator.impl.mutation.IntegerPolynomialMutation;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.qualityindicator.impl.*;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
//import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.*;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Deneme {
	private static final int INDEPENDENT_RUNS = 31;

	public static void main(String[] args) throws IOException {
		String experimentBaseDirectory;
		if (args.length == 1) {
			experimentBaseDirectory = args[0];
			//throw new JMetalException("Needed arguments: experimentBaseDirectory");
		}
		else {
			experimentBaseDirectory = "C:\\Users\\nilay\\eclipse-workspace-salt\\optimization" ;
		}
		//experimentBaseDirectory = args[0];

		List<ExperimentProblem<IntegerSolution>> problemList = new ArrayList<ExperimentProblem<IntegerSolution>>();
		problemList.add(new ExperimentProblem<IntegerSolution>(new AppWithSmp()));
		//problemList.add(new ExperimentProblem<IntegerSolution>(new AppWithSmp()));

		List<ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>> algorithmList =
				configureAlgorithmList(problemList);

		Experiment<IntegerSolution, List<IntegerSolution>> experiment =
				new ExperimentBuilder<IntegerSolution, List<IntegerSolution>>("NSGAIIStudy2")
				.setAlgorithmList(algorithmList)
				.setProblemList(problemList)
				.setExperimentBaseDirectory(experimentBaseDirectory)
				.setOutputParetoFrontFileName("FUN")
				.setOutputParetoSetFileName("VAR")
				.setReferenceFrontDirectory(experimentBaseDirectory + "\\NSGAIIStudy2\\referenceFronts")
				.setIndicatorList(Arrays.asList(
						new Epsilon<IntegerSolution>(),
						new Spread<IntegerSolution>(),
						new GenerationalDistance<IntegerSolution>(),
						new PISAHypervolume<IntegerSolution>(),
						new InvertedGenerationalDistance<IntegerSolution>(),
						new InvertedGenerationalDistancePlus<IntegerSolution>()))
				.setIndependentRuns(INDEPENDENT_RUNS)
				.setNumberOfCores(8)
				.build();

		  new ExecuteAlgorithms<IntegerSolution, List<IntegerSolution>>(experiment).run();
		  new GenerateReferenceParetoSetAndFrontFromDoubleSolutions(experiment).run();
		  new ComputeQualityIndicators<IntegerSolution, List<IntegerSolution>>(experiment).run();
		  new GenerateLatexTablesWithStatistics(experiment).run();
		  new GenerateWilcoxonTestTablesWithR<List<IntegerSolution>>(experiment).run();
	      new GenerateFriedmanTestTables<List<IntegerSolution>>(experiment).run();
		  new GenerateBoxplotsWithR<List<IntegerSolution>>(experiment).setRows(3).setColumns(3).run();
	}

	/**
	 * The algorithm list is composed of pairs {@link Algorithm} + {@link Problem} which form part of
	 * a {@link ExperimentAlgorithm}, which is a decorator for class {@link Algorithm}. The {@link
	 * ExperimentAlgorithm} has an optional tag component, that can be set as it is shown in this
	 * example, where four variants of a same algorithm are defined.
	 */
	static List<ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>> configureAlgorithmList(
			List<ExperimentProblem<IntegerSolution>> problemList) {
		List<ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>> algorithms = new ArrayList<ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>>();
		for (int run = 0; run < INDEPENDENT_RUNS; run++) {
			//NSGA-II
			// Her şeyi sabit tutup sadece Crossover probability değişirse

	/*		for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(15000)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIItb1", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(15000)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIItb2", problemList.get(i), run));
			}

			// Her şeyi sabit tutup sadece MaxEvaluations değişirse

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(25000)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIItb3", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(25000)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIItb4", problemList.get(i), run));
			}
			

			// Her şeyi sabit tutup sadece PopulationSize değişirse

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(15000)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIItb5", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(15000)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIItb6", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(25000)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIItb7", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(25000)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIItb8", problemList.get(i), run));
			}
*/
			//SPEA2
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(150)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2tb1", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(150)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2tb2", problemList.get(i), run));
			}

			// Her şeyi sabit tutup sadece MaxEvaluations değişirse

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(250)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2tb3", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(250)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2tb4", problemList.get(i), run));
			}

			// Her şeyi sabit tutup sadece PopulationSize değişirse

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(75)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2tb5", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(75)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2tb6", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(125)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2tb7", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(125)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2tb8", problemList.get(i), run));
			}
		}
		return algorithms;
	}
}
