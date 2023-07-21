/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.liferay.model;

import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.osb.customer.metrics.rabbitmq.constants.RoutingKeys;
import com.liferay.osb.customer.metrics.sync.liferay.model.util.MetricsTransformationUtil;
import com.liferay.osb.distributed.messaging.Message;
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
			Message message = messageFactory.createUpdateMessage(
				region.getModelClassName(), region);

			messagePublisher.publish(RoutingKeys.METRICS_UPDATE, message);
		}
	}

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

	@Reference
	private RegionService _regionService;

}