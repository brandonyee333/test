/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.asah.common.model.filter;

import com.liferay.osb.asah.common.entity.EventAttributeDefinition;

import java.util.Collections;
import java.util.List;

import org.jooq.Condition;
import org.jooq.Field;

/**
 * @author Leslie Wong
 */
public class EndsWithFilterOperator extends FilterOperator {

	public EndsWithFilterOperator(
		EventAttributeDefinition.DataType dataType, List<String> values) {

		super(dataType, "endsWith", values);
	}

	@Override
	public Condition getCondition(Field field) {
		return field.endsWithIgnoreCase(getValue(dataType, values.get(0)));
	}

	@Override
	protected List<EventAttributeDefinition.DataType> getSupportedDataTypes() {
		return Collections.singletonList(
			EventAttributeDefinition.DataType.STRING);
	}

}