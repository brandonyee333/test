/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.license.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.license.model.LicenseKeySet;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

/**
 * Provides the remote service interface for LicenseKeySet. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see LicenseKeySetServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(
	property = {
		"json.web.service.context.name=osb",
		"json.web.service.context.path=LicenseKeySet"
	},
	service = LicenseKeySetService.class
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface LicenseKeySetService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.osb.customer.license.service.impl.LicenseKeySetServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the license key set remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link LicenseKeySetServiceUtil} if injection and service tracking are not available.
	 */
	public LicenseKeySet deleteLicenseKeySet(long licenseKeySetId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public LicenseKeySet getLicenseKeySet(long licenseKeySetId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public LicenseKeySet updateLicenseKeySet(long licenseKeySetId, String name)
		throws PortalException;

}