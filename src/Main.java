import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class Main {

	public static final boolean draw = true; // whether to draw
	public static final boolean print = false;

	public static final String EULERTABLE = "eulerianTable.txt";
	public static final String EULERTABLEMODMODULO = "eulerianTableModModulo.txt";

	public static long[][] eulerianArray;
	public static int length, size, modulo;

	public static void main(String[] args) throws IOException {

		String s = (String) JOptionPane.showInputDialog(null,
				"Enter the length, size and modulo separated by spaces. For example: '20 10 2'", "Euler's Triangle",
				JOptionPane.PLAIN_MESSAGE, null, null, "16 20 2");

		String[] inputs = s.split(" ");
		length = Integer.parseInt(inputs[0]);
		size = Integer.parseInt(inputs[1]);
		modulo = Integer.parseInt(inputs[2]);

		eulerianArray = new long[length][length];

		setup();
		populate();
		if(print)
			printTable(15);
		write(EULERTABLE, 15);
		populateModMODULO();
		if(print)
			printTable(4);
		write(EULERTABLEMODMODULO, 4);
		if (draw) {
			new MainFrame(size, eulerianArray, length * size * 2, modulo);
		}
	}

	public static void populate() {
		// j is n
		// i is k - 1
		for (int j = 1; j < length; j++) {
			for (int i = j + 1; i < length; i++) {
				eulerianArray[i][j] = ((i - j + 1) * eulerianArray[i - 1][j - 1] + (j + 1) * eulerianArray[i - 1][j]);
			}
		}
	}

	public static void populateModMODULO() {
		// j is n
		// i is k - 1
		for (int j = 1; j < length; j++) {
			for (int i = j + 1; i < length; i++) {
				eulerianArray[i][j] = ((i - j + 1) * eulerianArray[i - 1][j - 1] + (j + 1) * eulerianArray[i - 1][j])
						% modulo;
			}
		}
	}

	public static void setup() {
		for (int i = 0; i < length; i++) {
			eulerianArray[i][0] = 1;
			eulerianArray[i][i] = 1;
		}
	}

	public static void printTable(int tableWidth) {
		String nString = "%"+tableWidth+"s";
		String nDecimal = "%"+tableWidth+"d";
		
		System.out.printf(nString, " ");

		for (int i = 0; i < length; i++) {
			System.out.printf(nDecimal, i);
		}
		System.out.println();

		for (int i = 0; i < length; i++) {
			System.out.printf(nDecimal, i + 1);
			for (int j = 0; j < length; j++) {
				System.out.printf(nDecimal, eulerianArray[i][j]);
			}
			System.out.println();
		}
	}

	public static void write(String filename, int tableWidth) throws IOException {
		FileWriter w = new FileWriter(filename);
		BufferedWriter bw = new BufferedWriter(w);
		
		String nString = "%"+tableWidth+"s";
		String nDecimal = "%"+tableWidth+"d";
		
		bw.write(String.format(nString, " "));

		for (int i = 0; i < length; i++) {
			bw.write(String.format(nDecimal, i));
		}
		bw.newLine();

		for (int i = 0; i < length; i++) {
			bw.write(String.format(nDecimal, i + 1));
			for (int j = 0; j < length; j++) {
				bw.write(String.format(nDecimal, eulerianArray[i][j]));
			}
			bw.newLine();
		}
		bw.close();
		w.close();
	}

}
