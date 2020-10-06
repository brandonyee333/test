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

	public ElasticsearchInvoker forCerebroInfo(boolean cacheable);

	public ElasticsearchInvoker forCerebroRaw();

	public ElasticsearchInvoker forCerebroRaw(boolean cacheable);

	public ElasticsearchInvoker forDXPRaw();

	public ElasticsearchInvoker forDXPRaw(boolean cacheable);

	public ElasticsearchInvoker forFaroInfo();

	public ElasticsearchInvoker forFaroInfo(boolean cacheable);

	public ElasticsearchInvoker forSalesforceRaw();

	public ElasticsearchInvoker forSalesforceRaw(boolean cacheable);

	public ElasticsearchInvoker forWeDeployDataService(
		WeDeployDataService weDeployDataService);

	public ElasticsearchInvoker forWeDeployDataService(
		WeDeployDataService weDeployDataService, boolean cacheable);

}