/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.taglib.ui;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Juan Fernández
 * @author Shuyang Zhou
 */
public class InputAssetLinksTag extends AssetLinksTag {

	@Override
	protected String getPage() {
		return _PAGE;
	}

	@Override
	protected void setAttributes(HttpServletRequest request) {
		long assetEntryId = getAssetEntryId();
		String className = getClassName();
		long classPK = getClassPK();

		if ((assetEntryId <= 0) && (classPK > 0)) {
			try {
				AssetEntry assetEntry = AssetEntryLocalServiceUtil.fetchEntry(
					className, classPK);

				if (assetEntry != null) {
					assetEntryId = assetEntry.getEntryId();
				}
			}
			catch (SystemException se) {

				// LPS-52675

				if (_log.isDebugEnabled()) {
					_log.debug(se, se);
				}
			}
		}

		request.setAttribute(
			"liferay-ui:input-asset-links:assetEntryId",
			String.valueOf(assetEntryId));
		request.setAttribute(
			"liferay-ui:input-asset-links:className", className);
	}

	private static final String _PAGE =
		"/html/taglib/ui/input_asset_links/page.jsp";

	private static final Log _log = LogFactoryUtil.getLog(
		InputAssetLinksTag.class);

}