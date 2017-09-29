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

import com.liferay.osb.exception.NoSuchAccountEntryException;
import com.liferay.osb.exception.TicketFlagTypeException;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.TicketFlag;
import com.liferay.osb.model.TicketFlagConstants;
import com.liferay.osb.service.base.TicketFlagLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ArrayUtil;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Alan Zhang
 */
public class TicketFlagLocalServiceImpl extends TicketFlagLocalServiceBaseImpl {

	public void deleteTicketFlag(
			long userId, long accountEntryId, long ticketEntryId, int type)
		throws PortalException {

		TicketFlag ticketFlag = ticketFlagPersistence.fetchByU_AEI_TEI_T(
			userId, accountEntryId, ticketEntryId, type);

		if (ticketFlag != null) {
			ticketFlagPersistence.remove(ticketFlag);
		}
	}

	public void deleteTicketFlags(long ticketEntryId, int type, int flag) {
		ticketFlagPersistence.removeByTEI_T_F(ticketEntryId, type, flag);
	}

	public List<TicketFlag> getTicketFlags(
		long ticketEntryId, int type, int flag) {

		return ticketFlagPersistence.findByTEI_T_F(ticketEntryId, type, flag);
	}

	public List<TicketFlag> getTicketFlags(
		long ticketEntryId, int[] types, int flag) {

		return ticketFlagPersistence.findByTEI_T_F(ticketEntryId, types, flag);
	}

	public int getTicketFlagsCount(long ticketEntryId, int type, int flag) {
		return ticketFlagPersistence.countByTEI_T_F(ticketEntryId, type, flag);
	}

	public int[] getTicketFlagTypes(long ticketEntryId, int[] types, int flag) {
		Set<Integer> ticketFlagTypes = new HashSet<>();

		List<TicketFlag> ticketFlags = ticketFlagPersistence.findByTEI_T_F(
			ticketEntryId, types, flag);

		for (TicketFlag ticketFlag : ticketFlags) {
			ticketFlagTypes.add(ticketFlag.getType());
		}

		return ArrayUtil.toArray(ticketFlagTypes.toArray(new Integer[0]));
	}

	public boolean hasTicketFlag(long ticketEntryId, int type, int flag) {
		if (ticketFlagPersistence.countByTEI_T_F(ticketEntryId, type, flag) >
				0) {

			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasTicketFlag(
		long userId, long accountEntryId, long ticketEntryId, int type,
		int flag) {

		TicketFlag ticketFlag = ticketFlagPersistence.fetchByU_AEI_TEI_T(
			userId, accountEntryId, ticketEntryId, type);

		if (ticketFlag == null) {
			return false;
		}

		if (ticketFlag.getFlag() == flag) {
			return true;
		}
		else {
			return false;
		}
	}

	public TicketFlag updateTicketFlag(
			long userId, long accountEntryId, long ticketEntryId, int type,
			int flag)
		throws PortalException {

		validate(userId, accountEntryId, ticketEntryId, type);

		TicketFlag ticketFlag = ticketFlagPersistence.fetchByU_AEI_TEI_T(
			userId, accountEntryId, ticketEntryId, type);

		if (ticketFlag == null) {
			long ticketFlagId = counterLocalService.increment();

			ticketFlag = ticketFlagPersistence.create(ticketFlagId);
		}

		ticketFlag.setUserId(userId);
		ticketFlag.setModifiedDate(new Date());
		ticketFlag.setAccountEntryId(accountEntryId);
		ticketFlag.setTicketEntryId(ticketEntryId);
		ticketFlag.setType(type);
		ticketFlag.setFlag(flag);

		return ticketFlagPersistence.update(ticketFlag);
	}

	protected void validate(
			long userId, long accountEntryId, long ticketEntryId, int type)
		throws PortalException {

		userLocalService.getUser(userId);

		AccountEntry accountEntry = accountEntryPersistence.findByPrimaryKey(
			accountEntryId);

		if (accountEntry.getRedirectAccountEntryId() > 0) {
			throw new NoSuchAccountEntryException();
		}

		ticketEntryPersistence.findByPrimaryKey(ticketEntryId);

		if ((type != TicketFlagConstants.TYPE_LIFERAY_COMMENT_UNREAD) &&
			(type != TicketFlagConstants.TYPE_PENDING_CUSTOMER) &&
			(type != TicketFlagConstants.TYPE_PENDING_LIFERAY) &&
			(type != TicketFlagConstants.TYPE_PENDING_PARTNER) &&
			(type != TicketFlagConstants.TYPE_READ_INSTRUCTIONS) &&
			(type != TicketFlagConstants.TYPE_READ_SOLUTION)) {

			throw new TicketFlagTypeException();
		}
	}

}