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

package com.liferay.osb.asah.common.elasticsearch.impl;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchConnection;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchIndexManager;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvokerFactory;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Brian Wing Shun Chan
 * @author Rachael Koestartyo
 * @author André Miranda
 */
@Component
public class ElasticsearchInvokerFactoryImpl
	implements ElasticsearchInvokerFactory {

	@Override
	public ElasticsearchInvoker forCerebroInfo() {
		return forWeDeployDataService(
			WeDeployDataService.OSB_ASAH_CEREBRO_INFO);
	}

	@Override
	public ElasticsearchInvoker forCerebroRaw() {
		return forWeDeployDataService(WeDeployDataService.OSB_ASAH_CEREBRO_RAW);
	}

	@Override
	public ElasticsearchInvoker forDXPRaw() {
		return forWeDeployDataService(WeDeployDataService.OSB_ASAH_DXP_RAW);
	}

	@Override
	public ElasticsearchInvoker forFaroInfo() {
		return forWeDeployDataService(WeDeployDataService.OSB_ASAH_FARO_INFO);
	}

	@Override
	public ElasticsearchInvoker forSalesforceRaw() {
		return forWeDeployDataService(
			WeDeployDataService.OSB_ASAH_SALESFORCE_RAW);
	}

	@Override
	public ElasticsearchInvoker forWeDeployDataService(
		WeDeployDataService weDeployDataService) {

		return new ElasticsearchInvokerImpl(
			_elasticsearchIndexManager.getAliases(),
			_elasticsearchConnection.getTransportClient(),
			_elasticsearchIndexManager.getIndexNamespace(weDeployDataService));
	}

	@PostConstruct
	private void _init() {
		_elasticsearchIndexManager.addTemplates();
		_elasticsearchIndexManager.checkIndices();
	}

	@Autowired
	private ElasticsearchConnection _elasticsearchConnection;

	@Autowired
	private ElasticsearchIndexManager _elasticsearchIndexManager;

}