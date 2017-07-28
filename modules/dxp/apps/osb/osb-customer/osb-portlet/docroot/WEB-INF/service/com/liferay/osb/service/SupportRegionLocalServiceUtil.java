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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.service.InvokableLocalService;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for SupportRegion. This utility wraps
 * {@link com.liferay.osb.service.impl.SupportRegionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SupportRegionLocalService
 * @see com.liferay.osb.service.base.SupportRegionLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.SupportRegionLocalServiceImpl
 * @generated
 */
@ProviderType
public class SupportRegionLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.SupportRegionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasAccountEntrySupportRegion(long accountEntryId,
		long supportRegionId) {
		return getService()
				   .hasAccountEntrySupportRegion(accountEntryId, supportRegionId);
	}

	public static boolean hasAccountEntrySupportRegions(long accountEntryId) {
		return getService().hasAccountEntrySupportRegions(accountEntryId);
	}

	public static boolean hasPartnerEntrySupportRegion(long partnerEntryId,
		long supportRegionId) {
		return getService()
				   .hasPartnerEntrySupportRegion(partnerEntryId, supportRegionId);
	}

	public static boolean hasPartnerEntrySupportRegions(long partnerEntryId) {
		return getService().hasPartnerEntrySupportRegions(partnerEntryId);
	}

	public static boolean hasSupportTeamSupportRegion(long supportTeamId,
		long supportRegionId) {
		return getService()
				   .hasSupportTeamSupportRegion(supportTeamId, supportRegionId);
	}

	public static boolean hasSupportTeamSupportRegions(long supportTeamId) {
		return getService().hasSupportTeamSupportRegions(supportTeamId);
	}

	/**
	* Adds the support region to the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @return the support region that was added
	*/
	public static com.liferay.osb.model.SupportRegion addSupportRegion(
		com.liferay.osb.model.SupportRegion supportRegion) {
		return getService().addSupportRegion(supportRegion);
	}

	public static com.liferay.osb.model.SupportRegion addSupportRegion(
		long userId, java.lang.String name, java.lang.String description,
		java.lang.String timeZoneId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addSupportRegion(userId, name, description, timeZoneId);
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
	* Deletes the support region from the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @return the support region that was removed
	*/
	public static com.liferay.osb.model.SupportRegion deleteSupportRegion(
		com.liferay.osb.model.SupportRegion supportRegion) {
		return getService().deleteSupportRegion(supportRegion);
	}

	/**
	* Deletes the support region with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegionId the primary key of the support region
	* @return the support region that was removed
	* @throws PortalException if a support region with the primary key could not be found
	*/
	public static com.liferay.osb.model.SupportRegion deleteSupportRegion(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSupportRegion(supportRegionId);
	}

	public static com.liferay.osb.model.SupportRegion fetchSupportRegion(
		long supportRegionId) {
		return getService().fetchSupportRegion(supportRegionId);
	}

	public static com.liferay.osb.model.SupportRegion fetchSupportRegionByName(
		java.lang.String name) {
		return getService().fetchSupportRegionByName(name);
	}

	/**
	* Returns the support region with the primary key.
	*
	* @param supportRegionId the primary key of the support region
	* @return the support region
	* @throws PortalException if a support region with the primary key could not be found
	*/
	public static com.liferay.osb.model.SupportRegion getSupportRegion(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSupportRegion(supportRegionId);
	}

	/**
	* Updates the support region in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportRegion the support region
	* @return the support region that was updated
	*/
	public static com.liferay.osb.model.SupportRegion updateSupportRegion(
		com.liferay.osb.model.SupportRegion supportRegion) {
		return getService().updateSupportRegion(supportRegion);
	}

	public static com.liferay.osb.model.SupportRegion updateSupportRegion(
		long supportRegionId, java.lang.String name,
		java.lang.String description, java.lang.String timeZoneId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateSupportRegion(supportRegionId, name, description,
			timeZoneId, supportTeamIds);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	public static int getAccountEntrySupportRegionsCount(long accountEntryId) {
		return getService().getAccountEntrySupportRegionsCount(accountEntryId);
	}

	public static int getPartnerEntrySupportRegionsCount(long partnerEntryId) {
		return getService().getPartnerEntrySupportRegionsCount(partnerEntryId);
	}

	/**
	* Returns the number of support regions.
	*
	* @return the number of support regions
	*/
	public static int getSupportRegionsCount() {
		return getService().getSupportRegionsCount();
	}

	public static int getSupportTeamSupportRegionsCount(long supportTeamId) {
		return getService().getSupportTeamSupportRegionsCount(supportTeamId);
	}

	public static java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return getService().invokeMethod(name, parameterTypes, arguments);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static java.lang.String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	public static java.util.List<com.liferay.osb.model.SupportRegion> getAccountEntrySupportRegions(
		long accountEntryId) {
		return getService().getAccountEntrySupportRegions(accountEntryId);
	}

	public static java.util.List<com.liferay.osb.model.SupportRegion> getAccountEntrySupportRegions(
		long accountEntryId, int start, int end) {
		return getService()
				   .getAccountEntrySupportRegions(accountEntryId, start, end);
	}

	public static java.util.List<com.liferay.osb.model.SupportRegion> getAccountEntrySupportRegions(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.SupportRegion> orderByComparator) {
		return getService()
				   .getAccountEntrySupportRegions(accountEntryId, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.osb.model.SupportRegion> getPartnerEntrySupportRegions(
		long partnerEntryId) {
		return getService().getPartnerEntrySupportRegions(partnerEntryId);
	}

	public static java.util.List<com.liferay.osb.model.SupportRegion> getPartnerEntrySupportRegions(
		long partnerEntryId, int start, int end) {
		return getService()
				   .getPartnerEntrySupportRegions(partnerEntryId, start, end);
	}

	public static java.util.List<com.liferay.osb.model.SupportRegion> getPartnerEntrySupportRegions(
		long partnerEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.SupportRegion> orderByComparator) {
		return getService()
				   .getPartnerEntrySupportRegions(partnerEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the support regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @return the range of support regions
	*/
	public static java.util.List<com.liferay.osb.model.SupportRegion> getSupportRegions(
		int start, int end) {
		return getService().getSupportRegions(start, end);
	}

	public static java.util.List<com.liferay.osb.model.SupportRegion> getSupportTeamSupportRegions(
		long supportTeamId) {
		return getService().getSupportTeamSupportRegions(supportTeamId);
	}

	public static java.util.List<com.liferay.osb.model.SupportRegion> getSupportTeamSupportRegions(
		long supportTeamId, int start, int end) {
		return getService()
				   .getSupportTeamSupportRegions(supportTeamId, start, end);
	}

	public static java.util.List<com.liferay.osb.model.SupportRegion> getSupportTeamSupportRegions(
		long supportTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.SupportRegion> orderByComparator) {
		return getService()
				   .getSupportTeamSupportRegions(supportTeamId, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	/**
	* Returns the accountEntryIds of the account entries associated with the support region.
	*
	* @param supportRegionId the supportRegionId of the support region
	* @return long[] the accountEntryIds of account entries associated with the support region
	*/
	public static long[] getAccountEntryPrimaryKeys(long supportRegionId) {
		return getService().getAccountEntryPrimaryKeys(supportRegionId);
	}

	/**
	* Returns the partnerEntryIds of the partner entries associated with the support region.
	*
	* @param supportRegionId the supportRegionId of the support region
	* @return long[] the partnerEntryIds of partner entries associated with the support region
	*/
	public static long[] getPartnerEntryPrimaryKeys(long supportRegionId) {
		return getService().getPartnerEntryPrimaryKeys(supportRegionId);
	}

	/**
	* Returns the supportTeamIds of the support teams associated with the support region.
	*
	* @param supportRegionId the supportRegionId of the support region
	* @return long[] the supportTeamIds of support teams associated with the support region
	*/
	public static long[] getSupportTeamPrimaryKeys(long supportRegionId) {
		return getService().getSupportTeamPrimaryKeys(supportRegionId);
	}

	public static void addAccountEntrySupportRegion(long accountEntryId,
		com.liferay.osb.model.SupportRegion supportRegion) {
		getService().addAccountEntrySupportRegion(accountEntryId, supportRegion);
	}

	public static void addAccountEntrySupportRegion(long accountEntryId,
		long supportRegionId) {
		getService()
			.addAccountEntrySupportRegion(accountEntryId, supportRegionId);
	}

	public static void addAccountEntrySupportRegions(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions) {
		getService()
			.addAccountEntrySupportRegions(accountEntryId, supportRegions);
	}

	public static void addAccountEntrySupportRegions(long accountEntryId,
		long[] supportRegionIds) {
		getService()
			.addAccountEntrySupportRegions(accountEntryId, supportRegionIds);
	}

	public static void addPartnerEntrySupportRegion(long partnerEntryId,
		com.liferay.osb.model.SupportRegion supportRegion) {
		getService().addPartnerEntrySupportRegion(partnerEntryId, supportRegion);
	}

	public static void addPartnerEntrySupportRegion(long partnerEntryId,
		long supportRegionId) {
		getService()
			.addPartnerEntrySupportRegion(partnerEntryId, supportRegionId);
	}

	public static void addPartnerEntrySupportRegions(long partnerEntryId,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions) {
		getService()
			.addPartnerEntrySupportRegions(partnerEntryId, supportRegions);
	}

	public static void addPartnerEntrySupportRegions(long partnerEntryId,
		long[] supportRegionIds) {
		getService()
			.addPartnerEntrySupportRegions(partnerEntryId, supportRegionIds);
	}

	public static void addSupportTeamSupportRegion(long supportTeamId,
		com.liferay.osb.model.SupportRegion supportRegion) {
		getService().addSupportTeamSupportRegion(supportTeamId, supportRegion);
	}

	public static void addSupportTeamSupportRegion(long supportTeamId,
		long supportRegionId) {
		getService().addSupportTeamSupportRegion(supportTeamId, supportRegionId);
	}

	public static void addSupportTeamSupportRegions(long supportTeamId,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions) {
		getService().addSupportTeamSupportRegions(supportTeamId, supportRegions);
	}

	public static void addSupportTeamSupportRegions(long supportTeamId,
		long[] supportRegionIds) {
		getService()
			.addSupportTeamSupportRegions(supportTeamId, supportRegionIds);
	}

	public static void clearAccountEntrySupportRegions(long accountEntryId) {
		getService().clearAccountEntrySupportRegions(accountEntryId);
	}

	public static void clearPartnerEntrySupportRegions(long partnerEntryId) {
		getService().clearPartnerEntrySupportRegions(partnerEntryId);
	}

	public static void clearSupportTeamSupportRegions(long supportTeamId) {
		getService().clearSupportTeamSupportRegions(supportTeamId);
	}

	public static void deleteAccountEntrySupportRegion(long accountEntryId,
		com.liferay.osb.model.SupportRegion supportRegion) {
		getService()
			.deleteAccountEntrySupportRegion(accountEntryId, supportRegion);
	}

	public static void deleteAccountEntrySupportRegion(long accountEntryId,
		long supportRegionId) {
		getService()
			.deleteAccountEntrySupportRegion(accountEntryId, supportRegionId);
	}

	public static void deleteAccountEntrySupportRegions(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions) {
		getService()
			.deleteAccountEntrySupportRegions(accountEntryId, supportRegions);
	}

	public static void deleteAccountEntrySupportRegions(long accountEntryId,
		long[] supportRegionIds) {
		getService()
			.deleteAccountEntrySupportRegions(accountEntryId, supportRegionIds);
	}

	public static void deletePartnerEntrySupportRegion(long partnerEntryId,
		com.liferay.osb.model.SupportRegion supportRegion) {
		getService()
			.deletePartnerEntrySupportRegion(partnerEntryId, supportRegion);
	}

	public static void deletePartnerEntrySupportRegion(long partnerEntryId,
		long supportRegionId) {
		getService()
			.deletePartnerEntrySupportRegion(partnerEntryId, supportRegionId);
	}

	public static void deletePartnerEntrySupportRegions(long partnerEntryId,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions) {
		getService()
			.deletePartnerEntrySupportRegions(partnerEntryId, supportRegions);
	}

	public static void deletePartnerEntrySupportRegions(long partnerEntryId,
		long[] supportRegionIds) {
		getService()
			.deletePartnerEntrySupportRegions(partnerEntryId, supportRegionIds);
	}

	public static void deleteSupportTeamSupportRegion(long supportTeamId,
		com.liferay.osb.model.SupportRegion supportRegion) {
		getService().deleteSupportTeamSupportRegion(supportTeamId, supportRegion);
	}

	public static void deleteSupportTeamSupportRegion(long supportTeamId,
		long supportRegionId) {
		getService()
			.deleteSupportTeamSupportRegion(supportTeamId, supportRegionId);
	}

	public static void deleteSupportTeamSupportRegions(long supportTeamId,
		java.util.List<com.liferay.osb.model.SupportRegion> supportRegions) {
		getService()
			.deleteSupportTeamSupportRegions(supportTeamId, supportRegions);
	}

	public static void deleteSupportTeamSupportRegions(long supportTeamId,
		long[] supportRegionIds) {
		getService()
			.deleteSupportTeamSupportRegions(supportTeamId, supportRegionIds);
	}

	public static void setAccountEntrySupportRegions(long accountEntryId,
		long[] supportRegionIds) {
		getService()
			.setAccountEntrySupportRegions(accountEntryId, supportRegionIds);
	}

	public static void setPartnerEntrySupportRegions(long partnerEntryId,
		long[] supportRegionIds) {
		getService()
			.setPartnerEntrySupportRegions(partnerEntryId, supportRegionIds);
	}

	public static void setSupportTeamSupportRegions(long supportTeamId,
		long[] supportRegionIds) {
		getService()
			.setSupportTeamSupportRegions(supportTeamId, supportRegionIds);
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

	private static SupportRegionLocalService _service;
}