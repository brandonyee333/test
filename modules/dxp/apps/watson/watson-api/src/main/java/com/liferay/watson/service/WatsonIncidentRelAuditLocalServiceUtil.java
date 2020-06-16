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
 * Provides the local service utility for WatsonIncidentRelAudit. This utility wraps
 * <code>com.liferay.watson.service.impl.WatsonIncidentRelAuditLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Steven Smith
 * @see WatsonIncidentRelAuditLocalService
 * @generated
 */
public class WatsonIncidentRelAuditLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.watson.service.impl.WatsonIncidentRelAuditLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the watson incident rel audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRelAudit the watson incident rel audit
	 * @return the watson incident rel audit that was added
	 */
	public static com.liferay.watson.model.WatsonIncidentRelAudit
		addWatsonIncidentRelAudit(
			com.liferay.watson.model.WatsonIncidentRelAudit
				watsonIncidentRelAudit) {

		return getService().addWatsonIncidentRelAudit(watsonIncidentRelAudit);
	}

	/**
	 * Creates a new watson incident rel audit with the primary key. Does not add the watson incident rel audit to the database.
	 *
	 * @param watsonIncidentRelAuditId the primary key for the new watson incident rel audit
	 * @return the new watson incident rel audit
	 */
	public static com.liferay.watson.model.WatsonIncidentRelAudit
		createWatsonIncidentRelAudit(long watsonIncidentRelAuditId) {

		return getService().createWatsonIncidentRelAudit(
			watsonIncidentRelAuditId);
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
	 * Deletes the watson incident rel audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRelAuditId the primary key of the watson incident rel audit
	 * @return the watson incident rel audit that was removed
	 * @throws PortalException if a watson incident rel audit with the primary key could not be found
	 */
	public static com.liferay.watson.model.WatsonIncidentRelAudit
			deleteWatsonIncidentRelAudit(long watsonIncidentRelAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteWatsonIncidentRelAudit(
			watsonIncidentRelAuditId);
	}

	/**
	 * Deletes the watson incident rel audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRelAudit the watson incident rel audit
	 * @return the watson incident rel audit that was removed
	 */
	public static com.liferay.watson.model.WatsonIncidentRelAudit
		deleteWatsonIncidentRelAudit(
			com.liferay.watson.model.WatsonIncidentRelAudit
				watsonIncidentRelAudit) {

		return getService().deleteWatsonIncidentRelAudit(
			watsonIncidentRelAudit);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentRelAuditModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentRelAuditModelImpl</code>.
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

	public static com.liferay.watson.model.WatsonIncidentRelAudit
		fetchWatsonIncidentRelAudit(long watsonIncidentRelAuditId) {

		return getService().fetchWatsonIncidentRelAudit(
			watsonIncidentRelAuditId);
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
	 * Returns the watson incident rel audit with the primary key.
	 *
	 * @param watsonIncidentRelAuditId the primary key of the watson incident rel audit
	 * @return the watson incident rel audit
	 * @throws PortalException if a watson incident rel audit with the primary key could not be found
	 */
	public static com.liferay.watson.model.WatsonIncidentRelAudit
			getWatsonIncidentRelAudit(long watsonIncidentRelAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getWatsonIncidentRelAudit(watsonIncidentRelAuditId);
	}

	/**
	 * Returns a range of all the watson incident rel audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentRelAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident rel audits
	 * @param end the upper bound of the range of watson incident rel audits (not inclusive)
	 * @return the range of watson incident rel audits
	 */
	public static java.util.List
		<com.liferay.watson.model.WatsonIncidentRelAudit>
			getWatsonIncidentRelAudits(int start, int end) {

		return getService().getWatsonIncidentRelAudits(start, end);
	}

	/**
	 * Returns the number of watson incident rel audits.
	 *
	 * @return the number of watson incident rel audits
	 */
	public static int getWatsonIncidentRelAuditsCount() {
		return getService().getWatsonIncidentRelAuditsCount();
	}

	/**
	 * Updates the watson incident rel audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentRelAudit the watson incident rel audit
	 * @return the watson incident rel audit that was updated
	 */
	public static com.liferay.watson.model.WatsonIncidentRelAudit
		updateWatsonIncidentRelAudit(
			com.liferay.watson.model.WatsonIncidentRelAudit
				watsonIncidentRelAudit) {

		return getService().updateWatsonIncidentRelAudit(
			watsonIncidentRelAudit);
	}

	public static WatsonIncidentRelAuditLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<WatsonIncidentRelAuditLocalService, WatsonIncidentRelAuditLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			WatsonIncidentRelAuditLocalService.class);

		ServiceTracker
			<WatsonIncidentRelAuditLocalService,
			 WatsonIncidentRelAuditLocalService> serviceTracker =
				new ServiceTracker
					<WatsonIncidentRelAuditLocalService,
					 WatsonIncidentRelAuditLocalService>(
						 bundle.getBundleContext(),
						 WatsonIncidentRelAuditLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}