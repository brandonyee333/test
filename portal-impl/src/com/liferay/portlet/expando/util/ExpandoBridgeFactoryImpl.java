/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.expando.util;

import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactory;
import com.liferay.portal.kernel.security.pacl.DoPrivileged;
import com.liferay.portal.security.lang.DoPrivilegedUtil;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

/**
 * @author Raymond Augé
 */
@DoPrivileged
public class ExpandoBridgeFactoryImpl implements ExpandoBridgeFactory {

	@Override
	public ExpandoBridge getExpandoBridge(long companyId, String className) {
		return DoPrivilegedUtil.wrap(
			new ExpandoBridgeImpl(companyId, className));
	}

	@Override
	public ExpandoBridge getExpandoBridge(
		long companyId, String className, long classPK) {

		return DoPrivilegedUtil.wrap(
			new ExpandoBridgeImpl(companyId, className, classPK));
	}

}