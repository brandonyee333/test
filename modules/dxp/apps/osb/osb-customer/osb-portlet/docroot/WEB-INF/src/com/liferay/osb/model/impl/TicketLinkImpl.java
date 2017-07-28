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

package com.liferay.osb.model.impl;

import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

/**
 * @author Amos Fong
 */
public class TicketLinkImpl extends TicketLinkBaseImpl {

	public TicketLinkImpl() {
	}

	public String getKey() {
		StringBundler sb = new StringBundler(3);

		sb.append(getUserId());
		sb.append(StringPool.UNDERLINE);

		Date createDate = DateUtils.truncate(getCreateDate(), Calendar.SECOND);

		sb.append(createDate.getTime());

		return sb.toString();
	}

	public TicketEntry getTicketEntry() throws PortalException {
		return TicketEntryLocalServiceUtil.getTicketEntry(getTicketEntryId());
	}

	public String getVisibilityLabel() {
		return VisibilityConstants.toLabel(getVisibility());
	}

}