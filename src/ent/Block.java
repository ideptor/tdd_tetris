package ent;

public class Block {

	private int curTypeIdx;
	private int curShapeIdx;
	
	public int getType() {
		return curTypeIdx;
	}

	Block(int type) {
		this.curTypeIdx = type;
		this.curShapeIdx = 0;
	}
	
	
	public int[] getShape() {
		return BLOCK_SHAPE[curTypeIdx][curShapeIdx];
	}

	public void rotate() {
		curShapeIdx+=1;
		if(curShapeIdx >= BLOCK_SHAPE[curTypeIdx].length)
			curShapeIdx = 0;
		
	}

	public void rotateReverse() {
		curShapeIdx-=1;
		if(curShapeIdx <0 )
			curShapeIdx = BLOCK_SHAPE[curTypeIdx].length-1;
		
	}

	public static final int TYPE_EMPTY= 0;
	public static final int TYPE_1 = 1;
	public static final int TYPE_2 = 2;
	public static final int TYPE_3 = 3;
	public static final int TYPE_4 = 4;
	public static final int TYPE_5 = 5;
	public static final int TYPE_6 = 6;
	public static final int TYPE_7 = 7;
	public static final int NUM_OF_TYPE = 7;
	
	public static final int WIDTH = 4;
	
	// first indix is type
	// second index is currentShape;
	private static int[][][] BLOCK_SHAPE = 
		{
			{
				{0,0,0,0,
				 0,0,0,0,
				 0,0,0,0,
				 0,0,0,0,
					} // dummy - never used
			},
			{
				{1,1,1,1,
				 0,0,0,0,
				 0,0,0,0,
				 0,0,0,0
				 }, 
				{0,1,0,0,
			     0,1,0,0,
			     0,1,0,0,
			     0,1,0,0
				}
			},
			{
				{0,2,2,0,
				 0,2,2,0,
				 0,0,0,0,
				 0,0,0,0
				}
			},
			{
				{0,3,3,0,
				 0,0,3,0,
				 0,0,3,0,
				 0,0,0,0,
				},
				{
				0,3,3,3,
				0,3,0,0,
				0,0,0,0,
				0,0,0,0,
				},
				{
				0,3,0,0,
				0,3,0,0,
				0,3,3,0,
				0,0,0,0
				},
				{
				0,0,0,3,
				0,3,3,3,
				0,0,0,0,
				0,0,0,0,
				}
			},
			{
				{0,4,4,0,
				 0,4,0,0,
				 0,4,0,0,
				 0,0,0,0,
				},
				{
				0,4,0,0,
				0,4,4,4,
				0,0,0,0,
				0,0,0,0,
				},
				{
				0,0,4,0,
				0,0,4,0,
				0,4,4,0,
				0,0,0,0
				},
				{
				0,4,4,4,
				0,0,0,4,
				0,0,0,0,
				0,0,0,0,
				}
			},
			{
				{0,5,5,5,
				 0,0,5,0,
				 0,0,0,0,
				 0,0,0,0,
				},
				{0,5,0,0,
				 0,5,5,0,
				 0,5,0,0,
				 0,0,0,0,
				},
				{0,0,5,0,
				 0,5,5,5,
				 0,0,0,0,
				 0,0,0,0,
				},
				{0,0,5,0,
				 0,5,5,0,
				 0,0,5,0,
				 0,0,0,0,
				},
			},
			{
				{0,6,6,0,
				 6,6,0,0,
				 0,0,0,0,
				 0,0,0,0
				 },
				{0,6,0,0,
				 0,6,6,0,
				 0,0,6,0,
				 0,0,0,0	 
				}
			},
			{
				{7,7,0,0,
				 0,7,7,0,
				 0,0,0,0,
				 0,0,0,0
				 },
				{0,0,7,0,
				 0,7,7,0,
				 0,7,0,0,
				 0,0,0,0	 
				}
			},
		};

}
