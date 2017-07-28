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

package com.liferay.osb.service.permission;

import com.liferay.osb.exception.OSBPrincipalException;
import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.model.SupportWorkerConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.model.TicketWorkerConstants;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerLocalServiceUtil;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.osb.service.TicketWorkerLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.OrganizationLocalServiceUtil;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amos Fong
 */
public class OSBTicketEntryPermission {

	public static void check(
			PermissionChecker permissionChecker, long ticketEntryId,
			String actionId)
		throws PortalException {

		TicketEntry ticketEntry = TicketEntryLocalServiceUtil.getTicketEntry(
			ticketEntryId);

		check(permissionChecker, ticketEntry, actionId);
	}

	public static void check(
			PermissionChecker permissionChecker, TicketEntry ticketEntry,
			String actionId)
		throws PortalException {

		int type = getType(permissionChecker, ticketEntry, actionId);

		if (type > 0) {
			throw new OSBPrincipalException(type);
		}
	}

	public static boolean contains(
			PermissionChecker permissionChecker, long ticketEntryId,
			String actionId)
		throws PortalException {

		TicketEntry ticketEntry = TicketEntryLocalServiceUtil.getTicketEntry(
			ticketEntryId);

		return contains(permissionChecker, ticketEntry, actionId);
	}

	public static boolean contains(
		PermissionChecker permissionChecker, TicketEntry ticketEntry,
		String actionId) {

		int type = getType(permissionChecker, ticketEntry, actionId);

		if (type > 0) {
			return false;
		}

		return true;
	}

	protected static int getType(
		PermissionChecker permissionChecker, TicketEntry ticketEntry,
		String actionId) {

		List<SupportWorker> supportWorkers = new ArrayList<>();

		try {
			supportWorkers =
				SupportWorkerLocalServiceUtil.getUserSupportWorkers(
					permissionChecker.getUserId());

			if (!supportWorkers.isEmpty()) {
				if (actionId.equals(OSBActionKeys.UPDATE_BULK)) {
					for (SupportWorker supportWorker : supportWorkers) {
						if (supportWorker.getRole() ==
								SupportWorkerConstants.ROLE_MANAGER) {

							return 0;
						}
					}
				}

				SupportWorker supportWorker = supportWorkers.get(0);

				if (!actionId.equals(OSBActionKeys.VIEW) &&
					!supportWorker.isClockedIn()) {

					return OSBPrincipalException.TYPE_GENERAL;
				}
			}
		}
		catch (Exception e) {
		}

		if (actionId.equals(OSBActionKeys.ESCALATE) &&
			(ticketEntry.getEscalationLevel() >=
				TicketEntryConstants.ESCALATION_LEVEL_2)) {

			return OSBPrincipalException.TYPE_GENERAL;
		}

		if (RoleLocalServiceUtil.hasUserRole(
				permissionChecker.getUserId(),
				OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)) {

			return 0;
		}

		if (actionId.equals(OSBActionKeys.UPDATE_BULK)) {
			return OSBPrincipalException.TYPE_GENERAL;
		}

		TicketWorker ticketWorker = null;

		try {
			ticketWorker = TicketWorkerLocalServiceUtil.getTicketWorker(
				permissionChecker.getUserId(), ticketEntry.getTicketEntryId());
		}
		catch (Exception e) {
		}

		if (actionId.equals(OSBActionKeys.FORWARD)) {
			if ((ticketWorker != null) &&
				(ticketWorker.getRole() != TicketWorkerConstants.ROLE_NONE)) {

				return 0;
			}

			return OSBPrincipalException.TYPE_GENERAL;
		}

		if (actionId.equals(OSBActionKeys.UPDATE_ADMIN)) {
			return OSBPrincipalException.TYPE_GENERAL;
		}

		if (actionId.equals(OSBActionKeys.ADD_COMMENT_LIFERAY_INC) ||
			actionId.equals(OSBActionKeys.UPDATE_TICKET_CALL)) {

			if (OrganizationLocalServiceUtil.hasUserOrganization(
					permissionChecker.getUserId(),
					OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

				return 0;
			}

			return OSBPrincipalException.TYPE_GENERAL;
		}

		AccountWorker accountWorker = null;

		try {
			accountWorker = AccountWorkerLocalServiceUtil.getAccountWorker(
				permissionChecker.getUserId(), ticketEntry.getAccountEntryId());

			if ((accountWorker.getRole() ==
					AccountWorkerConstants.ROLE_ADVOCACY_SPECIALIST) ||
				(accountWorker.getRole() ==
					AccountWorkerConstants.ROLE_SALES)) {

				return 0;
			}
		}
		catch (Exception e) {
		}

		PartnerWorker partnerWorker = null;

		try {
			AccountEntry accountEntry = ticketEntry.getAccountEntry();

			if (accountEntry.isPartnerManagedSupport()) {
				partnerWorker = PartnerWorkerLocalServiceUtil.getPartnerWorker(
					permissionChecker.getUserId(),
					accountEntry.getPartnerEntryId());

				if (actionId.equals(OSBActionKeys.ESCALATE) &&
					(ticketEntry.getEscalationLevel() !=
						TicketEntryConstants.ESCALATION_LEVEL_P1)) {

					return OSBPrincipalException.TYPE_GENERAL;
				}

				if (partnerWorker.getRole() ==
						PartnerWorkerConstants.ROLE_MANAGER) {

					return 0;
				}
			}
		}
		catch (Exception e) {
		}

		for (SupportWorker supportWorker : supportWorkers) {
			if (supportWorker.getRole() ==
					SupportWorkerConstants.ROLE_MANAGER) {

				return 0;
			}
		}

		if (actionId.equals(OSBActionKeys.ASSIGN_WORKERS)) {
			return OSBPrincipalException.TYPE_GENERAL;
		}

		if ((ticketWorker != null) &&
			((ticketWorker.getRole() ==
				TicketWorkerConstants.ROLE_ESCALATED_DEVELOPER) ||
			 (ticketWorker.getRole() == TicketWorkerConstants.ROLE_MANAGER))) {

			return 0;
		}

		if (actionId.equals(OSBActionKeys.UPDATE_SOLUTION)) {
			return OSBPrincipalException.TYPE_GENERAL;
		}

		if ((ticketWorker != null) &&
			(ticketWorker.getRole() == TicketWorkerConstants.ROLE_DEVELOPER)) {

			return 0;
		}

		if (actionId.equals(OSBActionKeys.ADD_LINK) ||
			actionId.equals(OSBActionKeys.DELETE_ATTACHMENT) ||
			actionId.equals(OSBActionKeys.DELETE_LINK) ||
			actionId.equals(OSBActionKeys.ESCALATE) ||
			actionId.equals(OSBActionKeys.UPDATE_ADVANCED)) {

			return OSBPrincipalException.TYPE_GENERAL;
		}

		if (accountWorker != null) {
			return 0;
		}

		if ((actionId.equals(OSBActionKeys.ADD_COMMENT_PUBLIC) ||
			 actionId.equals(OSBActionKeys.ADD_COMMENT_WORKERS)) &&
			(ticketEntry.getStatus() == TicketEntryConstants.STATUS_CLOSED)) {

			return OSBPrincipalException.TYPE_TICKET_CLOSED;
		}

		if ((partnerWorker != null) &&
			(partnerWorker.getRole() != PartnerWorkerConstants.ROLE_WATCHER)) {

			return 0;
		}

		if (!supportWorkers.isEmpty()) {
			return 0;
		}

		if (actionId.equals(OSBActionKeys.ADD_COMMENT_WORKERS)) {
			return OSBPrincipalException.TYPE_GENERAL;
		}

		AccountCustomer accountCustomer = null;

		try {
			accountCustomer =
				AccountCustomerLocalServiceUtil.getAccountCustomer(
					permissionChecker.getUserId(),
					ticketEntry.getAccountEntryId());

			if ((accountCustomer.getRole() ==
					AccountCustomerConstants.ROLE_DEVELOPER) ||
				(accountCustomer.getRole() ==
					AccountCustomerConstants.ROLE_MANAGER)) {

				if (actionId.equals(OSBActionKeys.WATCH)) {
					return OSBPrincipalException.TYPE_GENERAL;
				}

				return 0;
			}
		}
		catch (Exception e) {
		}

		if (actionId.equals(OSBActionKeys.ADD_ATTACHMENT) ||
			actionId.equals(OSBActionKeys.UPDATE_BASIC)) {

			return OSBPrincipalException.TYPE_GENERAL;
		}

		if (actionId.equals(OSBActionKeys.ADD_COMMENT_PUBLIC)) {
			return OSBPrincipalException.TYPE_GENERAL;
		}

		if (accountCustomer != null) {
			return 0;
		}

		if (partnerWorker != null) {
			return 0;
		}

		if (OrganizationLocalServiceUtil.hasUserOrganization(
				permissionChecker.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID)) {

			return 0;
		}

		if (ticketWorker != null) {
			return 0;
		}

		return OSBPrincipalException.TYPE_GENERAL;
	}

}