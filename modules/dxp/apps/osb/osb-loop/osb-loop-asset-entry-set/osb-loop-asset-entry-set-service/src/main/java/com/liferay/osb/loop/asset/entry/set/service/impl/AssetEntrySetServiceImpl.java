/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.entry.set.service.impl;

import com.liferay.osb.loop.asset.entry.set.service.base.AssetEntrySetServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;

import java.io.File;

/**
 * @author Brian Wing Shun Chan
 */
public class AssetEntrySetServiceImpl extends AssetEntrySetServiceBaseImpl {

	@Override
	public JSONObject addFileAttachment(File file) throws PortalException {
		return assetEntrySetLocalService.addFileAttachment(getUserId(), file);
	}

}