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

package com.liferay.lcs.subscription;

/**
 * @author     Igor Beslic
 * @version    2.1.1
 * @since      LCS 1.3
 * @deprecated As of 10.0.0, Moved into osb-lcs-api module
 */
@Deprecated
public enum SubscriptionStatus {

	ACTIVE("subscription-status-active", 1),
	CLOSED("subscription-status-closed", 2),
	ON_HOLD("subscription-status-on-hold", 3),
	UNDEFINED("subscription-status-undefined", 0);

	public static SubscriptionStatus valueOf(int status) {
		for (SubscriptionStatus subscriptionStatus : values()) {
			if (status == subscriptionStatus.getStatus()) {
				return subscriptionStatus;
			}
		}

		return UNDEFINED;
	}

	public String getLabel() {
		return _label;
	}

	public int getStatus() {
		return _status;
	}

	private SubscriptionStatus(String label, int status) {
		_label = label;
		_status = status;
	}

	private final String _label;
	private final int _status;

}