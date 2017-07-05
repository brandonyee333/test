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

package com.liferay.osb.service.impl;

import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.osb.exception.TicketCannedResponseContentException;
import com.liferay.osb.exception.TicketCannedResponseNameException;
import com.liferay.osb.model.TicketCannedResponse;
import com.liferay.osb.service.base.TicketCannedResponseLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.model.User;

import java.util.Date;
import java.util.List;

/**
 * @author Amos Fong
 */
public class TicketCannedResponseLocalServiceImpl
	extends TicketCannedResponseLocalServiceBaseImpl {

	public TicketCannedResponse addTicketCannedResponse(
			long userId, String defaultLanguageId, String name, String content)
		throws PortalException, SystemException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		validate(0, name, content);

		String nameXml = LocalizationUtil.updateLocalization(
			StringPool.BLANK, "static-content", name, defaultLanguageId,
			defaultLanguageId, true, true);

		String contentXml = LocalizationUtil.updateLocalization(
			StringPool.BLANK, "static-content", content, defaultLanguageId,
			defaultLanguageId, true, true);

		long ticketCannedResponseId = counterLocalService.increment();

		TicketCannedResponse ticketCannedResponse =
			ticketCannedResponsePersistence.create(ticketCannedResponseId);

		ticketCannedResponse.setUserId(user.getUserId());
		ticketCannedResponse.setUserName(user.getFullName());
		ticketCannedResponse.setCreateDate(now);
		ticketCannedResponse.setModifiedDate(now);
		ticketCannedResponse.setName(nameXml);
		ticketCannedResponse.setContent(contentXml);

		ticketCannedResponsePersistence.update(ticketCannedResponse, false);

		return ticketCannedResponse;
	}

	public void incrementUseCount(long ticketCannedResponseId)
		throws PortalException, SystemException {

		TicketCannedResponse ticketCannedResponse =
			ticketCannedResponsePersistence.findByPrimaryKey(
				ticketCannedResponseId);

		int useCount = ticketCannedResponse.getUseCount();

		ticketCannedResponse.setUseCount(useCount + 1);

		ticketCannedResponsePersistence.update(ticketCannedResponse, false);
	}

	public void removeCannedResponseLocale(
			long ticketCannedResponseId, String languageId)
		throws PortalException, SystemException {

		TicketCannedResponse ticketCannedResponse =
			ticketCannedResponsePersistence.findByPrimaryKey(
				ticketCannedResponseId);

		String defaultLanguageId = ticketCannedResponse.getDefaultLocale();

		if (languageId.equalsIgnoreCase(defaultLanguageId)) {
			throw new TicketCannedResponseNameException();
		}

		String nameXml = ticketCannedResponse.getName();
		String contentXml = ticketCannedResponse.getContent();

		nameXml = LocalizationUtil.removeLocalization(
			nameXml, "static-content", languageId);
		contentXml = LocalizationUtil.removeLocalization(
			contentXml, "static-content", languageId);

		ticketCannedResponse.setModifiedDate(new Date());
		ticketCannedResponse.setName(nameXml);
		ticketCannedResponse.setContent(contentXml);

		ticketCannedResponsePersistence.update(ticketCannedResponse, false);
	}

	public List<TicketCannedResponse> search(
			String keywords, int start, int end)
		throws SystemException {

		return ticketCannedResponseFinder.findByKeywords(keywords, start, end);
	}

	public List<TicketCannedResponse> search(
			String name, String content, boolean andSearch, int start, int end)
		throws SystemException {

		return ticketCannedResponseFinder.findByN_C(
			name, content, andSearch, start, end);
	}

	public int searchCount(String keywords) throws SystemException {
		return ticketCannedResponseFinder.countByKeywords(keywords);
	}

	public int searchCount(String name, String content, boolean andSearch)
		throws SystemException {

		return ticketCannedResponseFinder.countByN_C(name, content, andSearch);
	}

	public TicketCannedResponse updateTicketCannedResponse(
			long ticketCannedResponseId, String defaultLanguageId,
			String languageId, String name, String content)
		throws PortalException, SystemException {

		if (Validator.isNull(defaultLanguageId)) {
			defaultLanguageId = languageId;
		}

		validate(ticketCannedResponseId, name, content);

		TicketCannedResponse ticketCannedResponse =
			ticketCannedResponsePersistence.findByPrimaryKey(
				ticketCannedResponseId);

		String nameXml = LocalizationUtil.updateLocalization(
			ticketCannedResponse.getName(), "static-content", name, languageId,
			defaultLanguageId, true, true);

		String contentXml = LocalizationUtil.updateLocalization(
			ticketCannedResponse.getContent(), "static-content", content,
			languageId, defaultLanguageId, true, true);

		ticketCannedResponse.setModifiedDate(new Date());
		ticketCannedResponse.setName(nameXml);
		ticketCannedResponse.setContent(contentXml);

		ticketCannedResponsePersistence.update(ticketCannedResponse, false);

		return ticketCannedResponse;
	}

	protected void validate(
			long ticketCannedResponseId, String name, String content)
		throws PortalException {

		if (Validator.isNull(name)) {
			throw new TicketCannedResponseNameException();
		}

		if (Validator.isNull(content)) {
			throw new TicketCannedResponseContentException();
		}
	}

}