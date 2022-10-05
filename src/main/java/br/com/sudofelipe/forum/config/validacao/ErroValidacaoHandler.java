package br.com.sudofelipe.forum.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErroValidacaoHandler {

	@Autowired
	private MessageSource messageSource;
	
	/*
	 * Tratamento de Exceções
	 * Criação de Interceptador
	 */
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroFormularioDto> handle(MethodArgumentNotValidException exception) {
		
		List<ErroFormularioDto> dto = new ArrayList<ErroFormularioDto>();
		
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
		
		//	Tratando erro de acordo com a localização
		fieldErrors.forEach(e -> {
			ErroFormularioDto erro = new ErroFormularioDto(e.getField(), messageSource.getMessage(e, LocaleContextHolder.getLocale()));
			dto.add(erro);
		});
		
		return dto;
	}
}
