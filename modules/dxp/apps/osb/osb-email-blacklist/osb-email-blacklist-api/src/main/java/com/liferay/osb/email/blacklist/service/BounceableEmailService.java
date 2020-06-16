/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
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
 * @see com.liferay.osb.email.blacklist.service.base.BounceableEmailServiceBaseImpl
 * @see com.liferay.osb.email.blacklist.service.impl.BounceableEmailServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=osb", "json.web.service.context.path=BounceableEmail"}, service = BounceableEmailService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface BounceableEmailService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link BounceableEmailServiceUtil} to access the bounceable email remote service. Add custom service methods to {@link com.liferay.osb.email.blacklist.service.impl.BounceableEmailServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	public void sendBounceableEmail(MailMessage mailMessage);

	public void sendBounceableEmail(SubscriptionSender subscriptionSender)
		throws java.lang.Exception;

	@Async
	public void sendBounceableEmailAsync(MailMessage mailMessage);

	@Async
	public void sendBounceableEmailAsync(SubscriptionSender subscriptionSender);
}