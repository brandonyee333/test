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

package com.liferay.watson.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osgi.util.ServiceTrackerFactory;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.liferay.watson.model.WatsonDocument;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the watson document service. This utility wraps {@link com.liferay.watson.service.persistence.impl.WatsonDocumentPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see WatsonDocumentPersistence
 * @see com.liferay.watson.service.persistence.impl.WatsonDocumentPersistenceImpl
 * @generated
 */
@ProviderType
public class WatsonDocumentUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(WatsonDocument watsonDocument) {
		getPersistence().clearCache(watsonDocument);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<WatsonDocument> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<WatsonDocument> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<WatsonDocument> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<WatsonDocument> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static WatsonDocument update(WatsonDocument watsonDocument) {
		return getPersistence().update(watsonDocument);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static WatsonDocument update(WatsonDocument watsonDocument,
		ServiceContext serviceContext) {
		return getPersistence().update(watsonDocument, serviceContext);
	}

	/**
	* Caches the watson document in the entity cache if it is enabled.
	*
	* @param watsonDocument the watson document
	*/
	public static void cacheResult(WatsonDocument watsonDocument) {
		getPersistence().cacheResult(watsonDocument);
	}

	/**
	* Caches the watson documents in the entity cache if it is enabled.
	*
	* @param watsonDocuments the watson documents
	*/
	public static void cacheResult(List<WatsonDocument> watsonDocuments) {
		getPersistence().cacheResult(watsonDocuments);
	}

	/**
	* Creates a new watson document with the primary key. Does not add the watson document to the database.
	*
	* @param watsonDocumentId the primary key for the new watson document
	* @return the new watson document
	*/
	public static WatsonDocument create(long watsonDocumentId) {
		return getPersistence().create(watsonDocumentId);
	}

	/**
	* Removes the watson document with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonDocumentId the primary key of the watson document
	* @return the watson document that was removed
	* @throws NoSuchDocumentException if a watson document with the primary key could not be found
	*/
	public static WatsonDocument remove(long watsonDocumentId)
		throws com.liferay.watson.exception.NoSuchDocumentException {
		return getPersistence().remove(watsonDocumentId);
	}

	public static WatsonDocument updateImpl(WatsonDocument watsonDocument) {
		return getPersistence().updateImpl(watsonDocument);
	}

	/**
	* Returns the watson document with the primary key or throws a {@link NoSuchDocumentException} if it could not be found.
	*
	* @param watsonDocumentId the primary key of the watson document
	* @return the watson document
	* @throws NoSuchDocumentException if a watson document with the primary key could not be found
	*/
	public static WatsonDocument findByPrimaryKey(long watsonDocumentId)
		throws com.liferay.watson.exception.NoSuchDocumentException {
		return getPersistence().findByPrimaryKey(watsonDocumentId);
	}

	/**
	* Returns the watson document with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonDocumentId the primary key of the watson document
	* @return the watson document, or <code>null</code> if a watson document with the primary key could not be found
	*/
	public static WatsonDocument fetchByPrimaryKey(long watsonDocumentId) {
		return getPersistence().fetchByPrimaryKey(watsonDocumentId);
	}

	public static java.util.Map<java.io.Serializable, WatsonDocument> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the watson documents.
	*
	* @return the watson documents
	*/
	public static List<WatsonDocument> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the watson documents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson documents
	* @param end the upper bound of the range of watson documents (not inclusive)
	* @return the range of watson documents
	*/
	public static List<WatsonDocument> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the watson documents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson documents
	* @param end the upper bound of the range of watson documents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of watson documents
	*/
	public static List<WatsonDocument> findAll(int start, int end,
		OrderByComparator<WatsonDocument> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the watson documents.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link WatsonDocumentModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of watson documents
	* @param end the upper bound of the range of watson documents (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of watson documents
	*/
	public static List<WatsonDocument> findAll(int start, int end,
		OrderByComparator<WatsonDocument> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the watson documents from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of watson documents.
	*
	* @return the number of watson documents
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static WatsonDocumentPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<WatsonDocumentPersistence, WatsonDocumentPersistence> _serviceTracker =
		ServiceTrackerFactory.open(WatsonDocumentPersistence.class);
}