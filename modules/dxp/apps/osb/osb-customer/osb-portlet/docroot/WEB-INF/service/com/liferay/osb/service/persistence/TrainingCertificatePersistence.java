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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the training certificate service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrainingCertificatePersistenceImpl
 * @see TrainingCertificateUtil
 * @generated
 */
public interface TrainingCertificatePersistence extends BasePersistence<TrainingCertificate> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link TrainingCertificateUtil} to access the training certificate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the training certificate in the entity cache if it is enabled.
	*
	* @param trainingCertificate the training certificate
	*/
	public void cacheResult(
		com.liferay.osb.model.TrainingCertificate trainingCertificate);

	/**
	* Caches the training certificates in the entity cache if it is enabled.
	*
	* @param trainingCertificates the training certificates
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.TrainingCertificate> trainingCertificates);

	/**
	* Creates a new training certificate with the primary key. Does not add the training certificate to the database.
	*
	* @param trainingCertificateId the primary key for the new training certificate
	* @return the new training certificate
	*/
	public com.liferay.osb.model.TrainingCertificate create(
		long trainingCertificateId);

	/**
	* Removes the training certificate with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param trainingCertificateId the primary key of the training certificate
	* @return the training certificate that was removed
	* @throws com.liferay.osb.NoSuchTrainingCertificateException if a training certificate with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate remove(
		long trainingCertificateId)
		throws com.liferay.osb.NoSuchTrainingCertificateException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.TrainingCertificate updateImpl(
		com.liferay.osb.model.TrainingCertificate trainingCertificate,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training certificate with the primary key or throws a {@link com.liferay.osb.NoSuchTrainingCertificateException} if it could not be found.
	*
	* @param trainingCertificateId the primary key of the training certificate
	* @return the training certificate
	* @throws com.liferay.osb.NoSuchTrainingCertificateException if a training certificate with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate findByPrimaryKey(
		long trainingCertificateId)
		throws com.liferay.osb.NoSuchTrainingCertificateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training certificate with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param trainingCertificateId the primary key of the training certificate
	* @return the training certificate, or <code>null</code> if a training certificate with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate fetchByPrimaryKey(
		long trainingCertificateId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training certificate where trainingCustomerId = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingCertificateException} if it could not be found.
	*
	* @param trainingCustomerId the training customer ID
	* @return the matching training certificate
	* @throws com.liferay.osb.NoSuchTrainingCertificateException if a matching training certificate could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate findByTrainingCustomerId(
		long trainingCustomerId)
		throws com.liferay.osb.NoSuchTrainingCertificateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training certificate where trainingCustomerId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param trainingCustomerId the training customer ID
	* @return the matching training certificate, or <code>null</code> if a matching training certificate could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate fetchByTrainingCustomerId(
		long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training certificate where trainingCustomerId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param trainingCustomerId the training customer ID
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching training certificate, or <code>null</code> if a matching training certificate could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate fetchByTrainingCustomerId(
		long trainingCustomerId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training certificate where key = &#63; or throws a {@link com.liferay.osb.NoSuchTrainingCertificateException} if it could not be found.
	*
	* @param key the key
	* @return the matching training certificate
	* @throws com.liferay.osb.NoSuchTrainingCertificateException if a matching training certificate could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate findByKey(
		java.lang.String key)
		throws com.liferay.osb.NoSuchTrainingCertificateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training certificate where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching training certificate, or <code>null</code> if a matching training certificate could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate fetchByKey(
		java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the training certificate where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching training certificate, or <code>null</code> if a matching training certificate could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate fetchByKey(
		java.lang.String key, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the training certificates.
	*
	* @return the training certificates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.TrainingCertificate> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.TrainingCertificate> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.osb.model.TrainingCertificate> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the training certificate where trainingCustomerId = &#63; from the database.
	*
	* @param trainingCustomerId the training customer ID
	* @return the training certificate that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate removeByTrainingCustomerId(
		long trainingCustomerId)
		throws com.liferay.osb.NoSuchTrainingCertificateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the training certificate where key = &#63; from the database.
	*
	* @param key the key
	* @return the training certificate that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.TrainingCertificate removeByKey(
		java.lang.String key)
		throws com.liferay.osb.NoSuchTrainingCertificateException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the training certificates from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training certificates where trainingCustomerId = &#63;.
	*
	* @param trainingCustomerId the training customer ID
	* @return the number of matching training certificates
	* @throws SystemException if a system exception occurred
	*/
	public int countByTrainingCustomerId(long trainingCustomerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training certificates where key = &#63;.
	*
	* @param key the key
	* @return the number of matching training certificates
	* @throws SystemException if a system exception occurred
	*/
	public int countByKey(java.lang.String key)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of training certificates.
	*
	* @return the number of training certificates
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}