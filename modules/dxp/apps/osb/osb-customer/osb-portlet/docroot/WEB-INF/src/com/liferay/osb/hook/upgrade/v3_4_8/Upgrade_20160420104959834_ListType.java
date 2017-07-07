/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.osb.hook.upgrade.v3_4_8;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/*import com.liferay.osb.hook.upgrade.BaseUpgradeProcess;
import com.liferay.osb.model.TicketEntryConstants;

*/

/**
 * @author Jeremy Fu
 */
public class Upgrade_20160420104959834_ListType extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
	}

/*

	@Override
	public long getTimestamp() {
		return 20160420104959834L;
	}

	@Override
	protected void doUpgrade() throws Exception {
		insertListType(
			TicketEntryConstants.
				COMPONENT_ACTIVATION_KEY_PRODUCT_ADMINISTRATION,
			"activation-key-project-administration",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_ALERTS_ANNOUNCEMENTS,
			"alerts-announcements", TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_APPLICATION_DISPLAY_TEMPLATES,
			"application-display-templates",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_ASSET_PUBLISHER_FRAMEWORK,
			"asset-publisher-framework",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_AUDIENCE_TARGETING,
			"audience-targeting", TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_BLADE, "blade",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_BLOGS, "blogs",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_CACHING_CLUSTERING,
			"caching-clustering", TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_CATEGORIES_TAGS_VOCABULARIES,
			"categories-tags-vocabularies",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_DEPLOYMENT_ENVIRONMENTS,
			"deployment-environments",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_MOBILE_DEVICE_DETECTION,
			"device-recognition", TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_DOCUMENT_MANAGEMENT,
			"document-management", TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_DYNAMIC_DATA_MAPPING_DATA_LISTS,
			"dynamic-data-mapping-data-lists",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_ENTERPRISE_SEARCH,
			"enterprise-search", TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_FORM_VALIDATION, "form-validation",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_LCS_CLIENT, "lcs-client",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_LIFERAY_PUSH, "liferay-push",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_LIFERAY_SCREENS, "liferay-screens",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_LIFERAY_SCREENS_CONNECTOR,
			"liferay-screens-connector",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_LIFERAY_WORKSPACE,
			"liferay-workspace", TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_MAVEN, "maven",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_MESSAGE_BOARDS, "message-boards",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_NAVIGATION_BREADCRUMBS,
			"navigation-breadcrumbs", TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_PERFORMANCE, "performance",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_PORTAL_CONFIGURATION,
			"portal-configuration", TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_PORTAL_SERVICES, "portal-services",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_RECYCLE_BIN, "recycle-bin",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_REPORTING, "reporting",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.
				COMPONENT_SEARCH_ENGINE_OPTIMIZATIONS_ANALYTICS,
			"search-engine-optimizations-analytics",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.
				COMPONENT_SITE_ADMINISTRATION_PAGE_MANAGEMENT,
			"site-administration-page-management",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_SOCIAL_NETWORKING,
			"social-networking", TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_STAGING_EXPORT_IMPORT,
			"staging-export-import", TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_SYNC_CONNECTOR, "sync-connector",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_SYNC_DESKTOP, "sync-desktop",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_SYNC_MOBILE, "sync-mobile",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_UI_INFRASTRUCTURE_ACCESSIBILITY,
			"ui-infrastructure-accessibility",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_USER_MEMBERSHIP_ROLE_MANAGEMENT,
			"user-membership-role-management",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_WEB_CONTENT_ADMINISTRATION,
			"web-content-administration",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_WEB_FORMS, "web-forms",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_WIKI, "wiki",
			TicketEntryConstants.LIST_TYPE_COMPONENT);
		insertListType(
			TicketEntryConstants.COMPONENT_WORKFLOW_WORKFLOW_FORMS,
			"workflow-workflow-forms",
			TicketEntryConstants.LIST_TYPE_COMPONENT);

		runSQL(
			"update ListType set name = 'liferay-developer-studio' where " +
				"listTypeId = " +
					TicketEntryConstants.COMPONENT_LIFERAY_DEVELOPER_STUDIO);
		runSQL(
			"update ListType set name = 'project-administration' where " +
				"listTypeId = " +
					TicketEntryConstants.COMPONENT_PROJECT_ADMINISTRATION);
	}

}
*/

}