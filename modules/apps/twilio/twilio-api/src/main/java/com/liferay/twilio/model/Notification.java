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

package com.liferay.twilio.model;

import java.time.ZonedDateTime;

/**
 * @author Peter Richards
 */
public class Notification {

	public ZonedDateTime getDateCreated() {
		return _dateCreated;
	}

	public ZonedDateTime getDateSent() {
		return _dateSent;
	}

	public Integer getErrorCode() {
		return _errorCode;
	}

	public String getErrorMessage() {
		return _errorMessage;
	}

	public String getMessage() {
		return _message;
	}

	public String getRecipient() {
		return _recipient;
	}

	public String getSender() {
		return _sender;
	}

	public String getSid() {
		return _sid;
	}

	public String getStatus() {
		return _status;
	}

	Notification(
		final String sender, final String recipient, final String message) {

		this._sender = sender;
		this._recipient = recipient;
		this._message = message;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this._dateCreated = dateCreated;
	}

	public void setDateSent(ZonedDateTime dateSent) {
		this._dateSent = dateSent;
	}

	public void setErrorCode(Integer errorCode) {
		this._errorCode = errorCode;
	}

	public void setErrorMessage(String errorMessage) {
		this._errorMessage = errorMessage;
	}

	public void setSid(String sid) {
		this._sid = sid;
	}

	public void setStatus(String status) {
		this._status = status;
	}

	private ZonedDateTime _dateCreated;
	private ZonedDateTime _dateSent;
	private Integer _errorCode;
	private String _errorMessage;
	private final String _message;
	private final String _recipient;
	private final String _sender;
	private String _sid;
	private String _status;

}