/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.testray.unnamespace.bag;

import com.liferay.portal.custom.jsp.bag.BaseCustomJspBag;
import com.liferay.portal.deploy.hot.CustomJspBag;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

/**
 * @author Ethan Bustad
 */
@Component(
	immediate = true,
	property = {
		"context.id=TestrayUnnamespaceJspBag",
		"context.name=Testray Unnamespace JSP Bag",
		"service.ranking:Integer=100"
	}
)
public class TestrayUnnamespaceJspBag
	extends BaseCustomJspBag implements CustomJspBag {

	@Activate
	@Override
	protected void activate(BundleContext bundleContext) {
		super.activate(bundleContext);
	}

}