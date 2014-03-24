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

public class TVProgram extends GenericXml {
	@Key("GN_ID")
	private String gnId;
	
	@Key("TITLE")
	private String title;
	
	@Key("TITLE_SUB")
	private String subtitle;
	
	@Key("SYNOPSIS")
	private String synopsis;
	
	@Key("URL")
	private String url;

	@Key("CONTRIBUTOR")
	private Contributor[] contributor;

	@Key("IPGCATEGORY")
	private IPGCategory ipgCategory;

	public String getGnId() {
		return gnId;
	}

	public void setGnId(String gnId) {
		this.gnId = gnId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Contributor[] getContributor() {
		return contributor;
	}

	public void setContributor(Contributor[] contributor) {
		this.contributor = contributor;
	}

	public IPGCategory getIpgCategory() {
		return ipgCategory;
	}

	public void setIpgCategory(IPGCategory ipgCategory) {
		this.ipgCategory = ipgCategory;
	}
}	
