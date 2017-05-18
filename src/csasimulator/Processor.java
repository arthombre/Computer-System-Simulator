package csasimulator;

/**
 * 
 */

/**
 * @author phani
 * 
 * Processor or CPU
 * It contains, 
 * 	registers(general and special purpose)
 * 	Memory Unit, Control Unit, ALU
 * 	Instruction set architecture
 *  address(reference) to Console for Input/Output.
 * 
 */
public class Processor implements Runnable {

	public static final int WORD_LENGTH = 16;
	public static final int PROGRAM_START_ADDR = 1024;
	public static final int INPUT_START_ADDR = 256;
	public static final int KEY_DATA_SIZE_LOC = 2047;
	public Register r[];
	public Register ix[];
	public Register pc;
	public Register cc;
	public Register ir;
	public Register mar;
	public Register mbr;
	public Register msr;
	public Register mfr;
	public Register fr[];
	
	
	public MemoryUnit mu;
	public ControlUnit cu;
	public ALUnit alu;
	public Cache l1c;
	public FPUnit fpu;
	
	public Console cout;
	public Keyboard kb;
	public CardReader cr;
	public InstructionSet isa;
	/**
	 * 
	 */
	public Processor(Console cout, Memory m, Keyboard kb, CardReader cr) {
		r = new Register[4];
		r[0] = new Register("R0", 0, WORD_LENGTH);
		r[1] = new Register("R1", 0, WORD_LENGTH);
		r[2] = new Register("R2", 0, WORD_LENGTH);
		r[3] = new Register("R3", 0, WORD_LENGTH);
		
		pc = new Register("PC", 0, 12);
		cc = new Register("CC", 0, 4);
		ir = new Register("IR", 0, WORD_LENGTH);
		mar = new Register("MAR", 0, WORD_LENGTH);
		mbr = new Register("MBR", 0, WORD_LENGTH);
		msr = new Register("MSR", 0, WORD_LENGTH);
		mfr = new Register("MFR", 0, WORD_LENGTH);
		
		ix = new Register[3];
		ix[0] = new Register("IX1", 0, WORD_LENGTH);
		ix[1] = new Register("IX2", 0, WORD_LENGTH);
		ix[2] = new Register("IX3", 0, WORD_LENGTH);
		
		fr = new Register[2];
		fr[0] = new Register("FP0", 0, WORD_LENGTH);
		fr[1] = new Register("FP1", 0, WORD_LENGTH);
		
		this.cout = cout;
		this.kb = kb;
		this.cr = cr;
		l1c = new Cache(this, m);
		mu = new MemoryUnit(this, m, l1c);
		cu = new ControlUnit(this);
		alu = new ALUnit(this);
		isa = new InstructionSet(this); 
		fpu = new FPUnit(this);
	}
	
	
	/**
	 * @return the r
	 */
	public Register[] getR() {
		return r;
	}


	/**
	 * @param r the r to set
	 */
	public void setR(Register[] r) {
		for(int i = 0; i < 4; i ++) {
			this.r[i].setValue(r[i].getValue());
			cout.print(this.r[i]);
		}
	}

	/**
	 * @return the r
	 */
	public Register getR(int index) {
		return r[index];
	}


	/**
	 * @param r the r to set
	 */
	public void setR(int index, Register r) {
		this.r[index].setValue(r.getValue());
		cout.print(this.r[index]);
	}

	/**
	 * @param r the r to set
	 */
	public void setR(int index, int val) {
		this.r[index].setValue(val);
		cout.print(this.r[index]);
	}


	/**
	 * @return the x
	 */
	public Register[] getIx() {
		return ix;
	}


	/**
	 * @param x the x to set
	 */
	public void setIx(Register[] ix) {
		for(int i = 0; i < 3; i ++) {
			this.ix[i].setValue(ix[i].getValue());
			cout.print(this.ix[i]);
		}
	}

	/**
	 * @return the x
	 */
	public Register getIx(int index) {
		return ix[index - 1];
	}


	/**
	 * @param x the x to set
	 */
	public void setIx(int index, Register ix) {
		this.ix[index - 1].setValue(ix.getValue());
		cout.print(this.ix[index - 1]);
	}

	/**
	 * @param x the x to set
	 */
	public void setIx(int index, int val) {
		this.ix[index - 1].setValue(val);
		cout.print(this.ix[index - 1]);
	}
	
	/**
	 * @return the pc
	 */
	public Register getPc() {
		return pc;
	}


	/**
	 * @param pc the pc to set
	 */
	public void setPc(Register pc) {
		this.pc.setValue(pc.getValue());
	}

	/**
	 * @param pc the pc to set
	 */
	public void setPc(int val) {
		this.pc.setValue(val);
	}
	
	/**
	 * @return the cc
	 */
	public Register getCc() {
		return cc;
	}


	/**
	 * @param cc the cc to set
	 */
	public void setCc(Register cc) {
		this.cc.setValue(cc.getValue());
	}

	/**
	 * @param cc the cc to set
	 */
	public void setCc(int val) {
		this.cc.setValue(val);
	}

	/**
	 * @return the ir
	 */
	public Register getIr() {
		return ir;
	}


	/**
	 * @param ir the ir to set
	 */
	public void setIr(Register ir) {
		this.ir.setValue(ir.getValue());;
	}

	
	/**
	 * @param cc the cc to set
	 */
	public void setIr(int val) {
		this.ir.setValue(val);
	}

	
	/**
	 * @return the mar
	 */
	public Register getMar() {
		return mar;
	}


	/**
	 * @param mar the mar to set
	 */
	public void setMar(Register mar) {
		this.mar.setValue(mar.getValue());
		cout.print(this.mar);
	}

	/**
	 * @param cc the cc to set
	 */
	public void setMar(int val) {
		this.mar.setValue(val);
		cout.print(this.mar);
	}


	/**
	 * @return the mbr
	 */
	public Register getMbr() {
		return mbr;
	}

	/**
	 * @param cc the cc to set
	 */
	public void setMbr(int val) {
		this.mbr.setValue(val);
		cout.print(this.mbr);
	}

	
	/**
	 * @param mbr the mbr to set
	 */
	public void setMbr(Register mbr) {
		this.mbr.setValue(mbr.getValue());
		cout.print(this.mbr);
	}


	/**
	 * @return the msr
	 */
	public Register getMsr() {
		return msr;
	}


	/**
	 * @param msr the msr to set
	 */
	public void setMsr(Register msr) {
		this.msr.setValue(msr.getValue());
	}

	/**
	 * @param cc the cc to set
	 */
	public void setMsr(int val) {
		this.msr.setValue(val);
	}

	
	/**
	 * @return the mfr
	 */
	public Register getMfr() {
		return mfr;
	}


	/**
	 * @param mfr the mfr to set
	 */
	public void setMfr(Register mfr) {
		this.mfr.setValue(mfr.getValue());
	}

	/**
	 * @param cc the cc to set
	 */
	public void setMfr(int val) {
		this.mfr.setValue(val);
	}


	/**
	 * @return the r
	 */
	public Register[] getFr() {
		return fr;
	}


	/**
	 * @param r the r to set
	 */
	public void setFr(Register[] r) {
		for(int i = 0; i < 2; i ++) {
			this.fr[i].setValue(fr[i].getValue());
			cout.print(this.fr[i]);
		}
	}

	/**
	 * @return the r
	 */
	public Register getFr(int index) {
		return fr[index];
	}

	/**
	 * @param r the r to set
	 */
	public void setFr(int index, Register r) {
		this.fr[index].setValue(r.getValue());
		System.out.println(Integer.toBinaryString(this.fr[index].getValue()) + "=" + fpu.toFloat(this.fr[index].getValue()));
		//cout.print(this.fr[index]);
	}

	/**
	 * @param r the r to set
	 */
	public void setFr(int index, int val) {
		this.fr[index].setValue(val);
		System.out.println(Integer.toBinaryString(this.fr[index].getValue()) + "=" + fpu.toFloat(this.fr[index].getValue()));
		//cout.print(this.fr[index]);
	}

	/* 
	 * Run instruction using Control unit
	 */
	public void run() {
		while (getPc().getValue() >= 0)
			cu.run();
	}
	
	public void reset() {
		for(int i = 0; i < 4; i ++) {
			setR(i, 0);
			cout.clearBitmap(getR(i));
		}
		for(int i = 1; i < 4; i ++) {
			setIx(i, 0);
			cout.clearBitmap(getIx(i));
		}
		setPc(-1);
		setCc(0);
		setIr(0);
		cout.clearBitmap(getIr());
		setMar(0);
		cout.clearBitmap(getMar());
		setMbr(0);
		cout.clearBitmap(getMbr());
		setMsr(0);
		setMfr(0);
		for(int i = 0; i < 2; i ++) {
			setFr(i, 0);
			//cout.clearBitmap(getFr(i));
		}
	}
	
	public void runStep() {
		cu.runStep();
	}
}
