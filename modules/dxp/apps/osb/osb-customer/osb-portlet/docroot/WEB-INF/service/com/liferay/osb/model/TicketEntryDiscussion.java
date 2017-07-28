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

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Alan Zhang
 */
public class TicketEntryDiscussion {

	public static final String TYPE_ATTACHMENT = "attachment";

	public static final String TYPE_COMMENT = "comment";

	public static final String TYPE_LINK = "link";

	public TicketEntryDiscussion() {
	}

	public void addTicketAttachment(TicketAttachment ticketAttachment) {
		_ticketAttachments.add(ticketAttachment);

		if (_ticketComment == null) {
			_createDate = ticketAttachment.getCreateDate();
			_key = ticketAttachment.getKey();
			_status = ticketAttachment.getStatus();
			_userId = ticketAttachment.getUserId();
			_userName = ticketAttachment.getUserName();
			_visibilityLabel = ticketAttachment.getVisibilityLabel();

			try {
				_user = UserLocalServiceUtil.fetchUser(_userId);
			}
			catch (SystemException se) {
				_log.error(se, se);
			}

			if (_discussionId <= 0) {
				_discussionId = ticketAttachment.getTicketAttachmentId();
				_discussionType = TYPE_ATTACHMENT;
			}
		}
	}

	public void addTicketLink(TicketLink ticketLink) {
		_ticketLinks.add(ticketLink);

		if (_ticketComment == null) {
			_createDate = ticketLink.getCreateDate();
			_key = ticketLink.getKey();
			_userId = ticketLink.getUserId();
			_userName = ticketLink.getUserName();
			_visibilityLabel = ticketLink.getVisibilityLabel();

			try {
				_user = UserLocalServiceUtil.fetchUser(_userId);
			}
			catch (SystemException se) {
				_log.error(se, se);
			}

			if (_discussionId <= 0) {
				_discussionId = ticketLink.getTicketLinkId();
				_discussionType = TYPE_LINK;
			}
		}
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public long getDiscussionId() {
		return _discussionId;
	}

	public String getDiscussionType() {
		return _discussionType;
	}

	public String getKey() {
		return _key;
	}

	public int getStatus() {
		return _status;
	}

	public List<TicketAttachment> getTicketAttachments() {
		return _ticketAttachments;
	}

	public TicketComment getTicketComment() {
		return _ticketComment;
	}

	public List<TicketLink> getTicketLinks() {
		return _ticketLinks;
	}

	public User getUser() {
		return _user;
	}

	public long getUserId() {
		return _userId;
	}

	public String getUserName() {
		if (_user != null) {
			return _user.getFullName();
		}
		else {
			return _userName;
		}
	}

	public String getUserPortraitURL(ThemeDisplay themeDisplay)
		throws PortalException {

		if (_user != null) {
			return _user.getPortraitURL(themeDisplay);
		}
		else {
			return UserConstants.getPortraitURL(
				themeDisplay.getPathImage(), true, 0);
		}
	}

	public String getVisibilityLabel() {
		return _visibilityLabel;
	}

	public boolean hasTicketComment() {
		if (_ticketComment != null) {
			return true;
		}
		else {
			return false;
		}
	}

	public void setTicketComment(TicketComment ticketComment) {
		_ticketComment = ticketComment;

		_createDate = ticketComment.getCreateDate();
		_discussionId = ticketComment.getTicketCommentId();
		_discussionType = TYPE_COMMENT;
		_key = ticketComment.getKey();
		_status = ticketComment.getStatus();
		_userId = ticketComment.getUserId();
		_userName = ticketComment.getUserName();
		_visibilityLabel = ticketComment.getVisibilityLabel();

		try {
			_user = UserLocalServiceUtil.fetchUser(_userId);
		}
		catch (SystemException se) {
			_log.error(se, se);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		TicketEntryDiscussion.class);

	private Date _createDate;
	private long _discussionId;
	private String _discussionType;
	private String _key;
	private int _status;
	private List<TicketAttachment> _ticketAttachments = new ArrayList<>();
	private TicketComment _ticketComment;
	private List<TicketLink> _ticketLinks = new ArrayList<>();
	private User _user;
	private long _userId;
	private String _userName;
	private String _visibilityLabel;

}