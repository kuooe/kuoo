package kr.kuooe.comm.utility;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import kr.kuooe.comm.vo.MailVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SendMailUtil {

	public static String sendMail(String recvAddr, String recvSubject, String recvContent, MailVO mailVO) {
		log.debug("sendMail() =====> Start");
		
		String sendMsg	= "";
		
		// SMTP 서버 정보 설정
		Properties props	= System.getProperties();
		props.put("mail.smtp.host",			mailVO.getMailHost());
		props.put("mail.smtp.port",			mailVO.getMailPort());
		props.put("mail.smtp.auth",			"false");
		props.put("mail.smtp.ssl.enable",	mailVO.getMailSsl());
		props.put("mail.smtp.ssl.trust",	mailVO.getMailHost());
		
		//Session 생성
		Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(mailVO.getMailUser(), mailVO.getMailPass());
			}
		});
		mailSession.setDebug(true); //for debug
		
		try {
			MimeMessage mimeMessage = new MimeMessage(mailSession);
			mimeMessage.setFrom(new InternetAddress(mailVO.getMailAddr()));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recvAddr));
			mimeMessage.setSubject(recvSubject, "utf-8");					// 제목셋팅
			mimeMessage.setContent(recvContent,"text/html; charset=utf-8");	// 내용셋팅
			
			Transport t	= mailSession.getTransport("smtp");
			t.connect(mailVO.getMailUser(), mailVO.getMailPass());
			t.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			t.close();
			
			sendMsg	= "success";
		} catch (AddressException e) {
			e.printStackTrace();
			sendMsg	= "fail";
		} catch (MessagingException e) {
			e.printStackTrace();
			sendMsg	= "fail";
		}
		log.debug("sendMail() =====> End");
		
		return sendMsg;
	}
	
	public static String getCertMailForm() {
		String mailContent	= "";
		mailContent	+= "<html xmlns='http://www.w3.org/1999/xhtml'>";
		mailContent	+= "<head>";
		mailContent	+= "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />";
		mailContent	+= "<meta name='viewport' content='width=device-width, initial-scale=1, minimum-scale=1,maximum-scale=1,user-scalable=no' />";
		mailContent	+= "<title>[CERT_SUBJECT]</title>";
		mailContent	+= "<style>";
		mailContent	+= "@charset 'utf-8';";
		mailContent	+= "body {font-family:Malgun Gothic,'', dotum, '', sans-serif;font-size:12px; overflow:none; height:100%;  line-height:1.5; color:#333; letter-spacing:1px;}";
		mailContent	+= "</style>";
		mailContent	+= "</head>";
		mailContent	+= "<body>";
		mailContent	+= "<div style='padding:0.5em 0; font-size:1.2em;'>요청하신 인증번호는 다음과 같습니다.</div>";
		mailContent	+= "<div style='padding:0.5em 0; font-size:1.2em;'>[CERT_CODE]</div>";
		mailContent	+= "</body>";
		mailContent	+= "</html>";
		
		return mailContent;
	}
}
