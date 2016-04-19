package com.hercule.commun.navitia;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Antoine Lafuente on 14/07/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Link {
    
	private String href;
	private String type;
	private String rel;
	private boolean templated;

    public Link() {
    }

	public Link(String href, String type, String rel, boolean templated) {
		super();
		this.href = href;
		this.type = type;
		this.rel = rel;
		this.templated = templated;
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the rel
	 */
	public String getRel() {
		return rel;
	}

	/**
	 * @param rel the rel to set
	 */
	public void setRel(String rel) {
		this.rel = rel;
	}

	/**
	 * @return the templated
	 */
	public boolean isTemplated() {
		return templated;
	}

	/**
	 * @param templated the templated to set
	 */
	public void setTemplated(boolean templated) {
		this.templated = templated;
	}

    

    
}
