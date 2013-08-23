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

import org.bwgz.gracenote.web.api.model.TVChannel;
import org.bwgz.gracenote.web.api.model.TVGrid;
import org.bwgz.gracenote.web.api.model.TVProgram;
import org.bwgz.gracenote.web.api.model.TVProvider;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

public class GracenoteTest {
	  public static void main(String[] args) {
		    HttpTransport httpTransport = new NetHttpTransport();
		    Gracenote.Builder builder = new Gracenote.Builder(httpTransport,"client-id","client-tag");
		    Gracenote gracenote = builder.build();
		    try {
		    	gracenote.register();
		    	
		    	// Lookup the TV Providers in my area.
		    	TVProvider[] tvProviders = gracenote.tvProviderLookup("USA", "90028");
		    	for (TVProvider tvProvider : tvProviders) {
		    		// If DIRECTV is found then get more details.
		    		if (tvProvider.getName().contains("DIRECTV")) {
		    			System.out.printf("%s\n", tvProvider.getName());
		    			
		    			String channelNumber = "056"; // Get details for this channel.
		    			String channelGnId = null;
		    			
		    			TVChannel[] tvChannels = gracenote.tvChannelLookup(tvProvider.getGnId());
				    	for (TVChannel tvChannel : tvChannels) {
			    			
			    			if (tvChannel.getChannelNumber().equals(channelNumber)) {
			    				if (channelGnId == null) {
			    					System.out.printf("%4s - %s\n", tvChannel.getChannelNumber(), tvChannel.getName());
			    					channelGnId = tvChannel.getGnId();
			    					break;
			    				}
			    			}
				    	}
				    	
				    	if (channelGnId != null) {
				    		// What's on now.
				    		
				    		TVGrid tvGrid = gracenote.tvGridLookup(channelGnId);
			    			TVProgram tvProgram = gracenote.tvProgramFetch(tvGrid.getTvProgram()[0].getGnId());
			    			
			    			System.out.printf("%4s - %s: %s\n", tvProgram.getTitle(), tvProgram.getSubtitle(), tvProgram.getSynopsis());
				    	}
				    	
		    		}
		    	}
			} catch (IOException e) {
				e.printStackTrace();
			}
	  }
}
