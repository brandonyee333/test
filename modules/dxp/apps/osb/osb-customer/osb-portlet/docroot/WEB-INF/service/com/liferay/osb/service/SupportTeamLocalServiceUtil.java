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
 * Provides the local service utility for SupportTeam. This utility wraps
 * {@link com.liferay.osb.service.impl.SupportTeamLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeamLocalService
 * @see com.liferay.osb.service.base.SupportTeamLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.SupportTeamLocalServiceImpl
 * @generated
 */
@ProviderType
public class SupportTeamLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.SupportTeamLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static boolean hasAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId) {
		return getService()
				   .hasAccountEntrySupportTeam(accountEntryId, supportTeamId);
	}

	public static boolean hasAccountEntrySupportTeams(long accountEntryId) {
		return getService().hasAccountEntrySupportTeams(accountEntryId);
	}

	public static boolean hasSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId) {
		return getService()
				   .hasSupportRegionSupportTeam(supportRegionId, supportTeamId);
	}

	public static boolean hasSupportRegionSupportTeams(long supportRegionId) {
		return getService().hasSupportRegionSupportTeams(supportRegionId);
	}

	/**
	* Adds the support team to the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was added
	*/
	public static com.liferay.osb.model.SupportTeam addSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam) {
		return getService().addSupportTeam(supportTeam);
	}

	public static com.liferay.osb.model.SupportTeam addSupportTeam(
		long userId, long parentSupportTeamId, long supportLaborId,
		long locationSupportRegionId, java.lang.String name,
		java.lang.String description, int type)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addSupportTeam(userId, parentSupportTeamId, supportLaborId,
			locationSupportRegionId, name, description, type);
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
	* Deletes the support team from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was removed
	*/
	public static com.liferay.osb.model.SupportTeam deleteSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam) {
		return getService().deleteSupportTeam(supportTeam);
	}

	/**
	* Deletes the support team with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeamId the primary key of the support team
	* @return the support team that was removed
	* @throws PortalException if a support team with the primary key could not be found
	*/
	public static com.liferay.osb.model.SupportTeam deleteSupportTeam(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteSupportTeam(supportTeamId);
	}

	public static com.liferay.osb.model.SupportTeam fetchSupportTeam(
		long supportTeamId) {
		return getService().fetchSupportTeam(supportTeamId);
	}

	/**
	* Returns the support team with the primary key.
	*
	* @param supportTeamId the primary key of the support team
	* @return the support team
	* @throws PortalException if a support team with the primary key could not be found
	*/
	public static com.liferay.osb.model.SupportTeam getSupportTeam(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getSupportTeam(supportTeamId);
	}

	/**
	* Updates the support team in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param supportTeam the support team
	* @return the support team that was updated
	*/
	public static com.liferay.osb.model.SupportTeam updateSupportTeam(
		com.liferay.osb.model.SupportTeam supportTeam) {
		return getService().updateSupportTeam(supportTeam);
	}

	public static com.liferay.osb.model.SupportTeam updateSupportTeam(
		long supportTeamId, long parentSupportTeamId, long supportLaborId,
		long locationSupportRegionId, java.lang.String name,
		java.lang.String description, int type, long[] accountEntryIds,
		long[] supportRegionIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateSupportTeam(supportTeamId, parentSupportTeamId,
			supportLaborId, locationSupportRegionId, name, description, type,
			accountEntryIds, supportRegionIds);
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

	public static int getAccountEntrySupportTeamsCount(long accountEntryId) {
		return getService().getAccountEntrySupportTeamsCount(accountEntryId);
	}

	public static int getSupportRegionSupportTeamsCount(long supportRegionId) {
		return getService().getSupportRegionSupportTeamsCount(supportRegionId);
	}

	/**
	* Returns the number of support teams.
	*
	* @return the number of support teams
	*/
	public static int getSupportTeamsCount() {
		return getService().getSupportTeamsCount();
	}

	public static int searchCount(java.lang.String keywords) {
		return getService().searchCount(keywords);
	}

	public static int searchCount(java.lang.String name,
		java.lang.Integer type, boolean andOperator) {
		return getService().searchCount(name, type, andOperator);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static java.util.List<com.liferay.osb.model.SupportTeam> getAccountEntrySupportTeams(
		long accountEntryId) {
		return getService().getAccountEntrySupportTeams(accountEntryId);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> getAccountEntrySupportTeams(
		long accountEntryId, int start, int end) {
		return getService()
				   .getAccountEntrySupportTeams(accountEntryId, start, end);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> getAccountEntrySupportTeams(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.SupportTeam> orderByComparator) {
		return getService()
				   .getAccountEntrySupportTeams(accountEntryId, start, end,
			orderByComparator);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> getChildSupportTeams(
		long supportTeamId, boolean recursive) {
		return getService().getChildSupportTeams(supportTeamId, recursive);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportLaborSupportTeams(
		long supportLaborId) {
		return getService().getSupportLaborSupportTeams(supportLaborId);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId) {
		return getService().getSupportRegionSupportTeams(supportRegionId);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId, int start, int end) {
		return getService()
				   .getSupportRegionSupportTeams(supportRegionId, start, end);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportRegionSupportTeams(
		long supportRegionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.SupportTeam> orderByComparator) {
		return getService()
				   .getSupportRegionSupportTeams(supportRegionId, start, end,
			orderByComparator);
	}

	/**
	* Returns a range of all the support teams.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.liferay.osb.model.impl.SupportTeamModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support teams
	* @param end the upper bound of the range of support teams (not inclusive)
	* @return the range of support teams
	*/
	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		int start, int end) {
		return getService().getSupportTeams(start, end);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> getSupportTeams(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService().getSupportTeams(start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> getUserRoleSupportTeams(
		long userId, int role) {
		return getService().getUserRoleSupportTeams(userId, role);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> search(
		java.lang.String keywords, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService().search(keywords, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.SupportTeam> search(
		java.lang.String name, java.lang.Integer type, boolean andSearch,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc) {
		return getService().search(name, type, andSearch, start, end, obc);
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
	* Returns the accountEntryIds of the account entries associated with the support team.
	*
	* @param supportTeamId the supportTeamId of the support team
	* @return long[] the accountEntryIds of account entries associated with the support team
	*/
	public static long[] getAccountEntryPrimaryKeys(long supportTeamId) {
		return getService().getAccountEntryPrimaryKeys(supportTeamId);
	}

	/**
	* Returns the supportRegionIds of the support regions associated with the support team.
	*
	* @param supportTeamId the supportTeamId of the support team
	* @return long[] the supportRegionIds of support regions associated with the support team
	*/
	public static long[] getSupportRegionPrimaryKeys(long supportTeamId) {
		return getService().getSupportRegionPrimaryKeys(supportTeamId);
	}

	public static void addAccountEntrySupportTeam(long accountEntryId,
		com.liferay.osb.model.SupportTeam supportTeam) {
		getService().addAccountEntrySupportTeam(accountEntryId, supportTeam);
	}

	public static void addAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId) {
		getService().addAccountEntrySupportTeam(accountEntryId, supportTeamId);
	}

	public static void addAccountEntrySupportTeams(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportTeam> supportTeams) {
		getService().addAccountEntrySupportTeams(accountEntryId, supportTeams);
	}

	public static void addAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds) {
		getService().addAccountEntrySupportTeams(accountEntryId, supportTeamIds);
	}

	public static void addSupportRegionSupportTeam(long supportRegionId,
		com.liferay.osb.model.SupportTeam supportTeam) {
		getService().addSupportRegionSupportTeam(supportRegionId, supportTeam);
	}

	public static void addSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId) {
		getService().addSupportRegionSupportTeam(supportRegionId, supportTeamId);
	}

	public static void addSupportRegionSupportTeams(long supportRegionId,
		java.util.List<com.liferay.osb.model.SupportTeam> supportTeams) {
		getService().addSupportRegionSupportTeams(supportRegionId, supportTeams);
	}

	public static void addSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds) {
		getService()
			.addSupportRegionSupportTeams(supportRegionId, supportTeamIds);
	}

	public static void clearAccountEntrySupportTeams(long accountEntryId) {
		getService().clearAccountEntrySupportTeams(accountEntryId);
	}

	public static void clearSupportRegionSupportTeams(long supportRegionId) {
		getService().clearSupportRegionSupportTeams(supportRegionId);
	}

	public static void deleteAccountEntrySupportTeam(long accountEntryId,
		com.liferay.osb.model.SupportTeam supportTeam) {
		getService().deleteAccountEntrySupportTeam(accountEntryId, supportTeam);
	}

	public static void deleteAccountEntrySupportTeam(long accountEntryId,
		long supportTeamId) {
		getService().deleteAccountEntrySupportTeam(accountEntryId, supportTeamId);
	}

	public static void deleteAccountEntrySupportTeams(long accountEntryId,
		java.util.List<com.liferay.osb.model.SupportTeam> supportTeams) {
		getService().deleteAccountEntrySupportTeams(accountEntryId, supportTeams);
	}

	public static void deleteAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds) {
		getService()
			.deleteAccountEntrySupportTeams(accountEntryId, supportTeamIds);
	}

	public static void deleteSupportRegionSupportTeam(long supportRegionId,
		com.liferay.osb.model.SupportTeam supportTeam) {
		getService().deleteSupportRegionSupportTeam(supportRegionId, supportTeam);
	}

	public static void deleteSupportRegionSupportTeam(long supportRegionId,
		long supportTeamId) {
		getService()
			.deleteSupportRegionSupportTeam(supportRegionId, supportTeamId);
	}

	public static void deleteSupportRegionSupportTeams(long supportRegionId,
		java.util.List<com.liferay.osb.model.SupportTeam> supportTeams) {
		getService()
			.deleteSupportRegionSupportTeams(supportRegionId, supportTeams);
	}

	public static void deleteSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds) {
		getService()
			.deleteSupportRegionSupportTeams(supportRegionId, supportTeamIds);
	}

	public static void setAccountEntrySupportTeams(long accountEntryId,
		long[] supportTeamIds) {
		getService().setAccountEntrySupportTeams(accountEntryId, supportTeamIds);
	}

	public static void setChildSupportTeams(long parentSupportTeamId,
		long[] childSupportTeamIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService()
			.setChildSupportTeams(parentSupportTeamId, childSupportTeamIds);
	}

	public static void setSupportLaborId(long supportLaborId,
		long[] supportTeamIds)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().setSupportLaborId(supportLaborId, supportTeamIds);
	}

	public static void setSupportRegionSupportTeams(long supportRegionId,
		long[] supportTeamIds) {
		getService()
			.setSupportRegionSupportTeams(supportRegionId, supportTeamIds);
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

	private static SupportTeamLocalService _service;
}