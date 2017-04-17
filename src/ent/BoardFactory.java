/*
    tdd_tetris
    Copyright (C) 2017 Suhyun David Kim

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation; either version 2 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
 */

package ent;

public class BoardFactory {

	public static Board createBoardWithHeight(int height) {
		return new Board(height);
	}

	public static Board createBoardWithShape(int[][] initial_shape) {
		return new Board(initial_shape);
	}

}
