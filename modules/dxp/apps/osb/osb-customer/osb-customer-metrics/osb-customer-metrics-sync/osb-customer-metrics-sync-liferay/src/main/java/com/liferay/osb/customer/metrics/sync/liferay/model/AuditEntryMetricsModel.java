/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.liferay.model;

import com.liferay.osb.customer.admin.model.AuditEntry;
import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.osb.customer.metrics.sync.liferay.model.util.MetricsTransformationUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class AuditEntryMetricsModel extends BaseModelMetricsModel<AuditEntry> {

	@Override
	public Map<String, Object> getAttributes(AuditEntry auditEntry) {
		Map<String, Object> attributes =
			_metricsTransformationUtil.transformSharedAttributes(
				auditEntry.getModelAttributes());

		attributes.put("auditEntryId", -1 * auditEntry.getAuditEntryId());

		return attributes;
	}

	@Override
	public Class getModelClass() {
		return AuditEntry.class;
	}

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

}