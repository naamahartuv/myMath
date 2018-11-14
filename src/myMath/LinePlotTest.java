package myMath;

import java.awt.Color;

import javax.swing.JFrame;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;
/**
 * in this class we build a graph for the polynom that we get. we find the maxima and minima,
 *  and then we draw the  graph and the point's, and add to it marking of the maxima and minima.
 * @author Yael Hava and Naama Hartuv
 *
 */
public class LinePlotTest extends JFrame {

	public static void main(String[] args) {		//do the operation
        LinePlotTest frame = new LinePlotTest();	
        frame.setVisible(true);
	}
	
    public LinePlotTest() {
    	//draw the frame 
        setDefaultCloseOperation(EXIT_ON_CLOSE);	
        setSize(900, 900);
        
        // All points in graph
        DataTable data = new DataTable(Double.class, Double.class);
        // Min and Max points
        DataTable dataDer = new DataTable(Double.class, Double.class);

        Polynom p = new Polynom("0.2x^4 -1.5x^3 + 3x^2 -x -5");
        Polynom der = (Polynom) p.copy().derivative();		//the derivative of the polynom
        
        //create points and add them to the dataTable 
        for (double x = -2.0; x <= 6.0; x+=0.25) {
            data.add(x, p.f(x));
            
            if (der.f(x) >= 0 && der.f(x) <= 0.5) {	//if the point is max or mun
            	dataDer.add(x,p.f(x));				//add it to the dataTable of the derive
            	
            }
        }
        
        //draw the points on the graph
        XYPlot plot = new XYPlot(data, dataDer);
        getContentPane().add(new InteractivePanel(plot));

        //draw the lines between the points
        LineRenderer lines = new DefaultLineRenderer2D();
        plot.setLineRenderers(data, lines);
        
        Color color = new Color(0.0f, 0.3f, 1.0f);	// blue color
        Color color2 = new Color(0.6f, 0.0f, 0.0f);	// red color

        plot.getPointRenderers(data).get(0).setColor(color);	//paint the dots of the function
        plot.getLineRenderers(data).get(0).setColor(color);		//paint the lines of the function
        
        plot.getPointRenderers(dataDer).get(0).setColor(color2);	//paint the maxima and minima points

    }
}
