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

package com.liferay.osb.customer.metrics.models;

import com.liferay.osb.customer.metrics.api.model.MetricsModel;
import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.models.util.MetricsTransformationUtil;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.security.audit.storage.model.AuditEvent;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = MetricsModel.class)
public class AuditEventMetricsModel extends BaseModelMetricsModel<AuditEvent> {

	@Override
	public Map<String, Object> getAttributes(AuditEvent auditEvent) {
		Map<String, Object> attributes =
			_metricsTransformationUtil.transformSharedAttributes(
				auditEvent.getModelAttributes());

		attributes.put("auditEventId", -1 * auditEvent.getAuditEventId());

		return attributes;
	}

	@Override
	public Class getModelClass() {
		return AuditEvent.class;
	}

	@Reference
	private MetricsTransformationUtil _metricsTransformationUtil;

	@Reference
	private UserLocalService _userLocalService;

}