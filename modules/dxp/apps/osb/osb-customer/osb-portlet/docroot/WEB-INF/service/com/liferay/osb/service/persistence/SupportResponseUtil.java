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

import com.liferay.osb.model.SupportResponse;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the support response service. This utility wraps {@link com.liferay.osb.service.persistence.impl.SupportResponsePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportResponsePersistence
 * @see com.liferay.osb.service.persistence.impl.SupportResponsePersistenceImpl
 * @generated
 */
@ProviderType
public class SupportResponseUtil {
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
	public static void clearCache(SupportResponse supportResponse) {
		getPersistence().clearCache(supportResponse);
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
	public static List<SupportResponse> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SupportResponse> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SupportResponse> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SupportResponse> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SupportResponse update(SupportResponse supportResponse) {
		return getPersistence().update(supportResponse);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SupportResponse update(SupportResponse supportResponse,
		ServiceContext serviceContext) {
		return getPersistence().update(supportResponse, serviceContext);
	}

	/**
	* Returns the support response where name = &#63; or throws a {@link NoSuchSupportResponseException} if it could not be found.
	*
	* @param name the name
	* @return the matching support response
	* @throws NoSuchSupportResponseException if a matching support response could not be found
	*/
	public static SupportResponse findByName(java.lang.String name)
		throws com.liferay.osb.exception.NoSuchSupportResponseException {
		return getPersistence().findByName(name);
	}

	/**
	* Returns the support response where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @return the matching support response, or <code>null</code> if a matching support response could not be found
	*/
	public static SupportResponse fetchByName(java.lang.String name) {
		return getPersistence().fetchByName(name);
	}

	/**
	* Returns the support response where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching support response, or <code>null</code> if a matching support response could not be found
	*/
	public static SupportResponse fetchByName(java.lang.String name,
		boolean retrieveFromCache) {
		return getPersistence().fetchByName(name, retrieveFromCache);
	}

	/**
	* Removes the support response where name = &#63; from the database.
	*
	* @param name the name
	* @return the support response that was removed
	*/
	public static SupportResponse removeByName(java.lang.String name)
		throws com.liferay.osb.exception.NoSuchSupportResponseException {
		return getPersistence().removeByName(name);
	}

	/**
	* Returns the number of support responses where name = &#63;.
	*
	* @param name the name
	* @return the number of matching support responses
	*/
	public static int countByName(java.lang.String name) {
		return getPersistence().countByName(name);
	}

	/**
	* Caches the support response in the entity cache if it is enabled.
	*
	* @param supportResponse the support response
	*/
	public static void cacheResult(SupportResponse supportResponse) {
		getPersistence().cacheResult(supportResponse);
	}

	/**
	* Caches the support responses in the entity cache if it is enabled.
	*
	* @param supportResponses the support responses
	*/
	public static void cacheResult(List<SupportResponse> supportResponses) {
		getPersistence().cacheResult(supportResponses);
	}

	/**
	* Creates a new support response with the primary key. Does not add the support response to the database.
	*
	* @param supportResponseId the primary key for the new support response
	* @return the new support response
	*/
	public static SupportResponse create(long supportResponseId) {
		return getPersistence().create(supportResponseId);
	}

	/**
	* Removes the support response with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportResponseId the primary key of the support response
	* @return the support response that was removed
	* @throws NoSuchSupportResponseException if a support response with the primary key could not be found
	*/
	public static SupportResponse remove(long supportResponseId)
		throws com.liferay.osb.exception.NoSuchSupportResponseException {
		return getPersistence().remove(supportResponseId);
	}

	public static SupportResponse updateImpl(SupportResponse supportResponse) {
		return getPersistence().updateImpl(supportResponse);
	}

	/**
	* Returns the support response with the primary key or throws a {@link NoSuchSupportResponseException} if it could not be found.
	*
	* @param supportResponseId the primary key of the support response
	* @return the support response
	* @throws NoSuchSupportResponseException if a support response with the primary key could not be found
	*/
	public static SupportResponse findByPrimaryKey(long supportResponseId)
		throws com.liferay.osb.exception.NoSuchSupportResponseException {
		return getPersistence().findByPrimaryKey(supportResponseId);
	}

	/**
	* Returns the support response with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportResponseId the primary key of the support response
	* @return the support response, or <code>null</code> if a support response with the primary key could not be found
	*/
	public static SupportResponse fetchByPrimaryKey(long supportResponseId) {
		return getPersistence().fetchByPrimaryKey(supportResponseId);
	}

	public static java.util.Map<java.io.Serializable, SupportResponse> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the support responses.
	*
	* @return the support responses
	*/
	public static List<SupportResponse> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the support responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support responses
	* @param end the upper bound of the range of support responses (not inclusive)
	* @return the range of support responses
	*/
	public static List<SupportResponse> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the support responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support responses
	* @param end the upper bound of the range of support responses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support responses
	*/
	public static List<SupportResponse> findAll(int start, int end,
		OrderByComparator<SupportResponse> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the support responses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportResponseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support responses
	* @param end the upper bound of the range of support responses (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of support responses
	*/
	public static List<SupportResponse> findAll(int start, int end,
		OrderByComparator<SupportResponse> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the support responses from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of support responses.
	*
	* @return the number of support responses
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SupportResponsePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SupportResponsePersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					SupportResponsePersistence.class.getName());

			ReferenceRegistry.registerReference(SupportResponseUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static SupportResponsePersistence _persistence;
}