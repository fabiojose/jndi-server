package com.javaf.jndi.example;

import java.io.Serializable;

/**
 * 
 * @author fabiojm - F�bio Jos� de Moraes
 *
 */
public interface IWallet extends Serializable {

	Double push(Double money);
	Double pop(Double money) throws NoMoreMoneyException;
	Double peek();
	
	
}
