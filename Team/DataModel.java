package Team;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * DataModel class
 * @author Star team
 *
 */
public class DataModel
{
    public static enum GameState {
        RESET, SELECT_BOARD, SET_STONES, STARTED, END 
    } 
    
    public static final int NO_OF_PLAYERS = 2;
    public static final int NO_OF_PITS = 6;
    
    private ArrayList<ChangeListener> listeners;
    
    //current state
    private int[][] playerPit;
    private int[] playerMancala;
    
    //keep track previous state
    private int[][] previousPit;
    private int[] previousMancala;
    
    //keep track number of undo of each players
    private int[] undoCount;

    private int playerTurn;  //current player
    private boolean undoState;  //track the state of undo button
    
    private GameState gameState;  //keep track of the state of the game
    
    private int selectedPit;
       
    private ArrayList<String> messages; //messages to show on the JTextArea
    
    /**
     * Constructor method
     */
    public DataModel()
    {
        listeners = new ArrayList<ChangeListener>();
        messages = new ArrayList<String>();
        
        //initialize members
        reset();
    }
    
    /**
     * Reset the state of the game to game setup.
     */
    public void reset() {
        gameState = GameState.RESET;
        
        playerMancala = new int[NO_OF_PLAYERS];
        playerPit = new int[NO_OF_PLAYERS][NO_OF_PITS];
        
        previousMancala = new int[NO_OF_PLAYERS];
        previousPit = new int[NO_OF_PLAYERS][NO_OF_PITS];
        
        playerTurn = 0;  //0: 1st player | 1: 2nd player
        undoState = true;
        
        undoCount = new int[NO_OF_PLAYERS];
        selectedPit = -1; //nothing selected yet
        
        notifyListeners();
    }
    
    /**
     * Get current state of the game
     * @return GameState
     */
    public GameState getGameState()
    {
        return this.gameState;
    }
    
    /**
     * Set game state
     * @param s GameState to set
     */
    public void setGameState(GameState s)
    {
        this.gameState = s;
        this.notifyListeners();
    }
    
    /**
     * Get player turn number
     * @return player number
     */
    public int getPlayerTurn()
    {
        return playerTurn;
    }

    /**
     * Set player turn
     * @param playerTurn player number
     */
    public void setPlayerTurn(int playerTurn)
    {
        this.playerTurn = playerTurn;
        this.notifyListeners();
    }

    /**
     * Check if the game is started
     * @return true if game started, false if game in the setting mode or has a winner
     */
    public boolean isStarted()
    {
        return this.gameState == GameState.STARTED;
    }

    /**
     * Get selected pit number
     * @return the number of selected pit
     */
    public int getSelectedPit()
    {
        return selectedPit;
    }

    /**
     * Set selected pit
     * @param selectedPit the new selected pit position
     */
    public void setSelectedPit(int selectedPit)
    {
        this.selectedPit = selectedPit;
        this.notifyListeners();
    }
    
    /**
     * Add message to display in the game message/log
     * @param message
     */
    public void addMessage(String message)
    {
        this.messages.add(message);
        this.notifyListeners();
    }
    
    /**
     * Get messages in text format
     * @return messages in text
     */
    public String getMessages()
    {
        String str = "";
        for (String s : this.messages) 
            str += s + "\n";
        
        return str;
    }
    
    /**
     * Sets the starting stones of the game and officially starts the game.
     * @param stones starting stones
     */
    public void setStartingStones(int stones)
    {
        for (int i = 0; i < NO_OF_PLAYERS; i++)
        {
            for (int j = 0; j < NO_OF_PITS; j++)
            {
                playerPit[i][j] = stones;
                previousPit[i][j] = stones;
            }
        }
       
        setGameState(GameState.STARTED);
       
        //notify listeners for the changes
        notifyListeners();
    }
    
    /**
     * Get the number of stones at a pit position
     * @param player
     * @param pit
     * @return
     */
    public int getPitStones(int player, int pit)
    {
        if (player >= NO_OF_PLAYERS || pit >= NO_OF_PITS)
            return -1;
        int number = playerPit[player][pit];
       
        return number;
    }
    
    /**
     * Get the number of stones in Mancala of the player
     * @param player
     * @return
     */
    public int getMancalaStones(int player)
    {
        if (player >= NO_OF_PLAYERS)
            return -1;
        
        return playerMancala[player];
    }
    
    
    /**
     * Copy state of pits to current state to previous state.
     */
    private void saveState()
    {
        for (int i = 0; i < NO_OF_PLAYERS; i++)
        {
            for (int j = 0; j < NO_OF_PITS; j++)
                    previousPit[i][j] = playerPit[i][j];
            
            previousMancala[i] = playerMancala[i];
        }
    }
    
    /**
     * Restore the state from previous state.
     */
    private void restoreState()
    {
        for (int i = 0; i < NO_OF_PLAYERS; i++)
        {
            for (int j = 0; j < NO_OF_PITS; j++)              
                    playerPit[i][j] = previousPit[i][j];
       
            playerMancala[i] = previousMancala[i];
        }
    }
    
    /**
     * Set current undo state
     * @param state state of the undo action
     */
    public void setUndoState(boolean state)
    {
        this.undoState = state;
        this.notifyListeners();
    }
    
    /**
     * Check if user can undo at current state
     * @return if user can undo
     */
    public boolean canUndo()
    {
        if(this.gameState != GameState.STARTED) return false;
        if(this.undoState) return false;
        else return canUndo(this.playerTurn);
    }
    
    
    /**
     * Check if current player can undo
     * @param playerTurn
     * @return
     */
    public boolean canUndo(int player)
    {        
        if (undoCount[player] < 3)
        	return true;
        else
        	return false;
    }
    
    /**
     * undo what is done
     * @param playerTurn
     */ 
    public void undo()
    {
        if(canUndo())
        {
            undoCount[playerTurn]++;
            int times = undoCount[playerTurn];
            
            this.restoreState();
            
            this.switchPlayer();
            this.selectedPit = -1;
            
            setUndoState(true);

            addMessage("Player " + (char)('A' + playerTurn) + " undo " + times + " time(s)!");
        }           
    }
   
    /**
     * Check for winner.
     */
    private boolean checkWinner()
    {
        for (int i = 0; i < NO_OF_PLAYERS; i++)
        {
            int count = 0;
            for (Integer p : playerPit[i])
            {
                count += p;
                if(count > 0) break;
            }
           
            if(count == 0) return true;
        }

        return false;
    }

    /**
     * Get the winner.
     * @return winner player number, -1 if tie, -2 if no winner
     */
    private int getWinner()
    {
        if(checkWinner())
        {
            //move all leftover stones to users' mancalar
            for (int i = 0; i < NO_OF_PLAYERS; i++)
            {
                for (int j = 0; j < NO_OF_PITS; j++)
                {
                    playerMancala[i] += playerPit[i][j];
                    playerPit[i][j] = 0;
                }
            }
            
            int winner = 0;
            for (int i = 1; i < NO_OF_PLAYERS; i++)
            {
                if (playerMancala[i] > playerMancala[winner])
                    winner = i;
                else if (playerMancala[i] == playerMancala[winner])
                    winner = -1;
            }
            
            return winner;
        }
        
        return -2;
    }   
    
    /**
     * Switch the player
     */
    private void switchPlayer() 
    {
        this.setPlayerTurn(playerTurn == 0 ? 1 : 0);
    }
    
    /**
     * Move stones at selected pit, follow the game rules
     * @param player
     * @param pit
     */
    public void move(int playerTurn, int pit)
    {
        if (this.playerTurn != playerTurn) {
           addMessage("Can't move! Pick a pit on your side.");
           return;
        }
        
        int stones = playerPit[playerTurn][pit];
        
        if(stones == 0)
        {
            addMessage("Can't move! Pick a non-empty pit on your side.");
            return;
        }
        
        
        this.saveState();
        this.setUndoState(false);
        
        addMessage("Pit " + (char)('A' + playerTurn) + (pit + 1)  + " selected.");
        
        this.selectedPit = pit + playerTurn * NO_OF_PITS;
        
        playerPit[playerTurn][pit] = 0; //Empty the pit
        
        pit++;
        int i = playerTurn;
        while (stones > 0)
        {
            for (int j = pit; j < playerPit[i].length; j++)
            {
                playerPit[i][j]++;
                stones--;
                
                if (stones == 0)
                {
                    if (playerPit[i][j] == 1 && i == this.playerTurn) 
                    {
                       playerMancala[i] += playerPit[i][j];
                       playerPit[i][j] = 0;
                       int opponent = i == 0 ? 1 : 0;
                       playerMancala[i] += playerPit[opponent][playerPit[i].length - j - 1];
                       playerPit[opponent][playerPit[i].length - j - 1] = 0;
                       
                       addMessage("The last stone dropped on empty pit, captured the stones.");
                    }
                    
                    if(checkWinner())
                    {
                        int winner = getWinner();
                        
                        if(winner == -1)
                            addMessage("\nIt's a tie!!!!");
                        else
                            addMessage("\nPlayer " + (char)('A' + winner) + " win!!!");
                        
                        this.setGameState(GameState.END);
                    }
                    else
                    {
                        switchPlayer();
                    }
                      
                    return;
                }
            }

            //put in user's mancala, but not the other side
            if (i == this.playerTurn)
            {
                playerMancala[i]++;
                stones--;
                
                if(stones == 0)
                {
                    if(checkWinner())
                    {
                        int winner = getWinner();
                        
                        if(winner == -1)
                            addMessage("\nIt's a tie!!!!");
                        else
                            addMessage("\nPlayer " + (char)('A' + winner) + " win!!!");
                        
                        this.setGameState(GameState.END);
                    }
                    else
                    {
                        addMessage("The last stone dropped in the player's mancala. Get a free turn!!!");
                    }
                }
            }
            
            //Swap to the other player's side
            if(i < playerPit.length - 1) i++;
            else i = 0;
            
            pit = 0; //start from the first pit
        }        
    }
    
    /**
    * Attach a listener to the Model
    * @param c the listener
    */
    public void attach(ChangeListener c)
    {
        listeners.add(c);
    }
    
    /**
     * Notify all listeners of change
     */
    private void notifyListeners() 
    {
       for (ChangeListener l : listeners) {
          l.stateChanged(new ChangeEvent(this));
       }
    }   
}