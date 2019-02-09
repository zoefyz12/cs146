package Team;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Round Board
 * @author Star team
 *
 */
public class RoundBoard extends TemplateBoard
{     
    /**
     * Constructor, draw board with round pits
     */
    public RoundBoard()
    {   
        super();
        
        PitStyle[] mancalaStyles = this.getMancalas();
        PitStyle[][] pitStyles = this.getPits();
        
    	for (int i = 0; i < 2; i++)
        {
        	for (int j = 0; j < 6; j++)
        	{
        		if (i % 2 == 0) //1st player
        			pitStyles[i][j] = new RoundPit(
        					(2 * GAP + MANCALA_WIDTH + ((DataModel.NO_OF_PITS - 1) - j) * (GAP + PIT_WIDTH)), GAP,
        					PIT_WIDTH, PIT_HEIGHT);
        		else //2nd player
        			pitStyles[i][j] = new RoundPit((2 * GAP + MANCALA_WIDTH + j * (GAP + PIT_WIDTH)),
        					2 * GAP + PIT_HEIGHT, PIT_WIDTH, PIT_HEIGHT);
        	}

        	mancalaStyles[i] = new RoundPit(GAP + i
        			* (DataModel.NO_OF_PLAYERS * GAP + MANCALA_WIDTH + DataModel.NO_OF_PITS * (GAP + PIT_WIDTH) - GAP),
        			GAP, MANCALA_WIDTH, MANCALA_HEIGHT);
        }
    }
}
