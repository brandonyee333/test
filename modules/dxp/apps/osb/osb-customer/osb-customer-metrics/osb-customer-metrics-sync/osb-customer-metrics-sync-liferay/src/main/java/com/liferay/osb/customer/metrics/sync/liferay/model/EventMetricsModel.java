/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.liferay.model;

import com.liferay.osb.customer.account.entry.details.constants.EventConstants;
import com.liferay.osb.customer.account.entry.details.model.Event;
import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.model.MetricsModel;
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