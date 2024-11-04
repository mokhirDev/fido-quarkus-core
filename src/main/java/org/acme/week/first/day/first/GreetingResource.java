package org.acme.week.first.day.first;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.week.first.day.first.service.interfaces.CustomQualifier;
import org.acme.week.first.day.first.service.interfaces.GreetingService;

@Path("/hello")
public class GreetingResource {

    @Inject
    @CustomQualifier("english")
    GreetingService englishGreeting;

    @Inject
    @CustomQualifier("spanish")
    GreetingService spanishGreeting;

    @GET
    @Path("/greetings")
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public String sayGreeting() {
        return englishGreeting.greeting() + "\n" + spanishGreeting.greeting();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from Quarkus REST";
    }
}
