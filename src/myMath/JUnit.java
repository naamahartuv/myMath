package myMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JUnit {

	//*****test polynom*****//
	@Test
	void testFx() {			//check Polynom's f(x) method
		Polynom p1=new Polynom("x+3x^2+ 12*x -4-0-18.5+x^2+2x^2");
		double p1test=p1.f(-2);
		assertEquals(-24.5, p1test);
	}
	@Test
	public void testAddPolynom() {	//check Polynom's addPolynom method
		Polynom p1=new Polynom("x+3x^2+ 12*x -4-0-18.5+x^2+2x^2");
		Polynom p2=new Polynom("x+3x^2+0.5");

		p1.add(p2);
		Polynom p1test=new Polynom("14x+9x^2-22");
		assertEquals(p1test.toString(), p1.toString());
	}
	@Test
	public void testAddMonom() {		//check Polynom's addMonom method
		Polynom p1= new Polynom ("x+3x^2+ 12*x -4-0-18.5+x^2+2x^2");
		Monom m1= new Monom (1,1);
		Monom m2= new Monom (3,2);
		Monom m3= new Monom (0.5,0);
		p1.add(m1);
		p1.add(m2);
		p1.add(m3);
		Polynom p1test= new Polynom ("14x+9x^2-22");
		assertEquals(p1test.toString(), p1.toString());
	}
	@Test
	public void testSubstract() {		//check Polynom's substract method
		Polynom p1= new Polynom ("x+3x^2+ 12*x -4-0-18.5+x^2+2x^2");
		Polynom p2=new Polynom ("10x-3x^3+0.5");
		p1.substract(p2);
		Polynom p1test= new Polynom ("3x+6x^2+3x^3-23");
		assertEquals(p1test.toString(), p1.toString());

	}
	@Test
	public void testMultiply() {		//check Polynom's multiply method
		Polynom p1= new Polynom ("x+3x^2+ 12*x -4-0-18.5+x^2+2x^2");
		Polynom p2=new Polynom ("10x-3x^3-0.5");
		p1.multiply(p2);
		Polynom p1test= new Polynom ("-39x^4-18x^5+127.5x^3+127x^2-231.5x+11.25");
		assertEquals(p1test.toString(), p1.toString());
	}
	@Test
	public void testEquals() {		//check Polynom's equals method
		Polynom p1= new Polynom ("x+3x^2+ 12*x -4-0-18.5+x^2+2x^2");
		Polynom p2=new Polynom ("10x-3x^3+0.5");
		assertFalse(p1.equals(p2));;
		Polynom p3= new Polynom ("x+3x^2+ 12*x -4-0-18.5+x^2+2x^2");
		Polynom p4= new Polynom ("x+3x^2+ 12*x -4-0-18.5+x^2+2x^2");
		assertTrue(p3.equals(p4));;
	}
	@Test
	public void testIsZero() {		//check Polynom's isZero method
		Polynom p1= new Polynom ("x+3x-5+0+4x^6");
		assertFalse(p1.isZero());
		Polynom p2= new Polynom("0");
		assertTrue(p2.isZero());;
		Polynom p3=new Polynom("");
		assertTrue(p3.isZero());
	}
	@Test
	public void testRoot() {		//check Polynom's root method
		Polynom p1= new Polynom ("x^2+20x+5");
		double xTest= p1.f(p1.root(-10, 10, 0.001));
		assertTrue( xTest<=0.001);;
	}
	@Test
	public void testCopy() {		//check Polynom's copy method
		Polynom p1= new Polynom ("x+3x^2+ 12*x -4-0-18.5+x^2+2x^2");
		Polynom p2=new Polynom ();
		p2=(Polynom)p1.copy();
		Polynom p1test= new Polynom ("x+3x^2+ 12*x -4-0-18.5+x^2+2x^2");
		assertEquals(p1test.toString(), p2.toString());
	}
	@Test
	public void testDerivative() {		//check Polynom's derivative method
		Polynom p1= new Polynom ("x-7");
		p1.derivative();
		Polynom p1test= new Polynom ("1");
		assertEquals(p1test.toString(), p1.toString());
	}
	@Test
	public void testArea() {		//check Polynom's area method
		Polynom p1= new Polynom ("x^2+3");
		double x=p1.area(-5, 5, 0.00001);
		assertTrue(113<=x&&114>=x);

		Polynom p2= new Polynom ("8x^3-3x^2+3x-6");
		double x2=p2.area(-1, 1, 0.00001);
		assertTrue(0.102<=x2&&0.103>=x2);
	}
	@Test
	public void testAreaUnder() {		//check Polynom's areaUnder method
		Polynom p1= new Polynom ("x^2+3");
		double x=p1.areaUnderX(-5, 5, 0.00001);
		assertTrue(x==0.0);

		Polynom p2= new Polynom ("8x^3-3x^2+3x-6");
		double x2=p2.areaUnderX(-1, 1, 0.00001);
		assertTrue(-14.11<=x2 && -14.10>=x2);
	}
	//*****test monom*****//
	@Test
	public void testMonomFx() {		//check Monom's f(x) method
		Monom m1 = new Monom(3, 2);
		double m1Test = m1.f(-2);
		assertEquals(12, m1Test);

		Monom m2 = new Monom(0, 2);
		double m2Test = m2.f(17.8);
		assertEquals(0, m2Test);

		Monom m3 = new Monom(1.7, 4);
		double m3Test = m3.f(10);
		assertEquals(17000, m3Test);
	}
	@Test
	public void testMonomDerivative() {		//check Monom's derivative method
		Monom m1 = new Monom(3, 2);
		m1.derivative();
		Monom m1Test= new Monom (6,1);
		assertEquals(m1.toString(), m1Test.toString());

		Monom m2 = new Monom(0, 2);
		m2.derivative();
		Monom m2Test= new Monom (0,0);
		assertEquals(m2Test.toString(), m2.toString());

	}
	@Test
	public void testMonomAdd() {		//check Monom's add method

		Monom m1 = new Monom(3, 2);
		Monom m2 = new Monom(1, 3);
		Monom m3 = new Monom(5, 2);
		m1.add(m3);
		try {						//in case the powers are different- check if it throw exception
			m1.add(m2);
			fail("Something is bad with the add function in Monom - different powers");
		}
		catch(RuntimeException myException) {
			//all ok
		}
		Monom m1Test= new Monom (8,2);
		assertEquals(m1.toString(), m1Test.toString());
	}
	@Test 
	void testMonomMultiply () {		//check Monom's multiply method
		Monom m1 = new Monom(3, 2);
		Monom m2 = new Monom(1, 2);
		m1.multiply(m2);
		Monom m1Test= new Monom (3,4);
		assertEquals(m1.toString(), m1Test.toString());
		Monom m4 = new Monom(3, 2);
		Monom m3 = new Monom(2, 3);
		m4.multiply(m3);
		Monom m4Test= new Monom (6,5);
		assertEquals(m4.toString(), m4Test.toString());
	}

}
