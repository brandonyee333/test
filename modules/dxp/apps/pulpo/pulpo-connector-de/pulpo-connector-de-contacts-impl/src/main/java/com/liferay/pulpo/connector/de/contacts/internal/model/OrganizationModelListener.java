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

package com.liferay.pulpo.connector.de.contacts.internal.model;

import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.pulpo.connector.de.contacts.model.ContactsModelListener;

import java.util.Arrays;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Shinn Lok
 */
@Component(immediate = true, service = ContactsModelListener.class)
public class OrganizationModelListener
	extends ContactsModelListener<Organization> {

	@Override
	protected List<String> getAvailableFields() {
		return _availableFields;
	}

	@Override
	protected List<Organization> getModels(User user) throws Exception {
		return user.getOrganizations();
	}

	private static final List<String> _availableFields = Arrays.asList(
		"name", "type");

}