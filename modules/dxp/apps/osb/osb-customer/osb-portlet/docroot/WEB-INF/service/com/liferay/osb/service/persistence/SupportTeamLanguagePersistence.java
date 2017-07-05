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

import com.liferay.osb.exception.NoSuchSupportTeamLanguageException;
import com.liferay.osb.model.SupportTeamLanguage;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the support team language service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.SupportTeamLanguagePersistenceImpl
 * @see SupportTeamLanguageUtil
 * @generated
 */
@ProviderType
public interface SupportTeamLanguagePersistence extends BasePersistence<SupportTeamLanguage> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportTeamLanguageUtil} to access the support team language persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the support team languages where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @return the matching support team languages
	*/
	public java.util.List<SupportTeamLanguage> findBySupportTeamId(
		long supportTeamId);

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
	public java.util.List<SupportTeamLanguage> findBySupportTeamId(
		long supportTeamId, int start, int end);

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
	public java.util.List<SupportTeamLanguage> findBySupportTeamId(
		long supportTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportTeamLanguage> orderByComparator);

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
	public java.util.List<SupportTeamLanguage> findBySupportTeamId(
		long supportTeamId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportTeamLanguage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first support team language in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support team language
	* @throws NoSuchSupportTeamLanguageException if a matching support team language could not be found
	*/
	public SupportTeamLanguage findBySupportTeamId_First(long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportTeamLanguage> orderByComparator)
		throws NoSuchSupportTeamLanguageException;

	/**
	* Returns the first support team language in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching support team language, or <code>null</code> if a matching support team language could not be found
	*/
	public SupportTeamLanguage fetchBySupportTeamId_First(long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportTeamLanguage> orderByComparator);

	/**
	* Returns the last support team language in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support team language
	* @throws NoSuchSupportTeamLanguageException if a matching support team language could not be found
	*/
	public SupportTeamLanguage findBySupportTeamId_Last(long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportTeamLanguage> orderByComparator)
		throws NoSuchSupportTeamLanguageException;

	/**
	* Returns the last support team language in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching support team language, or <code>null</code> if a matching support team language could not be found
	*/
	public SupportTeamLanguage fetchBySupportTeamId_Last(long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportTeamLanguage> orderByComparator);

	/**
	* Returns the support team languages before and after the current support team language in the ordered set where supportTeamId = &#63;.
	*
	* @param supportTeamLanguageId the primary key of the current support team language
	* @param supportTeamId the support team ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next support team language
	* @throws NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	*/
	public SupportTeamLanguage[] findBySupportTeamId_PrevAndNext(
		long supportTeamLanguageId, long supportTeamId,
		com.liferay.portal.kernel.util.OrderByComparator<SupportTeamLanguage> orderByComparator)
		throws NoSuchSupportTeamLanguageException;

	/**
	* Removes all the support team languages where supportTeamId = &#63; from the database.
	*
	* @param supportTeamId the support team ID
	*/
	public void removeBySupportTeamId(long supportTeamId);

	/**
	* Returns the number of support team languages where supportTeamId = &#63;.
	*
	* @param supportTeamId the support team ID
	* @return the number of matching support team languages
	*/
	public int countBySupportTeamId(long supportTeamId);

	/**
	* Caches the support team language in the entity cache if it is enabled.
	*
	* @param supportTeamLanguage the support team language
	*/
	public void cacheResult(SupportTeamLanguage supportTeamLanguage);

	/**
	* Caches the support team languages in the entity cache if it is enabled.
	*
	* @param supportTeamLanguages the support team languages
	*/
	public void cacheResult(
		java.util.List<SupportTeamLanguage> supportTeamLanguages);

	/**
	* Creates a new support team language with the primary key. Does not add the support team language to the database.
	*
	* @param supportTeamLanguageId the primary key for the new support team language
	* @return the new support team language
	*/
	public SupportTeamLanguage create(long supportTeamLanguageId);

	/**
	* Removes the support team language with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportTeamLanguageId the primary key of the support team language
	* @return the support team language that was removed
	* @throws NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	*/
	public SupportTeamLanguage remove(long supportTeamLanguageId)
		throws NoSuchSupportTeamLanguageException;

	public SupportTeamLanguage updateImpl(
		SupportTeamLanguage supportTeamLanguage);

	/**
	* Returns the support team language with the primary key or throws a {@link NoSuchSupportTeamLanguageException} if it could not be found.
	*
	* @param supportTeamLanguageId the primary key of the support team language
	* @return the support team language
	* @throws NoSuchSupportTeamLanguageException if a support team language with the primary key could not be found
	*/
	public SupportTeamLanguage findByPrimaryKey(long supportTeamLanguageId)
		throws NoSuchSupportTeamLanguageException;

	/**
	* Returns the support team language with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportTeamLanguageId the primary key of the support team language
	* @return the support team language, or <code>null</code> if a support team language with the primary key could not be found
	*/
	public SupportTeamLanguage fetchByPrimaryKey(long supportTeamLanguageId);

	@Override
	public java.util.Map<java.io.Serializable, SupportTeamLanguage> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the support team languages.
	*
	* @return the support team languages
	*/
	public java.util.List<SupportTeamLanguage> findAll();

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
	public java.util.List<SupportTeamLanguage> findAll(int start, int end);

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
	public java.util.List<SupportTeamLanguage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportTeamLanguage> orderByComparator);

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
	public java.util.List<SupportTeamLanguage> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportTeamLanguage> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the support team languages from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of support team languages.
	*
	* @return the number of support team languages
	*/
	public int countAll();
}