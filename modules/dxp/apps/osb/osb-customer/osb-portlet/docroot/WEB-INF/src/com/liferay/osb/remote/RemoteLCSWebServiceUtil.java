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

package com.liferay.osb.remote;

/**
 * @author Amos Fong
 */
public class RemoteLCSWebServiceUtil {

	public static void deleteLCSMessage(
		long corpProjectId, long corpProjectMessageId) {

		getRemoteLCSWebService().deleteLCSMessage(
			corpProjectId, corpProjectMessageId);
	}

	public static RemoteLCSWebService getRemoteLCSWebService() {
		return _remoteLCSWebService;
	}

	public static void sendLCSMessage(
		long corpProjectId, long corpProjectMessageId, String content,
		int severityLevel, String title, int type) {

		getRemoteLCSWebService().sendLCSMessage(
			corpProjectId, corpProjectMessageId, content, severityLevel, title,
			type);
	}

	public static void sendLCSSubscriptionEntries(
		long corpProjectId, String lcsSubscriptionEntriesJSON) {

		getRemoteLCSWebService().sendLCSSubscriptionEntries(
			corpProjectId, lcsSubscriptionEntriesJSON);
	}

	public void setRemoteLCSWebService(
		RemoteLCSWebService remoteLCSWebService) {

		_remoteLCSWebService = remoteLCSWebService;
	}

	private static RemoteLCSWebService _remoteLCSWebService;

}