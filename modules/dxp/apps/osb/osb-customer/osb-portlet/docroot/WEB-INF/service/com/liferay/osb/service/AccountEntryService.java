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

package com.liferay.osb.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.AccountEntry;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.security.access.control.AccessControlled;
import com.liferay.portal.kernel.service.BaseService;
import com.liferay.portal.kernel.service.InvokableService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Provides the remote service interface for AccountEntry. Methods of this
 * service are expected to have security checks based on the propagated JAAS
 * credentials because this service can be accessed remotely.
 *
 * @author Brian Wing Shun Chan
 * @see AccountEntryServiceUtil
 * @see com.liferay.osb.service.base.AccountEntryServiceBaseImpl
 * @see com.liferay.osb.service.impl.AccountEntryServiceImpl
 * @generated
 */
@AccessControlled
@JSONWebService
@ProviderType
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
	PortalException.class, SystemException.class})
public interface AccountEntryService extends BaseService, InvokableService {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountEntryServiceUtil} to access the account entry remote service. Add custom service methods to {@link com.liferay.osb.service.impl.AccountEntryServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public AccountEntry deleteAccountEntry(long accountEntryId)
		throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry fetchCorpProjectAccountEntry(
		java.lang.String corpProjectUuid) throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry fetchCorpProjectIdAccountEntry(long corpProjectId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry getAccountEntry(long accountEntryId)
		throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry getAccountEntryByCode(java.lang.String code)
		throws PortalException;

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public AccountEntry getCorpProjectAccountEntry(
		java.lang.String corpProjectUuid) throws PortalException;

	public AccountEntry updateInstructions(long accountEntryId,
		java.lang.String instructions) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int searchCount(java.lang.Long createUserId, int createDateGTDay,
		int createDateGTMonth, int createDateGTYear, int createDateLTDay,
		int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear,
		java.lang.String dossieraAccountKey, java.lang.String corpEntryName,
		java.lang.String name, java.lang.String code, int[] industries,
		java.lang.Boolean partnerManagedSupport, int[] tiers, int[] statuses,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String partnerEntryCode, java.lang.String street,
		java.lang.Long countryId, java.lang.Long regionId,
		java.lang.String city, java.lang.String zip,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) throws PortalException;

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable;

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public java.lang.String getOSGiServiceIdentifier();

	@JSONWebService
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> getAccountEntries(java.lang.String userUuid,
		long[] productEntryIds) throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<AccountEntry> search(java.lang.Long createUserId,
		int createDateGTDay, int createDateGTMonth, int createDateGTYear,
		int createDateLTDay, int createDateLTMonth, int createDateLTYear,
		java.lang.Long modifiedUserId, int modifiedDateGTDay,
		int modifiedDateGTMonth, int modifiedDateGTYear, int modifiedDateLTDay,
		int modifiedDateLTMonth, int modifiedDateLTYear,
		java.lang.String dossieraAccountKey, java.lang.String corpEntryName,
		java.lang.String name, java.lang.String code, int[] industries,
		java.lang.Boolean partnerManagedSupport, int[] tiers, int[] statuses,
		java.lang.String instructions, java.lang.String notes,
		java.lang.String partnerEntryCode, java.lang.String street,
		java.lang.Long countryId, java.lang.Long regionId,
		java.lang.String city, java.lang.String zip,
		LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end, OrderByComparator obc)
		throws PortalException;
}