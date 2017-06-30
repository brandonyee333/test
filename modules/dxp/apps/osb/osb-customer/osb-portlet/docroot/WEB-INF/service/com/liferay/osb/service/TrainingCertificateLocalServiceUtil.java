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
 * The utility for the training certificate local service. This utility wraps {@link com.liferay.osb.service.impl.TrainingCertificateLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingCertificateLocalService
 * @see com.liferay.osb.service.base.TrainingCertificateLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TrainingCertificateLocalServiceImpl
 * @generated
 */
public class TrainingCertificateLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TrainingCertificateLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the training certificate to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificate the training certificate
	* @return the training certificate that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate addTrainingCertificate(
		com.liferay.osb.model.TrainingCertificate trainingCertificate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTrainingCertificate(trainingCertificate);
	}

	/**
	* Creates a new training certificate with the primary key. Does not add the training certificate to the database.
	*
	* @param trainingCertificateId the primary key for the new training certificate
	* @return the new training certificate
	*/
	public static com.liferay.osb.model.TrainingCertificate createTrainingCertificate(
		long trainingCertificateId) {
		return getService().createTrainingCertificate(trainingCertificateId);
	}

	/**
	* Deletes the training certificate with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificateId the primary key of the training certificate
	* @return the training certificate that was removed
	* @throws PortalException if a training certificate with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate deleteTrainingCertificate(
		long trainingCertificateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTrainingCertificate(trainingCertificateId);
	}

	/**
	* Deletes the training certificate from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificate the training certificate
	* @return the training certificate that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate deleteTrainingCertificate(
		com.liferay.osb.model.TrainingCertificate trainingCertificate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTrainingCertificate(trainingCertificate);
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

	public static com.liferay.osb.model.TrainingCertificate fetchTrainingCertificate(
		long trainingCertificateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTrainingCertificate(trainingCertificateId);
	}

	/**
	* Returns the training certificate with the primary key.
	*
	* @param trainingCertificateId the primary key of the training certificate
	* @return the training certificate
	* @throws PortalException if a training certificate with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate getTrainingCertificate(
		long trainingCertificateId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingCertificate(trainingCertificateId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the training certificates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training certificates
	* @param end the upper bound of the range of training certificates (not inclusive)
	* @return the range of training certificates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCertificate> getTrainingCertificates(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingCertificates(start, end);
	}

	/**
	* Returns the number of training certificates.
	*
	* @return the number of training certificates
	* @throws SystemException if a system exception occurred
	*/
	public static int getTrainingCertificatesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingCertificatesCount();
	}

	/**
	* Updates the training certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificate the training certificate
	* @return the training certificate that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate updateTrainingCertificate(
		com.liferay.osb.model.TrainingCertificate trainingCertificate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTrainingCertificate(trainingCertificate);
	}

	/**
	* Updates the training certificate in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificate the training certificate
	* @param merge whether to merge the training certificate with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the training certificate that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate updateTrainingCertificate(
		com.liferay.osb.model.TrainingCertificate trainingCertificate,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTrainingCertificate(trainingCertificate, merge);
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

	public static com.liferay.osb.model.TrainingCertificate addTrainingCertificate(
		long userId, long trainingCustomerId, java.lang.String key,
		int surveyStatus, long userProfileId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTrainingCertificate(userId, trainingCustomerId, key,
			surveyStatus, userProfileId);
	}

	public static void addTrainingCertificates(long userId,
		long[] trainingCustomerIds)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().addTrainingCertificates(userId, trainingCustomerIds);
	}

	public static void deleteTrainingCustomerTrainingCertificate(
		long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteTrainingCustomerTrainingCertificate(trainingCustomerId);
	}

	public static com.liferay.osb.model.TrainingCertificate fetchTrainingCertificateByTrainingCustomerId(
		long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .fetchTrainingCertificateByTrainingCustomerId(trainingCustomerId);
	}

	public static void getTrainingCertificate(
		javax.portlet.ResourceResponse resourceResponse, long trainingCustomerId)
		throws java.lang.Exception {
		getService().getTrainingCertificate(resourceResponse, trainingCustomerId);
	}

	public static com.liferay.osb.model.TrainingCertificate getTrainingCertificate(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingCertificate(key);
	}

	public static com.liferay.osb.model.TrainingCertificate getTrainingCertificate(
		java.lang.String key, java.lang.String lastName, long classNameId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingCertificate(key, lastName, classNameId);
	}

	public static com.liferay.osb.model.TrainingCertificate getTrainingCertificateByTrainingCustomerId(
		long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTrainingCertificateByTrainingCustomerId(trainingCustomerId);
	}

	public static boolean hasTrainingCertificate(long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().hasTrainingCertificate(trainingCustomerId);
	}

	public static boolean hasTrainingCertificateCertifiedDate(
		long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .hasTrainingCertificateCertifiedDate(trainingCustomerId);
	}

	public static void sendTrainingCertificate(long trainingCustomerId)
		throws java.lang.Exception {
		getService().sendTrainingCertificate(trainingCustomerId);
	}

	public static com.liferay.osb.model.TrainingCertificate updateCertifiedDate(
		long trainingCertificateId, java.util.Date date)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().updateCertifiedDate(trainingCertificateId, date);
	}

	public static com.liferay.osb.model.TrainingCertificate updateCertifiedDate(
		long trainingCertificateId, long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateCertifiedDate(trainingCertificateId,
			trainingCustomerId);
	}

	public static com.liferay.osb.model.TrainingCertificate updateTrainingCertificate(
		long trainingCertificateId, java.util.Date certifiedDate,
		int surveyStatus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTrainingCertificate(trainingCertificateId,
			certifiedDate, surveyStatus);
	}

	public static com.liferay.osb.model.TrainingCertificate updateTrainingCertificate(
		long trainingCertificateId, long userId, java.lang.String emailAddress,
		java.lang.String firstName, java.lang.String lastName,
		java.lang.String legalEntityName, int surveyStatus)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTrainingCertificate(trainingCertificateId, userId,
			emailAddress, firstName, lastName, legalEntityName, surveyStatus);
	}

	public static com.liferay.osb.model.TrainingCertificate updateUserProfileHistoryId(
		long trainingCertificateId, long userId, java.lang.String emailAddress,
		java.lang.String firstName, java.lang.String lastName,
		java.lang.String legalEntityName)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateUserProfileHistoryId(trainingCertificateId, userId,
			emailAddress, firstName, lastName, legalEntityName);
	}

	public static void clearService() {
		_service = null;
	}

	public static TrainingCertificateLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TrainingCertificateLocalService.class.getName());

			if (invokableLocalService instanceof TrainingCertificateLocalService) {
				_service = (TrainingCertificateLocalService)invokableLocalService;
			}
			else {
				_service = new TrainingCertificateLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TrainingCertificateLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TrainingCertificateLocalService service) {
	}

	private static TrainingCertificateLocalService _service;
}