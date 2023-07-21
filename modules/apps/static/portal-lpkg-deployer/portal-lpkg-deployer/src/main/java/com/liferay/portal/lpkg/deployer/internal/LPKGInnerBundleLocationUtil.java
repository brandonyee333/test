/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.lpkg.deployer.internal;

import org.osgi.framework.Bundle;

/**
 * @author Matthew Tambara
 */
public class LPKGInnerBundleLocationUtil {

	public static String generateInnerBundleLocation(
		Bundle lpkgBundle, String path) {

		String location = path.concat("?lpkgPath=");

		return location.concat(lpkgBundle.getLocation());
	}

}