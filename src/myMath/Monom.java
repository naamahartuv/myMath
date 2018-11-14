
package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author  Yael Hava and Naama Hartuv
 *
 */
public class Monom implements function{
	/**
	 * create the monom with the coefficient and power
	 * @param a- coefficient value
	 * @param b- power value
	 */
	public Monom(double a, int b){	//create the monom with the coefficient and power
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * copy constractor
	 * @param ot- new monom that gets the original monoms value
	 */
	public Monom(Monom ot) {		//copy constractor
		this(ot.get_coefficient(), ot.get_power());
	}
	/**
	 * function that calculate the function value at x
	 * @param x- the value of x
	 * @return the return the function value at x  
	 */
	@Override
	public double f(double x) {		//return the function value at x
		return this._coefficient*Math.pow(x, this._power);
	}
	/**
	 *the derivative of the monom 
	 */
	public void derivative () {		//return the derivative of the monom
		if (get_power()==0) set_coefficient(0);
		else {
			this.set_coefficient(get_coefficient()*get_power());
			this.set_power(get_power()-1);
		}
		
	}
	/**
	 * addition between two monoms that have the same power 
	 * @param m- the monom that we add to the original monom
	 */
	public void add(Monom m) {		//add monom to another monom
		if (get_power()!=m.get_power()) throw new RuntimeException();
		else
			this.set_coefficient(get_coefficient()+m.get_coefficient());
		
	}
	/**
	 * multiply monom by another monom
	 * @param m- monom that multiply with the original monom
	 */
	public void multiply (Monom m) { 		//multiply monom by another monom
		this.set_coefficient(get_coefficient()*m.get_coefficient());	
		this.set_power(get_power()+m.get_power());
	}
	/**
	 * prints the monom as a string
	 */
	public String toString () {				//prints the monom as a string
		if(this._coefficient==1) {
			if(this._power==0) return "" +this._coefficient;
			else if (this._power==1) return "x";
			else return "x^" + this._power;
		}
		else if(this._coefficient==-1) {
			if(this._power==0) return "" +this._coefficient;
			else if (this._power==1) return "-x";
			else return "-x^" + this._power;
		}
		else {
			if(this._power==0) return "" +this._coefficient;
			else if( this._coefficient==0) return ""+this._coefficient;
			else if (this._power==1) return this._coefficient + "x";
		}
		return  this._coefficient + "x^" + this._power;
	}
	
	/**
	 * getter of power
	 * @return this power
	 */
	public int get_power() {			//getter of power
		
		return this._power;
	}
	/**
	 * getter of coefficient
	 * @return this coefficient
	 */
	public double get_coefficient() {	//getter of coefficient
		
		return this._coefficient;
	}
	/**
	 * setter of coefficient
	 * @param a- the new value of the coefficient
	 */
	public void set_coefficient(double a){	//setter of coefficient
		this._coefficient = a;
	}
	/**
	 * setter of power
	 * @param p-the new value of the power
	 */
	public void set_power(int p) {			//setter of power
		this._power = p;
	}
	
	private double _coefficient; 
	private int _power;
	 
}
