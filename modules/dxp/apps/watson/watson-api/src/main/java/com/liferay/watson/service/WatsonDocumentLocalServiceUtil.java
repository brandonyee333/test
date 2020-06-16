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
 * Provides the local service utility for WatsonDocument. This utility wraps
 * <code>com.liferay.watson.service.impl.WatsonDocumentLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Steven Smith
 * @see WatsonDocumentLocalService
 * @generated
 */
public class WatsonDocumentLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.watson.service.impl.WatsonDocumentLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the watson document to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonDocument the watson document
	 * @return the watson document that was added
	 */
	public static com.liferay.watson.model.WatsonDocument addWatsonDocument(
		com.liferay.watson.model.WatsonDocument watsonDocument) {

		return getService().addWatsonDocument(watsonDocument);
	}

	/**
	 * Creates a new watson document with the primary key. Does not add the watson document to the database.
	 *
	 * @param watsonDocumentId the primary key for the new watson document
	 * @return the new watson document
	 */
	public static com.liferay.watson.model.WatsonDocument createWatsonDocument(
		long watsonDocumentId) {

		return getService().createWatsonDocument(watsonDocumentId);
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
	 * Deletes the watson document with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonDocumentId the primary key of the watson document
	 * @return the watson document that was removed
	 * @throws PortalException if a watson document with the primary key could not be found
	 */
	public static com.liferay.watson.model.WatsonDocument deleteWatsonDocument(
			long watsonDocumentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteWatsonDocument(watsonDocumentId);
	}

	/**
	 * Deletes the watson document from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonDocument the watson document
	 * @return the watson document that was removed
	 */
	public static com.liferay.watson.model.WatsonDocument deleteWatsonDocument(
		com.liferay.watson.model.WatsonDocument watsonDocument) {

		return getService().deleteWatsonDocument(watsonDocument);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonDocumentModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonDocumentModelImpl</code>.
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

	public static com.liferay.watson.model.WatsonDocument fetchWatsonDocument(
		long watsonDocumentId) {

		return getService().fetchWatsonDocument(watsonDocumentId);
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
	 * Returns the watson document with the primary key.
	 *
	 * @param watsonDocumentId the primary key of the watson document
	 * @return the watson document
	 * @throws PortalException if a watson document with the primary key could not be found
	 */
	public static com.liferay.watson.model.WatsonDocument getWatsonDocument(
			long watsonDocumentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getWatsonDocument(watsonDocumentId);
	}

	/**
	 * Returns a range of all the watson documents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.watson.model.impl.WatsonDocumentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of watson documents
	 * @param end the upper bound of the range of watson documents (not inclusive)
	 * @return the range of watson documents
	 */
	public static java.util.List<com.liferay.watson.model.WatsonDocument>
		getWatsonDocuments(int start, int end) {

		return getService().getWatsonDocuments(start, end);
	}

	/**
	 * Returns the number of watson documents.
	 *
	 * @return the number of watson documents
	 */
	public static int getWatsonDocumentsCount() {
		return getService().getWatsonDocumentsCount();
	}

	/**
	 * Updates the watson document in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonDocument the watson document
	 * @return the watson document that was updated
	 */
	public static com.liferay.watson.model.WatsonDocument updateWatsonDocument(
		com.liferay.watson.model.WatsonDocument watsonDocument) {

		return getService().updateWatsonDocument(watsonDocument);
	}

	public static WatsonDocumentLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<WatsonDocumentLocalService, WatsonDocumentLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			WatsonDocumentLocalService.class);

		ServiceTracker<WatsonDocumentLocalService, WatsonDocumentLocalService>
			serviceTracker =
				new ServiceTracker
					<WatsonDocumentLocalService, WatsonDocumentLocalService>(
						bundle.getBundleContext(),
						WatsonDocumentLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}