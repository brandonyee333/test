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

package com.liferay.lcs.portal;

/**
 * @author     Riccardo Ferrari
 * @deprecated As of 10.0.0, Moved into osb-lcs-api module
 */
@Deprecated
public enum SiteType {

	ORGANIZATION("organization-site", 0), SITE("site", 1);

	public static boolean isOrganization(int type) {
		if (ORGANIZATION.getType() == type) {
			return true;
		}

		return false;
	}

	public static boolean isSite(int type) {
		if (SITE.getType() == type) {
			return true;
		}

		return false;
	}

	public static SiteType toSiteType(int type) {
		for (SiteType siteType : values()) {
			if (type == siteType.getType()) {
				return siteType;
			}
		}

		return ORGANIZATION;
	}

	public String getLabel() {
		return _label;
	}

	public int getType() {
		return _type;
	}

	private SiteType(String label, int type) {
		_label = label;
		_type = type;
	}

	private final String _label;
	private final int _type;

}