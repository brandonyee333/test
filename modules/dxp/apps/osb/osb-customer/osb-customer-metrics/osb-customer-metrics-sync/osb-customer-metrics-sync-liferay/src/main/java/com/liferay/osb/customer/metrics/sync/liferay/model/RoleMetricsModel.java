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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.service.RoleLocalService;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class RoleMetricsModel extends BaseModelMetricsModel<Role> {

	@Override
	public boolean allowDeleteAll() {
		return false;
	}

	@Override
	public Map<String, Object> getAttributes(Role role) {
		return _metricsTransformationUtil.transformSharedAttributes(
			role.getModelAttributes());
	}

	@Override
	public Class getModelClass() {
		return Role.class;
	}

	@Override
	public void resyncAll() throws Exception {
		List<Role> roles = _roleLocalService.getRoles(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (Role role : roles) {
			Message message = messageFactory.createUpdateMessage(
				role.getModelClassName(), role);

			messagePublisher.publish(RoutingKeys.METRICS_UPDATE, message);
		}
	}

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

	@Reference
	private RoleLocalService _roleLocalService;

}