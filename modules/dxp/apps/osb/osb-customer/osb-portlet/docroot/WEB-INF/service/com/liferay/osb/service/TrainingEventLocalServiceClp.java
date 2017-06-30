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

import com.liferay.portal.service.InvokableLocalService;

/**
 * @author Brian Wing Shun Chan
 */
public class TrainingEventLocalServiceClp implements TrainingEventLocalService {
	public TrainingEventLocalServiceClp(
		InvokableLocalService invokableLocalService) {
		_invokableLocalService = invokableLocalService;

		_methodName0 = "addTrainingEvent";

		_methodParameterTypes0 = new String[] {
				"com.liferay.osb.model.TrainingEvent"
			};

		_methodName1 = "createTrainingEvent";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteTrainingEvent";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteTrainingEvent";

		_methodParameterTypes3 = new String[] {
				"com.liferay.osb.model.TrainingEvent"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "fetchTrainingEvent";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getTrainingEvent";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getTrainingEvents";

		_methodParameterTypes12 = new String[] { "int", "int" };

		_methodName13 = "getTrainingEventsCount";

		_methodParameterTypes13 = new String[] {  };

		_methodName14 = "updateTrainingEvent";

		_methodParameterTypes14 = new String[] {
				"com.liferay.osb.model.TrainingEvent"
			};

		_methodName15 = "updateTrainingEvent";

		_methodParameterTypes15 = new String[] {
				"com.liferay.osb.model.TrainingEvent", "boolean"
			};

		_methodName16 = "getBeanIdentifier";

		_methodParameterTypes16 = new String[] {  };

		_methodName17 = "setBeanIdentifier";

		_methodParameterTypes17 = new String[] { "java.lang.String" };

		_methodName19 = "addTrainingEvent";

		_methodParameterTypes19 = new String[] {
				"long", "long", "long", "long", "long", "long",
				"java.lang.String", "java.lang.String", "int", "int",
				"java.lang.String", "boolean", "java.lang.String", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "long", "long", "int",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName20 = "checkTrainingEventSurveys";

		_methodParameterTypes20 = new String[] { "int" };

		_methodName21 = "fetchTrainingEventByDDLRecordSetId";

		_methodParameterTypes21 = new String[] { "long" };

		_methodName22 = "getTrainingEventByDDLRecordSetId";

		_methodParameterTypes22 = new String[] { "long" };

		_methodName23 = "getTrainingEvents";

		_methodParameterTypes23 = new String[] {
				"int", "java.util.Date", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName24 = "getTrainingEvents";

		_methodParameterTypes24 = new String[] {
				"java.util.LinkedHashMap", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName25 = "getTrainingEvents";

		_methodParameterTypes25 = new String[] {
				"long", "int", "java.util.Date", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName26 = "getTrainingEvents";

		_methodParameterTypes26 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName27 = "getTrainingEvents";

		_methodParameterTypes27 = new String[] {
				"long[][]", "java.util.Date", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName28 = "getTrainingEventsCount";

		_methodParameterTypes28 = new String[] { "long" };

		_methodName29 = "getTrainingEventsCount";

		_methodParameterTypes29 = new String[] { "long[][]", "java.util.Date" };

		_methodName30 = "search";

		_methodParameterTypes30 = new String[] {
				"java.lang.Integer", "java.lang.String", "java.lang.String",
				"java.lang.Integer", "java.lang.String", "java.lang.Long",
				"java.lang.Long", "java.lang.String", "java.lang.String", "int",
				"int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String",
				"java.util.LinkedHashMap", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName31 = "search";

		_methodParameterTypes31 = new String[] {
				"java.lang.String", "java.lang.Long", "java.lang.Long", "int",
				"int", "int", "int", "int", "int", "java.util.LinkedHashMap",
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName32 = "searchCount";

		_methodParameterTypes32 = new String[] {
				"java.lang.Integer", "java.lang.String", "java.lang.String",
				"java.lang.Integer", "java.lang.String", "java.lang.Long",
				"java.lang.Long", "java.lang.String", "java.lang.String", "int",
				"int", "int", "int", "int", "int", "java.lang.String",
				"java.lang.String", "java.lang.String",
				"java.util.LinkedHashMap", "boolean"
			};

		_methodName33 = "searchCount";

		_methodParameterTypes33 = new String[] {
				"java.lang.String", "java.lang.Long", "java.lang.Long", "int",
				"int", "int", "int", "int", "int", "java.util.LinkedHashMap"
			};

		_methodName34 = "sendTrainingSurvey";

		_methodParameterTypes34 = new String[] { "long", "java.lang.String" };

		_methodName35 = "updateTrainingEvent";

		_methodParameterTypes35 = new String[] {
				"long", "long", "long", "long", "long", "long", "long",
				"java.lang.String", "java.lang.String", "int", "int",
				"java.lang.String", "boolean", "java.lang.String", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "int",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.lang.String", "long", "long", "int",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};
	}

	public com.liferay.osb.model.TrainingEvent addTrainingEvent(
		com.liferay.osb.model.TrainingEvent trainingEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName0,
					_methodParameterTypes0,
					new Object[] { ClpSerializer.translateInput(trainingEvent) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.TrainingEvent)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.osb.model.TrainingEvent createTrainingEvent(
		long trainingEventId) {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName1,
					_methodParameterTypes1, new Object[] { trainingEventId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.TrainingEvent)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.osb.model.TrainingEvent deleteTrainingEvent(
		long trainingEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName2,
					_methodParameterTypes2, new Object[] { trainingEventId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.TrainingEvent)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.osb.model.TrainingEvent deleteTrainingEvent(
		com.liferay.osb.model.TrainingEvent trainingEvent)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName3,
					_methodParameterTypes3,
					new Object[] { ClpSerializer.translateInput(trainingEvent) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.TrainingEvent)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName4,
					_methodParameterTypes4, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.kernel.dao.orm.DynamicQuery)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName5,
					_methodParameterTypes5,
					new Object[] { ClpSerializer.translateInput(dynamicQuery) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName6,
					_methodParameterTypes6,
					new Object[] {
						ClpSerializer.translateInput(dynamicQuery),
						
					start,
						
					end
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName7,
					_methodParameterTypes7,
					new Object[] {
						ClpSerializer.translateInput(dynamicQuery),
						
					start,
						
					end,
						
					ClpSerializer.translateInput(orderByComparator)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName8,
					_methodParameterTypes8,
					new Object[] { ClpSerializer.translateInput(dynamicQuery) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Long)returnObj).longValue();
	}

	public com.liferay.osb.model.TrainingEvent fetchTrainingEvent(
		long trainingEventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName9,
					_methodParameterTypes9, new Object[] { trainingEventId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.TrainingEvent)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.osb.model.TrainingEvent getTrainingEvent(
		long trainingEventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName10,
					_methodParameterTypes10, new Object[] { trainingEventId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.TrainingEvent)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName11,
					_methodParameterTypes11,
					new Object[] { ClpSerializer.translateInput(primaryKeyObj) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.portal.model.PersistedModel)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.osb.model.TrainingEvent> getTrainingEvents(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName12,
					_methodParameterTypes12, new Object[] { start, end });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.TrainingEvent>)ClpSerializer.translateOutput(returnObj);
	}

	public int getTrainingEventsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName13,
					_methodParameterTypes13, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.osb.model.TrainingEvent updateTrainingEvent(
		com.liferay.osb.model.TrainingEvent trainingEvent)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName14,
					_methodParameterTypes14,
					new Object[] { ClpSerializer.translateInput(trainingEvent) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.TrainingEvent)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.osb.model.TrainingEvent updateTrainingEvent(
		com.liferay.osb.model.TrainingEvent trainingEvent, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName15,
					_methodParameterTypes15,
					new Object[] {
						ClpSerializer.translateInput(trainingEvent),
						
					merge
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.TrainingEvent)ClpSerializer.translateOutput(returnObj);
	}

	public java.lang.String getBeanIdentifier() {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName16,
					_methodParameterTypes16, new Object[] {  });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.lang.String)ClpSerializer.translateOutput(returnObj);
	}

	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		try {
			_invokableLocalService.invokeMethod(_methodName17,
				_methodParameterTypes17,
				new Object[] { ClpSerializer.translateInput(beanIdentifier) });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		throw new UnsupportedOperationException();
	}

	public com.liferay.osb.model.TrainingEvent addTrainingEvent(long userId,
		long ddmStructureId, long partnerEntryId,
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
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName19,
					_methodParameterTypes19,
					new Object[] {
						userId,
						
					ddmStructureId,
						
					partnerEntryId,
						
					trainingCertificateTemplateId,
						
					trainingCourseId,
						
					trainingLocationId,
						
					ClpSerializer.translateInput(name),
						
					ClpSerializer.translateInput(emailAddress),
						
					portalMinorVersion,
						
					type,
						
					ClpSerializer.translateInput(languageId),
						
					localizedSlides,
						
					ClpSerializer.translateInput(timeZoneId),
						
					startDateMonth,
						
					startDateDay,
						
					startDateYear,
						
					startDateHour,
						
					startDateMinute,
						
					endDateMonth,
						
					endDateDay,
						
					endDateYear,
						
					endDateHour,
						
					endDateMinute,
						
					ClpSerializer.translateInput(addressStreet1),
						
					ClpSerializer.translateInput(addressStreet2),
						
					ClpSerializer.translateInput(addressStreet3),
						
					ClpSerializer.translateInput(addressCity),
						
					ClpSerializer.translateInput(addressZip),
						
					addressRegionId,
						
					addressCountryId,
						
					maxCustomers,
						
					ClpSerializer.translateInput(enrollmentURL),
						
					ClpSerializer.translateInput(serviceContext)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.TrainingEvent)ClpSerializer.translateOutput(returnObj);
	}

	public void checkTrainingEventSurveys(int weeksPast)
		throws java.lang.Exception {
		try {
			_invokableLocalService.invokeMethod(_methodName20,
				_methodParameterTypes20, new Object[] { weeksPast });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.lang.Exception) {
				throw (java.lang.Exception)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public com.liferay.osb.model.TrainingEvent fetchTrainingEventByDDLRecordSetId(
		long ddlRecordSetId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName21,
					_methodParameterTypes21, new Object[] { ddlRecordSetId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.TrainingEvent)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.osb.model.TrainingEvent getTrainingEventByDDLRecordSetId(
		long ddlRecordSetId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName22,
					_methodParameterTypes22, new Object[] { ddlRecordSetId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.TrainingEvent)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.osb.model.TrainingEvent> getTrainingEvents(
		int type, java.util.Date startDate, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName23,
					_methodParameterTypes23,
					new Object[] {
						type,
						
					ClpSerializer.translateInput(startDate),
						
					start,
						
					end,
						
					ClpSerializer.translateInput(obc)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.TrainingEvent>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.osb.model.TrainingEvent> getTrainingEvents(
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName24,
					_methodParameterTypes24,
					new Object[] {
						ClpSerializer.translateInput(params),
						
					start,
						
					end,
						
					ClpSerializer.translateInput(obc)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.TrainingEvent>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.osb.model.TrainingEvent> getTrainingEvents(
		long trainingCourseId, int type, java.util.Date startDate, int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName25,
					_methodParameterTypes25,
					new Object[] {
						trainingCourseId,
						
					type,
						
					ClpSerializer.translateInput(startDate),
						
					start,
						
					end,
						
					ClpSerializer.translateInput(obc)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.TrainingEvent>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.osb.model.TrainingEvent> getTrainingEvents(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName26,
					_methodParameterTypes26,
					new Object[] {
						userId,
						
					start,
						
					end,
						
					ClpSerializer.translateInput(obc)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.TrainingEvent>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.osb.model.TrainingEvent> getTrainingEvents(
		long[] trainingCourseIds, java.util.Date endDateGT, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName27,
					_methodParameterTypes27,
					new Object[] {
						ClpSerializer.translateInput(trainingCourseIds),
						
					ClpSerializer.translateInput(endDateGT),
						
					start,
						
					end,
						
					ClpSerializer.translateInput(obc)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.TrainingEvent>)ClpSerializer.translateOutput(returnObj);
	}

	public int getTrainingEventsCount(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName28,
					_methodParameterTypes28, new Object[] { userId });
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public int getTrainingEventsCount(long[] trainingCourseIds,
		java.util.Date endDateGT)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName29,
					_methodParameterTypes29,
					new Object[] {
						ClpSerializer.translateInput(trainingCourseIds),
						
					ClpSerializer.translateInput(endDateGT)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public java.util.List<com.liferay.osb.model.TrainingEvent> search(
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
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName30,
					_methodParameterTypes30,
					new Object[] {
						ClpSerializer.translateInput(type),
						
					ClpSerializer.translateInput(name),
						
					ClpSerializer.translateInput(course),
						
					ClpSerializer.translateInput(portalMinorVersion),
						
					ClpSerializer.translateInput(city),
						
					ClpSerializer.translateInput(regionId),
						
					ClpSerializer.translateInput(countryId),
						
					ClpSerializer.translateInput(language),
						
					ClpSerializer.translateInput(partner),
						
					startDateGTDay,
						
					startDateGTMonth,
						
					startDateGTYear,
						
					startDateLTDay,
						
					startDateLTMonth,
						
					startDateLTYear,
						
					ClpSerializer.translateInput(trainerFirstName),
						
					ClpSerializer.translateInput(trainerLastName),
						
					ClpSerializer.translateInput(trainerEmailAddress),
						
					ClpSerializer.translateInput(params),
						
					andOperator,
						
					start,
						
					end,
						
					ClpSerializer.translateInput(obc)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.TrainingEvent>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.osb.model.TrainingEvent> search(
		java.lang.String keywords, java.lang.Long regionId,
		java.lang.Long countryId, int startDateGTDay, int startDateGTMonth,
		int startDateGTYear, int startDateLTDay, int startDateLTMonth,
		int startDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName31,
					_methodParameterTypes31,
					new Object[] {
						ClpSerializer.translateInput(keywords),
						
					ClpSerializer.translateInput(regionId),
						
					ClpSerializer.translateInput(countryId),
						
					startDateGTDay,
						
					startDateGTMonth,
						
					startDateGTYear,
						
					startDateLTDay,
						
					startDateLTMonth,
						
					startDateLTYear,
						
					ClpSerializer.translateInput(params),
						
					start,
						
					end,
						
					ClpSerializer.translateInput(obc)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.osb.model.TrainingEvent>)ClpSerializer.translateOutput(returnObj);
	}

	public int searchCount(java.lang.Integer type, java.lang.String name,
		java.lang.String course, java.lang.Integer portalMinorVersion,
		java.lang.String city, java.lang.Long regionId,
		java.lang.Long countryId, java.lang.String language,
		java.lang.String partner, int startDateGTDay, int startDateGTMonth,
		int startDateGTYear, int startDateLTDay, int startDateLTMonth,
		int startDateLTYear, java.lang.String trainerFirstName,
		java.lang.String trainerLastName, java.lang.String trainerEmailAddress,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName32,
					_methodParameterTypes32,
					new Object[] {
						ClpSerializer.translateInput(type),
						
					ClpSerializer.translateInput(name),
						
					ClpSerializer.translateInput(course),
						
					ClpSerializer.translateInput(portalMinorVersion),
						
					ClpSerializer.translateInput(city),
						
					ClpSerializer.translateInput(regionId),
						
					ClpSerializer.translateInput(countryId),
						
					ClpSerializer.translateInput(language),
						
					ClpSerializer.translateInput(partner),
						
					startDateGTDay,
						
					startDateGTMonth,
						
					startDateGTYear,
						
					startDateLTDay,
						
					startDateLTMonth,
						
					startDateLTYear,
						
					ClpSerializer.translateInput(trainerFirstName),
						
					ClpSerializer.translateInput(trainerLastName),
						
					ClpSerializer.translateInput(trainerEmailAddress),
						
					ClpSerializer.translateInput(params),
						
					andOperator
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public int searchCount(java.lang.String keywords, java.lang.Long regionId,
		java.lang.Long countryId, int startDateGTDay, int startDateGTMonth,
		int startDateGTYear, int startDateLTDay, int startDateLTMonth,
		int startDateLTYear,
		java.util.LinkedHashMap<java.lang.String, java.lang.Object> params)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName33,
					_methodParameterTypes33,
					new Object[] {
						ClpSerializer.translateInput(keywords),
						
					ClpSerializer.translateInput(regionId),
						
					ClpSerializer.translateInput(countryId),
						
					startDateGTDay,
						
					startDateGTMonth,
						
					startDateGTYear,
						
					startDateLTDay,
						
					startDateLTMonth,
						
					startDateLTYear,
						
					ClpSerializer.translateInput(params)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public void sendTrainingSurvey(long trainingCustomerId,
		java.lang.String trainingSurveyURL) throws java.lang.Exception {
		try {
			_invokableLocalService.invokeMethod(_methodName34,
				_methodParameterTypes34,
				new Object[] {
					trainingCustomerId,
					
				ClpSerializer.translateInput(trainingSurveyURL)
				});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof java.lang.Exception) {
				throw (java.lang.Exception)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public com.liferay.osb.model.TrainingEvent updateTrainingEvent(
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
		Object returnObj = null;

		try {
			returnObj = _invokableLocalService.invokeMethod(_methodName35,
					_methodParameterTypes35,
					new Object[] {
						trainingEventId,
						
					ddmStructureId,
						
					ddlRecordSetId,
						
					partnerEntryId,
						
					trainingCertificateTemplateId,
						
					trainingCourseId,
						
					trainingLocationId,
						
					ClpSerializer.translateInput(name),
						
					ClpSerializer.translateInput(emailAddress),
						
					portalMinorVersion,
						
					type,
						
					ClpSerializer.translateInput(languageId),
						
					localizedSlides,
						
					ClpSerializer.translateInput(timeZoneId),
						
					startDateMonth,
						
					startDateDay,
						
					startDateYear,
						
					startDateHour,
						
					startDateMinute,
						
					endDateMonth,
						
					endDateDay,
						
					endDateYear,
						
					endDateHour,
						
					endDateMinute,
						
					ClpSerializer.translateInput(addressStreet1),
						
					ClpSerializer.translateInput(addressStreet2),
						
					ClpSerializer.translateInput(addressStreet3),
						
					ClpSerializer.translateInput(addressCity),
						
					ClpSerializer.translateInput(addressZip),
						
					addressRegionId,
						
					addressCountryId,
						
					maxCustomers,
						
					ClpSerializer.translateInput(enrollmentURL),
						
					ClpSerializer.translateInput(serviceContext)
					});
		}
		catch (Throwable t) {
			t = ClpSerializer.translateThrowable(t);

			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.osb.model.TrainingEvent)ClpSerializer.translateOutput(returnObj);
	}

	private InvokableLocalService _invokableLocalService;
	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName19;
	private String[] _methodParameterTypes19;
	private String _methodName20;
	private String[] _methodParameterTypes20;
	private String _methodName21;
	private String[] _methodParameterTypes21;
	private String _methodName22;
	private String[] _methodParameterTypes22;
	private String _methodName23;
	private String[] _methodParameterTypes23;
	private String _methodName24;
	private String[] _methodParameterTypes24;
	private String _methodName25;
	private String[] _methodParameterTypes25;
	private String _methodName26;
	private String[] _methodParameterTypes26;
	private String _methodName27;
	private String[] _methodParameterTypes27;
	private String _methodName28;
	private String[] _methodParameterTypes28;
	private String _methodName29;
	private String[] _methodParameterTypes29;
	private String _methodName30;
	private String[] _methodParameterTypes30;
	private String _methodName31;
	private String[] _methodParameterTypes31;
	private String _methodName32;
	private String[] _methodParameterTypes32;
	private String _methodName33;
	private String[] _methodParameterTypes33;
	private String _methodName34;
	private String[] _methodParameterTypes34;
	private String _methodName35;
	private String[] _methodParameterTypes35;
}