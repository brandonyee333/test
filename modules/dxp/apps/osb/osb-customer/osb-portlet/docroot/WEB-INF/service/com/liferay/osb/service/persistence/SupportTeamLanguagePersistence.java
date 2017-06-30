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

import com.liferay.osb.model.SupportTeamLanguage;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the support team language service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportTeamLanguagePersistenceImpl
 * @see SupportTeamLanguageUtil
 * @generated
 */
public interface SupportTeamLanguagePersistence extends BasePersistence<SupportTeamLanguage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportTeamLanguageUtil} to access the support team language persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the support team language in the entity cache if it is enabled.
	*
	* @param supportTeamLanguage the support team language
	*/
	public void cacheResult(
		com.liferay.osb.model.SupportTeamLanguage supportTeamLanguage);

	/**
	* Caches the support team languages in the entity cache if it is enabled.
	*
	* @param supportTeamLanguages the support team languages
	*/
	public void cacheResult(
		java.util.List<com.liferay.osb.model.SupportTeamLanguage> supportTeamLanguages);

	/**
	* Creates a new support team language with the primary key. Does not add the support team language to the database.
	*
	* @param supportTeamLanguageId the primary key for the new support team language
	* @return the new support team language
	*/
	public com.liferay.osb.model.SupportTeamLanguage create(
		long supportTeamLanguageId);

	/**
	* Removes the support team language with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeamLanguageId the primary key of the support team language
	* @return the support team language that was removed
	* @throws com.liferay.osb.NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeamLanguage remove(
		long supportTeamLanguageId)
		throws com.liferay.osb.NoSuchSupportTeamLanguageException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.osb.model.SupportTeamLanguage updateImpl(
		com.liferay.osb.model.SupportTeamLanguage supportTeamLanguage,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support team language with the primary key or throws a {@link com.liferay.osb.NoSuchSupportTeamLanguageException} if it could not be found.
	*
	* @param supportTeamLanguageId the primary key of the support team language
	* @return the support team language
	* @throws com.liferay.osb.NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeamLanguage findByPrimaryKey(
		long supportTeamLanguageId)
		throws com.liferay.osb.NoSuchSupportTeamLanguageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support team language with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportTeamLanguageId the primary key of the support team language
	* @return the support team language, or <code>null</code> if a support team language with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeamLanguage fetchByPrimaryKey(
		long supportTeamLanguageId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the support team languages where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @return the matching support team languages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeamLanguage> findBySupportTeamId(
		long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the support team languages where supportTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportTeamId the support team ID
	* @param start the lower bound of the range of support team languages
	* @param end the upper bound of the range of support team languages (not inclusive)
	* @return the range of matching support team languages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeamLanguage> findBySupportTeamId(
		long supportTeamId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the support team languages where supportTeamId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param supportTeamId the support team ID
	* @param start the lower bound of the range of support team languages
	* @param end the upper bound of the range of support team languages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching support team languages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeamLanguage> findBySupportTeamId(
		long supportTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first support team language in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support team language
	* @throws com.liferay.osb.NoSuchSupportTeamLanguageException if a matching support team language could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeamLanguage findBySupportTeamId_First(
		long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportTeamLanguageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first support team language in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support team language, or <code>null</code> if a matching support team language could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeamLanguage fetchBySupportTeamId_First(
		long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last support team language in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support team language
	* @throws com.liferay.osb.NoSuchSupportTeamLanguageException if a matching support team language could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeamLanguage findBySupportTeamId_Last(
		long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportTeamLanguageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last support team language in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support team language, or <code>null</code> if a matching support team language could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeamLanguage fetchBySupportTeamId_Last(
		long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the support team languages before and after the current support team language in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamLanguageId the primary key of the current support team language
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support team language
	* @throws com.liferay.osb.NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.osb.model.SupportTeamLanguage[] findBySupportTeamId_PrevAndNext(
		long supportTeamLanguageId, long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.osb.NoSuchSupportTeamLanguageException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the support team languages.
	*
	* @return the support team languages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeamLanguage> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the support team languages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support team languages
	* @param end the upper bound of the range of support team languages (not inclusive)
	* @return the range of support team languages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeamLanguage> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the support team languages.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of support team languages
	* @param end the upper bound of the range of support team languages (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support team languages
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.osb.model.SupportTeamLanguage> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the support team languages where supportTeamId = &#63; from the database.
	*
	* @param supportTeamId the support team ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeBySupportTeamId(long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the support team languages from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of support team languages where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @return the number of matching support team languages
	* @throws SystemException if a system exception occurred
	*/
	public int countBySupportTeamId(long supportTeamId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of support team languages.
	*
	* @return the number of support team languages
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}