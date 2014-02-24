package com.javaf.jndi.server;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Reference;

import org.apache.log4j.Logger;

/**
 * 
 * @author fabiojm - Fábio José de Moraes
 *
 */
public class Lookup {
	
	private static final Logger LOGGER = Logger.getLogger(Lookup.class);
	
	public Lookup(){
		
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		final Hashtable _properties = new Hashtable();
		
		_properties.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
		_properties.put(Context.URL_PKG_PREFIXES,      "org.jnp.interfaces");
		_properties.put("java.naming.provider.url",    "jnp://10.10.10.200:5400");
		
		
		try{
			final Context _context = new InitialContext(_properties);
			
			LOGGER.debug(_context);
			LOGGER.debug(_context.lookup("java:comp"));
			LOGGER.debug(_context.lookup("java:jdbc"));
			
			final Object _wfactory = _context.lookup("money/WalletFactory");
			LOGGER.debug("class of money/WalletFactory: " + _wfactory.getClass());
			LOGGER.debug(_wfactory);
			
			if(_wfactory instanceof Reference){
				final Reference _reference = (Reference)_wfactory;
			
			}
			
			LOGGER.debug(_context.lookup("java:link"));
			
			LOGGER.debug(_context.lookup("java:external"));
			
		}catch(Exception _e){
			_e.printStackTrace();
		}
	}
}
