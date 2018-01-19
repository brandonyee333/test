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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import com.liferay.portal.kernel.model.User;
import com.liferay.pulpo.connector.de.contacts.model.serializer.CustomFieldSerializer;
import com.liferay.pulpo.connector.de.contacts.test.custom.field.model.CustomFieldExample;
import com.liferay.pulpo.connector.de.contacts.test.custom.field.model.std.CustomFieldExampleStdSerializer;

/**
 * @author Cristina González
 */
public class CustomFieldExampleSerializer
	implements CustomFieldSerializer<CustomFieldExample> {

	@Override
	public CustomFieldExample getCustomField(User user) {
		return
			_customFieldExampleService.getCustomFieldExample(user.getUserId());
	}

	@Override
	public Class getCustomFieldClass() {
		return CustomFieldExample.class;
	}

	@Override
	public String getCustomFieldName() {
		return "customField";
	}

	public void setCustomFieldExampleService(
		CustomFieldExampleService customFieldExampleService) {

		_customFieldExampleService = customFieldExampleService;
	}

	@Override
	public String writeAsString(CustomFieldExample customFieldExample) {
		ObjectMapper objectMapper = new ObjectMapper();

		SimpleModule simpleModule = new SimpleModule();

		simpleModule.addSerializer(
			getCustomFieldClass(), new CustomFieldExampleStdSerializer());

		objectMapper.registerModule(simpleModule);

		try {
			return objectMapper.writeValueAsString(customFieldExample);
		}
		catch (JsonProcessingException jpe) {
			throw new RuntimeException(jpe);
		}
	}

	private CustomFieldExampleService _customFieldExampleService;

}