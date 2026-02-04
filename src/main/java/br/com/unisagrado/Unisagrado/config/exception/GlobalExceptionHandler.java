package br.com.unisagrado.Unisagrado.config.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import br.com.unisagrado.Unisagrado.unieventos.auth.exception.AccessTokenNotFound;
import br.com.unisagrado.Unisagrado.unieventos.auth.exception.RoleNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.auth.exception.TokenExpiredException;
import br.com.unisagrado.Unisagrado.unieventos.categoria.exceptions.CategoriaNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.courses.exception.CourseAlreadyExists;
import br.com.unisagrado.Unisagrado.unieventos.courses.exception.CursoNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.eventos.exception.EventNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.fotos.exception.FotoNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.fotos.exception.GenericException;
import br.com.unisagrado.Unisagrado.unieventos.storage.exception.SaveFileException;
import br.com.unisagrado.Unisagrado.unieventos.users.exception.IllegarUserIdException;
import br.com.unisagrado.Unisagrado.unieventos.users.exception.SendEmailException;
import br.com.unisagrado.Unisagrado.unieventos.users.exception.UserAlreadyInactive;
import br.com.unisagrado.Unisagrado.unieventos.users.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	

	@ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(HttpStatus.NOT_FOUND.value(), "user_not_found", "Usuário não encontrado."));
    }
    
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ErrorDTO> handleTokenExpiredException(TokenExpiredException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDTO(HttpStatus.UNAUTHORIZED.value(), "token_expired", "Token expirado."));
    }
    
    @ExceptionHandler(AccessTokenNotFound.class)
    public ResponseEntity<ErrorDTO> handleAccessTokenNotFound(AccessTokenNotFound e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorDTO(HttpStatus.UNAUTHORIZED.value(), "token_not_found", "Token não encontrado."));
    }
    
    @ExceptionHandler(IllegarUserIdException.class)
    public ResponseEntity<ErrorDTO> handleIllegarUserIdException(IllegarUserIdException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "invalid_user_id", "Id de usuário inválido."));
    }
    
    @ExceptionHandler(CursoNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleCursoNotFoundException(CursoNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "course_not_found", "Curso não encontrado."));
    }
    
    @ExceptionHandler(SendEmailException.class)
    public ResponseEntity<ErrorDTO> handleSendEmailException(SendEmailException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "send_email_error", "Não foi possível realizar o envio do email."));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception e) {
    	logger.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "internal_server_error", e.getMessage()));
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorDTO> handleBadCredentialsException(BadCredentialsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "bad_credentials", "Usuário ou senha incorretos."));
    }
    
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleRoleNotFoundException(RoleNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "role_not_found", "Role informada não foi encontrada."));
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDTO> handleAccessDeniedException(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorDTO(HttpStatus.FORBIDDEN.value(), "access_denied", "Você não tem permissão para acessar esse recurso."));
    }
    
    @ExceptionHandler(UserAlreadyInactive.class)
    public ResponseEntity<ErrorDTO> handleUserAlreadyInactive(UserAlreadyInactive e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "user_already_inactive", "Usuário já está inativo."));
    }
    
    @ExceptionHandler(CategoriaNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleCategoriaNotFoundException(CategoriaNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(HttpStatus.NOT_FOUND.value(), "categoria_not_found", "Não foi possível encontrar a categoria."));
    }
    
    @ExceptionHandler(FotoNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleFotoNotFoundException(FotoNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(HttpStatus.NOT_FOUND.value(), "foto_not_found", "Não foi possível encontrar a foto."));
    }
    
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleEventNotFound(EventNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(HttpStatus.NOT_FOUND.value(), "event_not_found", "Não foi possível encontrar o evento."));
    }
    
    @ExceptionHandler(SaveFileException.class)
    public ResponseEntity<ErrorDTO> handleSaveFileException(SaveFileException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "file_error", "Não foi possível salvar o seu arquivo."));
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "bad_request", e.getMessage()));
    }
    
    @ExceptionHandler(CourseAlreadyExists.class)
    public ResponseEntity<ErrorDTO> handleCourseAlreadyExists(CourseAlreadyExists e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "course_error", "O curso informado já existe."));
    }
    
    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorDTO> handleGenericException(GenericException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDTO(HttpStatus.BAD_REQUEST.value(), "generic_exception", "Ocorreu uma falha no seu processamento."));
    }
    
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNotFound(Exception ex) {
        ModelAndView mav = new ModelAndView("404");
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;
    }
}