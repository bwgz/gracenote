/*
 * Copyright 2013 bwgz.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.bwgz.gracenote.web.api.client;

import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Preconditions;

public class GracenoteHTTPClient {
	private final HttpRequestFactory httpRequestFactory;

	protected GracenoteHTTPClient(Builder builder) {
	    httpRequestFactory = builder.getTransport().createRequestFactory();
	}
	
	public HttpRequestFactory getRequestFactory() {
		return httpRequestFactory;
	}

	protected static class Builder {
	    private HttpTransport transport;
		
	    public Builder(HttpTransport transport) {
	    	this.transport = Preconditions.checkNotNull(transport);
	    }
	    
	    public HttpTransport getTransport() {
		      return transport;
	    }
	}
}
