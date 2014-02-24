package com.javaf.jndi.server;

import java.net.InetAddress;
import java.util.Hashtable;
import java.util.concurrent.Callable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.LinkRef;
import javax.naming.NameParser;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.StringRefAddr;

import org.apache.log4j.Logger;
import org.jnp.server.Main;
import org.jnp.server.NamingBeanImpl;

public class StandaloneJNDIServer implements Callable<Object> {
	
	private static final Logger LOGGER = Logger.getLogger(StandaloneJNDIServer.class);

	public Object call() throws Exception {

		setup();
		
		return null;
	}
 
	private void setup() throws Exception {
		
		//configure the initial factory
		//**in John´s code we did not have this**
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		System.setProperty(Context.OBJECT_FACTORIES,        "com.javaf.jndi.example.WalletFactory");
		System.setProperty(Context.URL_PKG_PREFIXES,        "org.jnp.interfaces");
		
		//start the naming info bean
		final NamingBeanImpl _naming = new NamingBeanImpl();
		_naming.start();
		
		//start the jnp server
		final Main _server = new Main();
		_server.setNamingInfo(_naming);
		_server.setPort(5400);
		_server.setBindAddress(InetAddress.getLocalHost().getHostName());
		_server.start();
		
		bind();
		
	}
	
	private void bind() throws NamingException {
		
		//configure the environment for initial context
		final Hashtable<String, String> _properties = new Hashtable<String, String>();
		_properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
		_properties.put(Context.PROVIDER_URL,            "jnp://10.10.10.200:5400");
		
		//bind a name
		final Context _context = new InitialContext(_properties);
		_context.bind("jdbc", "myJDBC");
		
		final Context _money     = _context.createSubcontext("money");
		
		//binding the wallet object factory
		final Reference _factory = new Reference("com.javaf.jndi.example.Wallet", "com.javaf.jndi.example.WalletFactory", null);
		_money.bind("WalletFactory", _factory);
		
		final NameParser _parser = _money.getNameParser("WalletFactory");
		LOGGER.debug(_parser);
		
		//binding link reference
		_context.bind("link", new LinkRef("jdbc"));
		
		final StringRefAddr _url = new StringRefAddr("URL", "file://D:/CADU");
		final Reference _urlref  = new Reference("javax.naming.Context", _url);
		_context.bind("external", _urlref);
		
	}
	
	public static void main(String...args){
		
		try{
			
			new StandaloneJNDIServer().call();
			
		}catch(Exception _e){
			_e.printStackTrace();
		}
		
	}
}
