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

import com.liferay.osb.model.ExternalIdMapper;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the external ID mapper service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ExternalIdMapperPersistenceImpl
 * @see ExternalIdMapperUtil
 * @generated
 */
public interface ExternalIdMapperPersistence extends BasePersistence<ExternalIdMapper> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ExternalIdMapperUtil} to access the external ID mapper persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the external ID mapper in the entity cache if it is enabled.
	*
	* @param externalIdMapper the external ID mapper
	*/
	public void cacheResult(
		com.liferay.osb.model.ExternalIdMapper externalIdMapper);

	/**
	* Caches the external ID mappers in the entity cache if it is enabled.
	*
	* @param externalIdMappers the external ID mappers
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.ExternalIdMapper> externalIdMappers);

	/**
	* Creates a new external ID mapper with the primary key. Does not add the external ID mapper to the database.
	*
	* @param externalIdMapperId the primary key for the new external ID mapper
	* @return the new external ID mapper
	*/
	public com.liferay.osb.model.ExternalIdMapper create(
		long externalIdMapperId);

	/**
	* Removes the external ID mapper with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param externalIdMapperId the primary key of the external ID mapper
	* @return the external ID mapper that was removed
	* @throws com.liferay.osb.NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper remove(
		long externalIdMapperId)
		throws com.liferay.osb.NoSuchExternalIdMapperException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.ExternalIdMapper updateImpl(
		com.liferay.osb.model.ExternalIdMapper externalIdMapper, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the external ID mapper with the primary key or throws a {@link com.liferay.osb.NoSuchExternalIdMapperException} if it could not be found.
	*
	* @param externalIdMapperId the primary key of the external ID mapper
	* @return the external ID mapper
	* @throws com.liferay.osb.NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper findByPrimaryKey(
		long externalIdMapperId)
		throws com.liferay.osb.NoSuchExternalIdMapperException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the external ID mapper with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param externalIdMapperId the primary key of the external ID mapper
	* @return the external ID mapper, or <code>null</code> if a external ID mapper with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper fetchByPrimaryKey(
		long externalIdMapperId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the matching external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ExternalIdMapper> findByC_C(
		long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @return the range of matching external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ExternalIdMapper> findByC_C(
		long classNameId, long classPK, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ExternalIdMapper> findByC_C(
		long classNameId, long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching external ID mapper
	* @throws com.liferay.osb.NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper findByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchExternalIdMapperException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper fetchByC_C_First(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching external ID mapper
	* @throws com.liferay.osb.NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper findByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchExternalIdMapperException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper fetchByC_C_Last(
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the external ID mappers before and after the current external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param externalIdMapperId the primary key of the current external ID mapper
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next external ID mapper
	* @throws com.liferay.osb.NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper[] findByC_C_PrevAndNext(
		long externalIdMapperId, long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchExternalIdMapperException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the matching external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ExternalIdMapper> findByC_C_T(
		long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @return the range of matching external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ExternalIdMapper> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ExternalIdMapper> findByC_C_T(
		long classNameId, long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching external ID mapper
	* @throws com.liferay.osb.NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper findByC_C_T_First(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchExternalIdMapperException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper fetchByC_C_T_First(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching external ID mapper
	* @throws com.liferay.osb.NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper findByC_C_T_Last(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchExternalIdMapperException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper fetchByC_C_T_Last(
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the external ID mappers before and after the current external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param externalIdMapperId the primary key of the current external ID mapper
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next external ID mapper
	* @throws com.liferay.osb.NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper[] findByC_C_T_PrevAndNext(
		long externalIdMapperId, long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchExternalIdMapperException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @return the matching external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ExternalIdMapper> findByC_T_EI(
		long classNameId, int type, java.lang.String externalId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @return the range of matching external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ExternalIdMapper> findByC_T_EI(
		long classNameId, int type, java.lang.String externalId, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ExternalIdMapper> findByC_T_EI(
		long classNameId, int type, java.lang.String externalId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching external ID mapper
	* @throws com.liferay.osb.NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper findByC_T_EI_First(
		long classNameId, int type, java.lang.String externalId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchExternalIdMapperException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper fetchByC_T_EI_First(
		long classNameId, int type, java.lang.String externalId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching external ID mapper
	* @throws com.liferay.osb.NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper findByC_T_EI_Last(
		long classNameId, int type, java.lang.String externalId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchExternalIdMapperException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper fetchByC_T_EI_Last(
		long classNameId, int type, java.lang.String externalId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the external ID mappers before and after the current external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* @param externalIdMapperId the primary key of the current external ID mapper
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next external ID mapper
	* @throws com.liferay.osb.NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.ExternalIdMapper[] findByC_T_EI_PrevAndNext(
		long externalIdMapperId, long classNameId, int type,
		java.lang.String externalId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchExternalIdMapperException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the external ID mappers.
	*
	* @return the external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ExternalIdMapper> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the external ID mappers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @return the range of external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ExternalIdMapper> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the external ID mappers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.ExternalIdMapper> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the external ID mappers where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_C_T(long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByC_T_EI(long classNameId, int type,
		java.lang.String externalId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the external ID mappers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of external ID mappers where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @return the number of matching external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C(long classNameId, long classPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class p k
	* @param type the type
	* @return the number of matching external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_C_T(long classNameId, long classPK, int type)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @return the number of matching external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public int countByC_T_EI(long classNameId, int type,
		java.lang.String externalId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of external ID mappers.
	*
	* @return the number of external ID mappers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}