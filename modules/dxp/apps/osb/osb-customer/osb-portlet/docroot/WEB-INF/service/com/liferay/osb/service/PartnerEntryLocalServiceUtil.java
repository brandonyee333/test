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
 * The utility for the partner entry local service. This utility wraps {@link com.liferay.osb.service.impl.PartnerEntryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PartnerEntryLocalService
 * @see com.liferay.osb.service.base.PartnerEntryLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.PartnerEntryLocalServiceImpl
 * @generated
 */
public class PartnerEntryLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.PartnerEntryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the partner entry to the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @return the partner entry that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry addPartnerEntry(
		com.liferay.osb.model.PartnerEntry partnerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addPartnerEntry(partnerEntry);
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
	* Deletes the partner entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry that was removed
	* @throws PortalException if a partner entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry deletePartnerEntry(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deletePartnerEntry(partnerEntryId);
	}

	/**
	* Deletes the partner entry from the database. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @return the partner entry that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry deletePartnerEntry(
		com.liferay.osb.model.PartnerEntry partnerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deletePartnerEntry(partnerEntry);
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

	public static com.liferay.osb.model.PartnerEntry fetchPartnerEntry(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchPartnerEntry(partnerEntryId);
	}

	/**
	* Returns the partner entry with the primary key.
	*
	* @param partnerEntryId the primary key of the partner entry
	* @return the partner entry
	* @throws PortalException if a partner entry with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry getPartnerEntry(
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPartnerEntry(partnerEntryId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the partner entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of partner entries
	* @param end the upper bound of the range of partner entries (not inclusive)
	* @return the range of partner entries
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PartnerEntry> getPartnerEntries(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPartnerEntries(start, end);
	}

	/**
	* Returns the number of partner entries.
	*
	* @return the number of partner entries
	* @throws SystemException if a system exception occurred
	*/
	public static int getPartnerEntriesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getPartnerEntriesCount();
	}

	/**
	* Updates the partner entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @return the partner entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry updatePartnerEntry(
		com.liferay.osb.model.PartnerEntry partnerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updatePartnerEntry(partnerEntry);
	}

	/**
	* Updates the partner entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param partnerEntry the partner entry
	* @param merge whether to merge the partner entry with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the partner entry that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.PartnerEntry updatePartnerEntry(
		com.liferay.osb.model.PartnerEntry partnerEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updatePartnerEntry(partnerEntry, merge);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegionPartnerEntry(long supportRegionId,
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addSupportRegionPartnerEntry(supportRegionId, partnerEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegionPartnerEntry(long supportRegionId,
		com.liferay.osb.model.PartnerEntry partnerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addSupportRegionPartnerEntry(supportRegionId, partnerEntry);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegionPartnerEntries(long supportRegionId,
		long[] partnerEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addSupportRegionPartnerEntries(supportRegionId, partnerEntryIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegionPartnerEntries(long supportRegionId,
		java.util.List<com.liferay.osb.model.PartnerEntry> PartnerEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addSupportRegionPartnerEntries(supportRegionId, PartnerEntries);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void clearSupportRegionPartnerEntries(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearSupportRegionPartnerEntries(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportRegionPartnerEntry(long supportRegionId,
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteSupportRegionPartnerEntry(supportRegionId, partnerEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportRegionPartnerEntry(long supportRegionId,
		com.liferay.osb.model.PartnerEntry partnerEntry)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteSupportRegionPartnerEntry(supportRegionId, partnerEntry);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportRegionPartnerEntries(long supportRegionId,
		long[] partnerEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteSupportRegionPartnerEntries(supportRegionId, partnerEntryIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportRegionPartnerEntries(long supportRegionId,
		java.util.List<com.liferay.osb.model.PartnerEntry> PartnerEntries)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteSupportRegionPartnerEntries(supportRegionId, PartnerEntries);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PartnerEntry> getSupportRegionPartnerEntries(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportRegionPartnerEntries(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PartnerEntry> getSupportRegionPartnerEntries(
		long supportRegionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSupportRegionPartnerEntries(supportRegionId, start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.PartnerEntry> getSupportRegionPartnerEntries(
		long supportRegionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSupportRegionPartnerEntries(supportRegionId, start, end,
			orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static int getSupportRegionPartnerEntriesCount(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportRegionPartnerEntriesCount(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasSupportRegionPartnerEntry(long supportRegionId,
		long partnerEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasSupportRegionPartnerEntry(supportRegionId, partnerEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasSupportRegionPartnerEntries(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasSupportRegionPartnerEntries(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void setSupportRegionPartnerEntries(long supportRegionId,
		long[] partnerEntryIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setSupportRegionPartnerEntries(supportRegionId, partnerEntryIds);
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

	public static com.liferay.osb.model.PartnerEntry addPartnerEntry(
		long userId, long parentPartnerEntryId,
		java.lang.String dossieraAccountKey, java.lang.String code,
		java.lang.String notes, long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addPartnerEntry(userId, parentPartnerEntryId,
			dossieraAccountKey, code, notes, supportRegionIds);
	}

	public static com.liferay.osb.model.PartnerEntry fetchPartnerEntry(
		java.lang.String dossieraAccountKey)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchPartnerEntry(dossieraAccountKey);
	}

	public static java.util.List<com.liferay.osb.model.PartnerEntry> getChildPartnerEntries(
		long partnerEntryId, boolean recursive)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildPartnerEntries(partnerEntryId, recursive);
	}

	public static com.liferay.osb.model.PartnerEntry getPartnerEntryByCode(
		java.lang.String code)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPartnerEntryByCode(code);
	}

	public static java.util.List<com.liferay.osb.model.PartnerEntry> getUserPartnerEntries(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserPartnerEntries(userId, start, end);
	}

	public static java.util.List<com.liferay.osb.model.PartnerEntry> search(
		java.lang.String code, int[] statuses,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(code, statuses, params, andOperator, start, end);
	}

	public static java.util.List<com.liferay.osb.model.PartnerEntry> search(
		java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(keywords, params, start, end);
	}

	public static int searchCount(java.lang.String code, int[] statuses,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(code, statuses, params, andOperator);
	}

	public static int searchCount(java.lang.String keywords,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(keywords, params);
	}

	public static com.liferay.osb.model.PartnerEntry updatePartnerEntry(
		long userId, long partnerEntryId, java.lang.String dossieraAccountKey,
		java.lang.String code, java.lang.String notes, int status,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updatePartnerEntry(userId, partnerEntryId,
			dossieraAccountKey, code, notes, status, supportRegionIds);
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

	/**
	 * @deprecated
	 */
	public void setService(PartnerEntryLocalService service) {
	}

	private static PartnerEntryLocalService _service;
}