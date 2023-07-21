/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.metrics.sync.liferay.model.listener;

import com.liferay.osb.customer.metrics.impl.model.BaseMetricsModelListener;
import com.liferay.osb.customer.metrics.sync.liferay.constants.ClassNameConstants;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.security.audit.storage.model.AuditEvent;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = ModelListener.class)
public class AuditEventModelListener
	extends BaseMetricsModelListener<AuditEvent> {

	@Override
	public boolean ignoreUpdate(AuditEvent auditEvent)
		throws ModelListenerException {

		if (ArrayUtil.contains(
				_AUDIT_EVENT_CLASS_NAMES, auditEvent.getClassName())) {

			return false;
		}

		return true;
	}

	private static final String[] _AUDIT_EVENT_CLASS_NAMES = {
		ClassNameConstants.DOWNLOAD_PORTLET_CLASS_NAME
	};

}