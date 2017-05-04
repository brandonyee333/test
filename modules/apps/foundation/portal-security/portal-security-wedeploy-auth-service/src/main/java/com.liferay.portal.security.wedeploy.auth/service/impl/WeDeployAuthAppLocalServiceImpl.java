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

package com.liferay.portal.security.wedeploy.auth.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Digester;
import com.liferay.portal.kernel.util.DigesterUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.security.wedeploy.auth.model.WeDeployAuthApp;
import com.liferay.portal.security.wedeploy.auth.service.base.WeDeployAuthAppLocalServiceBaseImpl;
import com.liferay.portal.security.wedeploy.auth.service.persistence.WeDeployAuthAppPersistence;

import java.util.Date;

//import org.springframework.security.oauth2.provider.token.;

/**
 * The implementation of the wedeploy auth app local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods
 * are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.portal.security.wedeploy.auth.service.WeDeployAuthAppLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Supritha Sundaram
 * @see com.liferay.portal.security.wedeploy.auth.service
 * .WeDeployAuthAppLocalServiceUtil
 */
public class WeDeployAuthAppLocalServiceImpl
		extends WeDeployAuthAppLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.portal.security.wedeploy.auth.service.WedeployAuthAppLocalServiceUtil} to access the wedeploy auth app local service.
	 */

	public WeDeployAuthApp addApplicationClient(
			long userId, String name, long companyId,
			ServiceContext serviceContext)
			throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		long weDeployAuthAppId = counterLocalService.increment();

		WeDeployAuthApp weDeployAuthApp = _weDeployAuthAppPersistence.create(
				weDeployAuthAppId);

		weDeployAuthApp.setCompanyId(user.getCompanyId());
		weDeployAuthApp.setUserId(user.getUserId());
		weDeployAuthApp.setUserName(user.getFullName());
		weDeployAuthApp.setCreateDate(serviceContext.getCreateDate(now));
		weDeployAuthApp.setModifiedDate(serviceContext.getModifiedDate(now));
		weDeployAuthApp.setName(name);

		String clientId = serviceContext.getUuid();

		if (Validator.isNull(clientId)) {
			clientId = PortalUUIDUtil.generate();
		}

		weDeployAuthApp.setClientId(clientId);

		weDeployAuthApp.setClientSecret(randomizeToken(clientId));

		_weDeployAuthAppPersistence.update(weDeployAuthApp, serviceContext);

		// Resources

		resourceLocalService.addModelResources(
				weDeployAuthApp, serviceContext);

		return weDeployAuthApp;
	}

	/**
	 * Returns the authorization code.
	 *
	 * @return authorization code
	 */
	public String generateAuthorizationCode(String clientId)
			throws PortalException, SystemException {

		return _getAuthorizationCode(clientId);
	}

	/**
	 * Returns the access token.
	 *
	 * @return access token
	 */
	public String generateAccessToken(String clientId, String clientSecret,
									 String
			authorizationCode) {

		WeDeployAuthApp weDeployAuthApp =
				_weDeployAuthAppPersistence.fetchByClientId(clientId);

		if (weDeployAuthApp == null) {
			//generate client not found error
		}

		if (!clientSecret.equalsIgnoreCase(weDeployAuthApp.getClientSecret())) {
			//generate secret does not match error
		}

		return randomizeToken(clientId.concat(authorizationCode));
	}

	private String _getAuthorizationCode(String clientId) {

		String authorizationCode = randomizeToken(clientId);

		return authorizationCode;
	}

	private String randomizeToken(String token) {
		return DigesterUtil.digestHex(
				Digester.MD5, token, PwdGenerator.getPassword());
	}

	private WeDeployAuthAppPersistence _weDeployAuthAppPersistence;
}