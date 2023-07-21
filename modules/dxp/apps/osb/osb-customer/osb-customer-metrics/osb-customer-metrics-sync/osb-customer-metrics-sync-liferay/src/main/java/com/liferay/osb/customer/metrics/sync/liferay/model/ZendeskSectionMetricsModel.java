/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.liferay.model;

import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.osb.customer.metrics.sync.liferay.model.util.MetricsTransformationUtil;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kyle Bischof
 */
@Component(immediate = true, service = MetricsModel.class)
public class ZendeskSectionMetricsModel
	extends BaseModelMetricsModel<ZendeskSection> {

	@Override
	public Map<String, Object> getAttributes(ZendeskSection zendeskSection) {
		return _metricsTransformationUtil.transformSharedAttributes(
			zendeskSection.getModelAttributes());
	}

	@Override
	public Class getModelClass() {
		return ZendeskSection.class;
	}

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

}