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

package com.liferay.osb.hook.events;

/* TODO update ecommerce integration
*/
/* TODO update assetReceipt integration
*/
/* TODO update corp integration
*/
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Douglas Wong
 */
public class OSBLoginPostAction extends Action {

	@Override
	public void run(HttpServletRequest request, HttpServletResponse response)
		throws ActionException {

/* TODO update assetReceipt integration
	TODO update corp integration
	TODO update ecommerce integration
		try {
			User user = PortalUtil.getUser(request);

			AssetReceiptRedeemToken assetReceiptRedeemToken =
				AssetReceiptRedeemTokenLocalServiceUtil.
					fetchUnredeemedAssetReceiptRedeemToken(
						user.getEmailAddress());

			if (assetReceiptRedeemToken == null) {
				return;
			}

			ECDocumentEntry ecDocumentEntry =
				ECDocumentEntryLocalServiceUtil.getECDocumentEntry(
					assetReceiptRedeemToken.getClassPK());

			ECDocumentEntryExtraSettings ecDocumentEntryExtraSettings =
				new ECDocumentEntryExtraSettings(ecDocumentEntry);

			CorpProjectLocalServiceUtil.addCorpProjectUsers(
				ecDocumentEntryExtraSettings.getOwnerClassPK(),
				new long[] {user.getUserId()});

			User resaleUser = UserLocalServiceUtil.getUser(
				ecDocumentEntry.getUserId());

			CorpProjectLocalServiceUtil.addUserCorpProjectRoles(
				resaleUser.getUserId(),
				ecDocumentEntryExtraSettings.getOwnerClassPK(),
				new long[] {user.getUserId()},
				OSBConstants.ROLE_OSB_CORP_ADMIN_ID);

			AssetReceiptRedeemTokenLocalServiceUtil.
				redeemAssetReceiptRedeemToken(user.getEmailAddress());
		}
		catch (Exception e) {
			throw new ActionException(e);
		}

*/
	}

}