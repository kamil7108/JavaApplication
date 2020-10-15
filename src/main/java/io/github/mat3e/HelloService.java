package io.github.mat3e;

import java.util.Optional;

class HelloService {
    static String defaultString="world";
    String doGreeting(String name){
        return "Hello "+ Optional.ofNullable(name).orElse(defaultString);
    }
}
