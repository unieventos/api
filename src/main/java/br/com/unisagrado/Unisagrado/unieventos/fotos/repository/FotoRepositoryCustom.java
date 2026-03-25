package br.com.unisagrado.Unisagrado.unieventos.fotos.repository;

import java.util.List;

import br.com.unisagrado.Unisagrado.unieventos.fotos.dto.CreateFotoRecord;
import br.com.unisagrado.Unisagrado.unieventos.fotos.model.Foto;
import br.com.unisagrado.Unisagrado.unieventos.model.ContemFoto;

public interface FotoRepositoryCustom {

	<T> List<Foto> findFotosByTargetId(String targetId, Class<T> type);
	void createNewFoto(CreateFotoRecord record, String newFilePath, Class<? extends ContemFoto> tipo);
	List<Foto> findFotosByEventoId(String targetId);
	List<Foto> findFotosByComentarioId(String targetId);
}
