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

package com.liferay.osb.asah.common.multitenancy;

import com.liferay.osb.asah.common.constants.ServiceConstants;
import com.liferay.osb.asah.common.elasticsearch.impl.ElasticsearchInvokerManager;
import com.liferay.osb.asah.common.multitenancy.impl.MultiTenantProjectDogImpl;
import com.liferay.osb.asah.common.multitenancy.impl.SingleTenantProjectDogImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author André Miranda
 */
@Configuration
public class ProjectDogConfiguration {

	@Bean
	public ProjectDog projectDog(
		ElasticsearchInvokerManager elasticsearchInvokerManager) {

		if (ServiceConstants.OSB_ASAH_MULTITENANCY_ENABLED) {
			return new MultiTenantProjectDogImpl(elasticsearchInvokerManager);
		}

		return new SingleTenantProjectDogImpl();
	}

}