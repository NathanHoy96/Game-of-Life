import java.awt.Color;

import javax.swing.JButton;

public class NewBoard {

	private Board board = MainPanel.getBoard();
	private JButton[][] cells = board.getCells();
	private int row = board.getRow(), column = board.getColumn();
	private boolean newCells[][] = new boolean[row][column];
	
	NewBoard(){
		//Call methods to create the new board
		checkNeighbours();
		createNewBoard();
	}
	
	public JButton[][] getNewBoard(){
		return cells;
	}

	//Checks how many neighbours each cell has (wraps around)
	private void checkNeighbours(){
		
		for(int i = 0; i<row;i++){
			for(int j=0;j<column;j++){
				//Reset neighbours count to 0 for each iteration
				int neighbours=0;
				//Loop through the  
				for(int x = -1; x<2;x++){
					for(int y=-1;y<2;y++){
						
						if(x==0 && y==0);
						
						else if(cells[(i+x+column)%column][(j+y+row)%row].isSelected()==true){
							neighbours++;
						}
					}
				}
				/*
				 * Store new cell in newCells boolean array, setCellStatus checks rules for cell life/death
				 * We need a separate array as otherwise as program loops through, previous cells would have 
				 * changed before other cells have been checked against the rules. Need board to change as one.
				 */
				newCells[i][j] = setCellStatus(cells[i][j],newCells[i][j],neighbours);
			}
		}
	
	}
	
	//Sets what the current cell should be for the new board depending on Game of Life rules
	private boolean setCellStatus(JButton cell, boolean newCell, int neighbours){

		if(cell.isSelected()==true && ((neighbours < 2) || (neighbours > 3))) newCell=false;

		else if(cell.isSelected()==false && (neighbours==3)) newCell=true;

		else if(cell.isSelected()==true) newCell=true;

		else{
			newCell=false; //Otherwise remain false
		}
		return newCell; //Return outcome to newCells index
	}
	
	//Creates and sets the new board based on boolean array newCells
	private void createNewBoard(){
		
		for(int i = 0; i<row;i++){
			for(int j = 0; j<column;j++){
				
				if(newCells[i][j]==true){
					cells[i][j].setSelected(true);
					cells[i][j].setBackground(Color.BLUE);
				}
				else{
					cells[i][j].setSelected(false);
					cells[i][j].setBackground(Color.WHITE);
				}
			}
		}
	}
}
