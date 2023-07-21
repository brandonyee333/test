/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.configuration.metatype.definitions.annotations.internal;

import com.liferay.portal.configuration.metatype.definitions.ExtendedAttributeDefinition;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.osgi.service.metatype.AttributeDefinition;

/**
 * @author Iván Zaera
 */
public class AnnotationsExtendedAttributeDefinition
	implements ExtendedAttributeDefinition {

	public AnnotationsExtendedAttributeDefinition(
		Class<?> configurationBeanClass,
		AttributeDefinition attributeDefinition) {

		_attributeDefinition = attributeDefinition;
	}

	@Override
	public int getCardinality() {
		return _attributeDefinition.getCardinality();
	}

	@Override
	public String[] getDefaultValue() {
		return _attributeDefinition.getDefaultValue();
	}

	@Override
	public String getDescription() {
		return _attributeDefinition.getDescription();
	}

	@Override
	public Map<String, String> getExtensionAttributes(String uri) {
		Map<String, String> extensionAttributes = _extensionAttributes.get(uri);

		if (extensionAttributes == null) {
			extensionAttributes = Collections.emptyMap();
		}

		return extensionAttributes;
	}

	@Override
	public Set<String> getExtensionUris() {
		return _extensionAttributes.keySet();
	}

	@Override
	public String getID() {
		return _attributeDefinition.getID();
	}

	@Override
	public String getName() {
		return _attributeDefinition.getName();
	}

	@Override
	public String[] getOptionLabels() {
		return _attributeDefinition.getOptionLabels();
	}

	@Override
	public String[] getOptionValues() {
		return _attributeDefinition.getOptionValues();
	}

	@Override
	public int getType() {
		return _attributeDefinition.getType();
	}

	@Override
	public String validate(String value) {
		return _attributeDefinition.validate(value);
	}

	private final AttributeDefinition _attributeDefinition;
	private final Map<String, Map<String, String>> _extensionAttributes =
		new HashMap<>();

}