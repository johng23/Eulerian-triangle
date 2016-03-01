import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class ControlPanel extends JPanel{

	private static final long serialVersionUID = -8199949340255566534L;
	JTextField text = new JTextField("Enter the modulo you wish to see, nothing for all modulos");
	public ControlPanel(int modulo) {
		setSize(20, 100);
		setLayout(new GridLayout());
		text.addKeyListener(new EnterListener());
		add(text);
	}
	public class EnterListener implements KeyListener {
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				String s = text.getText();
				MainFrame mf = (MainFrame)getTopLevelAncestor();
				HexagonDrawer drawer = mf.drawer;
				
				boolean exceptionCaught = false;
				
				if(s.equals("")) {
					drawer.drawModulo = -1;
				}
				else {
					int i;
					try {
						i = Integer.parseInt(s);
						if(i <= drawer.moduloMinusOne)
							drawer.drawModulo = i;
					}
					catch (NumberFormatException exception) {
						exceptionCaught = true;
					}
				}
				if(!exceptionCaught)
					mf.repaint();
			}		
		}
		
		public void keyPressed(KeyEvent arg0) {	
		}
		public void keyTyped(KeyEvent arg0) {	
		}		
	}
	
}
