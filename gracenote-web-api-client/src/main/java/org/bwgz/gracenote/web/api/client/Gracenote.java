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
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bwgz.gracenote.web.api.model.Authenticate;
import org.bwgz.gracenote.web.api.model.Option;
import org.bwgz.gracenote.web.api.model.Queries;
import org.bwgz.gracenote.web.api.model.Query;
import org.bwgz.gracenote.web.api.model.Response;
import org.bwgz.gracenote.web.api.model.Responses;
import org.bwgz.gracenote.web.api.model.TVChannel;
import org.bwgz.gracenote.web.api.model.TVGrid;
import org.bwgz.gracenote.web.api.model.TVProgram;
import org.bwgz.gracenote.web.api.model.TVProvider;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.xml.XmlHttpContent;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.xml.XmlNamespaceDictionary;
import com.google.api.client.xml.XmlObjectParser;

public class Gracenote extends GracenoteClient {
	private static final Logger LOGGER = Logger.getLogger(Gracenote.class.getName());

	private static final XmlNamespaceDictionary DICTIONARY = new XmlNamespaceDictionary().set("", "");
	private static final XmlObjectParser PARSER = new XmlObjectParser(DICTIONARY);

	public Gracenote(HttpTransport transport, String clientId, String clientTag) {
		this(new Builder(transport, clientId, clientTag));
	}

	public Gracenote(Builder builder) {
		super(builder);
	}

	public static final class Builder extends GracenoteClient.Builder {
		public Builder(HttpTransport transport, String clientId, String clientTag) {
			super(transport, clientId, clientTag);
		}

		public Gracenote build() {
			return new Gracenote(this);
		}
	}

	public ObjectParser getObjectParser() {
		return PARSER;
	}
	
	/*
	 * Registering a Device
	 * 
	 * To obtain a User ID for a device, the client application must submit a REGISTER query. 
	 * This query requires a valid Client ID. The client application needs to register a device only once.
	 * If registration succeeds, Gracenote returns a successful registration response that contains the User ID. 
	 */
	public void register() throws IOException {
		Query query = new Query(Query.CMD_REGISTER);
		query.setClient(getClientId());
		
		Queries queries = new Queries(query);
    	
		XmlHttpContent content = new XmlHttpContent(DICTIONARY, Queries.ELEMENT_NAME, queries);
    	//System.out.println(DICTIONARY.toStringOf(Queries.ELEMENT_NAME, queries));
    	
		GracenoteRequest<Responses> request = new GracenoteRequest<Responses>(this, content, Responses.class);
		Responses responses = request.execute();
    	//System.out.println(responses);
   	
    	if (responses.getResponse().getStatus().equals(Response.STATUS_ERROR)) {
    		LOGGER.log(Level.SEVERE, responses.getMessage());
    	}
    	else {
    		this.setUserId(responses.getResponse().getUser());
    	}
	}

	public TVProvider[] tvProviderLookup(String country, String postalCode) throws IOException {
		TVProvider[] providers = null;
		
		Authenticate authenticate = new Authenticate(getClientId(), getClientTag(), this.getUserId());
		
		Query query = new Query(Query.CMD_TVPROVIDER_LOOKUP);
		query.setPostalCode(postalCode);
		
		Queries queries = new Queries(authenticate, query);
		queries.setCountry(country);
		
		XmlHttpContent content = new XmlHttpContent(DICTIONARY, Queries.ELEMENT_NAME, queries);
    	//System.out.println(DICTIONARY.toStringOf(Queries.ELEMENT_NAME, queries));

		GracenoteRequest<Responses> request = new GracenoteRequest<Responses>(this, content, Responses.class);
		Responses responses = request.execute();
    	//System.out.println(responses);
   	
    	if (responses.getResponse().getStatus().equals(Response.STATUS_ERROR)) {
    		LOGGER.log(Level.SEVERE, responses.getMessage());
    	}
    	else {
    		providers = responses.getResponse().getTvProvider();
    	}

    	return providers;
	}

	public TVChannel[] tvChannelLookup(String gnId) throws IOException {
		TVChannel[] channels = null;
		
		Authenticate authenticate = new Authenticate(getClientId(), getClientTag(), this.getUserId());
		
		Option option = new Option(Option.PARAMETER_SELECT_EXTENDED, Option.VALUE_IMAGE);
		
		Query query = new Query(Query.CMD_TVCHANNEL_LOOKUP);
		query.setMode(Query.MODE_TVPROVIDER);
		query.setOption(option);
		query.setGnId(gnId);
		
		Queries queries = new Queries(authenticate, query);
		
		XmlHttpContent content = new XmlHttpContent(DICTIONARY, Queries.ELEMENT_NAME, queries);
    	//System.out.println(DICTIONARY.toStringOf(Queries.ELEMENT_NAME, queries));

		GracenoteRequest<Responses> request = new GracenoteRequest<Responses>(this, content, Responses.class);
		Responses responses = request.execute();
    	//System.out.println(responses);
   	
    	if (responses.getResponse().getStatus().equals(Response.STATUS_ERROR)) {
    		LOGGER.log(Level.SEVERE, responses.getMessage());
    	}
    	else {
    		channels = responses.getResponse().getTvChannel();
    	}
		return channels;
	}

	public TVGrid tvGridLookup(String gnId) throws IOException {
		TVGrid tvGrid = null;
		
		Authenticate authenticate = new Authenticate(getClientId(), getClientTag(), this.getUserId());
		
		TVChannel tvChannel = new TVChannel();
		tvChannel.setGnId(gnId);
		
		Query query = new Query(Query.CMD_TVGRID_LOOKUP);
		query.setTvChannel(tvChannel);
		
		Queries queries = new Queries(authenticate, query);
		
		XmlHttpContent content = new XmlHttpContent(DICTIONARY, Queries.ELEMENT_NAME, queries);
    	//System.out.println(DICTIONARY.toStringOf(Queries.ELEMENT_NAME, queries));

		GracenoteRequest<Responses> request = new GracenoteRequest<Responses>(this, content, Responses.class);
		Responses responses = request.execute();
    	//System.out.println(responses);
   	
    	if (responses.getResponse().getStatus().equals(Response.STATUS_ERROR)) {
    		LOGGER.log(Level.SEVERE, responses.getMessage());
    	}
    	else {
    		tvGrid = responses.getResponse().getTvGrid();
    	}
    	
		return tvGrid;
	}
	
	public TVProgram tvProgramFetch(String gnId) throws IOException {
		TVProgram tvProgram = null;
		
		Authenticate authenticate = new Authenticate(getClientId(), getClientTag(), this.getUserId());
		
		Query query = new Query(Query.CMD_TVPROGRAM_FETCH);
		query.setGnId(gnId);
		
		Queries queries = new Queries(authenticate, query);
		
		XmlHttpContent content = new XmlHttpContent(DICTIONARY, Queries.ELEMENT_NAME, queries);
    	//System.out.println(DICTIONARY.toStringOf(Queries.ELEMENT_NAME, queries));

		GracenoteRequest<Responses> request = new GracenoteRequest<Responses>(this, content, Responses.class);
		Responses responses = request.execute();
    	//System.out.println(responses);
   	
    	if (responses.getResponse().getStatus().equals(Response.STATUS_ERROR)) {
    		LOGGER.log(Level.SEVERE, responses.getMessage());
    	}
    	else {
    		tvProgram = responses.getResponse().getTvProgram();
    	}
    	
    	return tvProgram;
	}
}
