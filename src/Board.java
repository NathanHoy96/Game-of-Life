import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener{

	//Variables
	private static final long serialVersionUID = 2324324543246L;
	private int column,row;
	private  JButton[][] cells; 
	private Timer timer;

	Board(int columnInput, int rowInput, int speedInput){
		
		//This is a comment to test Git again!
		column = columnInput;
		row = rowInput;
		
		cells = new JButton[row][column]; 
		
		//Set panel to GridLayout to hold grid of cells of size row,column
		setLayout(new GridLayout(column,row,0,0));
		
		//Populate all the buttons in JButton[][] with new buttons and default settings
		for(int i = 0; i<row;i++){
			for(int j=0;j<column;j++){
				
				cells[i][j] = new JButton(); 
				cells[i][j].setBackground(Color.WHITE); 
				cells[i][j].setPreferredSize(new Dimension(20,20));
				cells[i][j].setActionCommand("Clicked");
				cells[i][j].addActionListener(this);
				//Add cell to GridLayout
				add(cells[i][j]); 
			}
		}
		//Create new timer to trigger every second to trigger events with no initial delay
		timer = new Timer(1100-(speedInput*100), this); 
		timer.setActionCommand("Timer");
		timer.setInitialDelay(0); 
		
	}
	
	//Getters and setters to be used by retrieve current state of board and timer
	public JButton[][] getCells() {
		return cells;
	}
	public void setCells(JButton[][] cells) {
		this.cells = cells;
	}
	
	public Timer getTimer() {
		return timer;
	}

	public int getColumn() {
		return column;
	}
	public int getRow() {
		return row;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Clicked")) {
			/*
			 * Get source of "Clicked" event and use selectedCells 
			 * method to change colour and state of button
			 */
			selectedCells((JButton) e.getSource());
		}
		if(e.getActionCommand().equals("Timer")){
			//Increment the current count
			MainPanel.getCounter().incrementCount();
			//Create new board and set cells to new board state
			NewBoard newBoard = new NewBoard();
			setCells(newBoard.getNewBoard());
			
		}
	}
	
	//Loop through all cells and reset to default colour and state
	public void reset(){
		
		for(int i = 0; i<row;i++){
			for(int j=0;j<column;j++){
				cells[i][j].setSelected(false); 
				cells[i][j].setBackground(Color.WHITE);
			}
		}
		//Stop timer from triggering events upon reset
		timer.stop();
	}
	
	//Set cell colour and state if clicked depending on previous colour and state
	private void selectedCells(JButton cell){

		if(cell.isSelected()==false){
			cell.setBackground(Color.BLUE);
			cell.setSelected(true);
		}
		else{
			cell.setBackground(Color.WHITE);
			cell.setSelected(false);
		}
	}
	
	//Loop through board and sets random colour
	public void randomizeBoard(){
		
		for(int i = 0; i<row;i++){
			for(int j=0;j<column;j++){
				
				Random cellRandom = new Random();
				//Set button selected status randomly
				cells[i][j].setSelected(cellRandom.nextBoolean());
				//If the random selection results in true, make button blue
				if(cells[i][j].isSelected()==true){
					cells[i][j].setBackground(Color.BLUE);
				}	
			}
		}
	}
	
	//Loops through board, if any cell is found to be alive, returns true, else false
	public boolean checkIfBoardPopulated(){

		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				if(cells[i][j].isSelected()==true){
					return true;
				}
			}
		}
		return false;
	}
}