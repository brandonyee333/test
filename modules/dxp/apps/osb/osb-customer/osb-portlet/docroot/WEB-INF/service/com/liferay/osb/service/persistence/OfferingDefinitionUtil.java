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

import com.liferay.osb.model.OfferingDefinition;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import java.util.List;

/**
 * The persistence utility for the offering definition service. This utility wraps {@link com.liferay.osb.service.persistence.impl.OfferingDefinitionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OfferingDefinitionPersistence
 * @see com.liferay.osb.service.persistence.impl.OfferingDefinitionPersistenceImpl
 * @generated
 */
@ProviderType
public class OfferingDefinitionUtil {
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
	public static void clearCache(OfferingDefinition offeringDefinition) {
		getPersistence().clearCache(offeringDefinition);
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
	public static List<OfferingDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<OfferingDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<OfferingDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static OfferingDefinition update(
		OfferingDefinition offeringDefinition) {
		return getPersistence().update(offeringDefinition);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static OfferingDefinition update(
		OfferingDefinition offeringDefinition, ServiceContext serviceContext) {
		return getPersistence().update(offeringDefinition, serviceContext);
	}

	/**
	* Returns all the offering definitions where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @return the matching offering definitions
	*/
	public static List<OfferingDefinition> findByProductEntryId(
		long productEntryId) {
		return getPersistence().findByProductEntryId(productEntryId);
	}

	/**
	* Returns a range of all the offering definitions where productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @return the range of matching offering definitions
	*/
	public static List<OfferingDefinition> findByProductEntryId(
		long productEntryId, int start, int end) {
		return getPersistence().findByProductEntryId(productEntryId, start, end);
	}

	/**
	* Returns an ordered range of all the offering definitions where productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering definitions
	*/
	public static List<OfferingDefinition> findByProductEntryId(
		long productEntryId, int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		return getPersistence()
				   .findByProductEntryId(productEntryId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the offering definitions where productEntryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching offering definitions
	*/
	public static List<OfferingDefinition> findByProductEntryId(
		long productEntryId, int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByProductEntryId(productEntryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first offering definition in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering definition
	* @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	*/
	public static OfferingDefinition findByProductEntryId_First(
		long productEntryId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingDefinitionException {
		return getPersistence()
				   .findByProductEntryId_First(productEntryId, orderByComparator);
	}

	/**
	* Returns the first offering definition in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering definition, or <code>null</code> if a matching offering definition could not be found
	*/
	public static OfferingDefinition fetchByProductEntryId_First(
		long productEntryId,
		OrderByComparator<OfferingDefinition> orderByComparator) {
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
	* @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	*/
	public static OfferingDefinition findByProductEntryId_Last(
		long productEntryId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingDefinitionException {
		return getPersistence()
				   .findByProductEntryId_Last(productEntryId, orderByComparator);
	}

	/**
	* Returns the last offering definition in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering definition, or <code>null</code> if a matching offering definition could not be found
	*/
	public static OfferingDefinition fetchByProductEntryId_Last(
		long productEntryId,
		OrderByComparator<OfferingDefinition> orderByComparator) {
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
	* @throws NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	*/
	public static OfferingDefinition[] findByProductEntryId_PrevAndNext(
		long offeringDefinitionId, long productEntryId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingDefinitionException {
		return getPersistence()
				   .findByProductEntryId_PrevAndNext(offeringDefinitionId,
			productEntryId, orderByComparator);
	}

	/**
	* Removes all the offering definitions where productEntryId = &#63; from the database.
	*
	* @param productEntryId the product entry ID
	*/
	public static void removeByProductEntryId(long productEntryId) {
		getPersistence().removeByProductEntryId(productEntryId);
	}

	/**
	* Returns the number of offering definitions where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @return the number of matching offering definitions
	*/
	public static int countByProductEntryId(long productEntryId) {
		return getPersistence().countByProductEntryId(productEntryId);
	}

	/**
	* Returns all the offering definitions where supportResponseId = &#63;.
	*
	* @param supportResponseId the support response ID
	* @return the matching offering definitions
	*/
	public static List<OfferingDefinition> findBySupportResponseId(
		long supportResponseId) {
		return getPersistence().findBySupportResponseId(supportResponseId);
	}

	/**
	* Returns a range of all the offering definitions where supportResponseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportResponseId the support response ID
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @return the range of matching offering definitions
	*/
	public static List<OfferingDefinition> findBySupportResponseId(
		long supportResponseId, int start, int end) {
		return getPersistence()
				   .findBySupportResponseId(supportResponseId, start, end);
	}

	/**
	* Returns an ordered range of all the offering definitions where supportResponseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportResponseId the support response ID
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering definitions
	*/
	public static List<OfferingDefinition> findBySupportResponseId(
		long supportResponseId, int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		return getPersistence()
				   .findBySupportResponseId(supportResponseId, start, end,
			orderByComparator);
	}

	/**
	* Returns an ordered range of all the offering definitions where supportResponseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param supportResponseId the support response ID
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching offering definitions
	*/
	public static List<OfferingDefinition> findBySupportResponseId(
		long supportResponseId, int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySupportResponseId(supportResponseId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first offering definition in the ordered set where supportResponseId = &#63;.
	*
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering definition
	* @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	*/
	public static OfferingDefinition findBySupportResponseId_First(
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingDefinitionException {
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
	*/
	public static OfferingDefinition fetchBySupportResponseId_First(
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator) {
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
	* @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	*/
	public static OfferingDefinition findBySupportResponseId_Last(
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingDefinitionException {
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
	*/
	public static OfferingDefinition fetchBySupportResponseId_Last(
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator) {
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
	* @throws NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	*/
	public static OfferingDefinition[] findBySupportResponseId_PrevAndNext(
		long offeringDefinitionId, long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingDefinitionException {
		return getPersistence()
				   .findBySupportResponseId_PrevAndNext(offeringDefinitionId,
			supportResponseId, orderByComparator);
	}

	/**
	* Removes all the offering definitions where supportResponseId = &#63; from the database.
	*
	* @param supportResponseId the support response ID
	*/
	public static void removeBySupportResponseId(long supportResponseId) {
		getPersistence().removeBySupportResponseId(supportResponseId);
	}

	/**
	* Returns the number of offering definitions where supportResponseId = &#63;.
	*
	* @param supportResponseId the support response ID
	* @return the number of matching offering definitions
	*/
	public static int countBySupportResponseId(long supportResponseId) {
		return getPersistence().countBySupportResponseId(supportResponseId);
	}

	/**
	* Returns all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @return the matching offering definitions
	*/
	public static List<OfferingDefinition> findByPEI_SRI(long productEntryId,
		long supportResponseId) {
		return getPersistence().findByPEI_SRI(productEntryId, supportResponseId);
	}

	/**
	* Returns a range of all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @return the range of matching offering definitions
	*/
	public static List<OfferingDefinition> findByPEI_SRI(long productEntryId,
		long supportResponseId, int start, int end) {
		return getPersistence()
				   .findByPEI_SRI(productEntryId, supportResponseId, start, end);
	}

	/**
	* Returns an ordered range of all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering definitions
	*/
	public static List<OfferingDefinition> findByPEI_SRI(long productEntryId,
		long supportResponseId, int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		return getPersistence()
				   .findByPEI_SRI(productEntryId, supportResponseId, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching offering definitions
	*/
	public static List<OfferingDefinition> findByPEI_SRI(long productEntryId,
		long supportResponseId, int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPEI_SRI(productEntryId, supportResponseId, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering definition
	* @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	*/
	public static OfferingDefinition findByPEI_SRI_First(long productEntryId,
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingDefinitionException {
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
	*/
	public static OfferingDefinition fetchByPEI_SRI_First(long productEntryId,
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator) {
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
	* @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	*/
	public static OfferingDefinition findByPEI_SRI_Last(long productEntryId,
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingDefinitionException {
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
	*/
	public static OfferingDefinition fetchByPEI_SRI_Last(long productEntryId,
		long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator) {
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
	* @throws NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	*/
	public static OfferingDefinition[] findByPEI_SRI_PrevAndNext(
		long offeringDefinitionId, long productEntryId, long supportResponseId,
		OrderByComparator<OfferingDefinition> orderByComparator)
		throws com.liferay.osb.exception.NoSuchOfferingDefinitionException {
		return getPersistence()
				   .findByPEI_SRI_PrevAndNext(offeringDefinitionId,
			productEntryId, supportResponseId, orderByComparator);
	}

	/**
	* Returns all the offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryIds the product entry IDs
	* @param supportResponseIds the support response IDs
	* @return the matching offering definitions
	*/
	public static List<OfferingDefinition> findByPEI_SRI(
		long[] productEntryIds, long[] supportResponseIds) {
		return getPersistence()
				   .findByPEI_SRI(productEntryIds, supportResponseIds);
	}

	/**
	* Returns a range of all the offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryIds the product entry IDs
	* @param supportResponseIds the support response IDs
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @return the range of matching offering definitions
	*/
	public static List<OfferingDefinition> findByPEI_SRI(
		long[] productEntryIds, long[] supportResponseIds, int start, int end) {
		return getPersistence()
				   .findByPEI_SRI(productEntryIds, supportResponseIds, start,
			end);
	}

	/**
	* Returns an ordered range of all the offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryIds the product entry IDs
	* @param supportResponseIds the support response IDs
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching offering definitions
	*/
	public static List<OfferingDefinition> findByPEI_SRI(
		long[] productEntryIds, long[] supportResponseIds, int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		return getPersistence()
				   .findByPEI_SRI(productEntryIds, supportResponseIds, start,
			end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;, optionally using the finder cache.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching offering definitions
	*/
	public static List<OfferingDefinition> findByPEI_SRI(
		long[] productEntryIds, long[] supportResponseIds, int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByPEI_SRI(productEntryIds, supportResponseIds, start,
			end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the offering definitions where productEntryId = &#63; and supportResponseId = &#63; from the database.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	*/
	public static void removeByPEI_SRI(long productEntryId,
		long supportResponseId) {
		getPersistence().removeByPEI_SRI(productEntryId, supportResponseId);
	}

	/**
	* Returns the number of offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @return the number of matching offering definitions
	*/
	public static int countByPEI_SRI(long productEntryId, long supportResponseId) {
		return getPersistence().countByPEI_SRI(productEntryId, supportResponseId);
	}

	/**
	* Returns the number of offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	*
	* @param productEntryIds the product entry IDs
	* @param supportResponseIds the support response IDs
	* @return the number of matching offering definitions
	*/
	public static int countByPEI_SRI(long[] productEntryIds,
		long[] supportResponseIds) {
		return getPersistence()
				   .countByPEI_SRI(productEntryIds, supportResponseIds);
	}

	/**
	* Returns the offering definition where productEntryId = &#63; and supportResponseId = &#63; and productDescription = &#63; and licenses = &#63; and unlimitedLicenses = &#63; and supportTickets = &#63; or throws a {@link NoSuchOfferingDefinitionException} if it could not be found.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param productDescription the product description
	* @param licenses the licenses
	* @param unlimitedLicenses the unlimited licenses
	* @param supportTickets the support tickets
	* @return the matching offering definition
	* @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	*/
	public static OfferingDefinition findByPEI_SRI_PD_L_UL_ST(
		long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets)
		throws com.liferay.osb.exception.NoSuchOfferingDefinitionException {
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
	*/
	public static OfferingDefinition fetchByPEI_SRI_PD_L_UL_ST(
		long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets) {
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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching offering definition, or <code>null</code> if a matching offering definition could not be found
	*/
	public static OfferingDefinition fetchByPEI_SRI_PD_L_UL_ST(
		long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets,
		boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByPEI_SRI_PD_L_UL_ST(productEntryId,
			supportResponseId, productDescription, licenses, unlimitedLicenses,
			supportTickets, retrieveFromCache);
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
	*/
	public static OfferingDefinition removeByPEI_SRI_PD_L_UL_ST(
		long productEntryId, long supportResponseId,
		java.lang.String productDescription, boolean licenses,
		boolean unlimitedLicenses, boolean supportTickets)
		throws com.liferay.osb.exception.NoSuchOfferingDefinitionException {
		return getPersistence()
				   .removeByPEI_SRI_PD_L_UL_ST(productEntryId,
			supportResponseId, productDescription, licenses, unlimitedLicenses,
			supportTickets);
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
	*/
	public static int countByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, java.lang.String productDescription,
		boolean licenses, boolean unlimitedLicenses, boolean supportTickets) {
		return getPersistence()
				   .countByPEI_SRI_PD_L_UL_ST(productEntryId,
			supportResponseId, productDescription, licenses, unlimitedLicenses,
			supportTickets);
	}

	/**
	* Caches the offering definition in the entity cache if it is enabled.
	*
	* @param offeringDefinition the offering definition
	*/
	public static void cacheResult(OfferingDefinition offeringDefinition) {
		getPersistence().cacheResult(offeringDefinition);
	}

	/**
	* Caches the offering definitions in the entity cache if it is enabled.
	*
	* @param offeringDefinitions the offering definitions
	*/
	public static void cacheResult(List<OfferingDefinition> offeringDefinitions) {
		getPersistence().cacheResult(offeringDefinitions);
	}

	/**
	* Creates a new offering definition with the primary key. Does not add the offering definition to the database.
	*
	* @param offeringDefinitionId the primary key for the new offering definition
	* @return the new offering definition
	*/
	public static OfferingDefinition create(long offeringDefinitionId) {
		return getPersistence().create(offeringDefinitionId);
	}

	/**
	* Removes the offering definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition that was removed
	* @throws NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	*/
	public static OfferingDefinition remove(long offeringDefinitionId)
		throws com.liferay.osb.exception.NoSuchOfferingDefinitionException {
		return getPersistence().remove(offeringDefinitionId);
	}

	public static OfferingDefinition updateImpl(
		OfferingDefinition offeringDefinition) {
		return getPersistence().updateImpl(offeringDefinition);
	}

	/**
	* Returns the offering definition with the primary key or throws a {@link NoSuchOfferingDefinitionException} if it could not be found.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition
	* @throws NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	*/
	public static OfferingDefinition findByPrimaryKey(long offeringDefinitionId)
		throws com.liferay.osb.exception.NoSuchOfferingDefinitionException {
		return getPersistence().findByPrimaryKey(offeringDefinitionId);
	}

	/**
	* Returns the offering definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition, or <code>null</code> if a offering definition with the primary key could not be found
	*/
	public static OfferingDefinition fetchByPrimaryKey(
		long offeringDefinitionId) {
		return getPersistence().fetchByPrimaryKey(offeringDefinitionId);
	}

	public static java.util.Map<java.io.Serializable, OfferingDefinition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the offering definitions.
	*
	* @return the offering definitions
	*/
	public static List<OfferingDefinition> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the offering definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @return the range of offering definitions
	*/
	public static List<OfferingDefinition> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the offering definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of offering definitions
	*/
	public static List<OfferingDefinition> findAll(int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the offering definitions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of offering definitions
	*/
	public static List<OfferingDefinition> findAll(int start, int end,
		OrderByComparator<OfferingDefinition> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the offering definitions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of offering definitions.
	*
	* @return the number of offering definitions
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	/**
	* Returns the primaryKeys of offering bundles associated with the offering definition.
	*
	* @param pk the primary key of the offering definition
	* @return long[] of the primaryKeys of offering bundles associated with the offering definition
	*/
	public static long[] getOfferingBundlePrimaryKeys(long pk) {
		return getPersistence().getOfferingBundlePrimaryKeys(pk);
	}

	/**
	* Returns all the offering bundles associated with the offering definition.
	*
	* @param pk the primary key of the offering definition
	* @return the offering bundles associated with the offering definition
	*/
	public static List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		long pk) {
		return getPersistence().getOfferingBundles(pk);
	}

	/**
	* Returns a range of all the offering bundles associated with the offering definition.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the offering definition
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @return the range of offering bundles associated with the offering definition
	*/
	public static List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		long pk, int start, int end) {
		return getPersistence().getOfferingBundles(pk, start, end);
	}

	/**
	* Returns an ordered range of all the offering bundles associated with the offering definition.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link OfferingDefinitionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the offering definition
	* @param start the lower bound of the range of offering definitions
	* @param end the upper bound of the range of offering definitions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of offering bundles associated with the offering definition
	*/
	public static List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		long pk, int start, int end,
		OrderByComparator<com.liferay.osb.model.OfferingBundle> orderByComparator) {
		return getPersistence()
				   .getOfferingBundles(pk, start, end, orderByComparator);
	}

	/**
	* Returns the number of offering bundles associated with the offering definition.
	*
	* @param pk the primary key of the offering definition
	* @return the number of offering bundles associated with the offering definition
	*/
	public static int getOfferingBundlesSize(long pk) {
		return getPersistence().getOfferingBundlesSize(pk);
	}

	/**
	* Returns <code>true</code> if the offering bundle is associated with the offering definition.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePK the primary key of the offering bundle
	* @return <code>true</code> if the offering bundle is associated with the offering definition; <code>false</code> otherwise
	*/
	public static boolean containsOfferingBundle(long pk, long offeringBundlePK) {
		return getPersistence().containsOfferingBundle(pk, offeringBundlePK);
	}

	/**
	* Returns <code>true</code> if the offering definition has any offering bundles associated with it.
	*
	* @param pk the primary key of the offering definition to check for associations with offering bundles
	* @return <code>true</code> if the offering definition has any offering bundles associated with it; <code>false</code> otherwise
	*/
	public static boolean containsOfferingBundles(long pk) {
		return getPersistence().containsOfferingBundles(pk);
	}

	/**
	* Adds an association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePK the primary key of the offering bundle
	*/
	public static void addOfferingBundle(long pk, long offeringBundlePK) {
		getPersistence().addOfferingBundle(pk, offeringBundlePK);
	}

	/**
	* Adds an association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundle the offering bundle
	*/
	public static void addOfferingBundle(long pk,
		com.liferay.osb.model.OfferingBundle offeringBundle) {
		getPersistence().addOfferingBundle(pk, offeringBundle);
	}

	/**
	* Adds an association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePKs the primary keys of the offering bundles
	*/
	public static void addOfferingBundles(long pk, long[] offeringBundlePKs) {
		getPersistence().addOfferingBundles(pk, offeringBundlePKs);
	}

	/**
	* Adds an association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundles the offering bundles
	*/
	public static void addOfferingBundles(long pk,
		List<com.liferay.osb.model.OfferingBundle> offeringBundles) {
		getPersistence().addOfferingBundles(pk, offeringBundles);
	}

	/**
	* Clears all associations between the offering definition and its offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition to clear the associated offering bundles from
	*/
	public static void clearOfferingBundles(long pk) {
		getPersistence().clearOfferingBundles(pk);
	}

	/**
	* Removes the association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePK the primary key of the offering bundle
	*/
	public static void removeOfferingBundle(long pk, long offeringBundlePK) {
		getPersistence().removeOfferingBundle(pk, offeringBundlePK);
	}

	/**
	* Removes the association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundle the offering bundle
	*/
	public static void removeOfferingBundle(long pk,
		com.liferay.osb.model.OfferingBundle offeringBundle) {
		getPersistence().removeOfferingBundle(pk, offeringBundle);
	}

	/**
	* Removes the association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePKs the primary keys of the offering bundles
	*/
	public static void removeOfferingBundles(long pk, long[] offeringBundlePKs) {
		getPersistence().removeOfferingBundles(pk, offeringBundlePKs);
	}

	/**
	* Removes the association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundles the offering bundles
	*/
	public static void removeOfferingBundles(long pk,
		List<com.liferay.osb.model.OfferingBundle> offeringBundles) {
		getPersistence().removeOfferingBundles(pk, offeringBundles);
	}

	/**
	* Sets the offering bundles associated with the offering definition, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePKs the primary keys of the offering bundles to be associated with the offering definition
	*/
	public static void setOfferingBundles(long pk, long[] offeringBundlePKs) {
		getPersistence().setOfferingBundles(pk, offeringBundlePKs);
	}

	/**
	* Sets the offering bundles associated with the offering definition, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundles the offering bundles to be associated with the offering definition
	*/
	public static void setOfferingBundles(long pk,
		List<com.liferay.osb.model.OfferingBundle> offeringBundles) {
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

	private static OfferingDefinitionPersistence _persistence;
}