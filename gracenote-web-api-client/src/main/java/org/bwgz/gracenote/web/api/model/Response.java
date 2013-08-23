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

/*
 * All responses have a root element of type <RESPONSES> containing one <RESPONSE> element.
 * If the value of the STATUS attribute indicates whether the corresponding query succeeded or failed.
 * 
 * <RESPONSES>
 *     <RESPONSE  STATUS=...>
 *     ...
 *     </RESPONSE>
 * </RESPONSES>
 */
public class Response extends GenericXml {
	public static final String STATUS_OK		= "OK";
	public static final String STATUS_NO_MATCH	= "NO_MATCH";
	public static final String STATUS_ERROR		= "ERROR";

	@Key("@STATUS")
	private String status;
	   
	@Key("USER")
	private String user;
	   
	@Key("TVPROVIDER")
	private TVProvider[] tvProvider;

	@Key("TVCHANNEL")
	private TVChannel[] tvChannel;

	@Key("TVGRID")
	private TVGrid tvGrid;
	
	@Key("TVPROGRAM")
	private TVProgram tvProgram;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public TVProvider[] getTvProvider() {
		return tvProvider;
	}

	public void setTvProvider(TVProvider[] tvProvider) {
		this.tvProvider = tvProvider;
	}

	public TVChannel[] getTvChannel() {
		return tvChannel;
	}

	public void setTvChannel(TVChannel[] tvChannel) {
		this.tvChannel = tvChannel;
	}

	public TVGrid getTvGrid() {
		return tvGrid;
	}

	public void setTvGrid(TVGrid tvGrid) {
		this.tvGrid = tvGrid;
	}

	public TVProgram getTvProgram() {
		return tvProgram;
	}

	public void setTvProgram(TVProgram tvProgram) {
		this.tvProgram = tvProgram;
	}

}
