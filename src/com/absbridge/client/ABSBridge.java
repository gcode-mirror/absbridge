package com.absbridge.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ABSBridge implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final TextBox userName = new TextBox();
		final PasswordTextBox userPassword = new PasswordTextBox();
		final Button signIn = new Button("sign in");
		final Button createLogin = new Button("create");
		
		signIn.setWidth("70px");
		createLogin.setWidth("70px");
		HorizontalPanel actions = new HorizontalPanel();
		actions.add(signIn);
		actions.add(createLogin);
		actions.setSpacing(5);
		
		createLogin.addClickHandler(new ClickHandler() {
	          public void onClick(ClickEvent event) {
	        	  UserServiceAsync userService = GWT.create(UserService.class);
	        	  userService.addUser(userName.getText(), userPassword.getText(), new AsyncCallback<Void>() {
	        		  public void onFailure(Throwable error) {
	        			  Window.alert("error: " + error.getMessage());
	        		  }
	        		  public void onSuccess(Void ignore) {
	        			  Window.alert("stored");
	        		  }
	        	  });
	          }
	    });
		
		signIn.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				UserServiceAsync userService = GWT.create(UserService.class);
				userService.validateUser(userName.getText(), userPassword.getText(), new AsyncCallback<Boolean> () {
					public void onFailure(Throwable error) {
						Window.alert("error: " + error.getMessage());
					}
					public void onSuccess(Boolean result) {
						if (result.booleanValue()) {
							Window.alert("signed in");
						} else {
							Window.alert("Could not sign in");
						}
					}
				});
			}
		});
		
		VerticalPanel loginForm = new VerticalPanel();
		
		userName.setWidth("150px");
		userPassword.setWidth("150px");
		
		loginForm.add(userName);
		loginForm.add(userPassword);
		loginForm.add(actions);
		
		RootPanel.get("content").add(loginForm);
	}
}
