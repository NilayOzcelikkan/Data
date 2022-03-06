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

public class DenemeWithKnownPF {
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
		
		//String referenceFrontFileName = "AppWithSmp.pf";

		Experiment<IntegerSolution, List<IntegerSolution>> experiment =
				new ExperimentBuilder<IntegerSolution, List<IntegerSolution>>("Deneme2")
				.setAlgorithmList(algorithmList)
				.setProblemList(problemList)
				.setExperimentBaseDirectory(experimentBaseDirectory)
				.setOutputParetoFrontFileName("FUN")
				.setOutputParetoSetFileName("VAR")
				.setReferenceFrontDirectory(experimentBaseDirectory + "\\Deneme2\\referenceFronts")
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
		  //new GenerateReferenceParetoSetAndFrontFromDoubleSolutions(experiment).run();
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

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(15000)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt1", problemList.get(i), run));
			}
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(15000)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt2", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(15000)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt3", problemList.get(i), run));
			}


			// Her şeyi sabit tutup sadece MaxEvaluations değişirse
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(20000)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt4", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(20000)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt5", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(20000)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt6", problemList.get(i), run));
			}
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(25000)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt7", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(25000)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt8", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(25000)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt9", problemList.get(i), run));
			}

			// Her şeyi sabit tutup sadece PopulationSize değişirse

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(15000)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt10", problemList.get(i), run));
			}
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(15000)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt11", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(15000)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt12", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(20000)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt13", problemList.get(i), run));
			}
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(20000)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt14", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(20000)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt15", problemList.get(i), run));
			}
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(25000)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt16", problemList.get(i), run));
			}
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(25000)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt17", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(25000)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt18", problemList.get(i), run));
			}
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(15000)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt19", problemList.get(i), run));
			}
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(15000)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt20", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(15000)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt21", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(20000)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt22", problemList.get(i), run));
			}
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(20000)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt23", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(20000)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt24", problemList.get(i), run));
			}
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(25000)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt25", problemList.get(i), run));
			}
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(25000)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt26", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new NSGAIIBuilder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 20.0))
						.setMaxEvaluations(25000)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "NSGAIIt27", problemList.get(i), run));
			}

			//SPEA2
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(150)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t1", problemList.get(i), run));
			}
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(150)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t2", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(150)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t3", problemList.get(i), run));
			}

			// Her şeyi sabit tutup sadece MaxIterations değişirse
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(200)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t4", problemList.get(i), run));
			}
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(200)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t5", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(200)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t6", problemList.get(i), run));
			}


			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(250)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t7", problemList.get(i), run));
			}
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(250)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t8", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(250)
						.setPopulationSize(100)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t9", problemList.get(i), run));
			}


			// Her şeyi sabit tutup sadece PopulationSize değişirse

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(300)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t10", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(300)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t11", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(300)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t12", problemList.get(i), run));
			}
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(400)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t13", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(400)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t14", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(400)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t15", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(500)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t16", problemList.get(i), run));
			}
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(500)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t17", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(500)
						.setPopulationSize(50)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t18", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(75)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t19", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(75)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t20", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(75)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t21", problemList.get(i), run));
			}
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(100)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t22", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(100)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t23", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(100)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t24", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.7, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(125)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t25", problemList.get(i), run));
			}
			
			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(),
						new IntegerSBXCrossover(0.8, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(125)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t26", problemList.get(i), run));
			}

			for (int i = 0; i < problemList.size(); i++) {
				Algorithm<List<IntegerSolution>> algorithm = new SPEA2Builder<IntegerSolution>(
						problemList.get(i).getProblem(), 
						new IntegerSBXCrossover(0.9, 20.0),
						new IntegerPolynomialMutation(1.0 / problemList.get(i).getProblem().getNumberOfVariables(), 10.0))
						.setMaxIterations(125)
						.setPopulationSize(200)
						.build();
				algorithms.add(new ExperimentAlgorithm<IntegerSolution, List<IntegerSolution>>(algorithm, "SPEA2t27", problemList.get(i), run));
			}

		}
		return algorithms;
	}
}
