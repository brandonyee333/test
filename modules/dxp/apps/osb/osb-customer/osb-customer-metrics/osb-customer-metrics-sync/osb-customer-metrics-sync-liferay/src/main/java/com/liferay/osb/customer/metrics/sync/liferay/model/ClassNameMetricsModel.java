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
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.service.ClassNameLocalService;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class ClassNameMetricsModel extends BaseModelMetricsModel<ClassName> {

	@Override
	public boolean allowDeleteAll() {
		return false;
	}

	@Override
	public Map<String, Object> getAttributes(ClassName className) {
		return _metricsTransformationUtil.transformSharedAttributes(
			className.getModelAttributes());
	}

	@Override
	public Class getModelClass() {
		return ClassName.class;
	}

	@Override
	public void resyncAll() throws Exception {
		List<ClassName> classNames = _classNameLocalService.getClassNames(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (ClassName className : classNames) {
			String classNameValue = className.getValue();

			if (classNameValue.contains("osb")) {
				Message message = messageFactory.createUpdateMessage(
					className.getModelClassName(), className);

				messagePublisher.publish(RoutingKeys.METRICS_UPDATE, message);
			}
		}
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

}