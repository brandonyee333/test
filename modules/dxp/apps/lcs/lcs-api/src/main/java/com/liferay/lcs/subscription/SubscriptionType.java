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
public enum SubscriptionType {

	BACKUP("backup"), CLUSTER("cluster"), DEVELOPER("developer"),
	DEVELOPER_CLUSTER("developer-cluster"), ELASTIC("elastic"),
	ENTERPRISE("enterprise"), LIMITED("limited"),
	NON_PRODUCTION("non-production"), OEM("oem"), PER_USER("per-user"),
	PRODUCTION("production"), TRIAL("trial"), UNDEFINED("no-subscriptions");

	public static SubscriptionType toSubscriptionType(String licenseEntryType) {
		if (licenseEntryType == null) {
			return UNDEFINED;
		}

		for (SubscriptionType subscriptionType : values()) {
			if (licenseEntryType.equals(
					subscriptionType.getLicenseEntryType())) {

				return subscriptionType;
			}
		}

		return UNDEFINED;
	}

	public String getLabel() {
		return _label;
	}

	public String getLicenseEntryType() {
		return _licenseEntryType;
	}

	private SubscriptionType(String licenseEntryType) {
		_licenseEntryType = licenseEntryType;

		_label = licenseEntryType;
	}

	private final String _label;
	private final String _licenseEntryType;

}