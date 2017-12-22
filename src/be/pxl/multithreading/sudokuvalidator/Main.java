package be.pxl.multithreading.sudokuvalidator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("sudokuInputFalse.txt"))) {
			int[][] sudoku = new int[9][9];
			String line = null;
			ArrayList<Thread> threadPool = new ArrayList<>();
			SudokuValidator sudokuValidator = new SudokuValidator();
			int lineIndex = 0;
			while ((line = bufferedReader.readLine()) != null) {
				line = line.replace("[", "");
				line = line.replace("]", "");
				line = line.replace(" ", "");
				String[] strings = line.split(",");
				for (int j = 0; j < 9; j++) {
					sudoku[lineIndex][j] = Integer.parseInt(strings[j]);
				}
				int currentIndex = lineIndex;
				threadPool.add(new Thread(() -> sudokuValidator.lineValidation(sudoku[currentIndex])));
				threadPool.get(threadPool.size()-1).start();
				lineIndex++;
				if (lineIndex % 3 == 0) {
					threadPool.add(new Thread(() -> sudokuValidator.sectionValidation(sudoku[currentIndex - 2], sudoku[currentIndex - 1],
							sudoku[currentIndex])));
					threadPool.get(threadPool.size()-1).start();
				}
			}
			threadPool.add(new Thread(()-> sudokuValidator.columnValidation(sudoku)));
			threadPool.get(threadPool.size()-1).start();
			for(Thread thread: threadPool){
				thread.join();
			}
			System.out.printf("Sudoku is valid? %b",sudokuValidator.isValid());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
