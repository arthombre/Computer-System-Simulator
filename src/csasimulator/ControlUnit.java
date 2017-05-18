package csasimulator;

/**
 * 
 */

/**
 * @author phani
 *
 * Control Unit of the Processor
 * This is the main piece of hardware which manages 
 * the control signals, co-ordinates with the rest of the processor units.
 * 
 */
public class ControlUnit {

	Processor p;
	/**
	 * 
	 */
	public ControlUnit(Processor p) {
		this.p = p;
	}

	/*
	 * co-ordinate with Memory unit and get value from the address.
	 * Mem unit expects, address in MAR and writes value to MBR.
	 */
	public int getData(int address) {
		this.p.setMar(address);
		this.p.mu.read();
		return this.p.getMbr().getValue();
	}

	/*
	 * co-ordinate with Memory unit and set the value at the address.
	 * Mem unit expects address in MAR and reads value from MBR.
	 */
	public void setData(int address, int data) {
		this.p.setMar(address);
		this.p.setMbr(data);
		this.p.mu.write();
	}
	
	/*
	 * same as above. except, address is already in MAR
	 */
	public void setData(int data) {
		this.p.setMbr(data);
		this.p.mu.write();
	}
	
	/*
	 * co-ordinate with ALU and add value to register.
	 */
	public int addData(Register r, int b) {
		this.p.alu.add(r, b);
		return r.getValue();
	}
	
	/*
	 * co-ordinate with ALU and subtract value from register.
	 */
	public int subData(Register r, int b) {
		this.p.alu.sub(r, b);
		return r.getValue();
	}

	public void mulData(int r1, int r2) {
		this.p.alu.mul(r1, r2);
	}
	
	public void divData(int r1, int r2) {
		this.p.alu.div(r1, r2);
	}
	
	public void testData(int r1, int r2) {
		this.p.alu.cmp(r1, r2);
	}
	
	public void andData(int r1, int r2) {
		this.p.alu.and(r1, r2);
	}
	
	public void orData(int r1, int r2) {
		this.p.alu.orr(r1, r2);
	}
	
	public void notData(int r) {
		this.p.alu.not(r);
	}
	
	public void shiftData(int r, int al, int rl, int count) {
		if (al == 0) {
			//arithmetic
			if (rl == 0) {
				//right
				p.alu.arsh(r, count);
			} else {
				//left
				p.alu.alsh(r, count);
			}
		} else {
			//logical
			if (rl == 0) {
				//right
				p.alu.lrsh(r, count);
			} else {
				//left
				p.alu.llsh(r, count);
			}
		}
	}
	
	public void rotateData(int r, int al, int rl, int count) {
		if (al == 1) {
			//logical
			if (rl == 0) {
				//right
				p.alu.rrrc(r, count);
			} else {
				//left
				p.alu.lrrc(r, count);
			}
		} else {
			System.err.println("Invalid instruction");
		}
	}
		
	/* 
	 * for Phase I, PC is not used. So, just loading IR from UI!
	 */
	public void fetchStep() {
		p.cout.getBitmap(p.getIr());
	}

	public void fetch() {
		getData(p.getPc().getValue());
		p.setIr(p.getMbr());
	}
	
	/*
	 * use instruction set obj to decode the instruction in IR.
	 */
	public void decode() {
		p.isa.decode();
	}
	
	/*
	 * use instruction set obj to execute instruction in IR using Control unit
	 */
	public void execute() {
		p.isa.execute();
	}
	
	public void halt() {
		p.reset();
		p.mu.m.reset();
		p.mu.c.reset();
		p.cout.disablePress();
	}
	/*
	 * ideally, it's a while(1) loop around fetch-decode-execute.
	 * But here, we support only "Single Step" for Phase I
	 */
	public void runStep() {
		fetchStep();
		decode();
		execute();
	}
	
	public void run() {
		fetch();
		decode();
		execute();
	}

}
