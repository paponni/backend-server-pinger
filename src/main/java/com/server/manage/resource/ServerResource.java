package com.server.manage.resource;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.manage.enumeration.Status;
import com.server.manage.model.Response;import com.server.manage.model.Server;
import com.server.manage.service.implementation.ServerServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
	
	private final ServerServiceImpl serviceImpl;
	
	
	@GetMapping("/list")
	public ResponseEntity<Response> getServers() throws InterruptedException{
		
		HashMap<String, Collection<Server>> servers  = new HashMap<String, Collection<Server>>();
		servers.put("servers",serviceImpl.list(30) );
		TimeUnit.SECONDS.sleep(3);
		

		
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(servers)
				.message("Servers retrieved")
				.status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build()		
				);
												}
	
	@GetMapping("/ping/{ipAddress}")
	public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException{
		Server s = serviceImpl.ping(ipAddress);
		HashMap<String, Server> server  = new HashMap<String, Server>();
		server.put("server", s);
		
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(server)
				.message(s.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed")
				.status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build()		
				);
												}
	
	@PostMapping("/save")
	public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server) throws IOException{
		HashMap<String, Server> dataServer  = new HashMap<String, Server>();
		dataServer.put("servers", serviceImpl.create(server));
		
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(dataServer)
				.message("Server Created")
				.status(HttpStatus.CREATED)
				.statusCode(HttpStatus.CREATED.value())
				.build()		
				);
												}
	
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
		Server s = serviceImpl.get(id);
		HashMap<String, Server> server  = new HashMap<String, Server>();
		server.put("server", s);
		
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(server)
				.message("fet ching the server by ID")
				.status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build()		
				);
												}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
		Boolean s = serviceImpl.delete(id);
		HashMap<String, Boolean> server  = new HashMap<String, Boolean>();
		server.put("deleted", s);
		
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(server)
				.message("Server deleted")
				.status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build()		
				);
												}
		@GetMapping(path = "/image/{filename}",produces = "image/png" )
		public byte[] getServerImage(@PathVariable("filename") String filename) throws IOException {
			return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/Downloads/images/"+filename));
			
		}
	}

