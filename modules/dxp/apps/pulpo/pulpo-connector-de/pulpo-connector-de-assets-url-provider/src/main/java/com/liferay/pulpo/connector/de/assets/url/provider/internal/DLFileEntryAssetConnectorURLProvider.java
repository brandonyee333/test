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

package com.liferay.pulpo.connector.de.assets.url.provider.internal;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.model.AssetRenderer;
import com.liferay.document.library.kernel.model.DLFileEntryConstants;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.FileVersion;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.util.PropsValues;
import com.liferay.pulpo.connector.de.assets.url.provider.AssetConnectorURLProvider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(service = AssetConnectorURLProvider.class)
public class DLFileEntryAssetConnectorURLProvider
	implements AssetConnectorURLProvider {

	@Override
	public String getClassName() {
		return DLFileEntryConstants.getClassName();
	}

	@Override
	public String getURL(AssetEntry assetEntry) {
		try {
			AssetRenderer assetRenderer = assetEntry.getAssetRenderer();

			if (assetRenderer == null) {
				return StringPool.BLANK;
			}

			FileEntry fileEntry = (FileEntry)assetRenderer.getAssetObject();

			FileVersion fileVersion = fileEntry.getFileVersion();

			String downloadURL = DLUtil.getDownloadURL(
				fileEntry, fileVersion, null, StringPool.BLANK, false, false);

			LayoutSet layoutSet = _layoutSetLocalService.getLayoutSet(
				fileEntry.getGroupId(), false);

			boolean secure = StringUtil.equalsIgnoreCase(
				Http.HTTPS, PropsValues.WEB_SERVER_PROTOCOL);

			StringBundler sb = new StringBundler(3);

			if (secure) {
				sb.append(Http.HTTPS_WITH_SLASH);
			}
			else {
				sb.append(Http.HTTP_WITH_SLASH);
			}

			sb.append(_portal.getVirtualHostname(layoutSet));
			sb.append(downloadURL);

			return sb.toString();
		}
		catch (Exception e) {
			_log.error("Unable to get file entry URL", e);
		}

		return StringPool.BLANK;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DLFileEntryAssetConnectorURLProvider.class);

	@Reference
	private LayoutSetLocalService _layoutSetLocalService;

	@Reference
	private Portal _portal;

}