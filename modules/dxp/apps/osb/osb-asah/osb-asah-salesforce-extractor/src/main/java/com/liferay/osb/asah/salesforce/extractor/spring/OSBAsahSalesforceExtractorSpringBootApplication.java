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

package com.liferay.osb.asah.salesforce.extractor.spring;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.spring.OSBAsahSpringBootApplication;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Vishal Reddy
 */
@ComponentScan("com.liferay.osb.asah.salesforce.extractor")
public class OSBAsahSalesforceExtractorSpringBootApplication
	extends OSBAsahSpringBootApplication {

	public static void main(String[] args) {
		if (ServiceConstants.OSB_ASAH_MULTITENANCY_ENABLED) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"Salesforce extractor cannot execute if multitenancy is " +
						"enabled");
			}

			return;
		}

		SpringApplication.run(
			OSBAsahSalesforceExtractorSpringBootApplication.class, args);
	}

	private static final Log _log = LogFactory.getLog(
		OSBAsahSalesforceExtractorSpringBootApplication.class);

}