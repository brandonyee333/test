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

import com.liferay.osb.model.OfferingDefinition;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the offering definition service. This utility wraps {@link OfferingDefinitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingDefinitionPersistence
 * @see OfferingDefinitionPersistenceImpl
 * @generated
 */
public class OfferingDefinitionUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(OfferingDefinition offeringDefinition) {
		getPersistence().clearCache(offeringDefinition);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<OfferingDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OfferingDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OfferingDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static OfferingDefinition update(
		OfferingDefinition offeringDefinition, boolean merge)
		throws SystemException {
		return getPersistence().update(offeringDefinition, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static OfferingDefinition update(
		OfferingDefinition offeringDefinition, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(offeringDefinition, merge, serviceContext);
	}

	/**
	* Caches the offering definition in the entity cache if it is enabled.
	*
	* @param offeringDefinition the offering definition
	*/
	public static void cacheResult(
		com.liferay.osb.model.OfferingDefinition offeringDefinition) {
		getPersistence().cacheResult(offeringDefinition);
	}

	/**
	* Caches the offering definitions in the entity cache if it is enabled.
	*
	* @param offeringDefinitions the offering definitions
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.OfferingDefinition> offeringDefinitions) {
		getPersistence().cacheResult(offeringDefinitions);
	}

	/**
	* Creates a new offering definition with the primary key. Does not add the offering definition to the database.
	*
	* @param offeringDefinitionId the primary key for the new offering definition
	* @return the new offering definition
	*/
	public static com.liferay.osb.model.OfferingDefinition create(
		long offeringDefinitionId) {
		return getPersistence().create(offeringDefinitionId);
	}

	/**
	* Removes the offering definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition that was removed
	* @throws com.liferay.osb.NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition remove(
		long offeringDefinitionId)
		throws com.liferay.osb.NoSuchOfferingDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(offeringDefinitionId);
	}

	public static com.liferay.osb.model.OfferingDefinition updateImpl(
		com.liferay.osb.model.OfferingDefinition offeringDefinition,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(offeringDefinition, merge);
	}

	/**
	* Returns the offering definition with the primary key or throws a {@link com.liferay.osb.NoSuchOfferingDefinitionException} if it could not be found.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition
	* @throws com.liferay.osb.NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition findByPrimaryKey(
		long offeringDefinitionId)
		throws com.liferay.osb.NoSuchOfferingDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(offeringDefinitionId);
	}

	/**
	* Returns the offering definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition, or <code>null</code> if a offering definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition fetchByPrimaryKey(
		long offeringDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(offeringDefinitionId);
	}

	/**
	* Returns all the offering definitions where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @return the matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> findByProductEntryId(
		long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByProductEntryId(productEntryId);
	}

	/**
	* Returns a range of all the offering definitions where productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @return the range of matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> findByProductEntryId(
		long productEntryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByProductEntryId(productEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the offering definitions where productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> findByProductEntryId(
		long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByProductEntryId(productEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first offering definition in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering definition
	* @throws com.liferay.osb.NoSuchOfferingDefinitionException if a matching offering definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition findByProductEntryId_First(
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByProductEntryId_First(productEntryId, orderByComparator);
	}

	/**
	* Returns the first offering definition in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering definition, or <code>null</code> if a matching offering definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition fetchByProductEntryId_First(
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByProductEntryId_First(productEntryId,
			orderByComparator);
	}

	/**
	* Returns the last offering definition in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering definition
	* @throws com.liferay.osb.NoSuchOfferingDefinitionException if a matching offering definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition findByProductEntryId_Last(
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByProductEntryId_Last(productEntryId, orderByComparator);
	}

	/**
	* Returns the last offering definition in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering definition, or <code>null</code> if a matching offering definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition fetchByProductEntryId_Last(
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByProductEntryId_Last(productEntryId, orderByComparator);
	}

	/**
	* Returns the offering definitions before and after the current offering definition in the ordered set where productEntryId = &#63;.
	*
	* @param offeringDefinitionId the primary key of the current offering definition
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering definition
	* @throws com.liferay.osb.NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition[] findByProductEntryId_PrevAndNext(
		long offeringDefinitionId, long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByProductEntryId_PrevAndNext(offeringDefinitionId,
			productEntryId, orderByComparator);
	}

	/**
	* Returns all the offering definitions where supportResponseId = &#63;.
	*
	* @param supportResponseId the support response ID
	* @return the matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> findBySupportResponseId(
		long supportResponseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySupportResponseId(supportResponseId);
	}

	/**
	* Returns a range of all the offering definitions where supportResponseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportResponseId the support response ID
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @return the range of matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> findBySupportResponseId(
		long supportResponseId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportResponseId(supportResponseId, start, end);
	}

	/**
	* Returns an ordered range of all the offering definitions where supportResponseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportResponseId the support response ID
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> findBySupportResponseId(
		long supportResponseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportResponseId(supportResponseId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first offering definition in the ordered set where supportResponseId = &#63;.
	*
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering definition
	* @throws com.liferay.osb.NoSuchOfferingDefinitionException if a matching offering definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition findBySupportResponseId_First(
		long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportResponseId_First(supportResponseId,
			orderByComparator);
	}

	/**
	* Returns the first offering definition in the ordered set where supportResponseId = &#63;.
	*
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering definition, or <code>null</code> if a matching offering definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition fetchBySupportResponseId_First(
		long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySupportResponseId_First(supportResponseId,
			orderByComparator);
	}

	/**
	* Returns the last offering definition in the ordered set where supportResponseId = &#63;.
	*
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering definition
	* @throws com.liferay.osb.NoSuchOfferingDefinitionException if a matching offering definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition findBySupportResponseId_Last(
		long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportResponseId_Last(supportResponseId,
			orderByComparator);
	}

	/**
	* Returns the last offering definition in the ordered set where supportResponseId = &#63;.
	*
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering definition, or <code>null</code> if a matching offering definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition fetchBySupportResponseId_Last(
		long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySupportResponseId_Last(supportResponseId,
			orderByComparator);
	}

	/**
	* Returns the offering definitions before and after the current offering definition in the ordered set where supportResponseId = &#63;.
	*
	* @param offeringDefinitionId the primary key of the current offering definition
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering definition
	* @throws com.liferay.osb.NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition[] findBySupportResponseId_PrevAndNext(
		long offeringDefinitionId, long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySupportResponseId_PrevAndNext(offeringDefinitionId,
			supportResponseId, orderByComparator);
	}

	/**
	* Returns all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @return the matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> findByPEI_SRI(
		long productEntryId, long supportResponseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPEI_SRI(productEntryId, supportResponseId);
	}

	/**
	* Returns a range of all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @return the range of matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> findByPEI_SRI(
		long productEntryId, long supportResponseId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPEI_SRI(productEntryId, supportResponseId, start, end);
	}

	/**
	* Returns an ordered range of all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> findByPEI_SRI(
		long productEntryId, long supportResponseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPEI_SRI(productEntryId, supportResponseId, start,
			end, orderByComparator);
	}

	/**
	* Returns the first offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering definition
	* @throws com.liferay.osb.NoSuchOfferingDefinitionException if a matching offering definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition findByPEI_SRI_First(
		long productEntryId, long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPEI_SRI_First(productEntryId, supportResponseId,
			orderByComparator);
	}

	/**
	* Returns the first offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering definition, or <code>null</code> if a matching offering definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition fetchByPEI_SRI_First(
		long productEntryId, long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPEI_SRI_First(productEntryId, supportResponseId,
			orderByComparator);
	}

	/**
	* Returns the last offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering definition
	* @throws com.liferay.osb.NoSuchOfferingDefinitionException if a matching offering definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition findByPEI_SRI_Last(
		long productEntryId, long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPEI_SRI_Last(productEntryId, supportResponseId,
			orderByComparator);
	}

	/**
	* Returns the last offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering definition, or <code>null</code> if a matching offering definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition fetchByPEI_SRI_Last(
		long productEntryId, long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPEI_SRI_Last(productEntryId, supportResponseId,
			orderByComparator);
	}

	/**
	* Returns the offering definitions before and after the current offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* @param offeringDefinitionId the primary key of the current offering definition
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering definition
	* @throws com.liferay.osb.NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition[] findByPEI_SRI_PrevAndNext(
		long offeringDefinitionId, long productEntryId, long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchOfferingDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPEI_SRI_PrevAndNext(offeringDefinitionId,
			productEntryId, supportResponseId, orderByComparator);
	}

	/**
	* Returns all the offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryIds the product entry IDs
	* @param supportResponseIds the support response IDs
	* @return the matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> findByPEI_SRI(
		long[] productEntryIds, long[] supportResponseIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPEI_SRI(productEntryIds, supportResponseIds);
	}

	/**
	* Returns a range of all the offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryIds the product entry IDs
	* @param supportResponseIds the support response IDs
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @return the range of matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> findByPEI_SRI(
		long[] productEntryIds, long[] supportResponseIds, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPEI_SRI(productEntryIds, supportResponseIds, start,
			end);
	}

	/**
	* Returns an ordered range of all the offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param productEntryIds the product entry IDs
	* @param supportResponseIds the support response IDs
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> findByPEI_SRI(
		long[] productEntryIds, long[] supportResponseIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPEI_SRI(productEntryIds, supportResponseIds, start,
			end, orderByComparator);
	}

	/**
	* Returns the offering definition where productEntryId = &#63; and supportResponseId = &#63; and productDescription = &#63; and licenses = &#63; and unlimitedLicenses = &#63; and supportTickets = &#63; or throws a {@link com.liferay.osb.NoSuchOfferingDefinitionException} if it could not be found.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param productDescription the product description
	* @param licenses the licenses
	* @param unlimitedLicenses the unlimited licenses
	* @param supportTickets the support tickets
	* @return the matching offering definition
	* @throws com.liferay.osb.NoSuchOfferingDefinitionException if a matching offering definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition findByPEI_SRI_PD_L_UL_ST(
		long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets)
		throws com.liferay.osb.NoSuchOfferingDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByPEI_SRI_PD_L_UL_ST(productEntryId, supportResponseId,
			productDescription, licenses, unlimitedLicenses, supportTickets);
	}

	/**
	* Returns the offering definition where productEntryId = &#63; and supportResponseId = &#63; and productDescription = &#63; and licenses = &#63; and unlimitedLicenses = &#63; and supportTickets = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param productDescription the product description
	* @param licenses the licenses
	* @param unlimitedLicenses the unlimited licenses
	* @param supportTickets the support tickets
	* @return the matching offering definition, or <code>null</code> if a matching offering definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition fetchByPEI_SRI_PD_L_UL_ST(
		long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPEI_SRI_PD_L_UL_ST(productEntryId,
			supportResponseId, productDescription, licenses, unlimitedLicenses,
			supportTickets);
	}

	/**
	* Returns the offering definition where productEntryId = &#63; and supportResponseId = &#63; and productDescription = &#63; and licenses = &#63; and unlimitedLicenses = &#63; and supportTickets = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param productDescription the product description
	* @param licenses the licenses
	* @param unlimitedLicenses the unlimited licenses
	* @param supportTickets the support tickets
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching offering definition, or <code>null</code> if a matching offering definition could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition fetchByPEI_SRI_PD_L_UL_ST(
		long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByPEI_SRI_PD_L_UL_ST(productEntryId,
			supportResponseId, productDescription, licenses, unlimitedLicenses,
			supportTickets, retrieveFromCache);
	}

	/**
	* Returns all the offering definitions.
	*
	* @return the offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the offering definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @return the range of offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the offering definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingDefinition> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the offering definitions where productEntryId = &#63; from the database.
	*
	* @param productEntryId the product entry ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByProductEntryId(long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByProductEntryId(productEntryId);
	}

	/**
	* Removes all the offering definitions where supportResponseId = &#63; from the database.
	*
	* @param supportResponseId the support response ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySupportResponseId(long supportResponseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySupportResponseId(supportResponseId);
	}

	/**
	* Removes all the offering definitions where productEntryId = &#63; and supportResponseId = &#63; from the database.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByPEI_SRI(long productEntryId,
		long supportResponseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByPEI_SRI(productEntryId, supportResponseId);
	}

	/**
	* Removes the offering definition where productEntryId = &#63; and supportResponseId = &#63; and productDescription = &#63; and licenses = &#63; and unlimitedLicenses = &#63; and supportTickets = &#63; from the database.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param productDescription the product description
	* @param licenses the licenses
	* @param unlimitedLicenses the unlimited licenses
	* @param supportTickets the support tickets
	* @return the offering definition that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.OfferingDefinition removeByPEI_SRI_PD_L_UL_ST(
		long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets)
		throws com.liferay.osb.NoSuchOfferingDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .removeByPEI_SRI_PD_L_UL_ST(productEntryId,
			supportResponseId, productDescription, licenses, unlimitedLicenses,
			supportTickets);
	}

	/**
	* Removes all the offering definitions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of offering definitions where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @return the number of matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByProductEntryId(long productEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByProductEntryId(productEntryId);
	}

	/**
	* Returns the number of offering definitions where supportResponseId = &#63;.
	*
	* @param supportResponseId the support response ID
	* @return the number of matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySupportResponseId(long supportResponseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySupportResponseId(supportResponseId);
	}

	/**
	* Returns the number of offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @return the number of matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPEI_SRI(long productEntryId, long supportResponseId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByPEI_SRI(productEntryId, supportResponseId);
	}

	/**
	* Returns the number of offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	*
	* @param productEntryIds the product entry IDs
	* @param supportResponseIds the support response IDs
	* @return the number of matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPEI_SRI(long[] productEntryIds,
		long[] supportResponseIds)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByPEI_SRI(productEntryIds, supportResponseIds);
	}

	/**
	* Returns the number of offering definitions where productEntryId = &#63; and supportResponseId = &#63; and productDescription = &#63; and licenses = &#63; and unlimitedLicenses = &#63; and supportTickets = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param productDescription the product description
	* @param licenses the licenses
	* @param unlimitedLicenses the unlimited licenses
	* @param supportTickets the support tickets
	* @return the number of matching offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static int countByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, java.lang.String productDescription,
		boolean licenses, boolean unlimitedLicenses, boolean supportTickets)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByPEI_SRI_PD_L_UL_ST(productEntryId,
			supportResponseId, productDescription, licenses, unlimitedLicenses,
			supportTickets);
	}

	/**
	* Returns the number of offering definitions.
	*
	* @return the number of offering definitions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	/**
	* Returns all the offering bundles associated with the offering definition.
	*
	* @param pk the primary key of the offering definition
	* @return the offering bundles associated with the offering definition
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getOfferingBundles(pk);
	}

	/**
	* Returns a range of all the offering bundles associated with the offering definition.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the offering definition
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @return the range of offering bundles associated with the offering definition
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getOfferingBundles(pk, start, end);
	}

	/**
	* Returns an ordered range of all the offering bundles associated with the offering definition.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param pk the primary key of the offering definition
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of offering bundles associated with the offering definition
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .getOfferingBundles(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of offering bundles associated with the offering definition.
	*
	* @param pk the primary key of the offering definition
	* @return the number of offering bundles associated with the offering definition
	* @throws SystemException if a system exception occurred
	*/
	public static int getOfferingBundlesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getOfferingBundlesSize(pk);
	}

	/**
	* Returns <code>true</code> if the offering bundle is associated with the offering definition.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePK the primary key of the offering bundle
	* @return <code>true</code> if the offering bundle is associated with the offering definition; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsOfferingBundle(long pk, long offeringBundlePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsOfferingBundle(pk, offeringBundlePK);
	}

	/**
	* Returns <code>true</code> if the offering definition has any offering bundles associated with it.
	*
	* @param pk the primary key of the offering definition to check for associations with offering bundles
	* @return <code>true</code> if the offering definition has any offering bundles associated with it; <code>false</code> otherwise
	* @throws SystemException if a system exception occurred
	*/
	public static boolean containsOfferingBundles(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsOfferingBundles(pk);
	}

	/**
	* Adds an association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePK the primary key of the offering bundle
	* @throws SystemException if a system exception occurred
	*/
	public static void addOfferingBundle(long pk, long offeringBundlePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addOfferingBundle(pk, offeringBundlePK);
	}

	/**
	* Adds an association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundle the offering bundle
	* @throws SystemException if a system exception occurred
	*/
	public static void addOfferingBundle(long pk,
		com.liferay.osb.model.OfferingBundle offeringBundle)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addOfferingBundle(pk, offeringBundle);
	}

	/**
	* Adds an association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePKs the primary keys of the offering bundles
	* @throws SystemException if a system exception occurred
	*/
	public static void addOfferingBundles(long pk, long[] offeringBundlePKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addOfferingBundles(pk, offeringBundlePKs);
	}

	/**
	* Adds an association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundles the offering bundles
	* @throws SystemException if a system exception occurred
	*/
	public static void addOfferingBundles(long pk,
		java.util.List<com.liferay.osb.model.OfferingBundle> offeringBundles)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().addOfferingBundles(pk, offeringBundles);
	}

	/**
	* Clears all associations between the offering definition and its offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition to clear the associated offering bundles from
	* @throws SystemException if a system exception occurred
	*/
	public static void clearOfferingBundles(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().clearOfferingBundles(pk);
	}

	/**
	* Removes the association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePK the primary key of the offering bundle
	* @throws SystemException if a system exception occurred
	*/
	public static void removeOfferingBundle(long pk, long offeringBundlePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeOfferingBundle(pk, offeringBundlePK);
	}

	/**
	* Removes the association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundle the offering bundle
	* @throws SystemException if a system exception occurred
	*/
	public static void removeOfferingBundle(long pk,
		com.liferay.osb.model.OfferingBundle offeringBundle)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeOfferingBundle(pk, offeringBundle);
	}

	/**
	* Removes the association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePKs the primary keys of the offering bundles
	* @throws SystemException if a system exception occurred
	*/
	public static void removeOfferingBundles(long pk, long[] offeringBundlePKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeOfferingBundles(pk, offeringBundlePKs);
	}

	/**
	* Removes the association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundles the offering bundles
	* @throws SystemException if a system exception occurred
	*/
	public static void removeOfferingBundles(long pk,
		java.util.List<com.liferay.osb.model.OfferingBundle> offeringBundles)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeOfferingBundles(pk, offeringBundles);
	}

	/**
	* Sets the offering bundles associated with the offering definition, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePKs the primary keys of the offering bundles to be associated with the offering definition
	* @throws SystemException if a system exception occurred
	*/
	public static void setOfferingBundles(long pk, long[] offeringBundlePKs)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setOfferingBundles(pk, offeringBundlePKs);
	}

	/**
	* Sets the offering bundles associated with the offering definition, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundles the offering bundles to be associated with the offering definition
	* @throws SystemException if a system exception occurred
	*/
	public static void setOfferingBundles(long pk,
		java.util.List<com.liferay.osb.model.OfferingBundle> offeringBundles)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().setOfferingBundles(pk, offeringBundles);
	}

	public static OfferingDefinitionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (OfferingDefinitionPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					OfferingDefinitionPersistence.class.getName());

			ReferenceRegistry.registerReference(OfferingDefinitionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(OfferingDefinitionPersistence persistence) {
	}

	private static OfferingDefinitionPersistence _persistence;
}