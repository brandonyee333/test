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

import com.liferay.osb.model.AccountCustomer;
import com.liferay.osb.model.AccountCustomerConstants;
import com.liferay.osb.model.AccountEntry;
import com.liferay.osb.model.AccountEntryConstants;
import com.liferay.osb.model.AccountWorker;
import com.liferay.osb.model.AccountWorkerConstants;
import com.liferay.osb.model.PartnerWorker;
import com.liferay.osb.model.PartnerWorkerConstants;
import com.liferay.osb.model.SupportWorker;
import com.liferay.osb.model.SupportWorkerConstants;
import com.liferay.osb.service.AccountCustomerLocalServiceUtil;
import com.liferay.osb.service.AccountEntryLocalServiceUtil;
import com.liferay.osb.service.AccountWorkerLocalServiceUtil;
import com.liferay.osb.service.PartnerWorkerLocalServiceUtil;
import com.liferay.osb.service.SupportWorkerLocalServiceUtil;
import com.liferay.osb.util.OSBConstants;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.OrganizationLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.math.RandomUtils;

import org.junit.Before;
import org.junit.runner.RunWith;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * @author Amos Fong
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(
	{
		AccountCustomerLocalServiceUtil.class,
		AccountEntryLocalServiceUtil.class, AccountWorkerLocalServiceUtil.class,
		OrganizationLocalServiceUtil.class, PartnerWorkerLocalServiceUtil.class,
		RoleLocalServiceUtil.class, SupportWorkerLocalServiceUtil.class
	})
public class BasePermissionTestCase extends PowerMockito {

	@Before
	public void setUp() throws Exception {
		setUpAccountCustomers();
		setUpAccountEntries();
		setUpAccountWorkers();
		setUpPartnerWorkers();
		setUpSupportWorkers();

		setUpAccountCustomerLocalServiceUtil();
		setUpAccountEntryLocalServiceUtil();
		setUpAccountWorkerLocalServiceUtil();
		setUpOrganizationLocalServiceUtil();
		setUpPartnerWorkerLocalServiceUtil();
		setUpRoleLocalServiceUtil();
		setUpSupportWorkerLocalServiceUtil();
	}

	protected PermissionChecker initPermissionChecker(long userId) {
		PermissionChecker permissionChecker = mock(PermissionChecker.class);

		when(
			permissionChecker.getUserId()
		).thenReturn(
			userId
		);

		if (userId == USER_ADMINISTRATOR_ID) {
			when(
				permissionChecker.isCompanyAdmin()
			).thenReturn(
				true
			);
		}

		return permissionChecker;
	}

	protected void setUpAccountCustomerLocalServiceUtil() throws Exception {
		mockStatic(AccountCustomerLocalServiceUtil.class);

		when(
			AccountCustomerLocalServiceUtil.getAccountCustomer(
				accountCustomerDeveloper.getUserId(),
				accountEntry.getAccountEntryId())
		).thenReturn(
			accountCustomerDeveloper
		);

		when(
			AccountCustomerLocalServiceUtil.getAccountCustomer(
				accountCustomerManager.getUserId(),
				accountEntry.getAccountEntryId())
		).thenReturn(
			accountCustomerManager
		);

		when(
			AccountCustomerLocalServiceUtil.getAccountCustomer(
				accountCustomerNoRole.getUserId(),
				accountEntry.getAccountEntryId())
		).thenReturn(
			accountCustomerNoRole
		);

		when(
			AccountCustomerLocalServiceUtil.getAccountCustomer(
				accountCustomerSales.getUserId(),
				accountEntry.getAccountEntryId())
		).thenReturn(
			accountCustomerSales
		);

		when(
			AccountCustomerLocalServiceUtil.getAccountCustomer(
				accountCustomerWatcher.getUserId(),
				accountEntry.getAccountEntryId())
		).thenReturn(
			accountCustomerWatcher
		);
	}

	protected void setUpAccountCustomers() {
		when(
			accountCustomerDeveloper.getRole()
		).thenReturn(
			AccountCustomerConstants.ROLE_DEVELOPER
		);

		when(
			accountCustomerDeveloper.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			accountCustomerManager.getRole()
		).thenReturn(
			AccountCustomerConstants.ROLE_MANAGER
		);

		when(
			accountCustomerManager.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			accountCustomerNoRole.getRole()
		).thenReturn(
			0
		);

		when(
			accountCustomerNoRole.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			accountCustomerSales.getRole()
		).thenReturn(
			AccountCustomerConstants.ROLE_SALES
		);

		when(
			accountCustomerSales.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			accountCustomerWatcher.getRole()
		).thenReturn(
			AccountCustomerConstants.ROLE_WATCHER
		);

		when(
			accountCustomerWatcher.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);
	}

	protected void setUpAccountEntries() {
		when(
			accountEntry.getAccountEntryId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			accountEntry.isPartnerManagedSupport()
		).thenReturn(
			false
		);

		when(
			partnerManagedSupportAccountEntry.getAccountEntryId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			partnerManagedSupportAccountEntry.getPartnerEntryId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			partnerManagedSupportAccountEntry.isPartnerManagedSupport()
		).thenReturn(
			true
		);

		when(
			restrictedAccountEntry.getAccountEntryId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			trialAccountEntry.getAccountEntryId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			trialAccountEntry.getType()
		).thenReturn(
			AccountEntryConstants.TYPE_TRIAL
		);
	}

	protected void setUpAccountEntryLocalServiceUtil() throws Exception {
		mockStatic(AccountEntryLocalServiceUtil.class);

		when(
			AccountEntryLocalServiceUtil.getAccountEntry(
				accountEntry.getAccountEntryId())
		).thenReturn(
			accountEntry
		);

		when(
			AccountEntryLocalServiceUtil.getAccountEntry(
				partnerManagedSupportAccountEntry.getAccountEntryId())
		).thenReturn(
			partnerManagedSupportAccountEntry
		);

		when(
			AccountEntryLocalServiceUtil.getAccountEntry(
				restrictedAccountEntry.getAccountEntryId())
		).thenReturn(
			restrictedAccountEntry
		);

		when(
			AccountEntryLocalServiceUtil.getAccountEntry(
				trialAccountEntry.getAccountEntryId())
		).thenReturn(
			trialAccountEntry
		);
	}

	protected void setUpAccountWorkerLocalServiceUtil() throws Exception {
		mockStatic(AccountWorkerLocalServiceUtil.class);

		when(
			AccountWorkerLocalServiceUtil.getAccountWorker(
				accountWorkerAdvocacySpecialist.getUserId(),
				accountEntry.getAccountEntryId())
		).thenReturn(
			accountWorkerAdvocacySpecialist
		);

		when(
			AccountWorkerLocalServiceUtil.getAccountWorker(
				accountWorkerManagedServices.getUserId(),
				accountEntry.getAccountEntryId())
		).thenReturn(
			accountWorkerManagedServices
		);

		when(
			AccountWorkerLocalServiceUtil.getAccountWorker(
				accountWorkerNoRole.getUserId(),
				accountEntry.getAccountEntryId())
		).thenReturn(
			accountWorkerNoRole
		);

		when(
			AccountWorkerLocalServiceUtil.getAccountWorker(
				accountWorkerSales.getUserId(),
				accountEntry.getAccountEntryId())
		).thenReturn(
			accountWorkerSales
		);
	}

	protected void setUpAccountWorkers() {
		when(
			accountWorkerAdvocacySpecialist.getRole()
		).thenReturn(
			AccountWorkerConstants.ROLE_ADVOCACY_SPECIALIST
		);

		when(
			accountWorkerAdvocacySpecialist.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			accountWorkerManagedServices.getRole()
		).thenReturn(
			AccountWorkerConstants.ROLE_MANAGED_SERVICES
		);

		when(
			accountWorkerManagedServices.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			accountWorkerNoRole.getRole()
		).thenReturn(
			0
		);

		when(
			accountWorkerNoRole.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			accountWorkerSales.getRole()
		).thenReturn(
			AccountWorkerConstants.ROLE_SALES
		);

		when(
			accountWorkerSales.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);
	}

	protected void setUpOrganizationLocalServiceUtil() throws Exception {
		mockStatic(OrganizationLocalServiceUtil.class);

		when(
			OrganizationLocalServiceUtil.hasUserOrganization(
				USER_LIFERAY_EMPLOYEE_ID,
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID)
		).thenReturn(
			true
		);

		when(
			OrganizationLocalServiceUtil.hasUserOrganization(
				clockedOutSupportWorker.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID)
		).thenReturn(
			true
		);

		when(
			OrganizationLocalServiceUtil.hasUserOrganization(
				supportWorkerDeveloper.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID)
		).thenReturn(
			true
		);

		when(
			OrganizationLocalServiceUtil.hasUserOrganization(
				supportWorkerManager.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID)
		).thenReturn(
			true
		);

		when(
			OrganizationLocalServiceUtil.hasUserOrganization(
				supportWorkerNoRole.getUserId(),
				OSBConstants.ORGANIZATION_LIFERAY_INC_ID)
		).thenReturn(
			true
		);
	}

	protected void setUpPartnerWorkerLocalServiceUtil() throws Exception {
		mockStatic(PartnerWorkerLocalServiceUtil.class);

		when(
			PartnerWorkerLocalServiceUtil.getPartnerWorker(
				partnerWorkerManager.getUserId(),
				partnerManagedSupportAccountEntry.getPartnerEntryId())
		).thenReturn(
			partnerWorkerManager
		);

		when(
			PartnerWorkerLocalServiceUtil.getPartnerWorker(
				partnerWorkerMember.getUserId(),
				partnerManagedSupportAccountEntry.getPartnerEntryId())
		).thenReturn(
			partnerWorkerMember
		);

		when(
			PartnerWorkerLocalServiceUtil.getPartnerWorker(
				partnerWorkerNoRole.getUserId(),
				partnerManagedSupportAccountEntry.getPartnerEntryId())
		).thenReturn(
			partnerWorkerNoRole
		);

		when(
			PartnerWorkerLocalServiceUtil.getPartnerWorker(
				partnerWorkerWatcher.getUserId(),
				partnerManagedSupportAccountEntry.getPartnerEntryId())
		).thenReturn(
			partnerWorkerWatcher
		);

		List<PartnerWorker> partnerWorkerManagerList =
			new ArrayList<PartnerWorker>();

		partnerWorkerManagerList.add(partnerWorkerManager);

		when(
			PartnerWorkerLocalServiceUtil.getUserPartnerWorkers(
				partnerWorkerManager.getUserId())
		).thenReturn(
			partnerWorkerManagerList
		);

		List<PartnerWorker> partnerWorkerMemberList =
			new ArrayList<PartnerWorker>();

		partnerWorkerMemberList.add(partnerWorkerMember);

		when(
			PartnerWorkerLocalServiceUtil.getUserPartnerWorkers(
				partnerWorkerMember.getUserId())
		).thenReturn(
			partnerWorkerMemberList
		);

		when(
			PartnerWorkerLocalServiceUtil.hasPartnerWorker(
				partnerWorkerManager.getUserId(),
				partnerManagedSupportAccountEntry.getPartnerEntryId())
		).thenReturn(
			true
		);

		when(
			PartnerWorkerLocalServiceUtil.hasPartnerWorker(
				partnerWorkerMember.getUserId(),
				partnerManagedSupportAccountEntry.getPartnerEntryId())
		).thenReturn(
			true
		);

		when(
			PartnerWorkerLocalServiceUtil.hasPartnerWorker(
				partnerWorkerNoRole.getUserId(),
				partnerManagedSupportAccountEntry.getPartnerEntryId())
		).thenReturn(
			true
		);

		when(
			PartnerWorkerLocalServiceUtil.hasPartnerWorker(
				partnerWorkerWatcher.getUserId(),
				partnerManagedSupportAccountEntry.getPartnerEntryId())
		).thenReturn(
			true
		);
	}

	protected void setUpPartnerWorkers() {
		when(
			partnerWorkerManager.getRole()
		).thenReturn(
			PartnerWorkerConstants.ROLE_MANAGER
		);

		when(
			partnerWorkerManager.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			partnerWorkerMember.getRole()
		).thenReturn(
			PartnerWorkerConstants.ROLE_MEMBER
		);

		when(
			partnerWorkerMember.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			partnerWorkerNoRole.getRole()
		).thenReturn(
			0
		);

		when(
			partnerWorkerNoRole.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			partnerWorkerWatcher.getRole()
		).thenReturn(
			PartnerWorkerConstants.ROLE_WATCHER
		);

		when(
			partnerWorkerWatcher.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);
	}

	protected void setUpRoleLocalServiceUtil() throws Exception {
		mockStatic(RoleLocalServiceUtil.class);

		when(
			RoleLocalServiceUtil.hasUserRole(
				USER_OSB_ADMIN_ID, OSBConstants.ROLE_OSB_ADMINISTRATOR_ID)
		).thenReturn(
			true
		);

		when(
			RoleLocalServiceUtil.hasUserRole(
				USER_OSB_ACCOUNT_ADMIN_ID,
				OSBConstants.ROLE_OSB_ACCOUNT_ADMIN_ID)
		).thenReturn(
			true
		);

		when(
			RoleLocalServiceUtil.hasUserRole(
				USER_OSB_TRIAL_LICENSE_ADMIN_ID,
				OSBConstants.ROLE_OSB_TRIAL_LICENSE_ADMIN_ID)
		).thenReturn(
			true
		);
	}

	protected void setUpSupportWorkerLocalServiceUtil() throws Exception {
		mockStatic(SupportWorkerLocalServiceUtil.class);

		List<SupportWorker> clockedOutSupportWorkerList =
			new ArrayList<SupportWorker>();

		clockedOutSupportWorkerList.add(clockedOutSupportWorker);

		when(
			SupportWorkerLocalServiceUtil.getUserSupportWorkers(
				clockedOutSupportWorker.getUserId())
		).thenReturn(
			clockedOutSupportWorkerList
		);

		List<SupportWorker> supportWorkerDeveloperList =
			new ArrayList<SupportWorker>();

		supportWorkerDeveloperList.add(supportWorkerDeveloper);

		when(
			SupportWorkerLocalServiceUtil.getUserSupportWorkers(
				supportWorkerDeveloper.getUserId())
		).thenReturn(
			supportWorkerDeveloperList
		);

		List<SupportWorker> supportWorkerManagerList =
			new ArrayList<SupportWorker>();

		supportWorkerManagerList.add(supportWorkerManager);

		when(
			SupportWorkerLocalServiceUtil.getUserSupportWorkers(
				supportWorkerManager.getUserId())
		).thenReturn(
			supportWorkerManagerList
		);

		List<SupportWorker> supportWorkerNoRoleList =
			new ArrayList<SupportWorker>();

		supportWorkerNoRoleList.add(supportWorkerNoRole);

		when(
			SupportWorkerLocalServiceUtil.getUserSupportWorkers(
				supportWorkerNoRole.getUserId())
		).thenReturn(
			supportWorkerNoRoleList
		);
	}

	protected void setUpSupportWorkers() {
		when(
			clockedOutSupportWorker.isClockedIn()
		).thenReturn(
			false
		);

		when(
			clockedOutSupportWorker.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			supportWorkerDeveloper.isClockedIn()
		).thenReturn(
			true
		);

		when(
			supportWorkerDeveloper.getRole()
		).thenReturn(
			SupportWorkerConstants.ROLE_DEVELOPER
		);

		when(
			supportWorkerDeveloper.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			supportWorkerManager.isClockedIn()
		).thenReturn(
			true
		);

		when(
			supportWorkerManager.getRole()
		).thenReturn(
			SupportWorkerConstants.ROLE_MANAGER
		);

		when(
			supportWorkerManager.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			supportWorkerNoRole.isClockedIn()
		).thenReturn(
			true
		);

		when(
			supportWorkerNoRole.getRole()
		).thenReturn(
			SupportWorkerConstants.ROLE_NONE
		);

		when(
			supportWorkerNoRole.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);

		when(
			supportWorkerWatcher.isClockedIn()
		).thenReturn(
			true
		);

		when(
			supportWorkerWatcher.getRole()
		).thenReturn(
			SupportWorkerConstants.ROLE_WATCHER
		);

		when(
			supportWorkerWatcher.getUserId()
		).thenReturn(
			RandomUtils.nextLong()
		);
	}

	protected static final long USER_ADMINISTRATOR_ID = RandomUtils.nextLong();

	protected static final long USER_LIFERAY_EMPLOYEE_ID =
		RandomUtils.nextLong();

	protected static final long USER_OSB_ACCOUNT_ADMIN_ID =
		RandomUtils.nextLong();

	protected static final long USER_OSB_ADMIN_ID = RandomUtils.nextLong();

	protected static final long USER_OSB_TRIAL_LICENSE_ADMIN_ID =
		RandomUtils.nextLong();

	protected AccountCustomer accountCustomerDeveloper = mock(
		AccountCustomer.class);
	protected AccountCustomer accountCustomerManager = mock(
		AccountCustomer.class);
	protected AccountCustomer accountCustomerNoRole = mock(
		AccountCustomer.class);
	protected AccountCustomer accountCustomerSales = mock(
		AccountCustomer.class);
	protected AccountCustomer accountCustomerWatcher = mock(
		AccountCustomer.class);
	protected AccountEntry accountEntry = mock(AccountEntry.class);
	protected AccountWorker accountWorkerAdvocacySpecialist = mock(
		AccountWorker.class);
	protected AccountWorker accountWorkerManagedServices = mock(
		AccountWorker.class);
	protected AccountWorker accountWorkerNoRole = mock(AccountWorker.class);
	protected AccountWorker accountWorkerSales = mock(AccountWorker.class);
	protected SupportWorker clockedOutSupportWorker = mock(SupportWorker.class);
	protected AccountEntry partnerManagedSupportAccountEntry = mock(
		AccountEntry.class);
	protected PartnerWorker partnerWorkerManager = mock(PartnerWorker.class);
	protected PartnerWorker partnerWorkerMember = mock(PartnerWorker.class);
	protected PartnerWorker partnerWorkerNoRole = mock(PartnerWorker.class);
	protected PartnerWorker partnerWorkerWatcher = mock(PartnerWorker.class);
	protected AccountEntry restrictedAccountEntry = mock(AccountEntry.class);
	protected SupportWorker supportWorkerDeveloper = mock(SupportWorker.class);
	protected SupportWorker supportWorkerManager = mock(SupportWorker.class);
	protected SupportWorker supportWorkerNoRole = mock(SupportWorker.class);
	protected SupportWorker supportWorkerWatcher = mock(SupportWorker.class);
	protected AccountEntry trialAccountEntry = mock(AccountEntry.class);

}