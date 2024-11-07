package org.acme.week.first.day.first.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.week.first.day.first.service.interfaces.GreetingService;

@ApplicationScoped
public class EnglishGreetingService implements GreetingService {
    @Override
    public String greeting() {
        return "Hello! This is English Greeting Service!";
    }
}
