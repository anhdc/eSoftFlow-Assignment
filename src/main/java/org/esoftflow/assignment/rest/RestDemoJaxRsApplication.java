package org.esoftflow.assignment.rest;

import org.glassfish.jersey.message.filtering.EntityFilteringFeature;
import org.glassfish.jersey.server.ResourceConfig;


public class RestDemoJaxRsApplication extends ResourceConfig {

	public RestDemoJaxRsApplication() {
		
        packages("org.esoftflow.assignment.rest");
        

		register(EntityFilteringFeature.class);
		//EncodingFilter.enableFor(this, GZipEncoder.class);		
		
	}
}
