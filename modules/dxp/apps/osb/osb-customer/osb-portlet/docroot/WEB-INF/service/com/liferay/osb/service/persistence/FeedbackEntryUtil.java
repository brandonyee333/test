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

package com.liferay.osb.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.model.FeedbackEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the feedback entry service. This utility wraps {@link com.liferay.osb.service.persistence.impl.FeedbackEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FeedbackEntryPersistence
 * @see com.liferay.osb.service.persistence.impl.FeedbackEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class FeedbackEntryUtil {
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
	public static void clearCache(FeedbackEntry feedbackEntry) {
		getPersistence().clearCache(feedbackEntry);
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
	public static List<FeedbackEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FeedbackEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FeedbackEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FeedbackEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FeedbackEntry update(FeedbackEntry feedbackEntry) {
		return getPersistence().update(feedbackEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FeedbackEntry update(FeedbackEntry feedbackEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(feedbackEntry, serviceContext);
	}

	/**
	* Caches the feedback entry in the entity cache if it is enabled.
	*
	* @param feedbackEntry the feedback entry
	*/
	public static void cacheResult(FeedbackEntry feedbackEntry) {
		getPersistence().cacheResult(feedbackEntry);
	}

	/**
	* Caches the feedback entries in the entity cache if it is enabled.
	*
	* @param feedbackEntries the feedback entries
	*/
	public static void cacheResult(List<FeedbackEntry> feedbackEntries) {
		getPersistence().cacheResult(feedbackEntries);
	}

	/**
	* Creates a new feedback entry with the primary key. Does not add the feedback entry to the database.
	*
	* @param feedbackEntryId the primary key for the new feedback entry
	* @return the new feedback entry
	*/
	public static FeedbackEntry create(long feedbackEntryId) {
		return getPersistence().create(feedbackEntryId);
	}

	/**
	* Removes the feedback entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param feedbackEntryId the primary key of the feedback entry
	* @return the feedback entry that was removed
	* @throws NoSuchFeedbackEntryException if a feedback entry with the primary key could not be found
	*/
	public static FeedbackEntry remove(long feedbackEntryId)
		throws com.liferay.osb.exception.NoSuchFeedbackEntryException {
		return getPersistence().remove(feedbackEntryId);
	}

	public static FeedbackEntry updateImpl(FeedbackEntry feedbackEntry) {
		return getPersistence().updateImpl(feedbackEntry);
	}

	/**
	* Returns the feedback entry with the primary key or throws a {@link NoSuchFeedbackEntryException} if it could not be found.
	*
	* @param feedbackEntryId the primary key of the feedback entry
	* @return the feedback entry
	* @throws NoSuchFeedbackEntryException if a feedback entry with the primary key could not be found
	*/
	public static FeedbackEntry findByPrimaryKey(long feedbackEntryId)
		throws com.liferay.osb.exception.NoSuchFeedbackEntryException {
		return getPersistence().findByPrimaryKey(feedbackEntryId);
	}

	/**
	* Returns the feedback entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param feedbackEntryId the primary key of the feedback entry
	* @return the feedback entry, or <code>null</code> if a feedback entry with the primary key could not be found
	*/
	public static FeedbackEntry fetchByPrimaryKey(long feedbackEntryId) {
		return getPersistence().fetchByPrimaryKey(feedbackEntryId);
	}

	public static java.util.Map<java.io.Serializable, FeedbackEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the feedback entries.
	*
	* @return the feedback entries
	*/
	public static List<FeedbackEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the feedback entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FeedbackEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of feedback entries
	* @param end the upper bound of the range of feedback entries (not inclusive)
	* @return the range of feedback entries
	*/
	public static List<FeedbackEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the feedback entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FeedbackEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of feedback entries
	* @param end the upper bound of the range of feedback entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of feedback entries
	*/
	public static List<FeedbackEntry> findAll(int start, int end,
		OrderByComparator<FeedbackEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the feedback entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link FeedbackEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of feedback entries
	* @param end the upper bound of the range of feedback entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of feedback entries
	*/
	public static List<FeedbackEntry> findAll(int start, int end,
		OrderByComparator<FeedbackEntry> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the feedback entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of feedback entries.
	*
	* @return the number of feedback entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static FeedbackEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (FeedbackEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					FeedbackEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(FeedbackEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static FeedbackEntryPersistence _persistence;
}