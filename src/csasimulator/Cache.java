package csasimulator;

import java.util.LinkedHashMap;

/**
 * @author phani
 *
 * Simple implementation of Fully-associative Write-through Cache
 * with LRU replacement policy
 * 
 */
public class Cache extends LinkedHashMap<Integer, Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int CACHE_SIZE = 32; //both for instructions and data cache
	Processor p;
	Memory m;

	public Cache(Processor p, Memory m) {
		
		super(CACHE_SIZE, 0.75f, true);
		this.p = p;
		this.m = m;
	}

	@Override
	protected boolean removeEldestEntry(
			java.util.Map.Entry<Integer, Integer> eldest) {

		// remove the oldest element when size limit is reached
		return size() > CACHE_SIZE;
	}

	public Integer get(Integer key)
	{
		if (containsKey(key)) {
			System.out.println("Read Hit!");
			System.out.println("[" + key + "]:" + super.get(key) );
		} else {
			System.out.println("Read Miss!");
			super.put(key, m.getData(key.intValue()));
		}
		
		Object value = super.get(key);
		return (Integer)value;
	}
	
	public Integer put(Integer key, Integer val) {
		
		if (containsKey(key)) {
			System.out.println("Write Hit!");
		} else {
			System.out.println("Write Miss!");	
		}
		
		super.put(key, val);
		m.setData(key.intValue(), val.intValue()); //write-through
		Object value = super.get(key);
		return (Integer)value;
	}
	
	public void reset() {
		super.clear();
	}
}
