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
 * @author Sharon Li
 */
public class TicketLinkConstants {

	public static final int TYPE_COMMUNITY_RESOURCE = 1;

	public static final int TYPE_KNOWLEDGE_BASE_ARTICLE = 2;

	public static final int TYPE_NORMAL = 0;

	public static final int TYPE_OFFICIAL_DOCUMENTATION = 3;

	public static final int TYPE_OTHER = 4;

	public static String getTypeLabel(int type) {
		if (type == TYPE_COMMUNITY_RESOURCE) {
			return "community-resource";
		}
		else if (type == TYPE_KNOWLEDGE_BASE_ARTICLE) {
			return "knowledge-base-article";
		}
		else if (type == TYPE_OFFICIAL_DOCUMENTATION) {
			return "official-documentation";
		}
		else if (type == TYPE_OTHER) {
			return "other";
		}
		else {
			return StringPool.BLANK;
		}
	}

}