package com.absbridge.server;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.absbridge.client.UserService;
import com.absbridge.client.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	@Override
	public void addUser(String name, String password) throws Exception {
		// TODO Auto-generated method stub
	    PersistenceManager pm = PMF.getPersistenceManager();
	    try {
	    	User user = new User();
	    	user.setName(name);
	    	user.setPassword(password);
	    	pm.makePersistent(user);
	    } finally {
	    	pm.close();
	    }
	}

	@Override
	public String[] getUsers() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUser(String user) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean validateUser(String name, String password) throws Exception {
		// TODO Auto-generated method stub
		PersistenceManager pm = PMF.getPersistenceManager();
		Query query = pm.newQuery(User.class);
		
		try {
	        List<User> results = (List<User>) query.execute();
	        if (results.iterator().hasNext()) {
	            for (User u : results) {
	            	if (u.getName().equals(name)) {
		            	if (u.getPassword().equals(password)) {
		            		return (true);
		            	}
		            }
	            }
	        } else {
	            return (false);
	        }
	    } finally {
	        query.closeAll();
	        pm.close();
	    }
		
		return (false);
	}

}
