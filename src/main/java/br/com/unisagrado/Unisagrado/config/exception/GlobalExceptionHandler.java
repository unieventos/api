package br.com.unisagrado.Unisagrado.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import br.com.unisagrado.Unisagrado.unieventos.auth.exception.AccessTokenNotFound;
import br.com.unisagrado.Unisagrado.unieventos.auth.exception.TokenExpiredException;
import br.com.unisagrado.Unisagrado.unieventos.courses.exception.CursoNotFoundException;
import br.com.unisagrado.Unisagrado.unieventos.users.exception.IllegarUserIdException;
import br.com.unisagrado.Unisagrado.unieventos.users.exception.UserNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
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
    
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleNotFound(Exception ex) {
        ModelAndView mav = new ModelAndView("404");
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;
    }
}