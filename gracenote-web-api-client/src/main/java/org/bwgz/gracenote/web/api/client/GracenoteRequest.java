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

import java.io.IOException;

import com.google.api.client.http.EmptyContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.util.Preconditions;

public class GracenoteRequest<T> {
	/*
	 * Requests to the Web API should be sent to the following URL:
	 * 		https://XXXXXXX.web.cddbp.net/webapi/xml/1.0/
	 * Where XXXXXXX is replaced with the digits of your Client ID that precede the hyphen.
	 */
	public static final String URL_FORMAT = "https://%s.web.cddbp.net/webapi/xml/1.0/";

	private final Gracenote gracenote;
	private Class<T> responseClass;
	private final HttpContent httpContent;

	private HttpHeaders lastHeaders;
	private int lastStatusCode = -1;
	private String lastStatusMessage;

	protected GracenoteRequest(Gracenote gracenote, HttpContent httpContent, Class<T> responseClass) {
		this.gracenote = Preconditions.checkNotNull(gracenote);
		this.responseClass = Preconditions.checkNotNull(responseClass);
		this.httpContent = httpContent;
	}

	public Gracenote getGracenote() {
		return gracenote;
	}

	public GenericUrl buildHttpRequestUrl() {
		return new GenericUrl(String.format(URL_FORMAT, getGracenote().getClientId()));
	}
	
	private HttpRequest buildHttpRequest() throws IOException {
		final HttpRequest httpRequest = getGracenote().getRequestFactory().buildRequest(HttpMethods.POST, buildHttpRequestUrl(), httpContent);
		httpRequest.setParser(getGracenote().getObjectParser());
		
		if (httpContent == null) {
			httpRequest.setContent(new EmptyContent());
		}
		
		return httpRequest;
	}

	private HttpResponse executeUnparsed() throws IOException {
		HttpResponse response = buildHttpRequest().execute();

		setLastHeaders(response.getHeaders());
		setLastStatusCode(response.getStatusCode());
		setLastStatusMessage(response.getStatusMessage());
		    
		return response;
	}

	public T execute() throws IOException {
		return executeUnparsed().parseAs(responseClass);
	}

	public HttpHeaders getLastHeaders() {
		return lastHeaders;
	}

	public void setLastHeaders(HttpHeaders lastHeaders) {
		this.lastHeaders = lastHeaders;
	}

	public int getLastStatusCode() {
		return lastStatusCode;
	}

	public void setLastStatusCode(int lastStatusCode) {
		this.lastStatusCode = lastStatusCode;
	}

	public String getLastStatusMessage() {
		return lastStatusMessage;
	}

	public void setLastStatusMessage(String lastStatusMessage) {
		this.lastStatusMessage = lastStatusMessage;
	}
}
