package kr.kuooe.comm.utility;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.Serializable;

public class UUID implements Serializable {

	/** The most significant 64 bits. **/
	private long mostSig;
	
	/** The least significant 64 bits. **/
	private long leastSig;
	
	/** Simple constructor. **/
	public UUID(long mostSig, long leastSig) {
		this.mostSig = mostSig;
		this.leastSig = leastSig;
	}
	
	/** Reads in 16 bytes in standard network byte order. **/
	public UUID(DataInput in) throws IOException {
		this.mostSig = in.readLong();
		this.leastSig = in.readLong();
	}
	
	/** Returns the most significant 64 bits of the service ID. */
	public long getMostSignificantBits() {
		return mostSig;
	}
	
	/** Returns the least significant 64 bits of the service ID. */
	public long getLeastSignificantBits() {
		return leastSig;
	}
	
	/** Writes out 16 bytes in standard network byte order. */
	public void writeBytes(DataOutput out) throws IOException {
		out.writeLong(mostSig);
		out.writeLong(leastSig);
	}
	
	public int hashCode() {
		return (int) ((mostSig >> 32) ^ mostSig ^ (leastSig >> 32) ^ leastSig);
	}
	
	/** UU IDs are equal if they represent the same 128-bit value. **/
	public boolean equals(Object obj) {
		if (!(obj instanceof UUID))
			return false;
		UUID sid = (UUID) obj;
		return (mostSig == sid.mostSig && leastSig == sid.leastSig);
	}
	
	/**
	 * Returns a 36-character string of six fields separated by hyphens, with
	 * each field represented in lowercase hexadecimal with the same number of
	 * digits as in the field. The order of fields is: time_low, time_mid,
	 * version and time_hi treated as a single field, variant and clock_seq
	 * treated as a single field, and node.
	 */
	public String toString() {
		return (digits(mostSig >> 32, 8) + "-" + digits(mostSig >> 16, 4) + "-" + digits(mostSig, 4) + "-" + digits(leastSig >> 48, 4) + "-" + digits(leastSig, 12));
	}
	
	public String toAString() {
		return (digits(mostSig >> 32, 8) + digits(mostSig >> 16, 4) + digits(mostSig, 4) + digits(leastSig >> 48, 4) + digits(leastSig, 12)).toUpperCase();
	}
	
	/** Returns val represented by the specified number of hex digits. */
	private static String digits(long val, int digits) {
		long hi = 1L << (digits * 4);
		return Long.toHexString(hi | (val & (hi - 1))).substring(1);
	}
	
}
