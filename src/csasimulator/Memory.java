package csasimulator;

/**
 * 
 */

/**
 * @author phani
 *
 * Memory module of the Computer.
 * 
 * Expected to be 2048 words
 * And word size of 16bit.
 * 
 * For convenience, using INT of size 32bit. 
 * Bitwidth validations are TODO.
 */
public class Memory {

	public static final int MEMORY_SIZE = 2048;
	int data[];
	/**
	 * 
	 */
	public Memory() {
		data = new int[MEMORY_SIZE];
	}

	/*
	 * Get data from offset=address in memory
	 */
	public int getData(int address) {
		System.out.println("[" + address + "]:" + data[address] );
		return data[address];
	}
	
	/*
	 * set data to offset=address in memory
	 */
	public void setData(int address, int data) {
		this.data[address] = data;
		System.out.println("[" + address + "]:" + this.data[address]);
	}
	
	/*
	 * unused: used for convenience.
	 */
	public void reset() {
		//int j = 1;
		for (int i = 0; i < MEMORY_SIZE; i ++ ) {
			this.data[i] = 0;
			//System.out.println(i + ":" + this.data[i]);
		}

		this.data[MEMORY_SIZE - 1] = 0;
	}
}
