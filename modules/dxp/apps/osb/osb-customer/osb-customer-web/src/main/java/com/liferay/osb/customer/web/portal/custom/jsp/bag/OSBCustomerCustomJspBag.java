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

package com.liferay.osb.customer.web.portal.custom.jsp.bag;

import com.liferay.portal.custom.jsp.bag.BaseCustomJspBag;
import com.liferay.portal.deploy.hot.CustomJspBag;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jenny Chen
 */
@Component(
	immediate = true,
	property = {
		"context.id=OSBCustomerCustomJspBag",
		"context.name=OSB Customer Custom JSP Bag", "service.ranking:Integer=2"
	}
)
public class OSBCustomerCustomJspBag
	extends BaseCustomJspBag implements CustomJspBag {
}