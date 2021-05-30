import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

/**
 * the code is documentation enough
 */
public class Picture extends Canvas {
	
	private static final long serialVersionUID = 760394220697809047L;
	
	private Map map;
	
	public Picture() {
		super();
	}
	
	public void setMap(int width, int height, double prob) {
	    map = new Map(width, height, prob);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		if(map != null) map.show(g);
	}
	
	public Map getMap() {
	    return map;
	}
}