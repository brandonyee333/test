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

package com.liferay.osb.customer.admin.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.admin.model.AccountEnvironment;
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
import java.util.Map;

/**
 * Provides the remote service interface for AccountEnvironment. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(
	property = {
		"json.web.service.context.name=osb",
		"json.web.service.context.path=AccountEnvironment"
	},
	service = AccountEnvironmentService.class
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface AccountEnvironmentService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountEnvironmentServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the account environment remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link AccountEnvironmentServiceUtil} if injection and service tracking are not available.
	 */
	public AccountEnvironment addAccountEnvironment(
			long accountEntryId, long productEntryId, String name, int envOS,
			String envOSCustom, int envDB, int envJVM, int envAS, int envLFR,
			int envCommerce, int envBrowser, int envCS, String envSearch)
		throws PortalException;

	public AccountEnvironment deleteAccountEnvironment(
			long accountEnvironmentId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEnvironment getAccountEnvironment(long accountEnvironmentId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEnvironment> getAccountEnvironments(long accountEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Map<String, List<AccountEnvironment>> getAccountEnvironmentsMap(
			long accountEntryId)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public AccountEnvironment updateAccountEnvironment(
			long accountEnvironmentId, long productEntryId, String name,
			int envOS, String envOSCustom, int envDB, int envJVM, int envAS,
			int envLFR, int envCommerce, int envBrowser, int envCS,
			String envSearch)
		throws PortalException;

}