import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener{

	Random random = new Random();
	JFrame frame = new JFrame("Tic Tac Toe");
	JPanel title_panel = new JPanel();
	JPanel button_panel = new JPanel();
	JLabel textfield = new JLabel();
	JButton[] buttons = new JButton[9];
	JButton resButton = new JButton("Restart");
	Image green, orange, blue, purple, pink;
	boolean player1_turn;

	TicTacToe(){
		
		try {
            Font spongebobFont = Font.createFont(Font.TRUETYPE_FONT, new File("Krabby Patty.ttf")).deriveFont(75f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(spongebobFont);

			
			

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 800);
            frame.setLayout(null);  // Optional if you're using setBounds everywhere
            frame.getContentPane().setBackground(new Color(137, 207, 240));

            textfield.setBackground(new Color(254, 239, 109));
            textfield.setForeground(new Color(156, 102, 31));
			textfield.setBorder(BorderFactory.createLineBorder(new Color(154, 165, 50), 6));
            textfield.setFont(spongebobFont);
            textfield.setHorizontalAlignment(JLabel.CENTER);
            textfield.setText("Tic-Tac-Toe");
            textfield.setOpaque(true);

            title_panel.setLayout(new BorderLayout());
            title_panel.setBounds(17, 9, 650, 100);
            title_panel.add(textfield);

            button_panel.setLayout(new GridLayout(3,3,10,10));
            button_panel.setOpaque(false);
			button_panel.setBackground(new Color(0, 0, 0, 0)); // transparent
            button_panel.setBounds(17, 130,650, 500);


            for (int i = 0; i < 9; i++) {
                buttons[i] = new JButton();
                buttons[i].setFont(spongebobFont.deriveFont(150f));
                buttons[i].setFocusable(false);
                buttons[i].addActionListener(this);
				buttons[i].setBackground(new Color(198, 239, 255));
				buttons[i].setBorder(BorderFactory.createLineBorder(new Color(54, 117, 136), 5));
                button_panel.add(buttons[i]);
            }

			resButton.setBounds(285,670,120,50);
			resButton.setBackground(new Color(255, 182, 193));
			resButton.setForeground(new Color(165, 108, 193));
			resButton.setBorder(BorderFactory.createLineBorder(new Color(124, 197, 118), 5));
			resButton.setHorizontalAlignment(JLabel.CENTER);
			resButton.setFont(spongebobFont.deriveFont(25f));
			resButton.setFocusPainted(false);
			resButton.addActionListener(this);

			ImageIcon blueIcon  = loadScaledIcon("blue.png", 100, 100);
			ImageIcon pinkIcon  = loadScaledIcon("pink.png", 107, 193);
			ImageIcon purpleIcon = loadScaledIcon("purple.png", 100, 100);
			ImageIcon orangeIcon = loadScaledIcon("orange.png", 100, 100);
			ImageIcon blue2Icon  = loadScaledIcon("blue.png", 100, 100); 
			ImageIcon greenIcon = loadScaledIcon("green.png", 100, 100);

			// Labels
			JLabel blueLabel   = new JLabel(blueIcon);
			blueLabel.setBounds(520, 20, 250, 250);

			JLabel pinkLabel   = new JLabel(pinkIcon);
			pinkLabel.setBounds(130, 70, 257, 100);

			JLabel purpleLabel = new JLabel(purpleIcon);
			purpleLabel.setBounds(370, 550, 203, 266);

			JLabel orangeLabel = new JLabel(orangeIcon);
			orangeLabel.setBounds(80, 350, 250, 250);

			JLabel blue2Label  = new JLabel(blue2Icon);
			blue2Label.setBounds(330, 310, 250, 250);

			JLabel greenLabel = new JLabel(greenIcon);
			greenLabel.setBounds(30, 550, 150, 150);

			
            frame.add(title_panel);
            frame.add(button_panel);
			frame.add(resButton);
			frame.add(blueLabel);
			frame.add(pinkLabel);
			frame.add(purpleLabel);
			frame.add(orangeLabel);
			frame.add(blue2Label);
			frame.add(greenLabel);
            frame.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
		firstTurn();
    }

	private ImageIcon loadScaledIcon(String path, int width, int height) {
		Image img = new ImageIcon(path).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i=0;i<9;i++) {
			if(e.getSource()==buttons[i]) {
				if(player1_turn) {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(242, 108, 140));
						buttons[i].setText("X");
						player1_turn=false;
						textfield.setText("O turn");
						check();
						checkDraw();
					}
				}
				else {
					if(buttons[i].getText()=="") {
						buttons[i].setForeground(new Color(74, 165, 176));
						buttons[i].setText("O");
						player1_turn=true;
						textfield.setText("X turn");
						check();
						checkDraw();
					}
				}
			}			
		}

		if(e.getSource() == resButton){
			for(int i=0;i<9;i++){
				buttons[i].setText("");
				buttons[i].setEnabled(true);
				buttons[i].setBackground(new Color(198, 239, 255));
			}
			textfield.setText("Tic-Tac-Toe");    
			player1_turn = true;
    		firstTurn(); 
		}	
	}
	
	public void firstTurn() {
		
		try {
			Thread.sleep(2000); //pausing the program or 2 sec
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(random.nextInt(2) == 0) { //randomy generates if O or X starts
			player1_turn=true;
			textfield.setText("X turn");
		}
		else {
			player1_turn=false;
			textfield.setText("O turn");
		}
	}
	
	public void check() {
		//check X win conditions
		if(
				(buttons[0].getText()=="X") &&
				(buttons[1].getText()=="X") &&
				(buttons[2].getText()=="X")
				) {
			xWins(0,1,2);
		}
		if(
				(buttons[3].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[5].getText()=="X")
				) {
			xWins(3,4,5);
		}
		if(
				(buttons[6].getText()=="X") &&
				(buttons[7].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			xWins(6,7,8);
		}
		if(
				(buttons[0].getText()=="X") &&
				(buttons[3].getText()=="X") &&
				(buttons[6].getText()=="X")
				) {
			xWins(0,3,6);
		}
		if(
				(buttons[1].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[7].getText()=="X")
				) {
			xWins(1,4,7);
		}
		if(
				(buttons[2].getText()=="X") &&
				(buttons[5].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			xWins(2,5,8);
		}
		if(
				(buttons[0].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[8].getText()=="X")
				) {
			xWins(0,4,8);
		}
		if(
				(buttons[2].getText()=="X") &&
				(buttons[4].getText()=="X") &&
				(buttons[6].getText()=="X")
				) {
			xWins(2,4,6);
		}
		//check O win conditions
		if(
				(buttons[0].getText()=="O") &&
				(buttons[1].getText()=="O") &&
				(buttons[2].getText()=="O")
				) {
			oWins(0,1,2);
		}
		if(
				(buttons[3].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[5].getText()=="O")
				) {
			oWins(3,4,5);
		}
		if(
				(buttons[6].getText()=="O") &&
				(buttons[7].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			oWins(6,7,8);
		}
		if(
				(buttons[0].getText()=="O") &&
				(buttons[3].getText()=="O") &&
				(buttons[6].getText()=="O")
				) {
			oWins(0,3,6);
		}
		if(
				(buttons[1].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[7].getText()=="O")
				) {
			oWins(1,4,7);
		}
		if(
				(buttons[2].getText()=="O") &&
				(buttons[5].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			oWins(2,5,8);
		}
		if(
				(buttons[0].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[8].getText()=="O")
				) {
			oWins(0,4,8);
		}
		if(
				(buttons[2].getText()=="O") &&
				(buttons[4].getText()=="O") &&
				(buttons[6].getText()=="O")
				) {
			oWins(2,4,6);
		}
	}
	
	public void xWins(int a,int b,int c) {
		buttons[a].setBackground(new Color(124, 252, 0));
		buttons[b].setBackground(new Color(124, 252, 0));
		buttons[c].setBackground(new Color(124, 252, 0));
		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
			buttons[i].setForeground(new Color(201, 167, 139));
		}
		textfield.setText("X WINS");
	}
	public void oWins(int a,int b,int c) {
		buttons[a].setBackground(new Color(124, 252, 0));
		buttons[b].setBackground(new Color(124, 252, 0));
		buttons[c].setBackground(new Color(124, 252, 0));
		
		for(int i=0;i<9;i++) {
			buttons[i].setEnabled(false);
			buttons[i].setForeground(new Color(201, 167, 139));
		}
		textfield.setText("O WINS");
	}

	public void checkDraw() {
		boolean draw = true;
	
		// Check if all buttons are filled
		for (int i = 0; i < 9; i++) {
			if (buttons[i].getText().equals("")) {
				draw = false;
				break;
			}
		}
	
		if (draw && !textfield.getText().equals("X WINS") && !textfield.getText().equals("O WINS")) {
			draw(); 
		}
	}
	

	public void draw() {
		for (int i = 0; i < 9; i++) {
			buttons[i].setEnabled(false);
		}
		textfield.setText("DRAW");
	}
}