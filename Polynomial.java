package bezout;

import java.util.Scanner;
/**
 * 
 * @author Charles_Kwak
 *This Polynomial class has attributes of two long variables which represent bezout's coefficient 
 */
public class Polynomial {

	//These are the bezout's coefficients
	long bigCoeff;
	long smallCoeff;
	
	//Initialize the coefficients 
	public Polynomial(long bigger, long smaller){
		bigCoeff = bigger;
		smallCoeff = smaller;
	}
	
	/**
	 * This method computes the coefficients of a polynomial resulted from substituting one polynomial 
	 * into another polynomial
	 * @param host - polynomial that is being substituted
	 * @param guest - polynomial that is substituting
	 * @return polynomial resulted from substitution
	 */
	public static Polynomial computeCoeff(Polynomial host, Polynomial guest){
		//calculate the coefficient of the bigger number 
		long bigger = guest.bigCoeff * host.smallCoeff;
		//calculate the coefficient of the smaller number
		long smaller = host.smallCoeff * guest.smallCoeff + host.bigCoeff;
		 
		Polynomial result = new Polynomial(bigger, smaller);
		return result;
	}
	
	/**
	 * This method returns the Bezout's coefficient of two numbers by recursively using Euclidean algorithm
	 * and computeCoeff method  
	 * @param bigger - the bigger number among the two numbers
	 * @param smaller - the smaller one
	 * @return the polynomial with Bezout's coefficient 
	 */
	public static Polynomial euclidean(long bigger, long smaller){
		long remainder = bigger % smaller;
		//if remainder is 0, return a polynomial with 0,1 coefficient in order to set the polynomial of 
		//the previous recursion as the host polynomial
		if(remainder == 0){
			Polynomial host = new Polynomial(0,1); 
			return host;
		}
		//the coefficient of the smaller number in guest polynomial is the negative quotient of the two numbers 
		long smallCoeff = -1 * (bigger/smaller);
		//the coefficient of the bigger number in guest polynomial is always 1
		long bigCoeff = 1;
		
		Polynomial guest = new Polynomial(bigCoeff, smallCoeff);
		//By using recursion, compute the host polynomial that will be substituted by the guest polynomial
		Polynomial host = euclidean(smaller, remainder);
		return computeCoeff(host,guest); 
		 
	}
	
	
	public static void main(String args[]){
		Scanner input = new Scanner(System.in);
		long a;
		long b;
		System.out.print("Enter the first number: ");
		a = input.nextLong();
		System.out.print("Enter the second number: ");
		b = input.nextLong();
		 
		long bigger;
		long smaller;
		//determine which one is bigger or smaller
		if(a == b){
			bigger = a;
			smaller = b;
		}else if(a > b){
			bigger = a;
			smaller = b;
		}else{
			bigger = b;
			smaller = a;
		}
		
		
		Polynomial result = euclidean(bigger, smaller);
		System.out.println("Coefficient of " + bigger + ": " + result.bigCoeff);
		System.out.println("Coefficient of " + smaller + ": " +result.smallCoeff);
		 
		 
		 
	}
	

}
