package com.ubi.academicapplication.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ubi.academicapplication.model.Authority;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="user_details")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column
	private Boolean isEnabled;

	@ManyToOne
	@JoinColumn(name="roleId",referencedColumnName = "id", nullable=true)
	private Role role;

	public User(String username, String password,Boolean activeStatus, Role role) {
		this.username = username;
		this.password = password;
		this.isEnabled = activeStatus;
		this.role = role;
	}

	// UserDetails Methods
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> roles = new HashSet<>();
		if(this.role != null) roles.add(new Authority(role.getRoleType()));
		return roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}