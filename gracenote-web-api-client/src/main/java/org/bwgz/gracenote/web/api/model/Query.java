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

public class Query {
	public static final String CMD_REGISTER				= "REGISTER";
	public static final String CMD_TVPROVIDER_LOOKUP	= "TVPROVIDER_LOOKUP";
	public static final String CMD_TVCHANNEL_LOOKUP		= "TVCHANNEL_LOOKUP";
	public static final String CMD_TVGRID_LOOKUP		= "TVGRID_LOOKUP";
	public static final String CMD_TVPROGRAM_FETCH		= "TVPROGRAM_FETCH";
	
	public static final String MODE_TVPROVIDER			= "TVPROVIDER";
	
	@Key("@CMD")
	private String command;
	
	@Key("CLIENT")
	private String client;
	   
	@Key("POSTALCODE")
	private String postalCode;
	
	@Key("MODE")
	private String mode;

	@Key("GN_ID")
	private String gnId;

	@Key("OPTION")
	private Option option;

	@Key("TVCHANNEL")
	private TVChannel tvChannel;

	public Query(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getGnId() {
		return gnId;
	}

	public void setGnId(String gnId) {
		this.gnId = gnId;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public TVChannel getTvChannel() {
		return tvChannel;
	}

	public void setTvChannel(TVChannel tvChannel) {
		this.tvChannel = tvChannel;
	}
}

