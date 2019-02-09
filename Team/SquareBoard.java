package Team;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Square board
 * @author Star team
 *
 */
public class SquareBoard extends TemplateBoard
{   
    /**
     * Constructor with the square pits
     */
    public SquareBoard()
    {
        super();
        
        PitStyle[] mancalaStyles = this.getMancalas();
        PitStyle[][] pitStyles = this.getPits();
   
        for (int i = 0; i < DataModel.NO_OF_PLAYERS; i++)
        {
            for (int j = 0; j < DataModel.NO_OF_PITS; j++)
            {
                if (i % 2 == 0) //1st player
                    pitStyles[i][j] = new SquarePit(
                            (2 * GAP + MANCALA_WIDTH + ((DataModel.NO_OF_PITS - 1) - j) * (GAP + PIT_WIDTH)), GAP,
                            PIT_WIDTH, PIT_HEIGHT);
                else //2nd player
                    pitStyles[i][j] = new SquarePit((2 * GAP + MANCALA_WIDTH + j * (GAP + PIT_WIDTH)),
                            2 * GAP + PIT_HEIGHT, PIT_WIDTH, PIT_HEIGHT);
            }

            mancalaStyles[i] = new SquarePit(GAP + i
                    * (DataModel.NO_OF_PLAYERS * GAP + MANCALA_WIDTH + DataModel.NO_OF_PITS * (GAP + PIT_WIDTH) - GAP),
                    GAP, MANCALA_WIDTH, MANCALA_HEIGHT);
        }
    }
    

}
