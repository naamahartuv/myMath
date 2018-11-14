package myMath;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Predicate;

import javax.management.RuntimeErrorException;

import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Yael Hava and Naama Hartuv
 *
 */
public class Polynom implements Polynom_able{
	private ArrayList <Monom> list;
	private Monom_Comperator MonomCompertor= new Monom_Comperator(); 
	/**
	 * polynom constractor that get polynom in type string and convert it to a list of monoms 
	 * @param s- the string that we convert to the polynom
	 */
	public Polynom (String s) {				//polynom constractor that get the string
		list= new ArrayList <Monom>();
		s=s.replace(" ", ""); 				
		if(s== "") list=this.list;			//if we get string that empty string, return empty string
		else if(s=="0"||s=="0.0") {			//if we get string that is "0" or "0.0", return zero
			Monom zero= new Monom (0,0);
			list.add(zero);
		}
		else {
			String [] splitMP = s.split("(?=[+-])");	//split the polynom to monoms by "+" "-"
			double coefficient;
			int power;
			for (int i=0; i< splitMP.length; i++) {		//run over the monoms
				if(splitMP[i].contains("x")) {
					String[] splitX= splitMP[i].split("x");//split the monom by x
					if(splitX.length==0) {				//if the value is "x"
						coefficient=1;
						power=0;
					}
					else if(splitX[0].contains("*")) {		//if the monom contain an *	
						coefficient= Double.parseDouble(splitX[0].substring(0, splitX[0].length()-1));
					}
					else {
						if(splitX[0].contains("-")) {	//if the monom contain an -
							if(splitX[0].length()>1) {	//if it has a number
								coefficient=Double.parseDouble(splitX[0]);
							}
							else {						//if the monom only contain an -
								coefficient= -1;
							}
						}
						else if (splitX[0].contains("+")) {	////if the monom contain an +
							if (splitX[0].length()>1) 		//if it has a number
								coefficient= Double.parseDouble(splitX[0]);
							else {							//if it dosent have a number
								coefficient = 1;
							}

						}
						else if(!splitMP[0].contains("-")&& !splitX[0].isEmpty() ) //if it dosent empty and it dosent have "-"
							coefficient= Double.parseDouble(splitX[0]); 
						else {							//if it dosent empty
							coefficient=1;
						}
					}

					if (splitX.length==2) {				//if there is a power for the x
						power= Integer.parseInt(splitX[1].substring(1));
					}
					else {								//if its dosent have power
						power= 1;
					}
					Monom m= new Monom (coefficient,power);	//create the new monom
					list.add(m);							//add the monom to the list
				}

				else {										//if the monom dosent contains x
					coefficient= Double.parseDouble(splitMP[i]);
					remove();								//remove zero 
					power= 0;								
					Monom m= new Monom (coefficient,power);	//create the new monom
					list.add(m);							//add the monom to the list
				}

			}
		}

		remove();								//remove zero
		list.sort(this.MonomCompertor);			//sort the monom by power
		polynomMerge();							//merge the monoms that have the same power

	}

	/**
	 * empty constructor
	 */

	public Polynom () {							//empty constructor
		list= new ArrayList <Monom>();
	}

	/**
	 * print the polynom as a string
	 */
	public String toString() {					//print the polynom as a string
		String s= "";							//new empty string

		for( int i=0; i<list.size();i++) {		//run over the list
			if(list.get(i).get_coefficient()>=0 && i>0) {	//if the coefficient is positive number and not the first monom
				s+= "+" + list.get(i).toString();
			}
			else									//if is the first monom or negtive coefficient
				s+= list.get(i).toString();
		}

		return  s;
	}
	/**
	 * function that calculate the  function value at x
	 * @return the function value at x
	 */
	@Override
	public double f(double x) {					//return the function value at x
		double result= 0;
		Iterator<Monom> it= this.iteretor();
		while(it.hasNext()) {
			Monom m= it.next();
			result += m.f(x);
		}

		return result;
	}
	/**
	 * addition between two polynoms
	 * @param p1- the polynom we add to the original polynom
	 */
	@Override
	public void add(Polynom_able p1) { 		//add polynom to polynom
		Iterator<Monom> it= p1.iteretor();	//create a pointer to the monom
		while(it.hasNext()) {	
			Monom m = it.next();
			for(int i=0; i<list.size();i++) {
				if(list.get(i).get_power()==m.get_power()) {	//if the poewers is equals 
					list.get(i).add(m);							
					break;
				}
				else if (i==list.size()-1) {					//if he dosent have an equals monoms power at the polynom
					list.add(m);
					break;
				}

			}
		}
		list.sort(MonomCompertor);				//sort the polynom
		polynomMerge();							//merge monoms
		remove();								//remove zero
	}
	/**
	 * addition between monom and polynom
	 * @param m1-the monom we add to the polynom
	 */
	@Override
	public void add(Monom m1) {				//add monom to the polynom
		if(list.size()==0) list.add(m1);	//if the list is empty
		else {
			for (int i=0; i<list.size(); i++) {
				if (list.get(i).get_power()==m1.get_power()) {	//if the poewers is equals
					list.get(i).add(m1);
					break;
				}
				else if(i==list.size()-1) {					//if he dosent have an equals monoms power at the polynom
					list.add(m1);
					break;
				}
			}
			remove();		//remove zero
			polynomMerge();
			list.sort(MonomCompertor);	//sort the list
		}

	}
	/**
	 * remove the zeros monom from the polynom
	 */
	private void remove() {			//remove the zeros monom from the polynom
		if(list.size()>1) {			//if the polynom is just zero, do not remove him
			for(int i=0; i<list.size(); i++) {
				if(list.get(i).get_coefficient()==0) {
					list.remove(i);
					i--;
				}
			}
		}

	}
	/**
	 * substract polynom from polynom
	 * @param p1-m the polynom we substract from the original polynom
	 */

	@Override
	public void substract(Polynom_able p1) {		//substract polynom from polynom 
		Iterator<Monom> it= p1.iteretor();			//creat a pointer to the monoms
		while(it.hasNext()) {	
			Monom m = it.next();
			for(int i=0; i<list.size();i++) {
				if(list.get(i).get_power()==m.get_power()) {	//if the powers equals
					list.get(i).set_coefficient(list.get(i).get_coefficient()-m.get_coefficient());		//substrace the coefficients of the monoms
					break;
				}
				else if (i==list.size()-1) {		//if the monom dosent have equals monoms power
					list.add(m);					//add the monom to the list
					m.set_coefficient(-1*m.get_coefficient());	//set it as a minus coefficient
					break;
				}
			}
		}

		list.sort(MonomCompertor);		//sort the list
		remove();						//remove zero

	}
	/**
	 * merge the monoms with an equals power
	 */
	private void polynomMerge() {		//merge the monoms with an equals power
		for(int i=0; i<list.size()-1;i++) {
			if(list.get(i).get_power()==list.get(i+1).get_power()) {		//if the powers is equals
				list.get(i).add(list.get(i+1));
				list.remove(i+1);											//remove the extra monom 
				i--;
			}
		}

	}
	/**
	 * multiply polynom with polynom
	 * @param p1- the polynom we multiply with the original polynom
	 */
	@Override
	public void multiply(Polynom_able p1) {		//multiply polynom with polynom
		Polynom_able savePoly= copy();			//create new polynom that same to the original polynom
		list=new ArrayList<Monom>();			//reset the original polynom
		Iterator<Monom> itp1= p1.iteretor();	//points on the new polynom

		while(itp1.hasNext()) {
			Monom m= itp1.next();
			Iterator<Monom> itSave= savePoly.iteretor();	//points on the original polynom
			while (itSave.hasNext()) {
				Monom saveMonom= new Monom(itSave.next());
				saveMonom.multiply(m);						//multiply the monoms
				list.add(saveMonom);						//add the monom to the list
				if(saveMonom.get_coefficient()==0 && saveMonom.get_power()>0) {	//in case the coefficient is zero and the power isnt zero
					saveMonom.set_power(0);					
				}
			}
		}
		list.sort(MonomCompertor);	//sort the list
		polynomMerge();				//merge monoms
		remove();				//remove zero	

	}
	/**
	 * check if the excepted polynom is equals to the original polynom
	 * @param p1- the polynom we check if is equals to the original
	 * @return true for equals, false for not equals
	 */
	@Override
	public boolean equals(Polynom_able p1) {		//check if the polynom is equals to the original 
		Iterator<Monom> itlist= this.iteretor();	//point on the new polynom
		Iterator<Monom> it= p1.iteretor();			//point on the original polynom

		while (it.hasNext() && itlist.hasNext()) {
			Monom m1=it.next();
			Monom m2= itlist.next();
			if(m1.get_coefficient()!=m2.get_coefficient()|| m1.get_power()!=m2.get_power())	//i fthe coefficient or the power is different
				return false;
		}
		if (it.hasNext()==true && itlist.hasNext()==false) return false;	//if the polynoms doesnt have the same size
		if (it.hasNext()==false && itlist.hasNext()==true) return false;	//if the polynoms doesnt have the same size

		return true;
	}
	/**
	 * check if the polynom is empty
	 * @return true for empty, false for not empty
	 */
	@Override
	public boolean isZero() {		//check if the polynom is empty
		if(list.size()==0) return true;
		else if(list.size()==1&&list.get(0).get_coefficient()==0) return true;	//if the polynom is zero
		return false;
	}
	/**
	 * operate the bisection method on the polynom
	 * @param x0- the x that under the x-axis
	 * @param x1- the x that above the x-axis
	 * @param eps-  the eps step (positive) value
	 * @return the root of the function
	 */
	@Override
	public double root(double x0, double x1, double eps) {			//operate the bisection method on the polynom
		double x2= x0;										//set x2 as one of the bounds
		while(Math.abs(x1-x0) > eps) {						//while the distance greater than the epsilon
			x2 = (x1 + x0)/2;								//calculate the middle
			if(f(x2) ==0) {
				return x2;						//if f(x2) is zero
			}
			else {
				if(f(x1)*f(x2)<0) x0=x2;					//if one is negative and one is positive
				else {
					x1=x2;
				}
			}
		}
		return x2;
	}
	/**
	 *create a new polynom that identical to the original polynom
	 *@return the new polynom 
	 */
	@Override
	public Polynom_able copy() {			//create a new polynom similar to the original
		Polynom NewPolynom= new Polynom();	//create a new polynom
		for (int i =0; i< list.size(); i++) {
			NewPolynom.add(new Monom (this.list.get(i)));	//add the monom to the new list
		}
		return NewPolynom;
	}
	/**
	 * the derivative of the polynom
	 * @return derivative of the polynom
	 */
	@Override
	public Polynom_able derivative() { 		//the derivative of the polynom
		Polynom_able newPoly= new Polynom();	//create a new polynom
		Iterator<Monom> it= list.iterator();	//point on the list
		while( it.hasNext()) {
			Monom m=it.next();
			m.derivative();						//do the derivative on the monom 
			if(m.get_coefficient()!=0)
				newPoly.add(m);

		}
		remove();				//remove zero
		return newPoly;
	}
	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps
	 * @param x0- the start point of the range
	 * @param x1- the end point of the range
	 * @param eps- the eps step value
	 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
	 */
	@Override
	public double area(double x0, double x1, double eps) {		//return the area of the polynom at the range it gets
		double sum= 0;

		while (x1-x0>0) {					//while x1 greater than x0
			if(f(x0)>0) {					//if x above the x-axis
				sum+= eps* f(x0);				//calculate the area of a rectangle

			}
			x0+= eps;							//do x0+eps 
		}
		return sum;
	}
	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps
	 * @param x0-the start point of the range
	 * @param x1-the end point of the range
	 * @param eps-the eps step value
	 * @return the approximated area under the x-axis above this Polynom and between the [x0,x1] range.
	 */
	public double areaUnderX(double x0, double x1, double eps) {		//return the area of the polynom at the range it gets
		double sum= 0;

		while (x1-x0>0) {					//while x1 greater than x0
			if(f(x0)<0) {					//if x under the x-axis
				sum+= eps* f(x0);				//calculate the area of a rectangle

			}
			x0+= eps;							//do x0+eps 
		}
		return sum;
	}
	/**
	 * @return an Iterator (of Monoms) over this Polynom
	 */
	@Override
	public Iterator<Monom> iteretor() {		//create a pointer to the polynoms monoms
		return list.iterator();
	}

	// ********** add your code below ***********

}
