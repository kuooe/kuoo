package kr.kuooe.comm.vo;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MailVO extends DefaultVO {
	private static final long serialVersionUID = 7835500707081351904L;
	
	
	@ApiParam(value = "전송메일 host 정보", 		required = false)
	private String mailHost	= "";
	@ApiParam(value = "전송메일 port 정보", 		required = false)
	private String mailPort	= "";
	@ApiParam(value = "전송메일 ssl 정보", 			required = false)
	private String mailSsl	= "";
	@ApiParam(value = "전송메일 메일 주소정보", 		required = false)
	private String mailAddr	= "";
	@ApiParam(value = "전송메일 메일 계정 아이디", 	required = false)
	private String mailUser	= "";
	@ApiParam(value = "전송메일 메일 계정 비밀번호", 	required = false)
	private String mailPass	= "";
	
}