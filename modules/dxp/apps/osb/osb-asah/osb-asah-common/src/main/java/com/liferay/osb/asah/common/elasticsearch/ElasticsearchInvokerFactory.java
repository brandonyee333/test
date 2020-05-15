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

package com.liferay.osb.asah.common.elasticsearch;

import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

/**
 * @author Brian Wing Shun Chan
 */
public interface ElasticsearchInvokerFactory {

	public ElasticsearchInvoker forCerebroInfo();

	public ElasticsearchInvoker forCerebroRaw();

	public ElasticsearchInvoker forDXPRaw();

	public ElasticsearchInvoker forFaroInfo();

	public ElasticsearchInvoker forSalesforceRaw();

	public ElasticsearchInvoker forWeDeployDataService(
		WeDeployDataService weDeployDataService);

}