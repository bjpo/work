package com.hrbsys.tools;

import java.util.UUID;

public class UUIDTools {
	public static String getUUID(){
		UUID u=UUID.randomUUID();
		return u.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(getUUID());
	}
}
