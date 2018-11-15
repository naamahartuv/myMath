package myMath;
import java.awt.Color;
import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

/**
 * in this class we build a graph for the polynom that we get. we find the maxima and minima, 
 * and then we draw the graph, and add to it marking of the maxima and minima.
 * @author yael hava and naama hartuv
 *
 */


public class LinePlotTest extends JFrame {
    public LinePlotTest() {
    		//draw the frame r
        setDefaultCloseOperation(EXIT_ON_CLOSE);		
        setSize(800, 800);
        
        	//all points in graph
       DataTable data = new DataTable(Double.class, Double.class);
       		//min and max points
       DataTable dataDer = new DataTable(Double.class, Double.class);

       
       Polynom p = new Polynom("0.2x^4-1.5x^3+3.0x^2-x-5");
       Polynom der = (Polynom) p.copy().derivative();	//the derivative of the polynom
       System.out.println("the area under x-axis, above the polynom in this range is: " + p.areaUnderX(-2, 6, 0.01));
       
       for (double x = -2.0; x <= 6.0; x+=0.25) {		//create points and add them to the data table
            data.add(x, p.f(x));
            
            if(der.f(x) >= 0 && der.f(x) <= 0.5) {		//if the point is max or min 
            	dataDer.add(x, p.f(x));					//add it to the data table of derive points
            }
        }
        
       		//draw the points on the graph
        XYPlot plot = new XYPlot(data, dataDer);		
        getContentPane().add(new InteractivePanel(plot));
       
        	//draw the lines between the dots
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        
        	
        Color color = new Color(0.0f, 0.3f, 1.0f);		//blue color
        Color color2 = new Color(0.6f, 0.0f, 0.0f);		//red color
        
        plot.getPointRenderers(data).get(0).setColor(color);		//paint the dots of the function 
        plot.getLineRenderers(data).get(0).setColor(color);			//paint the lines between the dots 
        plot.getPointRenderers(dataDer).get(0).setColor(color2);	//paint the dots of the function that are max and min 	
        
        
    }

    public static void main(String[] args) {			//do the operation
        LinePlotTest frame = new LinePlotTest();		
        frame.setVisible(true);
        
    }
}