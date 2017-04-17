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

public class Board {

	public static final int MOVE_LEFT = 0;
	public static final int MOVE_RIGHT = 1;
	public static final int MOVE_DOWN = 2;
	
	private static int[][] move_direction = 
		{{0,-1},{0,1},{1,0}};
	
	private boolean isGameOver;
	
	int reducedLine;
	int[][] shape;
	public final int WIDTH = 10;
	
	private Block nextBlock;
	
	private Block curBlock;
	
	private int curBlockX;
	private int curBlockY;
	
	private void init() {
		curBlock = BlockFactory.createEmptyBlock();
		nextBlock = BlockFactory.createRandomBlock();
		isGameOver = false;
		reducedLine = 0;
	}

	Board(int height) {
		shape = new int[height][WIDTH];
		init();
	}
	
	Board(int[][] initial_shape) {
		shape = initial_shape;
		init();
	}


	public Block getNextBlock() {
		return nextBlock;
	}

	public boolean isGameOver() {
		return isGameOver;
	}
	
	public int getReducedLine() {
		return reducedLine;
	}
	
	public int[][] getShape() {
		int[][] mixedShape = duplicate(shape);
		if(curBlock.getType() == Block.TYPE_EMPTY)
			return mixedShape;
		
		int[] blockShape = curBlock.getShape();
		for(int y=0; y<4; y++) {
			for(int x=0; x<4; x++) {
				int curBlockShapeIdx = y*4+x;
				if(blockShape[curBlockShapeIdx] == 0) continue;
				mixedShape[curBlockY+y][curBlockX+x] = blockShape[curBlockShapeIdx];
			}
		}
		return mixedShape;
	}
	
	
	public void setNextBlock(Block block) {
		nextBlock = block;
	}
	
	public void addBlock() {
		curBlock = nextBlock;
		nextBlock = BlockFactory.createRandomBlock();
		curBlockX = 3;
		curBlockY = 0;
		
		if(checkBoundaryExceedOrHasConflict())
			isGameOver = true;
	}

	public void moveBlock(int direction) {
		
		int yDirection=0;
		int xDirection=0;
		
		yDirection = move_direction[direction][0];
		xDirection = move_direction[direction][1];
		
		curBlockY += yDirection;
		curBlockX += xDirection;
		
		if(checkBoundaryExceedOrHasConflict()) {
			curBlockY -= yDirection;
			curBlockX -= xDirection;
		}
				
	}
	
	public void rotateBlock() {
		curBlock.rotate();	
		if(checkBoundaryExceedOrHasConflict())
			curBlock.rotateReverse();
	}
	
	public void fallDownBlock() {
		while(isAttachable()==false) {
			moveBlock(MOVE_DOWN);
		}
		attachCurrentBlock();
	}
	
	public void progress() {
		moveBlock(MOVE_DOWN);
		if(isAttachable()) {
			attachCurrentBlock();
			reduce(curBlockY);
			addBlock();
		}
	}
	
	public void reduce(int startY) {
		
		for(int y=startY; y<startY+4; y++) {
			if(y>=shape.length) break;
			int blockCnt = 0;
			for(int x=0; x<WIDTH; x++) {
				if(shape[y][x] != 0) blockCnt++;
			}
			if(blockCnt==10) removeLine(y);
		}
	}
	
		
	// TODO: Algorithm improvement needed
	private void removeLine(int removeY) {
		for(int y=removeY; y>0; y--) {
			for(int x=0; x<WIDTH; x++) {
				shape[y][x] = shape[y-1][x];
				shape[y-1][x] = 0;
			}
		}
		reducedLine++;
	}

	private boolean checkBoundaryExceedOrHasConflict() {
		if(curBlock.getType() == Block.TYPE_EMPTY)
			return false;
		
		int[] blockShape = curBlock.getShape();
		for(int y=0; y<4; y++) {
			for(int x=0; x<4; x++) {
				int curBlockShapeIdx = y*4+x;
				if(blockShape[curBlockShapeIdx] == 0) continue;
				if(exceedX(x) || exceedY(y)) return true;
				if(shape[curBlockY+y][curBlockX+x] != 0)
					return true;
			}
		}
		return false;
	}

	public boolean exceedY(int y) {
		return curBlockY+y >= shape.length;
	}

	public boolean exceedX(int x) {
		return curBlockX+x < 0 || curBlockX+x >= 10;
	}
	
	private int[][] duplicate(int[][] shape) {
		int[][] duplecated = new int[shape.length][WIDTH];
		for(int first=0; first<shape.length; first++) {
			for(int second=0; second<shape[first].length; second++) {
				duplecated[first][second] = shape[first][second];
			}
		}
		return duplecated;
	}
	
	private void attachCurrentBlock() {
		int[] blockShape = curBlock.getShape();
		for(int y=0; y<4; y++) {
			for(int x=0; x<4; x++) {
				int curBlockShapeIdx = y*4+x;
				if(blockShape[curBlockShapeIdx] == 0) continue;
				shape[curBlockY+y][curBlockX+x] = blockShape[curBlockShapeIdx];
			}
		}
		
	}
	
	private boolean isAttachable() {
		if(curBlock.getType() == Block.TYPE_EMPTY)
			return false;
		
		int[] blockShape = curBlock.getShape();
		for(int y=0; y<4; y++) {
			for(int x=0; x<4; x++) {
				int curBlockShapeIdx = y*4+x;
				
				if(blockShape[curBlockShapeIdx] == 0) continue;
				if(curBlockY+y>=shape.length-1) return true;
				
				if(blockShape[curBlockShapeIdx] != 0 &&
						shape[curBlockY+y+1][curBlockX+x] != 0)
					return true;
			}
		}
		return false;
	}


}
