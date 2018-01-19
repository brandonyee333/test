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

import com.liferay.pulpo.connector.de.contacts.model.serializer.CustomFieldSerializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Cristina González
 */
@Component(immediate = true, service = CustomFieldSerializerRegistry.class)
public class CustomFieldSerializerRegistry {

	public List<CustomFieldSerializer> getCustomFieldSerializers() {
		return new ArrayList<>(_customFieldSerializeres.values());
	};

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		unbind = "removeCustomFieldSerializer"
	)
	protected void addCustomFieldSerializer(
		CustomFieldSerializer customFieldSerializer) {

		Class<?> clazz = customFieldSerializer.getCustomFieldClass();

		String className = clazz.getName();

		_customFieldSerializeres.put(className, customFieldSerializer);
	}

	protected void removeCustomFieldSerializer(
		CustomFieldSerializer customFieldSerializer) {

		Class<?> clazz = customFieldSerializer.getClass();

		String className = clazz.getName();

		_customFieldSerializeres.remove(className);
	}

	private static final Map<String, CustomFieldSerializer>
		_customFieldSerializeres = new HashMap<>();

}