/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.apache.bridges.struts;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portlet.PortletResponseImpl;
import com.liferay.portlet.PortletURLImplWrapper;

import org.apache.portals.bridges.struts.StrutsPortletURL;

/**
 * @author Michael Young
 */
public class LiferayStrutsPortletURLImpl extends PortletURLImplWrapper {

	public LiferayStrutsPortletURLImpl(
		PortletResponseImpl portletResponseImpl, long plid, String lifecycle) {

		super(portletResponseImpl, plid, lifecycle);
	}

	@Override
	public void setParameter(String name, String value) {
		super.setParameter(name, value);

		// Add parameters from the query string because bridges passes these
		// through instead of setting them on the portlet URL

		String decodedValue = HttpUtil.decodeURL(value);

		try {
			if (name.equals(StrutsPortletURL.PAGE)) {
				String[] urlComponents = decodedValue.split("\\?", 2);

				if (urlComponents.length != 2) {
					return;
				}

				String[] nameValueArray = urlComponents[1].split("\\&");

				for (String nameValue : nameValueArray) {
					String[] nameValuePair = nameValue.split("\\=", 2);

					if (nameValuePair.length == 2) {
						super.setParameter(nameValuePair[0], nameValuePair[1]);
					}
					else if (nameValuePair.length == 1) {
						super.setParameter(nameValuePair[0], "true");
					}
				}
			}
		}
		catch (Throwable t) {
			_log.error("Could not parse Struts page query string " + value, t);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LiferayStrutsPortletURLImpl.class);

}