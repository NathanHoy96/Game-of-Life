import javax.swing.JPanel;
import java.awt.BorderLayout;

public class MainPanel extends JPanel 
{
	private static final long serialVersionUID = 1862962349L;
	
	private static Board board;
	private static Nav nav;
	private static Counter counter;

	//Constructor for our form
	public MainPanel() 
	{
	
		BoardSizeDialog options = new BoardSizeDialog();
		//Get inputs from dialogs to pass to other classes
		int rowInput = options.getRowInput();
		int columnInput = options.getColumnInput();
		int speedInput = options.getSpeedInput();
		
		//Main panel, set to border layout
		setLayout(new BorderLayout());
		//Add Nav sub-panel to main panel
		nav = new Nav();
		//Add counter J-Label to main label
		counter = new Counter();
		//Add Board sup-panel to main panel
		board = new Board(columnInput,rowInput,speedInput);
		
		//Add Nav JPanel to the top of the window
		add(nav,BorderLayout.NORTH);
		//Add the board to the middle of the window
		add(board,BorderLayout.CENTER);
		//Add the counter underneath the board
		add(counter,BorderLayout.SOUTH);
	}
	
	public static Board getBoard() {
		return board;
	}
	
	public static Counter getCounter(){
		return counter;
	}
}