import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	private static final long serialVersionUID = 1844731155096148883L;
	HexagonDrawer drawer;
	ControlPanel panel;
	public MainFrame(int size, long[][] eulerianArray, int frameSize, int modulo)
	{
		getContentPane().setBackground(Color.BLACK);
		
		setLayout(new BorderLayout());
		setSize(frameSize, frameSize);
		
		drawer = new HexagonDrawer(size, eulerianArray, frameSize, modulo);
		panel = new ControlPanel(modulo);
		
		add(drawer, BorderLayout.CENTER);
		add(panel, BorderLayout.NORTH);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
}
