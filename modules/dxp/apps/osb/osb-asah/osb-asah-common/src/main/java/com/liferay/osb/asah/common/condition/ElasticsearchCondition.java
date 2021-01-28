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

package com.liferay.osb.asah.common.condition;

import com.liferay.osb.asah.common.constants.ServiceConstants;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Inácio Nery
 */
public class ElasticsearchCondition implements Condition {

	@Override
	public boolean matches(
		ConditionContext conditionContext,
		AnnotatedTypeMetadata annotatedTypeMetadata) {

		return !ServiceConstants.OSB_ASAH_POSTGRESQL_ENABLED;
	}

}