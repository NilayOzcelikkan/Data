package org.nilay.thesis.optimization;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractIntegerProblem;
import org.uma.jmetal.problem.ConstrainedProblem;
import org.uma.jmetal.solution.IntegerSolution;
import org.uma.jmetal.util.solutionattribute.impl.NumberOfViolatedConstraints;
import org.uma.jmetal.util.solutionattribute.impl.OverallConstraintViolation;

@SuppressWarnings("serial")
public class App extends AbstractIntegerProblem implements ConstrainedProblem<IntegerSolution>
{
	public OverallConstraintViolation<IntegerSolution> overallConstraintViolationDegree;
	public NumberOfViolatedConstraints<IntegerSolution> numberOfViolatedConstraints;
	public int numOfUserStories;
	public int numOfSprints;
	
	ArrayList<Integer> priority;
	ArrayList<Integer> complexity;
	ArrayList<Integer> capacity;
	ArrayList<ArrayList<Integer>> orSet;
	ArrayList<ArrayList<Integer>> affinitySet;
	ArrayList<ArrayList<Integer>> alternativeSet;
	ArrayList<ArrayList<Integer>> andSet;
	
	int bigM = 999999999;

	public App() {
		
		DataGatherer dataGatherer = new DataGatherer();

		//Her bir user story'nin 1 priority değeri vardır.
		priority = dataGatherer.getPriorities();
		complexity = dataGatherer.getComplexities();
		capacity = dataGatherer.getCapacities();
		orSet = dataGatherer.getORSet();
		affinitySet = dataGatherer.getAffinitySetInt();
		alternativeSet = dataGatherer.getAlternativeSet();
		andSet = dataGatherer.getANDSet();
		
		numOfUserStories = priority.size();
		numOfSprints = capacity.size();
    	
        printPriority();
        printComplexity();
        printCapacity();
        printORSet();
        printAffinity();
        printAlternative();
        printANDSet();
        
        int lowerBoundForBinary = 0;
		int upperBoundForBinary = 1;
		
		int lowerBound = 0;
		int upperBound = 10;
		
		//user story * sprint sayısı kadar değişken oluyor (X11, X12,X13, şeklinde)
		Integer numberOfVariables = numOfUserStories * numOfSprints;
		
		//1 tane Var_M(sprint kurulma kararı) variable'ı olduğundan, sprint sayısı (j) kadar M'i variable sayısına eklemeliyiz.
		numberOfVariables = numberOfVariables + numOfSprints;
		
		//Var_Y(i,j) variable'ı olduğundan, user story * sprint sayısı kadar variable sayısına ekleme yapıyoruz.
		numberOfVariables = numberOfVariables + (numOfUserStories * numOfSprints);
		
		//Tüm variable'ların sayısını set ediyoruz.
		setNumberOfVariables(numberOfVariables);
		
		//3 tane objective function var.
		setNumberOfObjectives(3);
		
		/*
		 * Lingo'daki ilk constraint, toplam user story sayısı kadar değişken üretti. Her bir user user story'nin atanma durumu
		 * Lingo'daki ikinci constraint (establishment), bir sprintin kurulma kararı ile ilgili olup, user story * sprint sayısı 
		 * kadar değişken üretti.
		 * Lingo'daki 3. constraint, sprint sayÄ±sÄ± kadar değişken üretti.
		 * Lingo'daki 4. constraint, user story sayÄ±sÄ± kadar değişken üretti.
		 * Lingo'daki 5. constraint, user story sayÄ±sÄ± kadar değişken üretti.
		 * Lingo'daki 6. constraint, user story*sprint sayÄ±sÄ± kadar değişken üretti.
		 * Lingo'daki 7. constraint, user story*sprint sayÄ±sÄ± kadar değişken üretti.
		 */		
		setNumberOfConstraints(numOfUserStories + (numOfUserStories * numOfSprints) + numOfSprints );
		//setNumberOfConstraints(numOfUserStories + (numOfUserStories * numOfSprints) + numOfSprints + numOfUserStories + numOfUserStories+ (numOfUserStories * numOfSprints) +(numOfUserStories * numOfSprints) );

		setName("App") ;
		
		List<Integer> lowerLimit = new ArrayList<Integer>(getNumberOfVariables()) ;
	    List<Integer> upperLimit = new ArrayList<Integer>(getNumberOfVariables()) ;

	    
	    for (int i = 0; i < ((numOfUserStories * numOfSprints) + numOfSprints); i++) {
		      lowerLimit.add(lowerBoundForBinary);
		      upperLimit.add(upperBoundForBinary);
		    }
	    
	    for (int i = ((numOfUserStories * numOfSprints) + numOfSprints); i < getNumberOfVariables(); i++) {
	      lowerLimit.add(lowerBound);
	      upperLimit.add(upperBound);
	    }

	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);

		overallConstraintViolationDegree = new OverallConstraintViolation<IntegerSolution>() ;
	    numberOfViolatedConstraints = new NumberOfViolatedConstraints<IntegerSolution>() ;
	    
	    createResultFile();

	}

	private void appendResultFile(String inputData) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Tez/Data/Result.txt", true));
	    writer.append(' ');
	    writer.append(inputData);   
	    writer.close();
		
	}
	
	private void createResultFile() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Tez/Data/Result.txt"));
			writer.write("RESULTS"+"\r\n");
			
			writer.close();
		}catch (IOException e){
			e.printStackTrace();
		}		
	}

	private void printPriority() {
    	Iterator<Integer> iter = priority.iterator(); 
		System.out.println("\nThe iterator values of priority are: "); 
		while (iter.hasNext()) { 
			System.out.print(iter.next() + " "); 
		} 
	}
	
	private void printComplexity() {
    	Iterator<Integer> iter = complexity.iterator(); 
		System.out.println("\nThe iterator values of complexity are: "); 
		while (iter.hasNext()) { 
			System.out.print(iter.next() + " "); 
		} 
	}
	
	private void printCapacity() {
    	Iterator<Integer> iter = capacity.iterator(); 
		System.out.println("\nThe iterator values of capacity are: "); 
		while (iter.hasNext()) { 
			System.out.print(iter.next() + " "); 
		} 
	}
	
	private void printORSet() {
		System.out.println("\nThe iterator values of ORSet are: "); 
		//iterate on the general list
		int lineSum = 0;
		ArrayList<Integer> noPre = new ArrayList<Integer>();
		
		for(int i = 0 ; i < orSet.size() ; i++) {
		    ArrayList<Integer> currentList = orSet.get(i);
		    
		    System.out.println(); 
		    //now iterate on the current list
		   for (int j = 0; j < currentList.size(); j++) {
		        Integer s = currentList.get(j);
		        lineSum += s;
		        System.out.print(s + " "); 
		    }
		   noPre.add(lineSum);
		   lineSum = 0;
		}		
		 
		Iterator<Integer> iter = noPre.iterator(); 
		System.out.println("\nThe iterator values of number of prerequisites are: "); 
		while (iter.hasNext()) { 
			System.out.print(iter.next() + " "); 
		} 	
	}

	private void printAffinity() {
		System.out.println("\nThe iterator values of affinitySet are: "); 
		//iterate on the general list
		int lineSum = 0;
		ArrayList<Integer> noOfAffinity = new ArrayList<Integer>();
		
		for(int i = 0 ; i < affinitySet.size() ; i++) {
		    ArrayList<Integer> currentList = affinitySet.get(i);
		    
		    System.out.println(); 
		    //now iterate on the current list
		   for (int j = 0; j < currentList.size(); j++) {
		        Integer s = currentList.get(j);
		        lineSum += s;
		        System.out.print(s + " "); 
		    }
		   noOfAffinity.add(lineSum);
		   lineSum = 0;
		}		
		 
		Iterator<Integer> iter = noOfAffinity.iterator(); 
		System.out.println("\nThe iterator values of number of affinity are: "); 
		while (iter.hasNext()) { 
			System.out.print(iter.next() + " "); 
		} 	
	}

	private void printAlternative() {
		
		System.out.println("\nThe iterator values of alternativeSet are: "); 
		//iterate on the general list
		
		for(int i = 0 ; i < alternativeSet.size() ; i++) {
		    ArrayList<Integer> currentList = alternativeSet.get(i);
		    
		    System.out.println(); 
		    //now iterate on the current list
		   for (int j = 0; j < currentList.size(); j++) {
		        Integer s = currentList.get(j);
		        System.out.print(s + " "); 
		    }
		}			
	}
	
	private void printANDSet() {
		System.out.println("\nThe iterator values of ANDSet are: "); 
		
		//iterate on the general list
		for(int i = 0 ; i < andSet.size() ; i++) {
		    ArrayList<Integer> currentList = andSet.get(i);
		    
		    System.out.println(); 
		    //now iterate on the current list
		   for (int j = 0; j < currentList.size(); j++) {
		        Integer s = currentList.get(j);
		        System.out.print(s + " "); 
		    }
		}			
	}

	public void evaluate(IntegerSolution solution){
		
		//Toplam 3 tane objective function bulunmaktadır.
		double[] f = new double[solution.getNumberOfObjectives()];
		
	
		//1. objective function : Priority'si yüksek olanı (değer olarak düşük) önceki sprintlere ata
		for(int i=0;i<numOfUserStories;i++){
			for(int j=1;j<=numOfSprints;j++){
				f[0]+=j*priority.get(i)*solution.getVariableValue((i*numOfSprints)+(j-1)).intValue();
			}
		}
		solution.setObjective(0, f[0]);

		//2. objective function: Var_Y(i,j)'in maximize olması
		// Minimize -Var_Y(i,j) olarak değiştirdim.
		double interValue = 0;
		for(int i=0;i<numOfUserStories;i++){
			for(int j=0;j<numOfSprints;j++){
				interValue += solution.getVariableValue(((numOfUserStories*numOfSprints)+numOfSprints)+(i*numOfSprints)+j).intValue();
			}
		}
		
		//Var_Y(i,j) ile ilgili olan constraintleri buraya taşıdım.
		
		 /* Lingo'daki 6. constraint: i'nin affine olduğu user story'ler için affinity değişkeni toplamı (aik), Var_Y(i,j)'den küçük olamaz.
	     * 10'a bölmeden hesaplanmalı ki binary değerler ile sayı yazılabilsin. 
	     * */
	    int sumOfAff = 0;
	    ArrayList<Integer> noOfAffinity = new ArrayList<Integer>();
	    int lineSumAff = 0;
	    int difference = 0;
	    
	    for (int i = 0; i < numOfUserStories; i++) {
	    	ArrayList<Integer> tempAffinity = affinitySet.get(i);
	    	for (int j = 0; j < numOfSprints; j++) { 
	    		for (int k = 0; k < numOfUserStories; k++) {
	    			sumOfAff += (tempAffinity.get(k) * solution.getVariableValue((k*numOfSprints)+j).intValue());
	    			lineSumAff += tempAffinity.get(k);
	    		}
	    		difference = sumOfAff - solution.getVariableValue(((numOfUserStories*numOfSprints)+numOfSprints)+(i*numOfSprints)+j).intValue();
	    		
	    		sumOfAff = 0;
	    	}
	    	noOfAffinity.add(lineSumAff);
	    	//lineSumAff = 0;
	    } 
		
		 /*
	     * Lingo'daki 7. constraint: Eğer affinity yoksa Y(i,j) oluşmaz.
	     */
	    int fPart = 0, sPart = 0;
	    int difference2 = 0;
	    
	    for (int i = 0; i < numOfUserStories; i++) {
	    	for (int j = 0; j < numOfSprints; j++) { 
	    		fPart = noOfAffinity.get(i) * solution.getVariableValue((i*numOfSprints)+j).intValue();
	    		sPart = solution.getVariableValue(((numOfUserStories*numOfSprints)+numOfSprints)+(i*numOfSprints)+j).intValue();
	    		
	    		difference2 = fPart - sPart;
	    		//constraints[2*(numOfUserStories * numOfSprints) + (numOfSprints) + (3*numOfUserStories) + (i*numOfSprints) + j] = sPart - fPart;		
	    	}
	    }
		
	    // OR ve AND constraintlerini de buraya taşıdım.
	    /*
	     * Lingo'daki 4. constraint: OR kümesi
	     */
	    int totalOrDependency = 0, nonNegativeNoPre=0, firstPart = 0, secondPart= 0;
	    int lineSum = 0 ;
	    ArrayList<Integer> noPre = new ArrayList<Integer>();
	    int difference3 = 0;
	    
	    for (int i = 0; i < numOfUserStories; i++) {
	    	ArrayList<Integer> tempOR = orSet.get(i);
	    	for (int j = 0; j < numOfSprints; j++) { 
	    		for (int k = 0; k < numOfUserStories; k++) {
	    			for (int l = j+1; l < numOfSprints; l++) { 
	    				firstPart = 0;
	    				secondPart= 0;
	    				firstPart = tempOR.get(k) * solution.getVariableValue((k*numOfSprints)+l).intValue();
	    				secondPart = bigM * (1 - solution.getVariableValue((i*numOfSprints)+j).intValue());
	    				totalOrDependency += firstPart - secondPart;
	    			}		
    				lineSum += tempOR.get(k);
	    		}	
	    		noPre.add(lineSum);
	    		
	    		if ((noPre.get(i) - 1) > 0)
		    		nonNegativeNoPre = noPre.get(i);
		    	else
		    		nonNegativeNoPre = 0;
	    		
	    		difference3 = nonNegativeNoPre - totalOrDependency;
	    		
		    	totalOrDependency = 0;
		    	nonNegativeNoPre = 0;
	    	}	    		
	    }
	    
	    /*
	     * Lingo'daki 5. constraint: AND kümesi
	     */
	    int totalAndDependency = 0;
	    int difference4 = 0;
	    for (int i = 0; i < numOfUserStories; i++) {
	    	ArrayList<Integer> tempAND = andSet.get(i);
	    	for (int j = 0; j < numOfSprints; j++) { 
	    		for (int k = 0; k < numOfUserStories; k++) {
	    			for (int l = j+1; l < numOfSprints; l++) { 
	    				totalAndDependency += (tempAND.get(k) * solution.getVariableValue((k*numOfSprints)+l).intValue()) - (bigM * (1 - solution.getVariableValue((i*numOfSprints)+j).intValue()));	
	    			}
	    		}	
	    		
	    		difference4 = -1 * totalAndDependency;
    	    	
    	    	totalAndDependency = 0;
	    	}    	
	    } 
	    
	    
		f[1] = -1 * interValue + difference + difference2 + difference3 + difference4;
		solution.setObjective(1, f[1]);
	
		//3.objective function: Yaratılan sprintlerin toplam kapasitesini maximize etmelidir.
		// Her sprintte iş var mı diye kontrol etmemiz lazım.
		int totalCapacity = 0;
		int totalComplexityofSprints = 0;
		int isSprintEmpty[] = new int[numOfSprints];
		int totalNumberofUSerStoriesInaSprint = 0;
		for(int j=0;j<numOfSprints;j++){
			for(int i=0;i<numOfUserStories;i++){
				totalComplexityofSprints += complexity.get(i) * solution.getVariableValue((i*numOfSprints)+j).intValue();				
				totalNumberofUSerStoriesInaSprint += solution.getVariableValue((i*numOfSprints)+j).intValue();
			}
			if (totalNumberofUSerStoriesInaSprint > 0) {
				isSprintEmpty[j] = 1;
			}
			else {
				isSprintEmpty[j] = 0;
			}
			totalNumberofUSerStoriesInaSprint = 0;

			totalCapacity += capacity.get(j) * solution.getVariableValue((numOfUserStories*numOfSprints)+j).intValue();
		
		}
		
		f[2] = totalCapacity - totalComplexityofSprints;
	
		solution.setObjective(2, f[2]);
	
		try {
			appendResultFile("\r\n OF 1: " + f[0]);
			appendResultFile("OF 2: " + f[1]);

			appendResultFile("OF 3: " + f[2]);
	
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	public void evaluateConstraints(IntegerSolution solution) {

		int[] constraints = new int[this.getNumberOfConstraints()];
		
		/*ilk constraint aslında her user story için geçerli olduğundan toplam user story'lerin 
		 * sayısı kadar constraint elde etmiş oluyoruz. Diğer constraintler de bunların devamı olacağından
		 * array içine numOfUserStories ve numOfUserStories+1 gibi değerler vermek durumunda kalıyoruz.
		*/
		// Bu constraint sıfıra eşitleniyor.
		int sumOfAlt = 0;		
		for(int i=0;i<numOfUserStories;i++){
			ArrayList<Integer> tempAlternative = alternativeSet.get(i);
			
			for(int k=0;k<numOfUserStories;k++){
				for(int j=0;j<numOfSprints;j++){
					sumOfAlt += (tempAlternative.get(k) * solution.getVariableValue((k*numOfSprints)+j).intValue());
				}
			}	
			if(sumOfAlt != 1) {
				if (sumOfAlt < 1) {
					if (sumOfAlt == 0)
						constraints[i] = -10;
					else
						constraints[i] = sumOfAlt * 10;
				}
				else
					constraints[i] = -10 * sumOfAlt;
			}
			else {
				constraints[i] = 0;	
			}
			sumOfAlt = 0;
		}
		
		//Lingo'daki ikinci constraint - Establishment
		//Bu constraint <= 0 şeklinde eşitleniyor, o yüzden ters çevirip >=0 yazarak constraint violation hesaplanacak.
		/*Constraint 2: Bir sprint kurulmazsa hiç bir user story ona atanmamalıdır.
		*/
		for(int j=0;j<numOfSprints;j++){
			for(int i=0;i<numOfUserStories;i++){				
				constraints[numOfUserStories+(j*numOfUserStories)+i] = (solution.getVariableValue((numOfUserStories*numOfSprints)+j).intValue() - solution.getVariableValue((i*numOfSprints)+j).intValue());			
			}
		}
				
	    /*Lingo'daki 3. constraint: Eğer sprint kurulmuşsa, o sprinte atanan user story'lerin zorlukları toplamı,
	     * o sprintin kapasitesini aşamaz. 
	    */
	    int totalComplexity = 0;
	    for (int j = 0; j < numOfSprints; j++) {  	
	    	for (int i = 0; i < numOfUserStories; i++) {
	    		totalComplexity += complexity.get(i) * solution.getVariableValue((i*numOfSprints)+j).intValue();
	    	}	
	    	constraints[numOfUserStories +  (numOfUserStories*numOfSprints) +j] = (capacity.get(j) * solution.getVariableValue((numOfUserStories*numOfSprints)+j).intValue() - totalComplexity); 
	
	    	totalComplexity = 0;
	    }

	    /*
	     * Lingo'daki 4. constraint: OR kümesi
	     */
	/*    int totalOrDependency = 0, nonNegativeNoPre=0, firstPart = 0, secondPart= 0;
	    int lineSum = 0 ;
	    ArrayList<Integer> noPre = new ArrayList<Integer>();
	    
	    for (int i = 0; i < numOfUserStories; i++) {
	    	ArrayList<Integer> tempOR = orSet.get(i);
	    	for (int j = 0; j < numOfSprints; j++) { 
	    		for (int k = 0; k < numOfUserStories; k++) {
	    			for (int l = j+1; l < numOfSprints; l++) { 
	    				firstPart = 0;
	    				secondPart= 0;
	    				firstPart = tempOR.get(k) * solution.getVariableValue((k*numOfSprints)+l).intValue();
	    				secondPart = bigM * (1 - solution.getVariableValue((i*numOfSprints)+j).intValue());
	    				totalOrDependency += firstPart - secondPart;
	    			}		
    				lineSum += tempOR.get(k);
	    		}	
	    		noPre.add(lineSum);
	    		
	    		if ((noPre.get(i) - 1) > 0)
		    		nonNegativeNoPre = noPre.get(i);
		    	else
		    		nonNegativeNoPre = 0;
	    		
	    		constraints[numOfUserStories +  (numOfUserStories*numOfSprints) + numOfSprints + i] = nonNegativeNoPre - totalOrDependency;
	    		
		    	totalOrDependency = 0;
		    	nonNegativeNoPre = 0;
	    	}	    		
	    }
	  */  
	    /*
	     * Lingo'daki 5. constraint: AND kümesi
	     */
	/*    int totalAndDependency = 0;
	    for (int i = 0; i < numOfUserStories; i++) {
	    	ArrayList<Integer> tempAND = andSet.get(i);
	    	for (int j = 0; j < numOfSprints; j++) { 
	    		for (int k = 0; k < numOfUserStories; k++) {
	    			for (int l = j+1; l < numOfSprints; l++) { 
	    				totalAndDependency += (tempAND.get(k) * solution.getVariableValue((k*numOfSprints)+l).intValue()) - (bigM * (1 - solution.getVariableValue((i*numOfSprints)+j).intValue()));	
	    			}
	    		}	
	    		
	    		constraints[(2*numOfUserStories) + (numOfUserStories*numOfSprints) + numOfSprints + i] = -1 * totalAndDependency;
    	    	
    	    	totalAndDependency = 0;
	    	}    	
	    } 
*/
	    /* Lingo'daki 6. constraint: i'nin affine olduğu user story'ler için affinity değişkeni toplamı (aik), Var_Y(i,j)'den küçük olamaz.
	     * 10'a bölmeden hesaplanmalı ki binary değerler ile sayı yazılabilsin. 
	     * */
	/*    int sumOfAff = 0;
	    ArrayList<Integer> noOfAffinity = new ArrayList<Integer>();
	    int lineSumAff = 0;
	    
	    for (int i = 0; i < numOfUserStories; i++) {
	    	ArrayList<Integer> tempAffinity = affinitySet.get(i);
	    	for (int j = 0; j < numOfSprints; j++) { 
	    		for (int k = 0; k < numOfUserStories; k++) {
	    			sumOfAff += (tempAffinity.get(k) * solution.getVariableValue((k*numOfSprints)+j).intValue());
	    			lineSumAff += tempAffinity.get(k);
	    		}
	    		constraints[ (numOfUserStories*numOfSprints) + numOfSprints + (3*numOfUserStories) + (i*numOfSprints) + j] = sumOfAff - solution.getVariableValue(((numOfUserStories*numOfSprints)+numOfSprints)+(i*numOfSprints)+j).intValue();
	    		//constraints[ (numOfUserStories*numOfSprints) + numOfSprints + (3*numOfUserStories) + (i*numOfSprints) + j] = solution.getVariableValue(((numOfUserStories*numOfSprints)+numOfSprints)+(i*numOfSprints)+j).intValue() - sumOfAff;
	    		
	    		sumOfAff = 0;
	    	}
	    	noOfAffinity.add(lineSumAff);
	    	//lineSumAff = 0;
	    } */

	    /*
	     * Lingo'daki 7. constraint: Eğer affinity yoksa Y(i,j) oluşmaz.
	     */
	 /*   int fPart = 0, sPart = 0;
	    
	    for (int i = 0; i < numOfUserStories; i++) {
	    	for (int j = 0; j < numOfSprints; j++) { 
	    		fPart = noOfAffinity.get(i) * solution.getVariableValue((i*numOfSprints)+j).intValue();
	    		sPart = solution.getVariableValue(((numOfUserStories*numOfSprints)+numOfSprints)+(i*numOfSprints)+j).intValue();
	    		
	    		constraints[2*(numOfUserStories * numOfSprints) + (numOfSprints) + (3*numOfUserStories) + (i*numOfSprints) + j] = fPart - sPart;
	    		//constraints[2*(numOfUserStories * numOfSprints) + (numOfSprints) + (3*numOfUserStories) + (i*numOfSprints) + j] = sPart - fPart;		
	    	}
	    }*/

	    double overallConstraintViolation = 0.0;
	    int violatedConstraints = 0;
	    
	    /*Lingo'daki üçüncü, dördüncü, beşinci, altıncı ve yedinci constraintler >=0 olarak yazıldı. 
	     * ((numOfUserStories*numOfSprints)+numOfUserStories)
	     */
	    for (int i = 0; i < getNumberOfConstraints(); i++) {
	      if (constraints[i] < 0){
	        overallConstraintViolation+=constraints[i];
	        violatedConstraints++;
	      }
	    }
	    
	    overallConstraintViolationDegree.setAttribute(solution, overallConstraintViolation);
	    numberOfViolatedConstraints.setAttribute(solution, violatedConstraints);
	    
	    try {
	    	appendResultFile("sol: "+solution);
			//appendResultFile("sol: "+solution+"overallConstraintViolationDegree = " + overallConstraintViolation);
			//appendResultFile("numberOfViolatedConstraints = " + violatedConstraints);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
