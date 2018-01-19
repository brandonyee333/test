/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.pulpo.connector.de.contacts;

import java.util.Map;

/**
 * Models a Contacts Connector.
 *
 * @author Eduardo Garcia
 */
public interface ContactsConnector {

	/**
	 * Sends a payload to the given destination.
	 *
	 * @param destinationName - destination name
	 * @param payload - payload
	 */
	public void sendMessage(String destinationName, String payload);

	/**
	 * Sends a payload and metadata to the given destination.
	 *
	 * @param destinationName - destination name
	 * @param payload - payload
	 * @param metadata - metadata
	 */
	public void sendMessage(
		String destinationName, String payload, Map<String, String> metadata);

}