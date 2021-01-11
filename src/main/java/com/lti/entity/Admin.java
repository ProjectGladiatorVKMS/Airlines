package com.lti.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "admin_detail")
public class Admin {

	@Id
	@GeneratedValue
	@Column(name = "admin_id")
	private int adminId;

	@Column(name = "admin_username")
	private String adminUsername;

	@Column(name = "admin_password")
	private String adminPassword;

	@OneToMany(mappedBy = "admin")
	private Set<Flight> flightList;

	public Admin() {

	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getAdminUsername() {
		return adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminUsername=" + adminUsername + ", adminPassword=" + adminPassword
				+ ", flightList=" + flightList + "]";
	}

}
