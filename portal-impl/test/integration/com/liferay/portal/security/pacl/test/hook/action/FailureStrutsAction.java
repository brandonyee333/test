/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.security.pacl.test.hook.action;

import com.liferay.portal.kernel.struts.BaseStrutsAction;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Brian Wing Shun Chan
 */
public class FailureStrutsAction extends BaseStrutsAction {

	public static boolean isInstantiated() {
		return _instantiated.get();
	}

	public FailureStrutsAction() {
		_instantiated.set(true);
	}

	@Override
	public String execute(
			HttpServletRequest request, HttpServletResponse response)
		throws Exception {

		return null;
	}

	private static final AtomicBoolean _instantiated = new AtomicBoolean();

}