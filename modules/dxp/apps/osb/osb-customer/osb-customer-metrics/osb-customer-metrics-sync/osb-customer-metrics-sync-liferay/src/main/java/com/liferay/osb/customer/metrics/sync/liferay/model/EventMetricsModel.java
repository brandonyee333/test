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

import com.liferay.osb.customer.account.entry.details.constants.EventConstants;
import com.liferay.osb.customer.account.entry.details.model.Event;
import com.liferay.osb.customer.metrics.api.model.MetricsModel;
import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.sync.liferay.model.util.MetricsTransformationUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = MetricsModel.class)
public class EventMetricsModel extends BaseModelMetricsModel<Event> {

	@Override
	public Map<String, Object> getAttributes(Event event) {
		Map<String, Object> attributes =
			_metricsTransformationUtil.transformSharedAttributes(
				event.getModelAttributes());

		attributes.put("type", EventConstants.getTypeLabel(event.getType()));

		return attributes;
	}

	@Override
	public Class getModelClass() {
		return Event.class;
	}

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

}