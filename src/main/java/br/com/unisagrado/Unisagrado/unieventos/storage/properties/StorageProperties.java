package br.com.unisagrado.Unisagrado.unieventos.storage.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StorageProperties {

	@Value("${unieventos.storage.path}")
	private String path;
	
	public StorageProperties() {
	}

	public StorageProperties(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
