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
 * The utility for the corp project message local service. This utility wraps {@link com.liferay.osb.service.impl.CorpProjectMessageLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CorpProjectMessageLocalService
 * @see com.liferay.osb.service.base.CorpProjectMessageLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.CorpProjectMessageLocalServiceImpl
 * @generated
 */
public class CorpProjectMessageLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.CorpProjectMessageLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the corp project message to the database. Also notifies the appropriate model listeners.
	*
	* @param corpProjectMessage the corp project message
	* @return the corp project message that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage addCorpProjectMessage(
		com.liferay.osb.model.CorpProjectMessage corpProjectMessage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addCorpProjectMessage(corpProjectMessage);
	}

	/**
	* Creates a new corp project message with the primary key. Does not add the corp project message to the database.
	*
	* @param corpProjectMessageId the primary key for the new corp project message
	* @return the new corp project message
	*/
	public static com.liferay.osb.model.CorpProjectMessage createCorpProjectMessage(
		long corpProjectMessageId) {
		return getService().createCorpProjectMessage(corpProjectMessageId);
	}

	/**
	* Deletes the corp project message with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param corpProjectMessageId the primary key of the corp project message
	* @return the corp project message that was removed
	* @throws PortalException if a corp project message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage deleteCorpProjectMessage(
		long corpProjectMessageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteCorpProjectMessage(corpProjectMessageId);
	}

	/**
	* Deletes the corp project message from the database. Also notifies the appropriate model listeners.
	*
	* @param corpProjectMessage the corp project message
	* @return the corp project message that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage deleteCorpProjectMessage(
		com.liferay.osb.model.CorpProjectMessage corpProjectMessage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteCorpProjectMessage(corpProjectMessage);
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

	public static com.liferay.osb.model.CorpProjectMessage fetchCorpProjectMessage(
		long corpProjectMessageId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchCorpProjectMessage(corpProjectMessageId);
	}

	/**
	* Returns the corp project message with the primary key.
	*
	* @param corpProjectMessageId the primary key of the corp project message
	* @return the corp project message
	* @throws PortalException if a corp project message with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage getCorpProjectMessage(
		long corpProjectMessageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpProjectMessage(corpProjectMessageId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the corp project messages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of corp project messages
	* @param end the upper bound of the range of corp project messages (not inclusive)
	* @return the range of corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.CorpProjectMessage> getCorpProjectMessages(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpProjectMessages(start, end);
	}

	/**
	* Returns the number of corp project messages.
	*
	* @return the number of corp project messages
	* @throws SystemException if a system exception occurred
	*/
	public static int getCorpProjectMessagesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpProjectMessagesCount();
	}

	/**
	* Updates the corp project message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param corpProjectMessage the corp project message
	* @return the corp project message that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage updateCorpProjectMessage(
		com.liferay.osb.model.CorpProjectMessage corpProjectMessage)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCorpProjectMessage(corpProjectMessage);
	}

	/**
	* Updates the corp project message in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param corpProjectMessage the corp project message
	* @param merge whether to merge the corp project message with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the corp project message that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.CorpProjectMessage updateCorpProjectMessage(
		com.liferay.osb.model.CorpProjectMessage corpProjectMessage,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCorpProjectMessage(corpProjectMessage, merge);
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

	public static com.liferay.osb.model.CorpProjectMessage addCorpProjectMessage(
		long userId, long corpProjectId, int type, int severityLevel,
		java.lang.String title, java.lang.String content, boolean displayCP,
		boolean displayLCS, boolean displayLESA)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addCorpProjectMessage(userId, corpProjectId, type,
			severityLevel, title, content, displayCP, displayLCS, displayLESA);
	}

	public static void checkCorpProjects() throws java.lang.Exception {
		getService().checkCorpProjects();
	}

	public static void deleteFromLCS(
		com.liferay.osb.model.CorpProjectMessage corpProjectMessage)
		throws com.liferay.portal.kernel.exception.SystemException {
		getService().deleteFromLCS(corpProjectMessage);
	}

	public static java.util.List<com.liferay.osb.model.CorpProjectMessage> getCorpProjectMessages(
		long corpProjectId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getCorpProjectMessages(corpProjectId);
	}

	public static java.util.Map<java.lang.Integer, java.util.List<com.liferay.osb.model.CorpProjectMessage>> getCorpProjectMessagesMap(
		long userId, boolean displayCP, boolean displayLESA)
		throws java.lang.Exception {
		return getService()
				   .getCorpProjectMessagesMap(userId, displayCP, displayLESA);
	}

	public static void syncToLCS(long corpProjectMessageId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().syncToLCS(corpProjectMessageId);
	}

	public static com.liferay.osb.model.CorpProjectMessage updateCorpProjectMessage(
		long userId, long corpProjectMessageId, int type, int severityLevel,
		java.lang.String title, java.lang.String content, boolean displayCP,
		boolean displayLCS, boolean displayLESA)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCorpProjectMessage(userId, corpProjectMessageId,
			type, severityLevel, title, content, displayCP, displayLCS,
			displayLESA);
	}

	public static void clearService() {
		_service = null;
	}

	public static CorpProjectMessageLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					CorpProjectMessageLocalService.class.getName());

			if (invokableLocalService instanceof CorpProjectMessageLocalService) {
				_service = (CorpProjectMessageLocalService)invokableLocalService;
			}
			else {
				_service = new CorpProjectMessageLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(CorpProjectMessageLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(CorpProjectMessageLocalService service) {
	}

	private static CorpProjectMessageLocalService _service;
}