package me.frank.spring.boot.demo.service;

public interface IJwtService {

    String genTokenFor(String username);

    String getSubjectFrom(String token);
}
