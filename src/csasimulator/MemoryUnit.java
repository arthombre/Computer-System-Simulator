package csasimulator;

/**
 * 
 */

/**
 * @author phani
 *
 * Memory unit of the Processor.
 * Handles read/write operations on Memory.
 * 
 */
public class MemoryUnit {

	Memory m;
	Cache c;
	Processor p;
	/**
	 * 
	 */
	public MemoryUnit(Processor p, Memory m, Cache c) {
		this.p = p;
		this.m = m;
		this.c = c;
	}


/*	 
	 * fetch data to MBR from memory address in MAR
	 
	public void read() {
		p.setMbr(m.getData(p.mar.getValue()));
	}
	
	 
	 * write data in MBR to memory at address in MAR
	 
	public void write() {
		m.setData(p.mar.getValue(), p.mbr.getValue());
	}*/
	
	/* 
	 * fetch data to MBR from memory address in MAR
	 */
	public void read() {
		p.setMbr(c.get(p.getMar().getValue()));
	}
	
	/* 
	 * write data in MBR to memory at address in MAR
	 */
	public void write() {
		c.put(p.getMar().getValue(), p.getMbr().getValue());
	}
}
