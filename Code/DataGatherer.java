package org.nilay.thesis.optimization;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataGatherer {

	
	public BufferedReader br;
	
	public ArrayList <Integer> getPriorities(){
		
		ArrayList <Integer> priority = new ArrayList<Integer>();	
		
		try {
			br = new BufferedReader(new FileReader("C:/Tez/Data/priority.txt"));
		    String line = br.readLine();
		    while (line != null) {
		    	priority.add(Integer.parseInt(line));
		        line = br.readLine();
		    }
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found: priority.txt");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return priority;
	}
	
	public ArrayList <Integer> getComplexities() {
		
		ArrayList <Integer> complexity = new ArrayList<Integer>();	
		
		try{
			br = new BufferedReader(new FileReader("C:/Tez/Data/complexity.txt"));
			String line = br.readLine();
		    while (line != null) {
		    	complexity.add(Integer.parseInt(line));
		        line = br.readLine();
		    }
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found: complexity.txt");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return complexity;
	}
	
	public ArrayList <Integer> getCapacities() {
		
		ArrayList <Integer> capacity = new ArrayList<Integer>();	
		
		try{
			br = new BufferedReader(new FileReader("C:/Tez/Data/capacity.txt"));
			String line = br.readLine();
		    while (line != null) {
		    	capacity.add(Integer.parseInt(line));
		        line = br.readLine();
		    }
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found: capacity.txt");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return capacity;
	}
	
	public ArrayList <ArrayList<Integer>> getORSet() {
		
		ArrayList <ArrayList<Integer>> orSet = new ArrayList <ArrayList<Integer>>();
	
		try {
			br = new BufferedReader(new FileReader("C:/Tez/Data/OR.txt"));
		    String line = br.readLine();

		    while (line != null) {
		    	String fields[] = line.split(";");
		    	ArrayList<Integer> tempList = new ArrayList<Integer>();
		    	for(int k=0; k<fields.length;k++){
		    		tempList.add(Integer.parseInt(fields[k]));		
		    	}
		    	orSet.add(tempList);	     
		        line = br.readLine();
		    }
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found: OR.txt");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return orSet;
	}
	
	public ArrayList <ArrayList<Integer>> getAlternativeSet() {
		
		ArrayList <ArrayList<Integer>> alternativeSet = new ArrayList <ArrayList<Integer>>();
		
		try {
			br = new BufferedReader(new FileReader("C:/Tez/Data/Alternative.txt"));
		    String line = br.readLine();

		    while (line != null) {
		    	String fields[] = line.split(";");
		    	ArrayList<Integer> tempList = new ArrayList<Integer>();
		    	
		    	for(int k=0; k<fields.length;k++){
		    		tempList.add(Integer.parseInt(fields[k]));
		    	}	
		    	alternativeSet.add(tempList);
		        line = br.readLine();
		    }
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found: Alternative.txt");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return alternativeSet;
	}
	
	public ArrayList <ArrayList<Integer>> getANDSet() {
		
		ArrayList <ArrayList<Integer>> andSet = new ArrayList <ArrayList<Integer>>();
		
		try {
			br = new BufferedReader(new FileReader("C:/Tez/Data/AND.txt"));
		    String line = br.readLine();
		    
		    while (line != null) {
		    	String fields[] = line.split(";");
		    	ArrayList<Integer> tempList = new ArrayList<Integer>();
		    	
		    	for(int k=0; k<fields.length;k++){
		    		tempList.add(Integer.parseInt(fields[k]));
		    	}
		    	andSet.add(tempList);
		        line = br.readLine();
		    }
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found: AND.txt");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return andSet;
	}
	public ArrayList <ArrayList<Float>> getAffinitySet() {
		
		ArrayList <ArrayList<Float>> affinitySet = new ArrayList <ArrayList<Float>>();
		
		try {
			br = new BufferedReader(new FileReader("C:/Tez/Data/Affinity.txt"));
		    String line = br.readLine();

		    while (line != null) {
		    	String fields[] = line.split(";");		    	
		    	ArrayList<Float> tempList = new ArrayList<Float>();
		    	
		    	for(int k=0; k<fields.length;k++){	
		    		tempList.add(Float.parseFloat(fields[k]));
		    	}
		    	affinitySet.add(tempList); 	
		        line = br.readLine();		   
		    }		    
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found: Affinity.txt");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return affinitySet;
	}
	public ArrayList <ArrayList<Integer>> getAffinitySetInt() {
		
		ArrayList <ArrayList<Integer>> affinitySet = new ArrayList <ArrayList<Integer>>();
		
		try {
			br = new BufferedReader(new FileReader("C:/Tez/Data/Affinity.txt"));
		    String line = br.readLine();

		    while (line != null) {
		    	String fields[] = line.split(";");		    	
		    	ArrayList<Integer> tempList = new ArrayList<Integer>();
		    	
		    	for(int k=0; k<fields.length;k++){	
		    		tempList.add(Integer.parseInt(fields[k]));
		    	}
		    	affinitySet.add(tempList); 	
		        line = br.readLine();		   
		    }		    
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found: Affinity.txt");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		return affinitySet;
	}
}
