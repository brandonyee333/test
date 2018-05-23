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

package com.liferay.lcs.advisor;

import com.liferay.lcs.util.LCSAlert;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Igor Beslic
 */
public class LCSAlertAdvisor {

	public boolean add(LCSAlert lcsAlert) {
		return _lcsAlerts.add(lcsAlert);
	}

	public void clear() {
		_lcsAlerts.clear();
	}

	public Set<LCSAlert> getLCSAlerts() {
		return _lcsAlerts;
	}

	public boolean remove(LCSAlert lcsAlert) {
		return _lcsAlerts.remove(lcsAlert);
	}

	private final Set<LCSAlert> _lcsAlerts = new HashSet<>();

}