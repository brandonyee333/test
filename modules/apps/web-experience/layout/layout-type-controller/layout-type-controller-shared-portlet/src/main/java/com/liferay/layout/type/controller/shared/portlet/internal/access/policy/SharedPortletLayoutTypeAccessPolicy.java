/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.layout.type.controller.shared.portlet.internal.access.policy;

import com.liferay.layout.type.controller.shared.portlet.internal.constants.SharedPortletLayoutTypeControllerConstants;
import com.liferay.portal.kernel.model.LayoutTypeAccessPolicy;
import com.liferay.portal.kernel.model.impl.DefaultLayoutTypeAccessPolicyImpl;

import org.osgi.service.component.annotations.Component;

/**
 * @author     Leonardo Barros
 * @deprecated As of Judson (7.1.x), with no direct replacement
 */
@Component(
	property = "layout.type=" + SharedPortletLayoutTypeControllerConstants.LAYOUT_TYPE_SHARED_PORTLET,
	service = LayoutTypeAccessPolicy.class
)
@Deprecated
public class SharedPortletLayoutTypeAccessPolicy
	extends DefaultLayoutTypeAccessPolicyImpl {
}