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

package com.liferay.pulpo.connector.de.contacts.test.custom.field;

import com.liferay.pulpo.connector.de.contacts.test.custom.field.model.CustomFieldExample;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Cristina González
 */
public class CustomFieldExampleService {

	public void addCustomFieldExample(CustomFieldExample customFieldExample) {
		_customFieldExamples.put(
			customFieldExample.getUserId(), customFieldExample);
	}

	public CustomFieldExample getCustomFieldExample(long userId) {
		return _customFieldExamples.get(userId);
	}

	public void removeCustomFieldExample(
		CustomFieldExample customFieldExample) {

		_customFieldExamples.remove(customFieldExample.getUserId());
	}

	private final Map<Long, CustomFieldExample> _customFieldExamples =
		new HashMap<>();

}