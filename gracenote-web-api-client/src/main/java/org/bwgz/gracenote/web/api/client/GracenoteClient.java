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

import com.google.api.client.http.HttpTransport;
import com.google.api.client.util.Preconditions;

public class GracenoteClient extends GracenoteHTTPClient {
	private String clientId;
	private String clientTag;
	private String userId;

	public GracenoteClient(Builder builder) {
		super(builder);
    	this.clientId = builder.clientId;
    	this.clientTag = builder.clientTag;
	}
	
	public GracenoteClient(HttpTransport transport, String clientId, String clientTag) {
		this(new Builder(transport, clientId, clientTag));
	}

	public static class Builder extends GracenoteHTTPClient.Builder {
		private String clientId;
		private String clientTag;
		
	    public Builder(HttpTransport transport, String clientId, String clientTag) {
	    	super(transport);
	    	this.clientId = Preconditions.checkNotNull(clientId);
	    	this.clientTag = Preconditions.checkNotNull(clientTag);
	    }

		public String getClientId() {
			return clientId;
		}

		public String getClientTag() {
			return clientTag;
		}
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientTag() {
		return clientTag;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
