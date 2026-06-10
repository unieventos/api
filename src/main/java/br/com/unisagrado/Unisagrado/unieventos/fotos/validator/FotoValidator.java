package br.com.unisagrado.Unisagrado.unieventos.fotos.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import br.com.unisagrado.Unisagrado.unieventos.fotos.exception.InvalidFotoFormatException;

@Component
public class FotoValidator {

    public void validateIsPng(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return;
        }

        String contentType = file.getContentType();
        if (contentType == null || !contentType.equalsIgnoreCase("image/png")) {
            throw new InvalidFotoFormatException("Apenas fotos no formato PNG são permitidas. Recebido: " + contentType);
        }

        String fileName = file.getOriginalFilename();
        if (fileName != null && !fileName.toLowerCase().endsWith(".png")) {
            throw new InvalidFotoFormatException("A extensão do arquivo deve ser .png. Arquivo: " + fileName);
        }
    }

    public void validateIsPng(Iterable<MultipartFile> files) {
        if (files != null) {
            files.forEach(this::validateIsPng);
        }
    }
}
