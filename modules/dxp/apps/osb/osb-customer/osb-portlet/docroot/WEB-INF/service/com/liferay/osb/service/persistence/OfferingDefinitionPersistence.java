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

import com.liferay.osb.exception.NoSuchOfferingDefinitionException;
import com.liferay.osb.model.OfferingDefinition;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the offering definition service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.OfferingDefinitionPersistenceImpl
 * @see OfferingDefinitionUtil
 * @generated
 */
@ProviderType
public interface OfferingDefinitionPersistence extends BasePersistence<OfferingDefinition> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OfferingDefinitionUtil} to access the offering definition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the offering definitions where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @return the matching offering definitions
	*/
	public java.util.List<OfferingDefinition> findByProductEntryId(
		long productEntryId);

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
	public java.util.List<OfferingDefinition> findByProductEntryId(
		long productEntryId, int start, int end);

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
	public java.util.List<OfferingDefinition> findByProductEntryId(
		long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator);

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
	public java.util.List<OfferingDefinition> findByProductEntryId(
		long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first offering definition in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering definition
	* @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	*/
	public OfferingDefinition findByProductEntryId_First(long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException;

	/**
	* Returns the first offering definition in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering definition, or <code>null</code> if a matching offering definition could not be found
	*/
	public OfferingDefinition fetchByProductEntryId_First(long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator);

	/**
	* Returns the last offering definition in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering definition
	* @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	*/
	public OfferingDefinition findByProductEntryId_Last(long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException;

	/**
	* Returns the last offering definition in the ordered set where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering definition, or <code>null</code> if a matching offering definition could not be found
	*/
	public OfferingDefinition fetchByProductEntryId_Last(long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator);

	/**
	* Returns the offering definitions before and after the current offering definition in the ordered set where productEntryId = &#63;.
	*
	* @param offeringDefinitionId the primary key of the current offering definition
	* @param productEntryId the product entry ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering definition
	* @throws NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	*/
	public OfferingDefinition[] findByProductEntryId_PrevAndNext(
		long offeringDefinitionId, long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException;

	/**
	* Removes all the offering definitions where productEntryId = &#63; from the database.
	*
	* @param productEntryId the product entry ID
	*/
	public void removeByProductEntryId(long productEntryId);

	/**
	* Returns the number of offering definitions where productEntryId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @return the number of matching offering definitions
	*/
	public int countByProductEntryId(long productEntryId);

	/**
	* Returns all the offering definitions where supportResponseId = &#63;.
	*
	* @param supportResponseId the support response ID
	* @return the matching offering definitions
	*/
	public java.util.List<OfferingDefinition> findBySupportResponseId(
		long supportResponseId);

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
	public java.util.List<OfferingDefinition> findBySupportResponseId(
		long supportResponseId, int start, int end);

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
	public java.util.List<OfferingDefinition> findBySupportResponseId(
		long supportResponseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator);

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
	public java.util.List<OfferingDefinition> findBySupportResponseId(
		long supportResponseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first offering definition in the ordered set where supportResponseId = &#63;.
	*
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering definition
	* @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	*/
	public OfferingDefinition findBySupportResponseId_First(
		long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException;

	/**
	* Returns the first offering definition in the ordered set where supportResponseId = &#63;.
	*
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering definition, or <code>null</code> if a matching offering definition could not be found
	*/
	public OfferingDefinition fetchBySupportResponseId_First(
		long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator);

	/**
	* Returns the last offering definition in the ordered set where supportResponseId = &#63;.
	*
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering definition
	* @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	*/
	public OfferingDefinition findBySupportResponseId_Last(
		long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException;

	/**
	* Returns the last offering definition in the ordered set where supportResponseId = &#63;.
	*
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering definition, or <code>null</code> if a matching offering definition could not be found
	*/
	public OfferingDefinition fetchBySupportResponseId_Last(
		long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator);

	/**
	* Returns the offering definitions before and after the current offering definition in the ordered set where supportResponseId = &#63;.
	*
	* @param offeringDefinitionId the primary key of the current offering definition
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next offering definition
	* @throws NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	*/
	public OfferingDefinition[] findBySupportResponseId_PrevAndNext(
		long offeringDefinitionId, long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException;

	/**
	* Removes all the offering definitions where supportResponseId = &#63; from the database.
	*
	* @param supportResponseId the support response ID
	*/
	public void removeBySupportResponseId(long supportResponseId);

	/**
	* Returns the number of offering definitions where supportResponseId = &#63;.
	*
	* @param supportResponseId the support response ID
	* @return the number of matching offering definitions
	*/
	public int countBySupportResponseId(long supportResponseId);

	/**
	* Returns all the offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @return the matching offering definitions
	*/
	public java.util.List<OfferingDefinition> findByPEI_SRI(
		long productEntryId, long supportResponseId);

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
	public java.util.List<OfferingDefinition> findByPEI_SRI(
		long productEntryId, long supportResponseId, int start, int end);

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
	public java.util.List<OfferingDefinition> findByPEI_SRI(
		long productEntryId, long supportResponseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator);

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
	public java.util.List<OfferingDefinition> findByPEI_SRI(
		long productEntryId, long supportResponseId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering definition
	* @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	*/
	public OfferingDefinition findByPEI_SRI_First(long productEntryId,
		long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException;

	/**
	* Returns the first offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching offering definition, or <code>null</code> if a matching offering definition could not be found
	*/
	public OfferingDefinition fetchByPEI_SRI_First(long productEntryId,
		long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator);

	/**
	* Returns the last offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering definition
	* @throws NoSuchOfferingDefinitionException if a matching offering definition could not be found
	*/
	public OfferingDefinition findByPEI_SRI_Last(long productEntryId,
		long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException;

	/**
	* Returns the last offering definition in the ordered set where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching offering definition, or <code>null</code> if a matching offering definition could not be found
	*/
	public OfferingDefinition fetchByPEI_SRI_Last(long productEntryId,
		long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator);

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
	public OfferingDefinition[] findByPEI_SRI_PrevAndNext(
		long offeringDefinitionId, long productEntryId, long supportResponseId,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator)
		throws NoSuchOfferingDefinitionException;

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
	public java.util.List<OfferingDefinition> findByPEI_SRI(
		long[] productEntryIds, long[] supportResponseIds);

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
	public java.util.List<OfferingDefinition> findByPEI_SRI(
		long[] productEntryIds, long[] supportResponseIds, int start, int end);

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
	public java.util.List<OfferingDefinition> findByPEI_SRI(
		long[] productEntryIds, long[] supportResponseIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator);

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
	public java.util.List<OfferingDefinition> findByPEI_SRI(
		long[] productEntryIds, long[] supportResponseIds, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the offering definitions where productEntryId = &#63; and supportResponseId = &#63; from the database.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	*/
	public void removeByPEI_SRI(long productEntryId, long supportResponseId);

	/**
	* Returns the number of offering definitions where productEntryId = &#63; and supportResponseId = &#63;.
	*
	* @param productEntryId the product entry ID
	* @param supportResponseId the support response ID
	* @return the number of matching offering definitions
	*/
	public int countByPEI_SRI(long productEntryId, long supportResponseId);

	/**
	* Returns the number of offering definitions where productEntryId = any &#63; and supportResponseId = any &#63;.
	*
	* @param productEntryIds the product entry IDs
	* @param supportResponseIds the support response IDs
	* @return the number of matching offering definitions
	*/
	public int countByPEI_SRI(long[] productEntryIds, long[] supportResponseIds);

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
	public OfferingDefinition findByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, java.lang.String productDescription,
		boolean licenses, boolean unlimitedLicenses, boolean supportTickets)
		throws NoSuchOfferingDefinitionException;

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
	public OfferingDefinition fetchByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, java.lang.String productDescription,
		boolean licenses, boolean unlimitedLicenses, boolean supportTickets);

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
	public OfferingDefinition fetchByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, java.lang.String productDescription,
		boolean licenses, boolean unlimitedLicenses, boolean supportTickets,
		boolean retrieveFromCache);

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
	public OfferingDefinition removeByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, java.lang.String productDescription,
		boolean licenses, boolean unlimitedLicenses, boolean supportTickets)
		throws NoSuchOfferingDefinitionException;

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
	public int countByPEI_SRI_PD_L_UL_ST(long productEntryId,
		long supportResponseId, java.lang.String productDescription,
		boolean licenses, boolean unlimitedLicenses, boolean supportTickets);

	/**
	* Caches the offering definition in the entity cache if it is enabled.
	*
	* @param offeringDefinition the offering definition
	*/
	public void cacheResult(OfferingDefinition offeringDefinition);

	/**
	* Caches the offering definitions in the entity cache if it is enabled.
	*
	* @param offeringDefinitions the offering definitions
	*/
	public void cacheResult(
		java.util.List<OfferingDefinition> offeringDefinitions);

	/**
	* Creates a new offering definition with the primary key. Does not add the offering definition to the database.
	*
	* @param offeringDefinitionId the primary key for the new offering definition
	* @return the new offering definition
	*/
	public OfferingDefinition create(long offeringDefinitionId);

	/**
	* Removes the offering definition with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition that was removed
	* @throws NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	*/
	public OfferingDefinition remove(long offeringDefinitionId)
		throws NoSuchOfferingDefinitionException;

	public OfferingDefinition updateImpl(OfferingDefinition offeringDefinition);

	/**
	* Returns the offering definition with the primary key or throws a {@link NoSuchOfferingDefinitionException} if it could not be found.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition
	* @throws NoSuchOfferingDefinitionException if a offering definition with the primary key could not be found
	*/
	public OfferingDefinition findByPrimaryKey(long offeringDefinitionId)
		throws NoSuchOfferingDefinitionException;

	/**
	* Returns the offering definition with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param offeringDefinitionId the primary key of the offering definition
	* @return the offering definition, or <code>null</code> if a offering definition with the primary key could not be found
	*/
	public OfferingDefinition fetchByPrimaryKey(long offeringDefinitionId);

	@Override
	public java.util.Map<java.io.Serializable, OfferingDefinition> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the offering definitions.
	*
	* @return the offering definitions
	*/
	public java.util.List<OfferingDefinition> findAll();

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
	public java.util.List<OfferingDefinition> findAll(int start, int end);

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
	public java.util.List<OfferingDefinition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator);

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
	public java.util.List<OfferingDefinition> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<OfferingDefinition> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the offering definitions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of offering definitions.
	*
	* @return the number of offering definitions
	*/
	public int countAll();

	/**
	* Returns the primaryKeys of offering bundles associated with the offering definition.
	*
	* @param pk the primary key of the offering definition
	* @return long[] of the primaryKeys of offering bundles associated with the offering definition
	*/
	public long[] getOfferingBundlePrimaryKeys(long pk);

	/**
	* Returns all the offering bundles associated with the offering definition.
	*
	* @param pk the primary key of the offering definition
	* @return the offering bundles associated with the offering definition
	*/
	public java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		long pk);

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
	public java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		long pk, int start, int end);

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
	public java.util.List<com.liferay.osb.model.OfferingBundle> getOfferingBundles(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.OfferingBundle> orderByComparator);

	/**
	* Returns the number of offering bundles associated with the offering definition.
	*
	* @param pk the primary key of the offering definition
	* @return the number of offering bundles associated with the offering definition
	*/
	public int getOfferingBundlesSize(long pk);

	/**
	* Returns <code>true</code> if the offering bundle is associated with the offering definition.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePK the primary key of the offering bundle
	* @return <code>true</code> if the offering bundle is associated with the offering definition; <code>false</code> otherwise
	*/
	public boolean containsOfferingBundle(long pk, long offeringBundlePK);

	/**
	* Returns <code>true</code> if the offering definition has any offering bundles associated with it.
	*
	* @param pk the primary key of the offering definition to check for associations with offering bundles
	* @return <code>true</code> if the offering definition has any offering bundles associated with it; <code>false</code> otherwise
	*/
	public boolean containsOfferingBundles(long pk);

	/**
	* Adds an association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePK the primary key of the offering bundle
	*/
	public void addOfferingBundle(long pk, long offeringBundlePK);

	/**
	* Adds an association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundle the offering bundle
	*/
	public void addOfferingBundle(long pk,
		com.liferay.osb.model.OfferingBundle offeringBundle);

	/**
	* Adds an association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePKs the primary keys of the offering bundles
	*/
	public void addOfferingBundles(long pk, long[] offeringBundlePKs);

	/**
	* Adds an association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundles the offering bundles
	*/
	public void addOfferingBundles(long pk,
		java.util.List<com.liferay.osb.model.OfferingBundle> offeringBundles);

	/**
	* Clears all associations between the offering definition and its offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition to clear the associated offering bundles from
	*/
	public void clearOfferingBundles(long pk);

	/**
	* Removes the association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePK the primary key of the offering bundle
	*/
	public void removeOfferingBundle(long pk, long offeringBundlePK);

	/**
	* Removes the association between the offering definition and the offering bundle. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundle the offering bundle
	*/
	public void removeOfferingBundle(long pk,
		com.liferay.osb.model.OfferingBundle offeringBundle);

	/**
	* Removes the association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePKs the primary keys of the offering bundles
	*/
	public void removeOfferingBundles(long pk, long[] offeringBundlePKs);

	/**
	* Removes the association between the offering definition and the offering bundles. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundles the offering bundles
	*/
	public void removeOfferingBundles(long pk,
		java.util.List<com.liferay.osb.model.OfferingBundle> offeringBundles);

	/**
	* Sets the offering bundles associated with the offering definition, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundlePKs the primary keys of the offering bundles to be associated with the offering definition
	*/
	public void setOfferingBundles(long pk, long[] offeringBundlePKs);

	/**
	* Sets the offering bundles associated with the offering definition, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the offering definition
	* @param offeringBundles the offering bundles to be associated with the offering definition
	*/
	public void setOfferingBundles(long pk,
		java.util.List<com.liferay.osb.model.OfferingBundle> offeringBundles);
}