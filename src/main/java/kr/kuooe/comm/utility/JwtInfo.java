package kr.kuooe.comm.utility;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtInfo implements Serializable {
	private static final long serialVersionUID = -8590319775972379178L;
	
	public JwtInfo() {}
	
	private String relay;		// 중계사업자
	private String apiType;		// API종류
	private String requestIP;	// 요청자IP
	
	@Override
	public String toString() {
		return "JwtInfo [relay=" + relay + ", apiType=" + apiType + ", requestIP=" + requestIP + "]";
	}
}
