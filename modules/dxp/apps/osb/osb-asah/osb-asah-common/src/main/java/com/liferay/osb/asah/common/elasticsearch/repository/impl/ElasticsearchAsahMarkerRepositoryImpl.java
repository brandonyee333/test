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

package com.liferay.osb.asah.common.elasticsearch.repository.impl;

import com.liferay.osb.asah.common.elasticsearch.ElasticsearchInvoker;
import com.liferay.osb.asah.common.entity.AsahMarker;
import com.liferay.osb.asah.common.repository.AsahMarkerRepository;
import com.liferay.osb.asah.common.util.WeDeployServiceThreadLocal;
import com.liferay.osb.asah.common.wedeploy.data.WeDeployDataService;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

/**
 * @author Marcellus Tavares
 */
@ConditionalOnProperty(
	havingValue = "false", matchIfMissing = true,
	value = "osb.asah.postgresql.enabled"
)
@Repository
public class ElasticsearchAsahMarkerRepositoryImpl
	extends BaseElasticsearchRepository<AsahMarker, String>
	implements AsahMarkerRepository {

	@Override
	protected String getCollectionName() {
		return "OSBAsahMarkers";
	}

	@Override
	protected ElasticsearchInvoker getElasticsearchInvoker() {
		return _resolveElasticsearchInvoker();
	}

	private ElasticsearchInvoker _resolveElasticsearchInvoker() {
		WeDeployDataService weDeployDataService =
			WeDeployServiceThreadLocal.getWeDeployDataService();

		if (weDeployDataService == null) {
			throw new IllegalStateException("WeDeploy data service is null");
		}

		if (weDeployDataService == WeDeployDataService.OSB_ASAH_DXP_RAW) {
			return _dxpRawElasticsearchInvoker;
		}

		if (weDeployDataService == WeDeployDataService.OSB_ASAH_CEREBRO_INFO) {
			return _cerebroInfoElasticsearchInvoker;
		}

		if (weDeployDataService == WeDeployDataService.OSB_ASAH_FARO_INFO) {
			return _faroInfoElasticsearchInvoker;
		}

		if (weDeployDataService ==
				WeDeployDataService.OSB_ASAH_SALESFORCE_RAW) {

			return _salesforceRawElasticsearchInvoker;
		}

		throw new IllegalStateException(
			"Unexpected WeDeploy data service value " + weDeployDataService);
	}

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_CEREBRO_INFO)
	private ElasticsearchInvoker _cerebroInfoElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_DXP_RAW)
	private ElasticsearchInvoker _dxpRawElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_FARO_INFO)
	private ElasticsearchInvoker _faroInfoElasticsearchInvoker;

	@ElasticsearchInvoker.Autowired(WeDeployDataService.OSB_ASAH_SALESFORCE_RAW)
	private ElasticsearchInvoker _salesforceRawElasticsearchInvoker;

}