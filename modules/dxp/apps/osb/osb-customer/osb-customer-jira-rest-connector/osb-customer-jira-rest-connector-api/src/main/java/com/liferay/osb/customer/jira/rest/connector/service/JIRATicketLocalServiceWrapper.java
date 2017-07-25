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

package com.liferay.osb.customer.jira.rest.connector.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link JIRATicketLocalService}.
 *
 * @author Noah Sherrill
 * @see JIRATicketLocalService
 * @generated
 */
@ProviderType
public class JIRATicketLocalServiceWrapper implements JIRATicketLocalService,
	ServiceWrapper<JIRATicketLocalService> {
	public JIRATicketLocalServiceWrapper(
		JIRATicketLocalService jiraTicketLocalService) {
		_jiraTicketLocalService = jiraTicketLocalService;
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	@Override
	public java.lang.String getOSGiServiceIdentifier() {
		return _jiraTicketLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public JIRATicketLocalService getWrappedService() {
		return _jiraTicketLocalService;
	}

	@Override
	public void setWrappedService(JIRATicketLocalService jiraTicketLocalService) {
		_jiraTicketLocalService = jiraTicketLocalService;
	}

	private JIRATicketLocalService _jiraTicketLocalService;
}