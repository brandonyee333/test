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