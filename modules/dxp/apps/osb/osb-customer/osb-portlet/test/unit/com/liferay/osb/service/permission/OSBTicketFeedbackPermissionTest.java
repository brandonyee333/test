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

import com.liferay.compat.portal.kernel.util.Time;
import com.liferay.osb.model.TicketComment;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.model.TicketEntryConstants;
import com.liferay.osb.model.TicketFeedback;
import com.liferay.osb.model.TicketFeedbackConstants;
import com.liferay.osb.model.TicketWorker;
import com.liferay.osb.model.TicketWorkerConstants;
import com.liferay.osb.service.TicketCommentLocalServiceUtil;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.osb.service.TicketWorkerLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.OSBConstants;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.OrganizationLocalServiceUtil;

import java.util.Date;

import org.apache.commons.lang.math.RandomUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.powermock.core.classloader.annotations.PrepareForTest;

/**
 * @author Brent Krone-Schmidt
 * @author Jeremy Fu
 */
@PrepareForTest(
	{
		OrganizationLocalServiceUtil.class, TicketWorkerLocalServiceUtil.class,
		TicketCommentLocalServiceUtil.class, TicketEntryLocalServiceUtil.class
	})
public class OSBTicketFeedbackPermissionTest extends BasePermissionTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		long accountEntryId = accountEntry.getAccountEntryId();

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

		long ticketEntryId = ticketEntry.getTicketEntryId();

		when(
			ticketFeedbackClosedTicketEntry.getStatus()
		).thenReturn(
			TicketEntryConstants.STATUS_CLOSED
		);

		when(
			ticketFeedbackClosedTicketEntry.getAccountEntryId()
		).thenReturn(
			accountEntryId
		);

		when(
			ticketFeedbackClosedTicketEntry.getTicketEntryId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			ticketFeedbackClosedTicketEntry.getClosedDate()
		).thenReturn(
			new Date()
		);

		long openTicketFeedbackClosedTicketEntryId =
			ticketFeedbackClosedTicketEntry.getTicketEntryId();

		when(
			closedTicketFeedbackClosedTicketEntry.getStatus()
		).thenReturn(
			TicketEntryConstants.STATUS_CLOSED
		);

		when(
			closedTicketFeedbackClosedTicketEntry.getAccountEntryId()
		).thenReturn(
			accountEntryId
		);

		when(
			closedTicketFeedbackClosedTicketEntry.getTicketEntryId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			closedTicketFeedbackClosedTicketEntry.getClosedDate()
		).thenReturn(
			new Date(System.currentTimeMillis() - (30 * Time.DAY))
		);

		long closedTicketFeedbackClosedTicketEntryId =
			closedTicketFeedbackClosedTicketEntry.getTicketEntryId();

		when(
			closedTicketEntryClosedLiferayTicketFeedback.getAccountEntryId()
		).thenReturn(
			accountEntryId
		);

		when(
			closedTicketEntryClosedLiferayTicketFeedback.getTicketEntryId()
		).thenReturn(
			closedTicketFeedbackClosedTicketEntryId
		);

		when(
			closedTicketEntryClosedLiferayTicketFeedback.getSubject()
		).thenReturn(
			TicketFeedbackConstants.SUBJECT_LIFERAY
		);

		when(
			closedTicketEntryClosedLiferayTicketFeedback.isClosed()
		).thenReturn(
			true
		);

		when(
			liferayTicketFeedback.getAccountEntryId()
		).thenReturn(
			accountEntryId
		);

		when(
			liferayTicketFeedback.getTicketEntryId()
		).thenReturn(
			ticketEntryId
		);

		when(
			liferayTicketFeedback.getSubject()
		).thenReturn(
			TicketFeedbackConstants.SUBJECT_LIFERAY
		);

		when(
			closedPartnerTicketFeedback.getAccountEntryId()
		).thenReturn(
			accountEntryId
		);

		when(
			closedPartnerTicketFeedback.getSubject()
		).thenReturn(
			TicketFeedbackConstants.SUBJECT_PARTNER
		);

		when(
			closedPartnerTicketFeedback.isClosed()
		).thenReturn(
			true
		);

		when(
			closedTicketFeedback.getAccountEntryId()
		).thenReturn(
			accountEntryId
		);

		when(
			closedTicketFeedback.getTicketEntryId()
		).thenReturn(
			ticketEntryId
		);

		when(
			closedTicketFeedback.isClosed()
		).thenReturn(
			true
		);

		when(
			closedTicketEntryLiferayTicketFeedback.getAccountEntryId()
		).thenReturn(
			accountEntryId
		);

		when(
			closedTicketEntryLiferayTicketFeedback.getTicketEntryId()
		).thenReturn(
			openTicketFeedbackClosedTicketEntryId
		);

		when(
			closedTicketEntryLiferayTicketFeedback.getSubject()
		).thenReturn(
			TicketFeedbackConstants.SUBJECT_LIFERAY
		);

		long partnerManagedSupportAccountEntryId =
			partnerManagedSupportAccountEntry.getAccountEntryId();

		when(
			partnerTicketEntry.getAccountEntryId()
		).thenReturn(
			partnerManagedSupportAccountEntryId
		);

		when(
			partnerTicketEntry.getTicketEntryId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			partnerTicketEntry.getClosedDate()
		).thenReturn(
			new Date()
		);

		long partnerTicketEntryId = partnerTicketEntry.getTicketEntryId();

		when(
			partnerClosedTicketEntry.getStatus()
		).thenReturn(
			TicketEntryConstants.STATUS_CLOSED
		);

		when(
			partnerClosedTicketEntry.getAccountEntryId()
		).thenReturn(
			partnerManagedSupportAccountEntryId
		);

		when(
			partnerClosedTicketEntry.getTicketEntryId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			partnerClosedTicketEntry.getClosedDate()
		).thenReturn(
			new Date()
		);

		long partnerClosedTicketEntryId =
			partnerClosedTicketEntry.getTicketEntryId();

		when(
			partnerManagedClosedTicketEntryClosedLiferayTicketFeedback.
				getAccountEntryId()
		).thenReturn(
			partnerManagedSupportAccountEntryId
		);

		when(
			partnerManagedClosedTicketEntryClosedLiferayTicketFeedback.
				getTicketEntryId()
		).thenReturn(
			partnerClosedTicketEntryId
		);

		when(
			partnerManagedClosedTicketEntryClosedLiferayTicketFeedback.
				getSubject()
		).thenReturn(
			TicketFeedbackConstants.SUBJECT_LIFERAY
		);

		when(
			partnerManagedClosedTicketEntryClosedLiferayTicketFeedback.
				isClosed()
		).thenReturn(
			true
		);

		when(
			escalationP1ClosedTicketEntry.getAccountEntryId()
		).thenReturn(
			accountEntryId
		);

		when(
			escalationP1ClosedTicketEntry.getClosedDate()
		).thenReturn(
			new Date()
		);

		when(
			escalationP1ClosedTicketEntry.getEscalationLevel()
		).thenReturn(
			TicketEntryConstants.ESCALATION_LEVEL_P1
		);

		when(
			escalationP1ClosedTicketEntry.getStatus()
		).thenReturn(
			TicketEntryConstants.STATUS_CLOSED
		);

		when(
			escalationP1ClosedTicketEntry.getTicketEntryId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			escalationP1ClosedTicketEntryLiferayTicketFeedback.
				getAccountEntryId()
		).thenReturn(
			partnerManagedSupportAccountEntryId
		);

		when(
			escalationP1ClosedTicketEntryLiferayTicketFeedback.
				getSubject()
		).thenReturn(
			TicketFeedbackConstants.SUBJECT_LIFERAY
		);

		long escalationP1ClosedTicketEntryId =
			escalationP1ClosedTicketEntry.getTicketEntryId();

		when(
			escalationP1ClosedTicketEntryLiferayTicketFeedback.
				getTicketEntryId()
		).thenReturn(
			escalationP1ClosedTicketEntryId
		);

		when(
			partnerManagedLiferayTicketFeedback.getAccountEntryId()
		).thenReturn(
			partnerManagedSupportAccountEntryId
		);

		when(
			partnerManagedLiferayTicketFeedback.getTicketEntryId()
		).thenReturn(
			partnerTicketEntryId
		);

		when(
			partnerManagedLiferayTicketFeedback.getSubject()
		).thenReturn(
			TicketFeedbackConstants.SUBJECT_LIFERAY
		);

		when(
			partnerTicketFeedback.getSubject()
		).thenReturn(
			TicketFeedbackConstants.SUBJECT_PARTNER
		);

		long restrictedAccountEntryId =
			restrictedAccountEntry.getAccountEntryId();

		when(
			restrictedTicketFeedback.getAccountEntryId()
		).thenReturn(
			restrictedAccountEntryId
		);

		when(
			ticketFeedback.getAccountEntryId()
		).thenReturn(
			accountEntryId
		);

		when(
			ticketFeedback.getTicketEntryId()
		).thenReturn(
			ticketEntryId
		);

		setUpPartnerWorkerLocalServiceUtil();

		setUpTicketCommentLocalServiceUtil();

		setUpTicketEntryLocalServiceUtil();

		setUpTicketWorkers();

		setUpTicketWorkerLocalServiceUtil();
	}

	@Test
	public void testAccountCustomerDeveloperPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerDeveloper.getUserId());

		assertFalse(
			permissionChecker, closedTicketEntryClosedLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, closedPartnerTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertTrue(
			permissionChecker, closedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, liferayTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker,
			escalationP1ClosedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, partnerTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerManagerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerManager.getUserId());

		assertFalse(
			permissionChecker, closedTicketEntryClosedLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, closedPartnerTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, closedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, liferayTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker,
			escalationP1ClosedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, partnerTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerNoRole.getUserId());

		assertFalse(
			permissionChecker, closedTicketEntryClosedLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, closedPartnerTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, closedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, liferayTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker,
			escalationP1ClosedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, partnerTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerSalesPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerSales.getUserId());

		assertFalse(
			permissionChecker, closedTicketEntryClosedLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, closedPartnerTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, closedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, liferayTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker,
			escalationP1ClosedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, partnerTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testAccountCustomerWatcherPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerWatcher.getUserId());

		assertFalse(
			permissionChecker, closedTicketEntryClosedLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, closedPartnerTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, closedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, liferayTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker,
			escalationP1ClosedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, partnerTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testGuestPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(0);

		assertFalse(
			permissionChecker, ticketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testLiferayIncEmployeePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_LIFERAY_EMPLOYEE_ID);

		assertTrue(
			permissionChecker, closedTicketEntryClosedLiferayTicketFeedback,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, closedTicketFeedback, OSBActionKeys.UPDATE);

		assertFalse(
			permissionChecker, closedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE);

		assertFalse(
			permissionChecker, liferayTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertTrue(
			permissionChecker, partnerTicketFeedback, OSBActionKeys.VIEW);
	}

	@Test
	public void testOSBAdminPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_OSB_ADMIN_ID);

		assertTrue(
			permissionChecker, closedTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertTrue(
			permissionChecker, ticketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerManagerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerManager.getUserId());

		assertFalse(
			permissionChecker, closedPartnerTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, liferayTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker,
			escalationP1ClosedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker,
			partnerManagedClosedTicketEntryClosedLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, partnerManagedLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, partnerTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerMemberPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerMember.getUserId());

		assertFalse(
			permissionChecker, closedPartnerTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, liferayTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker,
			escalationP1ClosedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker,
			partnerManagedClosedTicketEntryClosedLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, partnerManagedLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, partnerTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerNoRole.getUserId());

		assertFalse(
			permissionChecker, closedPartnerTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, liferayTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker,
			escalationP1ClosedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker,
			partnerManagedClosedTicketEntryClosedLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, partnerManagedLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, partnerTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testPartnerWorkerWatcherPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerWatcher.getUserId());

		assertFalse(
			permissionChecker, closedPartnerTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, liferayTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker,
			escalationP1ClosedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker,
			partnerManagedClosedTicketEntryClosedLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, partnerManagedLiferayTicketFeedback,
			OSBActionKeys.UPDATE, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, partnerTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, restrictedTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);
	}

	@Test
	public void testTicketWorkerDeveloperPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			ticketWorkerDeveloper.getUserId());

		assertTrue(
			permissionChecker, closedTicketEntryClosedLiferayTicketFeedback,
			OSBActionKeys.VIEW);

		assertTrue(
			permissionChecker, closedPartnerTicketFeedback, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, closedTicketFeedback, OSBActionKeys.UPDATE);

		assertFalse(
			permissionChecker, closedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE);

		assertFalse(
			permissionChecker, liferayTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertTrue(
			permissionChecker, partnerTicketFeedback, OSBActionKeys.VIEW,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testTicketWorkerEscalatedDeveloperPermissions()
		throws Exception {

		PermissionChecker permissionChecker = initPermissionChecker(
			ticketWorkerEscalatedDeveloper.getUserId());

		assertTrue(
			permissionChecker, closedTicketEntryClosedLiferayTicketFeedback,
			OSBActionKeys.VIEW);

		assertTrue(
			permissionChecker, closedPartnerTicketFeedback, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, closedTicketFeedback, OSBActionKeys.UPDATE);

		assertFalse(
			permissionChecker, closedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE);

		assertFalse(
			permissionChecker, liferayTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertTrue(
			permissionChecker, partnerTicketFeedback, OSBActionKeys.VIEW,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testTicketWorkerManagerPermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			ticketWorkerManager.getUserId());

		assertTrue(
			permissionChecker, closedTicketEntryClosedLiferayTicketFeedback,
			OSBActionKeys.VIEW);

		assertTrue(
			permissionChecker, closedPartnerTicketFeedback, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, closedTicketFeedback, OSBActionKeys.UPDATE);

		assertFalse(
			permissionChecker, closedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE);

		assertFalse(
			permissionChecker, liferayTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertTrue(
			permissionChecker, partnerTicketFeedback, OSBActionKeys.VIEW,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testTicketWorkerNoRolePermissions() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			ticketWorkerNoRole.getUserId());

		assertTrue(
			permissionChecker, closedTicketEntryClosedLiferayTicketFeedback,
			OSBActionKeys.VIEW);

		assertTrue(
			permissionChecker, closedPartnerTicketFeedback, OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, closedTicketFeedback, OSBActionKeys.UPDATE);

		assertFalse(
			permissionChecker, closedTicketEntryLiferayTicketFeedback,
			OSBActionKeys.UPDATE);

		assertFalse(
			permissionChecker, liferayTicketFeedback, OSBActionKeys.UPDATE,
			OSBActionKeys.VIEW);

		assertFalse(
			permissionChecker, partnerTicketFeedback, OSBActionKeys.UPDATE);
		assertTrue(
			permissionChecker, partnerTicketFeedback, OSBActionKeys.VIEW);
	}

	protected void assertFalse(
			PermissionChecker permissionChecker, TicketFeedback ticketFeedback,
			String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertFalse(
				OSBTicketFeedbackPermission.contains(
					permissionChecker, ticketFeedback, actionId));
		}
	}

	protected void assertTrue(
			PermissionChecker permissionChecker, TicketFeedback ticketFeedback,
			String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertTrue(
				OSBTicketFeedbackPermission.contains(
					permissionChecker, ticketFeedback, actionId));
		}
	}

	protected void setUpTicketCommentLocalServiceUtil() throws Exception {
		mockStatic(TicketCommentLocalServiceUtil.class);

		long[] organizationIds = {
			OSBConstants.ORGANIZATION_LIFERAY_INC_ID,
			OSBConstants.ORGANIZATION_PARTNER_ID};

		when(
			TicketCommentLocalServiceUtil.getOrganizationTicketCommentsCount(
				organizationIds,
				closedTicketFeedbackClosedTicketEntry.getTicketEntryId(),
				VisibilityConstants.PUBLIC)
		).thenReturn(
			RandomUtils.nextInt()
		);

		when(
			TicketCommentLocalServiceUtil.getOrganizationTicketCommentsCount(
				organizationIds,
				ticketFeedbackClosedTicketEntry.getTicketEntryId(),
				VisibilityConstants.PUBLIC)
		).thenReturn(
			RandomUtils.nextInt()
		);

		when(
			TicketCommentLocalServiceUtil.getOrganizationTicketCommentsCount(
				organizationIds, partnerClosedTicketEntry.getTicketEntryId(),
				VisibilityConstants.PUBLIC)
		).thenReturn(
			RandomUtils.nextInt()
		);

		when(
			TicketCommentLocalServiceUtil.getOrganizationTicketCommentsCount(
				organizationIds,
				escalationP1ClosedTicketEntry.getTicketEntryId(),
				VisibilityConstants.PUBLIC)
		).thenReturn(
			RandomUtils.nextInt()
		);
	}

	protected void setUpTicketEntryLocalServiceUtil() throws Exception {
		mockStatic(TicketEntryLocalServiceUtil.class);

		when(
			TicketEntryLocalServiceUtil.fetchTicketEntry(
				ticketFeedback.getTicketEntryId())
		).thenReturn(
			ticketEntry
		);

		when(
			TicketEntryLocalServiceUtil.fetchTicketEntry(
				closedTicketFeedbackClosedTicketEntry.getTicketEntryId())
		).thenReturn(
			closedTicketFeedbackClosedTicketEntry
		);

		when(
			TicketEntryLocalServiceUtil.fetchTicketEntry(
				ticketFeedbackClosedTicketEntry.getTicketEntryId())
		).thenReturn(
			ticketFeedbackClosedTicketEntry
		);

		when(
			TicketEntryLocalServiceUtil.fetchTicketEntry(
				partnerClosedTicketEntry.getTicketEntryId())
		).thenReturn(
			partnerClosedTicketEntry
		);

		when(
			TicketEntryLocalServiceUtil.fetchTicketEntry(
				escalationP1ClosedTicketEntry.getTicketEntryId())
		).thenReturn(
			escalationP1ClosedTicketEntry
		);
	}

	protected void setUpTicketWorkerLocalServiceUtil() throws Exception {
		mockStatic(TicketWorkerLocalServiceUtil.class);

		when(
			TicketWorkerLocalServiceUtil.getTicketWorker(
				ticketWorkerDeveloper.getUserId(),
				closedPartnerTicketFeedback.getTicketEntryId())
		).thenReturn(
			ticketWorkerDeveloper
		);

		when(
			TicketWorkerLocalServiceUtil.getTicketWorker(
				ticketWorkerDeveloper.getUserId(),
				partnerTicketFeedback.getTicketEntryId())
		).thenReturn(
			ticketWorkerDeveloper
		);

		when(
			TicketWorkerLocalServiceUtil.getTicketWorker(
				ticketWorkerEscalatedDeveloper.getUserId(),
				closedPartnerTicketFeedback.getTicketEntryId())
		).thenReturn(
			ticketWorkerManager
		);

		when(
			TicketWorkerLocalServiceUtil.getTicketWorker(
				ticketWorkerEscalatedDeveloper.getUserId(),
				partnerTicketFeedback.getTicketEntryId())
		).thenReturn(
			ticketWorkerEscalatedDeveloper
		);

		when(
			TicketWorkerLocalServiceUtil.getTicketWorker(
				ticketWorkerManager.getUserId(),
				closedPartnerTicketFeedback.getTicketEntryId())
		).thenReturn(
			ticketWorkerManager
		);

		when(
			TicketWorkerLocalServiceUtil.getTicketWorker(
				ticketWorkerManager.getUserId(),
				partnerTicketFeedback.getTicketEntryId())
		).thenReturn(
			ticketWorkerManager
		);

		when(
			TicketWorkerLocalServiceUtil.getTicketWorker(
				ticketWorkerNoRole.getUserId(),
				closedPartnerTicketFeedback.getTicketEntryId())
		).thenReturn(
			ticketWorkerNoRole
		);

		when(
			TicketWorkerLocalServiceUtil.getTicketWorker(
				ticketWorkerNoRole.getUserId(),
				partnerTicketFeedback.getTicketEntryId())
		).thenReturn(
			ticketWorkerNoRole
		);
	}

	protected void setUpTicketWorkers() throws Exception {
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
			OrganizationLocalServiceUtil.hasUserOrganization(
				ticketWorkerDeveloper.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID)
		).thenReturn(
			true
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
			OrganizationLocalServiceUtil.hasUserOrganization(
				ticketWorkerEscalatedDeveloper.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID)
		).thenReturn(
			true
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
			OrganizationLocalServiceUtil.hasUserOrganization(
				ticketWorkerManager.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID)
		).thenReturn(
			true
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

		when(
			OrganizationLocalServiceUtil.hasUserOrganization(
				ticketWorkerNoRole.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID)
		).thenReturn(
			true
		);
	}

	protected TicketFeedback closedPartnerTicketFeedback = mock(
		TicketFeedback.class);
	protected TicketFeedback closedTicketEntryClosedLiferayTicketFeedback =
		mock(TicketFeedback.class);
	protected TicketFeedback closedTicketEntryLiferayTicketFeedback = mock(
		TicketFeedback.class);
	protected TicketFeedback closedTicketFeedback = mock(TicketFeedback.class);
	protected TicketEntry closedTicketFeedbackClosedTicketEntry = mock(
		TicketEntry.class);
	protected TicketEntry escalationP1ClosedTicketEntry = mock(
		TicketEntry.class);
	protected TicketFeedback liferayTicketFeedback = mock(TicketFeedback.class);
	protected TicketEntry partnerClosedTicketEntry = mock(TicketEntry.class);
	protected TicketFeedback
		escalationP1ClosedTicketEntryLiferayTicketFeedback = mock(
			TicketFeedback.class);
	protected TicketFeedback
		partnerManagedClosedTicketEntryClosedLiferayTicketFeedback = mock(
			TicketFeedback.class);
	protected TicketFeedback partnerManagedLiferayTicketFeedback = mock(
		TicketFeedback.class);
	protected TicketEntry partnerTicketEntry = mock(TicketEntry.class);
	protected TicketFeedback partnerTicketFeedback = mock(TicketFeedback.class);
	protected TicketFeedback restrictedTicketFeedback = mock(
		TicketFeedback.class);
	protected TicketComment ticketComment = mock(TicketComment.class);
	protected TicketEntry ticketEntry = mock(TicketEntry.class);
	protected TicketFeedback ticketFeedback = mock(TicketFeedback.class);
	protected TicketEntry ticketFeedbackClosedTicketEntry = mock(
		TicketEntry.class);
	protected TicketWorker ticketWorkerDeveloper = mock(TicketWorker.class);
	protected TicketWorker ticketWorkerEscalatedDeveloper = mock(
		TicketWorker.class);
	protected TicketWorker ticketWorkerManager = mock(TicketWorker.class);
	protected TicketWorker ticketWorkerNoRole = mock(TicketWorker.class);

}