package com.intutx.ecommshopservice.util;

import java.util.Random;
import java.util.UUID;

public class IdGenerator {

	public static Long getRandomId() {

		return System.currentTimeMillis() + new Random().nextLong();

	}

	public String getUUID() {

		return UUID.randomUUID().toString();

	}
}
