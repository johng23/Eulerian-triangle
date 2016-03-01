import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class HexagonDrawer extends JPanel {

	private static final long serialVersionUID = 2534387425586118266L;
	public final double ROOT_THREE = Math.sqrt(3);
	public int frameSize;
	public int size;
	public long[][] eulerianTable;
	public int moduloMinusOne;
	public int drawModulo;

	public HexagonDrawer(int size, long[][] eulerianTable, int frameSize, int modulo) {

		this.frameSize = frameSize;
		this.moduloMinusOne = modulo - 1;
		this.size = size;
		setPreferredSize(new Dimension(frameSize, frameSize));
		this.eulerianTable = eulerianTable;
		drawModulo = -1;
		setVisible(true);

	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Hexagon h = new Hexagon(frameSize / 2, size + frameSize/40, size);

		for (int i = 0; i < eulerianTable.length; i++) {
			for (int j = 0; j < i + 1; j++) {
				if (drawModulo == -1 || eulerianTable[i][j] == drawModulo) {
					g2.setColor(new Color(1 - 1f / moduloMinusOne * eulerianTable[i][j],
							0,
							1f / moduloMinusOne * eulerianTable[i][j]));
					g2.fillPolygon(h.xCI, h.yCI, 6);
				}

				h.move((size * ROOT_THREE), 0);
			}
			h.move((-size * (ROOT_THREE * (i + 1.5))), (size * 1.5));
		}
	}

	public class Hexagon {
		public double[] xC;
		public double[] yC;
		public int[] xCI;
		public int[] yCI;

		public Hexagon(int x, int y, int size) {
			xC = new double[6];
			yC = new double[6];
			xCI = new int[6];
			yCI = new int[6];

			xC[0] = xC[3] = x;
			xC[1] = xC[2] = x + (ROOT_THREE * size / 2);
			xC[4] = xC[5] = x - (ROOT_THREE * size / 2);
			yC[0] = y - size;
			yC[1] = yC[5] = y - size / 2;
			yC[2] = yC[4] = y + size / 2;
			yC[3] = y + size;
			toInt();
		}

		public void move(double xdisp, double ydisp) {
			for (int i = 0; i < 6; i++) {
				xC[i] += xdisp;
				yC[i] += ydisp;
			}
			toInt();
		}

		public void toInt() {
			for (int i = 0; i < 6; i++) {
				xCI[i] = (int) xC[i];
				yCI[i] = (int) yC[i];
			}
		}
	}
}
