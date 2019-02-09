package Team;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * TemplateBard class
 * @author Chuan
 *
 */
public class TemplateBoard
{
    public static final int MANCALA_WIDTH = 100;
    public static final int MANCALA_HEIGHT = 220;
    public static final int PIT_WIDTH = 100;
    public static final int PIT_HEIGHT = 100;
    public static final int GAP = 20;
    
    private PitStyle[] mancalaStyles;
    private PitStyle[][] pitStyles;
    
    private int selectedPit;
    
    /**
     * Constructor, initialize pit styles and mancala styles  
     */
    public TemplateBoard()
    {
        mancalaStyles = new PitStyle[2];
        pitStyles = new PitStyle[2][6];
    }
    
    /**
     * Get width of the board
     */
    public int getWidth()
    {
        return 2 * GAP + MANCALA_WIDTH * DataModel.NO_OF_PLAYERS + DataModel.NO_OF_PITS * (PIT_WIDTH + GAP) + GAP;
    }

    /**
     * Get height of the board
     */
    public int getHeight()
    {
        return 2 * GAP + MANCALA_HEIGHT;
    }

    /**
     * Get PitStyes of Mancalas
     */
    public PitStyle[] getMancalas()
    {
        return this.mancalaStyles;
    }

    /**
     * Get PitStyes of the Pits
     */
    public PitStyle[][] getPits()
    {
        return this.pitStyles;
    }

    /**
     * Generate stones for the pits from the model
     */
    
    public void generate(DataModel model)
    {
        selectedPit = model.getSelectedPit();
        for (int i = 0; i < DataModel.NO_OF_PLAYERS; i++)
        {
            for (int j = 0; j < DataModel.NO_OF_PITS; j++)
                pitStyles[i][j].setStones(model.getPitStones(i, j));
            
            mancalaStyles[i].setStones(model.getMancalaStones(i));
        }
    }

    /**
     * Get PitStyle of a pit at a position
     * @param player player number
     * @param pit pit number
     * @return PitStyle of the pit
     */
    public PitStyle getPit(int player, int pit)
    {
        return pitStyles[player][pit];
    }
    
    /**
     * Get PitStyle of the player's mancala
     * @param player player number
     * @return PitStyle of mancala
     */
    public PitStyle getMancala(int player)
    {
        return mancalaStyles[player];
    }
    
    /**
     * Get current position of the selected pit
     * @return
     */
    public int getSelectedPit()
    {
        return this.selectedPit;
    }
   
    /**
     * Draw the board with pits and mancalas style
     * @param g2 graphic 2D
     */
    public void draw(Graphics2D g2)
    {
        PitStyle[] m = getMancalas();
            m[0].draw(g2);
            m[1].drawRed(g2);
         
        for (int i = 0; i < DataModel.NO_OF_PLAYERS; i++)
        {
            for (int j = 0; j < DataModel.NO_OF_PITS; j++)
            {
                if (i % 2 == 0)
                    g2.drawString("    A" + (j + 1),
                            getPit(i, j).getX() + PIT_WIDTH / 4, getPit(i, j).getY() - GAP / 2);
                else
                    g2.drawString("    B" + (j + 1),
                            getPit(i, j).getX() + PIT_WIDTH / 4, getPit(i, j).getY() + PIT_HEIGHT + GAP);

                if (i == (int)(selectedPit/DataModel.NO_OF_PITS) && j == selectedPit % DataModel.NO_OF_PITS)
                {
                    g2.setColor(Color.BLUE);
                    getPit(i, j).draw(g2);
                    g2.setColor(Color.BLACK);
                }
                else
                    getPit(i, j).draw(g2);
            }
        }
        
        g2.drawString("Player A", getMancala(0).getX() + PIT_WIDTH / 8, GAP / 2);
        g2.drawString("Player B", getMancala(1).getX() + PIT_WIDTH / 4, getHeight());
    }
}
