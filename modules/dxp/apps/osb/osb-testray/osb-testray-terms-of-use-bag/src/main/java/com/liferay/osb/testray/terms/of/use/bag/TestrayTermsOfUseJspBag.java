/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.testray.terms.of.use.bag;

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
		"context.id=TestrayTermsOfUseJspBag",
		"context.name=Testray Terms of Use JSP Bag",
		"service.ranking:Integer=100"
	},
	service = CustomJspBag.class
)
public class TestrayTermsOfUseJspBag
	extends BaseCustomJspBag implements CustomJspBag {

	@Activate
	@Override
	protected void activate(BundleContext bundleContext) {
		super.activate(bundleContext);
	}

}