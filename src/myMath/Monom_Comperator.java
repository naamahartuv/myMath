package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> {

	/**
	 * compare the monoms by power
	 * @param m1- monom that comper to m2
	 * @param m2- monom that comper to m1
	 */
	
	public int compare (Monom m1, Monom m2) {
		if (m1.get_power()>m2.get_power()) return 1;
		if (m1.get_power()<m2.get_power()) return -1;
		else
			return 0;
	}

}
