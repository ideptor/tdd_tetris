package ent;

public class BoardFactory {

	public static Board createBoardWithHeight(int height) {
		return new Board(height);
	}

	public static Board createBoardWithShape(int[][] initial_shape) {
		return new Board(initial_shape);
	}

}
