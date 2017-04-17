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
