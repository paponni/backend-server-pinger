package com.server.manage.service;

import java.io.IOException;
import java.util.Collection;

import com.server.manage.model.Server;

public interface ServerService {

	Server create(Server server);
	Server ping(String ipAdress) throws IOException;
	Collection<Server> list(int limit);
	Server get(Long id);
	Server update(Server server);
	Boolean delete(Long id);
}
