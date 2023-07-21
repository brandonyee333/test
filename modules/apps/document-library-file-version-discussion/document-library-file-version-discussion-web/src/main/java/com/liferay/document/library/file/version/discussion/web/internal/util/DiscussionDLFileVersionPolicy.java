/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.document.library.file.version.discussion.web.internal.util;

import com.liferay.document.library.kernel.model.DLFileVersion;
import com.liferay.document.library.kernel.util.DLFileVersionPolicy;
import com.liferay.portal.kernel.comment.CommentManager;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Adolfo Pérez
 */
@Component(immediate = true)
public class DiscussionDLFileVersionPolicy implements DLFileVersionPolicy {

	@Override
	public boolean isKeepFileVersionLabel(
			DLFileVersion lastDLFileVersion, DLFileVersion latestDLFileVersion,
			boolean majorVersion, ServiceContext serviceContext)
		throws PortalException {

		int commentsCount = _commentManager.getCommentsCount(
			DLFileVersion.class.getName(),
			latestDLFileVersion.getFileVersionId());

		if (commentsCount == 0) {
			return true;
		}

		return false;
	}

	@Reference(unbind = "-")
	protected void setCommentManager(CommentManager commentManager) {
		_commentManager = commentManager;
	}

	private CommentManager _commentManager;

}