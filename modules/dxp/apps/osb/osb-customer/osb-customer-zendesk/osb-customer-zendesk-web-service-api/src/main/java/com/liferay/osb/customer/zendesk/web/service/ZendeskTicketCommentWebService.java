/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.osb.customer.zendesk.web.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.zendesk.model.ZendeskTicketComment;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Amos Fong
 */
@ProviderType
public interface ZendeskTicketCommentWebService {

	public ZendeskTicketComment addAgentZendeskTicketComment(
			long zendeskTicketId, long zendeskUserId, String body,
			boolean isPublic)
		throws PortalException;

	public void addEndUserZendeskTicketComment(
			long zendeskTicketId, String endUserEmailAddress, String body)
		throws PortalException;

	public List<ZendeskTicketComment> getZendeskTicketComments(
			long zendeskTicketId)
		throws PortalException;

}