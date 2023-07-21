/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.liferay.model;

import com.liferay.osb.customer.metrics.impl.model.BaseModelMetricsModel;
import com.liferay.osb.customer.metrics.model.MetricsModel;
import com.liferay.osb.customer.metrics.sync.liferay.model.util.MetricsTransformationUtil;
import com.liferay.portal.kernel.model.User;
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

		String className = auditEvent.getClassName();

		if (className.equals(User.class.getName())) {
			String classPK = auditEvent.getClassPK();

			User user = _userLocalService.fetchUser(Long.valueOf(classPK));

			if (user != null) {
				attributes.put("classPK", user);
			}
		}

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