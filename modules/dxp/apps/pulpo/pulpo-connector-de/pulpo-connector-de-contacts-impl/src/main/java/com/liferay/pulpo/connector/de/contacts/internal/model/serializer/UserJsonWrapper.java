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

package com.liferay.pulpo.connector.de.contacts.internal.model.serializer;

import com.liferay.portal.kernel.model.User;

import java.util.List;

/**
 * @author Cristina González
 */
public class UserJsonWrapper {

	public UserJsonWrapper(User user, List<Object> fields) {
		_user = user;
		_customFields = fields;
	}

	public List<Object> getCustomFields() {
		return _customFields;
	}

	public User getUser() {
		return _user;
	}

	private final List<Object> _customFields;
	private final User _user;

}