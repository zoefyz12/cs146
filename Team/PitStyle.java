package Team;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

/**
 * PitStyle interface
 * @author Chuan
 *
 */
public interface PitStyle
{
    public void setStones(int stones);    
    public int getStones();
    public void draw(Graphics2D g2);
    public void drawRed(Graphics2D g2);
    public int getX();
    public int getY();
    public int getWidth();
    public int getHeight();
    public boolean contains(Point2D p);
}
