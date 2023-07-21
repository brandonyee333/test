/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.sync.engine.document.library.handler;

import org.apache.http.client.ResponseHandler;

/**
 * @author Shinn Lok
 */
public interface Handler<T> extends ResponseHandler<T> {

	public String getException(String response);

	public void handleException(Exception e);

	public boolean handlePortalException(String exception) throws Exception;

	public void processFinally();

	public void processResponse(String response) throws Exception;

	public void removeEvent();

}