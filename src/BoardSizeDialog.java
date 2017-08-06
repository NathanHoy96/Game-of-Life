import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

//Class creates a dialog that allows the user to input the grid size they'd like
public class BoardSizeDialog extends JOptionPane{

	private int rowInput, columnInput, speedInput;
	private static final long serialVersionUID = 3008784835592L;

	BoardSizeDialog(){
		createOptionPanel();
	}

	public int getRowInput() {
		return rowInput;
	}

	public int getColumnInput() {
		return columnInput;
	}
	
	public int getSpeedInput() {
		return speedInput;
	}
	
	//Create panel to prompt user for input
	private void createOptionPanel(){
		//Create two text fields, one for row, one for column
		JTextField rowField = new JTextField(5);
		JTextField columnField = new JTextField(5);
		
		//Create slider to set speed of iterations
		JSlider speedSlider = new JSlider(JSlider.HORIZONTAL,1,10,5);
		speedSlider.setMajorTickSpacing(1);
		speedSlider.setMinorTickSpacing(1);
		speedSlider.setPaintTicks(true);
		speedSlider.setPaintLabels(true);
		speedSlider.setPreferredSize(new Dimension(400,50));
		//Set font size of text fields
		rowField.setFont(getFont().deriveFont(20.0f));
		columnField.setFont(getFont().deriveFont(20.0f));
		
		//Create labels to describe to user what input the program expects and set fonts
		JLabel inputDescription = new JLabel("Please enter two integers between 10 & 50...");
		inputDescription.setFont(getFont().deriveFont(20.0f));
		
		JLabel rowLabel = new JLabel("Row: ");
		rowLabel.setFont(getFont().deriveFont(20.0f));

		JLabel colLabel = new JLabel("Column: ");
		colLabel.setFont(getFont().deriveFont(20.0f));
		
		JLabel speedLabel = new JLabel("and speed...");
		speedLabel.setFont(getFont().deriveFont(20.0f));

		JPanel optionPanel = new JPanel();

		//Set size of the dialog box and add components
		optionPanel.setPreferredSize(new Dimension(450,175));
		optionPanel.add(inputDescription);
		optionPanel.add(rowLabel);
		optionPanel.add(rowField);
		optionPanel.add(Box.createHorizontalStrut(15));
		optionPanel.add(colLabel);
		optionPanel.add(columnField);
		optionPanel.add(speedLabel);
		optionPanel.add(speedSlider);

		//Show the dialog box and capture selection
		int selection = showConfirmDialog(null, optionPanel, "Please Enter Grid Size", JOptionPane.CANCEL_OPTION);

		captureInput(selection,rowField,columnField,speedSlider);
	}

	//Parses the integers and ensures they're within acceptable bounds
	private void captureInput(int selection, JTextField rowField, JTextField columnField, JSlider speedSlider){
		//If the okay button was pressed...
		if(selection == JOptionPane.OK_OPTION){
			//...attempt to parse integers
			try{
				rowInput = Integer.valueOf(rowField.getText());
				columnInput = Integer.valueOf(columnField.getText());
				speedInput = speedSlider.getValue();
				//Check they're within bounds, if they are call an error message and create a new dialog to enter
				if(rowInput < 10 || columnInput < 10 || rowInput > 50 || columnInput > 50){
					errorDialog();
					createOptionPanel();
				}
				
			}
			catch(NumberFormatException e){
				//If a non-integer is entered...
				errorDialog();
				createOptionPanel();
			}
		}
		//If cancel was pressed, exit program
		else{
			System.exit(0);
		}
	}
	
	//Error message to remind user expected values
	private void errorDialog(){
		JLabel errorMessage = new JLabel("Please make sure you only enter integers between "
										+ "grid sizes between 10 & 50 and speeds between 1 & 10");
		errorMessage.setFont(getFont().deriveFont(20.0f));
		showMessageDialog(this, errorMessage,"Input error",JOptionPane.WARNING_MESSAGE);
	}

}
