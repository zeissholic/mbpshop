package me.ijpark.shop.model;

import java.time.LocalDate;


public class Cart {

	private Long id;
	private String itemnm;
	private String username;
	private float price;
	private LocalDate targetDate;
	private boolean status;
	private String ipaddr;
	
	protected Cart() {
		
	}

	public Cart(long id, String itemnm, String username, float price, LocalDate targetDate, boolean isDone, String ipaddr) {
		super();
		this.id = id;
		this.itemnm = itemnm;
		this.username = username;
		this.price = price;
		this.targetDate = targetDate;
		this.status = isDone;
		this.ipaddr = ipaddr;
	}
	
	public Cart(long id, String itemnm, String username, float price, LocalDate targetDate, boolean isDone) {
		super();
		this.id = id;
		this.itemnm = itemnm;
		this.username = username;
		this.price = price;
		this.targetDate = targetDate;
		this.status = isDone;
	}

	public Cart(String itemnm, String username, float price, LocalDate targetDate, boolean isDone, String ipaddr) {
		super();
		this.itemnm = itemnm;
		this.username = username;
		this.price = price;
		this.targetDate = targetDate;
		this.status = isDone;
		this.ipaddr = ipaddr;
	}
	
	public Cart(String itemnm, String username, float price, LocalDate targetDate, boolean isDone) {
		super();
		this.itemnm = itemnm;
		this.username = username;
		this.price = price;
		this.targetDate = targetDate;
		this.status = isDone;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemnm() {
		return itemnm;
	}
	
	public void setItemnm(String itemnm) {
		this.itemnm = itemnm;
	}
	
	public String getIpaddr() {
		return ipaddr;
	}
	
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Cart other = (Cart) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
