package com.server.manage.service.implementation;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Random;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.server.manage.enumeration.Status;
import com.server.manage.model.Server;
import com.server.manage.repo.ServerRepo;
import com.server.manage.service.ServerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImpl implements ServerService{
	
	private final ServerRepo serverRepo;

	@Override
	public Server create(Server server) {
		log.info("Saving new server :{}",server.getName());
		server.setUrlImage(setServerImageUrl());
		return serverRepo.save(server);
	}
	
	@Override
	public Server ping(String ipAdress)throws IOException{
		log.info("Pinging Server IP :{}",ipAdress);
		Server server = serverRepo.findByIpAdress(ipAdress);
		InetAddress address = InetAddress.getByName(ipAdress);
		server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
		serverRepo.save(server);
		return server;
	}
 
	

	@Override
	public Collection<Server> list(int limit) {
		log.info("Fetching all servers");

		return serverRepo.findAll(PageRequest.of(0, limit)).toList();
	}
  
	@Override
	public Server get(Long id) {
		log.info("Fetching server by id {}",id);

		return serverRepo.findById(id).get();
	}

	@Override
	public Server update(Server server) {
		log.info("Updating server : {}",server.getName());
		return serverRepo.save(server);
	}

	@Override
	public Boolean delete(Long id) {
		log.info("Deleting server by ID : {}",id);
		serverRepo.deleteById(id);
		return true;
	}

	
	private String setServerImageUrl() {
		String[] imageNames= {"server1.png","server2.png","server3.png","server4.png"};
		return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/"+imageNames[new Random().nextInt(4)]).toUriString();
	}

}
