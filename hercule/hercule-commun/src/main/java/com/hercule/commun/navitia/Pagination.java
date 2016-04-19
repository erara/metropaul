package com.hercule.commun.navitia;

public class Pagination {

	private int start_page;
	private int items_on_page;
	private int items_per_page;
	private int total_result;
	
	
	/**
	 * 
	 */
	public Pagination() {
		super();
	}
	
	
	/**
	 * @return the start_page
	 */
	public int getStart_page() {
		return start_page;
	}
	/**
	 * @param start_page the start_page to set
	 */
	public void setStart_page(int start_page) {
		this.start_page = start_page;
	}
	/**
	 * @return the items_on_page
	 */
	public int getItems_on_page() {
		return items_on_page;
	}
	/**
	 * @param items_on_page the items_on_page to set
	 */
	public void setItems_on_page(int items_on_page) {
		this.items_on_page = items_on_page;
	}
	/**
	 * @return the items_per_page
	 */
	public int getItems_per_page() {
		return items_per_page;
	}
	/**
	 * @param items_per_page the items_per_page to set
	 */
	public void setItems_per_page(int items_per_page) {
		this.items_per_page = items_per_page;
	}
	/**
	 * @return the total_result
	 */
	public int getTotal_result() {
		return total_result;
	}
	/**
	 * @param total_result the total_result to set
	 */
	public void setTotal_result(int total_result) {
		this.total_result = total_result;
	}
	
	
}
