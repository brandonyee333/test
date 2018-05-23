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