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

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.watson.exception.NoSuchDocumentException;
import com.liferay.watson.model.WatsonDocument;

/**
 * The persistence interface for the watson document service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Steven Smith
 * @see com.liferay.watson.service.persistence.impl.WatsonDocumentPersistenceImpl
 * @see WatsonDocumentUtil
 * @generated
 */
@ProviderType
public interface WatsonDocumentPersistence extends BasePersistence<WatsonDocument> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link WatsonDocumentUtil} to access the watson document persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the watson document in the entity cache if it is enabled.
	*
	* @param watsonDocument the watson document
	*/
	public void cacheResult(WatsonDocument watsonDocument);

	/**
	* Caches the watson documents in the entity cache if it is enabled.
	*
	* @param watsonDocuments the watson documents
	*/
	public void cacheResult(java.util.List<WatsonDocument> watsonDocuments);

	/**
	* Creates a new watson document with the primary key. Does not add the watson document to the database.
	*
	* @param watsonDocumentId the primary key for the new watson document
	* @return the new watson document
	*/
	public WatsonDocument create(long watsonDocumentId);

	/**
	* Removes the watson document with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param watsonDocumentId the primary key of the watson document
	* @return the watson document that was removed
	* @throws NoSuchDocumentException if a watson document with the primary key could not be found
	*/
	public WatsonDocument remove(long watsonDocumentId)
		throws NoSuchDocumentException;

	public WatsonDocument updateImpl(WatsonDocument watsonDocument);

	/**
	* Returns the watson document with the primary key or throws a {@link NoSuchDocumentException} if it could not be found.
	*
	* @param watsonDocumentId the primary key of the watson document
	* @return the watson document
	* @throws NoSuchDocumentException if a watson document with the primary key could not be found
	*/
	public WatsonDocument findByPrimaryKey(long watsonDocumentId)
		throws NoSuchDocumentException;

	/**
	* Returns the watson document with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param watsonDocumentId the primary key of the watson document
	* @return the watson document, or <code>null</code> if a watson document with the primary key could not be found
	*/
	public WatsonDocument fetchByPrimaryKey(long watsonDocumentId);

	@Override
	public java.util.Map<java.io.Serializable, WatsonDocument> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the watson documents.
	*
	* @return the watson documents
	*/
	public java.util.List<WatsonDocument> findAll();

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
	public java.util.List<WatsonDocument> findAll(int start, int end);

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
	public java.util.List<WatsonDocument> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonDocument> orderByComparator);

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
	public java.util.List<WatsonDocument> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<WatsonDocument> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the watson documents from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of watson documents.
	*
	* @return the number of watson documents
	*/
	public int countAll();
}