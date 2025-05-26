package com.humanbooster;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class ApiApplication extends ResourceConfig{
    public ApiApplication(){
        packages("com.humanbooster", "com.humanbooster.model", "com.humanbooster.dao");
        register(JacksonFeature.class);
    }
}
