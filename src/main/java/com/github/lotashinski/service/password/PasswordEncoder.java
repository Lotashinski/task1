package com.github.lotashinski.service.password;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

final public class PasswordEncoder {

    private static final int iterations = 8;
    private static final int memory = 1 << 16;
    private static final int parallelism = 1;

    private static Argon2 argon2;


    public static String hash(char[] rawPassword) {
        Argon2 argon2 = getArgon2();
        return argon2.hash(iterations, memory, parallelism, rawPassword);
    }

    public static boolean verify(String hash, char[] rawPassword) {
        Argon2 argon2 = getArgon2();
        return argon2.verify(hash, rawPassword);
    }


    private static Argon2 getArgon2() {
        if (null == argon2) {
            argon2 = Argon2Factory.create();
        }
        return argon2;
    }

}
