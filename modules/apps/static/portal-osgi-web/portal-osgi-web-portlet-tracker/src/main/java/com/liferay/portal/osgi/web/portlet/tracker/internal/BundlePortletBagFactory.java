/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.osgi.web.portlet.tracker.internal;

import com.liferay.portlet.PortletBagFactory;

import javax.portlet.Portlet;

/**
 * @author Raymond Augé
 */
public class BundlePortletBagFactory extends PortletBagFactory {

	public BundlePortletBagFactory(Portlet portlet) {
		_portlet = portlet;
	}

	@Override
	protected Portlet getPortletInstance(
		com.liferay.portal.kernel.model.Portlet portletModel) {

		return _portlet;
	}

	private final Portlet _portlet;

}