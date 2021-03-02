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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.watson.model.WatsonResourceAudit;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for WatsonResourceAudit. This utility wraps
 * <code>com.liferay.watson.service.impl.WatsonResourceAuditLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Steven Smith
 * @see WatsonResourceAuditLocalService
 * @generated
 */
public class WatsonResourceAuditLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.watson.service.impl.WatsonResourceAuditLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the watson resource audit to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WatsonResourceAuditLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param watsonResourceAudit the watson resource audit
	 * @return the watson resource audit that was added
	 */
	public static WatsonResourceAudit addWatsonResourceAudit(
		WatsonResourceAudit watsonResourceAudit) {

		return getService().addWatsonResourceAudit(watsonResourceAudit);
	}

	/**
	 * Creates a new watson resource audit with the primary key. Does not add the watson resource audit to the database.
	 *
	 * @param watsonResourceAuditId the primary key for the new watson resource audit
	 * @return the new watson resource audit
	 */
	public static WatsonResourceAudit createWatsonResourceAudit(
		long watsonResourceAuditId) {

		return getService().createWatsonResourceAudit(watsonResourceAuditId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the watson resource audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WatsonResourceAuditLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param watsonResourceAuditId the primary key of the watson resource audit
	 * @return the watson resource audit that was removed
	 * @throws PortalException if a watson resource audit with the primary key could not be found
	 */
	public static WatsonResourceAudit deleteWatsonResourceAudit(
			long watsonResourceAuditId)
		throws PortalException {

		return getService().deleteWatsonResourceAudit(watsonResourceAuditId);
	}

	/**
	 * Deletes the watson resource audit from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WatsonResourceAuditLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param watsonResourceAudit the watson resource audit
	 * @return the watson resource audit that was removed
	 */
	public static WatsonResourceAudit deleteWatsonResourceAudit(
		WatsonResourceAudit watsonResourceAudit) {

		return getService().deleteWatsonResourceAudit(watsonResourceAudit);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonResourceAuditModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonResourceAuditModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
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
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static WatsonResourceAudit fetchWatsonResourceAudit(
		long watsonResourceAuditId) {

		return getService().fetchWatsonResourceAudit(watsonResourceAuditId);
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
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson resource audit with the primary key.
	 *
	 * @param watsonResourceAuditId the primary key of the watson resource audit
	 * @return the watson resource audit
	 * @throws PortalException if a watson resource audit with the primary key could not be found
	 */
	public static WatsonResourceAudit getWatsonResourceAudit(
			long watsonResourceAuditId)
		throws PortalException {

		return getService().getWatsonResourceAudit(watsonResourceAuditId);
	}

	/**
	 * Returns a range of all the watson resource audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonResourceAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson resource audits
	 * @param end the upper bound of the range of watson resource audits (not inclusive)
	 * @return the range of watson resource audits
	 */
	public static List<WatsonResourceAudit> getWatsonResourceAudits(
		int start, int end) {

		return getService().getWatsonResourceAudits(start, end);
	}

	/**
	 * Returns the number of watson resource audits.
	 *
	 * @return the number of watson resource audits
	 */
	public static int getWatsonResourceAuditsCount() {
		return getService().getWatsonResourceAuditsCount();
	}

	/**
	 * Updates the watson resource audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WatsonResourceAuditLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param watsonResourceAudit the watson resource audit
	 * @return the watson resource audit that was updated
	 */
	public static WatsonResourceAudit updateWatsonResourceAudit(
		WatsonResourceAudit watsonResourceAudit) {

		return getService().updateWatsonResourceAudit(watsonResourceAudit);
	}

	public static WatsonResourceAuditLocalService getService() {
		return _service;
	}

	private static volatile WatsonResourceAuditLocalService _service;

}