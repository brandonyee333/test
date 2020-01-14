/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.osb.customer.admin.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.admin.exception.NoSuchSupportRegionException;
import com.liferay.osb.customer.admin.model.SupportRegion;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the support region service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SupportRegionUtil
 * @generated
 */
@ProviderType
public interface SupportRegionPersistence
	extends BasePersistence<SupportRegion> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SupportRegionUtil} to access the support region persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, SupportRegion> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the support region where name = &#63; or throws a <code>NoSuchSupportRegionException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching support region
	 * @throws NoSuchSupportRegionException if a matching support region could not be found
	 */
	public SupportRegion findByName(String name)
		throws NoSuchSupportRegionException;

	/**
	 * Returns the support region where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching support region, or <code>null</code> if a matching support region could not be found
	 */
	public SupportRegion fetchByName(String name);

	/**
	 * Returns the support region where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching support region, or <code>null</code> if a matching support region could not be found
	 */
	public SupportRegion fetchByName(String name, boolean useFinderCache);

	/**
	 * Removes the support region where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the support region that was removed
	 */
	public SupportRegion removeByName(String name)
		throws NoSuchSupportRegionException;

	/**
	 * Returns the number of support regions where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching support regions
	 */
	public int countByName(String name);

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
	 * Returns the support region with the primary key or throws a <code>NoSuchSupportRegionException</code> if it could not be found.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportRegionModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportRegionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of support regions
	 */
	public java.util.List<SupportRegion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportRegion>
			orderByComparator);

	/**
	 * Returns an ordered range of all the support regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportRegionModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of support regions
	 */
	public java.util.List<SupportRegion> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SupportRegion>
			orderByComparator,
		boolean useFinderCache);

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
	public java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
		getAccountEntries(long pk);

	/**
	 * Returns a range of all the account entries associated with the support region.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportRegionModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the support region
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @return the range of account entries associated with the support region
	 */
	public java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
		getAccountEntries(long pk, int start, int end);

	/**
	 * Returns an ordered range of all the account entries associated with the support region.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>SupportRegionModelImpl</code>.
	 * </p>
	 *
	 * @param pk the primary key of the support region
	 * @param start the lower bound of the range of support regions
	 * @param end the upper bound of the range of support regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account entries associated with the support region
	 */
	public java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
		getAccountEntries(
			long pk, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<com.liferay.osb.customer.admin.model.AccountEntry>
					orderByComparator);

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
	public void addAccountEntry(
		long pk,
		com.liferay.osb.customer.admin.model.AccountEntry accountEntry);

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
	public void addAccountEntries(
		long pk,
		java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
			accountEntries);

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
	public void removeAccountEntry(
		long pk,
		com.liferay.osb.customer.admin.model.AccountEntry accountEntry);

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
	public void removeAccountEntries(
		long pk,
		java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
			accountEntries);

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
	public void setAccountEntries(
		long pk,
		java.util.List<com.liferay.osb.customer.admin.model.AccountEntry>
			accountEntries);

}