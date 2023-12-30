package com.javalec.base;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.function.ShareVar;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Keyboard extends JDialog {
	
	
	
	/*
	 * Description : keyboard  기능
	 * Date : 2023.12.30
	 * Author : Forrest D Park (PDG)
	 * Update 2023.12.30 by PDG
	 * 			1.
	 * 			2.
	 * 
	 * 
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfState;
	static ArrayList<String> resulOfTyped = new ArrayList<String>();
	
	
	static boolean UpLowCase =false;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Keyboard dialog = new Keyboard();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Keyboard() {
		
		
		
		
		
		setTitle("키보드");
		setBounds(ShareVar.position_window_x, ShareVar.position_window_y+400, 815, 412);
		contentPanel.setBackground(new Color(ShareVar.RGB_red, ShareVar.RGB_green, ShareVar.RGB_blue));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JButton btnQ = new JButton("Q");
		btnQ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("Q",tfState);
			}
		});
		
		btnQ.setFont(new Font("굴림", Font.BOLD, 13));
		btnQ.setBounds(135, 179, 45, 32);
		contentPanel.add(btnQ);
		
		JButton btnW = new JButton("W");
		btnW.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("W",tfState);
			}
		});
		btnW.setFont(new Font("굴림", Font.BOLD, 13));
		btnW.setBounds(182, 179, 46, 32);
		contentPanel.add(btnW);
		
		JButton btnE = new JButton("E");
		btnE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("E",tfState);
			}
		});
		btnE.setFont(new Font("굴림", Font.BOLD, 13));
		btnE.setBounds(226, 179, 45, 32);
		contentPanel.add(btnE);
		
		JButton btnR = new JButton("R");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("R",tfState);
			}
		});
		btnR.setFont(new Font("굴림", Font.BOLD, 13));
		btnR.setBounds(272, 179, 45, 32);
		contentPanel.add(btnR);
		
		JButton btnT = new JButton("T");
		btnT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("T",tfState);
			}
		});
		btnT.setFont(new Font("굴림", Font.BOLD, 13));
		btnT.setBounds(317, 179, 45, 32);
		contentPanel.add(btnT);
		
		JButton btnY = new JButton("Y");
		btnY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("Y",tfState);
			}
		});
		btnY.setFont(new Font("굴림", Font.BOLD, 13));
		btnY.setBounds(362, 179, 45, 32);
		contentPanel.add(btnY);
		
		JButton btnU = new JButton("U");
		btnU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("U",tfState);
			}
		});
		btnU.setFont(new Font("굴림", Font.BOLD, 13));
		btnU.setBounds(406, 179, 45, 32);
		contentPanel.add(btnU);
		
		JButton btnI = new JButton("I");
		btnI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("I",tfState);
			}
		});
		btnI.setFont(new Font("굴림", Font.BOLD, 13));
		btnI.setBounds(451, 179, 45, 32);
		contentPanel.add(btnI);
		
		JButton btnO = new JButton("O");
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("O",tfState);
			}
		});
		btnO.setFont(new Font("굴림", Font.BOLD, 13));
		btnO.setBounds(498, 179, 45, 32);
		contentPanel.add(btnO);
		
		JButton btnP = new JButton("P");
		btnP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("P",tfState);
			}
		});
		btnP.setFont(new Font("굴림", Font.BOLD, 13));
		btnP.setBounds(545, 179, 45, 32);
		contentPanel.add(btnP);
		
		JButton btnGwalhoLeft = new JButton("{");
		btnGwalhoLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("{",tfState);
			}
		});
		btnGwalhoLeft.setFont(new Font("굴림", Font.BOLD, 13));
		btnGwalhoLeft.setBounds(591, 179, 45, 32);
		contentPanel.add(btnGwalhoLeft);
		//// Alphabet
		JButton btnA = new JButton("A");
		btnA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("A",tfState);
			}
		});
		btnA.setFont(new Font("굴림", Font.BOLD, 13));
		btnA.setBounds(145, 213, 45, 32);
		contentPanel.add(btnA);
		
		JButton btnS = new JButton("S");
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("S",tfState);
			}
		});
		btnS.setFont(new Font("굴림", Font.BOLD, 13));
		btnS.setBounds(192, 213, 45, 32);
		contentPanel.add(btnS);
		
		JButton btnD = new JButton("D");
		btnD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("D",tfState);
			}
		});
		btnD.setFont(new Font("굴림", Font.BOLD, 13));
		btnD.setBounds(236, 213, 45, 32);
		contentPanel.add(btnD);
		
		JButton btnF = new JButton("F");
		btnF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("F",tfState);
			}
		});
		btnF.setFont(new Font("굴림", Font.BOLD, 13));
		btnF.setBounds(282, 213, 45, 32);
		contentPanel.add(btnF);
		
		JButton btnG = new JButton("G");
		btnG.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("G",tfState);
			}
		});
		btnG.setFont(new Font("굴림", Font.BOLD, 13));
		btnG.setBounds(327, 213, 45, 32);
		contentPanel.add(btnG);
		
		JButton btnH = new JButton("H");
		btnH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("H",tfState);
			}
		});
		btnH.setFont(new Font("굴림", Font.BOLD, 13));
		btnH.setBounds(372, 213, 45, 32);
		contentPanel.add(btnH);
		
		JButton btnJ = new JButton("J");
		btnJ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("J",tfState);
			}
		});
		btnJ.setFont(new Font("굴림", Font.BOLD, 13));
		btnJ.setBounds(416, 213, 45, 32);
		contentPanel.add(btnJ);
		
		JButton btnK = new JButton("K");
		btnK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("K",tfState);
			}
		});
		btnK.setFont(new Font("굴림", Font.BOLD, 13));
		btnK.setBounds(461, 213, 45, 32);
		contentPanel.add(btnK);
		
		JButton btnL = new JButton("L");
		btnL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("L",tfState);
			}
		});
		btnL.setFont(new Font("굴림", Font.BOLD, 13));
		btnL.setBounds(508, 213, 45, 32);
		contentPanel.add(btnL);
		
		JButton btnSemiColon = new JButton(";");
		btnSemiColon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn(";",tfState);
			}
		});
		btnSemiColon.setFont(new Font("굴림", Font.BOLD, 13));
		btnSemiColon.setBounds(555, 213, 45, 32);
		contentPanel.add(btnSemiColon);
		
		JButton btnDdaompyo = new JButton("'");
		btnDdaompyo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("'",tfState);
			}
		});
		btnDdaompyo.setFont(new Font("굴림", Font.BOLD, 13));
		btnDdaompyo.setBounds(601, 213, 45, 32);
		contentPanel.add(btnDdaompyo);
		
		JButton btnZ = new JButton("Z");
		btnZ.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("Z",tfState);
			}
		});
		btnZ.setFont(new Font("굴림", Font.BOLD, 13));
		btnZ.setBounds(155, 247, 45, 32);
		contentPanel.add(btnZ);
		
		JButton btnX = new JButton("X");
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("X",tfState);
			}
		});
		btnX.setFont(new Font("굴림", Font.BOLD, 13));
		btnX.setBounds(202, 247, 45, 32);
		contentPanel.add(btnX);
		
		JButton btnC = new JButton("C");
		btnC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("C",tfState);
			}
		});
		btnC.setFont(new Font("굴림", Font.BOLD, 13));
		btnC.setBounds(246, 247, 45, 32);
		contentPanel.add(btnC);
		
		JButton btnV = new JButton("V");
		btnV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("V",tfState);
			}
		});
		btnV.setFont(new Font("굴림", Font.BOLD, 13));
		btnV.setBounds(292, 247, 45, 32);
		contentPanel.add(btnV);
		
		JButton btnB = new JButton("B");
		btnB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("B",tfState);
			}
		});
		btnB.setFont(new Font("굴림", Font.BOLD, 13));
		btnB.setBounds(337, 247, 45, 32);
		contentPanel.add(btnB);
		
		JButton btnN = new JButton("N");
		btnN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("N",tfState);
			}
		});
		btnN.setFont(new Font("굴림", Font.BOLD, 13));
		btnN.setBounds(382, 247, 45, 32);
		contentPanel.add(btnN);
		
		JButton btnM = new JButton("M");
		btnM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("M",tfState);
			}
		});
		btnM.setFont(new Font("굴림", Font.BOLD, 13));
		btnM.setBounds(426, 247, 45, 32);
		contentPanel.add(btnM);
		
		JButton btnComma = new JButton(",");
		btnComma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn(",",tfState);
			}
		});
		btnComma.setFont(new Font("굴림", Font.BOLD, 13));
		btnComma.setBounds(471, 247, 45, 32);
		contentPanel.add(btnComma);
		
		JButton btnMachim = new JButton(".");
		btnMachim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn(".",tfState);
			}
		});
		btnMachim.setFont(new Font("굴림", Font.BOLD, 13));
		btnMachim.setBounds(518, 247, 45, 32);
		contentPanel.add(btnMachim);
		
		JButton btnSlash = new JButton("/");
		btnSlash.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("/",tfState);
			}
		});
		
		btnSlash.setFont(new Font("굴림", Font.BOLD, 13));
		btnSlash.setBounds(565, 247, 45, 32);
		contentPanel.add(btnSlash);
		/// shift action???
		JButton btnShift1 = new JButton("Shift");
		btnMachim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("shift?",tfState);
			}
		});
		btnShift1.setFont(new Font("굴림", Font.BOLD, 13));
		btnShift1.setBounds(611, 247, 119, 32);
		contentPanel.add(btnShift1);
		
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("<html><br></html>",tfState);
			}
		});
		btnEnter.setFont(new Font("굴림", Font.BOLD, 13));
		btnEnter.setBounds(647, 213, 83, 32);
		contentPanel.add(btnEnter);
		
		JButton btnGwalhoRight= new JButton("}");
		btnGwalhoRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("}",tfState);
			}
		});
		btnGwalhoRight.setFont(new Font("굴림", Font.BOLD, 13));
		btnGwalhoRight.setBounds(638, 179, 45, 32);
		contentPanel.add(btnGwalhoRight);
		
		JButton btnWon = new JButton("\\");
		btnWon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("\\",tfState);
			}
		});
		
		btnWon.setFont(new Font("굴림", Font.BOLD, 13));
		btnWon.setBounds(685, 179, 45, 32);
		contentPanel.add(btnWon);
		
		JButton btnShift2 = new JButton("Shift");
		btnShift2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("shift2",tfState);
			}
		});
		btnShift2.setFont(new Font("굴림", Font.BOLD, 13));
		btnShift2.setBounds(72, 247, 83, 32);
		contentPanel.add(btnShift2);
		
		JButton btnCapsLk = new JButton("CapsLk");
		btnCapsLk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpLowCase = !UpLowCase;
			}
		});
		btnCapsLk.setFont(new Font("굴림", Font.BOLD, 10));
		btnCapsLk.setBounds(72, 213, 70, 32);
		contentPanel.add(btnCapsLk);
		
		JButton btnTab = new JButton("Tab");
		btnTab.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("<pre>	</pre>",tfState);
			}
		});
		btnTab.setFont(new Font("굴림", Font.BOLD, 10));
		btnTab.setBounds(72, 179, 58, 32);
		contentPanel.add(btnTab);
		////숫자
		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("1",tfState);
			}
		});
		btn1.setFont(new Font("굴림", Font.BOLD, 13));
		btn1.setBounds(118, 145, 45, 32);
		contentPanel.add(btn1);
		
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("2",tfState);
			}
		});
		btn2.setFont(new Font("굴림", Font.BOLD, 13));
		btn2.setBounds(165, 145, 45, 32);
		contentPanel.add(btn2);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("3",tfState);
			}
		});
		btn3.setFont(new Font("굴림", Font.BOLD, 13));
		btn3.setBounds(212, 145, 45, 32);
		contentPanel.add(btn3);
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("4",tfState);
			}
		});
		btn4.setFont(new Font("굴림", Font.BOLD, 13));
		btn4.setBounds(256, 145, 45, 32);
		contentPanel.add(btn4);
		
		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("5",tfState);
			}
		});

		btn5.setFont(new Font("굴림", Font.BOLD, 13));
		btn5.setBounds(302, 145, 45, 32);
		contentPanel.add(btn5);
		
		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("6",tfState);
			}
		});

		btn6.setFont(new Font("굴림", Font.BOLD, 13));
		btn6.setBounds(347, 145, 45, 32);
		contentPanel.add(btn6);
		
		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("7",tfState);
			}
		});
		btn7.setFont(new Font("굴림", Font.BOLD, 13));
		btn7.setBounds(392, 145, 45, 32);
		contentPanel.add(btn7);
		
		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("8",tfState);
			}
		});
		btn8.setFont(new Font("굴림", Font.BOLD, 13));
		btn8.setBounds(436, 145, 45, 32);
		contentPanel.add(btn8);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("9",tfState);
			}
		});
		btn9.setFont(new Font("굴림", Font.BOLD, 13));
		btn9.setBounds(481, 145, 45, 32);
		contentPanel.add(btn9);
		
		JButton btn0 = new JButton("0");
		btn0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("0",tfState);
			}
		});
		btn0.setFont(new Font("굴림", Font.BOLD, 13));
		btn0.setBounds(528, 145, 45, 32);
		contentPanel.add(btn0);
		
		JButton btnMinus = new JButton("-");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("-",tfState);
			}
		});
		btnMinus.setFont(new Font("굴림", Font.BOLD, 13));
		btnMinus.setBounds(575, 145, 45, 32);
		contentPanel.add(btnMinus);
		
		JButton btnPlus = new JButton("+");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				keyReturn("+",tfState);
			}
		});
		btnPlus.setFont(new Font("굴림", Font.BOLD, 13));
		btnPlus.setBounds(624, 145, 45, 32);
		contentPanel.add(btnPlus);
		
		JButton btnBackSpace = new JButton("Back");
		btnBackSpace.setFont(new Font("굴림", Font.BOLD, 11));
		btnBackSpace.setBounds(670, 145, 60, 32);
		contentPanel.add(btnBackSpace);
		
		JButton btnLeftof1 = new JButton("`");
		btnLeftof1.setFont(new Font("굴림", Font.BOLD, 13));
		btnLeftof1.setBounds(72, 145, 45, 32);
		contentPanel.add(btnLeftof1);
		
		JButton btnCtrl = new JButton("Ctrl");
		btnCtrl.setFont(new Font("굴림", Font.BOLD, 13));
		btnCtrl.setBounds(72, 281, 58, 32);
		contentPanel.add(btnCtrl);
		
		JButton btnAlt = new JButton("Alt");
		btnAlt.setFont(new Font("굴림", Font.BOLD, 13));
		btnAlt.setBounds(135, 281, 55, 32);
		contentPanel.add(btnAlt);
		
		JButton btnSpace = new JButton("Space");
		btnSpace.setFont(new Font("굴림", Font.BOLD, 13));
		btnSpace.setBounds(192, 281, 324, 32);
		contentPanel.add(btnSpace);
		
		JButton btnLeft = new JButton("←");
		btnLeft.setFont(new Font("굴림", Font.BOLD, 13));
		btnLeft.setBounds(518, 281, 45, 32);
		contentPanel.add(btnLeft);
		
		JButton btnUp = new JButton("↑");
		btnUp.setFont(new Font("굴림", Font.BOLD, 9));
		btnUp.setBounds(565, 281, 45, 14);
		contentPanel.add(btnUp);
		
		JButton btnTransKorAndEng = new JButton("한/영");
		btnTransKorAndEng.setFont(new Font("굴림", Font.BOLD, 13));
		btnTransKorAndEng.setBounds(660, 281, 70, 32);
		contentPanel.add(btnTransKorAndEng);
		
		JButton btnDown = new JButton("↓");
		btnDown.setFont(new Font("굴림", Font.BOLD, 9));
		btnDown.setBounds(565, 299, 45, 14);
		contentPanel.add(btnDown);
		
		JButton btnRight = new JButton("→");
		btnRight.setFont(new Font("굴림", Font.BOLD, 13));
		btnRight.setBounds(614, 281, 45, 32);
		contentPanel.add(btnRight);
		
		tfState = new JTextField();
		tfState.setEditable(false);
		tfState.setHorizontalAlignment(SwingConstants.CENTER);
		tfState.setBounds(406, 103, 70, 32);
		contentPanel.add(tfState);
		tfState.setColumns(10);
		
		JButton btnLeftof1_1 = new JButton("");
		btnLeftof1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				keyboadOnOff(!ShareVar.keyboard);
				
			}
		});
		btnLeftof1_1.setIcon(new ImageIcon(Keyboard.class.getResource("/com/javalec/image/keyBoard.png")));
		btnLeftof1_1.setFont(new Font("굴림", Font.BOLD, 13));
		btnLeftof1_1.setBounds(362, 103, 31, 32);
		contentPanel.add(btnLeftof1_1);
	}
	
	//Function
	
	public JTextField keyReturn(String character, JTextField tfState /*Up =1, Down =0*/) {
		
		if(UpLowCase) {
			character =character.toUpperCase();
		}else {
			character =character.toLowerCase();
		}
		
		resulOfTyped.add(character);
		String str = String.join("", resulOfTyped);
		
		tfState.setText(str);
		
		return tfState;
		

	}
	
	public void keyboadOnOff(boolean currentState) {
		
		ShareVar.keyboard = currentState;
				
		if(currentState) {
			this.setVisible(true);
		}
		else {
			this.setVisible(false);
		}
		
		
	}
	
	
	
	
	
	
} //End





