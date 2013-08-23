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
package org.bwgz.gracenote.web.api.model;

import com.google.api.client.util.Key;
import com.google.api.client.xml.GenericXml;

public class TVChannel extends GenericXml {
	@Key("@ORD")
	private String ordinal;
	
	@Key("GN_ID")
	private String gnId;
	
	@Key("NAME")
	private String name;
	
	@Key("NAME_SHORT")
	private String shortName;
	
	@Key("COUNTRY")
	private String country;
	
	@Key("CHANNEL_NUM")
	private String channelNumber;
	
	@Key("URL")
	private String url;

	public String getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(String ordinal) {
		this.ordinal = ordinal;
	}

	public String getGnId() {
		return gnId;
	}

	public void setGnId(String gnId) {
		this.gnId = gnId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getChannelNumber() {
		return channelNumber;
	}

	public void setChannelNumber(String channelNumber) {
		this.channelNumber = channelNumber;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}	
