/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.admin.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.admin.model.AccountEntry;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;

import java.util.List;

/**
 * Provides the remote service interface for AccountEntry. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(
	property = {
		"json.web.service.context.name=osb",
		"json.web.service.context.path=AccountEntry"
	},
	service = AccountEntryService.class
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface AccountEntryService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountEntryServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the account entry remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link AccountEntryServiceUtil} if injection and service tracking are not available.
	 */
	public AccountEntry deleteAccountEntry(long accountEntryId)
		throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry fetchCorpProjectAccountEntry(String corpProjectUuid)
		throws Exception;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry fetchKoroneikiAccountEntry(String koroneikiAccountKey)
		throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> getAccountEntries(
			String userUuid, long[] productEntryIds)
		throws Exception;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry getAccountEntry(long accountEntryId)
		throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry getAccountEntryByCode(String code)
		throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry getCorpProjectAccountEntry(String corpProjectUuid)
		throws Exception;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public void syncToZendesk(String koroneikiAccountKey) throws Exception;

	public AccountEntry updateInstructions(
			long accountEntryId, String instructions)
		throws PortalException;

	@JSONWebService
	public AccountEntry updateInstructions(
			String koroneikiAccountKey, String instructions)
		throws PortalException;

	@JSONWebService
	public AccountEntry updateLanguageId(
			String koroneikiAccountKey, String languageId)
		throws PortalException;

}