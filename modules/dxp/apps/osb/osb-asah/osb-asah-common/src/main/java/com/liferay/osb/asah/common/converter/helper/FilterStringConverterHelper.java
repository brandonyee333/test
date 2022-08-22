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

package com.liferay.osb.asah.common.converter.helper;

import java.util.List;
import java.util.Map;

import org.jooq.Condition;

/**
 * @author Michael Bowerman
 */
public interface FilterStringConverterHelper {

	public Condition getCustomFunctionCondition(
			List<String> arguments, String customFunctionName, boolean negated)
		throws Exception;

	public Map<String, String> getFieldNameConversionMap();

	public Condition getInferredCondition(String fieldName);

	public Condition getLogicFunctionCondition(
			String fieldName, String operator, String valueString)
		throws Exception;

}