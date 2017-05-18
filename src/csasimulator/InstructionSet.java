package csasimulator;

/**
 * 
 */

/**
 * @author phani
 *
 */
public class InstructionSet {

	/*
	 * Phase I:
	 * 
	 * (001) LDR r, x, address [,I] 
	 * (002) STR r, x, address [,I] 
	 * (003) LDA r, x, address [,I] 
	 * (041) LDX x, address [,I] 
	 * (042) STX x, address [,I]
	 * 
	 * (004) AMR r, x, address [,I] 
	 * (005) SMR r, x, address [,I] 
	 * (006) AIR r, immed 
	 * (007) SIR r, immed
	 * 
	 */
	
	/* 
	 * Phase II:
	 * 
	 * (010) JZ r, x, address [,I]
	 * (011) JNE r, x, address [,I]
	 * (012) JCC cc, x, address [,I]
	 * (013) JMA x, address [,I]
	 * (014) JSR x, address [,I]
	 * (015) RFS immed
	 * (016) SOB r, x, address [,I]
	 * (017) JGE r, x, address [,I]
	 * 
	 * (020) MLT rx, ry
	 * (021) DVD rx, ry
	 * (022) TRR rx, ry
	 * (023) AND rx, ry
	 * (024) ORR rx, ry
	 * (025) NOT rx
	 * 
	 * (031) SRC r, count, L/R, A/L
	 * (032) RRC r, count, L/R, A/L
	 * 
	 * (061) IN r, devid
	 * (062) OUT r, devid
	 * (063) CHK r, devid
	 * 
	 * (000) HLT
	 * (036) TRAP code
	 * 
	 * (077) NOOP - for debugging
	 * (076) RET - return for debugging. 
	 */
	
	/* Opcode numbers in Octal */
	public static final int LDR = 001;
	public static final int STR = 002;
	public static final int LDA = 003;
	public static final int LDX = 041;
	public static final int STX = 042;

	public static final int AMR = 004;
	public static final int SMR = 005;
	public static final int AIR = 006;
	public static final int SIR = 007;
	
	public static final int JZ = 010;
	public static final int JNE = 011;
	public static final int JCC = 012;
	public static final int JMA = 013;
	public static final int JSR = 014;
	public static final int RFS = 015;
	public static final int SOB = 016;
	public static final int JGE = 017;
	
	public static final int MLT = 020;
	public static final int DVD = 021;
	public static final int TRR = 022;
	public static final int AND = 023;
	public static final int ORR = 024;
	public static final int NOT = 025;
	
	public static final int SRC = 031;
	public static final int RRC = 032;
	
	public static final int IN = 061;
	public static final int OUT = 062;
	public static final int CHK = 063;

	public static final int HLT = 000;
	public static final int TRAP = 067;
	
	public static final int FADD = 033;
	public static final int FSUB = 034;
	public static final int VADD = 035;
	public static final int VSUB = 036;
	public static final int CNVRT = 037;
	public static final int LDFR = 050;
	public static final int STFR = 051;
	
	public static final int NOOP = 077;
	public static final int RET = 076;

	public static final int KEYBOARD = 0;
	public static final int PRINTER = 1;
	public static final int CARD_READER = 2;
	
	/* Offsets for decoding the instructions */
	public static final int OPCODE_LENGTH = 6;
	public static final int OPCODE_MASK = 0xfc00;
	public static final int OPCODE_POS = 10;

	public static final int INDIRECT_MASK = 0x0020;
	public static final int INDIRECT_POS = 5;
	public static final int INDEXING_MASK = 0x00c0;
	public static final int INDEXING_POS = 6;
	public static final int REGISTER_MASK = 0x0300;
	public static final int REGISTER_POS = 8;
	public static final int ADDRESS_MASK = 0x001f;
	public static final int ADDRESS_POS = 0;
	public static final int IMMEDIATE_MASK = 0x001f;
	public static final int IMMEDIATE_POS = 0;

	public static final int REGISTER2_MASK = INDEXING_MASK;
	public static final int REGISTER2_POS = INDEXING_POS;
	
	public static final int DEVICE_MASK = ADDRESS_MASK;
	public static final int DEVICE_POS = ADDRESS_POS;
	
	public static final int TRAP_MASK = 0x000f;
	public static final int TRAP_POS = 0;
	
	Processor p;
	int dataType = 0; //0: Integer, 1: Character

	/**
	 * 
	 */
	public InstructionSet(Processor p) {
		this.p = p;
	}
	
	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	/*
	 * Decode the LDR instruction and load MBR with the value at address
	 */
	public void ldrDecode() {
		System.out.println("ldrDecode");

		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int index = (ir & INDEXING_MASK) >> INDEXING_POS;
		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;
		//int reg = (ir & REGISTER_MASK) >> REGISTER_POS;

		//p.cout.print("LDR r" + Integer.toString(reg) + ", " + (index > 0 ? "x" + Integer.toString(index) + ", " : "" ) + Integer.toString(addr) + (indirect == 1 ? ", [" + Integer.toString(indirect) + "]\n" : "\n"));
		
		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (index != 0) {
			ea += p.ix[index - 1].getValue();
		}

		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.cu.getData(ea); // Fetch data from memory
	}

	/*
	 * Execute LDR instruction Read from MBR and write to Register
	 */
	public void ldrExecute() {
		System.out.println("ldrExecute");
		int ir = p.ir.getValue();
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;

		p.setR(reg, p.getMbr().getValue());
		p.setPc(p.getPc().getValue() + 1);
	}

	/*
	 * Decode the STR instruction and store in MBR with the value in register.
	 */
	public void strDecode() {
		System.out.println("strDecode");

		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int index = (ir & INDEXING_MASK) >> INDEXING_POS;
		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;
		//int reg = (ir & REGISTER_MASK) >> REGISTER_POS;
		
		//p.cout.print("STR r" + Integer.toString(reg) + ", " + (index > 0 ? "x" + Integer.toString(index) + ", " : "" ) + Integer.toString(addr) + (indirect == 1 ? ", [" + Integer.toString(indirect) + "]\n" : "\n"));
		
		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (index != 0) {
			ea += p.ix[index - 1].getValue();
		}

		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.setMar(ea); // Store data to memory
	}

	/*
	 * Execute STR instruction Read from Register and write to memory.
	 */
	public void strExecute() {
		System.out.println("strExecute");
		int ir = p.ir.getValue();
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;

		p.cu.setData(p.getR(reg).getValue());
		p.setPc(p.getPc().getValue() + 1);
	}

	/*
	 * Decode LDA instruction and store MAR with effective address.
	 */
	public void ldaDecode() {
		System.out.println("ldaDecode");

		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int index = (ir & INDEXING_MASK) >> INDEXING_POS;
		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;

		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (index != 0) {
			ea += p.ix[index - 1].getValue();
		}

		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.setMar(ea); // Store data to memory
	}

	/*
	 * Execute LDA instruction Load the register with address.
	 */
	public void ldaExecute() {
		System.out.println("strExecute");
		int ir = p.ir.getValue();
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;

		p.setR(reg, p.getMar().getValue());
		p.setPc(p.getPc().getValue() + 1);
	}

	/*
	 * Decode LDX instruction and load MAR with address and MBR with value at
	 * address
	 */
	public void ldxDecode() {
		System.out.println("ldxDecode");
		
		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;

		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.cu.getData(ea);
	}

	/*
	 * Execute LDX instruction write MBR to Index Register
	 */
	public void ldxExecute() {
		System.out.println("ldxExecute");
		int ir = p.ir.getValue();
		int index = (ir & INDEXING_MASK) >> INDEXING_POS;

		p.setIx(index, p.getMbr().getValue());
		p.setPc(p.getPc().getValue() + 1);
	}

	/*
	 * Decode STX instruction and store memory with effective address
	 * address
	 */
	public void stxDecode() {
		System.out.println("stxDecode");
		
		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;

		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}
		p.setMar(ea);
	}

	/*
	 * Execute STX instruction write value from memory to Index Register
	 */
	public void stxExecute() {
		System.out.println("stxExecute");
		int ir = p.ir.getValue();
		int index = (ir & INDEXING_MASK) >> INDEXING_POS;
		if (index != 0)
			p.cu.setData(p.getIx(index).getValue());
		
		p.setPc(p.getPc().getValue() + 1);
	}

	/*
	 * Decode AMR instruction 
	 */
	public void amrDecode() {
		System.out.println("amrDecode");
		
		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int index = (ir & INDEXING_MASK) >> INDEXING_POS;
		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;

		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (index != 0) {
			ea += p.ix[index - 1].getValue();
		}

		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.cu.getData(ea);
		System.out.println(p.getMbr().getValue());
		System.out.println("amrDec end");
	}

	/*
	 * Execute AMR instruction add memory to register
	 */
	public void amrExecute() {
		System.out.println("amrExecute");

		int ir = p.ir.getValue();
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;
		System.out.println(p.getMbr().getValue());
		System.out.println(p.getR(reg).getValue());

		p.setR(reg, p.cu.addData(p.getR(reg), p.getMbr().getValue()));
		System.out.println(p.getR(reg).getValue());
		
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("amrExe end");
	}

	/*
	 * Decode SMR instruction 
	 */
	public void smrDecode() {
		System.out.println("smrDecode");
		
		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int index = (ir & INDEXING_MASK) >> INDEXING_POS;
		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;

		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (index != 0) {
			ea += p.ix[index - 1].getValue();
		}

		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.cu.getData(ea);
	}

	/*
	 * Execute SMR instruction subtract memory from register
	 */
	public void smrExecute() {
		System.out.println("smrExecute");

		int ir = p.ir.getValue();
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;
		System.out.println(p.getMbr().getValue());
		System.out.println(p.getR(reg).getValue());

		p.setR(reg, p.cu.subData(p.getR(reg), p.getMbr().getValue()));
		System.out.println(p.getR(reg).getValue());
		
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("smrExe end");
	}

	public void airDecode() {
		// do nothing..
	}

	/*
	 * Execute AIR instruction add immediate to register
	 */
	public void airExecute() {
		System.out.println("airExecute");

		int ir = p.ir.getValue();
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;
		int data = (ir & IMMEDIATE_MASK) >> IMMEDIATE_POS;

		System.out.println(p.getR(reg).getValue());

		p.setR(reg, p.cu.addData(p.getR(reg), data));
		System.out.println(p.getR(reg).getValue());
		
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("airExe end");

	}

	public void sirDecode() {
		// do nothing..
	}

	/*
	 * Execute SIR instruction subtract immediate from register
	 */
	public void sirExecute() {
		System.out.println("sirExecute");

		int ir = p.ir.getValue();
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;
		int data = (ir & IMMEDIATE_MASK) >> IMMEDIATE_POS;

		System.out.println(p.getR(reg).getValue());

		p.setR(reg, p.cu.subData(p.getR(reg), data));
		System.out.println(p.getR(reg).getValue());
		
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("sirExe end");

	}

	public void jzDecode() {
		System.out.println("jzDecode");
		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;

		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.cu.getData(ea);

		System.out.println("jzDecode end");
	}

	public void jzExecute() {
		System.out.println("jzExecute");
		int ir = p.ir.getValue();
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;
		
		if (p.getR(reg).getValue() == 0) {
			p.setPc(p.getMbr());
		} else
			p.setPc(p.getPc().getValue() + 1);

		System.out.println("jzExecute end");
	}

	public void jneDecode() {
		System.out.println("jneDecode");
		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;

		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.cu.getData(ea);

		System.out.println("jneDecode end");
	}

	public void jneExecute() {
		System.out.println("jneExecute");
		int ir = p.ir.getValue();
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;
		
		if (p.getR(reg).getValue() != 0) {
			p.setPc(p.getMbr());
		} else
			p.setPc(p.getPc().getValue() + 1);

		System.out.println("jneExecute end");
	}

	public void jccDecode() {
		System.out.println("jccDecode");
		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;

		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.cu.getData(ea);

		System.out.println("jccDecode end");
	}

	public void jccExecute() {
		System.out.println("jccExecute");
		int ir = p.ir.getValue();
		int bit = (ir & REGISTER_MASK) >> REGISTER_POS;
		
		if ((p.getCc().getValue() & (0x01 << bit)) != 0) {
			p.setPc(p.getMbr());
		} else
			p.setPc(p.getPc().getValue() + 1);
		System.out.println("jccExecute end");
	}


	public void jmaDecode() {
		System.out.println("jmaDecode");
		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;

		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.cu.getData(ea);

		System.out.println("jmaDecode end");
	}

	public void jmaExecute() {
		System.out.println("jmaExecute");		
		p.setPc(p.getMbr());		
		System.out.println("jmaExecute end");
	}
	
	public void jsrDecode() {
		System.out.println("jsrDecode");
		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;

		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.cu.getData(ea);

		System.out.println("jsrDecode end");
	}

	public void jsrExecute() {
		System.out.println("jsrExecute");
		
		p.setR(3, p.getPc().getValue() + 1);
		p.setPc(p.getMbr());		

		/*
		 * FIXME: r0 should contain pointer to arguments
		 * 		Argument list should end with -17777 value
		 */
		System.out.println("jsrExecute end");
	}

	public void rfsDecode() {
		System.out.println("rfsDecode");
		//do nothing..
		System.out.println("rfsDecode end");
	}

	public void rfsExecute() {
		System.out.println("rfsExecute");
		
		int ir = p.ir.getValue();
		int immed = (ir & IMMEDIATE_MASK) >> IMMEDIATE_POS;
		
		p.setR(0, immed);
		p.setPc(p.getR(3));
		
		System.out.println("rfsExecute end");
	}
	
	public void sobDecode() {
		System.out.println("sobDecode");

		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;

		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.cu.getData(ea);
	
		System.out.println("sobDecode end");
	}

	public void sobExecute() {
		System.out.println("sobExecute");

		int ir = p.ir.getValue();
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;
		
		p.setR(reg, p.getR(reg).getValue() - 1);
		if (p.getR(reg).getValue() > 0) {
			p.setPc(p.getMbr());
		} else
			p.setPc(p.getPc().getValue() + 1);

		System.out.println("sobExecute end");
	}

	public void jgeDecode() {
		System.out.println("jgeDecode");

		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;

		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.cu.getData(ea);

		System.out.println("jgeDecode end");
	}

	public void jgeExecute() {
		System.out.println("jgeExecute");

		int ir = p.ir.getValue();
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;
		
		if (p.getR(reg).getValue() >= 0) {
			p.setPc(p.getMbr());
		} else
			p.setPc(p.getPc().getValue() + 1);

		System.out.println("jgeExecute end");
	}
	
	public void mltDecode() {
		System.out.println("mltDecode");
		
		//do nothing..
		System.out.println("mltDecode end");
	}

	public void dvdDecode() {
		System.out.println("dvdDecode");
		
		//do nothing..
		System.out.println("dvdDecode end");
	}

	public void trrDecode() {
		System.out.println("trrDecode");
		
		//do nothing..
		System.out.println("trrDecode end");
	}

	public void andDecode() {
		System.out.println("andDecode");
		
		//do nothing..
		System.out.println("andDecode end");
	}

	public void orrDecode() {
		System.out.println("arrDecode");
		
		//do nothing..
		System.out.println("orrDecode end");
	}

	public void notDecode() {
		System.out.println("notDecode");
		
		//do nothing..
		System.out.println("notDecode end");
	}

	public void hltDecode() {
		System.out.println("hltDecode");
		
		//do nothing..
		System.out.println("hltDecode end");
	}

	public void trapDecode() {
		System.out.println("trapDecode");

		System.out.println("trapDecode end");
	}

	public void mltExecute() {
		System.out.println("mltExecute");
		
		int ir = p.ir.getValue();
		int r1 = (ir & REGISTER_MASK) >> REGISTER_POS;
		int r2 = (ir & REGISTER2_MASK) >> REGISTER2_POS;
		
		p.cu.mulData(r1, r2);
		
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("mltExecute end");
	}

	public void dvdExecute() {
		System.out.println("dvdExecute");
		
		int ir = p.ir.getValue();
		int r1 = (ir & REGISTER_MASK) >> REGISTER_POS;
		int r2 = (ir & REGISTER2_MASK) >> REGISTER2_POS;
		
		p.cu.divData(r1, r2);
		
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("dvdExecute end");
	}

	public void trrExecute() {
		System.out.println("trrExecute");
		
		int ir = p.ir.getValue();
		int r1 = (ir & REGISTER_MASK) >> REGISTER_POS;
		int r2 = (ir & REGISTER2_MASK) >> REGISTER2_POS;
		
		p.cu.testData(r1, r2);
		
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("trrExecute end");
	}

	public void andExecute() {
		System.out.println("andExecute");
		
		int ir = p.ir.getValue();
		int r1 = (ir & REGISTER_MASK) >> REGISTER_POS;
		int r2 = (ir & REGISTER2_MASK) >> REGISTER2_POS;
		
		p.cu.andData(r1, r2);
		
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("andExecute end");
	}

	public void orrExecute() {
		System.out.println("orrExecute");
		
		int ir = p.ir.getValue();
		int r1 = (ir & REGISTER_MASK) >> REGISTER_POS;
		int r2 = (ir & REGISTER2_MASK) >> REGISTER2_POS;
		
		p.cu.orData(r1, r2);
		
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("orrExecute end");
	}

	public void notExecute() {
		System.out.println("notExecute");
		
		int ir = p.ir.getValue();
		int r = (ir & REGISTER_MASK) >> REGISTER_POS;
		
		p.cu.notData(r);
		
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("notExecute end");
	}

	public void hltExecute() {
		System.out.println("hltExecute");
		
		p.setPc(-1);
		p.cu.halt();
		
		System.out.println("hltExecute end");
	}

	public void trapExecute() {
		System.out.println("trapExecute");
		
		int ir = p.ir.getValue();
		int code = (ir & TRAP_MASK) >> TRAP_POS;
		
		p.cu.setData(2, p.getPc().getValue() + 1);
		int tbl_addr = p.cu.getData(0);
		p.cu.getData(tbl_addr + code);
		p.setIr(p.getMbr());

		p.runStep();
		
		p.setPc(p.cu.getData(2));
		
		System.out.println("trapExecute end");
	}

	public void inDecode() {
		System.out.println("inDecode");
		//do nothing
		System.out.println("inDecode end");
	}

	public void inExecute() {
		System.out.println("inExecute");

		int ir = p.ir.getValue();
		int r = (ir & REGISTER_MASK) >> REGISTER_POS;
		int dev = (ir & DEVICE_MASK) >> DEVICE_POS;
		
		switch(dev) {
		case KEYBOARD:
			if (dataType == 1) 
				p.setR(r, p.kb.getChar());
			else 
				p.setR(r, p.kb.getInt());
			break;
		case CARD_READER:
			p.setR(r, p.cr.getChar());
			break;
		default:
			System.err.println("Invalid deviceid: " + dev);
			break;	
		}
		
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("inExecute end");
	}	
	
	public void outDecode() {
		System.out.println("outDecode");
		//do nothing
		System.out.println("outDecode end");
	}

	public void outExecute() {
		System.out.println("outExecute");
		
		int ir = p.ir.getValue();
		int r = (ir & REGISTER_MASK) >> REGISTER_POS;
		int dev = (ir & DEVICE_MASK) >> DEVICE_POS;
		
		switch(dev) {
		case PRINTER:
			if (dataType == 1) {
				System.out.println("value:" + p.getR(r).getValue() + ":" + (char)p.getR(r).getValue());
				p.cout.printChar(p.getR(r).getValue());
			}
			else
				p.cout.print(p.getR(r).getValue());
			break;
		default:
			System.err.println("Invalid deviceid: " + dev);
			break;	
		}
		
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("outExecute end");
	}

	public void chkDecode() {
		System.out.println("chkDecode");
		//do nothing..
		System.out.println("chkDecode end");
	}

	public void chkExecute() {
		System.out.println("chkExecute");
		
		int ir = p.ir.getValue();
		int r = (ir & REGISTER_MASK) >> REGISTER_POS;
		int dev = (ir & DEVICE_MASK) >> DEVICE_POS;
		
		switch(dev) {
		case KEYBOARD:
			p.setR(r, p.kb.getStatus());
			break;
		case PRINTER:
			p.setR(r, p.cout.getStatus());
			break;
		case CARD_READER:
			p.setR(r, p.cr.getStatus());
			break;
		default:
			System.err.println("Invalid deviceid: " + dev);
			break;	
		}
		
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("chkExecute end");
	}
	
	public static final int AL_MASK = 0x0080;
	public static final int AL_POS = 7;
	public static final int RL_MASK = 0x0040;
	public static final int RL_POS = 6;
	public static final int COUNT_MASK = 0x000f;
	public static final int COUNT_POS = 0;
	
	public void srcDecode() {
		System.out.println("srcDecode");
		System.out.println("srcDecode end");
	}
	
	public void srcExecute() {
		System.out.println("srcExecute");
		int ir = p.ir.getValue();
		int r = (ir & REGISTER_MASK) >> REGISTER_POS;
		
		int al = (ir & AL_MASK) >> AL_POS;
		int rl = (ir & RL_MASK) >> RL_POS;
		int count = (ir & COUNT_MASK) >> COUNT_POS;
		
		p.cu.shiftData(r, al, rl, count);
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("srcExecute end");
	}
	
	public void rrcDecode() {
		System.out.println("rrcDecode");
		//do nothing
		System.out.println("rrcDecode end");
	}
	
	public void rrcExecute() {
		System.out.println("rrcExecute");
		int ir = p.ir.getValue();
		int r = (ir & REGISTER_MASK) >> REGISTER_POS;
		
		int al = (ir & AL_MASK) >> AL_POS;
		int rl = (ir & RL_MASK) >> RL_POS;
		int count = (ir & COUNT_MASK) >> COUNT_POS;
		
		p.cu.rotateData(r, al, rl, count);
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("rrcExecute end");
	}
	
	public void faddDecode() {
		System.out.println("faddDecode enter");
		System.out.println("faddDecode exit");
	}
	
	public void fsubDecode() {
		System.out.println("fsubDecode enter");
		System.out.println("fsubDecode exit");
	}
	
	public void vaddDecode() {
		System.out.println("vaddDecode enter");
		// do nothing..
		System.out.println("vaddDecode exit");
	}
	
	public void vsubDecode() {
		System.out.println("vsubDecode enter");
		// do nothing..
		System.out.println("vsubDecode exit");
	}
	
	public void cnvrtDecode() {
		System.out.println("cnvrtDecode enter");
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int index = (ir & INDEXING_MASK) >> INDEXING_POS;
		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;
		//int reg = (ir & REGISTER_MASK) >> REGISTER_POS;

		//p.cout.print("LDR r" + Integer.toString(reg) + ", " + (index > 0 ? "x" + Integer.toString(index) + ", " : "" ) + Integer.toString(addr) + (indirect == 1 ? ", [" + Integer.toString(indirect) + "]\n" : "\n"));
		
		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (index != 0) {
			ea += p.ix[index - 1].getValue();
		}

		/*if (indirect != 0) {
			ea = p.cu.getData(ea);
		}*/

		p.cu.getData(ea); // Fetch data from memory
		
		System.out.println("cnvrtDecode exit");
	}
	
	public void ldfrDecode() {
		System.out.println("ldfrDecode enter");
		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int index = (ir & INDEXING_MASK) >> INDEXING_POS;
		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;
		//int reg = (ir & REGISTER_MASK) >> REGISTER_POS;

		//p.cout.print("LDR r" + Integer.toString(reg) + ", " + (index > 0 ? "x" + Integer.toString(index) + ", " : "" ) + Integer.toString(addr) + (indirect == 1 ? ", [" + Integer.toString(indirect) + "]\n" : "\n"));
		
		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (index != 0) {
			ea += p.ix[index - 1].getValue();
		}

		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.cu.getData(ea); // Fetch data from memory

		System.out.println("ldfrDecode exit");
	}
	
	public void stfrDecode() {
		System.out.println("stfrDecode enter");
		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int index = (ir & INDEXING_MASK) >> INDEXING_POS;
		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;
		//int reg = (ir & REGISTER_MASK) >> REGISTER_POS;
		
		//p.cout.print("STR r" + Integer.toString(reg) + ", " + (index > 0 ? "x" + Integer.toString(index) + ", " : "" ) + Integer.toString(addr) + (indirect == 1 ? ", [" + Integer.toString(indirect) + "]\n" : "\n"));
		
		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (index != 0) {
			ea += p.ix[index - 1].getValue();
		}

		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.setMar(ea); // Store data to memory

		System.out.println("stfrDecode exit");
	}

	public void faddExecute() {
		System.out.println("faddExecute enter");
		int ir = p.ir.getValue();
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int index = (ir & INDEXING_MASK) >> INDEXING_POS;
		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;

		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (index != 0) {
			ea += p.ix[index - 1].getValue();
		}

		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.cu.getData(ea); // Fetch data from memory
		float f1 = p.fpu.toFloat(p.getMbr().getValue());
		float f = p.fpu.toFloat(p.fr[reg].getValue());
		float f2 = f;
		f += f1;
		System.out.println(f);
		p.setFr(reg, p.fpu.fromFloat(f));
		p.setPc(p.getPc().getValue() + 1);
		p.cout.print(f2 + "+" + f1 + "=" + p.fpu.toFloat(p.fr[reg].getValue()) + "\n");
		System.out.println("faddExecute exit");
	}
	
	public void fsubExecute() {
		System.out.println("fsubExecute enter");
		int ir = p.ir.getValue();
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int index = (ir & INDEXING_MASK) >> INDEXING_POS;
		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;

		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (index != 0) {
			ea += p.ix[index - 1].getValue();
		}

		if (indirect != 0) {
			ea = p.cu.getData(ea);
		}

		p.cu.getData(ea); // Fetch data from memory
		float f1 = p.fpu.toFloat(p.getMbr().getValue());
		System.out.println(f1);
		float f = p.fpu.toFloat(p.fr[reg].getValue());
		System.out.println(f);
		float f2 = f;
		f -= f1;
		System.out.println(f);
		p.setFr(reg, p.fpu.fromFloat(f));
		p.cout.print(f2 + "-" + f1 + "=" + p.fpu.toFloat(p.fr[reg].getValue()) + "\n");
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("fsubExecute exit");
	}
	
	public void vaddExecute() {
		System.out.println("vaddExecute enter");
		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int index = (ir & INDEXING_MASK) >> INDEXING_POS;
		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;

		//p.cout.print("LDR r" + Integer.toString(reg) + ", " + (index > 0 ? "x" + Integer.toString(index) + ", " : "" ) + Integer.toString(addr) + (indirect == 1 ? ", [" + Integer.toString(indirect) + "]\n" : "\n"));
		
		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (index != 0) {
			ea += p.ix[index - 1].getValue();
		}
		int ea2 = ea + 1;
		
		if (indirect != 0) {
			ea = p.cu.getData(ea);
			ea2 = p.cu.getData(ea2);
		}
		p.fpu.vadd(reg, ea, ea2);
		
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("vaddExecute exit");
	}
	
	public void vsubExecute() {
		System.out.println("vsubExecute enter");
		/*
		 * Decode IR and get Address, Index register no. and indirect addressing
		 * flag
		 */
		int ir = p.ir.getValue();
		int addr = (ir & ADDRESS_MASK) >> ADDRESS_POS;

		int index = (ir & INDEXING_MASK) >> INDEXING_POS;
		int indirect = (ir & INDIRECT_MASK) >> INDIRECT_POS;
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;

		/*
		 * Calculate Effective address using indirect and indexing.
		 */
		int ea = addr;
		if (index != 0) {
			ea += p.ix[index - 1].getValue();
		}
		int ea2 = ea + 1;
		
		if (indirect != 0) {
			ea = p.cu.getData(ea);
			ea2 = p.cu.getData(ea2);
		}
		p.fpu.vsub(reg, ea, ea2);
		
		p.setPc(p.getPc().getValue() + 1);
		System.out.println("vsubExecute exit");
	}
	
	public void cnvrtExecute() {
		System.out.println("cnvrtExecute enter");

		int ir = p.ir.getValue();
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;

		if (p.getR(reg).getValue() == 0) {
			float f = p.fpu.toFloat(p.getMbr().getValue());
			System.out.println(f);
			int i = (int)f;
			System.out.println(i);
			p.setR(reg, i);
			p.cout.print(f + "->" + p.getR(reg).getValue() + "\n");
			
		} else {
			System.out.println(p.getMbr().getValue());
			float f = (float)p.getMbr().getValue();
			System.out.println(f);
			p.setFr(0, p.fpu.fromFloat(f));
			p.cout.print((int)f + "->" + p.fpu.toFloat(p.getFr(0).getValue()) + "\n");
		}
		p.setPc(p.getPc().getValue() + 1);
		
		System.out.println("cnvrtExecute exit");
	}
	
	public void ldfrExecute() {
		System.out.println("ldfrExecute enter");
		int ir = p.ir.getValue();
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;

		p.setFr(reg, p.getMbr().getValue());
		p.setPc(p.getPc().getValue() + 1);

		System.out.println("ldfrExecute exit");
	}
	
	public void stfrExecute() {
		System.out.println("stfrExecute enter");
		int ir = p.ir.getValue();
		int reg = (ir & REGISTER_MASK) >> REGISTER_POS;

		p.cu.setData(p.getFr(reg).getValue());
		p.setPc(p.getPc().getValue() + 1);

		System.out.println("stfrExecute exit");
	}
	
	
	/*
	 * Decode Instructions
	 */
	public int decode() {
		System.out.println("ins decode");
		int opcode = (p.getIr().getValue() & OPCODE_MASK) >> OPCODE_POS;

		switch (opcode) {
		case LDR:
			ldrDecode();
			break;
		case STR:
			strDecode();
			break;
		case LDA:
			ldaDecode();
			break;
		case LDX:
			ldxDecode();
			break;
		case STX:
			stxDecode();
			break;
		case AMR:
			amrDecode();
			break;
		case SMR:
			smrDecode();
			break;
		case AIR:
			airDecode();
			break;
		case SIR:
			sirDecode();
			break;
		case JZ:
			jzDecode();
	        break;
		case JNE:
			jneDecode();
	        break;
		case JCC:
			jccDecode();
	        break;
		case JMA:
			jmaDecode();
	        break;
		case JSR:
			jsrDecode();
	        break;
		case RFS:
			rfsDecode();
	        break;
		case SOB:
			sobDecode();
	        break;
		case JGE:
			jgeDecode();
	        break;
		case MLT:
			mltDecode();
	        break;
		case DVD:
			dvdDecode();
	        break;
		case TRR:
			trrDecode();
	        break;
		case AND:
			andDecode();
	        break;
		case ORR:
			orrDecode();
	        break;
		case NOT:
			notDecode();
	        break;
	    
		case SRC:
			srcDecode();
			break;
		case RRC:
			rrcDecode();
			break;
	        
		case IN:
			inDecode();
			break;
		case OUT:
			outDecode();
			break;
		case CHK:
			chkDecode();
			break;
	        
	        
		case HLT:
			hltDecode();
	        break;
		case TRAP:
			trapDecode();
	        break;

		case NOOP:
			System.out.println("NOOP Decode");
			break;
		case RET:
			System.out.println("RET Decode");
			break;
			
		case FADD:
			faddDecode();
			break;
		case FSUB:
			fsubDecode();
			break;
		case VADD:
			vaddDecode();
			break;
		case VSUB:
			vsubDecode();
			break;
		case CNVRT:
			cnvrtDecode();
			break;
		case LDFR:
			ldfrDecode();
			break;
		case STFR:
			stfrDecode();
			break;
			
		default:
			System.out.println("Invalid Opcode");
			return -1;
		}

		return opcode;
	}

	/*
	 * Execute Instructions
	 */
	public int execute() {
		int opcode = (p.getIr().getValue() & OPCODE_MASK) >> OPCODE_POS;

		switch (opcode) {
		case LDR:
			ldrExecute();
			break;
		case STR:
			strExecute();
			break;
		case LDA:
			ldaExecute();
			break;
		case LDX:
			ldxExecute();
			break;
		case STX:
			stxExecute();
			break;
		case AMR:
			amrExecute();
			break;
		case SMR:
			smrExecute();
			break;
		case AIR:
			airExecute();
			break;
		case SIR:
			sirExecute();
			break;
		case JZ:
			jzExecute();
	        break;
		case JNE:
			jneExecute();
	        break;
		case JCC:
			jccExecute();
	        break;
		case JMA:
			jmaExecute();
	        break;
		case JSR:
			jsrExecute();
	        break;
		case RFS:
			rfsExecute();
	        break;
		case SOB:
			sobExecute();
	        break;
		case JGE:
			jgeExecute();
	        break;
		case MLT:
			mltExecute();
	        break;
		case DVD:
			dvdExecute();
	        break;
		case TRR:
			trrExecute();
	        break;
		case AND:
			andExecute();
	        break;
		case ORR:
			orrExecute();
	        break;
		case NOT:
			notExecute();
	        break;

		    
		case SRC:
			srcExecute();
			break;
		case RRC:
			rrcExecute();
			break;	        
	        
		case IN:
			inExecute();
			break;
		case OUT:
			outExecute();
			break;
		case CHK:
			chkExecute();
			break;
		
		case HLT:
			hltExecute();
	        break;
		case TRAP:
			trapExecute();
	        break;

		case NOOP:
			System.out.println("NOOP Execute");
			p.setPc(p.getPc().getValue() + 1);
			break;
		case RET:
			System.out.println("RET Execute");
			p.setPc(-1);
			return -1;

		case FADD:
			faddExecute();
			break;
		case FSUB:
			fsubExecute();
			break;
		case VADD:
			vaddExecute();
			break;
		case VSUB:
			vsubExecute();
			break;
		case CNVRT:
			cnvrtExecute();
			break;
		case LDFR:
			ldfrExecute();
			break;
		case STFR:
			stfrExecute();
			break;
			
		default:
			System.out.println("Invalid Opcode");
			return -1;
		}

		return opcode;
	}
}
