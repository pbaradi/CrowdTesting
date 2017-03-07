package com.mtc.util;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.mtc.resource.ClientEndPoint;


@ApplicationPath("/")
public class MyApplication extends ResourceConfig {

    public MyApplication() {
        super(ClientEndPoint.class, MultiPartFeature.class);
    }
}
