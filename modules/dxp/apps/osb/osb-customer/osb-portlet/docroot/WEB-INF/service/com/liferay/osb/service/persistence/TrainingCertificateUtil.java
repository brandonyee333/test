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

import com.liferay.osb.model.TrainingCertificate;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the training certificate service. This utility wraps {@link TrainingCertificatePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingCertificatePersistence
 * @see TrainingCertificatePersistenceImpl
 * @generated
 */
public class TrainingCertificateUtil {
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
	public static void clearCache(TrainingCertificate trainingCertificate) {
		getPersistence().clearCache(trainingCertificate);
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
	public static List<TrainingCertificate> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<TrainingCertificate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<TrainingCertificate> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static TrainingCertificate update(
		TrainingCertificate trainingCertificate, boolean merge)
		throws SystemException {
		return getPersistence().update(trainingCertificate, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static TrainingCertificate update(
		TrainingCertificate trainingCertificate, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(trainingCertificate, merge, serviceContext);
	}

	/**
	* Caches the training certificate in the entity cache if it is enabled.
	*
	* @param trainingCertificate the training certificate
	*/
	public static void cacheResult(
		com.liferay.osb.model.TrainingCertificate trainingCertificate) {
		getPersistence().cacheResult(trainingCertificate);
	}

	/**
	* Caches the training certificates in the entity cache if it is enabled.
	*
	* @param trainingCertificates the training certificates
	*/
	public static void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingCertificate> trainingCertificates) {
		getPersistence().cacheResult(trainingCertificates);
	}

	/**
	* Creates a new training certificate with the primary key. Does not add the training certificate to the database.
	*
	* @param trainingCertificateId the primary key for the new training certificate
	* @return the new training certificate
	*/
	public static com.liferay.osb.model.TrainingCertificate create(
		long trainingCertificateId) {
		return getPersistence().create(trainingCertificateId);
	}

	/**
	* Removes the training certificate with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificateId the primary key of the training certificate
	* @return the training certificate that was removed
	* @throws com.liferay.osb.NoSuchTrainingCertificateException if a training certificate with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate remove(
		long trainingCertificateId)
		throws com.liferay.osb.NoSuchTrainingCertificateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(trainingCertificateId);
	}

	public static com.liferay.osb.model.TrainingCertificate updateImpl(
		com.liferay.osb.model.TrainingCertificate trainingCertificate,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(trainingCertificate, merge);
	}

	/**
	* Returns the training certificate with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingCertificateException} if it could not be found.
	*
	* @param trainingCertificateId the primary key of the training certificate
	* @return the training certificate
	* @throws com.liferay.osb.NoSuchTrainingCertificateException if a training certificate with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate findByPrimaryKey(
		long trainingCertificateId)
		throws com.liferay.osb.NoSuchTrainingCertificateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(trainingCertificateId);
	}

	/**
	* Returns the training certificate with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingCertificateId the primary key of the training certificate
	* @return the training certificate, or <code>null</code> if a training certificate with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate fetchByPrimaryKey(
		long trainingCertificateId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(trainingCertificateId);
	}

	/**
	* Returns the training certificate where trainingCustomerId = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingCertificateException} if it could not be found.
	*
	* @param trainingCustomerId the training customer ID
	* @return the matching training certificate
	* @throws com.liferay.osb.NoSuchTrainingCertificateException if a matching training certificate could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate findByTrainingCustomerId(
		long trainingCustomerId)
		throws com.liferay.osb.NoSuchTrainingCertificateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByTrainingCustomerId(trainingCustomerId);
	}

	/**
	* Returns the training certificate where trainingCustomerId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param trainingCustomerId the training customer ID
	* @return the matching training certificate, or <code>null</code> if a matching training certificate could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate fetchByTrainingCustomerId(
		long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByTrainingCustomerId(trainingCustomerId);
	}

	/**
	* Returns the training certificate where trainingCustomerId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param trainingCustomerId the training customer ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching training certificate, or <code>null</code> if a matching training certificate could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate fetchByTrainingCustomerId(
		long trainingCustomerId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByTrainingCustomerId(trainingCustomerId,
			retrieveFromCache);
	}

	/**
	* Returns the training certificate where key = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingCertificateException} if it could not be found.
	*
	* @param key the key
	* @return the matching training certificate
	* @throws com.liferay.osb.NoSuchTrainingCertificateException if a matching training certificate could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate findByKey(
		java.lang.String key)
		throws com.liferay.osb.NoSuchTrainingCertificateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKey(key);
	}

	/**
	* Returns the training certificate where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching training certificate, or <code>null</code> if a matching training certificate could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate fetchByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKey(key);
	}

	/**
	* Returns the training certificate where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching training certificate, or <code>null</code> if a matching training certificate could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate fetchByKey(
		java.lang.String key, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKey(key, retrieveFromCache);
	}

	/**
	* Returns all the training certificates.
	*
	* @return the training certificates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCertificate> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the training certificates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training certificates
	* @param end the upper bound of the range of training certificates (not inclusive)
	* @return the range of training certificates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCertificate> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the training certificates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of training certificates
	* @param end the upper bound of the range of training certificates (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of training certificates
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.osb.model.TrainingCertificate> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes the training certificate where trainingCustomerId = &#63; from the database.
	*
	* @param trainingCustomerId the training customer ID
	* @return the training certificate that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate removeByTrainingCustomerId(
		long trainingCustomerId)
		throws com.liferay.osb.NoSuchTrainingCertificateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByTrainingCustomerId(trainingCustomerId);
	}

	/**
	* Removes the training certificate where key = &#63; from the database.
	*
	* @param key the key
	* @return the training certificate that was removed
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.osb.model.TrainingCertificate removeByKey(
		java.lang.String key)
		throws com.liferay.osb.NoSuchTrainingCertificateException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().removeByKey(key);
	}

	/**
	* Removes all the training certificates from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of training certificates where trainingCustomerId = &#63;.
	*
	* @param trainingCustomerId the training customer ID
	* @return the number of matching training certificates
	* @throws SystemException if a system exception occurred
	*/
	public static int countByTrainingCustomerId(long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByTrainingCustomerId(trainingCustomerId);
	}

	/**
	* Returns the number of training certificates where key = &#63;.
	*
	* @param key the key
	* @return the number of matching training certificates
	* @throws SystemException if a system exception occurred
	*/
	public static int countByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKey(key);
	}

	/**
	* Returns the number of training certificates.
	*
	* @return the number of training certificates
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static TrainingCertificatePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (TrainingCertificatePersistence)PortletBeanLocatorUtil.locate(com.liferay.osb.service.ClpSerializer.getServletContextName(),
					TrainingCertificatePersistence.class.getName());

			ReferenceRegistry.registerReference(TrainingCertificateUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(TrainingCertificatePersistence persistence) {
	}

	private static TrainingCertificatePersistence _persistence;
}