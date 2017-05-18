package csasimulator;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Console class: Input/Output UI for CPU
 * @author phani
 *
 */
public class Console {

	private JFrame frmConsole;
	private JTextField memAddrField;
	private JTextField memDataField;
	private JTextField r0InpField;
	private JTextField r1InpField;
	private JTextField r2InpField;
	private JTextField r3InpField;
	private JTextField ix1InpField;
	private JTextField ix2InpField;
	private JTextField ix3InpField;
	private JTextField marInpField;
	private JTextField mbrInpField;
	
	private JRadioButton rbsINSbits[];
	private JRadioButton rbsR0bits[];
	private JRadioButton rbsR1bits[];
	private JRadioButton rbsR2bits[];
	private JRadioButton rbsR3bits[];
	private JRadioButton rbsIX1bits[];
	private JRadioButton rbsIX2bits[];
	private JRadioButton rbsIX3bits[];
	private JRadioButton rbsMARbits[];
	private JRadioButton rbsMBRbits[];
	
	private JLabel lblOpCodeVal;
	private JLabel lblR0Val;
	private JLabel lblR1Val;
	private JLabel lblR2Val;
	private JLabel lblR3Val;
	private JLabel lblIx1Val;
	private JLabel lblIx2Val;
	private JLabel lblIx3Val;
	private JLabel lblMarVal;
	private JLabel lblMbrVal;
	
	private JButton btnDepositMem;
	private JButton btnSingleStep;
	private JButton btnR0Deposit;
	private JButton btnR1Deposit;
	private JButton btnR2Deposit;
	private JButton btnR3Deposit;
	private JButton btnIx1Deposit;
	private JButton btnIx2Deposit;
	private JButton btnIx3Deposit;
	private JButton btnMarDeposit;
	private JButton btnMbrDeposit;
	
	private JPanel memPanel;
	private JPanel insPanel;
	private JPanel r0Panel;
	private JPanel r1Panel;
	private JPanel r2Panel;
	private JPanel r3Panel;
	private JPanel ix1Panel;
	private JPanel ix2Panel;
	private JPanel ix3Panel;
	private JPanel marPanel;
	private JPanel mbrPanel;
	private JPanel keyPanel;
	private JPanel iplPanel;
	private JPanel conPanel;
	
	private JButton btnProgram1;
	private JButton btnProgram2;
	private JButton btnProgram3;
	private JButton btnEnter;
	private JButton btnClear;
	
	private Processor cpu;
	private Memory mem;
	private Keyboard kb;
	private CardReader cr;
	private JTextArea textArea;
	private JTextField textKeyField;
	
	private JToggleButton tglbtnOnoff;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Console window = new Console();
						window.frmConsole.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}); 	
	}

	/**
	 * Create the application.
	 */
	public Console() {
		mem = new Memory();
		kb = new Keyboard();
		cr = new CardReader();
		//mem.debug();
		cpu = new Processor(this, mem, kb, cr);
		
		initialize();
		disablePress();
	}
	
	public int getStatus() {
		return 0;
	}
	
	/* 
	 * Disable all the "Deposit" buttons 
	 */
	public void disablePress() {
		
		btnDepositMem.setVisible(false);
        btnSingleStep.setVisible(false);
        btnR0Deposit.setVisible(false);
        btnR1Deposit.setVisible(false);
        btnR2Deposit.setVisible(false);
        btnR3Deposit.setVisible(false);
        btnIx1Deposit.setVisible(false);
        btnIx2Deposit.setVisible(false);
        btnIx3Deposit.setVisible(false);
        btnMarDeposit.setVisible(false);
        btnMbrDeposit.setVisible(false);
        btnProgram1.setVisible(false);
        btnProgram2.setVisible(false);
        btnProgram3.setVisible(false);
        btnEnter.setVisible(false);
        btnClear.setVisible(false);

		memPanel.setEnabled(false);
		insPanel.setEnabled(false);
		r0Panel.setEnabled(false);
		r1Panel.setEnabled(false);
		r2Panel.setEnabled(false);
		r3Panel.setEnabled(false);
		ix1Panel.setEnabled(false);
		ix2Panel.setEnabled(false);
		ix3Panel.setEnabled(false);
		marPanel.setEnabled(false);
		mbrPanel.setEnabled(false);
		keyPanel.setEnabled(false);
		iplPanel.setEnabled(false);
		conPanel.setEnabled(false);
		
		tglbtnOnoff.setText("RUN");
	}

	/* 
	 * Enable all the "Deposit" buttons 
	 */
	public void enablePress() {
		
        btnDepositMem.setVisible(true);
        btnSingleStep.setVisible(true);
        btnR0Deposit.setVisible(true);
        btnR1Deposit.setVisible(true);
        btnR2Deposit.setVisible(true);
        btnR3Deposit.setVisible(true);
        btnIx1Deposit.setVisible(true);
        btnIx2Deposit.setVisible(true);
        btnIx3Deposit.setVisible(true);
        btnMarDeposit.setVisible(true);
        btnMbrDeposit.setVisible(true);
        btnProgram1.setVisible(true);
        btnProgram2.setVisible(true);
        btnProgram3.setVisible(true);
        btnEnter.setVisible(true);
        btnClear.setVisible(true);

        //tglbtnOnoff.setVisible(true);

		memPanel.setEnabled(true);
		insPanel.setEnabled(true);
		r0Panel.setEnabled(true);
		r1Panel.setEnabled(true);
		r2Panel.setEnabled(true);
		r3Panel.setEnabled(true);
		ix1Panel.setEnabled(true);
		ix2Panel.setEnabled(true);
		ix3Panel.setEnabled(true);
		marPanel.setEnabled(true);
		mbrPanel.setEnabled(true);
		keyPanel.setEnabled(true);
		iplPanel.setEnabled(true);
		conPanel.setEnabled(true);
		
		tglbtnOnoff.setText("HALT");
	}

	/*
	 * Load the register with bitmap from UI (radio buttons input)
	 */
	public String getBitmap(Register r) {
		
		String regStr = "";
		JRadioButton rbBits[] = rbsINSbits;
		
		if (r.getName() == "R0") {
			rbBits = rbsR0bits;
		} else if (r.getName() == "R1") {
			rbBits = rbsR1bits;
		} else if (r.getName() == "R2") {
			rbBits = rbsR2bits;
		} else if (r.getName() == "R3") {
			rbBits = rbsR3bits;
		} else if (r.getName() == "IX1") {
			rbBits = rbsIX1bits;
		} else if (r.getName() == "IX2") {
			rbBits = rbsIX2bits;
		} else if (r.getName() == "IX3") {
			rbBits = rbsIX3bits;
		} else if (r.getName() == "MAR") {
			rbBits = rbsMARbits;
		} else if (r.getName() == "MBR") {
			rbBits = rbsMBRbits;
		}  
	
		if (r.getName() == "IR") {
			/*
			 * IR loaded from "Instruction" radio buttons but have
			 * strange bit offsets.. 
			 */
			for (int i = 0; i < r.getBitLength(); i ++) {
				regStr += (rbBits[i].isSelected() ? "1" : "0");
			}
			System.out.println(regStr);
			System.out.println(Integer.parseInt(regStr, 2));
		} else
			for (int i = r.getBitLength() - 1; i >= 0; i --) {
				regStr += rbBits[i].isSelected() ? "1" : "0";
			}

		r.setValue(Integer.parseInt(regStr, 2));
		print(r);
		return regStr;
	}

	/*
	 * Get the opcode from "Instruction" radio buttons
	 */
	public String getOpcode() {
		String regStr = "";
		JRadioButton rbBits[] = rbsINSbits;
		for (int i = 0; i < InstructionSet.OPCODE_LENGTH; i ++) {
			regStr += (rbBits[i].isSelected() ? "1" : "0");
		}
		
		return regStr;
	}
	
	/*
	 * Set bitmap for registers in the UI
	 */	
	public void setBitmap(Register r) {
		JRadioButton rbBits[] = null;
		JLabel lblVal = null;
		if (r.getName() == "R0") {
			rbBits = rbsR0bits;
			lblVal = lblR0Val;
		} else if (r.getName() == "R1") {
			rbBits = rbsR1bits;
			lblVal = lblR1Val;
		} else if (r.getName() == "R2") {
			rbBits = rbsR2bits;
			lblVal = lblR2Val;
		} else if (r.getName() == "R3") {
			rbBits = rbsR3bits;
			lblVal = lblR3Val;
		} else if (r.getName() == "IX1") {
			rbBits = rbsIX1bits;
			lblVal = lblIx1Val;
		} else if (r.getName() == "IX2") {
			rbBits = rbsIX2bits;
			lblVal = lblIx2Val;
		} else if (r.getName() == "IX3") {
			rbBits = rbsIX3bits;
			lblVal = lblIx3Val;
		} else if (r.getName() == "MAR") {
			rbBits = rbsMARbits;
			lblVal = lblMarVal;
		} else if (r.getName() == "MBR") {
			rbBits = rbsMBRbits;
			lblVal = lblMbrVal;
		}  

		String bitStr = Integer.toBinaryString(r.getValue());
		String zeroStr = "0000000000000000";
		System.out.println(bitStr);
		
		// if binary string is less than the bitlength of register, 
		// prefix zeros.
		// if it's greater, ignore characters over bitlength
		if(bitStr.length() > r.getBitLength()) {
			bitStr.substring(bitStr.length() - r.getBitLength(), r.getBitLength());
		} else if(bitStr.length() < r.getBitLength()) {
			bitStr = zeroStr.substring(bitStr.length()) + bitStr;
			System.out.println(bitStr);
		}
		
		
		for (int i = r.getBitLength() - 1, j = 0; i >= 0 && j < r.getBitLength(); i --, j ++) {
			rbBits[i].setSelected(bitStr.charAt(j) == '0' ? false : true);
		}
		// set also the "VAL" field.
		lblVal.setText(Integer.toString(r.getValue()));
	}

	/*
	 * Set bitmap for registers in the UI
	 */	
	public void clearBitmap(Register r) {
		JRadioButton rbBits[] = rbsINSbits;
		JLabel lblVal = lblOpCodeVal;
		if (r.getName() == "R0") {
			rbBits = rbsR0bits;
			lblVal = lblR0Val;
		} else if (r.getName() == "R1") {
			rbBits = rbsR1bits;
			lblVal = lblR1Val;
		} else if (r.getName() == "R2") {
			rbBits = rbsR2bits;
			lblVal = lblR2Val;
		} else if (r.getName() == "R3") {
			rbBits = rbsR3bits;
			lblVal = lblR3Val;
		} else if (r.getName() == "IX1") {
			rbBits = rbsIX1bits;
			lblVal = lblIx1Val;
		} else if (r.getName() == "IX2") {
			rbBits = rbsIX2bits;
			lblVal = lblIx2Val;
		} else if (r.getName() == "IX3") {
			rbBits = rbsIX3bits;
			lblVal = lblIx3Val;
		} else if (r.getName() == "MAR") {
			rbBits = rbsMARbits;
			lblVal = lblMarVal;
		} else if (r.getName() == "MBR") {
			rbBits = rbsMBRbits;
			lblVal = lblMbrVal;
		}  

		String bitStr = "0000000000000000";
		System.out.println(bitStr);
				
		
		for (int i = r.getBitLength() - 1, j = 0; i >= 0 && j < r.getBitLength(); i --, j ++) {
			rbBits[i].setSelected(bitStr.charAt(j) == '0' ? false : true);
		}
		// set also the "VAL" field.
		lblVal.setText("VAL");
		if (r.getName() == "IR")
			lblVal.setText("000000");
	}

	
	/*
	 * write to UI the register value.
	 */
	public void print(Register r) {
	
		if (r.getName() == "IR") {
			lblOpCodeVal.setText(getOpcode());
		} else 
			setBitmap(r);
	}
	
	public void print(String str) {
		System.out.println("print str enter");
		String txt = textArea.getText();
		String[] lines = txt.split("\r\n|\r|\n");
		
		if (lines.length >= 28) {
			txt = "";
			for (int i = 1; i < 28; i ++) {
				txt += (lines[i] + "\n");
			}
		}
		/*String [] linesStr = str.split(".");
		
		for (int i = 0; i < linesStr.length; i ++) {
			txt += (linesStr[i] + "\n");
		}*/
		
		txt += str;
		textArea.setText(txt);
		System.out.println("print str exit");
	}
	
	public void print(char ch) {
		System.out.println("print ch enter: " + ch);
		String txt = textArea.getText();
		String[] lines = txt.split("\r\n|\r|\n");
		
		if (lines.length >= 28) {
			txt = "";
			for (int i = 1; i < 28; i ++) {
				txt += (lines[i] + "\n");
			}
		}
				
		txt += ch;
		
		textArea.setText(txt);
		System.out.println("print ch exit");
	}
	
	public void print(int val) {
		System.out.println("print int enter");
		String txt = textArea.getText();
		String[] lines = txt.split("\r\n|\r|\n");
		
		if (lines.length >= 28) {
			txt = "";
			for (int i = 1; i < 28; i ++) {
				txt += (lines[i] + "\n");
			}
		}
				
		txt += Integer.toString(val) + "\n";
		textArea.setText(txt);
		System.out.println(txt);
		System.out.println("print int exit");
	}
	
	public void print(float val) {
		System.out.println("print float enter");
		String txt = textArea.getText();
		String[] lines = txt.split("\r\n|\r|\n");
		
		if (lines.length >= 28) {
			txt = "";
			for (int i = 1; i < 28; i ++) {
				txt += (lines[i] + "\n");
			}
		}
				
		txt += Float.toString(val) + "\n";
		textArea.setText(txt);
		System.out.println(txt);
		System.out.println("print float exit");
	}
	
	public void printChar(int val) {
		System.out.println("print int enter");
		String txt = textArea.getText();
		String[] lines = txt.split("\r\n|\r|\n");
		
		if (lines.length >= 28) {
			txt = "";
			for (int i = 1; i < 28; i ++) {
				txt += (lines[i] + "\n");
			}
		}
		
		if (val >= 40 && val <= 49) {
			val -= 40;
		} else { print((char)(val)); return ; }
		
		txt += Integer.toString(val) + "\n";
		textArea.setText(txt);
		System.out.println(txt);
		System.out.println("print int exit");
	}

	
	public String input() {
		String inpstr = "";

		
		return "";
	}
	
	public int scan() {
		
		
		return 0;
	}
	
	public void loadProgram(String filename) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
		    String line = br.readLine();
		    
		    int currAddr = Processor.PROGRAM_START_ADDR;
		    while (line != null) {
		    	String[] code = line.split(";");
		        mem.setData(currAddr, Integer.parseInt(code[0], 2));
		        currAddr ++;
		        line = br.readLine();
		    }

		    br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadInput(String filename) {
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
		    String line = br.readLine();
		    
		    mem.setData(Processor.INPUT_START_ADDR - 1, line.length());
		    for (int i = 0; i < line.length(); i ++) {
		    	mem.setData(Processor.INPUT_START_ADDR + i, line.charAt(i));
		    }
		    mem.setData(Processor.INPUT_START_ADDR + line.length(), 0); //terminating char.
		    print(line);

		    br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

        rbsINSbits = new JRadioButton[16];
        rbsR0bits = new JRadioButton[16];
        rbsR1bits = new JRadioButton[16];
        rbsR2bits = new JRadioButton[16];
        rbsR3bits = new JRadioButton[16];
        rbsIX1bits = new JRadioButton[16];
        rbsIX2bits = new JRadioButton[16];
        rbsIX3bits = new JRadioButton[16];
        rbsMARbits = new JRadioButton[16];
        rbsMBRbits = new JRadioButton[16];
		
		frmConsole = new JFrame();
		//frmConsole.setAlwaysOnTop(true);
		frmConsole.setFont(new Font("Dialog", Font.PLAIN, 12));
		frmConsole.setTitle("Console");
		frmConsole.setBounds(100, 100, 960, 720);
		frmConsole.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConsole.getContentPane().setLayout(null);
		
		keyPanel = new JPanel();
		keyPanel.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)), "Keyboard", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		keyPanel.setBounds(665, 25, 275, 75);
		frmConsole.getContentPane().add(keyPanel);
		keyPanel.setLayout(null);
		
		textKeyField = new JTextField();
		textKeyField.setBounds(10, 20, 150, 40);
		keyPanel.add(textKeyField);
		textKeyField.setColumns(10);
		
		btnEnter = new JButton("Enter");
		btnEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//read that text and save it to a String.
				kb.setScanner(textKeyField.getText());
				mem.setData(Processor.KEY_DATA_SIZE_LOC, textKeyField.getText().length());
				textKeyField.setText("");
			}
		});
		btnEnter.setBounds(180, 20, 80, 40);
		keyPanel.add(btnEnter);
		
		memPanel = new JPanel();
		memPanel.setBorder(new TitledBorder(new LineBorder(null, 1, true), "Memory", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		memPanel.setBounds(20, 85, 190, 100);
		frmConsole.getContentPane().add(memPanel);
		memPanel.setLayout(null);
		
		memAddrField = new JTextField();
		memAddrField.setBounds(10, 45, 80, 20);
		memPanel.add(memAddrField);
		memAddrField.setBackground(UIManager.getColor("EditorPane.background"));
		memAddrField.setColumns(10);
		
		memDataField = new JTextField();
		memDataField.setBounds(100, 45, 80, 20);
		memPanel.add(memDataField);
		memDataField.setBackground(UIManager.getColor("EditorPane.background"));
		memDataField.setColumns(10);
		
		btnDepositMem = new JButton("Deposit");
		btnDepositMem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				String addr = memAddrField.getText();
				String data = memDataField.getText();
				if (addr.isEmpty() || data.isEmpty()) {
					System.err.println("Memory: Invalid input");
					return;
				}
				try {
					mem.setData(Integer.parseInt(memAddrField.getText()), Integer.parseInt(memDataField.getText()));
					memAddrField.setText("");
					memDataField.setText("");
				} catch (NumberFormatException nfe) {
					System.err.println("Memory: Invalid format");
				}
			}
		});
		btnDepositMem.setBounds(45, 70, 80, 20);
		memPanel.add(btnDepositMem);
		btnDepositMem.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JLabel lblMemAddress = new JLabel("Address");
		lblMemAddress.setFont(new Font("Dialog", Font.BOLD, 12));
		lblMemAddress.setBounds(20, 20, 70, 15);
		memPanel.add(lblMemAddress);
		
		JLabel lblMemData = new JLabel("Data");
		lblMemData.setFont(new Font("Dialog", Font.BOLD, 12));
		lblMemData.setBounds(110, 20, 70, 15);
		memPanel.add(lblMemData);
		
		insPanel = new JPanel();
		insPanel.setBorder(new TitledBorder(new LineBorder(null, 1, true), "Instruction", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		insPanel.setBounds(240, 85, 410, 100);
		frmConsole.getContentPane().add(insPanel);
		insPanel.setLayout(null);
		
		JRadioButton rbIns0 = new JRadioButton("0");
		rbIns0.setBounds(5, 17, 50, 20);
		insPanel.add(rbIns0);
		rbsINSbits[0] = rbIns0;
		
		btnSingleStep = new JButton("Single Step");
		btnSingleStep.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				lblOpCodeVal.setText("");

				cpu.runStep();

			}
		});
		btnSingleStep.setBounds(250, 70, 150, 20);
		insPanel.add(btnSingleStep);
		btnSingleStep.setFont(new Font("Dialog", Font.PLAIN, 10));
		
		JRadioButton rbIns1 = new JRadioButton("1");
		rbIns1.setBounds(55, 17, 50, 20);
		insPanel.add(rbIns1);
		rbsINSbits[1] = rbIns1;
		
		JRadioButton rbIns2 = new JRadioButton("2");
		rbIns2.setBounds(105, 17, 50, 20);
		insPanel.add(rbIns2);
		rbsINSbits[2] = rbIns2;
		
		JRadioButton rbIns3 = new JRadioButton("3");
		rbIns3.setBounds(155, 17, 50, 20);
		insPanel.add(rbIns3);
		rbsINSbits[3] = rbIns3;
		
		JRadioButton rbIns4 = new JRadioButton("4");
		rbIns4.setBounds(205, 17, 50, 20);
		insPanel.add(rbIns4);
		rbsINSbits[4] = rbIns4;
		
		JRadioButton rbIns5 = new JRadioButton("5");
		rbIns5.setBounds(255, 17, 50, 20);
		insPanel.add(rbIns5);
		rbsINSbits[5] = rbIns5;
		
		JRadioButton rbIns6 = new JRadioButton("6");
		rbIns6.setBounds(305, 17, 50, 20);
		insPanel.add(rbIns6);
		rbsINSbits[6] = rbIns6;
		
		JRadioButton rbIns7 = new JRadioButton("7");
		rbIns7.setBounds(355, 17, 40, 20);
		insPanel.add(rbIns7);
		rbsINSbits[7] = rbIns7;
		
		JRadioButton rbIns8 = new JRadioButton("8");
		rbIns8.setBounds(5, 42, 50, 20);
		insPanel.add(rbIns8);
		rbsINSbits[8] = rbIns8;
		
		JRadioButton rbIns9 = new JRadioButton("9");
		rbIns9.setBounds(55, 42, 50, 20);
		insPanel.add(rbIns9);
		rbsINSbits[9] = rbIns9;
		
		JRadioButton rbIns10 = new JRadioButton("10");
		rbIns10.setBounds(105, 42, 50, 20);
		insPanel.add(rbIns10);
		rbsINSbits[10] = rbIns10;
		
		JRadioButton rbIns11 = new JRadioButton("11");
		rbIns11.setBounds(155, 42, 50, 20);
		insPanel.add(rbIns11);
		rbsINSbits[11] = rbIns11;
		
		JRadioButton rbIns12 = new JRadioButton("12");
		rbIns12.setBounds(205, 42, 50, 20);
		insPanel.add(rbIns12);
		rbsINSbits[12] = rbIns12;
		
		JRadioButton rbIns13 = new JRadioButton("13");
		rbIns13.setBounds(255, 42, 50, 20);
		insPanel.add(rbIns13);
		rbsINSbits[13] = rbIns13;
		
		JRadioButton rbIns14 = new JRadioButton("14");
		rbIns14.setBounds(305, 42, 50, 20);
		insPanel.add(rbIns14);
		rbsINSbits[14] = rbIns14;
		
		JRadioButton rbIns15 = new JRadioButton("15");
		rbIns15.setBounds(355, 42, 50, 20);
		insPanel.add(rbIns15);
		rbsINSbits[15] = rbIns15;
		
		JLabel lblOpcd = new JLabel("OPCODE:");
		lblOpcd.setHorizontalAlignment(SwingConstants.CENTER);
		lblOpcd.setForeground(Color.BLACK);
		lblOpcd.setFont(new Font("Dialog", Font.BOLD, 12));
		lblOpcd.setBounds(10, 70, 70, 20);
		insPanel.add(lblOpcd);
		
		lblOpCodeVal = new JLabel("000000");
		lblOpCodeVal.setHorizontalAlignment(SwingConstants.LEFT);
		lblOpCodeVal.setForeground(Color.BLACK);
		lblOpCodeVal.setFont(new Font("Dialog", Font.BOLD, 12));
		lblOpCodeVal.setBounds(80, 70, 170, 20);
		insPanel.add(lblOpCodeVal);
		
		r0Panel = new JPanel();
		r0Panel.setLayout(null);
		r0Panel.setBorder(new TitledBorder(new LineBorder(null), "R0", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		r0Panel.setBounds(20, 190, 630, 45);
		frmConsole.getContentPane().add(r0Panel);
		
		JRadioButton rbR015 = new JRadioButton("");
		rbR015.setBounds(25, 17, 20, 20);
		r0Panel.add(rbR015);
		rbsR0bits[15] = rbR015;
		
		JLabel r0bit15 = new JLabel("15");
		r0bit15.setHorizontalAlignment(SwingConstants.CENTER);
		r0bit15.setForeground(Color.GRAY);
		r0bit15.setFont(new Font("Dialog", Font.PLAIN, 12));
		r0bit15.setBounds(5, 17, 20, 20);
		r0Panel.add(r0bit15);
		
		r0InpField = new JTextField();
		r0InpField.setColumns(10);
		r0InpField.setBackground(Color.WHITE);
		r0InpField.setBounds(490, 17, 45, 20);
		r0Panel.add(r0InpField);
		
		lblR0Val = new JLabel("VAL");
		lblR0Val.setForeground(Color.RED);
		lblR0Val.setHorizontalAlignment(SwingConstants.CENTER);
		lblR0Val.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblR0Val.setBounds(445, 17, 45, 20);
		r0Panel.add(lblR0Val);
		
		btnR0Deposit = new JButton("Deposit");
		btnR0Deposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				String bits = r0InpField.getText();
				if (bits.isEmpty()) {
					System.err.println("R0: Invalid input");
					return;
				}
				try {
					cpu.setR(0, new Register("tmp", Integer.parseInt(bits),cpu.getR(0).getBitLength()));
					setBitmap(cpu.getR(0));
					r0InpField.setText("");
				} catch (NumberFormatException nfe) {
					System.err.println("R0: Invalid format");
				}
			}
		});
		btnR0Deposit.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnR0Deposit.setBounds(540, 17, 80, 20);
		r0Panel.add(btnR0Deposit);
		
		JRadioButton rbR014 = new JRadioButton("");
		rbR014.setBounds(50, 17, 20, 20);
		r0Panel.add(rbR014);
		rbsR0bits[14] = rbR014;
		
		JRadioButton rbR013 = new JRadioButton("");
		rbR013.setBounds(75, 17, 20, 20);
		r0Panel.add(rbR013);
		rbsR0bits[13] = rbR013;
		
		JRadioButton rbR012 = new JRadioButton("");
		rbR012.setBounds(100, 17, 20, 20);
		r0Panel.add(rbR012);
		rbsR0bits[12] = rbR012;
		
		JRadioButton rbR011 = new JRadioButton("");
		rbR011.setBounds(125, 17, 20, 20);
		r0Panel.add(rbR011);
		rbsR0bits[11] = rbR011;
		
		JRadioButton rbR010 = new JRadioButton("");
		rbR010.setBounds(150, 17, 20, 20);
		r0Panel.add(rbR010);
		rbsR0bits[10] = rbR010;
		
		JRadioButton rbR009 = new JRadioButton("");
		rbR009.setBounds(175, 17, 20, 20);
		r0Panel.add(rbR009);
		rbsR0bits[9] = rbR009;
		
		JRadioButton rbR008 = new JRadioButton("");
		rbR008.setBounds(200, 17, 20, 20);
		r0Panel.add(rbR008);
		rbsR0bits[8] = rbR008;

		JRadioButton rbR007 = new JRadioButton("");
		rbR007.setBounds(225, 17, 20, 20);
		r0Panel.add(rbR007);
		rbsR0bits[7] = rbR007;

		JRadioButton rbR006 = new JRadioButton("");
		rbR006.setBounds(250, 17, 20, 20);
		r0Panel.add(rbR006);
		rbsR0bits[6] = rbR006;

		JRadioButton rbR005 = new JRadioButton("");
		rbR005.setBounds(275, 17, 20, 20);
		r0Panel.add(rbR005);
		rbsR0bits[5] = rbR005;

		JRadioButton rbR004 = new JRadioButton("");
		rbR004.setBounds(300, 17, 20, 20);
		r0Panel.add(rbR004);
		rbsR0bits[4] = rbR004;

		JRadioButton rbR003 = new JRadioButton("");
		rbR003.setBounds(325, 17, 20, 20);
		r0Panel.add(rbR003);
		rbsR0bits[3] = rbR003;

		JRadioButton rbR002 = new JRadioButton("");
		rbR002.setBounds(350, 17, 20, 20);
		r0Panel.add(rbR002);
		rbsR0bits[2] = rbR002;

		JRadioButton rbR001 = new JRadioButton("");
		rbR001.setBounds(375, 17, 20, 20);
		r0Panel.add(rbR001);
		rbsR0bits[1] = rbR001;

		JRadioButton rbR000 = new JRadioButton("0");
		rbR000.setForeground(Color.GRAY);
		rbR000.setFont(new Font("Dialog", Font.PLAIN, 12));
		rbR000.setBounds(400, 17, 50, 20);
		r0Panel.add(rbR000);
		rbsR0bits[0] = rbR000;

		r1Panel = new JPanel();
		r1Panel.setLayout(null);
		r1Panel.setBorder(new TitledBorder(new LineBorder(null, 1, true), "R1", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		r1Panel.setBounds(20, 240, 630, 50);
		frmConsole.getContentPane().add(r1Panel);
		
		JRadioButton rbR115 = new JRadioButton("");
		rbR115.setBounds(25, 17, 20, 20);
		r1Panel.add(rbR115);
		rbsR1bits[15] = rbR115;

		JLabel r1bit15 = new JLabel("15");
		r1bit15.setHorizontalAlignment(SwingConstants.CENTER);
		r1bit15.setForeground(Color.GRAY);
		r1bit15.setFont(new Font("Dialog", Font.PLAIN, 12));
		r1bit15.setBounds(5, 17, 20, 20);
		r1Panel.add(r1bit15);
		
		r1InpField = new JTextField();
		r1InpField.setColumns(10);
		r1InpField.setBackground(Color.WHITE);
		r1InpField.setBounds(490, 17, 45, 20);
		r1Panel.add(r1InpField);
		
		lblR1Val = new JLabel("VAL");
		lblR1Val.setHorizontalAlignment(SwingConstants.CENTER);
		lblR1Val.setForeground(Color.RED);
		lblR1Val.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblR1Val.setBounds(445, 17, 45, 20);
		r1Panel.add(lblR1Val);
		
		btnR1Deposit = new JButton("Deposit");
		btnR1Deposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String bits = r1InpField.getText();
				if (bits.isEmpty()) {
					System.err.println("R1: Invalid input");
					return;
				}
				try {
					cpu.setR(1, new Register("tmp", Integer.parseInt(bits),cpu.getR(1).getBitLength()));
					setBitmap(cpu.getR(1));
					r1InpField.setText("");
				} catch (NumberFormatException nfe) {
					System.err.println("R1: Invalid format");
				}
			}
		});
		btnR1Deposit.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnR1Deposit.setBounds(540, 17, 80, 20);
		r1Panel.add(btnR1Deposit);
		
		JRadioButton rbR114 = new JRadioButton("");
		rbR114.setBounds(50, 17, 20, 20);
		r1Panel.add(rbR114);
		rbsR1bits[14] = rbR114;
		
		JRadioButton rbR113 = new JRadioButton("");
		rbR113.setBounds(75, 17, 20, 20);
		r1Panel.add(rbR113);
		rbsR1bits[13] = rbR113;
		
		JRadioButton rbR112 = new JRadioButton("");
		rbR112.setBounds(100, 17, 20, 20);
		r1Panel.add(rbR112);
		rbsR1bits[12] = rbR112;
		
		JRadioButton rbR111 = new JRadioButton("");
		rbR111.setBounds(125, 17, 20, 20);
		r1Panel.add(rbR111);
		rbsR1bits[11] = rbR111;
		
		JRadioButton rbR110 = new JRadioButton("");
		rbR110.setBounds(150, 17, 20, 20);
		r1Panel.add(rbR110);
		rbsR1bits[10] = rbR110;
		
		JRadioButton rbR109 = new JRadioButton("");
		rbR109.setBounds(175, 17, 20, 20);
		r1Panel.add(rbR109);
		rbsR1bits[9] = rbR109;
		
		JRadioButton rbR108 = new JRadioButton("");
		rbR108.setBounds(200, 17, 20, 20);
		r1Panel.add(rbR108);
		rbsR1bits[8] = rbR108;
		
		JRadioButton rbR107 = new JRadioButton("");
		rbR107.setBounds(225, 17, 20, 20);
		r1Panel.add(rbR107);
		rbsR1bits[7] = rbR107;
		
		JRadioButton rbR106 = new JRadioButton("");
		rbR106.setBounds(250, 17, 20, 20);
		r1Panel.add(rbR106);
		rbsR1bits[6] = rbR106;
		
		JRadioButton rbR105 = new JRadioButton("");
		rbR105.setBounds(275, 17, 20, 20);
		r1Panel.add(rbR105);
		rbsR1bits[5] = rbR105;
		
		JRadioButton rbR104 = new JRadioButton("");
		rbR104.setBounds(300, 17, 20, 20);
		r1Panel.add(rbR104);
		rbsR1bits[4] = rbR104;
		
		JRadioButton rbR103 = new JRadioButton("");
		rbR103.setBounds(325, 17, 20, 20);
		r1Panel.add(rbR103);
		rbsR1bits[3] = rbR103;
		
		JRadioButton rbR102 = new JRadioButton("");
		rbR102.setBounds(350, 17, 20, 20);
		r1Panel.add(rbR102);
		rbsR1bits[2] = rbR102;
		
		JRadioButton rbR101 = new JRadioButton("");
		rbR101.setBounds(375, 17, 20, 20);
		r1Panel.add(rbR101);
		rbsR1bits[1] = rbR101;
		
		JRadioButton rbR100 = new JRadioButton("0");
		rbR100.setForeground(Color.GRAY);
		rbR100.setFont(new Font("Dialog", Font.PLAIN, 12));
		rbR100.setBounds(400, 17, 50, 20);
		r1Panel.add(rbR100);
		rbsR1bits[0] = rbR100;
		
		r2Panel = new JPanel();
		r2Panel.setLayout(null);
		r2Panel.setBorder(new TitledBorder(new LineBorder(null, 1, true), "R2", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		r2Panel.setBounds(20, 290, 630, 50);
		frmConsole.getContentPane().add(r2Panel);
		
		JRadioButton rbR215 = new JRadioButton("");
		rbR215.setBounds(25, 17, 20, 20);
		r2Panel.add(rbR215);
		
		JLabel r2bit15 = new JLabel("15");
		r2bit15.setHorizontalAlignment(SwingConstants.CENTER);
		r2bit15.setForeground(Color.GRAY);
		r2bit15.setFont(new Font("Dialog", Font.PLAIN, 12));
		r2bit15.setBounds(5, 17, 20, 20);
		r2Panel.add(r2bit15);
		rbsR2bits[15] = rbR215;
		
		r2InpField = new JTextField();
		r2InpField.setColumns(10);
		r2InpField.setBackground(Color.WHITE);
		r2InpField.setBounds(490, 17, 45, 20);
		r2Panel.add(r2InpField);
		
		lblR2Val = new JLabel("VAL");
		lblR2Val.setHorizontalAlignment(SwingConstants.CENTER);
		lblR2Val.setForeground(Color.RED);
		lblR2Val.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblR2Val.setBounds(445, 17, 45, 20);
		r2Panel.add(lblR2Val);
		
		btnR2Deposit = new JButton("Deposit");
		btnR2Deposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String bits = r2InpField.getText();
				if (bits.isEmpty()) {
					System.err.println("R2: Invalid input");
					return;
				}
				try {
					cpu.setR(2, new Register("tmp", Integer.parseInt(bits),cpu.getR(2).getBitLength()));
					setBitmap(cpu.getR(2));
					r2InpField.setText("");
				} catch (NumberFormatException nfe) {
					System.err.println("R2: Invalid format");
				}
			}
		});
		btnR2Deposit.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnR2Deposit.setBounds(540, 17, 80, 20);
		r2Panel.add(btnR2Deposit);
		
		JRadioButton rbR214 = new JRadioButton("");
		rbR214.setBounds(50, 17, 20, 20);
		r2Panel.add(rbR214);
		rbsR2bits[14] = rbR214;
		
		JRadioButton rbR213 = new JRadioButton("");
		rbR213.setBounds(75, 17, 20, 20);
		r2Panel.add(rbR213);
		rbsR2bits[13] = rbR213;
		
		JRadioButton rbR212 = new JRadioButton("");
		rbR212.setBounds(100, 17, 20, 20);
		r2Panel.add(rbR212);
		rbsR2bits[12] = rbR212;
		
		JRadioButton rbR211 = new JRadioButton("");
		rbR211.setBounds(125, 17, 20, 20);
		r2Panel.add(rbR211);
		rbsR2bits[11] = rbR211;
		
		JRadioButton rbR210 = new JRadioButton("");
		rbR210.setBounds(150, 17, 20, 20);
		r2Panel.add(rbR210);
		rbsR2bits[10] = rbR210;
		
		JRadioButton rbR209 = new JRadioButton("");
		rbR209.setBounds(175, 17, 20, 20);
		r2Panel.add(rbR209);
		rbsR2bits[9] = rbR209;
		
		JRadioButton rbR208 = new JRadioButton("");
		rbR208.setBounds(200, 17, 20, 20);
		r2Panel.add(rbR208);
		rbsR2bits[8] = rbR208;
		
		JRadioButton rbR207 = new JRadioButton("");
		rbR207.setBounds(225, 17, 20, 20);
		r2Panel.add(rbR207);
		rbsR2bits[7] = rbR207;
		
		JRadioButton rbR206 = new JRadioButton("");
		rbR206.setBounds(250, 17, 20, 20);
		r2Panel.add(rbR206);
		rbsR2bits[6] = rbR206;
		
		JRadioButton rbR205 = new JRadioButton("");
		rbR205.setBounds(275, 17, 20, 20);
		r2Panel.add(rbR205);
		rbsR2bits[5] = rbR205;
		
		JRadioButton rbR204 = new JRadioButton("");
		rbR204.setBounds(300, 17, 20, 20);
		r2Panel.add(rbR204);
		rbsR2bits[4] = rbR204;
		
		JRadioButton rbR203 = new JRadioButton("");
		rbR203.setBounds(325, 17, 20, 20);
		r2Panel.add(rbR203);
		rbsR2bits[3] = rbR203;
		
		JRadioButton rbR202 = new JRadioButton("");
		rbR202.setBounds(350, 17, 20, 20);
		r2Panel.add(rbR202);
		rbsR2bits[2] = rbR202;
		
		JRadioButton rbR201 = new JRadioButton("");
		rbR201.setBounds(375, 17, 20, 20);
		r2Panel.add(rbR201);
		rbsR2bits[1] = rbR201;
		
		JRadioButton rbR200 = new JRadioButton("0");
		rbR200.setForeground(Color.GRAY);
		rbR200.setFont(new Font("Dialog", Font.PLAIN, 12));
		rbR200.setBounds(400, 17, 50, 20);
		r2Panel.add(rbR200);
		rbsR2bits[0] = rbR200;
		
		r3Panel = new JPanel();
		r3Panel.setLayout(null);
		r3Panel.setBorder(new TitledBorder(new LineBorder(null, 1, true), "R3", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		r3Panel.setBounds(20, 340, 630, 50);
		frmConsole.getContentPane().add(r3Panel);
		
		JRadioButton rbR315 = new JRadioButton("");
		rbR315.setBounds(25, 17, 20, 20);
		r3Panel.add(rbR315);
		rbsR3bits[15] = rbR315;
		
		JLabel r3bit15 = new JLabel("15");
		r3bit15.setHorizontalAlignment(SwingConstants.CENTER);
		r3bit15.setForeground(Color.GRAY);
		r3bit15.setFont(new Font("Dialog", Font.PLAIN, 12));
		r3bit15.setBounds(5, 17, 20, 20);
		r3Panel.add(r3bit15);
		
		r3InpField = new JTextField();
		r3InpField.setColumns(10);
		r3InpField.setBackground(Color.WHITE);
		r3InpField.setBounds(490, 17, 45, 20);
		r3Panel.add(r3InpField);
		
		lblR3Val = new JLabel("VAL");
		lblR3Val.setHorizontalAlignment(SwingConstants.CENTER);
		lblR3Val.setForeground(Color.RED);
		lblR3Val.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblR3Val.setBounds(445, 17, 45, 20);
		r3Panel.add(lblR3Val);
		
		btnR3Deposit = new JButton("Deposit");
		btnR3Deposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String bits = r3InpField.getText();
				if (bits.isEmpty()) {
					System.err.println("R3: Invalid input");
					return;
				}
				try {
					cpu.setR(3, new Register("tmp", Integer.parseInt(bits),cpu.getR(3).getBitLength()));
					setBitmap(cpu.getR(3));
					r3InpField.setText("");
				} catch (NumberFormatException nfe) {
					System.err.println("R3: Invalid format");
				}
			}
		});
		btnR3Deposit.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnR3Deposit.setBounds(540, 17, 80, 20);
		r3Panel.add(btnR3Deposit);
		
		JRadioButton rbR314 = new JRadioButton("");
		rbR314.setBounds(50, 17, 20, 20);
		r3Panel.add(rbR314);
		rbsR3bits[14] = rbR314;
		
		JRadioButton rbR313 = new JRadioButton("");
		rbR313.setBounds(75, 17, 20, 20);
		r3Panel.add(rbR313);
		rbsR3bits[13] = rbR313;
		
		JRadioButton rbR312 = new JRadioButton("");
		rbR312.setBounds(100, 17, 20, 20);
		r3Panel.add(rbR312);
		rbsR3bits[12] = rbR312;
		
		JRadioButton rbR311 = new JRadioButton("");
		rbR311.setBounds(125, 17, 20, 20);
		r3Panel.add(rbR311);
		rbsR3bits[11] = rbR311;
		
		JRadioButton rbR310 = new JRadioButton("");
		rbR310.setBounds(150, 17, 20, 20);
		r3Panel.add(rbR310);
		rbsR3bits[10] = rbR310;
		
		JRadioButton rbR309 = new JRadioButton("");
		rbR309.setBounds(175, 17, 20, 20);
		r3Panel.add(rbR309);
		rbsR3bits[9] = rbR309;
		
		JRadioButton rbR308 = new JRadioButton("");
		rbR308.setBounds(200, 17, 20, 20);
		r3Panel.add(rbR308);
		rbsR3bits[8] = rbR308;
		
		JRadioButton rbR307 = new JRadioButton("");
		rbR307.setBounds(225, 17, 20, 20);
		r3Panel.add(rbR307);
		rbsR3bits[7] = rbR307;
		
		JRadioButton rbR306 = new JRadioButton("");
		rbR306.setBounds(250, 17, 20, 20);
		r3Panel.add(rbR306);
		rbsR3bits[6] = rbR306;
		
		JRadioButton rbR305 = new JRadioButton("");
		rbR305.setBounds(275, 17, 20, 20);
		r3Panel.add(rbR305);
		rbsR3bits[5] = rbR305;
		
		JRadioButton rbR304 = new JRadioButton("");
		rbR304.setBounds(300, 17, 20, 20);
		r3Panel.add(rbR304);
		rbsR3bits[4] = rbR304;
		
		JRadioButton rbR303 = new JRadioButton("");
		rbR303.setBounds(325, 17, 20, 20);
		r3Panel.add(rbR303);
		rbsR3bits[3] = rbR303;
		
		JRadioButton rbR302 = new JRadioButton("");
		rbR302.setBounds(350, 17, 20, 20);
		r3Panel.add(rbR302);
		rbsR3bits[2] = rbR302;
		
		JRadioButton rbR301 = new JRadioButton("");
		rbR301.setBounds(375, 17, 20, 20);
		r3Panel.add(rbR301);
		rbsR3bits[1] = rbR301;
		
		JRadioButton rbR300 = new JRadioButton("0");
		rbR300.setForeground(Color.GRAY);
		rbR300.setFont(new Font("Dialog", Font.PLAIN, 12));
		rbR300.setBounds(400, 17, 50, 20);
		r3Panel.add(rbR300);
		rbsR3bits[0] = rbR300;
		
		ix1Panel = new JPanel();
		ix1Panel.setLayout(null);
		ix1Panel.setBorder(new TitledBorder(new LineBorder(null, 1, true), "IX1", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		ix1Panel.setBounds(20, 390, 630, 50);
		frmConsole.getContentPane().add(ix1Panel);
		
		JRadioButton rbIx115 = new JRadioButton("");
		rbIx115.setBounds(25, 17, 20, 20);
		ix1Panel.add(rbIx115);
		rbsIX1bits[15] = rbIx115;
		
		JLabel ix1bit15 = new JLabel("15");
		ix1bit15.setHorizontalAlignment(SwingConstants.CENTER);
		ix1bit15.setForeground(Color.GRAY);
		ix1bit15.setFont(new Font("Dialog", Font.PLAIN, 12));
		ix1bit15.setBounds(5, 17, 20, 20);
		ix1Panel.add(ix1bit15);
		
		ix1InpField = new JTextField();
		ix1InpField.setColumns(10);
		ix1InpField.setBackground(Color.WHITE);
		ix1InpField.setBounds(490, 17, 45, 20);
		ix1Panel.add(ix1InpField);
		
		lblIx1Val = new JLabel("VAL");
		lblIx1Val.setHorizontalAlignment(SwingConstants.CENTER);
		lblIx1Val.setForeground(Color.RED);
		lblIx1Val.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblIx1Val.setBounds(445, 17, 45, 20);
		ix1Panel.add(lblIx1Val);
		
		btnIx1Deposit = new JButton("Deposit");
		btnIx1Deposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String bits = ix1InpField.getText();
				if (bits.isEmpty()) {
					System.err.println("IX1: Invalid input");
					return;
				}
				try {
					cpu.setIx(1, new Register("tmp", Integer.parseInt(bits),cpu.getIx(1).getBitLength()));
					setBitmap(cpu.getIx(1));
					ix1InpField.setText("");
				} catch (NumberFormatException nfe) {
					System.err.println("IX1: Invalid format");
				}
			}
		});
		btnIx1Deposit.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnIx1Deposit.setBounds(540, 17, 80, 20);
		ix1Panel.add(btnIx1Deposit);
		
		JRadioButton rbIx114 = new JRadioButton("");
		rbIx114.setBounds(50, 17, 20, 20);
		ix1Panel.add(rbIx114);
		rbsIX1bits[14] = rbIx114;
		
		JRadioButton rbIx113 = new JRadioButton("");
		rbIx113.setBounds(75, 17, 20, 20);
		ix1Panel.add(rbIx113);
		rbsIX1bits[13] = rbIx113;
		
		JRadioButton rbIx112 = new JRadioButton("");
		rbIx112.setBounds(100, 17, 20, 20);
		ix1Panel.add(rbIx112);
		rbsIX1bits[12] = rbIx112;
		
		JRadioButton rbIx111 = new JRadioButton("");
		rbIx111.setBounds(125, 17, 20, 20);
		ix1Panel.add(rbIx111);
		rbsIX1bits[11] = rbIx111;
		
		JRadioButton rbIx110 = new JRadioButton("");
		rbIx110.setBounds(150, 17, 20, 20);
		ix1Panel.add(rbIx110);
		rbsIX1bits[10] = rbIx110;
		
		JRadioButton rbIx109 = new JRadioButton("");
		rbIx109.setBounds(175, 17, 20, 20);
		ix1Panel.add(rbIx109);
		rbsIX1bits[9] = rbIx109;
		
		JRadioButton rbIx108 = new JRadioButton("");
		rbIx108.setBounds(200, 17, 20, 20);
		ix1Panel.add(rbIx108);
		rbsIX1bits[8] = rbIx108;
		
		JRadioButton rbIx107 = new JRadioButton("");
		rbIx107.setBounds(225, 17, 20, 20);
		ix1Panel.add(rbIx107);
		rbsIX1bits[7] = rbIx107;
		
		JRadioButton rbIx106 = new JRadioButton("");
		rbIx106.setBounds(250, 17, 20, 20);
		ix1Panel.add(rbIx106);
		rbsIX1bits[6] = rbIx106;
		
		JRadioButton rbIx105 = new JRadioButton("");
		rbIx105.setBounds(275, 17, 20, 20);
		ix1Panel.add(rbIx105);
		rbsIX1bits[5] = rbIx105;
		
		JRadioButton rbIx104 = new JRadioButton("");
		rbIx104.setBounds(300, 17, 20, 20);
		ix1Panel.add(rbIx104);
		rbsIX1bits[4] = rbIx104;
		
		JRadioButton rbIx103 = new JRadioButton("");
		rbIx103.setBounds(325, 17, 20, 20);
		ix1Panel.add(rbIx103);
		rbsIX1bits[3] = rbIx103;
		
		JRadioButton rbIx102 = new JRadioButton("");
		rbIx102.setBounds(350, 17, 20, 20);
		ix1Panel.add(rbIx102);
		rbsIX1bits[2] = rbIx102;
		
		JRadioButton rbIx101 = new JRadioButton("");
		rbIx101.setBounds(375, 17, 20, 20);
		ix1Panel.add(rbIx101);
		rbsIX1bits[1] = rbIx101;
		
		JRadioButton rbIx100 = new JRadioButton("0");
		rbIx100.setForeground(Color.GRAY);
		rbIx100.setFont(new Font("Dialog", Font.PLAIN, 12));
		rbIx100.setBounds(400, 17, 50, 20);
		ix1Panel.add(rbIx100);
		rbsIX1bits[0] = rbIx100;
		
		ix2Panel = new JPanel();
		ix2Panel.setLayout(null);
		ix2Panel.setBorder(new TitledBorder(new LineBorder(null, 1, true), "IX2", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		ix2Panel.setBounds(20, 440, 630, 50);
		frmConsole.getContentPane().add(ix2Panel);
		
		JRadioButton rbIx215 = new JRadioButton("");
		rbIx215.setBounds(25, 17, 20, 20);
		ix2Panel.add(rbIx215);
		rbsIX2bits[15] = rbIx215;
		
		JLabel ix2bit15 = new JLabel("15");
		ix2bit15.setHorizontalAlignment(SwingConstants.CENTER);
		ix2bit15.setForeground(Color.GRAY);
		ix2bit15.setFont(new Font("Dialog", Font.PLAIN, 12));
		ix2bit15.setBounds(5, 17, 20, 20);
		ix2Panel.add(ix2bit15);
		
		ix2InpField = new JTextField();
		ix2InpField.setColumns(10);
		ix2InpField.setBackground(Color.WHITE);
		ix2InpField.setBounds(490, 17, 45, 20);
		ix2Panel.add(ix2InpField);
		
		lblIx2Val = new JLabel("VAL");
		lblIx2Val.setHorizontalAlignment(SwingConstants.CENTER);
		lblIx2Val.setForeground(Color.RED);
		lblIx2Val.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblIx2Val.setBounds(445, 17, 45, 20);
		ix2Panel.add(lblIx2Val);
		
		btnIx2Deposit = new JButton("Deposit");
		btnIx2Deposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String bits = ix2InpField.getText();
				if (bits.isEmpty()) {
					System.err.println("IX2: Invalid input");
					return;
				}
				try {
					cpu.setIx(2, new Register("tmp", Integer.parseInt(bits),cpu.getIx(2).getBitLength()));
					setBitmap(cpu.getIx(2));
					ix2InpField.setText("");
				} catch (NumberFormatException nfe) {
					System.err.println("IX2: Invalid format");
				}
			}
		});
		btnIx2Deposit.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnIx2Deposit.setBounds(540, 17, 80, 20);
		ix2Panel.add(btnIx2Deposit);
		
		JRadioButton rbIx214 = new JRadioButton("");
		rbIx214.setBounds(50, 17, 20, 20);
		ix2Panel.add(rbIx214);
		rbsIX2bits[14] = rbIx214;
		
		JRadioButton rbIx213 = new JRadioButton("");
		rbIx213.setBounds(75, 17, 20, 20);
		ix2Panel.add(rbIx213);
		rbsIX2bits[13] = rbIx213;
		
		JRadioButton rbIx212 = new JRadioButton("");
		rbIx212.setBounds(100, 17, 20, 20);
		ix2Panel.add(rbIx212);
		rbsIX2bits[12] = rbIx212;
		
		JRadioButton rbIx211 = new JRadioButton("");
		rbIx211.setBounds(125, 17, 20, 20);
		ix2Panel.add(rbIx211);
		rbsIX2bits[11] = rbIx211;
		
		JRadioButton rbIx210 = new JRadioButton("");
		rbIx210.setBounds(150, 17, 20, 20);
		ix2Panel.add(rbIx210);
		rbsIX2bits[10] = rbIx210;
		
		JRadioButton rbIx209 = new JRadioButton("");
		rbIx209.setBounds(175, 17, 20, 20);
		ix2Panel.add(rbIx209);
		rbsIX2bits[9] = rbIx209;
		
		JRadioButton rbIx208 = new JRadioButton("");
		rbIx208.setBounds(200, 17, 20, 20);
		ix2Panel.add(rbIx208);
		rbsIX2bits[8] = rbIx208;
		
		JRadioButton rbIx207 = new JRadioButton("");
		rbIx207.setBounds(225, 17, 20, 20);
		ix2Panel.add(rbIx207);
		rbsIX2bits[7] = rbIx207;
		
		JRadioButton rbIx206 = new JRadioButton("");
		rbIx206.setBounds(250, 17, 20, 20);
		ix2Panel.add(rbIx206);
		rbsIX2bits[6] = rbIx206;
		
		JRadioButton rbIx205 = new JRadioButton("");
		rbIx205.setBounds(275, 17, 20, 20);
		ix2Panel.add(rbIx205);
		rbsIX2bits[5] = rbIx205;
		
		JRadioButton rbIx204 = new JRadioButton("");
		rbIx204.setBounds(300, 17, 20, 20);
		ix2Panel.add(rbIx204);
		rbsIX2bits[4] = rbIx204;
		
		JRadioButton rbIx203 = new JRadioButton("");
		rbIx203.setBounds(325, 17, 20, 20);
		ix2Panel.add(rbIx203);
		rbsIX2bits[3] = rbIx203;
		
		JRadioButton rbIx202 = new JRadioButton("");
		rbIx202.setBounds(350, 17, 20, 20);
		ix2Panel.add(rbIx202);
		rbsIX2bits[2] = rbIx202;
		
		JRadioButton rbIx201 = new JRadioButton("");
		rbIx201.setBounds(375, 17, 20, 20);
		ix2Panel.add(rbIx201);
		rbsIX2bits[1] = rbIx201;
		
		JRadioButton rbIx200 = new JRadioButton("0");
		rbIx200.setForeground(Color.GRAY);
		rbIx200.setFont(new Font("Dialog", Font.PLAIN, 12));
		rbIx200.setBounds(400, 17, 50, 20);
		ix2Panel.add(rbIx200);
		rbsIX2bits[0] = rbIx200;
		
		ix3Panel = new JPanel();
		ix3Panel.setLayout(null);
		ix3Panel.setBorder(new TitledBorder(new LineBorder(null, 1, true), "IX3", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		ix3Panel.setBounds(20, 490, 630, 50);
		frmConsole.getContentPane().add(ix3Panel);
		
		JRadioButton rbIx315 = new JRadioButton("");
		rbIx315.setBounds(25, 17, 20, 20);
		ix3Panel.add(rbIx315);
		rbsIX3bits[15] = rbIx315;
		
		JLabel ix3bit15 = new JLabel("15");
		ix3bit15.setHorizontalAlignment(SwingConstants.CENTER);
		ix3bit15.setForeground(Color.GRAY);
		ix3bit15.setFont(new Font("Dialog", Font.PLAIN, 12));
		ix3bit15.setBounds(5, 17, 20, 20);
		ix3Panel.add(ix3bit15);
		
		ix3InpField = new JTextField();
		ix3InpField.setColumns(10);
		ix3InpField.setBackground(Color.WHITE);
		ix3InpField.setBounds(490, 17, 45, 20);
		ix3Panel.add(ix3InpField);
		
		lblIx3Val = new JLabel("VAL");
		lblIx3Val.setHorizontalAlignment(SwingConstants.CENTER);
		lblIx3Val.setForeground(Color.RED);
		lblIx3Val.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblIx3Val.setBounds(445, 17, 45, 20);
		ix3Panel.add(lblIx3Val);
		
		btnIx3Deposit = new JButton("Deposit");
		btnIx3Deposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String bits = ix3InpField.getText();
				if (bits.isEmpty()) {
					System.err.println("IX3: Invalid input");
					return;
				}
				try {
					cpu.setIx(3, new Register("tmp", Integer.parseInt(bits),cpu.getIx(3).getBitLength()));
					setBitmap(cpu.getIx(3));
					ix3InpField.setText("");
				} catch (NumberFormatException nfe) {
					System.err.println("IX3: Invalid format");
				}
			}
		});
		btnIx3Deposit.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnIx3Deposit.setBounds(540, 17, 80, 20);
		ix3Panel.add(btnIx3Deposit);
		
		JRadioButton rbIx314 = new JRadioButton("");
		rbIx314.setBounds(50, 17, 20, 20);
		ix3Panel.add(rbIx314);
		rbsIX3bits[14] = rbIx314;
		
		JRadioButton rbIx313 = new JRadioButton("");
		rbIx313.setBounds(75, 17, 20, 20);
		ix3Panel.add(rbIx313);
		rbsIX3bits[13] = rbIx313;
		
		JRadioButton rbIx312 = new JRadioButton("");
		rbIx312.setBounds(100, 17, 20, 20);
		ix3Panel.add(rbIx312);
		rbsIX3bits[12] = rbIx312;
		
		JRadioButton rbIx311 = new JRadioButton("");
		rbIx311.setBounds(125, 17, 20, 20);
		ix3Panel.add(rbIx311);
		rbsIX3bits[11] = rbIx311;
		
		JRadioButton rbIx310 = new JRadioButton("");
		rbIx310.setBounds(150, 17, 20, 20);
		ix3Panel.add(rbIx310);
		rbsIX3bits[10] = rbIx310;
		
		JRadioButton rbIx309 = new JRadioButton("");
		rbIx309.setBounds(175, 17, 20, 20);
		ix3Panel.add(rbIx309);
		rbsIX3bits[9] = rbIx309;
		
		JRadioButton rbIx308 = new JRadioButton("");
		rbIx308.setBounds(200, 17, 20, 20);
		ix3Panel.add(rbIx308);
		rbsIX3bits[8] = rbIx308;
		
		JRadioButton rbIx307 = new JRadioButton("");
		rbIx307.setBounds(225, 17, 20, 20);
		ix3Panel.add(rbIx307);
		rbsIX3bits[7] = rbIx307;
		
		JRadioButton rbIx306 = new JRadioButton("");
		rbIx306.setBounds(250, 17, 20, 20);
		ix3Panel.add(rbIx306);
		rbsIX3bits[6] = rbIx306;
		
		JRadioButton rbIx305 = new JRadioButton("");
		rbIx305.setBounds(275, 17, 20, 20);
		ix3Panel.add(rbIx305);
		rbsIX3bits[5] = rbIx305;
		
		JRadioButton rbIx304 = new JRadioButton("");
		rbIx304.setBounds(300, 17, 20, 20);
		ix3Panel.add(rbIx304);
		rbsIX3bits[4] = rbIx304;
		
		JRadioButton rbIx303 = new JRadioButton("");
		rbIx303.setBounds(325, 17, 20, 20);
		ix3Panel.add(rbIx303);
		rbsIX3bits[3] = rbIx303;
		
		JRadioButton rbIx302 = new JRadioButton("");
		rbIx302.setBounds(350, 17, 20, 20);
		ix3Panel.add(rbIx302);
		rbsIX3bits[2] = rbIx302;
		
		JRadioButton rbIx301 = new JRadioButton("");
		rbIx301.setBounds(375, 17, 20, 20);
		ix3Panel.add(rbIx301);
		rbsIX3bits[1] = rbIx301;
		
		JRadioButton rbIx300 = new JRadioButton("0");
		rbIx300.setFont(new Font("Dialog", Font.PLAIN, 12));
		rbIx300.setForeground(Color.GRAY);
		rbIx300.setBounds(400, 17, 50, 20);
		ix3Panel.add(rbIx300);
		rbsIX3bits[0] = rbIx300;
		
		marPanel = new JPanel();
		marPanel.setLayout(null);
		marPanel.setBorder(new TitledBorder(new LineBorder(null, 1, true), "MAR", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		marPanel.setBounds(20, 540, 630, 50);
		frmConsole.getContentPane().add(marPanel);
		
		JRadioButton rbMar15 = new JRadioButton("");
		rbMar15.setBounds(25, 17, 20, 20);
		marPanel.add(rbMar15);
		rbsMARbits[15] = rbMar15;
		
		JLabel marbit15 = new JLabel("15");
		marbit15.setHorizontalAlignment(SwingConstants.CENTER);
		marbit15.setForeground(Color.GRAY);
		marbit15.setFont(new Font("Dialog", Font.PLAIN, 12));
		marbit15.setBounds(5, 17, 20, 20);
		marPanel.add(marbit15);
		
		marInpField = new JTextField();
		marInpField.setColumns(10);
		marInpField.setBackground(Color.WHITE);
		marInpField.setBounds(490, 17, 45, 20);
		marPanel.add(marInpField);
		
		lblMarVal = new JLabel("VAL");
		lblMarVal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMarVal.setForeground(Color.RED);
		lblMarVal.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblMarVal.setBounds(445, 17, 45, 20);
		marPanel.add(lblMarVal);
		
		btnMarDeposit = new JButton("Deposit");
		btnMarDeposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String bits = marInpField.getText();
				if (bits.isEmpty()) {
					System.err.println("MAR: Invalid input");
					return;
				}
				try {
					cpu.setMar(new Register("tmp", Integer.parseInt(bits),cpu.getMar().getBitLength()));
					setBitmap(cpu.getMar());
					marInpField.setText("");
				} catch (NumberFormatException nfe) {
					System.err.println("MAR: Invalid format");
				}
			}
		});
		btnMarDeposit.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnMarDeposit.setBounds(540, 17, 80, 20);
		marPanel.add(btnMarDeposit);
		
		JRadioButton rbMar14 = new JRadioButton("");
		rbMar14.setBounds(50, 17, 20, 20);
		marPanel.add(rbMar14);
		rbsMARbits[14] = rbMar14;
		
		JRadioButton rbMar13 = new JRadioButton("");
		rbMar13.setBounds(75, 17, 20, 20);
		marPanel.add(rbMar13);
		rbsMARbits[13] = rbMar13;
		
		JRadioButton rbMar12 = new JRadioButton("");
		rbMar12.setBounds(100, 17, 20, 20);
		marPanel.add(rbMar12);
		rbsMARbits[12] = rbMar12;
		
		JRadioButton rbMar11 = new JRadioButton("");
		rbMar11.setBounds(125, 17, 20, 20);
		marPanel.add(rbMar11);
		rbsMARbits[11] = rbMar11;
		
		JRadioButton rbMar10 = new JRadioButton("");
		rbMar10.setBounds(150, 17, 20, 20);
		marPanel.add(rbMar10);
		rbsMARbits[10] = rbMar10;
		
		JRadioButton rbMar09 = new JRadioButton("");
		rbMar09.setBounds(175, 17, 20, 20);
		marPanel.add(rbMar09);
		rbsMARbits[9] = rbMar09;
		
		JRadioButton rbMar08 = new JRadioButton("");
		rbMar08.setBounds(200, 17, 20, 20);
		marPanel.add(rbMar08);
		rbsMARbits[8] = rbMar08;
		
		JRadioButton rbMar07 = new JRadioButton("");
		rbMar07.setBounds(225, 17, 20, 20);
		marPanel.add(rbMar07);
		rbsMARbits[7] = rbMar07;
		
		JRadioButton rbMar06 = new JRadioButton("");
		rbMar06.setBounds(250, 17, 20, 20);
		marPanel.add(rbMar06);
		rbsMARbits[6] = rbMar06;
		
		JRadioButton rbMar05 = new JRadioButton("");
		rbMar05.setBounds(275, 17, 20, 20);
		marPanel.add(rbMar05);
		rbsMARbits[5] = rbMar05;
		
		JRadioButton rbMar04 = new JRadioButton("");
		rbMar04.setBounds(300, 17, 20, 20);
		marPanel.add(rbMar04);
		rbsMARbits[4] = rbMar04;
		
		JRadioButton rbMar03 = new JRadioButton("");
		rbMar03.setBounds(325, 17, 20, 20);
		marPanel.add(rbMar03);
		rbsMARbits[3] = rbMar03;
		
		JRadioButton rbMar02 = new JRadioButton("");
		rbMar02.setBounds(350, 17, 20, 20);
		marPanel.add(rbMar02);
		rbsMARbits[2] = rbMar02;
		
		JRadioButton rbMar01 = new JRadioButton("");
		rbMar01.setBounds(375, 17, 20, 20);
		marPanel.add(rbMar01);
		rbsMARbits[1] = rbMar01;
		
		JRadioButton rbMar00 = new JRadioButton("0");
		rbMar00.setForeground(Color.GRAY);
		rbMar00.setFont(new Font("Dialog", Font.PLAIN, 12));
		rbMar00.setBounds(400, 17, 50, 20);
		marPanel.add(rbMar00);
		rbsMARbits[0] = rbMar00;
		
		mbrPanel = new JPanel();
		mbrPanel.setLayout(null);
		mbrPanel.setBorder(new TitledBorder(new LineBorder(null, 1, true), "MBR", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		mbrPanel.setBounds(20, 590, 630, 50);
		frmConsole.getContentPane().add(mbrPanel);
		
		JRadioButton rbMbr15 = new JRadioButton("");
		rbMbr15.setBounds(25, 17, 20, 20);
		mbrPanel.add(rbMbr15);
		rbsMBRbits[15] = rbMbr15;
		
		JLabel mbrbit15 = new JLabel("15");
		mbrbit15.setHorizontalAlignment(SwingConstants.CENTER);
		mbrbit15.setForeground(Color.GRAY);
		mbrbit15.setFont(new Font("Dialog", Font.PLAIN, 12));
		mbrbit15.setBounds(5, 17, 20, 20);
		mbrPanel.add(mbrbit15);
		
		mbrInpField = new JTextField();
		mbrInpField.setColumns(10);
		mbrInpField.setBackground(Color.WHITE);
		mbrInpField.setBounds(490, 17, 45, 20);
		mbrPanel.add(mbrInpField);
		
		lblMbrVal = new JLabel("VAL");
		lblMbrVal.setHorizontalAlignment(SwingConstants.CENTER);
		lblMbrVal.setForeground(Color.RED);
		lblMbrVal.setFont(new Font("Dialog", Font.ITALIC, 12));
		lblMbrVal.setBounds(445, 17, 45, 20);
		mbrPanel.add(lblMbrVal);
		
		btnMbrDeposit = new JButton("Deposit");
		btnMbrDeposit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String bits = mbrInpField.getText();
				if (bits.isEmpty()) {
					System.err.println("MBR: Invalid input");
					return;
				}
				try {
					cpu.setMbr(new Register("tmp", Integer.parseInt(bits),cpu.getMbr().getBitLength()));
					setBitmap(cpu.getMbr());
					mbrInpField.setText("");
				} catch (NumberFormatException nfe) {
					System.err.println("MBR: Invalid format");
				}
			}
		});
		btnMbrDeposit.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnMbrDeposit.setBounds(540, 17, 80, 20);
		mbrPanel.add(btnMbrDeposit);
		
		JRadioButton rbMbr14 = new JRadioButton("");
		rbMbr14.setBounds(50, 17, 20, 20);
		mbrPanel.add(rbMbr14);
		rbsMBRbits[14] = rbMbr14;
		
		JRadioButton rbMbr13 = new JRadioButton("");
		rbMbr13.setBounds(75, 17, 20, 20);
		mbrPanel.add(rbMbr13);
		rbsMBRbits[13] = rbMbr13;
		
		JRadioButton rbMbr12 = new JRadioButton("");
		rbMbr12.setBounds(100, 17, 20, 20);
		mbrPanel.add(rbMbr12);
		rbsMBRbits[12] = rbMbr12;
		
		JRadioButton rbMbr11 = new JRadioButton("");
		rbMbr11.setBounds(125, 17, 20, 20);
		mbrPanel.add(rbMbr11);
		rbsMBRbits[11] = rbMbr11;
		
		JRadioButton rbMbr10 = new JRadioButton("");
		rbMbr10.setBounds(150, 17, 20, 20);
		mbrPanel.add(rbMbr10);
		rbsMBRbits[10] = rbMbr10;
		
		JRadioButton rbMbr09 = new JRadioButton("");
		rbMbr09.setBounds(175, 17, 20, 20);
		mbrPanel.add(rbMbr09);
		rbsMBRbits[9] = rbMbr09;
		
		JRadioButton rbMbr08 = new JRadioButton("");
		rbMbr08.setBounds(200, 17, 20, 20);
		mbrPanel.add(rbMbr08);
		rbsMBRbits[8] = rbMbr08;
		
		JRadioButton rbMbr07 = new JRadioButton("");
		rbMbr07.setBounds(225, 17, 20, 20);
		mbrPanel.add(rbMbr07);
		rbsMBRbits[7] = rbMbr07;
		
		JRadioButton rbMbr06 = new JRadioButton("");
		rbMbr06.setBounds(250, 17, 20, 20);
		mbrPanel.add(rbMbr06);
		rbsMBRbits[6] = rbMbr06;
		
		JRadioButton rbMbr05 = new JRadioButton("");
		rbMbr05.setBounds(275, 17, 20, 20);
		mbrPanel.add(rbMbr05);
		rbsMBRbits[5] = rbMbr05;
		
		JRadioButton rbMbr04 = new JRadioButton("");
		rbMbr04.setBounds(300, 17, 20, 20);
		mbrPanel.add(rbMbr04);
		rbsMBRbits[4] = rbMbr04;
		
		JRadioButton rbMbr03 = new JRadioButton("");
		rbMbr03.setBounds(325, 17, 20, 20);
		mbrPanel.add(rbMbr03);
		rbsMBRbits[3] = rbMbr03;
		
		JRadioButton rbMbr02 = new JRadioButton("");
		rbMbr02.setBounds(350, 17, 20, 20);
		mbrPanel.add(rbMbr02);
		rbsMBRbits[2] = rbMbr02;
		
		JRadioButton rbMbr01 = new JRadioButton("");
		rbMbr01.setBounds(375, 17, 20, 20);
		mbrPanel.add(rbMbr01);
		rbsMBRbits[1] = rbMbr01;
		
		JRadioButton rbMbr00 = new JRadioButton("0");
		rbMbr00.setForeground(Color.GRAY);
		rbMbr00.setFont(new Font("Dialog", Font.PLAIN, 12));
		rbMbr00.setBounds(400, 17, 50, 20);
		mbrPanel.add(rbMbr00);
		rbsMBRbits[0] = rbMbr00;
		
		iplPanel = new JPanel();

		iplPanel.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)), "IPL", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		iplPanel.setBounds(240, 25, 410, 50);
		frmConsole.getContentPane().add(iplPanel);
		iplPanel.setLayout(null);
		
		btnProgram1 = new JButton("Program 1");
		btnProgram1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				loadProgram("program1.txt");
				cpu.setPc(Processor.PROGRAM_START_ADDR);
				
				/* 
				 * The whole point of this initialization is that 
				 * the address/data field's in the INSTRUCTIONS can be MAX of 32 which 
				 * isn't sufficient for addressing 2048 words in the memory.
				 * 
				 * And, to make user's life easier by not requiring to enter all these values in "Memory" Console
				 * before running the Program1.txt.
				 * 
				 */
				mem.setData(10, 200); //array start address
				mem.setData(11, 21); //array size
				mem.setData(12, 1026); //first jump address
				mem.setData(13, 1047); //2nd jump address
				mem.setData(14, 1059); //3rd jump address
				mem.setData(9, 1066); //4th jump address
				mem.setData(8, 1054); //5th jump address

				(new Thread(cpu)).start();
			}
		});
		btnProgram1.setBounds(20, 15, 115, 25);
		iplPanel.add(btnProgram1);

		btnProgram2 = new JButton("Program 2");
		btnProgram2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				loadInput("input2.txt");
				loadProgram("program2.txt");
				cpu.setPc(Processor.PROGRAM_START_ADDR);
				
				/* 
				 * The whole point of this initialization is that 
				 * the address/data field's in the INSTRUCTIONS can be MAX of 32 which 
				 * isn't sufficient for addressing 2048 words in the memory.
				 * 
				 * And, to make user's life easier by not requiring to enter all these values in "Memory" Console
				 * before running the Program2.txt.
				 */
				mem.setData(10, Processor.INPUT_START_ADDR);
				mem.setData(11, ' '); //word delimiter
				mem.setData(12, '.'); //sentence delimiter
				mem.setData(13, 900); //word/pattern location
				mem.setData(14, 41); //sentence count 
				mem.setData(15, 41); //word count in sentence
				mem.setData(16, 0); //character count (word? sentence? paragraph?) - need this ??
				mem.setData(17, 900); //word/pattern location - copy
				mem.setData(18, Processor.KEY_DATA_SIZE_LOC); //pattern size
				mem.setData(19, Processor.INPUT_START_ADDR);
				mem.setData(20, 900);
				mem.setData(21, Processor.INPUT_START_ADDR);
				mem.setData(22, 41); //word count in sentence
				
				/* jump addresses */
				mem.setData(24, Processor.PROGRAM_START_ADDR); //first jmp address
				mem.setData(25, 1031);	//second jmp addr
				mem.setData(26, 1039);	//third jmp addr
				mem.setData(23, 1051);	//word jmp addr
				mem.setData(27, 1057);	//fourth jmp addr
				mem.setData(28, 1070);	//fifth jmp addr
				mem.setData(29, 1084);	//sixth jmp addr
				mem.setData(30, 1090);	//seventh jmp addr
				mem.setData(31, 1097);	//eighth jmp addr


				cpu.isa.setDataType(1); //set it to read characters when using IN/OUT instructions.
				(new Thread(cpu)).start();
			}
		});
		btnProgram2.setBounds(150, 15, 115, 25);
		iplPanel.add(btnProgram2);
		
		btnProgram3 = new JButton("Program 3");
		btnProgram3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				/* Run Floating point and Vector demo program!! */
				/*float f = 2.5f;
				int i = cpu.fpu.fromFloat(f);
				print(i);
				print(Integer.toBinaryString(i) + "\n");
				float f2 = cpu.fpu.toFloat(i);
				print(f2);*/
				
/*				float f2 = 2.5f;
				int if2 = cpu.fpu.setFloat(f2);
				System.out.println(Integer.toBinaryString(if2));
				print(Integer.toBinaryString(if2));*/
				
				loadProgram("program3.txt");
				cpu.setPc(Processor.PROGRAM_START_ADDR);
				
				float f1 = 3.2f;
				int if1 = cpu.fpu.fromFloat(f1);
				float f2 = 2.5f;
				int if2 = cpu.fpu.fromFloat(f2);
				float f3 = 1.5f;
				int if3 = cpu.fpu.fromFloat(f3);
				mem.setData(20, if1); //first floating point number 3.2f
				mem.setData(21, if2); //second floating point number 2.5f
				mem.setData(22, if3); // third floating point number 2.0f
				
				mem.setData(28, 0);
				mem.setData(29, 1);
				mem.setData(30, 5);
				// for vector add 
				int j = 20;
				for (int i = 512; i <= 521; i ++) {
					mem.setData(i, j++);
				}
				mem.setData(12, 512);
				mem.setData(13, 517);
				
				// for vector sub 
				j = 100;
				for (int i = 522; i <= 531; i ++) {
					mem.setData(i, j--);
				}
				mem.setData(14, 522);
				mem.setData(15, 527);
				mem.setData(26, 3); //int to float
				
				(new Thread(cpu)).start();
				/*try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
				/*System.out.println("Floating point add/sub results");
				int radd = mem.getData(23);
				float fadd = cpu.fpu.getFloat(radd);
				System.out.println(fadd);
				print(fadd);
				int rsub = mem.getData(24);
				float fsub = cpu.fpu.getFloat(rsub);
				System.out.println(fsub);
				print(fsub);
				
				int it = mem.getData(25); //float to int
				System.out.println(it);
				print(it);
				
				
				int flt = mem.getData(27);
				float fcv = cpu.fpu.getFloat(flt);
				System.out.println(fcv);
				print(fcv);
				
				System.out.println("Vector add/sub results");
				 for vector add 
				for (int i = 512; i <= 521; i ++) {
					mem.getData(i);
					//print to console?? 
				}
							
				 for vector sub 
				for (int i = 522; i <= 531; i ++) {
					mem.getData(i);
					//print to console??
				}
				//cpu.reset();
*/			}
		});
		btnProgram3.setBounds(280, 15, 115, 25);
		iplPanel.add(btnProgram3);
		
		tglbtnOnoff = new JToggleButton("RUN");
		
		tglbtnOnoff.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (tglbtnOnoff.getText() == "RUN") {
					enablePress();
					
				} else {
					cpu.cu.halt();
					disablePress();
				}
			}
		});
		tglbtnOnoff.setBounds(20, 31, 167, 25);
		frmConsole.getContentPane().add(tglbtnOnoff);
		
		conPanel = new JPanel();
		conPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Console", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		conPanel.setBounds(665, 100, 275, 538);
		frmConsole.getContentPane().add(conPanel);
		conPanel.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(15, 25, 250, 465);
		conPanel.add(textArea);
		
		btnClear = new JButton("Clear");
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				textArea.setText("");
			}
		});
		btnClear.setBounds(150, 500, 100, 25);
		conPanel.add(btnClear);
	}
}
