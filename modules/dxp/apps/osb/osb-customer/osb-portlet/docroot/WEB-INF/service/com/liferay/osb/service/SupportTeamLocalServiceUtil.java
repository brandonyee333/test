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
 * The utility for the support team local service. This utility wraps {@link com.liferay.osb.service.impl.SupportTeamLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeamLocalService
 * @see com.liferay.osb.service.base.SupportTeamLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.SupportTeamLocalServiceImpl
 * @generated
 */
public class SupportTeamLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.SupportTeamLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the support team to the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportTeam addSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addSupportTeam(supportTeam);
	}

	/**
	* Creates a new support team with the primary key. Does not add the support team to the database.
	*
	* @param supportTeamId the primary key for the new support team
	* @return the new support team
	*/
	public static com.liferay.osb.model.SupportTeam createSupportTeam(
		long supportTeamId) {
		return getService().createSupportTeam(supportTeamId);
	}

	/**
	* Deletes the support team with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeamId the primary key of the support team
	* @return the support team that was removed
	* @throws PortalException if a support team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportTeam deleteSupportTeam(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSupportTeam(supportTeamId);
	}

	/**
	* Deletes the support team from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportTeam deleteSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteSupportTeam(supportTeam);
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

	public static com.liferay.osb.model.SupportTeam fetchSupportTeam(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchSupportTeam(supportTeamId);
	}

	/**
	* Returns the support team with the primary key.
	*
	* @param supportTeamId the primary key of the support team
	* @return the support team
	* @throws PortalException if a support team with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportTeam getSupportTeam(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportTeam(supportTeamId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the support teams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support teams
	* @param end the upper bound of the range of support teams (not inclusive)
	* @return the range of support teams
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportTeams(start, end);
	}

	/**
	* Returns the number of support teams.
	*
	* @return the number of support teams
	* @throws SystemException if a system exception occurred
	*/
	public static int getSupportTeamsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportTeamsCount();
	}

	/**
	* Updates the support team in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportTeam updateSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSupportTeam(supportTeam);
	}

	/**
	* Updates the support team in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @param merge whether to merge the support team with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the support team that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.SupportTeam updateSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateSupportTeam(supportTeam, merge);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addAccountEntrySupportTeam(accountEntryId, supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addAccountEntrySupportTeam(long accountEntryId,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addAccountEntrySupportTeam(accountEntryId, supportTeam);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addAccountEntrySupportTeams(accountEntryId, supportTeamIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addAccountEntrySupportTeams(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportTeam> SupportTeams)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addAccountEntrySupportTeams(accountEntryId, SupportTeams);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void clearAccountEntrySupportTeams(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearAccountEntrySupportTeams(accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAccountEntrySupportTeam(accountEntryId, supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteAccountEntrySupportTeam(long accountEntryId,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAccountEntrySupportTeam(accountEntryId, supportTeam);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteAccountEntrySupportTeams(accountEntryId, supportTeamIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteAccountEntrySupportTeams(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportTeam> SupportTeams)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteAccountEntrySupportTeams(accountEntryId, SupportTeams);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportTeam> getAccountEntrySupportTeams(
		long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEntrySupportTeams(accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportTeam> getAccountEntrySupportTeams(
		long accountEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAccountEntrySupportTeams(accountEntryId, start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportTeam> getAccountEntrySupportTeams(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getAccountEntrySupportTeams(accountEntryId, start, end,
			orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static int getAccountEntrySupportTeamsCount(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getAccountEntrySupportTeamsCount(accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasAccountEntrySupportTeam(accountEntryId, supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasAccountEntrySupportTeams(long accountEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasAccountEntrySupportTeams(accountEntryId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void setAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().setAccountEntrySupportTeams(accountEntryId, supportTeamIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addSupportRegionSupportTeam(supportRegionId, supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegionSupportTeam(long supportRegionId,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addSupportRegionSupportTeam(supportRegionId, supportTeam);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.addSupportRegionSupportTeams(supportRegionId, supportTeamIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void addSupportRegionSupportTeams(long supportRegionId,
		java.util.List<com.liferay.osb.model.SupportTeam> SupportTeams)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().addSupportRegionSupportTeams(supportRegionId, SupportTeams);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void clearSupportRegionSupportTeams(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().clearSupportRegionSupportTeams(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteSupportRegionSupportTeam(supportRegionId, supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportRegionSupportTeam(long supportRegionId,
		com.liferay.osb.model.SupportTeam supportTeam)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteSupportRegionSupportTeam(supportRegionId, supportTeam);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteSupportRegionSupportTeams(supportRegionId, supportTeamIds);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteSupportRegionSupportTeams(long supportRegionId,
		java.util.List<com.liferay.osb.model.SupportTeam> SupportTeams)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteSupportRegionSupportTeams(supportRegionId, SupportTeams);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportRegionSupportTeams(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSupportRegionSupportTeams(supportRegionId, start, end);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getSupportRegionSupportTeams(supportRegionId, start, end,
			orderByComparator);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static int getSupportRegionSupportTeamsCount(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportRegionSupportTeamsCount(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasSupportRegionSupportTeam(supportRegionId, supportTeamId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static boolean hasSupportRegionSupportTeams(long supportRegionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasSupportRegionSupportTeams(supportRegionId);
	}

	/**
	* @throws SystemException if a system exception occurred
	*/
	public static void setSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setSupportRegionSupportTeams(supportRegionId, supportTeamIds);
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

	public static com.liferay.osb.model.SupportTeam addSupportTeam(
		long userId, long parentSupportTeamId, long supportLaborId,
		long locationSupportRegionId, java.lang.String name,
		java.lang.String description, int type)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addSupportTeam(userId, parentSupportTeamId, supportLaborId,
			locationSupportRegionId, name, description, type);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> getChildSupportTeams(
		long supportTeamId, boolean recursive)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getChildSupportTeams(supportTeamId, recursive);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportLaborSupportTeams(
		long supportLaborId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportLaborSupportTeams(supportLaborId);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getSupportTeams(start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> getUserRoleSupportTeams(
		long userId, int role)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getUserRoleSupportTeams(userId, role);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> search(
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(keywords, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> search(
		java.lang.String name, java.lang.Integer type, boolean andSearch,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().search(name, type, andSearch, start, end, obc);
	}

	public static int searchCount(java.lang.String keywords)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(keywords);
	}

	public static int searchCount(java.lang.String name,
		java.lang.Integer type, boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().searchCount(name, type, andOperator);
	}

	public static void setChildSupportTeams(long parentSupportTeamId,
		long[] childSupportTeamIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.setChildSupportTeams(parentSupportTeamId, childSupportTeamIds);
	}

	public static void setSupportLaborId(long supportLaborId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().setSupportLaborId(supportLaborId, supportTeamIds);
	}

	public static com.liferay.osb.model.SupportTeam updateSupportTeam(
		long supportTeamId, long parentSupportTeamId, long supportLaborId,
		long locationSupportRegionId, java.lang.String name,
		java.lang.String description, int type, long[] accountEntryIds,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateSupportTeam(supportTeamId, parentSupportTeamId,
			supportLaborId, locationSupportRegionId, name, description, type,
			accountEntryIds, supportRegionIds);
	}

	public static void clearService() {
		_service = null;
	}

	public static SupportTeamLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					SupportTeamLocalService.class.getName());

			if (invokableLocalService instanceof SupportTeamLocalService) {
				_service = (SupportTeamLocalService)invokableLocalService;
			}
			else {
				_service = new SupportTeamLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(SupportTeamLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(SupportTeamLocalService service) {
	}

	private static SupportTeamLocalService _service;
}