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
import com.liferay.watson.model.WatsonDocumentAudit;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for WatsonDocumentAudit. This utility wraps
 * <code>com.liferay.watson.service.impl.WatsonDocumentAuditLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Steven Smith
 * @see WatsonDocumentAuditLocalService
 * @generated
 */
public class WatsonDocumentAuditLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.watson.service.impl.WatsonDocumentAuditLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the watson document audit to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WatsonDocumentAuditLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param watsonDocumentAudit the watson document audit
	 * @return the watson document audit that was added
	 */
	public static WatsonDocumentAudit addWatsonDocumentAudit(
		WatsonDocumentAudit watsonDocumentAudit) {

		return getService().addWatsonDocumentAudit(watsonDocumentAudit);
	}

	/**
	 * Creates a new watson document audit with the primary key. Does not add the watson document audit to the database.
	 *
	 * @param watsonDocumentAuditId the primary key for the new watson document audit
	 * @return the new watson document audit
	 */
	public static WatsonDocumentAudit createWatsonDocumentAudit(
		long watsonDocumentAuditId) {

		return getService().createWatsonDocumentAudit(watsonDocumentAuditId);
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
	 * Deletes the watson document audit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WatsonDocumentAuditLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param watsonDocumentAuditId the primary key of the watson document audit
	 * @return the watson document audit that was removed
	 * @throws PortalException if a watson document audit with the primary key could not be found
	 */
	public static WatsonDocumentAudit deleteWatsonDocumentAudit(
			long watsonDocumentAuditId)
		throws PortalException {

		return getService().deleteWatsonDocumentAudit(watsonDocumentAuditId);
	}

	/**
	 * Deletes the watson document audit from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WatsonDocumentAuditLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param watsonDocumentAudit the watson document audit
	 * @return the watson document audit that was removed
	 */
	public static WatsonDocumentAudit deleteWatsonDocumentAudit(
		WatsonDocumentAudit watsonDocumentAudit) {

		return getService().deleteWatsonDocumentAudit(watsonDocumentAudit);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonDocumentAuditModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonDocumentAuditModelImpl</code>.
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

	public static WatsonDocumentAudit fetchWatsonDocumentAudit(
		long watsonDocumentAuditId) {

		return getService().fetchWatsonDocumentAudit(watsonDocumentAuditId);
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
	 * Returns the watson document audit with the primary key.
	 *
	 * @param watsonDocumentAuditId the primary key of the watson document audit
	 * @return the watson document audit
	 * @throws PortalException if a watson document audit with the primary key could not be found
	 */
	public static WatsonDocumentAudit getWatsonDocumentAudit(
			long watsonDocumentAuditId)
		throws PortalException {

		return getService().getWatsonDocumentAudit(watsonDocumentAuditId);
	}

	/**
	 * Returns a range of all the watson document audits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonDocumentAuditModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson document audits
	 * @param end the upper bound of the range of watson document audits (not inclusive)
	 * @return the range of watson document audits
	 */
	public static List<WatsonDocumentAudit> getWatsonDocumentAudits(
		int start, int end) {

		return getService().getWatsonDocumentAudits(start, end);
	}

	/**
	 * Returns the number of watson document audits.
	 *
	 * @return the number of watson document audits
	 */
	public static int getWatsonDocumentAuditsCount() {
		return getService().getWatsonDocumentAuditsCount();
	}

	/**
	 * Updates the watson document audit in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect WatsonDocumentAuditLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param watsonDocumentAudit the watson document audit
	 * @return the watson document audit that was updated
	 */
	public static WatsonDocumentAudit updateWatsonDocumentAudit(
		WatsonDocumentAudit watsonDocumentAudit) {

		return getService().updateWatsonDocumentAudit(watsonDocumentAudit);
	}

	public static WatsonDocumentAuditLocalService getService() {
		return _service;
	}

	private static volatile WatsonDocumentAuditLocalService _service;

}