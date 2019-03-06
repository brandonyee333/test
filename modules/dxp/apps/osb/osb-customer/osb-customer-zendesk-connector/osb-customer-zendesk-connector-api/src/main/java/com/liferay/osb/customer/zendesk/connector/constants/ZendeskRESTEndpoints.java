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

package com.liferay.osb.customer.zendesk.connector.constants;

/**
 * @author Amos Fong
 */
public interface ZendeskRESTEndpoints {

	public static final String IDENTITIES = "/identities.json";

	public static final String INCREMENTAL_ARTICLES =
		"help_center/incremental/articles.json";

	public static final String INCREMENTAL_ORGANIZATIONS =
		"incremental/organizations.json";

	public static final String INCREMENTAL_TICKET_EVENTS = 
		"incremental/ticket_events.json";

	public static final String INCREMENTAL_TICKETS = "incremental/tickets.json";

	public static final String INCREMENTAL_USERS = "incremental/users.json";

	public static final String ORGANIZATION_MEMBERSHIPS_CREATE_MANY =
		"organization_memberships/create_many.json";

	public static final String ORGANIZATION_MEMBERSHIPS_DESTROY_MANY =
		"organization_memberships/destroy_many.json";

	public static final String ORGANIZATION_SUBSCRIPTIONS =
		"organization_subscriptions.json";

	public static final String ORGANIZATIONS = "organizations.json";

	public static final String ORGANIZATIONS_CREATE_OR_UPDATE =
		"organizations/create_or_update.json";

	public static final String TICKET_FIELDS = "ticket_fields.json";

	public static final String URL_API_V2 = "/api/v2/";

	public static final String USERS_CREATE_OR_UPDATE =
		"users/create_or_update.json";

}