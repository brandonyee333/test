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

package com.liferay.osb.model.impl;

import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CompanyConstants;
import com.liferay.portlet.documentlibrary.store.DLStoreUtil;

import java.io.InputStream;

/**
 * @author Ryan Park
 */
public class AssetAttachmentImpl extends AssetAttachmentBaseImpl {

	public AssetAttachmentImpl() {
	}

	public String getDir() {
		return "/marketplace/asset_attachments";
	}

	public InputStream getFileAsStream()
		throws PortalException, SystemException {

		return DLStoreUtil.getFileAsStream(
			OSBConstants.COMPANY_ID, CompanyConstants.SYSTEM, getPath());
	}

	public String getPath() {
		return getDir() + StringPool.SLASH + getAssetAttachmentId();
	}

}