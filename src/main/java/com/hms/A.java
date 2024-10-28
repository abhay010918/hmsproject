package com.hms;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class A {
    public static void main(String[] args) {
        String encode = BCrypt.hashpw("admin", BCrypt.gensalt(5));
        System.out.println(encode);
    }
}

