package com.example.tracker.security;

public enum UserPermission {

	BASIC_USER_READ("basic_user:read"), BASIC_USER_WRITE("basic_user:write");

	private final String permission;

	UserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}

}
