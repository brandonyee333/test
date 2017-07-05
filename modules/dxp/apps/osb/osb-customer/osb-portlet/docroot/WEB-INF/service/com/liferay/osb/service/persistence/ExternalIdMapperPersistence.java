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

import com.liferay.osb.exception.NoSuchExternalIdMapperException;
import com.liferay.osb.model.ExternalIdMapper;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the external ID mapper service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.ExternalIdMapperPersistenceImpl
 * @see ExternalIdMapperUtil
 * @generated
 */
@ProviderType
public interface ExternalIdMapperPersistence extends BasePersistence<ExternalIdMapper> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ExternalIdMapperUtil} to access the external ID mapper persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the matching external ID mappers
	*/
	public java.util.List<ExternalIdMapper> findByC_C(long classNameId,
		long classPK);

	/**
	* Returns a range of all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @return the range of matching external ID mappers
	*/
	public java.util.List<ExternalIdMapper> findByC_C(long classNameId,
		long classPK, int start, int end);

	/**
	* Returns an ordered range of all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching external ID mappers
	*/
	public java.util.List<ExternalIdMapper> findByC_C(long classNameId,
		long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator);

	/**
	* Returns an ordered range of all the external ID mappers where classNameId = &#63; and classPK = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching external ID mappers
	*/
	public java.util.List<ExternalIdMapper> findByC_C(long classNameId,
		long classPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching external ID mapper
	* @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	*/
	public ExternalIdMapper findByC_C_First(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException;

	/**
	* Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	*/
	public ExternalIdMapper fetchByC_C_First(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator);

	/**
	* Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching external ID mapper
	* @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	*/
	public ExternalIdMapper findByC_C_Last(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException;

	/**
	* Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	*/
	public ExternalIdMapper fetchByC_C_Last(long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator);

	/**
	* Returns the external ID mappers before and after the current external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63;.
	*
	* @param externalIdMapperId the primary key of the current external ID mapper
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next external ID mapper
	* @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	*/
	public ExternalIdMapper[] findByC_C_PrevAndNext(long externalIdMapperId,
		long classNameId, long classPK,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException;

	/**
	* Removes all the external ID mappers where classNameId = &#63; and classPK = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	*/
	public void removeByC_C(long classNameId, long classPK);

	/**
	* Returns the number of external ID mappers where classNameId = &#63; and classPK = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @return the number of matching external ID mappers
	*/
	public int countByC_C(long classNameId, long classPK);

	/**
	* Returns all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @return the matching external ID mappers
	*/
	public java.util.List<ExternalIdMapper> findByC_C_T(long classNameId,
		long classPK, int type);

	/**
	* Returns a range of all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @return the range of matching external ID mappers
	*/
	public java.util.List<ExternalIdMapper> findByC_C_T(long classNameId,
		long classPK, int type, int start, int end);

	/**
	* Returns an ordered range of all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching external ID mappers
	*/
	public java.util.List<ExternalIdMapper> findByC_C_T(long classNameId,
		long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator);

	/**
	* Returns an ordered range of all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching external ID mappers
	*/
	public java.util.List<ExternalIdMapper> findByC_C_T(long classNameId,
		long classPK, int type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching external ID mapper
	* @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	*/
	public ExternalIdMapper findByC_C_T_First(long classNameId, long classPK,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException;

	/**
	* Returns the first external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	*/
	public ExternalIdMapper fetchByC_C_T_First(long classNameId, long classPK,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator);

	/**
	* Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching external ID mapper
	* @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	*/
	public ExternalIdMapper findByC_C_T_Last(long classNameId, long classPK,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException;

	/**
	* Returns the last external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	*/
	public ExternalIdMapper fetchByC_C_T_Last(long classNameId, long classPK,
		int type,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator);

	/**
	* Returns the external ID mappers before and after the current external ID mapper in the ordered set where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param externalIdMapperId the primary key of the current external ID mapper
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next external ID mapper
	* @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	*/
	public ExternalIdMapper[] findByC_C_T_PrevAndNext(long externalIdMapperId,
		long classNameId, long classPK, int type,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException;

	/**
	* Removes all the external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	*/
	public void removeByC_C_T(long classNameId, long classPK, int type);

	/**
	* Returns the number of external ID mappers where classNameId = &#63; and classPK = &#63; and type = &#63;.
	*
	* @param classNameId the class name ID
	* @param classPK the class pk
	* @param type the type
	* @return the number of matching external ID mappers
	*/
	public int countByC_C_T(long classNameId, long classPK, int type);

	/**
	* Returns all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @return the matching external ID mappers
	*/
	public java.util.List<ExternalIdMapper> findByC_T_EI(long classNameId,
		int type, java.lang.String externalId);

	/**
	* Returns a range of all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @return the range of matching external ID mappers
	*/
	public java.util.List<ExternalIdMapper> findByC_T_EI(long classNameId,
		int type, java.lang.String externalId, int start, int end);

	/**
	* Returns an ordered range of all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching external ID mappers
	*/
	public java.util.List<ExternalIdMapper> findByC_T_EI(long classNameId,
		int type, java.lang.String externalId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator);

	/**
	* Returns an ordered range of all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching external ID mappers
	*/
	public java.util.List<ExternalIdMapper> findByC_T_EI(long classNameId,
		int type, java.lang.String externalId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching external ID mapper
	* @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	*/
	public ExternalIdMapper findByC_T_EI_First(long classNameId, int type,
		java.lang.String externalId,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException;

	/**
	* Returns the first external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	*/
	public ExternalIdMapper fetchByC_T_EI_First(long classNameId, int type,
		java.lang.String externalId,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator);

	/**
	* Returns the last external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching external ID mapper
	* @throws NoSuchExternalIdMapperException if a matching external ID mapper could not be found
	*/
	public ExternalIdMapper findByC_T_EI_Last(long classNameId, int type,
		java.lang.String externalId,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException;

	/**
	* Returns the last external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching external ID mapper, or <code>null</code> if a matching external ID mapper could not be found
	*/
	public ExternalIdMapper fetchByC_T_EI_Last(long classNameId, int type,
		java.lang.String externalId,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator);

	/**
	* Returns the external ID mappers before and after the current external ID mapper in the ordered set where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* @param externalIdMapperId the primary key of the current external ID mapper
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next external ID mapper
	* @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	*/
	public ExternalIdMapper[] findByC_T_EI_PrevAndNext(
		long externalIdMapperId, long classNameId, int type,
		java.lang.String externalId,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator)
		throws NoSuchExternalIdMapperException;

	/**
	* Removes all the external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63; from the database.
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	*/
	public void removeByC_T_EI(long classNameId, int type,
		java.lang.String externalId);

	/**
	* Returns the number of external ID mappers where classNameId = &#63; and type = &#63; and externalId = &#63;.
	*
	* @param classNameId the class name ID
	* @param type the type
	* @param externalId the external ID
	* @return the number of matching external ID mappers
	*/
	public int countByC_T_EI(long classNameId, int type,
		java.lang.String externalId);

	/**
	* Caches the external ID mapper in the entity cache if it is enabled.
	*
	* @param externalIdMapper the external ID mapper
	*/
	public void cacheResult(ExternalIdMapper externalIdMapper);

	/**
	* Caches the external ID mappers in the entity cache if it is enabled.
	*
	* @param externalIdMappers the external ID mappers
	*/
	public void cacheResult(java.util.List<ExternalIdMapper> externalIdMappers);

	/**
	* Creates a new external ID mapper with the primary key. Does not add the external ID mapper to the database.
	*
	* @param externalIdMapperId the primary key for the new external ID mapper
	* @return the new external ID mapper
	*/
	public ExternalIdMapper create(long externalIdMapperId);

	/**
	* Removes the external ID mapper with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param externalIdMapperId the primary key of the external ID mapper
	* @return the external ID mapper that was removed
	* @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	*/
	public ExternalIdMapper remove(long externalIdMapperId)
		throws NoSuchExternalIdMapperException;

	public ExternalIdMapper updateImpl(ExternalIdMapper externalIdMapper);

	/**
	* Returns the external ID mapper with the primary key or throws a {@link NoSuchExternalIdMapperException} if it could not be found.
	*
	* @param externalIdMapperId the primary key of the external ID mapper
	* @return the external ID mapper
	* @throws NoSuchExternalIdMapperException if a external ID mapper with the primary key could not be found
	*/
	public ExternalIdMapper findByPrimaryKey(long externalIdMapperId)
		throws NoSuchExternalIdMapperException;

	/**
	* Returns the external ID mapper with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param externalIdMapperId the primary key of the external ID mapper
	* @return the external ID mapper, or <code>null</code> if a external ID mapper with the primary key could not be found
	*/
	public ExternalIdMapper fetchByPrimaryKey(long externalIdMapperId);

	@Override
	public java.util.Map<java.io.Serializable, ExternalIdMapper> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the external ID mappers.
	*
	* @return the external ID mappers
	*/
	public java.util.List<ExternalIdMapper> findAll();

	/**
	* Returns a range of all the external ID mappers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @return the range of external ID mappers
	*/
	public java.util.List<ExternalIdMapper> findAll(int start, int end);

	/**
	* Returns an ordered range of all the external ID mappers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of external ID mappers
	*/
	public java.util.List<ExternalIdMapper> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator);

	/**
	* Returns an ordered range of all the external ID mappers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ExternalIdMapperModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of external ID mappers
	* @param end the upper bound of the range of external ID mappers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of external ID mappers
	*/
	public java.util.List<ExternalIdMapper> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ExternalIdMapper> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the external ID mappers from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of external ID mappers.
	*
	* @return the number of external ID mappers
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}