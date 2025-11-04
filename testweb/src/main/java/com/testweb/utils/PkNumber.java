package com.testweb.utils;

import java.util.Random;

public class PkNumber {

	public PkNumber() {
	}

    public static int getId() {
        Random random = new Random();
        int id = random.nextInt(100000); // 0 to 99999
        System.out.println("Generated PK ID: " + id);
        return id;
    }
}
