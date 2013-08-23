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
 * Every query a client application sends to Gracenote must include both of Client ID string and User ID string inside an authorization <AUTH> block.
 * 
 * Currently, the Client string has the following format: 123456-789123456789012312. The first part is the Client ID, and the second part is the Client ID Tag. 
 * The client application must submit a REGISTER query to obtain a User ID for a device. The client application needs to register a device only once. 
 * 
 *    <AUTH>
 *        <CLIENT>client_id_string</CLIENT>
 *        <USER>user_id_string</USER>
 *    </AUTH>
 */

public class Authenticate {
	@Key("CLIENT")
	private String clientId;
	
	@Key("USER")
	private String userId;

	public Authenticate(String clientId, String userId) {
		this.clientId = clientId;
		this.userId = userId;
	}
	
	public Authenticate(String clientId, String clientTag, String userId) {
		this(clientId + "-" + clientTag, userId);
	}
}
