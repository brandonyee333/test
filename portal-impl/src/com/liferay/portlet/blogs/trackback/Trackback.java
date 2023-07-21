/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portlet.blogs.trackback;

import com.liferay.blogs.kernel.model.BlogsEntry;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Function;
import com.liferay.portlet.blogs.linkback.LinkbackConsumer;

/**
 * @author André de Oliveira
 */
public interface Trackback {

	public void addTrackback(
			BlogsEntry entry, ThemeDisplay themeDisplay, String excerpt,
			String url, String blogName, String title,
			Function<String, ServiceContext> serviceContextFunction)
		throws PortalException;

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void setCommentManager(CommentManager commentManager);

	/**
	 * @deprecated As of Judson (7.1.x), with no direct replacement
	 */
	@Deprecated
	public void setLinkbackConsumer(LinkbackConsumer linkbackConsumer);

}