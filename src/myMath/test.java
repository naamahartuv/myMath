package myMath;

public class test {

	public static void main(String[] args) {
		//***monom test***
		Monom m1= new Monom (3,2);
		Monom m2 = new Monom(m1);
		Monom m3= new Monom (4,2);
		
		System.out.println("m1: "+ m1);
		System.out.println("m2: "+ m2);
		System.out.println("f(x): "+ m1.f(3));
		m1.derivative();
		System.out.println("derivative m1: "+ m1);
		m2.add(m3);
		System.out.println("add: "+ m2);
		m1.multiply(m3);
		System.out.println("multiply: " + m1);
		Monom m = new Monom (0,2);
		System.out.println("monom m:"+m);
		m.derivative();
		
		//***polynom test***
		
		Polynom p1= new Polynom ("3x^2+ 12*x -4");
		Polynom p2= new Polynom ("x^3- x");
		Polynom p3= new Polynom ("3");
		Polynom p4= new Polynom ("");
		Polynom p5= new Polynom ("4x^3+ 2x -1");
		Polynom p6= new Polynom ("x^3+2x");
		Polynom p7= new Polynom("x-5");
		System.out.println("p7 root: "+p7.root(2, 8, 0.01));
		
		
		System.out.println();
		System.out.println("p1: "+ p1);
		System.out.println("p2: "+ p2);
		System.out.println("p3: "+ p3);
		System.out.println("p4: "+p4);
		
		p4=(Polynom) p1.copy();						//p4 is now 3x^2+12x-4
		System.out.println("copy p1 to p4: "+ p4);
		System.out.println("f(x) of p2: "+ p2.f(2));	
		p1.add(p2);									//p4 is now -4.0+11.0x+3.0x^2+x^3
		System.out.println("add: "+ p1);
		p2.add(m3);									//p2 is now -x+4.0x^2+x^3
		System.out.println(" add monom: "+p2);
		p5.substract(p6);							//p5 is now -1.0+3.0x^3
		System.out.println("substract: "+ p5);
		p6.multiply(p3);							//p6 is now 6.0x+3.0x^3
		System.out.println("multiply: "+ p6);
		System.out.println("equals: "+ p1.equals(p5));
		System.out.println("is zero: "+ p3.isZero());
		System.out.println("root: "+p1.root(-2, 5, 0.001));
		p5.derivative();						//p5 is now 9.0x^2
		System.out.println("derivative: "+p5);
		System.out.println("area p1: "+ p1.area(-3, 3, 0.0001));
		
		Polynom p8= new Polynom("8x^3-3x^2+3x-6");		
		System.out.println("areaUnderX p8:"+p8.areaUnderX(-5, 5, 0.001));		
	
		
		
	}

}
