package br.com.unisagrado.Unisagrado.config.exception;

public class ErrorDTO {

	private int status_code;
	private String identification;
	private String reason;

	public ErrorDTO(int status_code, String identification, String reason) {
		this.status_code = status_code;
		this.identification = identification;
		this.reason = reason;
	}

	public ErrorDTO() {
	}

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
