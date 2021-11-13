package com.server.manage.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.server.manage.enumeration.Status;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Server {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	@Column(unique = true)
	@NotEmpty(message = "IP ADRESS CAN NOT BE EMPTY")
	private String ipAdress ; 
	private String name ; 
	private String 	memory ; 
	private String type ; 
	private String urlImage ; 
	private Status status ; 

}
