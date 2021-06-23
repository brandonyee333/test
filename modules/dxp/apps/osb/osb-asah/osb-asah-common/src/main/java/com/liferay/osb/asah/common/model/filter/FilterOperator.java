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
import com.liferay.osb.asah.common.util.ListUtil;
import com.liferay.osb.asah.common.util.StringUtil;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;

import org.jooq.Condition;
import org.jooq.Field;

/**
 * @author Leslie Wong
 */
public abstract class FilterOperator {

	public static FilterOperator of(
		EventAttributeDefinition.DataType dataType, String name,
		List<String> values) {

		if (name.equals("between")) {
			return new BetweenFilterOperator(dataType, values);
		}
		else if (name.equals("contains")) {
			return new ContainsFilterOperator(dataType, values);
		}
		else if (name.equals("endsWith")) {
			return new EndsWithFilterOperator(dataType, values);
		}
		else if (name.equals("eq")) {
			return new EqualsFilterOperator(dataType, values);
		}
		else if (name.equals("ge")) {
			return new GreaterThanEqualsFilterOperator(dataType, values);
		}
		else if (name.equals("gt")) {
			return new GreaterThanFilterOperator(dataType, values);
		}
		else if (name.equals("le")) {
			return new LessThanEqualsFilterOperator(dataType, values);
		}
		else if (name.equals("lt")) {
			return new LessThanFilterOperator(dataType, values);
		}
		else if (name.equals("ne")) {
			return new NotEqualsFilterOperator(dataType, values);
		}
		else if (name.equals("similarTo")) {
			return new SimilarToFilterOperator(dataType, values);
		}
		else if (name.equals("startsWith")) {
			return new StartsWithFilterOperator(dataType, values);
		}

		throw new IllegalArgumentException("Invalid operator: " + name);
	}

	public abstract Condition getCondition(Field field);

	public abstract QueryBuilder getQueryBuilder(String fieldName);

	protected FilterOperator(
		EventAttributeDefinition.DataType dataType, int expectedArgumentCount,
		String name, List<String> values) {

		_validate(dataType, expectedArgumentCount, name, values);

		this.values = values;
	}

	protected FilterOperator(
		EventAttributeDefinition.DataType dataType, String name,
		List<String> values) {

		_validate(dataType, 1, name, values);

		this.values = values;
	}

	protected List<String> formatStringValues() {
		return ListUtil.map(values, StringUtil::unquoteAndDecodeInnerQuotes);
	}

	protected abstract List<EventAttributeDefinition.DataType>
		getSupportedDataTypes();

	protected List<String> values;

	private void _validate(
		EventAttributeDefinition.DataType dataType, int expectedArgumentCount,
		String name, List<String> values) {

		if (expectedArgumentCount != values.size()) {
			throw new IllegalArgumentException(
				String.format(
					"Expected %d value(s) for %s operation, got %d instead: %s",
					expectedArgumentCount, name, values.size(), values));
		}

		List<EventAttributeDefinition.DataType> supportedDataTypes =
			getSupportedDataTypes();

		if (!supportedDataTypes.contains(dataType)) {
			throw new IllegalArgumentException(
				"Filter operation " + name + " does not support data type " +
					dataType);
		}
	}

}