package Team;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * BoardPanel class to display the game board with selected style
 * @author Chuan
 *
 */
public class BoardPanel extends JPanel implements ChangeListener
{
    private static final long serialVersionUID = -6606964881790175960L;
    
    private ArrayList<JButton> styleButtons;
    private TemplateBoard board;
    private DataModel model;
    
    public BoardPanel(DataModel model)
    {
        this.styleButtons = new ArrayList<JButton>();
        this.model = model;
        
        addStyleSelection("Round", new RoundBoard());
        addStyleSelection("Square", new SquareBoard());
        
        //Add mouse listener to check if a player click on a PIT
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent me)
            {
                if (board != null && model.isStarted())
                {
                    PitStyle[][] pits = board.getPits();
                    for (int i = 0; i < pits.length; i++)
                    {
                        for (int j = 0; j < pits[i].length; j++)
                        {
                            Point2D p = me.getPoint();
                            if (pits[i][j].contains(p))
                            {
                                model.move(i, j);
                            }
                        }
                    }
                }
            }
        });
    }
    
    /**
     * Displays graphics
     * @param g graphic
     */
    @Override
    public void paintComponent(Graphics g)
    {
        if (model.getGameState() != DataModel.GameState.RESET)
        {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            board.generate(model);
            board.draw(g2);
        }
    }
  
    /**
     * Update the view notified by data model. 
     */
    @Override
    public void stateChanged(ChangeEvent e)
    {
        if(model.getGameState() == DataModel.GameState.RESET)
            showStyleOptions(true);
        else showStyleOptions(false);
        
        repaint();
    }

    /**
     * Add a button for the BoardStyle.
     * @param name name of style
     * @param s BoardStyle
     */
    private void addStyleSelection(String name, TemplateBoard s) 
    {
       JButton button = new JButton(name);
       button.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             setStyle(s);
             showStyleOptions(false);
             
             model.setGameState(DataModel.GameState.SELECT_BOARD);
             
             model.addMessage(name + " board has been selected.");
             model.addMessage("Enter the number of starting stones to start the game.");
             
             repaint();
          }
       });
       
       styleButtons.add(button);
       add(button);
    }

    /**
     * Shows all style options.
     * @param show true if show
     */
    public void showStyleOptions(Boolean show) {
       for (JButton b : styleButtons) {
          b.setVisible(show);
       }
    }

    /**
     * Set style to specified s.
     * @param s style
     */
    public void setStyle(TemplateBoard s) {
       this.board = s;
    }
}
