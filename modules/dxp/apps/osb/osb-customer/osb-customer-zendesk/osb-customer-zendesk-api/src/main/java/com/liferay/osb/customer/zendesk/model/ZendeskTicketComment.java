/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.model;

import java.util.List;

/**
 * @author Amos Fong
 */
public class ZendeskTicketComment {

	public ZendeskTicketComment() {
	}

	public List<ZendeskAttachment> getZendeskAttachments() {
		return _zendeskAttachments;
	}

	public long getZendeskTicketCommentId() {
		return _zendeskTicketCommentId;
	}

	public void setZendeskAttachments(
		List<ZendeskAttachment> zendeskAttachments) {

		_zendeskAttachments = zendeskAttachments;
	}

	public void setZendeskTicketCommentId(long zendeskTicketCommentId) {
		_zendeskTicketCommentId = zendeskTicketCommentId;
	}

	private List<ZendeskAttachment> _zendeskAttachments;
	private long _zendeskTicketCommentId;

}