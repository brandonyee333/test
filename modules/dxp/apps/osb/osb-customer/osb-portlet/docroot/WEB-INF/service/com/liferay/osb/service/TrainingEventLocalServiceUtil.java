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
 * The utility for the training event local service. This utility wraps {@link com.liferay.osb.service.impl.TrainingEventLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingEventLocalService
 * @see com.liferay.osb.service.base.TrainingEventLocalServiceBaseImpl
 * @see com.liferay.osb.service.impl.TrainingEventLocalServiceImpl
 * @generated
 */
public class TrainingEventLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.osb.service.impl.TrainingEventLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the training event to the database. Also notifies the appropriate model listeners.
	*
	* @param trainingEvent the training event
	* @return the training event that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent addTrainingEvent(
		com.liferay.osb.model.TrainingEvent trainingEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addTrainingEvent(trainingEvent);
	}

	/**
	* Creates a new training event with the primary key. Does not add the training event to the database.
	*
	* @param trainingEventId the primary key for the new training event
	* @return the new training event
	*/
	public static com.liferay.osb.model.TrainingEvent createTrainingEvent(
		long trainingEventId) {
		return getService().createTrainingEvent(trainingEventId);
	}

	/**
	* Deletes the training event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingEventId the primary key of the training event
	* @return the training event that was removed
	* @throws PortalException if a training event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent deleteTrainingEvent(
		long trainingEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTrainingEvent(trainingEventId);
	}

	/**
	* Deletes the training event from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingEvent the training event
	* @return the training event that was removed
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent deleteTrainingEvent(
		com.liferay.osb.model.TrainingEvent trainingEvent)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().deleteTrainingEvent(trainingEvent);
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

	public static com.liferay.osb.model.TrainingEvent fetchTrainingEvent(
		long trainingEventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTrainingEvent(trainingEventId);
	}

	/**
	* Returns the training event with the primary key.
	*
	* @param trainingEventId the primary key of the training event
	* @return the training event
	* @throws PortalException if a training event with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent getTrainingEvent(
		long trainingEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingEvent(trainingEventId);
	}

	public static com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the training events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training events
	* @param end the upper bound of the range of training events (not inclusive)
	* @return the range of training events
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingEvent> getTrainingEvents(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingEvents(start, end);
	}

	/**
	* Returns the number of training events.
	*
	* @return the number of training events
	* @throws SystemException if a system exception occurred
	*/
	public static int getTrainingEventsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingEventsCount();
	}

	/**
	* Updates the training event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingEvent the training event
	* @return the training event that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent updateTrainingEvent(
		com.liferay.osb.model.TrainingEvent trainingEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTrainingEvent(trainingEvent);
	}

	/**
	* Updates the training event in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param trainingEvent the training event
	* @param merge whether to merge the training event with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the training event that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingEvent updateTrainingEvent(
		com.liferay.osb.model.TrainingEvent trainingEvent, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateTrainingEvent(trainingEvent, merge);
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

	public static com.liferay.osb.model.TrainingEvent addTrainingEvent(
		long userId, long ddmStructureId, long partnerEntryId,
		long trainingCertificateTemplateId, long trainingCourseId,
		long trainingLocationId, java.lang.String name,
		java.lang.String emailAddress, int portalMinorVersion, int type,
		java.lang.String languageId, boolean localizedSlides,
		java.lang.String timeZoneId, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, java.lang.String addressStreet1,
		java.lang.String addressStreet2, java.lang.String addressStreet3,
		java.lang.String addressCity, java.lang.String addressZip,
		long addressRegionId, long addressCountryId, int maxCustomers,
		java.lang.String enrollmentURL,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addTrainingEvent(userId, ddmStructureId, partnerEntryId,
			trainingCertificateTemplateId, trainingCourseId,
			trainingLocationId, name, emailAddress, portalMinorVersion, type,
			languageId, localizedSlides, timeZoneId, startDateMonth,
			startDateDay, startDateYear, startDateHour, startDateMinute,
			endDateMonth, endDateDay, endDateYear, endDateHour, endDateMinute,
			addressStreet1, addressStreet2, addressStreet3, addressCity,
			addressZip, addressRegionId, addressCountryId, maxCustomers,
			enrollmentURL, serviceContext);
	}

	public static void checkTrainingEventSurveys(int weeksPast)
		throws java.lang.Exception {
		getService().checkTrainingEventSurveys(weeksPast);
	}

	public static com.liferay.osb.model.TrainingEvent fetchTrainingEventByDDLRecordSetId(
		long ddlRecordSetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().fetchTrainingEventByDDLRecordSetId(ddlRecordSetId);
	}

	public static com.liferay.osb.model.TrainingEvent getTrainingEventByDDLRecordSetId(
		long ddlRecordSetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingEventByDDLRecordSetId(ddlRecordSetId);
	}

	public static java.util.List<com.liferay.osb.model.TrainingEvent> getTrainingEvents(
		int type, java.util.Date startDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingEvents(type, startDate, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingEvent> getTrainingEvents(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingEvents(params, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingEvent> getTrainingEvents(
		long trainingCourseId, int type, java.util.Date startDate, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTrainingEvents(trainingCourseId, type, startDate, start,
			end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingEvent> getTrainingEvents(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingEvents(userId, start, end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingEvent> getTrainingEvents(
		long[] trainingCourseIds, java.util.Date endDateGT, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .getTrainingEvents(trainingCourseIds, endDateGT, start, end,
			obc);
	}

	public static int getTrainingEventsCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingEventsCount(userId);
	}

	public static int getTrainingEventsCount(long[] trainingCourseIds,
		java.util.Date endDateGT)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getTrainingEventsCount(trainingCourseIds, endDateGT);
	}

	public static java.util.List<com.liferay.osb.model.TrainingEvent> search(
		java.lang.Integer type, java.lang.String name, java.lang.String course,
		java.lang.Integer portalMinorVersion, java.lang.String city,
		java.lang.Long regionId, java.lang.Long countryId,
		java.lang.String language, java.lang.String partner,
		int startDateGTDay, int startDateGTMonth, int startDateGTYear,
		int startDateLTDay, int startDateLTMonth, int startDateLTYear,
		java.lang.String trainerFirstName, java.lang.String trainerLastName,
		java.lang.String trainerEmailAddress,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(type, name, course, portalMinorVersion, city,
			regionId, countryId, language, partner, startDateGTDay,
			startDateGTMonth, startDateGTYear, startDateLTDay,
			startDateLTMonth, startDateLTYear, trainerFirstName,
			trainerLastName, trainerEmailAddress, params, andOperator, start,
			end, obc);
	}

	public static java.util.List<com.liferay.osb.model.TrainingEvent> search(
		java.lang.String keywords, java.lang.Long regionId,
		java.lang.Long countryId, int startDateGTDay, int startDateGTMonth,
		int startDateGTYear, int startDateLTDay, int startDateLTMonth,
		int startDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .search(keywords, regionId, countryId, startDateGTDay,
			startDateGTMonth, startDateGTYear, startDateLTDay,
			startDateLTMonth, startDateLTYear, params, start, end, obc);
	}

	public static int searchCount(java.lang.Integer type,
		java.lang.String name, java.lang.String course,
		java.lang.Integer portalMinorVersion, java.lang.String city,
		java.lang.Long regionId, java.lang.Long countryId,
		java.lang.String language, java.lang.String partner,
		int startDateGTDay, int startDateGTMonth, int startDateGTYear,
		int startDateLTDay, int startDateLTMonth, int startDateLTYear,
		java.lang.String trainerFirstName, java.lang.String trainerLastName,
		java.lang.String trainerEmailAddress,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(type, name, course, portalMinorVersion, city,
			regionId, countryId, language, partner, startDateGTDay,
			startDateGTMonth, startDateGTYear, startDateLTDay,
			startDateLTMonth, startDateLTYear, trainerFirstName,
			trainerLastName, trainerEmailAddress, params, andOperator);
	}

	public static int searchCount(java.lang.String keywords,
		java.lang.Long regionId, java.lang.Long countryId, int startDateGTDay,
		int startDateGTMonth, int startDateGTYear, int startDateLTDay,
		int startDateLTMonth, int startDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .searchCount(keywords, regionId, countryId, startDateGTDay,
			startDateGTMonth, startDateGTYear, startDateLTDay,
			startDateLTMonth, startDateLTYear, params);
	}

	public static void sendTrainingSurvey(long trainingCustomerId,
		java.lang.String trainingSurveyURL) throws java.lang.Exception {
		getService().sendTrainingSurvey(trainingCustomerId, trainingSurveyURL);
	}

	public static com.liferay.osb.model.TrainingEvent updateTrainingEvent(
		long trainingEventId, long ddmStructureId, long ddlRecordSetId,
		long partnerEntryId, long trainingCertificateTemplateId,
		long trainingCourseId, long trainingLocationId, java.lang.String name,
		java.lang.String emailAddress, int portalMinorVersion, int type,
		java.lang.String languageId, boolean localizedSlides,
		java.lang.String timeZoneId, int startDateMonth, int startDateDay,
		int startDateYear, int startDateHour, int startDateMinute,
		int endDateMonth, int endDateDay, int endDateYear, int endDateHour,
		int endDateMinute, java.lang.String addressStreet1,
		java.lang.String addressStreet2, java.lang.String addressStreet3,
		java.lang.String addressCity, java.lang.String addressZip,
		long addressRegionId, long addressCountryId, int maxCustomers,
		java.lang.String enrollmentURL,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateTrainingEvent(trainingEventId, ddmStructureId,
			ddlRecordSetId, partnerEntryId, trainingCertificateTemplateId,
			trainingCourseId, trainingLocationId, name, emailAddress,
			portalMinorVersion, type, languageId, localizedSlides, timeZoneId,
			startDateMonth, startDateDay, startDateYear, startDateHour,
			startDateMinute, endDateMonth, endDateDay, endDateYear,
			endDateHour, endDateMinute, addressStreet1, addressStreet2,
			addressStreet3, addressCity, addressZip, addressRegionId,
			addressCountryId, maxCustomers, enrollmentURL, serviceContext);
	}

	public static void clearService() {
		_service = null;
	}

	public static TrainingEventLocalService getService() {
		if (_service == null) {
			InvokableLocalService invokableLocalService = (InvokableLocalService)PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
					TrainingEventLocalService.class.getName());

			if (invokableLocalService instanceof TrainingEventLocalService) {
				_service = (TrainingEventLocalService)invokableLocalService;
			}
			else {
				_service = new TrainingEventLocalServiceClp(invokableLocalService);
			}

			ReferenceRegistry.registerReference(TrainingEventLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	/**
	 * @deprecated
	 */
	public void setService(TrainingEventLocalService service) {
	}

	private static TrainingEventLocalService _service;
}