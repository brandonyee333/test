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

import com.liferay.osb.model.TrainingCustomer;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the training customer service. This utility wraps {@link TrainingCustomerPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingCustomerPersistence
 * @see TrainingCustomerPersistenceImpl
 * @generated
 */
public class TrainingCustomerUtil {
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
	public static void clearCache(TrainingCustomer trainingCustomer) {
		getPersistence().clearCache(trainingCustomer);
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
	public static List<TrainingCustomer> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TrainingCustomer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TrainingCustomer> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TrainingCustomer update(TrainingCustomer trainingCustomer,
		boolean merge) throws SystemException {
		return getPersistence().update(trainingCustomer, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TrainingCustomer update(TrainingCustomer trainingCustomer,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(trainingCustomer, merge, serviceContext);
	}

	/**
	* Caches the training customer in the entity cache if it is enabled.
	*
	* @param trainingCustomer the training customer
	*/
	public static void cacheResult(
		com.liferay.osb.model.TrainingCustomer trainingCustomer) {
		getPersistence().cacheResult(trainingCustomer);
	}

	/**
	* Caches the training customers in the entity cache if it is enabled.
	*
	* @param trainingCustomers the training customers
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingCustomer> trainingCustomers) {
		getPersistence().cacheResult(trainingCustomers);
	}

	/**
	* Creates a new training customer with the primary key. Does not add the training customer to the database.
	*
	* @param trainingCustomerId the primary key for the new training customer
	* @return the new training customer
	*/
	public static com.liferay.osb.model.TrainingCustomer create(
		long trainingCustomerId) {
		return getPersistence().create(trainingCustomerId);
	}

	/**
	* Removes the training customer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCustomerId the primary key of the training customer
	* @return the training customer that was removed
	* @throws com.liferay.osb.NoSuchTrainingCustomerException if a training customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer remove(
		long trainingCustomerId)
		throws com.liferay.osb.NoSuchTrainingCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(trainingCustomerId);
	}

	public static com.liferay.osb.model.TrainingCustomer updateImpl(
		com.liferay.osb.model.TrainingCustomer trainingCustomer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(trainingCustomer, merge);
	}

	/**
	* Returns the training customer with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingCustomerException} if it could not be found.
	*
	* @param trainingCustomerId the primary key of the training customer
	* @return the training customer
	* @throws com.liferay.osb.NoSuchTrainingCustomerException if a training customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer findByPrimaryKey(
		long trainingCustomerId)
		throws com.liferay.osb.NoSuchTrainingCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(trainingCustomerId);
	}

	/**
	* Returns the training customer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingCustomerId the primary key of the training customer
	* @return the training customer, or <code>null</code> if a training customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer fetchByPrimaryKey(
		long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(trainingCustomerId);
	}

	/**
	* Returns all the training customers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the training customers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of training customers
	* @param end the upper bound of the range of training customers (not inclusive)
	* @return the range of matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the training customers where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of training customers
	* @param end the upper bound of the range of training customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first training customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training customer
	* @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first training customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training customer, or <code>null</code> if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last training customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training customer
	* @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last training customer in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training customer, or <code>null</code> if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the training customers before and after the current training customer in the ordered set where userId = &#63;.
	*
	* @param trainingCustomerId the primary key of the current training customer
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training customer
	* @throws com.liferay.osb.NoSuchTrainingCustomerException if a training customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer[] findByUserId_PrevAndNext(
		long trainingCustomerId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId_PrevAndNext(trainingCustomerId, userId,
			orderByComparator);
	}

	/**
	* Returns all the training customers where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @return the matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findByU_C(
		long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_C(userId, classNameId);
	}

	/**
	* Returns a range of all the training customers where userId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of training customers
	* @param end the upper bound of the range of training customers (not inclusive)
	* @return the range of matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findByU_C(
		long userId, long classNameId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_C(userId, classNameId, start, end);
	}

	/**
	* Returns an ordered range of all the training customers where userId = &#63; and classNameId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param start the lower bound of the range of training customers
	* @param end the upper bound of the range of training customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findByU_C(
		long userId, long classNameId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_C(userId, classNameId, start, end, orderByComparator);
	}

	/**
	* Returns the first training customer in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training customer
	* @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer findByU_C_First(
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_C_First(userId, classNameId, orderByComparator);
	}

	/**
	* Returns the first training customer in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training customer, or <code>null</code> if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer fetchByU_C_First(
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_C_First(userId, classNameId, orderByComparator);
	}

	/**
	* Returns the last training customer in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training customer
	* @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer findByU_C_Last(
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_C_Last(userId, classNameId, orderByComparator);
	}

	/**
	* Returns the last training customer in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training customer, or <code>null</code> if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer fetchByU_C_Last(
		long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_C_Last(userId, classNameId, orderByComparator);
	}

	/**
	* Returns the training customers before and after the current training customer in the ordered set where userId = &#63; and classNameId = &#63;.
	*
	* @param trainingCustomerId the primary key of the current training customer
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training customer
	* @throws com.liferay.osb.NoSuchTrainingCustomerException if a training customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer[] findByU_C_PrevAndNext(
		long trainingCustomerId, long userId, long classNameId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByU_C_PrevAndNext(trainingCustomerId, userId,
			classNameId, orderByComparator);
	}

	/**
	* Returns all the training customers where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK);
	}

	/**
	* Returns a range of all the training customers where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of training customers
	* @param end the upper bound of the range of training customers (not inclusive)
	* @return the range of matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C(classNameId, classPK, start, end);
	}

	/**
	* Returns an ordered range of all the training customers where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of training customers
	* @param end the upper bound of the range of training customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C(classNameId, classPK, start, end,
			orderByComparator);
	}

	/**
	* Returns the first training customer in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training customer
	* @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the first training customer in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training customer, or <code>null</code> if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_First(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last training customer in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training customer
	* @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the last training customer in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training customer, or <code>null</code> if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_Last(classNameId, classPK, orderByComparator);
	}

	/**
	* Returns the training customers before and after the current training customer in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param trainingCustomerId the primary key of the current training customer
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training customer
	* @throws com.liferay.osb.NoSuchTrainingCustomerException if a training customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer[] findByC_C_PrevAndNext(
		long trainingCustomerId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_PrevAndNext(trainingCustomerId, classNameId,
			classPK, orderByComparator);
	}

	/**
	* Returns the training customer where userId = &#63; and classNameId = &#63; and classPK = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingCustomerException} if it could not be found.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching training customer
	* @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer findByU_C_C(
		long userId, long classNameId, long classPK)
		throws com.liferay.osb.NoSuchTrainingCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByU_C_C(userId, classNameId, classPK);
	}

	/**
	* Returns the training customer where userId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching training customer, or <code>null</code> if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer fetchByU_C_C(
		long userId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByU_C_C(userId, classNameId, classPK);
	}

	/**
	* Returns the training customer where userId = &#63; and classNameId = &#63; and classPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching training customer, or <code>null</code> if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer fetchByU_C_C(
		long userId, long classNameId, long classPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByU_C_C(userId, classNameId, classPK, retrieveFromCache);
	}

	/**
	* Returns all the training customers where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @return the matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findByC_C_S(
		long classNameId, long classPK, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C_S(classNameId, classPK, status);
	}

	/**
	* Returns a range of all the training customers where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param start the lower bound of the range of training customers
	* @param end the upper bound of the range of training customers (not inclusive)
	* @return the range of matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findByC_C_S(
		long classNameId, long classPK, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_S(classNameId, classPK, status, start, end);
	}

	/**
	* Returns an ordered range of all the training customers where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param start the lower bound of the range of training customers
	* @param end the upper bound of the range of training customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findByC_C_S(
		long classNameId, long classPK, int status, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_S(classNameId, classPK, status, start, end,
			orderByComparator);
	}

	/**
	* Returns the first training customer in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training customer
	* @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer findByC_C_S_First(
		long classNameId, long classPK, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_S_First(classNameId, classPK, status,
			orderByComparator);
	}

	/**
	* Returns the first training customer in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching training customer, or <code>null</code> if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer fetchByC_C_S_First(
		long classNameId, long classPK, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_S_First(classNameId, classPK, status,
			orderByComparator);
	}

	/**
	* Returns the last training customer in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training customer
	* @throws com.liferay.osb.NoSuchTrainingCustomerException if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer findByC_C_S_Last(
		long classNameId, long classPK, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_S_Last(classNameId, classPK, status,
			orderByComparator);
	}

	/**
	* Returns the last training customer in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching training customer, or <code>null</code> if a matching training customer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer fetchByC_C_S_Last(
		long classNameId, long classPK, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_C_S_Last(classNameId, classPK, status,
			orderByComparator);
	}

	/**
	* Returns the training customers before and after the current training customer in the ordered set where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param trainingCustomerId the primary key of the current training customer
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next training customer
	* @throws com.liferay.osb.NoSuchTrainingCustomerException if a training customer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer[] findByC_C_S_PrevAndNext(
		long trainingCustomerId, long classNameId, long classPK, int status,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchTrainingCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_S_PrevAndNext(trainingCustomerId, classNameId,
			classPK, status, orderByComparator);
	}

	/**
	* Returns all the training customers where classNameId = &#63; and classPK = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param statuses the statuses
	* @return the matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findByC_C_S(
		long classNameId, long classPK, int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_C_S(classNameId, classPK, statuses);
	}

	/**
	* Returns a range of all the training customers where classNameId = &#63; and classPK = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param statuses the statuses
	* @param start the lower bound of the range of training customers
	* @param end the upper bound of the range of training customers (not inclusive)
	* @return the range of matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findByC_C_S(
		long classNameId, long classPK, int[] statuses, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_S(classNameId, classPK, statuses, start, end);
	}

	/**
	* Returns an ordered range of all the training customers where classNameId = &#63; and classPK = &#63; and status = any &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param statuses the statuses
	* @param start the lower bound of the range of training customers
	* @param end the upper bound of the range of training customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findByC_C_S(
		long classNameId, long classPK, int[] statuses, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_C_S(classNameId, classPK, statuses, start, end,
			orderByComparator);
	}

	/**
	* Returns all the training customers.
	*
	* @return the training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the training customers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training customers
	* @param end the upper bound of the range of training customers (not inclusive)
	* @return the range of training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the training customers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training customers
	* @param end the upper bound of the range of training customers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of training customers
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCustomer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the training customers where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Removes all the training customers where userId = &#63; and classNameId = &#63; from the database.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByU_C(long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByU_C(userId, classNameId);
	}

	/**
	* Removes all the training customers where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_C(classNameId, classPK);
	}

	/**
	* Removes the training customer where userId = &#63; and classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the training customer that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCustomer removeByU_C_C(
		long userId, long classNameId, long classPK)
		throws com.liferay.osb.NoSuchTrainingCustomerException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByU_C_C(userId, classNameId, classPK);
	}

	/**
	* Removes all the training customers where classNameId = &#63; and classPK = &#63; and status = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByC_C_S(long classNameId, long classPK, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_C_S(classNameId, classPK, status);
	}

	/**
	* Removes all the training customers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of training customers where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Returns the number of training customers where userId = &#63; and classNameId = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @return the number of matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_C(long userId, long classNameId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_C(userId, classNameId);
	}

	/**
	* Returns the number of training customers where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C(classNameId, classPK);
	}

	/**
	* Returns the number of training customers where userId = &#63; and classNameId = &#63; and classPK = &#63;.
	*
	* @param userId the user ID
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByU_C_C(long userId, long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByU_C_C(userId, classNameId, classPK);
	}

	/**
	* Returns the number of training customers where classNameId = &#63; and classPK = &#63; and status = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param status the status
	* @return the number of matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C_S(long classNameId, long classPK, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C_S(classNameId, classPK, status);
	}

	/**
	* Returns the number of training customers where classNameId = &#63; and classPK = &#63; and status = any &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param statuses the statuses
	* @return the number of matching training customers
	* @throws SystemException if a system exception occurred
	*/
	public static int countByC_C_S(long classNameId, long classPK,
		int[] statuses)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_C_S(classNameId, classPK, statuses);
	}

	/**
	* Returns the number of training customers.
	*
	* @return the number of training customers
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TrainingCustomerPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TrainingCustomerPersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TrainingCustomerPersistence.class.getName());

			ReferenceRegistry.registerReference(TrainingCustomerUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(TrainingCustomerPersistence persistence) {
	}

	private static TrainingCustomerPersistence _persistence;
}