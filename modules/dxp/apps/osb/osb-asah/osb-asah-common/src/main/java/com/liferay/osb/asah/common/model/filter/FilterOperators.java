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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Marcellus Tavares
 */
public class FilterOperators {

	public static FilterOperator of(
		EventAttributeDefinition.DataType dataType, String name,
		List<String> values) {

		FilterOperatorSupplier filterOperatorSupplier = Optional.ofNullable(
			_filterOperatorSuppliers.get(name)
		).orElseThrow(
			() -> new IllegalArgumentException("Invalid operator: " + name)
		);

		return filterOperatorSupplier.get(dataType, values);
	}

	private static final Map<String, FilterOperatorSupplier>
		_filterOperatorSuppliers =
			new HashMap<String, FilterOperatorSupplier>() {
				{
					put("between", BetweenFilterOperator::new);
					put("bin", BinFilterOperator::new);
					put("contains", ContainsFilterOperator::new);
					put("dateGrouping", DateGroupingFilterOperator::new);
					put("endsWith", EndsWithFilterOperator::new);
					put("eq", EqualsFilterOperator::new);
					put("ge", GreaterThanEqualsFilterOperator::new);
					put("gt", GreaterThanFilterOperator::new);
					put("le", LessThanEqualsFilterOperator::new);
					put("lt", LessThanFilterOperator::new);
					put("ne", NotEqualsFilterOperator::new);
					put("notContains", NotContainsFilterOperator::new);
					put("similarTo", SimilarToFilterOperator::new);
					put("startsWith", StartsWithFilterOperator::new);
				}
			};

}