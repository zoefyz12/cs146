package Team;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/**
 * Main frame display game board, game options, game state, messages
 * @author Chuan
 *
 */
public class MainFrame extends JFrame
{
    private static final long serialVersionUID = 2269971701250845501L;
    public static final int FRAME_LOCATION_X = 500;
    public static final int FRAME_LOCATION_Y = 100;
    public static final int FRAME_WIDTH = 1000;
    public static final int FRAME_HEIGHT = 480;
    
    public static final int MESSAGES_WIDTH = 20;
    public static final int MESSAGES_HEIGHT = 8;
    
    private int stoneNumber;
    
    private JTextArea messagesTextArea;

    /**
     * Constructor
     */
    public MainFrame()
    {
        setTitle("Mancala Game");
        setLocation(FRAME_LOCATION_X, FRAME_LOCATION_Y);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setResizable(false);
        setLayout(new BorderLayout());
        final DataModel model = new DataModel();
        final BoardPanel board = new BoardPanel(model);

        final JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        
        
        final JLabel stateLabel = new JLabel("");
        northPanel.add(stateLabel, BorderLayout.WEST);
        
        final JPanel actionPanel = new JPanel();
        
        final JLabel stoneLabel = new JLabel("Enter number of starting stones: "); 
        final JTextField stoneField = new JTextField(); // where the number of stone is inputed
        final JButton startButton = new JButton("Start");
        final JButton undoButton = new JButton("Undo");
        final JButton resetButton = new JButton("Reset");
        
        messagesTextArea = new JTextArea(MESSAGES_HEIGHT, MESSAGES_WIDTH);
        messagesTextArea.setEditable(false);
        final JScrollPane messagesPane = new JScrollPane(messagesTextArea);
        
        stoneLabel.setVisible(false);
        stoneField.setVisible(false);
        stoneField.setPreferredSize(new Dimension(35, 26));
        startButton.setVisible(false);
        
        undoButton.setEnabled(false);
        resetButton.setEnabled(false);

        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    stoneNumber = Integer.parseInt(stoneField.getText());
                } 
                catch (Exception ex)
                {
                    stoneNumber = 0;
                }
                
                stoneField.setText("");
                
                if (stoneNumber == 3 || stoneNumber == 4)
                {                    
                    model.setStartingStones(stoneNumber);
                    model.setGameState(DataModel.GameState.STARTED);
                    
                    model.addMessage("\nGame started!!!");
                    model.addMessage("Player " + (char)('A' + model.getPlayerTurn()) + "'s Turn");
                }
                else
                {
                    model.addMessage("Wrong input number. Enter either 3 or 4.");
                }
            }
        });       
        
        undoButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                model.undo();
            }
        });
        
        resetButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                model.reset();
                
                model.addMessage("\nGame Reset!");
                model.addMessage("Please select the board style.");
            }
        });
        
        actionPanel.add(stoneLabel);
        actionPanel.add(stoneField);
        actionPanel.add(startButton);
        actionPanel.add(undoButton);
        actionPanel.add(resetButton);
             
        northPanel.add(actionPanel, BorderLayout.EAST);

        //Attach listeners
        model.attach(board);       
        model.attach(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                messagesTextArea.setText(model.getMessages());
                
                DataModel.GameState state = model.getGameState();
                
                stoneLabel.setVisible(state == DataModel.GameState.SELECT_BOARD);
                stoneField.setVisible(state == DataModel.GameState.SELECT_BOARD);
                startButton.setVisible(state == DataModel.GameState.SELECT_BOARD);
                
                resetButton.setEnabled(state == DataModel.GameState.SELECT_BOARD
                        || state == DataModel.GameState.STARTED
                        || state == DataModel.GameState.END);
                
                undoButton.setEnabled(model.canUndo());
                
                if(state == DataModel.GameState.RESET
                    || state == DataModel.GameState.SELECT_BOARD)
                    stateLabel.setText("Game Options");
                else if(state == DataModel.GameState.STARTED)
                    stateLabel.setText("Player " + (char)('A' + model.getPlayerTurn()) + "'s Turn");
                
                
                if(state == DataModel.GameState.SELECT_BOARD)
                    stoneField.requestFocus();

            }
         });      

        add(northPanel, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER);
        add(messagesPane, BorderLayout.SOUTH);

        //Welcome message
        model.addMessage("Welcome to Mancala Game!!!");
        model.addMessage("Please select the board style.");
        
        setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE));
        setVisible(true);
    }     
}
