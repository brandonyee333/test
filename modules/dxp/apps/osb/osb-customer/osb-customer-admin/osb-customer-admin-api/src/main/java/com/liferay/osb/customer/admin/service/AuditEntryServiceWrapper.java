/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AuditEntryService}.
 *
 * @author Brian Wing Shun Chan
 * @see AuditEntryService
 * @generated
 */
public class AuditEntryServiceWrapper
	implements AuditEntryService, ServiceWrapper<AuditEntryService> {

	public AuditEntryServiceWrapper(AuditEntryService auditEntryService) {
		_auditEntryService = auditEntryService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _auditEntryService.getOSGiServiceIdentifier();
	}

	@Override
	public AuditEntryService getWrappedService() {
		return _auditEntryService;
	}

	@Override
	public void setWrappedService(AuditEntryService auditEntryService) {
		_auditEntryService = auditEntryService;
	}

	private AuditEntryService _auditEntryService;

}