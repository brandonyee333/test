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

package com.liferay.osb.customer.metrics.sync.liferay.model;

import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.osb.customer.metrics.rabbitmq.constants.RoutingKeys;
import com.liferay.osb.customer.metrics.sync.liferay.model.util.MetricsTransformationUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.service.RegionService;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class RegionMetricsModel extends BaseModelMetricsModel<Region> {

	@Override
	public boolean allowDeleteAll() {
		return false;
	}

	@Override
	public Map<String, Object> getAttributes(Region region) {
		return _metricsTransformationUtil.transformSharedAttributes(
			region.getModelAttributes());
	}

	@Override
	public Class getModelClass() {
		return Region.class;
	}

	@Override
	public void resyncAll() throws Exception {
		List<Region> regions = _regionService.getRegions();

		for (Region region : regions) {
			JSONObject jsonObject = messageFactory.createUpdateJSONObject(
				region.getModelClassName(), region);

			messagePublisher.sendMessage(
				RoutingKeys.METRICS_UPDATE, jsonObject);
		}
	}

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

	@Reference
	private RegionService _regionService;

}