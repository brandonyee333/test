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

package com.liferay.osb.loop.token.auth.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.loop.token.auth.model.TokenAuthEntry;

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
 * Provides the remote service interface for TokenAuthEntry. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Bruno Farache
 * @see TokenAuthEntryServiceUtil
 * @see com.liferay.osb.loop.token.auth.service.base.TokenAuthEntryServiceBaseImpl
 * @see com.liferay.osb.loop.token.auth.service.impl.TokenAuthEntryServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@OSGiBeanProperties(property =  {
	"json.web.service.context.name=tokenauth", "json.web.service.context.path=TokenAuthEntry"}, service = TokenAuthEntryService.class)
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface TokenAuthEntryService extends BaseService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TokenAuthEntryServiceUtil} to access the token auth entry remote service. Add custom service methods to {@link com.liferay.osb.loop.token.auth.service.impl.TokenAuthEntryServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public TokenAuthEntry addTokenAuthEntry(java.lang.String device)
		throws PortalException;

	public TokenAuthEntry deleteTokenAuthEntry(long tokenAuthEntryId)
		throws PortalException;

	public TokenAuthEntry deleteTokenAuthEntry(java.lang.String token)
		throws PortalException;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<TokenAuthEntry> getTokenAuthEntries(int start, int end)
		throws PortalException;
}