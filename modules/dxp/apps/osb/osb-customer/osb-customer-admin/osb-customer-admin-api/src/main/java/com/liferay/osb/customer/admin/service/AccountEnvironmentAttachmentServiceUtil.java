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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for AccountEnvironmentAttachment. This utility wraps
 * <code>com.liferay.osb.customer.admin.service.impl.AccountEnvironmentAttachmentServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentAttachmentService
 * @generated
 */
public class AccountEnvironmentAttachmentServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountEnvironmentAttachmentServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountEnvironmentAttachmentServiceUtil} to access the account environment attachment remote service. Add custom service methods to <code>com.liferay.osb.customer.admin.service.impl.AccountEnvironmentAttachmentServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static
		com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment
				getAccountEnvironmentAttachment(
					long accountEnvironmentAttachmentId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountEnvironmentAttachment(
			accountEnvironmentAttachmentId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static AccountEnvironmentAttachmentService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<AccountEnvironmentAttachmentService,
		 AccountEnvironmentAttachmentService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			AccountEnvironmentAttachmentService.class);

		ServiceTracker
			<AccountEnvironmentAttachmentService,
			 AccountEnvironmentAttachmentService> serviceTracker =
				new ServiceTracker
					<AccountEnvironmentAttachmentService,
					 AccountEnvironmentAttachmentService>(
						 bundle.getBundleContext(),
						 AccountEnvironmentAttachmentService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}