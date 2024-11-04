package org.acme.week.first.day.first.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.week.first.day.first.service.interfaces.CustomQualifier;
import org.acme.week.first.day.first.service.interfaces.GreetingService;


@CustomQualifier("spanish")
@ApplicationScoped
public class SpanishGreetingService implements GreetingService {

    @Override
    public String greeting() {
        return "Hola! This is Spanish Greeting Service!";
    }
}
