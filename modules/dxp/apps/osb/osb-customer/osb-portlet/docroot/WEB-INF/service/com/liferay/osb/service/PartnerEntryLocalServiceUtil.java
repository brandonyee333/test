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
 * Provides the local service utility for PartnerEntry. This utility wraps
 * {@link com.liferay.osb.service.impl.PartnerEntryLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see PartnerEntryLocalService
 * @see com.liferay.osb.service.base.PartnerEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.PartnerEntryLocalServiceImpl
 * @generated
 */
@ProviderType
public class PartnerEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.PartnerEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasSupportRegionPartnerEntries(long supportRegionId) {
		return getService().hasSupportRegionPartnerEntries(supportRegionId);
	}

	public static boolean hasSupportRegionPartnerEntry(long supportRegionId,
		long partnerEntryId) {
		return getService()
				   .hasSupportRegionPartnerEntry(supportRegionId, partnerEntryId);
	}

	/**
	* Adds the partner entry to the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @return the partner entry that was added
	*/
	public static com.liferay.osb.model.PartnerEntry addPartnerEntry(
		com.liferay.osb.model.PartnerEntry partnerEntry) {
		return getService().addPartnerEntry(partnerEntry);
	}

	public static com.liferay.osb.model.PartnerEntry addPartnerEntry(
		long userId, long parentPartnerEntryId,
		java.lang.String dossieraAccountKey, java.lang.String jiraProjectKey,
		java.lang.String code, java.lang.String notes, long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addPartnerEntry(userId, parentPartnerEntryId,
			dossieraAccountKey, jiraProjectKey, code, notes, supportRegionIds);
	}

	/**
	* Creates a new partner entry with the primary key. Does not add the partner entry to the database.
	*
	* @param partnerEntryId the primary key for the new partner entry
	* @return the new partner entry
	*/
	public static com.liferay.osb.model.PartnerEntry createPartnerEntry(
		long partnerEntryId) {
		return getService().createPartnerEntry(partnerEntryId);
	}

	/**
	* Deletes the partner entry from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @return the partner entry that was removed
	*/
	public static com.liferay.osb.model.PartnerEntry deletePartnerEntry(
		com.liferay.osb.model.PartnerEntry partnerEntry) {
		return getService().deletePartnerEntry(partnerEntry);
	}

	/**
	* Deletes the partner entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry that was removed
	* @throws PortalException if a partner entry with the primary key could not be found
	*/
	public static com.liferay.osb.model.PartnerEntry deletePartnerEntry(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePartnerEntry(partnerEntryId);
	}

	public static com.liferay.osb.model.PartnerEntry fetchPartnerEntry(
		java.lang.String dossieraAccountKey) {
		return getService().fetchPartnerEntry(dossieraAccountKey);
	}

	public static com.liferay.osb.model.PartnerEntry fetchPartnerEntry(
		long partnerEntryId) {
		return getService().fetchPartnerEntry(partnerEntryId);
	}

	/**
	* Returns the partner entry with the primary key.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry
	* @throws PortalException if a partner entry with the primary key could not be found
	*/
	public static com.liferay.osb.model.PartnerEntry getPartnerEntry(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPartnerEntry(partnerEntryId);
	}

	public static com.liferay.osb.model.PartnerEntry getPartnerEntryByCode(
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPartnerEntryByCode(code);
	}

	/**
	* Updates the partner entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @return the partner entry that was updated
	*/
	public static com.liferay.osb.model.PartnerEntry updatePartnerEntry(
		com.liferay.osb.model.PartnerEntry partnerEntry) {
		return getService().updatePartnerEntry(partnerEntry);
	}

	public static com.liferay.osb.model.PartnerEntry updatePartnerEntry(
		long userId, long partnerEntryId, java.lang.String dossieraAccountKey,
		java.lang.String jiraProjectKey, java.lang.String code,
		java.lang.String notes, int status, long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updatePartnerEntry(userId, partnerEntryId,
			dossieraAccountKey, jiraProjectKey, code, notes, status,
			supportRegionIds);
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

	/**
	* Returns the number of partner entries.
	*
	* @return the number of partner entries
	*/
	public static int getPartnerEntriesCount() {
		return getService().getPartnerEntriesCount();
	}

	public static int getSupportRegionPartnerEntriesCount(long supportRegionId) {
		return getService().getSupportRegionPartnerEntriesCount(supportRegionId);
	}

	public static int searchCount(java.lang.String code, int[] statuses,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator) {
		return getService().searchCount(code, statuses, params, andOperator);
	}

	public static int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params) {
		return getService().searchCount(keywords, params);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.liferay.osb.model.PartnerEntry> getChildPartnerEntries(
		long partnerEntryId, boolean recursive) {
		return getService().getChildPartnerEntries(partnerEntryId, recursive);
	}

	/**
	* Returns a range of all the partner entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.PartnerEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @return the range of partner entries
	*/
	public static java.util.List<com.liferay.osb.model.PartnerEntry> getPartnerEntries(
		int start, int end) {
		return getService().getPartnerEntries(start, end);
	}

	public static java.util.List<com.liferay.osb.model.PartnerEntry> getSupportRegionPartnerEntries(
		long supportRegionId) {
		return getService().getSupportRegionPartnerEntries(supportRegionId);
	}

	public static java.util.List<com.liferay.osb.model.PartnerEntry> getSupportRegionPartnerEntries(
		long supportRegionId, int start, int end) {
		return getService()
				   .getSupportRegionPartnerEntries(supportRegionId, start, end);
	}

	public static java.util.List<com.liferay.osb.model.PartnerEntry> getSupportRegionPartnerEntries(
		long supportRegionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.PartnerEntry> orderByComparator) {
		return getService()
				   .getSupportRegionPartnerEntries(supportRegionId, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.osb.model.PartnerEntry> getUserPartnerEntries(
		long userId, int start, int end) {
		return getService().getUserPartnerEntries(userId, start, end);
	}

	public static java.util.List<com.liferay.osb.model.PartnerEntry> search(
		java.lang.String code, int[] statuses,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end) {
		return getService()
				   .search(code, statuses, params, andOperator, start, end);
	}

	public static java.util.List<com.liferay.osb.model.PartnerEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end) {
		return getService().search(keywords, params, start, end);
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
	* Returns the supportRegionIds of the support regions associated with the partner entry.
	*
	* @param partnerEntryId the partnerEntryId of the partner entry
	* @return long[] the supportRegionIds of support regions associated with the partner entry
	*/
	public static long[] getSupportRegionPrimaryKeys(long partnerEntryId) {
		return getService().getSupportRegionPrimaryKeys(partnerEntryId);
	}

	public static void addSupportRegionPartnerEntries(long supportRegionId,
		java.util.List<com.liferay.osb.model.PartnerEntry> partnerEntries) {
		getService()
			.addSupportRegionPartnerEntries(supportRegionId, partnerEntries);
	}

	public static void addSupportRegionPartnerEntries(long supportRegionId,
		long[] partnerEntryIds) {
		getService()
			.addSupportRegionPartnerEntries(supportRegionId, partnerEntryIds);
	}

	public static void addSupportRegionPartnerEntry(long supportRegionId,
		com.liferay.osb.model.PartnerEntry partnerEntry) {
		getService().addSupportRegionPartnerEntry(supportRegionId, partnerEntry);
	}

	public static void addSupportRegionPartnerEntry(long supportRegionId,
		long partnerEntryId) {
		getService()
			.addSupportRegionPartnerEntry(supportRegionId, partnerEntryId);
	}

	public static void clearSupportRegionPartnerEntries(long supportRegionId) {
		getService().clearSupportRegionPartnerEntries(supportRegionId);
	}

	public static void deleteSupportRegionPartnerEntries(long supportRegionId,
		java.util.List<com.liferay.osb.model.PartnerEntry> partnerEntries) {
		getService()
			.deleteSupportRegionPartnerEntries(supportRegionId, partnerEntries);
	}

	public static void deleteSupportRegionPartnerEntries(long supportRegionId,
		long[] partnerEntryIds) {
		getService()
			.deleteSupportRegionPartnerEntries(supportRegionId, partnerEntryIds);
	}

	public static void deleteSupportRegionPartnerEntry(long supportRegionId,
		com.liferay.osb.model.PartnerEntry partnerEntry) {
		getService()
			.deleteSupportRegionPartnerEntry(supportRegionId, partnerEntry);
	}

	public static void deleteSupportRegionPartnerEntry(long supportRegionId,
		long partnerEntryId) {
		getService()
			.deleteSupportRegionPartnerEntry(supportRegionId, partnerEntryId);
	}

	public static void setSupportRegionPartnerEntries(long supportRegionId,
		long[] partnerEntryIds) {
		getService()
			.setSupportRegionPartnerEntries(supportRegionId, partnerEntryIds);
	}

	public static void clearService() {
		_service = null;
	}

	public static PartnerEntryLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					PartnerEntryLocalService.class.getName());

			if (invokableLocalService instanceof PartnerEntryLocalService) {
				_service = (PartnerEntryLocalService)invokableLocalService;
			}
			else {
				_service = new PartnerEntryLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(PartnerEntryLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static PartnerEntryLocalService _service;
}