package com.javaf.jndi.example;

/**
 * 
 * @author fabiojm - Fábio José de Moraes
 *
 */
public class Wallet implements IWallet {
	private static final long serialVersionUID = -7579775036873753208L;
	
	private double amount;

	@Override
	public Double pop(Double money) throws NoMoreMoneyException  {
		if(null== money){
			throw new NullPointerException("arg1 is null!");
		}
		
		if(Double.compare(money, 0.0D) <= 0){
			throw new IllegalArgumentException("invalid value: " + money);
		}
		
		if(Double.compare(amount - money, 0.0D) < 0){
			throw new NoMoreMoneyException("no balance to pop: " + money);
		}
		
		return (amount -= money);
	}

	@Override
	public Double push(Double money) {
		if(null== money){
			throw new NullPointerException("arg1 is null!");
		}
		
		if(Double.compare(money, 0.0D) <= 0){
			throw new IllegalArgumentException("invalid value: " + money);
		}

		return (amount += money);
	}

	@Override
	public Double peek() {
		return amount;
	}

}
