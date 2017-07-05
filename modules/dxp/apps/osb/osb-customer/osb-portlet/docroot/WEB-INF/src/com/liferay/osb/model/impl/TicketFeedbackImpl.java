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

import com.liferay.portal.kernel.util.Time;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketFeedbackConstants;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.Date;

/**
 * @author Brian Wing Shun Chan
 * @author Mate Thurzo
 */
public class TicketFeedbackImpl extends TicketFeedbackBaseImpl {

	public TicketFeedbackImpl() {
	}

	public String getAnswer1Label() {
		return TicketFeedbackConstants.getAnswerLabel(getAnswer1());
	}

	public String getAnswer2Label() {
		return TicketFeedbackConstants.getAnswerLabel(getAnswer2());
	}

	public String getAnswer3Label() {
		return TicketFeedbackConstants.getAnswerLabel(getAnswer3());
	}

	public double getAverageRating() {
		int totalRatings =
			getRating1() + getRating2() + getRating3() + getRating4();

		return totalRatings / 4.0;
	}

	public String getAverageRatingDisplay() {
		if ((getStatus() == TicketFeedbackConstants.STATUS_ANSWERED) &&
			(getSatisfied() ==
				TicketFeedbackConstants.SATISFIED_NOT_APPLICABLE)) {

			double averageRating = getAverageRating();

			return averageRating + " / 5.0";
		}
		else {
			return "N/A";
		}
	}

	public String getRating1Label() {
		return TicketFeedbackConstants.getRatingLabel(getRating1());
	}

	public String getRating2Label() {
		return TicketFeedbackConstants.getRatingLabel(getRating2());
	}

	public String getRating3Label() {
		return TicketFeedbackConstants.getRatingLabel(getRating3());
	}

	public String getRating4Label() {
		return TicketFeedbackConstants.getRatingLabel(getRating4());
	}

	public String getSatisfiedLabel() {
		return TicketFeedbackConstants.getSatisfiedLabel(getSatisfied());
	}

	public TicketEntry getTicketEntry()
		throws PortalException, SystemException {

		return TicketEntryLocalServiceUtil.getTicketEntry(getTicketEntryId());
	}

	public boolean isClosed() {
		long now = System.currentTimeMillis();

		Date createDate = getCreateDate();

		if ((now - createDate.getTime()) > (30 * Time.DAY)) {
			return true;
		}
		else {
			return false;
		}
	}

}