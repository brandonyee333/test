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

import com.liferay.osb.model.SupportTeamLanguage;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the support team language service. This utility wraps {@link com.liferay.osb.service.persistence.impl.SupportTeamLanguagePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeamLanguagePersistence
 * @see com.liferay.osb.service.persistence.impl.SupportTeamLanguagePersistenceImpl
 * @generated
 */
@ProviderType
public class SupportTeamLanguageUtil {
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
	public static void clearCache(SupportTeamLanguage supportTeamLanguage) {
		getPersistence().clearCache(supportTeamLanguage);
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
	public static List<SupportTeamLanguage> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SupportTeamLanguage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SupportTeamLanguage> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SupportTeamLanguage> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SupportTeamLanguage update(
		SupportTeamLanguage supportTeamLanguage) {
		return getPersistence().update(supportTeamLanguage);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SupportTeamLanguage update(
		SupportTeamLanguage supportTeamLanguage, ServiceContext serviceContext) {
		return getPersistence().update(supportTeamLanguage, serviceContext);
	}

	/**
	* Returns all the support team languages where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @return the matching support team languages
	*/
	public static List<SupportTeamLanguage> findBySupportTeamId(
		long supportTeamId) {
		return getPersistence().findBySupportTeamId(supportTeamId);
	}

	/**
	* Returns a range of all the support team languages where supportTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportTeamId the support team ID
	* @param start the lower bound of the range of support team languages
	* @param end the upper bound of the range of support team languages (not inclusive)
	* @return the range of matching support team languages
	*/
	public static List<SupportTeamLanguage> findBySupportTeamId(
		long supportTeamId, int start, int end) {
		return getPersistence().findBySupportTeamId(supportTeamId, start, end);
	}

	/**
	* Returns an ordered range of all the support team languages where supportTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportTeamId the support team ID
	* @param start the lower bound of the range of support team languages
	* @param end the upper bound of the range of support team languages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support team languages
	*/
	public static List<SupportTeamLanguage> findBySupportTeamId(
		long supportTeamId, int start, int end,
		OrderByComparator<SupportTeamLanguage> orderByComparator) {
		return getPersistence()
				   .findBySupportTeamId(supportTeamId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the support team languages where supportTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportTeamId the support team ID
	* @param start the lower bound of the range of support team languages
	* @param end the upper bound of the range of support team languages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching support team languages
	*/
	public static List<SupportTeamLanguage> findBySupportTeamId(
		long supportTeamId, int start, int end,
		OrderByComparator<SupportTeamLanguage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySupportTeamId(supportTeamId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first support team language in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support team language
	* @throws NoSuchSupportTeamLanguageException if a matching support team language could not be found
	*/
	public static SupportTeamLanguage findBySupportTeamId_First(
		long supportTeamId,
		OrderByComparator<SupportTeamLanguage> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSupportTeamLanguageException {
		return getPersistence()
				   .findBySupportTeamId_First(supportTeamId, orderByComparator);
	}

	/**
	* Returns the first support team language in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support team language, or <code>null</code> if a matching support team language could not be found
	*/
	public static SupportTeamLanguage fetchBySupportTeamId_First(
		long supportTeamId,
		OrderByComparator<SupportTeamLanguage> orderByComparator) {
		return getPersistence()
				   .fetchBySupportTeamId_First(supportTeamId, orderByComparator);
	}

	/**
	* Returns the last support team language in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support team language
	* @throws NoSuchSupportTeamLanguageException if a matching support team language could not be found
	*/
	public static SupportTeamLanguage findBySupportTeamId_Last(
		long supportTeamId,
		OrderByComparator<SupportTeamLanguage> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSupportTeamLanguageException {
		return getPersistence()
				   .findBySupportTeamId_Last(supportTeamId, orderByComparator);
	}

	/**
	* Returns the last support team language in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support team language, or <code>null</code> if a matching support team language could not be found
	*/
	public static SupportTeamLanguage fetchBySupportTeamId_Last(
		long supportTeamId,
		OrderByComparator<SupportTeamLanguage> orderByComparator) {
		return getPersistence()
				   .fetchBySupportTeamId_Last(supportTeamId, orderByComparator);
	}

	/**
	* Returns the support team languages before and after the current support team language in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamLanguageId the primary key of the current support team language
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support team language
	* @throws NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	*/
	public static SupportTeamLanguage[] findBySupportTeamId_PrevAndNext(
		long supportTeamLanguageId, long supportTeamId,
		OrderByComparator<SupportTeamLanguage> orderByComparator)
		throws com.liferay.osb.exception.NoSuchSupportTeamLanguageException {
		return getPersistence()
				   .findBySupportTeamId_PrevAndNext(supportTeamLanguageId,
			supportTeamId, orderByComparator);
	}

	/**
	* Removes all the support team languages where supportTeamId = &#63; from the database.
	*
	* @param supportTeamId the support team ID
	*/
	public static void removeBySupportTeamId(long supportTeamId) {
		getPersistence().removeBySupportTeamId(supportTeamId);
	}

	/**
	* Returns the number of support team languages where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @return the number of matching support team languages
	*/
	public static int countBySupportTeamId(long supportTeamId) {
		return getPersistence().countBySupportTeamId(supportTeamId);
	}

	/**
	* Caches the support team language in the entity cache if it is enabled.
	*
	* @param supportTeamLanguage the support team language
	*/
	public static void cacheResult(SupportTeamLanguage supportTeamLanguage) {
		getPersistence().cacheResult(supportTeamLanguage);
	}

	/**
	* Caches the support team languages in the entity cache if it is enabled.
	*
	* @param supportTeamLanguages the support team languages
	*/
	public static void cacheResult(
		List<SupportTeamLanguage> supportTeamLanguages) {
		getPersistence().cacheResult(supportTeamLanguages);
	}

	/**
	* Creates a new support team language with the primary key. Does not add the support team language to the database.
	*
	* @param supportTeamLanguageId the primary key for the new support team language
	* @return the new support team language
	*/
	public static SupportTeamLanguage create(long supportTeamLanguageId) {
		return getPersistence().create(supportTeamLanguageId);
	}

	/**
	* Removes the support team language with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeamLanguageId the primary key of the support team language
	* @return the support team language that was removed
	* @throws NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	*/
	public static SupportTeamLanguage remove(long supportTeamLanguageId)
		throws com.liferay.osb.exception.NoSuchSupportTeamLanguageException {
		return getPersistence().remove(supportTeamLanguageId);
	}

	public static SupportTeamLanguage updateImpl(
		SupportTeamLanguage supportTeamLanguage) {
		return getPersistence().updateImpl(supportTeamLanguage);
	}

	/**
	* Returns the support team language with the primary key or throws a {@link NoSuchSupportTeamLanguageException} if it could not be found.
	*
	* @param supportTeamLanguageId the primary key of the support team language
	* @return the support team language
	* @throws NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	*/
	public static SupportTeamLanguage findByPrimaryKey(
		long supportTeamLanguageId)
		throws com.liferay.osb.exception.NoSuchSupportTeamLanguageException {
		return getPersistence().findByPrimaryKey(supportTeamLanguageId);
	}

	/**
	* Returns the support team language with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportTeamLanguageId the primary key of the support team language
	* @return the support team language, or <code>null</code> if a support team language with the primary key could not be found
	*/
	public static SupportTeamLanguage fetchByPrimaryKey(
		long supportTeamLanguageId) {
		return getPersistence().fetchByPrimaryKey(supportTeamLanguageId);
	}

	public static java.util.Map<java.io.Serializable, SupportTeamLanguage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the support team languages.
	*
	* @return the support team languages
	*/
	public static List<SupportTeamLanguage> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the support team languages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support team languages
	* @param end the upper bound of the range of support team languages (not inclusive)
	* @return the range of support team languages
	*/
	public static List<SupportTeamLanguage> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the support team languages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support team languages
	* @param end the upper bound of the range of support team languages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support team languages
	*/
	public static List<SupportTeamLanguage> findAll(int start, int end,
		OrderByComparator<SupportTeamLanguage> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the support team languages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportTeamLanguageModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support team languages
	* @param end the upper bound of the range of support team languages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of support team languages
	*/
	public static List<SupportTeamLanguage> findAll(int start, int end,
		OrderByComparator<SupportTeamLanguage> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the support team languages from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of support team languages.
	*
	* @return the number of support team languages
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SupportTeamLanguagePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SupportTeamLanguagePersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					SupportTeamLanguagePersistence.class.getName());

			ReferenceRegistry.registerReference(SupportTeamLanguageUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static SupportTeamLanguagePersistence _persistence;
}