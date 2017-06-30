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

import com.liferay.compat.portal.kernel.util.ListUtil;
import com.liferay.osb.model.AssetAttachment;
import com.liferay.osb.service.base.AssetAttachmentServiceBaseImpl;
import com.liferay.osb.service.permission.OSBAssetAttachmentPermission;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.jsonwebservice.JSONWebServiceMode;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.Iterator;
import java.util.List;

/**
 * @author Ryan Park
 * @author Joan Kim
 */
@JSONWebService(mode = JSONWebServiceMode.MANUAL)
public class AssetAttachmentServiceImpl extends AssetAttachmentServiceBaseImpl {

	public AssetAttachment fetchAssetAttachment(long assetAttachmentId)
		throws PortalException, SystemException {

		AssetAttachment assetAttachment =
			assetAttachmentLocalService.fetchAssetAttachment(assetAttachmentId);

		if (assetAttachment == null) {
			return null;
		}

		OSBAssetAttachmentPermission.check(
			getPermissionChecker(), assetAttachment, OSBActionKeys.VIEW);

		return assetAttachment;
	}

	public List<AssetAttachment> getAssetAttachments(
			String className, long classPK)
		throws PortalException, SystemException {

		List<AssetAttachment> assetAttachments =
			assetAttachmentLocalService.getAssetAttachments(className, classPK);

		return filterAssetAttachments(assetAttachments);
	}

	public List<AssetAttachment> getAssetAttachments(
			String className, long classPK, int type, int start, int end,
			OrderByComparator obc)
		throws PortalException, SystemException {

		List<AssetAttachment> assetAttachments =
			assetAttachmentLocalService.getAssetAttachments(
				className, classPK, type, start, end, obc);

		return filterAssetAttachments(assetAttachments);
	}

	protected List<AssetAttachment> filterAssetAttachments(
			List<AssetAttachment> assetAttachments)
		throws PortalException, SystemException {

		List<AssetAttachment> filteredAssetAttachments = ListUtil.copy(
			assetAttachments);

		Iterator<AssetAttachment> itr = filteredAssetAttachments.iterator();

		while (itr.hasNext()) {
			AssetAttachment assetAttachment = itr.next();

			if (!OSBAssetAttachmentPermission.contains(
					getPermissionChecker(), assetAttachment,
					OSBActionKeys.VIEW)) {

				itr.remove();
			}
		}

		return filteredAssetAttachments;
	}

}