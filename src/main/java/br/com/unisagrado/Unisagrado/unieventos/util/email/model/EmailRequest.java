package br.com.unisagrado.Unisagrado.unieventos.util.email.model;

import java.util.List;

public class EmailRequest {

	private Sender sender;
	private List<Recipient> to;
	private String subject;
	private String htmlContent;
	
	public EmailRequest() {
	}

	public EmailRequest(Sender sender, List<Recipient> to, String subject, String htmlContent) {
		this.sender = sender;
		this.to = to;
		this.subject = subject;
		this.htmlContent = htmlContent;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public List<Recipient> getTo() {
		return to;
	}

	public void setTo(List<Recipient> to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

}
