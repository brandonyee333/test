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
 * The utility for the corp group local service. This utility wraps {@link com.liferay.osb.service.impl.CorpGroupLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpGroupLocalService
 * @see com.liferay.osb.service.base.CorpGroupLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.CorpGroupLocalServiceImpl
 * @generated
 */
public class CorpGroupLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.CorpGroupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the corp group to the database. Also notifies the appropriate model listeners.
	*
	* @param corpGroup the corp group
	* @return the corp group that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup addCorpGroup(
		com.liferay.osb.model.CorpGroup corpGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addCorpGroup(corpGroup);
	}

	/**
	* Creates a new corp group with the primary key. Does not add the corp group to the database.
	*
	* @param corpGroupId the primary key for the new corp group
	* @return the new corp group
	*/
	public static com.liferay.osb.model.CorpGroup createCorpGroup(
		long corpGroupId) {
		return getService().createCorpGroup(corpGroupId);
	}

	/**
	* Deletes the corp group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpGroupId the primary key of the corp group
	* @return the corp group that was removed
	* @throws PortalException if a corp group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup deleteCorpGroup(
		long corpGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteCorpGroup(corpGroupId);
	}

	/**
	* Deletes the corp group from the database. Also notifies the appropriate model listeners.
	*
	* @param corpGroup the corp group
	* @return the corp group that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup deleteCorpGroup(
		com.liferay.osb.model.CorpGroup corpGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteCorpGroup(corpGroup);
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

	public static com.liferay.osb.model.CorpGroup fetchCorpGroup(
		long corpGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchCorpGroup(corpGroupId);
	}

	/**
	* Returns the corp group with the primary key.
	*
	* @param corpGroupId the primary key of the corp group
	* @return the corp group
	* @throws PortalException if a corp group with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup getCorpGroup(long corpGroupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpGroup(corpGroupId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the corp groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp groups
	* @param end the upper bound of the range of corp groups (not inclusive)
	* @return the range of corp groups
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpGroup> getCorpGroups(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpGroups(start, end);
	}

	/**
	* Returns the number of corp groups.
	*
	* @return the number of corp groups
	* @throws SystemException if a system exception occurred
	*/
	public static int getCorpGroupsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpGroupsCount();
	}

	/**
	* Updates the corp group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param corpGroup the corp group
	* @return the corp group that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup updateCorpGroup(
		com.liferay.osb.model.CorpGroup corpGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCorpGroup(corpGroup);
	}

	/**
	* Updates the corp group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param corpGroup the corp group
	* @param merge whether to merge the corp group with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the corp group that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpGroup updateCorpGroup(
		com.liferay.osb.model.CorpGroup corpGroup, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCorpGroup(corpGroup, merge);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addCorpEntryCorpGroup(long corpEntryId, long corpGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addCorpEntryCorpGroup(corpEntryId, corpGroupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addCorpEntryCorpGroup(long corpEntryId,
		com.liferay.osb.model.CorpGroup corpGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addCorpEntryCorpGroup(corpEntryId, corpGroup);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addCorpEntryCorpGroups(long corpEntryId,
		long[] corpGroupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addCorpEntryCorpGroups(corpEntryId, corpGroupIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addCorpEntryCorpGroups(long corpEntryId,
		java.util.List<com.liferay.osb.model.CorpGroup> CorpGroups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addCorpEntryCorpGroups(corpEntryId, CorpGroups);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void clearCorpEntryCorpGroups(long corpEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearCorpEntryCorpGroups(corpEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteCorpEntryCorpGroup(long corpEntryId,
		long corpGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCorpEntryCorpGroup(corpEntryId, corpGroupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteCorpEntryCorpGroup(long corpEntryId,
		com.liferay.osb.model.CorpGroup corpGroup)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCorpEntryCorpGroup(corpEntryId, corpGroup);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteCorpEntryCorpGroups(long corpEntryId,
		long[] corpGroupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCorpEntryCorpGroups(corpEntryId, corpGroupIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteCorpEntryCorpGroups(long corpEntryId,
		java.util.List<com.liferay.osb.model.CorpGroup> CorpGroups)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteCorpEntryCorpGroups(corpEntryId, CorpGroups);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpGroup> getCorpEntryCorpGroups(
		long corpEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpEntryCorpGroups(corpEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpGroup> getCorpEntryCorpGroups(
		long corpEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpEntryCorpGroups(corpEntryId, start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpGroup> getCorpEntryCorpGroups(
		long corpEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getCorpEntryCorpGroups(corpEntryId, start, end,
			orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static int getCorpEntryCorpGroupsCount(long corpEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpEntryCorpGroupsCount(corpEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasCorpEntryCorpGroup(long corpEntryId,
		long corpGroupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasCorpEntryCorpGroup(corpEntryId, corpGroupId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasCorpEntryCorpGroups(long corpEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasCorpEntryCorpGroups(corpEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void setCorpEntryCorpGroups(long corpEntryId,
		long[] corpGroupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().setCorpEntryCorpGroups(corpEntryId, corpGroupIds);
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

	public static com.liferay.osb.model.CorpGroup addCorpGroup(long userId,
		java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.io.File logoFile, java.lang.String emailAddress,
		java.lang.String website,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addCorpGroup(userId, name, descriptionMap, logoFile,
			emailAddress, website, serviceContext);
	}

	public static com.liferay.osb.model.CorpGroup fetchOrganizationCorpGroup(
		long organizationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchOrganizationCorpGroup(organizationId);
	}

	public static com.liferay.osb.model.CorpGroup getOrganizationCorpGroup(
		long organizationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getOrganizationCorpGroup(organizationId);
	}

	public static java.util.List<com.liferay.osb.model.CorpGroup> getUserCorpGroups(
		long userId, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserCorpGroups(userId, status, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.CorpGroup> search(
		java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(name, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.CorpGroup> search(
		java.lang.String name, long[] notCorpGroupIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(name, notCorpGroupIds, start, end, obc);
	}

	public static int searchCount(java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(name);
	}

	public static int searchCount(java.lang.String name, long[] notCorpGroupIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(name, notCorpGroupIds);
	}

	public static com.liferay.osb.model.CorpGroup updateCorpGroup(
		long corpGroupId, java.lang.String name,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.io.File logoFile, java.lang.String emailAddress,
		java.lang.String website,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCorpGroup(corpGroupId, name, descriptionMap,
			logoFile, emailAddress, website, serviceContext);
	}

	public static void updateSite(long corpGroupId, boolean autoFriendlyURL)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().updateSite(corpGroupId, autoFriendlyURL);
	}

	public static void clearService() {
		_service = null;
	}

	public static CorpGroupLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					CorpGroupLocalService.class.getName());

			if (invokableLocalService instanceof CorpGroupLocalService) {
				_service = (CorpGroupLocalService)invokableLocalService;
			}
			else {
				_service = new CorpGroupLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(CorpGroupLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(CorpGroupLocalService service) {
	}

	private static CorpGroupLocalService _service;
}