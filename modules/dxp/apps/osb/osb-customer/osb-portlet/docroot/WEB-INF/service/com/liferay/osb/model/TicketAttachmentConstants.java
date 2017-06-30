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

package com.liferay.osb.model;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Kyle Bischof
 */
public class TicketAttachmentConstants {

	public static final long EXTRACT_FILE_SIZE_LIMIT = 1048576;

	public static final long TICKET_ENTRY_DEFAULT_ID = 0;

	public static final int TYPE_DATA_FOLDER = 10;

	public static final int TYPE_DATABASE = 9;

	public static final int TYPE_HOTFIX = 1;

	public static final int TYPE_LARGE_FILE = 11;

	public static final int TYPE_LARGE_HOTFIX = 12;

	public static final int TYPE_NEW_PATCH_LEVEL = 8;

	public static final int TYPE_NEW_PORTAL_EXT = 7;

	public static final int TYPE_NONE = 0;

	public static final int TYPE_PATCH_LEVEL = 3;

	public static final int TYPE_PORTAL_EXT = 2;

	public static final int TYPE_SCREEN_SHOT = 4;

	public static final int TYPE_SERVER_LOG = 5;

	public static final int TYPE_SOCIAL_OFFICE_PLUGIN = 6;

	public static final int[] TYPES = {
		TYPE_DATA_FOLDER, TYPE_DATABASE, TYPE_HOTFIX, TYPE_NEW_PATCH_LEVEL,
		TYPE_NEW_PORTAL_EXT, TYPE_NONE, TYPE_PATCH_LEVEL, TYPE_PORTAL_EXT,
		TYPE_SCREEN_SHOT, TYPE_SERVER_LOG, TYPE_SOCIAL_OFFICE_PLUGIN
	};

	public static final int[] TYPES_HOTFIX = {
		TYPE_HOTFIX, TYPE_LARGE_HOTFIX
	};

	public static final int[] TYPES_LARGE = {
		TYPE_LARGE_FILE, TYPE_LARGE_HOTFIX
	};

	public static String getTypeLabel(int type) {
		if (type == TYPE_DATA_FOLDER) {
			return "data-folder-image-gallery";
		}
		else if (type == TYPE_DATABASE) {
			return "database";
		}
		else if (type == TYPE_HOTFIX) {
			return "hotfix";
		}
		else if (type == TYPE_NEW_PATCH_LEVEL) {
			return "new-patch-level";
		}
		else if (type == TYPE_NEW_PORTAL_EXT) {
			return "new-portal-ext";
		}
		else if (type == TYPE_PATCH_LEVEL) {
			return "patch-level";
		}
		else if (type == TYPE_PORTAL_EXT) {
			return "portal-ext";
		}
		else if (type == TYPE_LARGE_FILE) {
			return "large-file";
		}
		else if (type == TYPE_LARGE_HOTFIX) {
			return "large-hotfix";
		}
		else if (type == TYPE_SCREEN_SHOT) {
			return "screen-shot";
		}
		else if (type == TYPE_SERVER_LOG) {
			return "server-log";
		}
		else if (type == TYPE_SOCIAL_OFFICE_PLUGIN) {
			return "social-office-plugin";
		}
		else {
			return StringPool.BLANK;
		}
	}

	public static String getTypeString(int type) {
		if (type == TYPE_DATA_FOLDER) {
			return "dataFolder";
		}
		else if (type == TYPE_DATABASE) {
			return "database";
		}
		else if (type == TYPE_HOTFIX) {
			return "hotfix";
		}
		else if (type == TYPE_NEW_PATCH_LEVEL) {
			return "toPatchLevel";
		}
		else if (type == TYPE_NEW_PORTAL_EXT) {
			return "toPortalExt";
		}
		else if (type == TYPE_PATCH_LEVEL) {
			return "patchLevel";
		}
		else if (type == TYPE_PORTAL_EXT) {
			return "portalExt";
		}
		else if (type == TYPE_SCREEN_SHOT) {
			return "screenshot";
		}
		else if (type == TYPE_SERVER_LOG) {
			return "serverLog";
		}
		else if (type == TYPE_SOCIAL_OFFICE_PLUGIN) {
			return "socialOfficePlugin";
		}
		else {
			return StringPool.BLANK;
		}
	}

}