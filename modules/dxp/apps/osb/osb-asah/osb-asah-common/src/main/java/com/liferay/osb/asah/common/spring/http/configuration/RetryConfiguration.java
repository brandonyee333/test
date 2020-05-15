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

package com.liferay.osb.asah.common.spring.http.configuration;

import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.NeverRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.HttpClientErrorException;

/**
 * @author André Miranda
 * @author Rachael Koestartyo
 */
@Configuration
public class RetryConfiguration {

	@Bean
	public RetryTemplate retryTemplate() {
		RetryPolicy retryPolicy = new ExceptionClassifierRetryPolicy() {
			{
				setExceptionClassifier(
					(Classifier<Throwable, RetryPolicy>)classifier -> {
						if (classifier instanceof HttpClientErrorException) {
							HttpClientErrorException hcee =
								(HttpClientErrorException)classifier;

							if ((hcee.getStatusCode() ==
									HttpStatus.FORBIDDEN) ||
								(hcee.getStatusCode() ==
									HttpStatus.UNAUTHORIZED)) {

								return _neverRetryPolicy;
							}
						}

						return _simplyRetryPolicy;
					});
			}
		};

		return new RetryTemplate() {
			{
				setRetryPolicy(retryPolicy);
				setThrowLastExceptionOnExhausted(true);
			}
		};
	}

	private static final RetryPolicy _neverRetryPolicy = new NeverRetryPolicy();
	private static final RetryPolicy _simplyRetryPolicy =
		new SimpleRetryPolicy();

}