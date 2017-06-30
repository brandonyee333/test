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

import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.model.TicketWorkerConstants;
import com.liferay.osb.service.TicketWorkerLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;

import org.apache.commons.lang.math.RandomUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.powermock.core.classloader.annotations.PrepareForTest;

/**
 * @author Brent Krone-Schmidt
 */
@PrepareForTest(TicketWorkerLocalServiceUtil.class)
public class OSBTicketEntryPermissionTest extends BasePermissionTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		long accountEntryId = accountEntry.getAccountEntryId();

		when(
			closedTicketEntry.getAccountEntryId()
		).thenReturn(
			accountEntryId
		);

		when(
			closedTicketEntry.getStatus()
		).thenReturn(
			TicketEntryConstants.STATUS_CLOSED
		);

		when(
			level2TicketEntry.getAccountEntryId()
		).thenReturn(
			accountEntryId
		);

		when(
			level2TicketEntry.getEscalationLevel()
		).thenReturn(
			TicketEntryConstants.ESCALATION_LEVEL_2
		);

		when(
			level2TicketEntry.getTicketEntryId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			partnerManagedSupportLevelP1TicketEntry.getAccountEntry()
		).thenReturn(
			partnerManagedSupportAccountEntry
		);

		long partnerManagedSupportAccountEntryId =
			partnerManagedSupportAccountEntry.getAccountEntryId();

		when(
			partnerManagedSupportLevelP1TicketEntry.getAccountEntryId()
		).thenReturn(
			partnerManagedSupportAccountEntryId
		);

		when(
			partnerManagedSupportLevelP1TicketEntry.getEscalationLevel()
		).thenReturn(
			TicketEntryConstants.ESCALATION_LEVEL_P1
		);

		when(
			partnerManagedSupportTicketEntry.getAccountEntry()
		).thenReturn(
			partnerManagedSupportAccountEntry
		);

		when(
			partnerManagedSupportTicketEntry.getAccountEntryId()
		).thenReturn(
			partnerManagedSupportAccountEntryId
		);

		when(
			ticketEntry.getAccountEntryId()
		).thenReturn(
			accountEntryId
		);

		when(
			ticketEntry.getTicketEntryId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		setUpTicketWorkers();

		setUpTicketWorkerLocalServiceUtil();
	}

	@Test
	public void testAccountCustomerDeveloperPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerDeveloper.getUserId());

		assertFalse(
			permissionChecker, closedTicketEntry,
			OSBActionKeys.ADD_COMMENT_PUBLIC);

		assertFalse(
			permissionChecker, restrictedTicketEntry,
			OSBActionKeys.ADD_ATTACHMENT, OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, ticketEntry,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_WORKERS, OSBActionKeys.ADD_LINK,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.DELETE_ATTACHMENT,
			OSBActionKeys.DELETE_LINK, OSBActionKeys.ESCALATE,
			OSBActionKeys.FORWARD, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL);
		assertTrue(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerManagerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerManager.getUserId());

		assertFalse(
			permissionChecker, closedTicketEntry,
			OSBActionKeys.ADD_COMMENT_PUBLIC);

		assertFalse(
			permissionChecker, restrictedTicketEntry,
			OSBActionKeys.ADD_ATTACHMENT, OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, ticketEntry,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_WORKERS, OSBActionKeys.ADD_LINK,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.DELETE_ATTACHMENT,
			OSBActionKeys.DELETE_LINK, OSBActionKeys.ESCALATE,
			OSBActionKeys.FORWARD, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL);
		assertTrue(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerNoRole.getUserId());

		assertFalse(
			permissionChecker, restrictedTicketEntry,
			OSBActionKeys.ADD_ATTACHMENT, OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL);
		assertTrue(permissionChecker, ticketEntry, OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountWorkerAdvocacySpecialistPermissions()
		throws Exception {

		PermissionChecker permissionChecker = initPermissionChecker(
			accountWorkerAdvocacySpecialist.getUserId());

		assertFalse(
			permissionChecker, level2TicketEntry, OSBActionKeys.ESCALATE);

		assertFalse(
			permissionChecker, restrictedTicketEntry,
			OSBActionKeys.ADD_ATTACHMENT, OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, ticketEntry,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_TICKET_CALL);
		assertTrue(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_SOLUTION,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountWorkerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountWorkerNoRole.getUserId());

		assertFalse(
			permissionChecker, restrictedTicketEntry,
			OSBActionKeys.ADD_ATTACHMENT, OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, ticketEntry,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC, OSBActionKeys.ADD_LINK,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.DELETE_ATTACHMENT,
			OSBActionKeys.DELETE_LINK, OSBActionKeys.ESCALATE,
			OSBActionKeys.FORWARD, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL);
		assertTrue(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountWorkerSalesPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountWorkerSales.getUserId());

		assertFalse(
			permissionChecker, level2TicketEntry, OSBActionKeys.ESCALATE);

		assertFalse(
			permissionChecker, restrictedTicketEntry,
			OSBActionKeys.ADD_ATTACHMENT, OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, ticketEntry,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_TICKET_CALL);
		assertTrue(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_SOLUTION,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testClockedOutSupportWorkerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			clockedOutSupportWorker.getUserId());

		assertFalse(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL);
		assertTrue(permissionChecker, ticketEntry, OSBActionKeys.VIEW);
	}

	@Test
	public void testGuestPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(0);

		assertFalse(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testLiferayIncEmployeePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_LIFERAY_EMPLOYEE_ID);

		assertFalse(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION);
		assertTrue(
			permissionChecker, ticketEntry,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.UPDATE_TICKET_CALL, OSBActionKeys.VIEW);
	}

	@Test
	public void testOSBAdminPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_OSB_ADMIN_ID);

		assertFalse(
			permissionChecker, level2TicketEntry, OSBActionKeys.ESCALATE);

		assertTrue(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerManagerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerManager.getUserId());

		assertFalse(
			permissionChecker, closedTicketEntry,
			OSBActionKeys.ADD_COMMENT_PUBLIC,
			OSBActionKeys.ADD_COMMENT_WORKERS);

		assertTrue(
			permissionChecker, partnerManagedSupportLevelP1TicketEntry,
			OSBActionKeys.ESCALATE);

		assertFalse(
			permissionChecker, partnerManagedSupportTicketEntry,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC, OSBActionKeys.ESCALATE,
			OSBActionKeys.FORWARD, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_BULK, OSBActionKeys.UPDATE_TICKET_CALL);
		assertTrue(
			permissionChecker, partnerManagedSupportTicketEntry,
			OSBActionKeys.ADD_ATTACHMENT, OSBActionKeys.ADD_COMMENT_PUBLIC,
			OSBActionKeys.ADD_COMMENT_WORKERS, OSBActionKeys.ADD_LINK,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.DELETE_ATTACHMENT,
			OSBActionKeys.DELETE_LINK, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_SOLUTION,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerMemberPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerMember.getUserId());

		assertFalse(
			permissionChecker, closedTicketEntry,
			OSBActionKeys.ADD_COMMENT_PUBLIC,
			OSBActionKeys.ADD_COMMENT_WORKERS);

		assertFalse(
			permissionChecker, partnerManagedSupportTicketEntry,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC, OSBActionKeys.ADD_LINK,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.DELETE_ATTACHMENT,
			OSBActionKeys.DELETE_LINK, OSBActionKeys.ESCALATE,
			OSBActionKeys.FORWARD, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL);
		assertTrue(
			permissionChecker, partnerManagedSupportTicketEntry,
			OSBActionKeys.ADD_ATTACHMENT, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerNoRole.getUserId());

		assertFalse(
			permissionChecker, closedTicketEntry,
			OSBActionKeys.ADD_COMMENT_PUBLIC,
			OSBActionKeys.ADD_COMMENT_WORKERS);

		assertFalse(
			permissionChecker, partnerManagedSupportTicketEntry,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC, OSBActionKeys.ADD_LINK,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.DELETE_ATTACHMENT,
			OSBActionKeys.DELETE_LINK, OSBActionKeys.ESCALATE,
			OSBActionKeys.FORWARD, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL);
		assertTrue(
			permissionChecker, partnerManagedSupportTicketEntry,
			OSBActionKeys.ADD_ATTACHMENT, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.UPDATE_BASIC,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerWatcherPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerWatcher.getUserId());

		assertFalse(
			permissionChecker, partnerManagedSupportTicketEntry,
			OSBActionKeys.ADD_ATTACHMENT, OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL);
		assertTrue(
			permissionChecker, partnerManagedSupportTicketEntry,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testSupportWorkerManagerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			supportWorkerManager.getUserId());

		assertFalse(
			permissionChecker, level2TicketEntry, OSBActionKeys.ESCALATE);

		assertFalse(
			permissionChecker, ticketEntry, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN);
		assertTrue(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testSupportWorkerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			supportWorkerNoRole.getUserId());

		assertFalse(
			permissionChecker, closedTicketEntry,
			OSBActionKeys.ADD_COMMENT_PUBLIC,
			OSBActionKeys.ADD_COMMENT_WORKERS);

		assertFalse(
			permissionChecker, level2TicketEntry, OSBActionKeys.ESCALATE);

		assertFalse(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_LINK,
			OSBActionKeys.ASSIGN_WORKERS, OSBActionKeys.DELETE_ATTACHMENT,
			OSBActionKeys.DELETE_LINK, OSBActionKeys.ESCALATE,
			OSBActionKeys.FORWARD, OSBActionKeys.UPDATE_ADMIN,
			OSBActionKeys.UPDATE_ADVANCED, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION);
		assertTrue(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_TICKET_CALL,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testTicketWorkerDeveloperPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			ticketWorkerDeveloper.getUserId());

		assertFalse(
			permissionChecker, level2TicketEntry, OSBActionKeys.ESCALATE);

		assertFalse(
			permissionChecker, restrictedTicketEntry,
			OSBActionKeys.ADD_ATTACHMENT, OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL);

		assertFalse(
			permissionChecker, ticketEntry,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL);
		assertTrue(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.DELETE_ATTACHMENT,
			OSBActionKeys.DELETE_LINK, OSBActionKeys.ESCALATE,
			OSBActionKeys.FORWARD, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.VIEW);
	}

	@Test
	public void testTicketWorkerEscalatedDeveloperPermissions()
		throws Exception {

		PermissionChecker permissionChecker = initPermissionChecker(
			ticketWorkerEscalatedDeveloper.getUserId());

		assertFalse(
			permissionChecker, level2TicketEntry, OSBActionKeys.ESCALATE);

		assertFalse(
			permissionChecker, restrictedTicketEntry,
			OSBActionKeys.ADD_ATTACHMENT, OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL);

		assertFalse(
			permissionChecker, ticketEntry,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_TICKET_CALL);
		assertTrue(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.DELETE_ATTACHMENT,
			OSBActionKeys.DELETE_LINK, OSBActionKeys.ESCALATE,
			OSBActionKeys.FORWARD, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_SOLUTION,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testTicketWorkerManagerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			ticketWorkerManager.getUserId());

		assertFalse(
			permissionChecker, level2TicketEntry, OSBActionKeys.ESCALATE);

		assertFalse(
			permissionChecker, restrictedTicketEntry,
			OSBActionKeys.ADD_ATTACHMENT, OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL);

		assertFalse(
			permissionChecker, ticketEntry,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_TICKET_CALL);
		assertTrue(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.DELETE_ATTACHMENT,
			OSBActionKeys.DELETE_LINK, OSBActionKeys.ESCALATE,
			OSBActionKeys.FORWARD, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_SOLUTION,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testTicketWorkerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			ticketWorkerNoRole.getUserId());

		assertFalse(
			permissionChecker, restrictedTicketEntry, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, ticketEntry, OSBActionKeys.ADD_ATTACHMENT,
			OSBActionKeys.ADD_COMMENT_LIFERAY_INC,
			OSBActionKeys.ADD_COMMENT_PUBLIC, OSBActionKeys.ADD_COMMENT_WORKERS,
			OSBActionKeys.ADD_LINK, OSBActionKeys.ASSIGN_WORKERS,
			OSBActionKeys.DELETE_ATTACHMENT, OSBActionKeys.DELETE_LINK,
			OSBActionKeys.ESCALATE, OSBActionKeys.FORWARD,
			OSBActionKeys.UPDATE_ADMIN, OSBActionKeys.UPDATE_ADVANCED,
			OSBActionKeys.UPDATE_BASIC, OSBActionKeys.UPDATE_BULK,
			OSBActionKeys.UPDATE_SOLUTION, OSBActionKeys.UPDATE_TICKET_CALL);
		assertTrue(permissionChecker, ticketEntry, OSBActionKeys.VIEW);
	}

	protected void assertFalse(
			PermissionChecker permissionChecker, TicketEntry ticketEntry,
			String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertFalse(
				OSBTicketEntryPermission.contains(
					permissionChecker, ticketEntry, actionId));
		}
	}

	protected void assertTrue(
			PermissionChecker permissionChecker, TicketEntry ticketEntry,
			String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertTrue(
				OSBTicketEntryPermission.contains(
					permissionChecker, ticketEntry, actionId));
		}
	}

	protected void setUpTicketWorkerLocalServiceUtil() throws Exception {
		mockStatic(TicketWorkerLocalServiceUtil.class);

		when(
			TicketWorkerLocalServiceUtil.getTicketWorker(
				ticketWorkerDeveloper.getUserId(),
				level2TicketEntry.getTicketEntryId())
		).thenReturn(
			ticketWorkerDeveloper
		);

		when(
			TicketWorkerLocalServiceUtil.getTicketWorker(
				ticketWorkerDeveloper.getUserId(),
				ticketEntry.getTicketEntryId())
		).thenReturn(
			ticketWorkerDeveloper
		);

		when(
			TicketWorkerLocalServiceUtil.getTicketWorker(
				ticketWorkerEscalatedDeveloper.getUserId(),
				level2TicketEntry.getTicketEntryId())
		).thenReturn(
			ticketWorkerEscalatedDeveloper
		);

		when(
			TicketWorkerLocalServiceUtil.getTicketWorker(
				ticketWorkerEscalatedDeveloper.getUserId(),
				ticketEntry.getTicketEntryId())
		).thenReturn(
			ticketWorkerEscalatedDeveloper
		);

		when(
			TicketWorkerLocalServiceUtil.getTicketWorker(
				ticketWorkerManager.getUserId(),
				level2TicketEntry.getTicketEntryId())
		).thenReturn(
			ticketWorkerManager
		);

		when(
			TicketWorkerLocalServiceUtil.getTicketWorker(
				ticketWorkerManager.getUserId(), ticketEntry.getTicketEntryId())
		).thenReturn(
			ticketWorkerManager
		);

		when(
			TicketWorkerLocalServiceUtil.getTicketWorker(
				ticketWorkerNoRole.getUserId(),
				level2TicketEntry.getTicketEntryId())
		).thenReturn(
			ticketWorkerNoRole
		);

		when(
			TicketWorkerLocalServiceUtil.getTicketWorker(
				ticketWorkerNoRole.getUserId(), ticketEntry.getTicketEntryId())
		).thenReturn(
			ticketWorkerNoRole
		);
	}

	protected void setUpTicketWorkers() {
		when(
			ticketWorkerDeveloper.getRole()
		).thenReturn(
			TicketWorkerConstants.ROLE_DEVELOPER
		);

		when(
			ticketWorkerDeveloper.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			ticketWorkerEscalatedDeveloper.getRole()
		).thenReturn(
			TicketWorkerConstants.ROLE_ESCALATED_DEVELOPER
		);

		when(
			ticketWorkerEscalatedDeveloper.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			ticketWorkerManager.getRole()
		).thenReturn(
			TicketWorkerConstants.ROLE_MANAGER
		);

		when(
			ticketWorkerManager.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			ticketWorkerNoRole.getRole()
		).thenReturn(
			TicketWorkerConstants.ROLE_NONE
		);

		when(
			ticketWorkerNoRole.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);
	}

	protected TicketEntry closedTicketEntry = mock(TicketEntry.class);
	protected TicketEntry level2TicketEntry = mock(TicketEntry.class);
	protected TicketEntry partnerManagedSupportLevelP1TicketEntry = mock(
		TicketEntry.class);
	protected TicketEntry partnerManagedSupportTicketEntry = mock(
		TicketEntry.class);
	protected TicketEntry restrictedTicketEntry = mock(TicketEntry.class);
	protected TicketEntry ticketEntry = mock(TicketEntry.class);
	protected TicketWorker ticketWorkerDeveloper = mock(TicketWorker.class);
	protected TicketWorker ticketWorkerEscalatedDeveloper = mock(
		TicketWorker.class);
	protected TicketWorker ticketWorkerManager = mock(TicketWorker.class);
	protected TicketWorker ticketWorkerNoRole = mock(TicketWorker.class);

}