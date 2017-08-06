import javax.swing.JFrame;

public class GUIFrame implements Runnable
{
	public void run() 
	{
	     //Create and set up the Window
        JFrame frame = new JFrame("Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Create and set up the content Pane based on our MainPanel class
        MainPanel newContentPane = new MainPanel();
        //All content Panes must be opaque apparently...
        newContentPane.setOpaque(true);
        //Put the Pane in the Window!
        frame.setContentPane(newContentPane);
        //Set position when frame opens
        frame.setBounds(200, 200,400,400);
        frame.setResizable(false);
        //Expand frame to fit all contents
        frame.pack();
        //Display the frame.
        frame.setVisible(true);
    }
}