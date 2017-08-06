import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class Counter extends JLabel{
	
	private static final long serialVersionUID = 3698564745876L;
	 int count = 0;
	
	Counter(){
		//Creates counter appearance
		setOpaque(true);
		setText("Iteration");
		setHorizontalAlignment(JLabel.CENTER);
		setFont(getFont().deriveFont(40.0f)); //Set counter size
		setBorder(new EmptyBorder(10,10,10,10));
	
	}
	
	public void incrementCount(){
		//Sets counter text and increments count by 1
		setText(String.valueOf(count++));
	}
	
	public void resetCount(){
		//Resets counter variable to zero and the text back to "Iteration"
		count = 0;
		setText("Iteration");
	}

}
