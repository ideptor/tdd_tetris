package ui.simple;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.border.EtchedBorder;

import ent.Block;
import ent.BlockFactory;
import ent.Board;
import ent.BoardFactory;

public class SimpleUIMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String BLOCK_STRING = "бс";
	private static final String EMPTY_STRING = " ";
	private static final Font FONT = new Font("Consolas", Font.PLAIN, 32);

	
	JTextPane jaBoard;
	JTextPane jaNext;
	JLabel jtReducedLines;
	Board board;
	KeyListener keyListener;
	
	public static void main(String[] args) {
		SimpleUIMain simpleUIMain = new SimpleUIMain();
		simpleUIMain.setVisible(true);
		simpleUIMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public SimpleUIMain() {
		Container cp = getContentPane();
		BorderLayout br = new BorderLayout();
		cp.setLayout(br);
		
		board = BoardFactory.createBoardWithHeight(25);
		jaBoard = new JTextPane();
		jaBoard.setEditable(false);
		jaBoard.setFont(FONT);
		jaBoard.setText(convertString(board));
		
		jaNext = new JTextPane();
		jaNext.setEditable(false);
		jaNext.setFont(FONT);
		jaNext.setText(convertString(BlockFactory.createEmptyBlock()));
		
		jtReducedLines = new JLabel("0");
		JPanel eastPanel = new JPanel(new BorderLayout());
		JPanel eastInformationPanel = new JPanel(new BorderLayout());
		
		eastInformationPanel.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		JPanel statusPanel = new JPanel(new GridLayout(0,1));
		statusPanel.add(new JLabel(""));
		statusPanel.add(new JLabel("Lines:"));
		statusPanel.add(jtReducedLines);
		statusPanel.add(new JLabel(""));
		statusPanel.add(BorderLayout.NORTH, new JLabel("next:"));
		eastInformationPanel.add(BorderLayout.CENTER, jaNext);
		eastInformationPanel.add(BorderLayout.NORTH, statusPanel);
		
		eastPanel.add(BorderLayout.NORTH, eastInformationPanel);
		eastPanel.add(BorderLayout.CENTER, new JLabel(""));
		
		SwingWorker timer = new SwingWorker() {

			@Override
			protected Object doInBackground() throws Exception {
				while(board.isGameOver() == false) {
					updateEastPanel();
					Thread.sleep(300);
					board.progress();
					jaBoard.setText(convertString(board));
					if(board.isGameOver()) {
						JOptionPane.showMessageDialog(SimpleUIMain.this, "Game Over");
					}
				}
				return null;
			}
			
		};
		
		JMenuBar menubar = new JMenuBar();
		JMenu menuGame = new JMenu("Game");
		JMenuItem menuItemStart = new JMenuItem("Start Game");
		menubar.add(menuGame);
		menuGame.add(menuItemStart);
		this.setJMenuBar(menubar);
	
		
		menuItemStart.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						keyListener = new KeyAdapter() {
							
							@Override
							public void keyPressed(KeyEvent e) {
								int key = e.getKeyCode();
								switch(key) {
								case KeyEvent.VK_LEFT:
									board.moveBlock(Board.MOVE_LEFT);
									break;
								case KeyEvent.VK_RIGHT:
									board.moveBlock(Board.MOVE_RIGHT);
									break;
								case KeyEvent.VK_DOWN:
									board.moveBlock(Board.MOVE_DOWN);
									break;
								case KeyEvent.VK_UP:
									board.rotateBlock();
									break;
								case KeyEvent.VK_SPACE:
									board.fallDownBlock();
									break;
								
								}
								
								updateEastPanel();
							}
						};
						jaBoard.addKeyListener(keyListener);
						
						board.addBlock();
						timer.execute();
						
					}
				}
				);
		cp.add(BorderLayout.CENTER, jaBoard);
		cp.add(BorderLayout.EAST, eastPanel);
		
		


		this.pack();
		this.addKeyListener(keyListener);


	}
	
	protected void updateEastPanel() {
		jtReducedLines.setText(""+board.getReducedLine());
		jaNext.setText(convertString(board.getNextBlock()));
		
	}

	private String convertString(Block nextBlock) {
		StringBuffer buf = new StringBuffer();
		int[] shape = nextBlock.getShape();
		for(int y=0; y<Block.WIDTH; y++) {
			for(int x=0; x<Block.WIDTH; x++) {
				if(shape[y*4+x]==0) buf.append(EMPTY_STRING).append(" ");
				else buf.append(BLOCK_STRING).append(" ");
			}
			buf.append("\n");
		}
		return buf.toString();
	}

	private String convertString(Board board) {
		StringBuffer buf = new StringBuffer();
		int[][] shape = board.getShape();
		for(int y=0; y<shape.length; y++) {
			for(int x=0; x<shape[y].length; x++) {
				if(shape[y][x]==0) buf.append(EMPTY_STRING).append(" ");
				else buf.append(BLOCK_STRING).append(" ");
			}
			buf.append("\n");
		}
		return buf.toString();
	}
}
