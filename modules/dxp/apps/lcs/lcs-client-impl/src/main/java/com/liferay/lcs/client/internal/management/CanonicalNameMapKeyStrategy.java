/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.client.internal.management;

import javax.management.ObjectName;

/**
 * @author Riccardo Ferrari
 */
public class CanonicalNameMapKeyStrategy implements MapKeyStrategy {

	@Override
	public String getMapKey(ObjectName objectName) {
		return objectName.getCanonicalName();
	}

}