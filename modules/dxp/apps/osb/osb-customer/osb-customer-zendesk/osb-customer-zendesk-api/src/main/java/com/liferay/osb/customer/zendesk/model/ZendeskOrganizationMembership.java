/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.model;

/**
 * @author Amos Fong
 */
public class ZendeskOrganizationMembership {

	public ZendeskOrganizationMembership() {
	}

	public long getZendeskOrganizationId() {
		return _zendeskOrganizationId;
	}

	public long getZendeskOrganizationMembershipId() {
		return _zendeskOrganizationMembershipId;
	}

	public long getZendeskUserId() {
		return _zendeskUserId;
	}

	public void setZendeskOrganizationId(long zendeskOrganizationId) {
		_zendeskOrganizationId = zendeskOrganizationId;
	}

	public void setZendeskOrganizationMembershipId(
		long zendeskOrganizationMembershipId) {

		_zendeskOrganizationMembershipId = zendeskOrganizationMembershipId;
	}

	public void setZendeskUserId(long zendeskUserId) {
		_zendeskUserId = zendeskUserId;
	}

	private long _zendeskOrganizationId;
	private long _zendeskOrganizationMembershipId;
	private long _zendeskUserId;

}