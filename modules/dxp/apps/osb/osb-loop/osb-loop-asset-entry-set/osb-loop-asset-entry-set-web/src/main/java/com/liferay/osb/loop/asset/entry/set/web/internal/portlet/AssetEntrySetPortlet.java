/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.loop.asset.entry.set.web.internal.portlet;

import com.liferay.osb.loop.asset.entry.set.constants.AssetEntrySetPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author Calvin Keum
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=AssetEntrySet",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.name=" + AssetEntrySetPortletKeys.ASSET_ENTRY_SET,
		"javax.portlet.portlet.info.keywords=Asset Entry Set",
		"javax.portlet.portlet.info.short-title=Asset Entry Set",
		"javax.portlet.portlet.info.title=Asset Entry Set",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.supports.mime-type=text/html"
	},
	service = Portlet.class
)
public class AssetEntrySetPortlet extends MVCPortlet {
}