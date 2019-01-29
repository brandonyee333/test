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

package com.liferay.osb.customer.metrics.model.listener;

import com.liferay.osb.customer.metrics.impl.model.BaseMetricsModelListener;
import com.liferay.osb.customer.metrics.model.listener.constants.ClassNameConstants;
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