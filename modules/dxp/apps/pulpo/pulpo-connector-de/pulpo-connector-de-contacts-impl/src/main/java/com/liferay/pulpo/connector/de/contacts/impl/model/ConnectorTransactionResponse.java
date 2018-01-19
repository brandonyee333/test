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

package com.liferay.pulpo.connector.de.contacts.impl.model;

/**
 * Represents the payload of a LCS message bus response to a transaction.
 *
 * @author Eduardo Garcia
 */
public class ConnectorTransactionResponse {

	/**
	 * Returns the connection transaction Id.
	 *
	 * @return The connection transaction Id
	 */
	public long getConnectionTransactionId() {
		return _connectionTransactionId;
	}

	/**
	 * Returns the status.
	 *
	 * @return The status
	 */
	public String getStatus() {
		return _status;
	}

	/**
	 * Sets the connection transaction Id.
	 *
	 * @param connectionTransactionId The connection transaction Id
	 */
	public void setConnectionTransactionId(long connectionTransactionId) {
		_connectionTransactionId = connectionTransactionId;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the status
	 */
	public void setStatus(String status) {
		_status = status;
	}

	private long _connectionTransactionId;
	private String _status;

}