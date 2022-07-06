package it.polito.tdp.crimes.model;

import com.javadocmd.simplelatlng.LatLng;

public class Adiacenza {
	
	private int distretto;
	private LatLng centro;
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
	 * @return the centro
	 */
	public LatLng getCentro() {
		return centro;
	}
	/**
	 * @param centro the centro to set
	 */
	public void setCentro(LatLng centro) {
		this.centro = centro;
	}
	public Adiacenza(int distretto, LatLng centro) {
		super();
		this.distretto = distretto;
		this.centro = centro;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((centro == null) ? 0 : centro.hashCode());
		result = prime * result + distretto;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adiacenza other = (Adiacenza) obj;
		if (centro == null) {
			if (other.centro != null)
				return false;
		} else if (!centro.equals(other.centro))
			return false;
		if (distretto != other.distretto)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "vertice : "  +distretto;
	}

	
	

}
