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

import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.model.TicketComment;
import com.liferay.osb.model.TicketCommentConstants;
import com.liferay.osb.model.TicketEntry;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerLocalServiceUtil;
import com.liferay.osb.service.TicketCommentLocalServiceUtil;
import com.liferay.osb.service.TicketEntryLocalServiceUtil;
import com.liferay.osb.util.OSBActionKeys;
import com.liferay.osb.util.VisibilityConstants;
import com.liferay.osb.util.WorkflowConstants;
import com.liferay.osb.util.comparator.TicketCommentCreateDateComparator;
import com.liferay.portal.kernel.security.permission.PermissionChecker;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.powermock.core.classloader.annotations.PrepareForTest;

/**
 * @author Alan Zhang
 */
@PrepareForTest(
	{
		OSBTicketCommentPermission.class, TicketCommentLocalServiceUtil.class,
		TicketEntryLocalServiceUtil.class
	})
public class OSBTicketCommentPermissionTest extends BasePermissionTestCase {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();

		lastPartnerWorkerManagerTicketComment = initTicketComment(
			partnerWorkerManager.getUserId(),
			WorkflowConstants.STATUS_APPROVED);

		lastSupportWorkerDeveloperTicketComment = initTicketComment(
			supportWorkerDeveloper.getUserId(),
			WorkflowConstants.STATUS_APPROVED);

		when(
			ticketEntry.getAccountEntryId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			AccountCustomerLocalServiceUtil.hasAccountCustomer(
				accountCustomerDeveloper.getUserId(),
				ticketEntry.getAccountEntryId())
		).thenReturn(
			true
		);

		when(
			AccountCustomerLocalServiceUtil.hasAccountCustomer(
				supportWorkerDeveloper.getUserId(),
				ticketEntry.getAccountEntryId())
		).thenReturn(
			false
		);

		List<PartnerWorker> partnerWorkerList = new ArrayList<PartnerWorker>();

		when(
			PartnerWorkerLocalServiceUtil.getUserPartnerWorkers(
				USER_OSB_ADMIN_ID)
		).thenReturn(
			partnerWorkerList
		);

		List<SupportWorker> supportWorkerList = new ArrayList<SupportWorker>();

		when(
			SupportWorkerLocalServiceUtil.getUserSupportWorkers(
				USER_OSB_ADMIN_ID)
		).thenReturn(
			supportWorkerList
		);

		setUpTicketCommentCreateDateComparator();
		setUpTicketCommentLocalServiceUtil();
		setUpTicketEntryLocalServiceUtil();
	}

	@Test
	public void testAccountCustomerDeveloper() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			accountCustomerDeveloper.getUserId());

		TicketComment ticketComment = initTicketComment(
			RandomUtils.nextLong(), RandomUtils.nextInt());

		assertFalse(
			permissionChecker, ticketComment, OSBActionKeys.DELETE,
			OSBActionKeys.MARK_AS_SOLUTION, OSBActionKeys.UPDATE);

		TicketComment ownTicketComment = initTicketComment(
			accountCustomerDeveloper.getUserId(), RandomUtils.nextInt());

		assertFalse(
			permissionChecker, ownTicketComment, OSBActionKeys.DELETE,
			OSBActionKeys.MARK_AS_SOLUTION, OSBActionKeys.UPDATE);

		TicketComment ownTicketCommentDraft = initTicketComment(
			accountCustomerDeveloper.getUserId(),
			WorkflowConstants.STATUS_DRAFT);

		assertFalse(
			permissionChecker, ownTicketCommentDraft,
			OSBActionKeys.MARK_AS_SOLUTION);

		assertTrue(
			permissionChecker, ownTicketCommentDraft, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testAdministrator() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			USER_OSB_ADMIN_ID);

		TicketComment ticketComment = initTicketComment(
			RandomUtils.nextLong(), RandomUtils.nextInt());

		assertTrue(
			permissionChecker, ticketComment, OSBActionKeys.DELETE,
			OSBActionKeys.MARK_AS_SOLUTION, OSBActionKeys.UPDATE);
	}

	@Test
	public void testClockedOutSupportWorker() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			clockedOutSupportWorker.getUserId());

		TicketComment ticketComment = initTicketComment(
			RandomUtils.nextLong(), RandomUtils.nextInt());

		assertFalse(
			permissionChecker, ticketComment, OSBActionKeys.DELETE,
			OSBActionKeys.MARK_AS_SOLUTION, OSBActionKeys.UPDATE);
	}

	@Test
	public void testPartnerWorkerManager() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			partnerWorkerManager.getUserId());

		TicketComment ticketComment = initTicketComment(
			RandomUtils.nextLong(), WorkflowConstants.STATUS_APPROVED);

		assertFalse(
			permissionChecker, ticketComment, OSBActionKeys.MARK_AS_SOLUTION);

		assertFalse(
			permissionChecker, ticketComment, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);

		TicketComment ownTicketComment = initTicketComment(
			partnerWorkerManager.getUserId(),
			WorkflowConstants.STATUS_APPROVED);

		assertFalse(
			permissionChecker, ownTicketComment, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);

		assertTrue(
			permissionChecker, lastPartnerWorkerManagerTicketComment,
			OSBActionKeys.UPDATE);

		TicketComment ownTicketCommentDraft = initTicketComment(
			partnerWorkerManager.getUserId(), WorkflowConstants.STATUS_DRAFT);

		assertTrue(
			permissionChecker, ownTicketCommentDraft, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);
	}

	@Test
	public void testSupportWorkerDeveloper() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			supportWorkerDeveloper.getUserId());

		TicketComment ticketComment = initTicketComment(
			RandomUtils.nextLong(), WorkflowConstants.STATUS_APPROVED);

		assertTrue(
			permissionChecker, ticketComment, OSBActionKeys.MARK_AS_SOLUTION);

		assertFalse(
			permissionChecker, ticketComment, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);

		TicketComment ownTicketComment = initTicketComment(
			supportWorkerDeveloper.getUserId(),
			WorkflowConstants.STATUS_APPROVED);

		assertFalse(
			permissionChecker, ownTicketComment, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);

		assertTrue(
			permissionChecker, lastSupportWorkerDeveloperTicketComment,
			OSBActionKeys.UPDATE);

		TicketComment ownTicketCommentDraft = initTicketComment(
			supportWorkerDeveloper.getUserId(), WorkflowConstants.STATUS_DRAFT);

		assertTrue(
			permissionChecker, ownTicketCommentDraft, OSBActionKeys.DELETE,
			OSBActionKeys.MARK_AS_SOLUTION, OSBActionKeys.UPDATE);
	}

	@Test
	public void testSupportWorkerWatcher() throws Exception {
		PermissionChecker permissionChecker = initPermissionChecker(
			supportWorkerWatcher.getUserId());

		TicketComment ticketComment = initTicketComment(
			RandomUtils.nextLong(), RandomUtils.nextInt());

		assertFalse(
			permissionChecker, ticketComment, OSBActionKeys.DELETE,
			OSBActionKeys.MARK_AS_SOLUTION, OSBActionKeys.UPDATE);

		TicketComment ownTicketCommentDraft = initTicketComment(
			supportWorkerWatcher.getUserId(), WorkflowConstants.STATUS_DRAFT);

		assertFalse(
			permissionChecker, ownTicketCommentDraft,
			OSBActionKeys.MARK_AS_SOLUTION);

		assertTrue(
			permissionChecker, ownTicketCommentDraft, OSBActionKeys.DELETE,
			OSBActionKeys.UPDATE);
	}

	protected void assertFalse(
			PermissionChecker permissionChecker, TicketComment ticketComment,
			String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertFalse(
				OSBTicketCommentPermission.contains(
					permissionChecker, ticketComment, actionId));
		}
	}

	protected void assertTrue(
			PermissionChecker permissionChecker, TicketComment ticketComment,
			String... actionIds)
		throws Exception {

		for (String actionId : actionIds) {
			Assert.assertTrue(
				OSBTicketCommentPermission.contains(
					permissionChecker, ticketComment, actionId));
		}
	}

	protected TicketComment initTicketComment(long userId, int status) {
		TicketComment ticketComment = mock(TicketComment.class);

		when(
			ticketComment.getUserId()
		).thenReturn(
			userId
		);

		when(
			ticketComment.getTicketCommentId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			ticketComment.getTicketEntryId()
		).thenReturn(
			TICKET_ENTRY_ID
		);

		when(
			ticketComment.getType()
		).thenReturn(
			TicketCommentConstants.TYPE_NORMAL
		);

		when(
			ticketComment.getVisibility()
		).thenReturn(
			VisibilityConstants.PUBLIC
		);

		when(
			ticketComment.getStatus()
		).thenReturn(
			status
		);

		return ticketComment;
	}

	protected void setUpTicketCommentCreateDateComparator() throws Exception {
		whenNew(
			TicketCommentCreateDateComparator.class
		).withArguments(
			true
		).thenReturn(
			ticketCommentComparator
		);
	}

	protected void setUpTicketCommentLocalServiceUtil() throws Exception {
		mockStatic(TicketCommentLocalServiceUtil.class);

		when(
			TicketCommentLocalServiceUtil.fetchLastTicketComment(
				partnerWorkerManager.getUserId(), TICKET_ENTRY_ID,
				VisibilityConstants.PUBLIC, WorkflowConstants.STATUS_APPROVED,
				ticketCommentComparator)
		).thenReturn(
			lastPartnerWorkerManagerTicketComment
		);

		when(
			TicketCommentLocalServiceUtil.fetchLastTicketComment(
				supportWorkerDeveloper.getUserId(), TICKET_ENTRY_ID,
				VisibilityConstants.PUBLIC, WorkflowConstants.STATUS_APPROVED,
				ticketCommentComparator)
		).thenReturn(
			lastSupportWorkerDeveloperTicketComment
		);
	}

	protected void setUpTicketEntryLocalServiceUtil() throws Exception {
		mockStatic(TicketEntryLocalServiceUtil.class);

		when(
			TicketEntryLocalServiceUtil.getTicketEntry(TICKET_ENTRY_ID)
		).thenReturn(
			ticketEntry
		);
	}

	protected static final long TICKET_ENTRY_ID = RandomUtils.nextLong();

	protected TicketComment lastPartnerWorkerManagerTicketComment;
	protected TicketComment lastSupportWorkerDeveloperTicketComment;
	protected TicketCommentCreateDateComparator ticketCommentComparator = mock(
		TicketCommentCreateDateComparator.class);
	protected TicketEntry ticketEntry = mock(TicketEntry.class);

}