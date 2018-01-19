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

package com.liferay.pulpo.connector.de.contacts.model.serializer;

import com.liferay.portal.kernel.model.User;

/**
 * Models a Serializer of User's custom fields.
 *
 * @author Cristina González
 */
public interface CustomFieldSerializer<T> extends Serializer<T> {

	/**
	 * Return a custom field for a given user.
	 *
	 * @param  user - the user of the custom field
	 * @return a Object that should be serialized with the User
	 */
	public T getCustomField(User user);

	/**
	 * Return the class of the custom field.
	 *
	 * @return The class of the custom field
	 */
	public Class<T> getCustomFieldClass();

	/**
	 * Returns the name with which the custom field will be serialized.
	 *
	 * @return the name with which the custom field will be serialized
	 */
	public String getCustomFieldName();

}