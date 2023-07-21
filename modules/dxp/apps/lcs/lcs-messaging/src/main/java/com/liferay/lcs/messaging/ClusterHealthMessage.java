/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.lcs.messaging;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivica Cardic
 */
public class ClusterHealthMessage extends HealthMessage {

	public List<String> getSiblingKeys() {
		return _siblingKeys;
	}

	public void setSiblingKeys(List<String> siblingKeys) {
		_siblingKeys = siblingKeys;
	}

	private List<String> _siblingKeys = new ArrayList<String>();

}