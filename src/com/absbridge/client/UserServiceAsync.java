package com.absbridge.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
	public void addUser(String name, String password, AsyncCallback<Void> async);
	public void removeUser(String name, AsyncCallback<Void> async);
	public void getUsers(AsyncCallback<String[]> async);
	public void validateUser(String name, String password, AsyncCallback<Boolean> async);
}
