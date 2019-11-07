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
import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.pulpo.connector.de.assets.url.provider.AssetConnectorURLProvider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Pavel Savinov
 */
@Component(service = AssetConnectorURLProvider.class)
public class BlogsEntryAssetConnectorURLProvider
	implements AssetConnectorURLProvider {

	@Override
	public String getClassName() {
		return BlogsEntry.class.getName();
	}

	@Override
	public String getURL(AssetEntry assetEntry) {
		AssetRenderer assetRenderer = assetEntry.getAssetRenderer();

		if (assetRenderer == null) {
			return StringPool.BLANK;
		}

		BlogsEntry blogsEntry = (BlogsEntry)assetRenderer.getAssetObject();

		String portletId = PortletProviderUtil.getPortletId(
			BlogsEntry.class.getName(), PortletProvider.Action.VIEW);

		if (Validator.isNull(portletId)) {
			return StringPool.BLANK;
		}

		try {
			String layoutURL = _portal.getLayoutFullURL(
				blogsEntry.getGroupId(), portletId);

			if (Validator.isNotNull(layoutURL)) {
				return layoutURL + Portal.FRIENDLY_URL_SEPARATOR + "blogs" +
					StringPool.SLASH + blogsEntry.getEntryId();
			}
		}
		catch (Exception e) {
			_log.error("Unable to get blogs entry URL", e);
		}

		return StringPool.BLANK;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BlogsEntryAssetConnectorURLProvider.class);

	@Reference
	private Portal _portal;

}