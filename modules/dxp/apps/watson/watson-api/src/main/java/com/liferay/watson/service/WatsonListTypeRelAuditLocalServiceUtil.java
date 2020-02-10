/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.watson.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for WatsonListTypeRelAudit. This utility wraps
 * <code>com.liferay.watson.service.impl.WatsonListTypeRelAuditLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Steven Smith
 * @see WatsonListTypeRelAuditLocalService
 * @generated
 */
public class WatsonListTypeRelAuditLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.watson.service.impl.WatsonListTypeRelAuditLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the watson list type rel audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeRelAudit the watson list type rel audit
	 * @return the watson list type rel audit that was added
	 */
	public static com.liferay.watson.model.WatsonListTypeRelAudit
		addWatsonListTypeRelAudit(
			com.liferay.watson.model.WatsonListTypeRelAudit
				watsonListTypeRelAudit) {

		return getService().addWatsonListTypeRelAudit(watsonListTypeRelAudit);
	}

	/**
	 * Creates a new watson list type rel audit with the primary key. Does not add the watson list type rel audit to the database.
	 *
	 * @param watsonListTypeRelAuditId the primary key for the new watson list type rel audit
	 * @return the new watson list type rel audit
	 */
	public static com.liferay.watson.model.WatsonListTypeRelAudit
		createWatsonListTypeRelAudit(long watsonListTypeRelAuditId) {

		return getService().createWatsonListTypeRelAudit(
			watsonListTypeRelAuditId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the watson list type rel audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeRelAuditId the primary key of the watson list type rel audit
	 * @return the watson list type rel audit that was removed
	 * @throws PortalException if a watson list type rel audit with the primary key could not be found
	 */
	public static com.liferay.watson.model.WatsonListTypeRelAudit
			deleteWatsonListTypeRelAudit(long watsonListTypeRelAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteWatsonListTypeRelAudit(
			watsonListTypeRelAuditId);
	}

	/**
	 * Deletes the watson list type rel audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeRelAudit the watson list type rel audit
	 * @return the watson list type rel audit that was removed
	 */
	public static com.liferay.watson.model.WatsonListTypeRelAudit
		deleteWatsonListTypeRelAudit(
			com.liferay.watson.model.WatsonListTypeRelAudit
				watsonListTypeRelAudit) {

		return getService().deleteWatsonListTypeRelAudit(
			watsonListTypeRelAudit);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonListTypeRelAuditModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonListTypeRelAuditModelImpl</code>.
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

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
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

	public static com.liferay.watson.model.WatsonListTypeRelAudit
		fetchWatsonListTypeRelAudit(long watsonListTypeRelAuditId) {

		return getService().fetchWatsonListTypeRelAudit(
			watsonListTypeRelAuditId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson list type rel audit with the primary key.
	 *
	 * @param watsonListTypeRelAuditId the primary key of the watson list type rel audit
	 * @return the watson list type rel audit
	 * @throws PortalException if a watson list type rel audit with the primary key could not be found
	 */
	public static com.liferay.watson.model.WatsonListTypeRelAudit
			getWatsonListTypeRelAudit(long watsonListTypeRelAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getWatsonListTypeRelAudit(watsonListTypeRelAuditId);
	}

	/**
	 * Returns a range of all the watson list type rel audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonListTypeRelAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson list type rel audits
	 * @param end the upper bound of the range of watson list type rel audits (not inclusive)
	 * @return the range of watson list type rel audits
	 */
	public static java.util.List
		<com.liferay.watson.model.WatsonListTypeRelAudit>
			getWatsonListTypeRelAudits(int start, int end) {

		return getService().getWatsonListTypeRelAudits(start, end);
	}

	/**
	 * Returns the number of watson list type rel audits.
	 *
	 * @return the number of watson list type rel audits
	 */
	public static int getWatsonListTypeRelAuditsCount() {
		return getService().getWatsonListTypeRelAuditsCount();
	}

	/**
	 * Updates the watson list type rel audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonListTypeRelAudit the watson list type rel audit
	 * @return the watson list type rel audit that was updated
	 */
	public static com.liferay.watson.model.WatsonListTypeRelAudit
		updateWatsonListTypeRelAudit(
			com.liferay.watson.model.WatsonListTypeRelAudit
				watsonListTypeRelAudit) {

		return getService().updateWatsonListTypeRelAudit(
			watsonListTypeRelAudit);
	}

	public static WatsonListTypeRelAuditLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<WatsonListTypeRelAuditLocalService, WatsonListTypeRelAuditLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			WatsonListTypeRelAuditLocalService.class);

		ServiceTracker
			<WatsonListTypeRelAuditLocalService,
			 WatsonListTypeRelAuditLocalService> serviceTracker =
				new ServiceTracker
					<WatsonListTypeRelAuditLocalService,
					 WatsonListTypeRelAuditLocalService>(
						 bundle.getBundleContext(),
						 WatsonListTypeRelAuditLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}