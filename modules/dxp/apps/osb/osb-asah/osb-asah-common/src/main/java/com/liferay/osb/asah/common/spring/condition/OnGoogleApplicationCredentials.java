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

package com.liferay.osb.asah.common.spring.condition;

import com.liferay.osb.asah.common.spring.annotation.ConditionalOnGoogleApplicationCredentials;

import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author Marcellus Tavares
 */
public class OnGoogleApplicationCredentials extends SpringBootCondition {

	@Override
	public ConditionOutcome getMatchOutcome(
		ConditionContext conditionContext,
		AnnotatedTypeMetadata annotatedTypeMetadata) {

		Environment environment = conditionContext.getEnvironment();

		String googleApplicationCredentials = environment.getProperty(
			"GOOGLE_APPLICATION_CREDENTIALS");

		Boolean matchIfMissing =
			_getConditionalOnGoogleCredentialsAnnotationAttribute(
				annotatedTypeMetadata, "matchIfMissing");

		if (googleApplicationCredentials == null) {
			if (matchIfMissing) {
				return ConditionOutcome.match();
			}

			return ConditionOutcome.noMatch(ConditionMessage.empty());
		}

		if (matchIfMissing) {
			return ConditionOutcome.noMatch(ConditionMessage.empty());
		}

		return ConditionOutcome.match();
	}

	private <T> T _getConditionalOnGoogleCredentialsAnnotationAttribute(
		AnnotatedTypeMetadata annotatedTypeMetadata, String attributeName) {

		Map<String, Object> annotationAttributes =
			annotatedTypeMetadata.getAnnotationAttributes(
				ConditionalOnGoogleApplicationCredentials.class.getName());

		return (T)annotationAttributes.get(attributeName);
	}

}