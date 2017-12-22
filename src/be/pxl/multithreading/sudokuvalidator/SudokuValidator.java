package be.pxl.multithreading.sudokuvalidator;

import java.util.Arrays;

public class SudokuValidator {
	private boolean valid = true;

	{
		valid = true;
	}

	public boolean isValid() {
		return valid;
	}

	public void lineValidation(int[] array) {
		if (Arrays.stream(array).distinct().count() < 9) {
			handleInvalid();
		}
	}

	public void sectionValidation(int[] line1, int[] line2, int[] line3) {
		int[] section1 = getSectionInArray(1, line1, line2, line3);
		int[] section2 = getSectionInArray(2, line1, line2, line3);
		int[] section3 = getSectionInArray(3, line1, line2, line3);

		if ((Arrays.stream(section1).distinct().count() < 9) || (Arrays.stream(section2).distinct().count() < 9)
				|| (Arrays.stream(section3).distinct().count() < 9)) {
			handleInvalid();
		}
	}

	private int[] getSectionInArray(Integer secNumber, int[] line1, int[] line2, int[] line3) {
		int[] array = new int[9];
		int arrayIndex = 0;
		for (int i = 0; i < 3; i++) {
			array[arrayIndex] = line1[i + (secNumber - 1) * 3];
			arrayIndex++;
			array[arrayIndex] = line2[i + (secNumber - 1) * 3];
			arrayIndex++;
			array[arrayIndex] = line3[i + (secNumber - 1) * 3];
			arrayIndex++;
		}
		return array;
	}

	private void handleInvalid() {
		valid = false;
	}

	public void columnValidation(int[][] sudoku) {
		for (int i = 0; i < sudoku.length; i++) {
			int[] column = new int[9];
			for (int j = 0; j < sudoku[i].length; j++) {
				column[j] = sudoku[j][i];
			}
			if (Arrays.stream(column).distinct().count() < 9) {
				handleInvalid();
			}
		}
	}
}
