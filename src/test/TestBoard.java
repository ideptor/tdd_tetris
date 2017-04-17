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

import org.junit.Ignore;
import org.junit.Test;

import ent.Block;
import ent.BlockFactory;
import ent.Board;
import ent.BoardFactory;

public class TestBoard {

	static int[][] blank_shape = {
		{0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,},
		{0,0,0,0,0,0,0,0,0,0,},
	};
	
	@Test
	public void testCreateBoard() {
		Board board = BoardFactory.createBoardWithHeight(blank_shape.length);
		assertTrue(isSame(blank_shape, board.getShape()));
	}
	
//	@Ignore
	@Test
	public void testType2BlockShouldBeCenter() {
		int[][] cur_shape = {
				{0,0,0,0,2,2,0,0,0,0,},
				{0,0,0,0,2,2,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		
		Board board = BoardFactory.createBoardWithHeight(blank_shape.length);
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_2));
		board.addBlock();
		
		assertTrue(isSame(cur_shape, board.getShape()));
	}
	
	@Test
	public void testType3BlockShouldBeCenter() {
		int[][] cur_shape = {
				{0,0,0,0,3,3,0,0,0,0,},
				{0,0,0,0,0,3,0,0,0,0,},
				{0,0,0,0,0,3,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		
		Board board = BoardFactory.createBoardWithHeight(blank_shape.length);
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_3));
		board.addBlock();
		
		assertTrue(isSame(cur_shape, board.getShape()));
	}
	

	@Test
	public void testMoveDown() {
		// given
		int[][] cur_shape = {
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,3,3,0,0,0,0,},
				{0,0,0,0,0,3,0,0,0,0,},
				{0,0,0,0,0,3,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		
		Board board = BoardFactory.createBoardWithHeight(blank_shape.length);
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_3));
		board.addBlock();
		
		// when
		board.moveBlock(Board.MOVE_DOWN);
		
		//then
		assertTrue(isSame(cur_shape, board.getShape()));
	}

	@Test
	public void testMoveLeft() {
		// given
		int[][] cur_shape = {
				{0,0,0,3,3,0,0,0,0,0,},
				{0,0,0,0,3,0,0,0,0,0,},
				{0,0,0,0,3,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		
		Board board = BoardFactory.createBoardWithHeight(blank_shape.length);
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_3));
		board.addBlock();
		
		// when
		board.moveBlock(Board.MOVE_LEFT);
		
		//then
		assertTrue(isSame(cur_shape, board.getShape()));
	}	
	
//	@Ignore
	@Test
	public void testNotMoveLeftWhenAlreadyBlock() {
		// given
		int[][] initial_shape = {
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,1,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		int[][] cur_shape = {
				{0,0,0,3,3,0,0,0,0,0,},
				{0,0,0,1,3,0,0,0,0,0,},
				{0,0,0,0,3,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		
		Board board = BoardFactory.createBoardWithShape(initial_shape);
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_3));
		board.addBlock();
		
		board.moveBlock(Board.MOVE_LEFT);
		assertTrue(isSame(cur_shape, board.getShape()));
		
		// when
		board.moveBlock(Board.MOVE_LEFT);
		
		//then
		assertTrue(isSame(cur_shape, board.getShape()));
	}
	
	@Test
	public void testNotMoveLeftWhenBoundary() {
		// given
		int[][] cur_shape = {
				{3,3,0,0,0,0,0,0,0,0,},
				{0,3,0,0,0,0,0,0,0,0,},
				{0,3,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		
		Board board = BoardFactory.createBoardWithHeight(blank_shape.length);
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_3));
		board.addBlock();
		for(int i=0; i<4; i++)
			board.moveBlock(Board.MOVE_LEFT);
		assertTrue(isSame(cur_shape, board.getShape()));
		
		// when
		board.moveBlock(Board.MOVE_LEFT);
		
		//then
		assertTrue(isSame(cur_shape, board.getShape()));
	}
	
	// move right

	@Test
	public void testNotMoveRightWhenBoundary() {
		// given
		int[][] cur_shape = {
				{0,0,0,0,0,0,0,0,3,3,},
				{0,0,0,0,0,0,0,0,0,3,},
				{0,0,0,0,0,0,0,0,0,3,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		
		Board board = BoardFactory.createBoardWithHeight(blank_shape.length);
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_3));
		board.addBlock();
		for(int i=0; i<4; i++) {
			board.moveBlock(Board.MOVE_RIGHT);
		}
		assertTrue(isSame(cur_shape, board.getShape()));
		
		// when
		board.moveBlock(Board.MOVE_RIGHT);
		
		//then
		assertTrue(isSame(cur_shape, board.getShape()));
	}
	
	public void testNotMoveRightWhenAlreadyBlock() {
		// given
		int[][] initial_shape = {
				{0,0,0,0,0,0,1,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		int[][] cur_shape = {
				{0,0,0,0,3,3,1,0,0,0,},
				{0,0,0,0,0,3,0,0,0,0,},
				{0,0,0,0,0,3,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		
		Board board = BoardFactory.createBoardWithShape(initial_shape);
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_3));
		board.addBlock();
		assertTrue(isSame(cur_shape, board.getShape()));
		
		// when
		board.moveBlock(Board.MOVE_LEFT);
		
		//then
		assertTrue(isSame(cur_shape, board.getShape()));
	}

	@Test
	public void testRotateBlock() {
		// given
		int[][] first_shape = {
				{0,0,0,0,5,5,5,0,0,0,},
				{0,0,0,0,0,5,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		int[][] cur_shape = {
				{0,0,0,0,5,0,0,0,0,0,},
				{0,0,0,0,5,5,0,0,0,0,},
				{0,0,0,0,5,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		
		Board board = BoardFactory.createBoardWithHeight(blank_shape.length);
		assertTrue(isSame(blank_shape, board.getShape()));
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_5));
		board.addBlock();
		assertTrue(isSame(first_shape, board.getShape()));
		
		// when
		board.rotateBlock();
		
		//then
		assertTrue(isSame(cur_shape, board.getShape()));	
	}
	
	@Test
	public void testNotRotateBlockWhenAleardyBlock() {
		// given
		int[][] initial_shape = {
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,1,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		int[][] cur_shape = {
				{0,0,0,7,7,0,0,0,0,0,},
				{0,0,0,0,7,7,0,0,0,0,},
				{0,0,0,0,1,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		
		Board board = BoardFactory.createBoardWithShape(initial_shape);
		assertTrue(isSame(initial_shape, board.getShape()));
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_7));
		board.addBlock();
		assertTrue(isSame(cur_shape, board.getShape()));
		
		// when
		board.rotateBlock();
		
		//then
		assertTrue(isSame(cur_shape, board.getShape()));	
	}
	
	@Test
	public void testfalldown() {
		// given
		int[][] initial_shape = {
				{0,0,0,0,2,2,0,0,0,0,},
				{0,0,0,0,2,2,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		int[][] cur_shape = {
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,2,2,0,0,0,0,},
				{0,0,0,0,2,2,0,0,0,0,},
			};
		
		Board board = BoardFactory.createBoardWithHeight(blank_shape.length);
		assertTrue(isSame(blank_shape, board.getShape()));
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_2));
		board.addBlock();
		assertTrue(isSame(initial_shape, board.getShape()));
		// when
		board.fallDownBlock();
		
		//then
		board.setNextBlock(BlockFactory.createEmptyBlock());
		board.addBlock();
		assertTrue(isSame(cur_shape, board.getShape()));	
	}
	
	// progress

	@Test
	public void testProgress() {
		// given
		int[][] initial_shape = {
				{0,0,0,0,2,2,0,0,0,0,},
				{0,0,0,0,2,2,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		int[][] cur_shape = {
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,2,2,0,0,0,0,},
				{0,0,0,0,2,2,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		
		Board board = BoardFactory.createBoardWithHeight(blank_shape.length);
		assertTrue(isSame(blank_shape, board.getShape()));
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_2));
		board.addBlock();
		assertTrue(isSame(initial_shape, board.getShape()));
		// when
		board.progress();
		
		//then
		assertTrue(isSame(cur_shape, board.getShape()));	
	}
	
	@Test
	public void testProgressAndAttachWhenTouchBottomOfBoard() {
		// given
		int[][] first_shape = {
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,1,1,1,1,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
			};
		int[][] second_shape = {
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,1,1,1,1,0,0,0,},
			};
		int[][] cur_shape = {
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,2,2,0,0,0,0,},
				{0,0,0,0,2,2,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,1,1,1,1,0,0,0,},
			};
		
		Board board = BoardFactory.createBoardWithHeight(blank_shape.length);
		assertTrue(isSame(blank_shape, board.getShape()));
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_1));
		board.addBlock();
		for(int i=0; i<3; i++)
			board.moveBlock(Board.MOVE_DOWN);
		
		assertTrue(isSame(first_shape, board.getShape()));
		// when
		board.progress();
		board.setNextBlock(BlockFactory.createEmptyBlock());
		board.addBlock();
		assertTrue(isSame(second_shape, board.getShape()));
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_2));
		board.addBlock();
		board.moveBlock(Board.MOVE_DOWN);
		
		//then
		assertTrue(isSame(cur_shape, board.getShape()));	
	}
	
	@Test
	public void testProgressAndAttachWhenTouchTopOfAlreadyAttached() {
		// given
		int[][] first_shape = {
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,1,1,1,1,0,0,0,},
			};
		int[][] second_shape = {
				{0,0,0,1,1,1,1,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,1,1,1,1,0,0,0,},
			};
		int[][] cur_shape = {
				{0,0,0,0,2,2,0,0,0,0,},
				{0,0,0,0,2,2,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,1,1,1,1,0,0,0,},
				{0,0,0,1,1,1,1,0,0,0,},
			};
		
		Board board = BoardFactory.createBoardWithHeight(blank_shape.length);
		assertTrue(isSame(blank_shape, board.getShape()));
		
		// first block
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_1));
		board.addBlock();
		for(int i=0; i<4; i++)
			board.progress();
		board.setNextBlock(BlockFactory.createEmptyBlock());
		board.addBlock();
		assertTrue(isSame(first_shape, board.getShape()));
		
		// second block
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_1));
		board.addBlock();
		assertTrue(isSame(second_shape, board.getShape()));
		for(int i=0; i<3; i++)
			board.progress();
		
		//third block
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_2));
		board.addBlock();
		assertTrue(isSame(cur_shape, board.getShape()));	
	}
	
	// remove line which is fully filled
	@Test
	public void testReduce() {
		// given
		int[][] initial_shape = {
				{0,0,0,0,0,0,0,0,4,4,},
				{3,3,3,3,3,3,3,3,3,3,},
				{0,0,0,1,1,0,0,0,0,0,},
				{3,3,3,3,3,3,3,3,3,3,},
				{2,2,2,0,0,0,0,0,0,0,},
			};
		int[][] cur_shape = {
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,0,0,},
				{0,0,0,0,0,0,0,0,4,4,},
				{0,0,0,1,1,0,0,0,0,0,},
				{2,2,2,0,0,0,0,0,0,0,},
			};
		
		Board board = BoardFactory.createBoardWithShape(initial_shape);
		assertTrue(isSame(initial_shape, board.getShape()));
		int preReducedLine = board.getReducedLine();

		// when
		board.reduce(0);
		
		//then
		assertTrue(isSame(cur_shape, board.getShape()));
		assertEquals(2, board.getReducedLine() - preReducedLine);
	}
	
	

	// cannot add block -> then exit program
	@Test
	public void testGameOver() {
		// given
		int[][] initial_shape = {
				{0,0,0,0,0,0,0,0,4,4,},
				{3,3,3,3,3,3,3,3,3,3,},
				{0,0,0,1,1,0,0,0,0,0,},
				{3,3,3,3,3,3,3,3,3,3,},
				{2,2,2,0,0,0,0,0,0,0,},
			};

		
		Board board = BoardFactory.createBoardWithShape(initial_shape);
		assertTrue(isSame(initial_shape, board.getShape()));

		// when
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_1));
		board.addBlock();
		assertFalse(board.isGameOver());
		
		board.setNextBlock(BlockFactory.createBlock(Block.TYPE_2));
		board.addBlock();
		
		//then
		assertTrue(board.isGameOver());	
	}
	
	
	// TODO: total line ¼ö
	
	
	private boolean isSame(int[][] one, int[][] other) {
		
		if(one.length != other.length) {
			display(one);
			display(other);
			return false;
		}
		
		for(int first=0; first<one.length; first++) {
			if(one[first].length != other[first].length) {
				display(one);
				display(other);
				return false;	
			}
			for(int second=0; second<one[first].length; second++) {
				if(one[first][second] != other[first][second]) {
					display(one);
					display(other);
					return false;
				}
			}
		}
		return true;
	}
	
	

	private void display(int[][] array) {
		System.out.println("-----------------------");
		for(int first=0; first<array.length; first++) {
			for(int second=0; second<array[first].length; second++) {
				System.out.print(array[first][second]+" ");
			}
			System.out.println();
		}
		System.out.println("-----------------------");
		
	}
	
}
