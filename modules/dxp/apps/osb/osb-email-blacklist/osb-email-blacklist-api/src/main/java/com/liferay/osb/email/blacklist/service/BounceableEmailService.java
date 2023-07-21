/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.email.blacklist.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.messaging.async.Async;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.spring.osgi.OSGiBeanProperties;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.SubscriptionSender;

/**
 * Provides the remote service interface for BounceableEmail. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Jamie Sammons
 * @see BounceableEmailServiceUtil
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(
	property = {
		"json.web.service.context.name=osb",
		"json.web.service.context.path=BounceableEmail"
	},
	service = BounceableEmailService.class
)
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface BounceableEmailService extends BaseService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.osb.email.blacklist.service.impl.BounceableEmailServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the bounceable email remote service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link BounceableEmailServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	public void sendBounceableEmail(MailMessage mailMessage);

	public void sendBounceableEmail(SubscriptionSender subscriptionSender)
		throws Exception;

	@Async
	public void sendBounceableEmailAsync(MailMessage mailMessage);

	@Async
	public void sendBounceableEmailAsync(SubscriptionSender subscriptionSender);

}