package br.com.bethpapp.controller.exeption;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.bethpapp.dominio.service.exeption.EntidadeEmUsoExeption;
import br.com.bethpapp.dominio.service.exeption.NegocioException;
import br.com.bethpapp.dominio.service.exeption.RegistroNaoEncontrado;

@RestControllerAdvice
public class ControllerExeption extends ResponseEntityExceptionHandler {
	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		var campos = new ArrayList<Problema.Campo>();

		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

			campos.add(new Problema.Campo(nome, mensagem));
		}

		var problema = Problema.builder().status(status.value())
				.titulo("Um ou mais campos estão inválidos. " + "Faça o preenchimento correto e tente novamente")
				.status(status.value()).dataHora(OffsetDateTime.now()).campos(campos).build();

		return handleExceptionInternal(ex, problema, headers, status, request);
	}

	@ExceptionHandler({NegocioException.class})
	public ResponseEntity<Object> IlegalExeption(NegocioException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		var problema = Problema.builder().status(status.value()).titulo(ex.getMessage())
				.dataHora(OffsetDateTime.now()).build();
         System.out.println(problema.getTitulo());
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler({ EntidadeEmUsoExeption.class })
	public ResponseEntity<Object> ViolacaoIntegriadade(EntidadeEmUsoExeption ex,
			WebRequest request) {
		var status = HttpStatus.CONFLICT;
		var problema = Problema.builder().
				status(status.value()).
				titulo(ex.getMessage())
				.dataHora(OffsetDateTime.now()).build();;

		
	

		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(RegistroNaoEncontrado.class)
	public ResponseEntity<Object> EntidadeNaoEncontrada(RegistroNaoEncontrado ex, WebRequest request) {
		var status = HttpStatus.NOT_FOUND;

		var problema = Problema.builder().
				status(status.value()).
				titulo(ex.getMessage())
				.dataHora(OffsetDateTime.now()).build();

		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<Object> DeleteEmpenty(NoSuchElementException ex,
			WebRequest request) {
		var status = HttpStatus.NOT_FOUND;

		var problema = Problema.builder().
				status(status.value()).
				titulo(ex.getMessage())
				.dataHora(OffsetDateTime.now()).build();

		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
	
//	@ExceptionHandler({ EntityNotFoundException.class })
//	public ModelAndView resolveException(HttpServletRequest request, Exception ex) {
//	    request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.BAD_REQUEST.value());
//	    request.setAttribute(RequestDispatcher.ERROR_MESSAGE,
//	            "This will override the error message configured by Boot");
//	    ModelAndView mav = new ModelAndView();
//	    mav.setViewName("/error");
//	    return mav;
//	}
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ResponseEntity<Object> chavecompoostaExeption(InvalidDataAccessApiUsageException ex, WebRequest request){
		var status = HttpStatus.BAD_REQUEST;
		var problema = Problema.builder().
				status(status.value()).
				titulo(ex.getMessage())
				.dataHora(OffsetDateTime.now()).build();
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}
}
