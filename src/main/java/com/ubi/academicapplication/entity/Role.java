package com.ubi.academicapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String roleName;

	@Column(nullable = false)
	private String roleType;

	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private Set<User> users;

    public Role(String roleName, String roleType) {
    	this.roleName = roleName;
		this.roleType = roleType;
	}
}
