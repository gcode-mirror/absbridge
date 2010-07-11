package com.absbridge.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {
	public void addUser(String name, String password) throws Exception;
	public void removeUser(String name) throws Exception;
	public String[] getUsers() throws Exception;
	public Boolean validateUser(String name, String password) throws Exception;
}
