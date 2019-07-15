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
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
				JSONObject jsonObject = messageFactory.createUpdateJSONObject(
					className.getModelClassName(), className);

				messagePublisher.sendMessage(
					RoutingKeys.METRICS_UPDATE, jsonObject);
			}
		}
	}

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

}