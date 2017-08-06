import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Nav extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 573248957204985L;
	
	//Create the buttons and their labels
	private JButton run = new JButton("Start");
	private JButton pause = new JButton("Pause");
	private JButton reset = new JButton("Reset");
	
	Nav(){
	
		setLayout(new GridLayout(1,3,0,0));
		
		//All buttons colour, starting state and ActionCommands set
		
		run.setBackground(Color.GREEN); 
		//Set the size of the counter
		run.setFont(run.getFont().deriveFont(20.0f));
		run.setActionCommand("Start");
		run.addActionListener(this);
		
		pause.setBackground(Color.ORANGE);
		pause.setFont(pause.getFont().deriveFont(20.0f));
		//Set initial state to be disabled
		pause.setEnabled(false);
		pause.setActionCommand("Pause");
		pause.addActionListener(this);
		
		//Similar to above
		reset.setBackground(Color.RED);
		reset.setFont(reset.getFont().deriveFont(20.0f));
		reset.setEnabled(false); 
		reset.setActionCommand("Reset");
		reset.addActionListener(this);
		
		//Add the buttons to the panel
		add(run);
		add(pause);
		add(reset);
	
	}
	
	//Takes care of button presses
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Gets the current board state instantiated in the MainPanel class
		Board board = MainPanel.getBoard();
		//Gets current state of counter instantiated in the MainPanel class
		Counter counter = MainPanel.getCounter();
		
		if(e.getActionCommand().equals("Start")){
			//Once started disable start button and enable others.
			run.setEnabled(false); 
			pause.setEnabled(true); 
			reset.setEnabled(true); 
			//If board is empty make a random board
			if(board.checkIfBoardPopulated()==false){
				board.randomizeBoard();
			}
			//Start timer in Board class to start triggering events regardless
			board.getTimer().start();
		}
		if(e.getActionCommand().equals("Pause")){
			//Make pause button no longer selectable
			pause.setEnabled(false); 
			//Enable run button to start program again
			run.setEnabled(true); 
			//Stop timer in Board class from triggering events
			board.getTimer().stop(); 
		}
		if(e.getActionCommand().equals("Reset")){
			//Reset buttons to original states
			reset.setEnabled(false); 
			pause.setEnabled(false);
			run.setEnabled(true);
			//Resets the board to the default state
			board.reset();
			//Resets the counter at the bottom of the window
			counter.resetCount();
		}
	}
}
