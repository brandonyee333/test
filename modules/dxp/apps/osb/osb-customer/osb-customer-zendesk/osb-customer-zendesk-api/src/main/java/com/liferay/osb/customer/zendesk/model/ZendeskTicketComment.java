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