package csasimulator;

/**
 * 
 */

/**
 * @author phani
 * 
 * Register -> Name of the register, 
 * 			value of register (expected 16bit. using INT =32bit for convenience.)
 * 			bitlength: TODO: validation of input based on bitlength not done.
 *
 */
public class Register {

	private String name;
	private int value;
	private int bitLength;
	/**
	 * 
	 */
	public Register(String name, int value, int bitLength) {
		this.name = name;
		this.value = value;
		this.bitLength = bitLength;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public int getValue() {

		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {

		this.value = value;
	}
	/**
	 * @return the bitLength
	 */
	public int getBitLength() {
		return bitLength;
	}
	/**
	 * @param bitLength the bitLength to set
	 */
	public void setBitLength(int bitLength) {
		this.bitLength = bitLength;
	}
}
