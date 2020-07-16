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
	 * Never modify or reference this interface directly. Always use {@link BounceableEmailServiceUtil} to access the bounceable email remote service. Add custom service methods to <code>com.liferay.osb.email.blacklist.service.impl.BounceableEmailServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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