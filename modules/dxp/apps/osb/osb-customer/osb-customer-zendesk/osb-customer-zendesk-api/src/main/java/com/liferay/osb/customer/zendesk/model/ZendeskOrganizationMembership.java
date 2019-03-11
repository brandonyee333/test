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