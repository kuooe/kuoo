package kr.kuooe.comm.utility;

public class IdUtil {

	public synchronized static String generateId() {
		String id = UUIDFactory.getInstance().newUUID().toString();
		return id;
	}
	
	public synchronized static String generateAId() {
		String id = UUIDFactory.getInstance().newUUID().toAString();
		return id;
	}
	
}
