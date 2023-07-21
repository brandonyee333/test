/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.trash.web.internal.portlet;

import com.liferay.portal.kernel.portlet.BasePortletProvider;
import com.liferay.portal.kernel.portlet.ViewPortletProvider;
import com.liferay.trash.web.internal.constants.TrashPortletKeys;

import org.osgi.service.component.annotations.Component;

/**
 * Provides an implementation of <code>ViewPortletProvider</code> (in
 * <code>com.liferay.portal.kernel</code>) for the Recycle Bin portlet. This
 * implementation is aimed to generate instances of <code>PortletURL</code> (in
 * <code>javax.portlet</code> entities to provide URLs to view Recycle Bin
 * entries.
 *
 * @author Eudaldo Alonso
 */
@Component(
	immediate = true,
	property = "model.class.name=com.liferay.trash.kernel.model.TrashEntry",
	service = ViewPortletProvider.class
)
public class TrashViewPortletProvider
	extends BasePortletProvider implements ViewPortletProvider {

	@Override
	public String getPortletName() {
		return TrashPortletKeys.TRASH;
	}

}