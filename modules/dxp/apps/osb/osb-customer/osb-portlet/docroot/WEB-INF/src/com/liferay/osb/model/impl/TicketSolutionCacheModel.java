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

import com.liferay.osb.model.TicketSolution;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing TicketSolution in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TicketSolution
 * @generated
 */
public class TicketSolutionCacheModel implements CacheModel<TicketSolution>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(41);

		sb.append("{ticketSolutionId=");
		sb.append(ticketSolutionId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", ticketEntryId=");
		sb.append(ticketEntryId);
		sb.append(", summary=");
		sb.append(summary);
		sb.append(", useCustomerSummary=");
		sb.append(useCustomerSummary);
		sb.append(", issueType=");
		sb.append(issueType);
		sb.append(", solution=");
		sb.append(solution);
		sb.append(", type=");
		sb.append(type);
		sb.append(", customerSpecific=");
		sb.append(customerSpecific);
		sb.append(", environmentSpecific=");
		sb.append(environmentSpecific);
		sb.append(", versionSpecific=");
		sb.append(versionSpecific);
		sb.append(", reviewForKB=");
		sb.append(reviewForKB);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append(", statusMessage=");
		sb.append(statusMessage);
		sb.append(", statusReason=");
		sb.append(statusReason);
		sb.append("}");

		return sb.toString();
	}

	public TicketSolution toEntityModel() {
		TicketSolutionImpl ticketSolutionImpl = new TicketSolutionImpl();

		ticketSolutionImpl.setTicketSolutionId(ticketSolutionId);
		ticketSolutionImpl.setUserId(userId);

		if (userName == null) {
			ticketSolutionImpl.setUserName(StringPool.BLANK);
		}
		else {
			ticketSolutionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			ticketSolutionImpl.setCreateDate(null);
		}
		else {
			ticketSolutionImpl.setCreateDate(new Date(createDate));
		}

		ticketSolutionImpl.setTicketEntryId(ticketEntryId);

		if (summary == null) {
			ticketSolutionImpl.setSummary(StringPool.BLANK);
		}
		else {
			ticketSolutionImpl.setSummary(summary);
		}

		ticketSolutionImpl.setUseCustomerSummary(useCustomerSummary);
		ticketSolutionImpl.setIssueType(issueType);

		if (solution == null) {
			ticketSolutionImpl.setSolution(StringPool.BLANK);
		}
		else {
			ticketSolutionImpl.setSolution(solution);
		}

		ticketSolutionImpl.setType(type);
		ticketSolutionImpl.setCustomerSpecific(customerSpecific);
		ticketSolutionImpl.setEnvironmentSpecific(environmentSpecific);
		ticketSolutionImpl.setVersionSpecific(versionSpecific);
		ticketSolutionImpl.setReviewForKB(reviewForKB);
		ticketSolutionImpl.setStatus(status);
		ticketSolutionImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			ticketSolutionImpl.setStatusByUserName(StringPool.BLANK);
		}
		else {
			ticketSolutionImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			ticketSolutionImpl.setStatusDate(null);
		}
		else {
			ticketSolutionImpl.setStatusDate(new Date(statusDate));
		}

		if (statusMessage == null) {
			ticketSolutionImpl.setStatusMessage(StringPool.BLANK);
		}
		else {
			ticketSolutionImpl.setStatusMessage(statusMessage);
		}

		ticketSolutionImpl.setStatusReason(statusReason);

		ticketSolutionImpl.resetOriginalValues();

		return ticketSolutionImpl;
	}

	public long ticketSolutionId;
	public long userId;
	public String userName;
	public long createDate;
	public long ticketEntryId;
	public String summary;
	public boolean useCustomerSummary;
	public int issueType;
	public String solution;
	public int type;
	public boolean customerSpecific;
	public boolean environmentSpecific;
	public boolean versionSpecific;
	public boolean reviewForKB;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;
	public String statusMessage;
	public int statusReason;
}