package com.server.manage.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.manage.model.Server;

public interface ServerRepo extends JpaRepository<Server, Long>{

	public Server findByIpAdress(String ipAdress);
	
}
