package com.example.foodcartrevised;

import java.util.UUID;

public class Unique {
	public static String getUnique()
	{
		String uuid = UUID.randomUUID().toString();
		//System.out.println("uuid = " + uuid);
		String unique=uuid;
		return unique;
	}

}
