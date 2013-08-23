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

/*
 * The root element for all queries is <QUERIES> containing one <QUERY> element.
 * 
 * Every query, except REGISTER, must include an authorization <AUTH> block.
 * A query can include an optional <LANG> element to specify a preferred natural language in which to receive response metadata. 
 * 
 * <QUERIES>
 *     <AUTH>
 *     ...
 *     </AUTH>
 *     <LANG>ger</LANG>
 *     <QUERY>
 *     ...
 *     </QUERY>
 * </QUERIES>
 */

public class Queries {
	public static final String ELEMENT_NAME	= "QUERIES";

	@Key("AUTH")
	private Authenticate authenticate;
	
	@Key("LANG")
	private String language;
	
	@Key("QUERY")
	private Query query;
	
	@Key("COUNTRY")
	private String country;

	public Queries(Authenticate authenticate, Query query, String language) {
		this.authenticate = authenticate;
		this.query = query;
		this.language = language;
	}

	public Queries(Authenticate authenticate, Query query) {
		this(authenticate, query, null);
	}

	public Queries(Query query) {
		this(null, query);
	}

	public Authenticate getAuthenticate() {
		return authenticate;
	}

	public void setAuthenticate(Authenticate authenticate) {
		this.authenticate = authenticate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}