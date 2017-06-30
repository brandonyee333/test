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

package com.liferay.osb.service.impl;

import com.liferay.compat.portal.kernel.util.StringUtil;
import com.liferay.compat.portal.kernel.util.Validator;
import com.liferay.osb.AssetReceiptRedeemTokenEmailAddressException;
import com.liferay.osb.model.AssetReceiptRedeemToken;
import com.liferay.osb.service.base.AssetReceiptRedeemTokenLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.util.Date;

/**
 * @author Douglas Wong
 */
public class AssetReceiptRedeemTokenLocalServiceImpl
	extends AssetReceiptRedeemTokenLocalServiceBaseImpl {

	public AssetReceiptRedeemToken addAssetReceiptRedeemToken(
			long userId, long classNameId, long classPK,
			String redeemEmailAddress)
		throws PortalException, SystemException {

		User user = userLocalService.getUser(userId);
		Date now = new Date();

		long assetReceiptRedeemTokenId = counterLocalService.increment(
			AssetReceiptRedeemToken.class.getName());

		AssetReceiptRedeemToken assetReceiptRedeemToken =
			assetReceiptRedeemTokenPersistence.create(
				assetReceiptRedeemTokenId);

		assetReceiptRedeemToken.setUserId(user.getUserId());
		assetReceiptRedeemToken.setUserName(user.getFullName());
		assetReceiptRedeemToken.setCreateDate(now);
		assetReceiptRedeemToken.setClassNameId(classNameId);
		assetReceiptRedeemToken.setClassPK(classPK);
		assetReceiptRedeemToken.setRedeemEmailAddress(redeemEmailAddress);
		assetReceiptRedeemToken.setToken(getToken());

		assetReceiptRedeemTokenPersistence.update(
			assetReceiptRedeemToken, false);

		return assetReceiptRedeemToken;
	}

	public AssetReceiptRedeemToken fetchUnredeemedAssetReceiptRedeemToken(
			String redeemEmailAddress)
		throws SystemException {

		return assetReceiptRedeemTokenPersistence.fetchByREA_RD(
			redeemEmailAddress, null);
	}

	public AssetReceiptRedeemToken redeemAssetReceiptRedeemToken(
			String redeemEmailAddress)
		throws PortalException, SystemException {

		validate(redeemEmailAddress);

		Date now = new Date();

		AssetReceiptRedeemToken assetReceiptRedeemToken =
			assetReceiptRedeemTokenPersistence.fetchByREA_RD(
				redeemEmailAddress, null);

		assetReceiptRedeemToken.setRedeemDate(now);

		assetReceiptRedeemTokenPersistence.update(
			assetReceiptRedeemToken, false);

		return assetReceiptRedeemToken;
	}

	protected String getToken() throws SystemException {
		String token = StringUtil.randomString();

		AssetReceiptRedeemToken assetReceiptRedeemToken =
			assetReceiptRedeemTokenPersistence.fetchByToken(token);

		if (assetReceiptRedeemToken != null) {
			token = getToken();
		}

		return token;
	}

	protected void validate(String redeemEmailAddress)
		throws PortalException, SystemException {

		if (!Validator.isEmailAddress(redeemEmailAddress)) {
			throw new AssetReceiptRedeemTokenEmailAddressException();
		}
	}

}