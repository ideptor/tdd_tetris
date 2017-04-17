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

package test;

import static org.junit.Assert.*;

import org.junit.Test;

import ent.Block;
import ent.BlockFactory;

public class TestBlock {

	@Test
	public void testRotateBlock() {
		// given
		Block block = BlockFactory.createBlock(Block.TYPE_1);
		assertEquals(Block.TYPE_1, block.getType());
		int[] shape = block.getShape();
		
		// when
		block.rotate();
		
		//then
		assertNotEquals(shape, block.getShape());
	}
	
	@Test
	public void testManyRotateBlockToGetOriginShape() {
		// given
		Block block = BlockFactory.createBlock(Block.TYPE_3);
		assertEquals(Block.TYPE_3, block.getType());
		int[] shape = block.getShape();
		
		// when
		block.rotate();
		assertNotEquals(shape, block.getShape());
		block.rotate();
		assertNotEquals(shape, block.getShape());
		block.rotate();
		assertNotEquals(shape, block.getShape());
		block.rotate();
		
		//then
		assertEquals(shape, block.getShape());
		
	}
}
