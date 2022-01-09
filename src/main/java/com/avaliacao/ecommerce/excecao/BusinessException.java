package com.avaliacao.ecommerce.excecao;

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public BusinessException(String messagem) {
		super(messagem);
	}
}
