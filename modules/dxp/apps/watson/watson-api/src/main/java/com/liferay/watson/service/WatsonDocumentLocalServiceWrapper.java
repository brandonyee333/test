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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link WatsonDocumentLocalService}.
 *
 * @author Steven Smith
 * @see WatsonDocumentLocalService
 * @generated
 */
public class WatsonDocumentLocalServiceWrapper
	implements ServiceWrapper<WatsonDocumentLocalService>,
			   WatsonDocumentLocalService {

	public WatsonDocumentLocalServiceWrapper(
		WatsonDocumentLocalService watsonDocumentLocalService) {

		_watsonDocumentLocalService = watsonDocumentLocalService;
	}

	/**
	 * Adds the watson document to the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonDocument the watson document
	 * @return the watson document that was added
	 */
	@Override
	public com.liferay.watson.model.WatsonDocument addWatsonDocument(
		com.liferay.watson.model.WatsonDocument watsonDocument) {

		return _watsonDocumentLocalService.addWatsonDocument(watsonDocument);
	}

	/**
	 * Creates a new watson document with the primary key. Does not add the watson document to the database.
	 *
	 * @param watsonDocumentId the primary key for the new watson document
	 * @return the new watson document
	 */
	@Override
	public com.liferay.watson.model.WatsonDocument createWatsonDocument(
		long watsonDocumentId) {

		return _watsonDocumentLocalService.createWatsonDocument(
			watsonDocumentId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonDocumentLocalService.deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the watson document with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonDocumentId the primary key of the watson document
	 * @return the watson document that was removed
	 * @throws PortalException if a watson document with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonDocument deleteWatsonDocument(
			long watsonDocumentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonDocumentLocalService.deleteWatsonDocument(
			watsonDocumentId);
	}

	/**
	 * Deletes the watson document from the database. Also notifies the appropriate model listeners.
	 *
	 * @param watsonDocument the watson document
	 * @return the watson document that was removed
	 */
	@Override
	public com.liferay.watson.model.WatsonDocument deleteWatsonDocument(
		com.liferay.watson.model.WatsonDocument watsonDocument) {

		return _watsonDocumentLocalService.deleteWatsonDocument(watsonDocument);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _watsonDocumentLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _watsonDocumentLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _watsonDocumentLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _watsonDocumentLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _watsonDocumentLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _watsonDocumentLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.watson.model.WatsonDocument fetchWatsonDocument(
		long watsonDocumentId) {

		return _watsonDocumentLocalService.fetchWatsonDocument(
			watsonDocumentId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _watsonDocumentLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _watsonDocumentLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _watsonDocumentLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonDocumentLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the watson document with the primary key.
	 *
	 * @param watsonDocumentId the primary key of the watson document
	 * @return the watson document
	 * @throws PortalException if a watson document with the primary key could not be found
	 */
	@Override
	public com.liferay.watson.model.WatsonDocument getWatsonDocument(
			long watsonDocumentId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _watsonDocumentLocalService.getWatsonDocument(watsonDocumentId);
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
	@Override
	public java.util.List<com.liferay.watson.model.WatsonDocument>
		getWatsonDocuments(int start, int end) {

		return _watsonDocumentLocalService.getWatsonDocuments(start, end);
	}

	/**
	 * Returns the number of watson documents.
	 *
	 * @return the number of watson documents
	 */
	@Override
	public int getWatsonDocumentsCount() {
		return _watsonDocumentLocalService.getWatsonDocumentsCount();
	}

	/**
	 * Updates the watson document in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param watsonDocument the watson document
	 * @return the watson document that was updated
	 */
	@Override
	public com.liferay.watson.model.WatsonDocument updateWatsonDocument(
		com.liferay.watson.model.WatsonDocument watsonDocument) {

		return _watsonDocumentLocalService.updateWatsonDocument(watsonDocument);
	}

	@Override
	public WatsonDocumentLocalService getWrappedService() {
		return _watsonDocumentLocalService;
	}

	@Override
	public void setWrappedService(
		WatsonDocumentLocalService watsonDocumentLocalService) {

		_watsonDocumentLocalService = watsonDocumentLocalService;
	}

	private WatsonDocumentLocalService _watsonDocumentLocalService;

}