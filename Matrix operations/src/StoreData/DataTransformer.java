package StoreData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Author
 * Sai Sunil Menta
 * CSID : menta
 * Banner ID : B00832218
 */
public class DataTransformer {
	//Setting up all the reader and writer objects to null so that it will be used later in functions 
	FileReader fr=null;
	BufferedReader br=null;
	FileWriter fw=null;
	BufferedWriter bw=null;
	Scanner sc=new Scanner(System.in);
	//The Data which is coming in file will be read as ArrayList of Arraylist strings
	List<ArrayList<String>> al=new ArrayList<ArrayList<String>>();
	ArrayList<String> ao=null;
	
	public boolean clear (  ) {
		  al.clear();//clearing the ArrayList so that all data in that is cleared
			if(al.size()==0) { //checking the size and throwing the boolean values
				return true;
			}
			else {
				return false;
		}
	}
	@SuppressWarnings("finally")
	public Integer read( String filename ) {
		
		Integer length=0; //length is the row size which is initialised to 0 at the first
		
		try {
			br=new BufferedReader(new FileReader(filename));
			String scurrentline; //scurrentline is declared to store the lines of file in every iteraton of arraylist
			while((scurrentline=br.readLine())!=null){
				
				
				String[] temp=scurrentline.split("	");//splitting the file and storing into a string variable
				if(temp.length>10) {
					System.out.println("File has more than 10 columns"); //This makes not to read file more than 10 columns
					break;
				}
				ao=new ArrayList<String>();
				for(int i=0;i<temp.length;i++) {
					ao.add(temp[i]);//reading all the data in file
				}
				al.add(ao);
				length++;//updating the number of rows read
			}
		}
		catch(FileNotFoundException e) {
		  System.out.println("File name not found");
		}
		finally{
			return length;
		}
		
	}

	public boolean newColumn ( String columnName ) throws IOException {
		
		boolean added=false; //assigning boolean which turns to false when column added
					try {
				if(columnName.equals("")||columnName=="null") {
					System.out.println("Enter Valid Column name");
				}
				else {
					String trimmedColumnName = columnName.replaceAll("\\s+","");
					if(columnName==trimmedColumnName) {//assuring column name doesnt contain spaces
						for(ArrayList<String> as:al) {
							ao=new ArrayList<String>();
							ao=as;
								if(ao.contains(columnName)) {
									
									System.out.println("Column name already exists");
								
								}
								else {
									//adding column into data structure
									if(al.indexOf(ao)==0) {
										ao.add(columnName);
										if(ao.contains(columnName)) {
											added=true; 
										}
									}
									else {
										ao.add("0");
									}
								}
						}
					}
					else {
						System.out.println("Column Name should not contain space");
					}
				}
			}
			catch(NullPointerException e) {
				System.out.println("ColumnName should not be null"); 	
			}		
		return added;
       }
	public Integer calculate( String equation ) { 
		
		String result=null;//left side attribute before equals
		String calc=null;//right side attribute after equals
		int count =0; //This variable updates the number of rows updated
		String[] lhs=equation.split("=");
		for(int i=0;i<lhs.length;i++) {//seperating the equation
			
			if(i==0) {
				result=lhs[0];
				result=result.trim();
			}
			else {
				calc=lhs[1];
				calc=calc.trim();
			}
		}
		int indexLhs=0;
		int operandOne=0;
		int operandTwo=0;
		
		if(calc.contains("+")||calc.contains("-")||calc.contains("*")||calc.contains("/")) {
			//Separating the single operands and incorrect operands
			if(calc.contains("+")) {//Scenario if addition operator occurs
				try {
				String[] rhs=calc.split("\\+");
				for(int i=0;i<al.size();i++) {
					
					ao=new ArrayList<String>();
					ao=al.get(i);
					if(i==0) {
						
						if(!ao.contains(result)) {//if the result string is not in file
							System.out.println("Invalid Equation");
							break;
						}
						indexLhs=ao.indexOf(result);
						rhs[0]=rhs[0].trim();
						rhs[1]=rhs[1].trim();
						operandOne=ao.indexOf(rhs[0]);
						operandTwo=ao.indexOf(rhs[1]);
					}
					else {
						try {//Dealing with different operands and scenarios of numbers and strings
							if(operandOne==-1 && operandTwo==-1) {
								int tempResult=Math.round(Float.parseFloat(rhs[0])+Float.parseFloat(rhs[1]));
								ao.set(indexLhs, Integer.toString(tempResult));	
							}
							else if(operandOne==-1) {
								int tempResult=Math.round(Float.parseFloat(rhs[0])+Float.parseFloat(ao.get(operandOne)));
								ao.set(indexLhs, Integer.toString(tempResult));	
							}
							else if(operandTwo==-1){
								
								int tempResult=Math.round(Float.parseFloat(ao.get(operandOne))+Float.parseFloat(rhs[1]));
								ao.set(indexLhs, Integer.toString(tempResult));	
							}
							else {
								 int tempResult=Math.round(Float.parseFloat(ao.get(operandOne))+Float.parseFloat(ao.get(operandTwo)));
									ao.set(indexLhs, Integer.toString(tempResult));	
							}
							count++;//updating the rows calculated
						}
						catch(NumberFormatException e) {
							System.out.println("Invalid Expression");
							break;
						}
						
						
					}
				}
			}
				catch(Exception e) {
					System.out.println("Invalid Equation");
				}
			}
			else if(calc.contains("*")) {//scenario of multiplication
				try {
				String[] rhs11=calc.split("\\*");//splitting the operands
				for(int i=0;i<al.size();i++) {
					ao=new ArrayList<String>();
					ao=al.get(i);
					if(i==0) {
						if(!ao.contains(result)) {
							System.out.println("Invalid Equation");
							break;
						}
						indexLhs=ao.indexOf(result);//Grabbing the index of all operands
						rhs11[0]=rhs11[0].trim();
						rhs11[1]=rhs11[1].trim();
						operandOne=ao.indexOf(rhs11[0]);
						operandTwo=ao.indexOf(rhs11[1]);
					}
					else {
						try {//Dealing with different operands and scenarios of numbers and strings
							if(operandOne==-1 && operandTwo==-1) {
								int tempResult=Math.round(Float.parseFloat(rhs11[0])*Float.parseFloat(rhs11[1]));
								ao.set(indexLhs, Integer.toString(tempResult));	
							}
							else if(operandOne==-1) {
								int tempResult=Math.round(Float.parseFloat(rhs11[0])*Float.parseFloat(ao.get(operandTwo)));
								ao.set(indexLhs, Integer.toString(tempResult));
							}
							else if(operandTwo==-1){
								int tempResult=Math.round(Float.parseFloat(ao.get(operandOne))*Float.parseFloat(rhs11[1]));
								ao.set(indexLhs, Integer.toString(tempResult));	
							}
							else {
								 int tempResult=Math.round(Float.parseFloat(ao.get(operandOne))*Float.parseFloat(ao.get(operandTwo)));
									ao.set(indexLhs, Integer.toString(tempResult));	
							}
							count++;//updating number of rows calculated
						}
						catch(NumberFormatException e) {
							//e.printStackTrace();
							System.out.println("Invalid Expression");
							break;
						}
				
					}
				}
			}
				catch(Exception e) {
					System.out.println("Invalid Equation");
				}
			
				
			}
			else if(calc.contains("/")){
				try {
				String[] rhs111=calc.split("\\/");//dealing with division operation
				for(int i=0;i<al.size();i++) {
					ao=new ArrayList<String>();
					ao=al.get(i);
					if(i==0) {
						if(!ao.contains(result)) {//dealing with invalid result string
							System.out.println("Invalid Equation");
							break;
						}
						//grabbing indexes of all operands
						indexLhs=ao.indexOf(result);
						rhs111[0]=rhs111[0].trim();
						rhs111[1]=rhs111[1].trim();
						operandOne=ao.indexOf(rhs111[0]);
						operandTwo=ao.indexOf(rhs111[1]);
					}
					else {
						try {//Dealing with different operands,constants and strings
							if(operandOne==-1 && operandTwo==-1) {
								int tempResult=Math.round(Float.parseFloat(rhs111[0])/Float.parseFloat(rhs111[1]));
								ao.set(indexLhs, Integer.toString(tempResult));	
							}
							else if(operandOne==-1){
								int tempResult=(Integer.parseInt(rhs111[0])/Integer.parseInt(ao.get(operandTwo)));
								ao.set(indexLhs, Integer.toString(tempResult));
							}
							else if(operandTwo==-1){
								int tempResult=(Integer.parseInt(ao.get(operandOne))/Integer.parseInt(rhs111[1]));
								ao.set(indexLhs, Integer.toString(tempResult));	
							}
							else {
								 int tempResult=Math.round(Float.parseFloat(ao.get(operandOne))/Float.parseFloat(ao.get(operandTwo)));
									ao.set(indexLhs, Integer.toString(tempResult));	
							}
							count++;//updating the numbers of rows calculated
						}
						catch(NumberFormatException e) {
							System.out.println("Invalid Expression");
							break;
						}
						catch(ArithmeticException e) {
							System.out.println("operand should not be zero while division");
							break;
						}
						
					}
				}
				}
				catch(Exception e) {
					System.out.println("Invalid Equation");
				}
			
			}
           else if(calc.contains("-")) {//Dealing with subtraction scenarios
        	 
				try {
				String[] rhs1=calc.split("\\-");//splitting the opearnds
				for(int i=0;i<al.size();i++) {
					ao=new ArrayList<String>();
					ao=al.get(i);
					if(i==0) {
						if(!ao.contains(result)) {//Dealing with incorrect result string
							System.out.println("Invalid Equation");
							break;
						}
						//Grabbing all the indexes of operands
						indexLhs=ao.indexOf(result);
						rhs1[0]=rhs1[0].trim();
						rhs1[1]=rhs1[1].trim();
						operandOne=ao.indexOf(rhs1[0]);
						operandTwo=ao.indexOf(rhs1[1]);
					}
					else {//Dealing with different operands,constants and strings
						try {
							if(operandOne==-1 && operandTwo==-1) {
								int tempResult=Math.round(Float.parseFloat(rhs1[0])-Float.parseFloat(rhs1[1]));
								ao.set(indexLhs, Integer.toString(tempResult));	
							}
							else if(operandOne==-1) {
								int tempResult=Math.round(Float.parseFloat(rhs1[0])-Float.parseFloat(ao.get(operandTwo)));
								ao.set(indexLhs, Integer.toString(tempResult));	
							}
							else if(operandTwo==-1){
								int tempResult=Math.round(Float.parseFloat(ao.get(operandOne))-Float.parseFloat(rhs1[1]));
								ao.set(indexLhs, Integer.toString(tempResult));	
							}
							else {
								 int tempResult=Math.round(Float.parseFloat(ao.get(operandOne))-Float.parseFloat(ao.get(operandTwo)));
									ao.set(indexLhs, Integer.toString(tempResult));	
							}
							count++;
						}
						catch(NumberFormatException e) {
							System.out.println("Invalid Expression");
							break;
						}
						
						
					}
				}
				}
			catch(Exception e) {
				System.out.println("Invalid Equation");
			}
			}
		}
		else {
			//Dealing with Scenarios if the equation doesnt contain any of operatots 
			int operand=0;//variable to store index of single operand
			if( (calc.matches("[a-zA-Z]+"))|| calc.matches("[0-9]+")) {//Filtering all the invalid strings
				for(int i=0;i<al.size();i++) {
					ao=new ArrayList<String>();
					ao=al.get(i);
					if(i==0) {
						if(!ao.contains(result)) {
							System.out.println("Invalid Equation");
							break;
						}
						//Grabbing the indexes of operands
						indexLhs=ao.indexOf(result);
						operand = ao.indexOf(calc);
					}
					else {
						//dealing with operands and constants or strings
						if(operand==-1) {
							 ao.set(indexLhs, calc);
								
						}
						else {
							ao.set(indexLhs, ao.get(operand));
						}
						count++;
						
					}
				}
			}
			else {
				System.out.println("Invalid expression");
			}
				

			}
				
					return count;//returning the number of columns updated
	}

	public void top(  ) {
	
	try {	
        int i=0;//iterating values that counts the numbers of rows getting printed
		for(int a=0;a<al.size();a++) {
			int row=al.get(a).size();
			if(i==5) {
				break;//Loop stops printing after fifth row gets printed
			}
			else
			{
				for(int b=0;b<row;b++)
				{
					System.out.print(al.get(a).get(b)+"\t");	//printing the data structure
				}
			}
			
			i++;//counting the number of rows getting printed
			System.out.println();
		}
	
	}
	catch(Exception e) {
		e.printStackTrace();
	}

	}

	public void print(  ) {//Printing entire content of files
		try {
			for(int a=0;a<al.size();a++) {
				int row=al.get(a).size();
				for(int b=0;b<row;b++)
				{
					System.out.print(al.get(a).get(b)+"\t");	//printing the values
				}
				System.out.println();
			}
		
		}
		catch(Exception e) {
		
		}
	}

	public Integer write( String filename ) {
		
		int count=0;//variable to counts number of rows getting written
		PrintWriter pw=null;//creating an instance of printwritter to write the content to file
		try {
			fw=new FileWriter(filename);
		    pw=new PrintWriter(fw);
			ao=new ArrayList<String>();
			for (ArrayList<String> mainlist: al) {
				ao=mainlist;
				for(String sublist:ao) {
					pw.write(sublist+"\t"); //writting the content into file
				}
				count++;
				pw.println();
			}
			} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Error in writting the file");
		}
		finally {
		
			pw.close();
		}	
		return count;//returning the number of columns written
	}
}
