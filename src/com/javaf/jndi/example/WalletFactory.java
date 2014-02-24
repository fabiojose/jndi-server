package com.javaf.jndi.example;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;

import org.apache.log4j.Logger;

/**
 * 
 * @author fabiojm - Fábio José de Moraes
 *
 */
public class WalletFactory implements ObjectFactory {
	
	private static final Logger LOGGER = Logger.getLogger(WalletFactory.class);

	@Override
	public Object getObjectInstance(final Object o, final Name name, final Context context, final Hashtable<?, ?> envmt) throws Exception {
		IWallet _result = null;
		
		LOGGER.debug("arguments:\n" +
				"object  = >" + o + "<\n" +
				"name    = >" + name + "<\n" +
				"context = >" + context + "<\n" +
				"envmt   = >" + envmt + "<");
		
		_result = new Wallet();
		LOGGER.trace("New wallet instance created: " + _result);
		
		return _result;
	}

}
