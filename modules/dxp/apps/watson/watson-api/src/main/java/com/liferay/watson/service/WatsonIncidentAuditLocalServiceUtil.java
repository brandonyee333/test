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
 * Provides the local service utility for WatsonIncidentAudit. This utility wraps
 * <code>com.liferay.watson.service.impl.WatsonIncidentAuditLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Steven Smith
 * @see WatsonIncidentAuditLocalService
 * @generated
 */
public class WatsonIncidentAuditLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.watson.service.impl.WatsonIncidentAuditLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the watson incident audit to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentAudit the watson incident audit
	 * @return the watson incident audit that was added
	 */
	public static com.liferay.watson.model.WatsonIncidentAudit
		addWatsonIncidentAudit(
			com.liferay.watson.model.WatsonIncidentAudit watsonIncidentAudit) {

		return getService().addWatsonIncidentAudit(watsonIncidentAudit);
	}

	/**
	 * Creates a new watson incident audit with the primary key. Does not add the watson incident audit to the database.
	 *
	 * @param watsonIncidentAuditId the primary key for the new watson incident audit
	 * @return the new watson incident audit
	 */
	public static com.liferay.watson.model.WatsonIncidentAudit
		createWatsonIncidentAudit(long watsonIncidentAuditId) {

		return getService().createWatsonIncidentAudit(watsonIncidentAuditId);
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
	 * Deletes the watson incident audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentAuditId the primary key of the watson incident audit
	 * @return the watson incident audit that was removed
	 * @throws PortalException if a watson incident audit with the primary key could not be found
	 */
	public static com.liferay.watson.model.WatsonIncidentAudit
			deleteWatsonIncidentAudit(long watsonIncidentAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteWatsonIncidentAudit(watsonIncidentAuditId);
	}

	/**
	 * Deletes the watson incident audit from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentAudit the watson incident audit
	 * @return the watson incident audit that was removed
	 */
	public static com.liferay.watson.model.WatsonIncidentAudit
		deleteWatsonIncidentAudit(
			com.liferay.watson.model.WatsonIncidentAudit watsonIncidentAudit) {

		return getService().deleteWatsonIncidentAudit(watsonIncidentAudit);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentAuditModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentAuditModelImpl</code>.
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

	public static com.liferay.watson.model.WatsonIncidentAudit
		fetchWatsonIncidentAudit(long watsonIncidentAuditId) {

		return getService().fetchWatsonIncidentAudit(watsonIncidentAuditId);
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
	 * Returns the watson incident audit with the primary key.
	 *
	 * @param watsonIncidentAuditId the primary key of the watson incident audit
	 * @return the watson incident audit
	 * @throws PortalException if a watson incident audit with the primary key could not be found
	 */
	public static com.liferay.watson.model.WatsonIncidentAudit
			getWatsonIncidentAudit(long watsonIncidentAuditId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getWatsonIncidentAudit(watsonIncidentAuditId);
	}

	/**
	 * Returns a range of all the watson incident audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonIncidentAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson incident audits
	 * @param end the upper bound of the range of watson incident audits (not inclusive)
	 * @return the range of watson incident audits
	 */
	public static java.util.List<com.liferay.watson.model.WatsonIncidentAudit>
		getWatsonIncidentAudits(int start, int end) {

		return getService().getWatsonIncidentAudits(start, end);
	}

	/**
	 * Returns the number of watson incident audits.
	 *
	 * @return the number of watson incident audits
	 */
	public static int getWatsonIncidentAuditsCount() {
		return getService().getWatsonIncidentAuditsCount();
	}

	/**
	 * Updates the watson incident audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonIncidentAudit the watson incident audit
	 * @return the watson incident audit that was updated
	 */
	public static com.liferay.watson.model.WatsonIncidentAudit
		updateWatsonIncidentAudit(
			com.liferay.watson.model.WatsonIncidentAudit watsonIncidentAudit) {

		return getService().updateWatsonIncidentAudit(watsonIncidentAudit);
	}

	public static WatsonIncidentAuditLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<WatsonIncidentAuditLocalService, WatsonIncidentAuditLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			WatsonIncidentAuditLocalService.class);

		ServiceTracker
			<WatsonIncidentAuditLocalService, WatsonIncidentAuditLocalService>
				serviceTracker =
					new ServiceTracker
						<WatsonIncidentAuditLocalService,
						 WatsonIncidentAuditLocalService>(
							 bundle.getBundleContext(),
							 WatsonIncidentAuditLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}