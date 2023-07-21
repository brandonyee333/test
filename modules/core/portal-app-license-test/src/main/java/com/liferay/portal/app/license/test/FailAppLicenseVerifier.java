/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.app.license.test;

import com.liferay.portal.app.license.AppLicenseVerifier;

import org.osgi.framework.Bundle;

/**
 * @author Amos Fong
 */
public class FailAppLicenseVerifier implements AppLicenseVerifier {

	@Override
	public void verify(
			Bundle bundle, String productId, String productType,
			String productVersion)
		throws Exception {

		throw new Exception();
	}

}