/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.wsrp.internal.proxy;

import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;

/**
 * Emulates interface for org.apache.axis.client.Stub for proxy use.
 *
 * @author Michael Young
 */
public interface Stub {

	public Call _createCall() throws ServiceException;

}