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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the support region local service. This utility wraps {@link com.liferay.osb.service.impl.SupportRegionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportRegionLocalService
 * @see com.liferay.osb.service.base.SupportRegionLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.SupportRegionLocalServiceImpl
 * @generated
 */
public class SupportRegionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.SupportRegionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the support region to the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @return the support region that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportRegion addSupportRegion(
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSupportRegion(supportRegion);
	}

	/**
	* Creates a new support region with the primary key. Does not add the support region to the database.
	*
	* @param supportRegionId the primary key for the new support region
	* @return the new support region
	*/
	public static com.liferay.osb.model.SupportRegion createSupportRegion(
		long supportRegionId) {
		return getService().createSupportRegion(supportRegionId);
	}

	/**
	* Deletes the support region with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegionId the primary key of the support region
	* @return the support region that was removed
	* @throws PortalException if a support region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportRegion deleteSupportRegion(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSupportRegion(supportRegionId);
	}

	/**
	* Deletes the support region from the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @return the support region that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportRegion deleteSupportRegion(
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSupportRegion(supportRegion);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	public static com.liferay.osb.model.SupportRegion fetchSupportRegion(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSupportRegion(supportRegionId);
	}

	/**
	* Returns the support region with the primary key.
	*
	* @param supportRegionId the primary key of the support region
	* @return the support region
	* @throws PortalException if a support region with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportRegion getSupportRegion(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportRegion(supportRegionId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the support regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @return the range of support regions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportRegions(start, end);
	}

	/**
	* Returns the number of support regions.
	*
	* @return the number of support regions
	* @throws SystemException if a system exception occurred
	*/
	public static int getSupportRegionsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportRegionsCount();
	}

	/**
	* Updates the support region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @return the support region that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportRegion updateSupportRegion(
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSupportRegion(supportRegion);
	}

	/**
	* Updates the support region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @param merge whether to merge the support region with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the support region that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportRegion updateSupportRegion(
		com.liferay.osb.model.SupportRegion supportRegion, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSupportRegion(supportRegion, merge);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addAccountEntrySupportRegion(long accountEntryId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addAccountEntrySupportRegion(accountEntryId, supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addAccountEntrySupportRegion(long accountEntryId,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addAccountEntrySupportRegion(accountEntryId, supportRegion);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addAccountEntrySupportRegions(long accountEntryId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addAccountEntrySupportRegions(accountEntryId, supportRegionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addAccountEntrySupportRegions(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportRegion> SupportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addAccountEntrySupportRegions(accountEntryId, SupportRegions);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void clearAccountEntrySupportRegions(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearAccountEntrySupportRegions(accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteAccountEntrySupportRegion(long accountEntryId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteAccountEntrySupportRegion(accountEntryId, supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteAccountEntrySupportRegion(long accountEntryId,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteAccountEntrySupportRegion(accountEntryId, supportRegion);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteAccountEntrySupportRegions(long accountEntryId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteAccountEntrySupportRegions(accountEntryId, supportRegionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteAccountEntrySupportRegions(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportRegion> SupportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteAccountEntrySupportRegions(accountEntryId, SupportRegions);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> getAccountEntrySupportRegions(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEntrySupportRegions(accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> getAccountEntrySupportRegions(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAccountEntrySupportRegions(accountEntryId, start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> getAccountEntrySupportRegions(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAccountEntrySupportRegions(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static int getAccountEntrySupportRegionsCount(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEntrySupportRegionsCount(accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasAccountEntrySupportRegion(long accountEntryId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasAccountEntrySupportRegion(accountEntryId, supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasAccountEntrySupportRegions(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasAccountEntrySupportRegions(accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void setAccountEntrySupportRegions(long accountEntryId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setAccountEntrySupportRegions(accountEntryId, supportRegionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addPartnerEntrySupportRegion(long partnerEntryId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addPartnerEntrySupportRegion(partnerEntryId, supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addPartnerEntrySupportRegion(long partnerEntryId,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addPartnerEntrySupportRegion(partnerEntryId, supportRegion);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addPartnerEntrySupportRegions(long partnerEntryId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addPartnerEntrySupportRegions(partnerEntryId, supportRegionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addPartnerEntrySupportRegions(long partnerEntryId,
		java.util.List<com.liferay.osb.model.SupportRegion> SupportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addPartnerEntrySupportRegions(partnerEntryId, SupportRegions);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void clearPartnerEntrySupportRegions(long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearPartnerEntrySupportRegions(partnerEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deletePartnerEntrySupportRegion(long partnerEntryId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deletePartnerEntrySupportRegion(partnerEntryId, supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deletePartnerEntrySupportRegion(long partnerEntryId,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deletePartnerEntrySupportRegion(partnerEntryId, supportRegion);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deletePartnerEntrySupportRegions(long partnerEntryId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deletePartnerEntrySupportRegions(partnerEntryId, supportRegionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deletePartnerEntrySupportRegions(long partnerEntryId,
		java.util.List<com.liferay.osb.model.SupportRegion> SupportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deletePartnerEntrySupportRegions(partnerEntryId, SupportRegions);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> getPartnerEntrySupportRegions(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPartnerEntrySupportRegions(partnerEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> getPartnerEntrySupportRegions(
		long partnerEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getPartnerEntrySupportRegions(partnerEntryId, start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> getPartnerEntrySupportRegions(
		long partnerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getPartnerEntrySupportRegions(partnerEntryId, start, end,
			orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static int getPartnerEntrySupportRegionsCount(long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPartnerEntrySupportRegionsCount(partnerEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasPartnerEntrySupportRegion(long partnerEntryId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasPartnerEntrySupportRegion(partnerEntryId, supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasPartnerEntrySupportRegions(long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasPartnerEntrySupportRegions(partnerEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void setPartnerEntrySupportRegions(long partnerEntryId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setPartnerEntrySupportRegions(partnerEntryId, supportRegionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportTeamSupportRegion(long supportTeamId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addSupportTeamSupportRegion(supportTeamId, supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportTeamSupportRegion(long supportTeamId,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addSupportTeamSupportRegion(supportTeamId, supportRegion);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportTeamSupportRegions(long supportTeamId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addSupportTeamSupportRegions(supportTeamId, supportRegionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportTeamSupportRegions(long supportTeamId,
		java.util.List<com.liferay.osb.model.SupportRegion> SupportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addSupportTeamSupportRegions(supportTeamId, SupportRegions);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void clearSupportTeamSupportRegions(long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearSupportTeamSupportRegions(supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportTeamSupportRegion(long supportTeamId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteSupportTeamSupportRegion(supportTeamId, supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportTeamSupportRegion(long supportTeamId,
		com.liferay.osb.model.SupportRegion supportRegion)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteSupportTeamSupportRegion(supportTeamId, supportRegion);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportTeamSupportRegions(long supportTeamId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteSupportTeamSupportRegions(supportTeamId, supportRegionIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportTeamSupportRegions(long supportTeamId,
		java.util.List<com.liferay.osb.model.SupportRegion> SupportRegions)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteSupportTeamSupportRegions(supportTeamId, SupportRegions);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> getSupportTeamSupportRegions(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportTeamSupportRegions(supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> getSupportTeamSupportRegions(
		long supportTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSupportTeamSupportRegions(supportTeamId, start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> getSupportTeamSupportRegions(
		long supportTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSupportTeamSupportRegions(supportTeamId, start, end,
			orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static int getSupportTeamSupportRegionsCount(long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportTeamSupportRegionsCount(supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasSupportTeamSupportRegion(long supportTeamId,
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasSupportTeamSupportRegion(supportTeamId, supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasSupportTeamSupportRegions(long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasSupportTeamSupportRegions(supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void setSupportTeamSupportRegions(long supportTeamId,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setSupportTeamSupportRegions(supportTeamId, supportRegionIds);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public static java.lang.String getBeanIdentifier() {
		return getService().getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public static void setBeanIdentifier(java.lang.String beanIdentifier) {
		getService().setBeanIdentifier(beanIdentifier);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	public static com.liferay.osb.model.SupportRegion addSupportRegion(
		long userId, java.lang.String name, java.lang.String description,
		java.lang.String timeZoneId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addSupportRegion(userId, name, description, timeZoneId);
	}

	public static com.liferay.osb.model.SupportRegion fetchSupportRegionByName(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSupportRegionByName(name);
	}

	public static com.liferay.osb.model.SupportRegion updateSupportRegion(
		long supportRegionId, java.lang.String name,
		java.lang.String description, java.lang.String timeZoneId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateSupportRegion(supportRegionId, name, description,
			timeZoneId, supportTeamIds);
	}

	public static void clearService() {
		_service = null;
	}

	public static SupportRegionLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SupportRegionLocalService.class.getName());

			if (invokableLocalService instanceof SupportRegionLocalService) {
				_service = (SupportRegionLocalService)invokableLocalService;
			}
			else {
				_service = new SupportRegionLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SupportRegionLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(SupportRegionLocalService service) {
	}

	private static SupportRegionLocalService _service;
}