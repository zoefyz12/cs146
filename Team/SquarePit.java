package Team;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/**
 * SquarePit class
 * @author Star team
 *
 */
public class SquarePit implements PitStyle
{	
    public static final int STONE_WIDTH = 7;
    public static final int STONE_HEIGHT = 7;
    public Shape shape;
    public int stones;
    
    /**
     * Constructor 
     * @param x x position
     * @param y y position
     * @param w width of the pit
     * @param h height of the pit
     */
    public SquarePit(int x, int y, int w, int h)
    {
        shape = new Rectangle2D.Double(x, y, w, h);
    }
    
  
    
    /**
     * Set number of stones in the pit
     */
    @Override
    public void setStones(int stones)
    {
        this.stones = stones;
    }

    /**
     * Set number of stones in the pit
     */
    @Override
    public int getStones()
    {
        return this.stones;
    }

    /**
     * Draw a string in a center
     */
    public void drawString(Graphics g, Rectangle r, String s, Font font)
    {
        FontRenderContext frc = new FontRenderContext(null, true, true);

        Rectangle2D r2D = font.getStringBounds(s, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (r.width / 2) - (rWidth / 2) - rX;
        int b = (r.height / 2) - (rHeight / 2) - rY + 3; //adjust to the center

        g.setFont(font);
        g.drawString(s, r.x + a, r.y + b);
    }

    @Override
    public int getX()
    {
        return (int) shape.getBounds2D().getX();
    }

    @Override
    public int getY()
    {
        return (int) shape.getBounds2D().getY();
    }

    @Override
    public int getWidth()
    {
        return (int) shape.getBounds2D().getWidth();
    }

    @Override
    public int getHeight()
    {
        return (int) shape.getBounds2D().getHeight();
    }

    @Override
    public boolean contains(Point2D p)
    {
        return shape.contains(p);
    }
    
    /**
     * Draw square pit with correct number of stones
     */
    @Override
    public void draw(Graphics2D g2)
    {
    	g2.draw(shape);
        
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        drawString(g2, shape.getBounds(), "" + this.stones, font);

        int maxX = getWidth() / 8;
        int maxY = getHeight() / 8;

        int stones = getStones();

        g2.setColor(Color.BLUE);

        int count = 0;
        int maxStones = 8;
        while(count < stones) //draw stones in circle
        {
            int remain = stones - count;
            if(remain <= maxStones) maxStones = remain;
            
            for (int j = 0; j < maxStones; j++)
            {
                g2.fill(new Rectangle2D.Double(
                        getX() + (getWidth() / 2) + (maxX * Math.cos((j * (360 / maxStones)) * Math.PI / (360 / 2)))
                                - (STONE_WIDTH / 2),
                        getY() + (getHeight() / 2) + (maxY * Math.sin((j * (360 / maxStones)) * Math.PI / (360 / 2)))
                                - (STONE_HEIGHT / 2),
                        STONE_WIDTH, STONE_HEIGHT));
                count++;
            }
            
            maxStones += 12;
            maxX += (getWidth() / 8);
            maxY += (getHeight() / 8);
        }

        g2.setColor(Color.BLACK);
    }
    public void drawRed(Graphics2D g2)
    {
    	g2.draw(shape);
        
        Font font = new Font("TimesRoman", Font.PLAIN, 14);
        drawString(g2, shape.getBounds(), "" + this.stones, font);

        int maxX = getWidth() / 8;
        int maxY = getHeight() / 8;

        int stones = getStones();

        g2.setColor(Color.RED);

        int count = 0;
        int maxStones = 8;
        while(count < stones) //draw stones in circle
        {
            int remain = stones - count;
            if(remain <= maxStones) maxStones = remain;
            
            for (int j = 0; j < maxStones; j++)
            {
                g2.fill(new Rectangle2D.Double(
                        getX() + (getWidth() / 2) + (maxX * Math.cos((j * (360 / maxStones)) * Math.PI / (360 / 2)))
                                - (STONE_WIDTH / 2),
                        getY() + (getHeight() / 2) + (maxY * Math.sin((j * (360 / maxStones)) * Math.PI / (360 / 2)))
                                - (STONE_HEIGHT / 2),
                        STONE_WIDTH, STONE_HEIGHT));
                count++;
            }
            
            maxStones += 12;
            maxX += (getWidth() / 8);
            maxY += (getHeight() / 8);
        }

        g2.setColor(Color.BLACK);
    }
}
