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
