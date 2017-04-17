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

import java.util.Random;

public class BlockFactory {

	private static Random random = new Random();
	public static Block createBlock(int type) {
		return new Block(type);
	}

	public static Block createEmptyBlock() {
		return new Block(Block.TYPE_EMPTY);
	}

	public static Block createRandomBlock() {
		int type = Math.abs(random.nextInt());
		type %= Block.NUM_OF_TYPE;
		type += 1;
		return new Block(type);
	}

}
