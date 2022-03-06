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
public class AppWithSmp extends AbstractIntegerProblem implements ConstrainedProblem<IntegerSolution>
{
	public OverallConstraintViolation<IntegerSolution> overallConstraintViolationDegree;
	public NumberOfViolatedConstraints<IntegerSolution> numberOfViolatedConstraints;
	public int numOfUserStories;
	public int numOfSprints;
	
	ArrayList<Integer> priority;
	ArrayList<Integer> complexity;
	ArrayList<Integer> capacity;
	ArrayList<ArrayList<Integer>> orSet;
	ArrayList<ArrayList<Float>> affinitySet;
	ArrayList<ArrayList<Integer>> alternativeSet;
	ArrayList<ArrayList<Integer>> andSet;
	
	int bigM = 999999999;

	public AppWithSmp() {
		
		DataGatherer dataGatherer = new DataGatherer();

		//Her bir user story'nin 1 priority değeri vardır.
		priority = dataGatherer.getPriorities();
		complexity = dataGatherer.getComplexities();
		capacity = dataGatherer.getCapacities();
		orSet = dataGatherer.getORSet();
		affinitySet = dataGatherer.getAffinitySet();
		alternativeSet = dataGatherer.getAlternativeSet();
		andSet = dataGatherer.getANDSet();
		
		numOfUserStories = priority.size();
		numOfSprints = capacity.size();
    	
   /*     printPriority();
        printComplexity();
        printCapacity();
        printORSet();
        printAffinity();
        printAlternative();
        printANDSet();
    */    
        int lowerBoundForUS = 0;
		int upperBoundForUS = numOfSprints;
		
		//user story * sprint sayısı kadar değişken oluyor (X11, X12,X13, şeklinde)
		//Integer numberOfVariables = numOfUserStories * numOfSprints;
		
		//Her user story için 1 tane variable tutacağız ve hangi sprinte atandığını belirleyeceğiz bu değişkende.
		Integer numberOfVariables = numOfUserStories;
		
		//1 tane Var_M(sprint kurulma kararı) variable'ı olduğundan, sprint sayısı (j) kadar M'i variable sayısına eklemeliyiz.
		//Mj'leri kendimiz üreteceğiz.
		//numberOfVariables = numberOfVariables + numOfSprints;
		
		//Var_Y(i,j) variable'ı olduğundan, user story * sprint sayısı kadar variable sayısına ekleme yapıyoruz.
		//Var_Y(i,j)'leri kendimiz hesaplayacağız.
		//numberOfVariables = numberOfVariables + (numOfUserStories * numOfSprints);
		
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
		//setNumberOfConstraints(numOfUserStories + (numOfUserStories * numOfSprints) + numOfSprints + numOfUserStories + numOfUserStories + (numOfUserStories * numOfSprints));
		setNumberOfConstraints(numOfUserStories + (numOfUserStories * numOfSprints) + numOfSprints + numOfUserStories + numOfUserStories + (numOfUserStories * numOfSprints) +(numOfUserStories * numOfSprints) );
		//setNumberOfConstraints(numOfUserStories + (numOfUserStories * numOfSprints) + numOfSprints + numOfUserStories + numOfUserStories+ (numOfUserStories * numOfSprints));
		setName("AppWithSmp") ;
		
		List<Integer> lowerLimit = new ArrayList<Integer>(getNumberOfVariables()) ;
	    List<Integer> upperLimit = new ArrayList<Integer>(getNumberOfVariables()) ;

	    for (int i = 0; i < numOfUserStories; i++) {
		    lowerLimit.add(lowerBoundForUS);   
		    upperLimit.add(upperBoundForUS);
		}
	    
	    setLowerLimit(lowerLimit);
	    setUpperLimit(upperLimit);

		overallConstraintViolationDegree = new OverallConstraintViolation<IntegerSolution>() ;
	    numberOfViolatedConstraints = new NumberOfViolatedConstraints<IntegerSolution>() ;
	    
	//    createResultFile();

	}

	/*private void appendResultFile(String inputData) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Tez/Data/Result.txt", true));
	    writer.append(' ');
	    writer.append(inputData);   
	    writer.close();
		
	}*/
	
	/*private void createResultFile() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Tez/Data/Result.txt"));
			writer.write("RESULTS"+"\r\n");
			
			writer.close();
		}catch (IOException e){
			e.printStackTrace();
		}		
	}*/

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
		    ArrayList<Float> currentList = affinitySet.get(i);
		    
		    System.out.println(); 
		    //now iterate on the current list
		   for (int j = 0; j < currentList.size(); j++) {
		        Float s = currentList.get(j);
		        if(s > 0) {
		        	lineSum += 1;
		        }
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
			
		ArrayList <ArrayList<Integer>> userStories = new ArrayList <ArrayList<Integer>>();
		
		int whichSprint = 0;
		
		for(int i=0;i<numOfUserStories;i++){
			
			ArrayList <Integer> sprints = new ArrayList<Integer>();	
			whichSprint = solution.getVariableValue(i).intValue();
			
			for(int j=1;j<=numOfSprints;j++){			
				if (j == whichSprint) {
					sprints.add(1);
				}
				else {
					sprints.add(0);
				}
			}
			userStories.add(sprints);	
		}
	
		//1. objective function : Priority'si yüksek olanı (değer olarak düşük) önceki sprintlere ata
		for(int i=0;i<numOfUserStories;i++){
			for(int j=1;j<=numOfSprints;j++){
				f[0]+=j*priority.get(i)*userStories.get(i).get(j-1);
			}
		}
		solution.setObjective(0, f[0]);

		//2. objective function: Var_Y(i,j)'in maximize olması
		// Minimize -Var_Y(i,j) olarak değiştirdim.
		
		//Var_Y(i,j)'i kendimiz hesaplayarak Objective Function değerini set ediyoruz.
		/*Eğer bir user story için, afiinity set içerisinde başka user story'ler ile kurulmuş bir bağ varsa,
		 * yani sağa doğru bir değer var ise o zaman o user story için Y(i,j) oluşur.
		 * Oluşan Y(i,j) değeri X(i,j) hangi sprinte atanmışsa o j için oluşur.
		 * Şöyle bakacak olursak, her user story için tarama yapılarak hangi sprinte atandığı bulunur. 
		 * AffinitySet'ten o user story'nin başka hangi user story'lere bağlı olduğu bulunur. 
		 * Diğer user story'ler de aynı sprinte atanmışsa, affinitySet'teki değerler toplanarak Y(i,j) oluşturulur.*/
		
		int sprintOfUS = -1; 
		int sprintOfAff = -1;
		float valueY = 0;
		
		ArrayList<ArrayList<Float>> var_Y = new ArrayList <ArrayList<Float>>();
		
		
		for(int i = 0; i < numOfUserStories; i++){
			for(int j = 0; j < numOfSprints; j++){
				if (userStories.get(i).get(j) == 1) {
					sprintOfUS = j;
				}				
			}		
			
			ArrayList<Float> currentList = affinitySet.get(i);
			
			for (int n = 0; n < currentList.size(); n++) {
			    if( currentList.get(n) > 0) {
			    	for(int j = 0; j < numOfSprints; j++){
						if (userStories.get(n).get(j) == 1) {
							sprintOfAff = j;
						}				
					}	
			    	if (sprintOfUS == sprintOfAff) {
				    	valueY += currentList.get(n);
				    }
				    sprintOfAff = -1;
			    }
			}
			
			ArrayList<Float> var_Yj = new ArrayList<Float>(numOfSprints);
			
			for(int j = 0; j < numOfSprints; j++){
				if(j == sprintOfUS) {
					if(valueY > 0) {
						var_Yj.add(valueY);
					}
					else {
						var_Yj.add((float)0);
					}
				}
				else {
					var_Yj.add((float)0);
				}
			}
			valueY = 0;
			var_Y.add(var_Yj);
		}
		
		
		float interValue = 0;
		for(int i=0;i<numOfUserStories;i++){
			for(int j=0;j<numOfSprints;j++){
				interValue += var_Y.get(i).get(j);
				//interValue += solution.getVariableValue(numOfUserStories+(i*numOfSprints)+j).intValue();
			}
		}
		if(interValue == 0) {
			f[1] = interValue;
			solution.setObjective(1, f[1]);
		}
		else {
			f[1] = -1 * interValue;
			solution.setObjective(1, f[1]);
		}
		
		//Sprint'in kurulma kararını yani Mj'i biz hesaplıyoruz.
		ArrayList<Integer> sprintCreation = new ArrayList<Integer>();
		int created = 0;
		for(int j=0;j<numOfSprints;j++){
			for(int i=0;i<numOfUserStories;i++){	
				if (userStories.get(i).get(j) == 1) {
					created = 1;
				}
			}
			if (created == 1) {
				sprintCreation.add(1);
			}
			else {
				sprintCreation.add(0);
			}
			created = 0;
		}
		
	
		//3.objective function: Yaratılan sprintlerin toplam kapasitesini maximize etmelidir.
		// Her sprintte iş var mı diye kontrol etmemiz lazım.
		int totalCapacity = 0;
		int totalComplexityofSprints = 0;
		
		for(int j=0;j<numOfSprints;j++){
			for(int i=0;i<numOfUserStories;i++){
				totalComplexityofSprints += complexity.get(i) * userStories.get(i).get(j);
			}
			totalCapacity += capacity.get(j) * sprintCreation.get(j);		
		}
		
		f[2] = totalCapacity - totalComplexityofSprints;
	
		solution.setObjective(2, f[2]);
	
	/*	try {
			appendResultFile("\r\n OF 1: " + f[0]);
			appendResultFile("OF 2: " + f[1]);

			appendResultFile("OF 3: " + f[2]);
	
		} catch (IOException e) {
			e.printStackTrace();
		}	*/
	}

	public void evaluateConstraints(IntegerSolution solution) {

		float[] constraints = new float[this.getNumberOfConstraints()];
		
		ArrayList <ArrayList<Integer>> userStories = new ArrayList <ArrayList<Integer>>();
		
		int whichSprint = 0;
		
		for(int i=0;i<numOfUserStories;i++){
			
			ArrayList <Integer> sprints = new ArrayList<Integer>();	
			whichSprint = solution.getVariableValue(i).intValue();
			
			for(int j=1;j<=numOfSprints;j++){			
				if (j == whichSprint) {
					sprints.add(1);
				}
				else {
					sprints.add(0);
				}
			}
			userStories.add(sprints);	
		}
		
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
					//sumOfAlt += (tempAlternative.get(k) * solution.getVariableValue((k*numOfSprints)+j).intValue());
					sumOfAlt += (tempAlternative.get(k) * userStories.get(k).get(j));
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
		
		//Sprint'in kurulma kararını yani Mj'i biz hesaplıyoruz.
		ArrayList<Integer> sprintCreation = new ArrayList<Integer>();
		int created = 0;
		for(int j=0;j<numOfSprints;j++){
			for(int i=0;i<numOfUserStories;i++){	
				if (userStories.get(i).get(j) == 1) {
					created = 1;
				}
			}
			if (created == 1) {
				sprintCreation.add(1);
			}
			else {
				sprintCreation.add(0);
			}
			created = 0;
		}
		
		//Lingo'daki ikinci constraint - Establishment
		//Bu constraint <= 0 şeklinde eşitleniyor, o yüzden ters çevirip >=0 yazarak constraint violation hesaplanacak.
		/*Constraint 2: Bir sprint kurulmazsa hiç bir user story ona atanmamalıdır.
		*/
		for(int j=0;j<numOfSprints;j++){
			for(int i=0;i<numOfUserStories;i++){				
				constraints[numOfUserStories+(j*numOfUserStories)+i] = (sprintCreation.get(j) - userStories.get(i).get(j));		
			}
		}
				
	    /*Lingo'daki 3. constraint: Eğer sprint kurulmuşsa, o sprinte atanan user story'lerin zorlukları toplamı,
	     * o sprintin kapasitesini aşamaz. 
	    */
	    int totalComplexity = 0;
	    for (int j = 0; j < numOfSprints; j++) {  	
	    	for (int i = 0; i < numOfUserStories; i++) {
	    		totalComplexity += complexity.get(i) * userStories.get(i).get(j);
	    	}	
	    	constraints[numOfUserStories +  (numOfUserStories*numOfSprints) +j] = ((capacity.get(j) * sprintCreation.get(j)) - totalComplexity); 
	    	
	    	totalComplexity = 0;
	    }

	    /*
	     * Lingo'daki 4. constraint: OR kümesi
	     */
	    int totalOrDependency = 0, nonNegativeNoPre=0;
	    int lineSum = 0 ;
	    ArrayList<Integer> noPre = new ArrayList<Integer>(numOfUserStories);
	    
	    for (int i = 0; i < numOfUserStories; i++) {
	    	ArrayList<Integer> tempOR_v1 = orSet.get(i);
	    	for (int k = 0; k < numOfUserStories; k++) {
	    		lineSum += tempOR_v1.get(k);
	    	}
	    	noPre.add(lineSum);
	    	lineSum = 0;
	    }
	    
	    for (int i = 0; i < numOfUserStories; i++) {
	    	ArrayList<Integer> tempOR = orSet.get(i);
	    	for (int j = 0; j < numOfSprints; j++) { 
	    		if(userStories.get(i).get(j) == 1) {
		    		for (int k = 0; k < numOfUserStories; k++) {
		    			if(tempOR.get(k) == 1) {
			    			for (int l = j+1; l < numOfSprints; l++) { 		
			    				totalOrDependency +=  userStories.get(k).get(l);
			    			}				    				
		    			}
		    		}	  		
	    		}    		    		
	    	}	
	    	
    		if ((noPre.get(i) - 1) > 0)
	    		nonNegativeNoPre = noPre.get(i)-1;
	    	else
	    		nonNegativeNoPre = 0;
    		
	    	constraints[numOfUserStories +  (numOfUserStories*numOfSprints) + numOfSprints + i] = nonNegativeNoPre - totalOrDependency;
    		
	    	totalOrDependency = 0;
	    	nonNegativeNoPre = 0;	
	    }
	    
	    /*
	     * Lingo'daki 5. constraint: AND kümesi  
	     */
	    int totalAndDependency = 0;
	    for (int i = 0; i < numOfUserStories; i++) {
	    	ArrayList<Integer> tempAND = andSet.get(i);
	    	for (int j = 0; j < numOfSprints; j++) { 
	    		if(userStories.get(i).get(j) == 1) {
		    		for (int k = 0; k < numOfUserStories; k++) {
		    			if(tempAND.get(k) == 1) {
			    			for (int l = j+1; l < numOfSprints; l++) { 
			    				totalAndDependency += userStories.get(k).get(l);
			    			}
		    			}
		    		}		
	    		}
	    	}  
	    	constraints[(2*numOfUserStories) + (numOfUserStories*numOfSprints) + numOfSprints + i] = -1 * totalAndDependency;	    	
	    	totalAndDependency = 0;
	    } 

	    //Affinity
	    
	    int sprintOfUS = -1; 
		int sprintOfAff = -1;
		float valueY = 0;
		ArrayList<Integer> numOfAffinity = new ArrayList<Integer>(numOfUserStories);
		int interVal = 0;
		
		ArrayList<ArrayList<Float>> var_Y = new ArrayList <ArrayList<Float>>();	
		
		for(int i = 0; i < numOfUserStories; i++){
			for(int j = 0; j < numOfSprints; j++){
				if (userStories.get(i).get(j) == 1) {
					sprintOfUS = j;
				}				
			}		
			
			ArrayList<Float> currentList = affinitySet.get(i);
			
			for (int n = 0; n < currentList.size(); n++) {
			    if( currentList.get(n) > 0) {
			    	for(int j = 0; j < numOfSprints; j++){
						if (userStories.get(n).get(j) == 1) {
							sprintOfAff = j;
						}				
					}	
			    	if (sprintOfUS == sprintOfAff) {
				    	valueY += currentList.get(n);
				    }
				    sprintOfAff = -1;
				    interVal++;
			    }
			}
			
			ArrayList<Float> var_Yj = new ArrayList<Float>(numOfSprints);
			
			for(int j = 0; j < numOfSprints; j++){
				if(j == sprintOfUS) {
					if(valueY > 0) {
						var_Yj.add(valueY);
					}
					else {
						var_Yj.add((float)0);
					}
				}
				else {
					var_Yj.add((float)0);
				}
			}
			valueY = 0;
			var_Y.add(var_Yj);
			numOfAffinity.add(interVal);
			interVal = 0;
		}	    
	    
	    /* Lingo'daki 6. constraint: i'nin affine olduğu user story'ler için affinity değişkeni toplamı (aik), Var_Y(i,j)'den küçük olamaz.
	     * 10'a bölmeden hesaplanmalı ki binary değerler ile sayı yazılabilsin. 
	     * */
		float interValue = 0;
		for(int i = 0; i < numOfUserStories; i++){
			ArrayList<Float> currentList = affinitySet.get(i);
			
			for (int n = 0; n < currentList.size(); n++) {
				for(int j = 0; j < numOfSprints; j++){
					interValue += currentList.get(n) * userStories.get(n).get(j);					
				}
			}
			for(int j = 0; j < numOfSprints; j++){
				constraints[ (numOfUserStories*numOfSprints) + numOfSprints + (3*numOfUserStories) + (i*numOfSprints) + j] = interValue - var_Y.get(i).get(j);
			}
			interValue = 0;
		}
		
		
		
	  /*  int sumOfAff = 0;
	    
	    for (int i = 0; i < numOfUserStories; i++) {
	    	ArrayList<Float> tempAffinity = affinitySet.get(i);
	    	for (int j = 0; j < numOfSprints; j++) { 
	    		for (int k = 0; k < numOfUserStories; k++) {
	    			sumOfAff += (tempAffinity.get(k) * userStories.get(k).get(j));
	    		}
	    		constraints[ (numOfUserStories*numOfSprints) + numOfSprints + (3*numOfUserStories) + (i*numOfSprints) + j] = sumOfAff - var_Y.get(i).get(j);
	 
	    		sumOfAff = 0;
	    	}
	    } */

	    /*
	     * Lingo'daki 7. constraint: Eğer affinity yoksa Y(i,j) oluşmaz.
	     */
	    float fPart = 0, sPart = 0;
	    
	    for (int i = 0; i < numOfUserStories; i++) {
	    	for (int j = 0; j < numOfSprints; j++) { 
	    		fPart = numOfAffinity.get(i) * userStories.get(i).get(j);
	    		sPart = var_Y.get(i).get(j);
	    		//		solution.getVariableValue(numOfUserStories+(i*numOfSprints)+j).intValue();
	    		
	    		constraints[2*(numOfUserStories * numOfSprints) + (numOfSprints) + (3*numOfUserStories) + (i*numOfSprints) + j] = fPart - sPart;
	    	}
	    }

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
/*	    
	    try {
	    	appendResultFile(solution+" ");
	    	for (int j = 0; j < numOfSprints; j++) { 
	    		appendResultFile("M" + j + ": "+sprintCreation.get(j));
	    	}
	    	appendResultFile("Yij");
	    	for (int i = 0; i < numOfUserStories; i++) {
		    	for (int j = 0; j < numOfSprints; j++) { 
		    		appendResultFile(var_Y.get(i).get(j)+" ");
		    	}
	    	}
	    	
		} catch (IOException e) {
			e.printStackTrace();
		}*/

	}
}
