package csasimulator;

/**
 * 
 */

/**
 * @author phani
 *
 * Arithmetic and Logic Unit in Processor
 * Current logical operations supported:
 * 		Add to register, a value.
 * 		Subtract from register, a value.  
 */
public class ALUnit {

	/* Use accumulator register to store temporary results? */
	Register accum;
	Processor p;
	/**
	 * 
	 */
	public ALUnit(Processor p) {
		this.p = p;
		accum = new Register("ACCUM", 0, Processor.WORD_LENGTH);
	}
	
	public void add(Register r, int b) {
		accum.setValue(b);
		r.setValue(r.getValue() + accum.getValue());
	}
	
	public void sub(Register r, int b) {
		accum.setValue(b);
		r.setValue(r.getValue() - accum.getValue());
	}
	
	public void mul(int r1, int r2) {
		long val = p.getR(r1).getValue() * p.getR(r2).getValue();
		if ((val) > Integer.MAX_VALUE) {			
			p.setCc(0x01);
			return;
		} else {
			p.setCc(0x00);
		}
		int vali = (int) val;
		p.setR(r1, (vali & 0xffff0000) >> 16);
		p.setR(r1 + 1, (vali & 0x0000ffff));

	}
	
	public void div(int r1, int r2) {
		
		if (p.getR(r2).getValue() == 0) {			
			p.setCc(0x04);
			return;
		} else {
			p.setCc(0x00);
		}
		
		int quotient = p.getR(r1).getValue() / p.getR(r2).getValue();
		
		int reminder = p.getR(r1).getValue() % p.getR(r2).getValue();
		p.setR(r1, quotient);
		p.setR(r1 + 1, reminder);

	}
	
	public void cmp(int r1, int r2) {
		if (p.getR(r1).getValue() == p.getR(r2).getValue()) {
			p.setCc(0x08);
		} else
			p.setCc(0x00);
	}
	
	public void and(int r1, int r2) {
		p.setR(r1, p.getR(r1).getValue() & p.getR(r2).getValue());
	}
	
	public void orr(int r1, int r2) {
		p.setR(r1, p.getR(r1).getValue() | p.getR(r2).getValue());
	}

	public void not(int r) {
		p.setR(r, ~ p.getR(r).getValue());
	}

	public void alsh(int r, int count) {
		int val = p.getR(r).getValue();
		val <<= 16;
		val &= 0xffff0000;
		val <<= count;
		val >>=16;
		val &= 0x0000ffff;
		p.setR(r, val);
	}
	
	public void arsh(int r, int count) {
		int val = p.getR(r).getValue();
		val <<= 16;
		val &= 0xffff0000;
		val >>= count;
		val >>=16;
		val &= 0x0000ffff;
		p.setR(r, val);
	}
	
	public void llsh(int r, int count) {
		int val = p.getR(r).getValue();
		val <<= count;
		val &= 0x0000ffff;
		p.setR(r, val);
	}
	
	public void lrsh(int r, int count) {
		int val = p.getR(r).getValue();
		val >>>= count;
		val &= 0x0000ffff;
		p.setR(r, val);
	}
	
	public void lrrc(int r, int count) {
		int val = p.getR(r).getValue();
		int vall = val;
		vall <<= count;
		
		val = ((vall & 0xffff) | ((vall >> 16) & 0xffff));
		p.setR(r, val);
	}
	
	public void rrrc(int r, int count) {
		int val = p.getR(r).getValue();
		
		int valr = val << 16;
		valr >>>= count;
		
		val = ((valr & 0xffff) | ((valr >> 16) & 0xffff));
	}
}
