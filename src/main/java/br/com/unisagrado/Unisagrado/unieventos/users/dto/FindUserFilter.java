package br.com.unisagrado.Unisagrado.unieventos.users.dto;

public class FindUserFilter {

	private String name;
	private Boolean active;
	
	public FindUserFilter(Boolean active) {
		this.name = "";
		this.active = true;
	}
	
	public FindUserFilter(String name) {
		this.name = name;
		this.active = true;
	}
	
	public FindUserFilter() {
		this.name = "";
		this.active = true;
	}
	
	public FindUserFilter(String name, Boolean active) {
		this.name = name;
		this.active = active;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	
}
