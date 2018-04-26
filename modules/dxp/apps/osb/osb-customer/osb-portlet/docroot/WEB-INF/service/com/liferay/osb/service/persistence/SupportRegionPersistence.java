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

import com.liferay.osb.exception.NoSuchSupportRegionException;
import com.liferay.osb.model.SupportRegion;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the support region service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.service.persistence.impl.SupportRegionPersistenceImpl
 * @see SupportRegionUtil
 * @generated
 */
@ProviderType
public interface SupportRegionPersistence extends BasePersistence<SupportRegion> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportRegionUtil} to access the support region persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the support region where name = &#63; or throws a {@link NoSuchSupportRegionException} if it could not be found.
	*
	* @param name the name
	* @return the matching support region
	* @throws NoSuchSupportRegionException if a matching support region could not be found
	*/
	public SupportRegion findByName(java.lang.String name)
		throws NoSuchSupportRegionException;

	/**
	* Returns the support region where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param name the name
	* @return the matching support region, or <code>null</code> if a matching support region could not be found
	*/
	public SupportRegion fetchByName(java.lang.String name);

	/**
	* Returns the support region where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param name the name
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching support region, or <code>null</code> if a matching support region could not be found
	*/
	public SupportRegion fetchByName(java.lang.String name,
		boolean retrieveFromCache);

	/**
	* Removes the support region where name = &#63; from the database.
	*
	* @param name the name
	* @return the support region that was removed
	*/
	public SupportRegion removeByName(java.lang.String name)
		throws NoSuchSupportRegionException;

	/**
	* Returns the number of support regions where name = &#63;.
	*
	* @param name the name
	* @return the number of matching support regions
	*/
	public int countByName(java.lang.String name);

	/**
	* Caches the support region in the entity cache if it is enabled.
	*
	* @param supportRegion the support region
	*/
	public void cacheResult(SupportRegion supportRegion);

	/**
	* Caches the support regions in the entity cache if it is enabled.
	*
	* @param supportRegions the support regions
	*/
	public void cacheResult(java.util.List<SupportRegion> supportRegions);

	/**
	* Creates a new support region with the primary key. Does not add the support region to the database.
	*
	* @param supportRegionId the primary key for the new support region
	* @return the new support region
	*/
	public SupportRegion create(long supportRegionId);

	/**
	* Removes the support region with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param supportRegionId the primary key of the support region
	* @return the support region that was removed
	* @throws NoSuchSupportRegionException if a support region with the primary key could not be found
	*/
	public SupportRegion remove(long supportRegionId)
		throws NoSuchSupportRegionException;

	public SupportRegion updateImpl(SupportRegion supportRegion);

	/**
	* Returns the support region with the primary key or throws a {@link NoSuchSupportRegionException} if it could not be found.
	*
	* @param supportRegionId the primary key of the support region
	* @return the support region
	* @throws NoSuchSupportRegionException if a support region with the primary key could not be found
	*/
	public SupportRegion findByPrimaryKey(long supportRegionId)
		throws NoSuchSupportRegionException;

	/**
	* Returns the support region with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param supportRegionId the primary key of the support region
	* @return the support region, or <code>null</code> if a support region with the primary key could not be found
	*/
	public SupportRegion fetchByPrimaryKey(long supportRegionId);

	@Override
	public java.util.Map<java.io.Serializable, SupportRegion> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the support regions.
	*
	* @return the support regions
	*/
	public java.util.List<SupportRegion> findAll();

	/**
	* Returns a range of all the support regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @return the range of support regions
	*/
	public java.util.List<SupportRegion> findAll(int start, int end);

	/**
	* Returns an ordered range of all the support regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of support regions
	*/
	public java.util.List<SupportRegion> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportRegion> orderByComparator);

	/**
	* Returns an ordered range of all the support regions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of support regions
	*/
	public java.util.List<SupportRegion> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportRegion> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the support regions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of support regions.
	*
	* @return the number of support regions
	*/
	public int countAll();

	/**
	* Returns the primaryKeys of account entries associated with the support region.
	*
	* @param pk the primary key of the support region
	* @return long[] of the primaryKeys of account entries associated with the support region
	*/
	public long[] getAccountEntryPrimaryKeys(long pk);

	/**
	* Returns all the account entries associated with the support region.
	*
	* @param pk the primary key of the support region
	* @return the account entries associated with the support region
	*/
	public java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		long pk);

	/**
	* Returns a range of all the account entries associated with the support region.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the support region
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @return the range of account entries associated with the support region
	*/
	public java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		long pk, int start, int end);

	/**
	* Returns an ordered range of all the account entries associated with the support region.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the support region
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of account entries associated with the support region
	*/
	public java.util.List<com.liferay.osb.model.AccountEntry> getAccountEntries(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.AccountEntry> orderByComparator);

	/**
	* Returns the number of account entries associated with the support region.
	*
	* @param pk the primary key of the support region
	* @return the number of account entries associated with the support region
	*/
	public int getAccountEntriesSize(long pk);

	/**
	* Returns <code>true</code> if the account entry is associated with the support region.
	*
	* @param pk the primary key of the support region
	* @param accountEntryPK the primary key of the account entry
	* @return <code>true</code> if the account entry is associated with the support region; <code>false</code> otherwise
	*/
	public boolean containsAccountEntry(long pk, long accountEntryPK);

	/**
	* Returns <code>true</code> if the support region has any account entries associated with it.
	*
	* @param pk the primary key of the support region to check for associations with account entries
	* @return <code>true</code> if the support region has any account entries associated with it; <code>false</code> otherwise
	*/
	public boolean containsAccountEntries(long pk);

	/**
	* Adds an association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntryPK the primary key of the account entry
	*/
	public void addAccountEntry(long pk, long accountEntryPK);

	/**
	* Adds an association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntry the account entry
	*/
	public void addAccountEntry(long pk,
		com.liferay.osb.model.AccountEntry accountEntry);

	/**
	* Adds an association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntryPKs the primary keys of the account entries
	*/
	public void addAccountEntries(long pk, long[] accountEntryPKs);

	/**
	* Adds an association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntries the account entries
	*/
	public void addAccountEntries(long pk,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries);

	/**
	* Clears all associations between the support region and its account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region to clear the associated account entries from
	*/
	public void clearAccountEntries(long pk);

	/**
	* Removes the association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntryPK the primary key of the account entry
	*/
	public void removeAccountEntry(long pk, long accountEntryPK);

	/**
	* Removes the association between the support region and the account entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntry the account entry
	*/
	public void removeAccountEntry(long pk,
		com.liferay.osb.model.AccountEntry accountEntry);

	/**
	* Removes the association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntryPKs the primary keys of the account entries
	*/
	public void removeAccountEntries(long pk, long[] accountEntryPKs);

	/**
	* Removes the association between the support region and the account entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntries the account entries
	*/
	public void removeAccountEntries(long pk,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries);

	/**
	* Sets the account entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntryPKs the primary keys of the account entries to be associated with the support region
	*/
	public void setAccountEntries(long pk, long[] accountEntryPKs);

	/**
	* Sets the account entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param accountEntries the account entries to be associated with the support region
	*/
	public void setAccountEntries(long pk,
		java.util.List<com.liferay.osb.model.AccountEntry> accountEntries);

	/**
	* Returns the primaryKeys of partner entries associated with the support region.
	*
	* @param pk the primary key of the support region
	* @return long[] of the primaryKeys of partner entries associated with the support region
	*/
	public long[] getPartnerEntryPrimaryKeys(long pk);

	/**
	* Returns all the partner entries associated with the support region.
	*
	* @param pk the primary key of the support region
	* @return the partner entries associated with the support region
	*/
	public java.util.List<com.liferay.osb.model.PartnerEntry> getPartnerEntries(
		long pk);

	/**
	* Returns a range of all the partner entries associated with the support region.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the support region
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @return the range of partner entries associated with the support region
	*/
	public java.util.List<com.liferay.osb.model.PartnerEntry> getPartnerEntries(
		long pk, int start, int end);

	/**
	* Returns an ordered range of all the partner entries associated with the support region.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SupportRegionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param pk the primary key of the support region
	* @param start the lower bound of the range of support regions
	* @param end the upper bound of the range of support regions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of partner entries associated with the support region
	*/
	public java.util.List<com.liferay.osb.model.PartnerEntry> getPartnerEntries(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.osb.model.PartnerEntry> orderByComparator);

	/**
	* Returns the number of partner entries associated with the support region.
	*
	* @param pk the primary key of the support region
	* @return the number of partner entries associated with the support region
	*/
	public int getPartnerEntriesSize(long pk);

	/**
	* Returns <code>true</code> if the partner entry is associated with the support region.
	*
	* @param pk the primary key of the support region
	* @param partnerEntryPK the primary key of the partner entry
	* @return <code>true</code> if the partner entry is associated with the support region; <code>false</code> otherwise
	*/
	public boolean containsPartnerEntry(long pk, long partnerEntryPK);

	/**
	* Returns <code>true</code> if the support region has any partner entries associated with it.
	*
	* @param pk the primary key of the support region to check for associations with partner entries
	* @return <code>true</code> if the support region has any partner entries associated with it; <code>false</code> otherwise
	*/
	public boolean containsPartnerEntries(long pk);

	/**
	* Adds an association between the support region and the partner entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntryPK the primary key of the partner entry
	*/
	public void addPartnerEntry(long pk, long partnerEntryPK);

	/**
	* Adds an association between the support region and the partner entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntry the partner entry
	*/
	public void addPartnerEntry(long pk,
		com.liferay.osb.model.PartnerEntry partnerEntry);

	/**
	* Adds an association between the support region and the partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntryPKs the primary keys of the partner entries
	*/
	public void addPartnerEntries(long pk, long[] partnerEntryPKs);

	/**
	* Adds an association between the support region and the partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntries the partner entries
	*/
	public void addPartnerEntries(long pk,
		java.util.List<com.liferay.osb.model.PartnerEntry> partnerEntries);

	/**
	* Clears all associations between the support region and its partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region to clear the associated partner entries from
	*/
	public void clearPartnerEntries(long pk);

	/**
	* Removes the association between the support region and the partner entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntryPK the primary key of the partner entry
	*/
	public void removePartnerEntry(long pk, long partnerEntryPK);

	/**
	* Removes the association between the support region and the partner entry. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntry the partner entry
	*/
	public void removePartnerEntry(long pk,
		com.liferay.osb.model.PartnerEntry partnerEntry);

	/**
	* Removes the association between the support region and the partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntryPKs the primary keys of the partner entries
	*/
	public void removePartnerEntries(long pk, long[] partnerEntryPKs);

	/**
	* Removes the association between the support region and the partner entries. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntries the partner entries
	*/
	public void removePartnerEntries(long pk,
		java.util.List<com.liferay.osb.model.PartnerEntry> partnerEntries);

	/**
	* Sets the partner entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntryPKs the primary keys of the partner entries to be associated with the support region
	*/
	public void setPartnerEntries(long pk, long[] partnerEntryPKs);

	/**
	* Sets the partner entries associated with the support region, removing and adding associations as necessary. Also notifies the appropriate model listeners and clears the mapping table finder cache.
	*
	* @param pk the primary key of the support region
	* @param partnerEntries the partner entries to be associated with the support region
	*/
	public void setPartnerEntries(long pk,
		java.util.List<com.liferay.osb.model.PartnerEntry> partnerEntries);
}