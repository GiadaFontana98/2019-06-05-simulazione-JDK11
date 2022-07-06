package it.polito.tdp.crimes.model;

public class Vicini implements Comparable <Vicini> {

	private int distretto;
	private double distanza;
	
	
	
	public Vicini(int distretto, double distanza) {
		super();
		this.distretto = distretto;
		this.distanza = distanza;
	}
	/**
	 * @return the distretto
	 */
	public int getDistretto() {
		return distretto;
	}
	/**
	 * @param distretto the distretto to set
	 */
	public void setDistretto(int distretto) {
		this.distretto = distretto;
	}
	/**
	 * @return the distanza
	 */
	public double getDistanza() {
		return distanza;
	}

	/**
	 * @param distanza the distanza to set
	 */
	public void setDistanza(double distanza) {
		this.distanza = distanza;
	}
	@Override
	public int compareTo(Vicini o) {
		// TODO Auto-generated method stub
		return (int)(this.distanza-o.distanza);
	}
	@Override
	public String toString() {
		return " \nVertice adiacente: " + distretto+  " distanza " + distanza ;
	}
	
	
}
