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

import com.liferay.osb.customer.admin.exception.NoSuchLicenseEntryException;
import com.liferay.osb.customer.admin.model.LicenseEntry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the license entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LicenseEntryUtil
 * @generated
 */
@ProviderType
public interface LicenseEntryPersistence extends BasePersistence<LicenseEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link LicenseEntryUtil} to access the license entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, LicenseEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the license entries where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the matching license entries
	 */
	public java.util.List<LicenseEntry> findByProductEntryId(
		long productEntryId);

	/**
	 * Returns a range of all the license entries where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of matching license entries
	 */
	public java.util.List<LicenseEntry> findByProductEntryId(
		long productEntryId, int start, int end);

	/**
	 * Returns an ordered range of all the license entries where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license entries
	 */
	public java.util.List<LicenseEntry> findByProductEntryId(
		long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the license entries where productEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license entries
	 */
	public java.util.List<LicenseEntry> findByProductEntryId(
		long productEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public LicenseEntry findByProductEntryId_First(
			long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
				orderByComparator)
		throws NoSuchLicenseEntryException;

	/**
	 * Returns the first license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public LicenseEntry fetchByProductEntryId_First(
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
			orderByComparator);

	/**
	 * Returns the last license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public LicenseEntry findByProductEntryId_Last(
			long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
				orderByComparator)
		throws NoSuchLicenseEntryException;

	/**
	 * Returns the last license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public LicenseEntry fetchByProductEntryId_Last(
		long productEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
			orderByComparator);

	/**
	 * Returns the license entries before and after the current license entry in the ordered set where productEntryId = &#63;.
	 *
	 * @param licenseEntryId the primary key of the current license entry
	 * @param productEntryId the product entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	public LicenseEntry[] findByProductEntryId_PrevAndNext(
			long licenseEntryId, long productEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
				orderByComparator)
		throws NoSuchLicenseEntryException;

	/**
	 * Removes all the license entries where productEntryId = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 */
	public void removeByProductEntryId(long productEntryId);

	/**
	 * Returns the number of license entries where productEntryId = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @return the number of matching license entries
	 */
	public int countByProductEntryId(long productEntryId);

	/**
	 * Returns the license entry where productEntryId = &#63; and type = &#63; or throws a <code>NoSuchLicenseEntryException</code> if it could not be found.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @return the matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public LicenseEntry findByPEI_T(long productEntryId, String type)
		throws NoSuchLicenseEntryException;

	/**
	 * Returns the license entry where productEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public LicenseEntry fetchByPEI_T(long productEntryId, String type);

	/**
	 * Returns the license entry where productEntryId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public LicenseEntry fetchByPEI_T(
		long productEntryId, String type, boolean useFinderCache);

	/**
	 * Removes the license entry where productEntryId = &#63; and type = &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @return the license entry that was removed
	 */
	public LicenseEntry removeByPEI_T(long productEntryId, String type)
		throws NoSuchLicenseEntryException;

	/**
	 * Returns the number of license entries where productEntryId = &#63; and type = &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param type the type
	 * @return the number of matching license entries
	 */
	public int countByPEI_T(long productEntryId, String type);

	/**
	 * Returns all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @return the matching license entries
	 */
	public java.util.List<LicenseEntry> findByPEI_PV(
		long productEntryId, int portalVersionMin);

	/**
	 * Returns a range of all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of matching license entries
	 */
	public java.util.List<LicenseEntry> findByPEI_PV(
		long productEntryId, int portalVersionMin, int start, int end);

	/**
	 * Returns an ordered range of all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching license entries
	 */
	public java.util.List<LicenseEntry> findByPEI_PV(
		long productEntryId, int portalVersionMin, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching license entries
	 */
	public java.util.List<LicenseEntry> findByPEI_PV(
		long productEntryId, int portalVersionMin, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public LicenseEntry findByPEI_PV_First(
			long productEntryId, int portalVersionMin,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
				orderByComparator)
		throws NoSuchLicenseEntryException;

	/**
	 * Returns the first license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public LicenseEntry fetchByPEI_PV_First(
		long productEntryId, int portalVersionMin,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
			orderByComparator);

	/**
	 * Returns the last license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry
	 * @throws NoSuchLicenseEntryException if a matching license entry could not be found
	 */
	public LicenseEntry findByPEI_PV_Last(
			long productEntryId, int portalVersionMin,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
				orderByComparator)
		throws NoSuchLicenseEntryException;

	/**
	 * Returns the last license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching license entry, or <code>null</code> if a matching license entry could not be found
	 */
	public LicenseEntry fetchByPEI_PV_Last(
		long productEntryId, int portalVersionMin,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
			orderByComparator);

	/**
	 * Returns the license entries before and after the current license entry in the ordered set where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param licenseEntryId the primary key of the current license entry
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	public LicenseEntry[] findByPEI_PV_PrevAndNext(
			long licenseEntryId, long productEntryId, int portalVersionMin,
			com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
				orderByComparator)
		throws NoSuchLicenseEntryException;

	/**
	 * Removes all the license entries where productEntryId = &#63; and portalVersionMin &le; &#63; from the database.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 */
	public void removeByPEI_PV(long productEntryId, int portalVersionMin);

	/**
	 * Returns the number of license entries where productEntryId = &#63; and portalVersionMin &le; &#63;.
	 *
	 * @param productEntryId the product entry ID
	 * @param portalVersionMin the portal version min
	 * @return the number of matching license entries
	 */
	public int countByPEI_PV(long productEntryId, int portalVersionMin);

	/**
	 * Caches the license entry in the entity cache if it is enabled.
	 *
	 * @param licenseEntry the license entry
	 */
	public void cacheResult(LicenseEntry licenseEntry);

	/**
	 * Caches the license entries in the entity cache if it is enabled.
	 *
	 * @param licenseEntries the license entries
	 */
	public void cacheResult(java.util.List<LicenseEntry> licenseEntries);

	/**
	 * Creates a new license entry with the primary key. Does not add the license entry to the database.
	 *
	 * @param licenseEntryId the primary key for the new license entry
	 * @return the new license entry
	 */
	public LicenseEntry create(long licenseEntryId);

	/**
	 * Removes the license entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry that was removed
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	public LicenseEntry remove(long licenseEntryId)
		throws NoSuchLicenseEntryException;

	public LicenseEntry updateImpl(LicenseEntry licenseEntry);

	/**
	 * Returns the license entry with the primary key or throws a <code>NoSuchLicenseEntryException</code> if it could not be found.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry
	 * @throws NoSuchLicenseEntryException if a license entry with the primary key could not be found
	 */
	public LicenseEntry findByPrimaryKey(long licenseEntryId)
		throws NoSuchLicenseEntryException;

	/**
	 * Returns the license entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param licenseEntryId the primary key of the license entry
	 * @return the license entry, or <code>null</code> if a license entry with the primary key could not be found
	 */
	public LicenseEntry fetchByPrimaryKey(long licenseEntryId);

	/**
	 * Returns all the license entries.
	 *
	 * @return the license entries
	 */
	public java.util.List<LicenseEntry> findAll();

	/**
	 * Returns a range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @return the range of license entries
	 */
	public java.util.List<LicenseEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of license entries
	 */
	public java.util.List<LicenseEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the license entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>LicenseEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of license entries
	 * @param end the upper bound of the range of license entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of license entries
	 */
	public java.util.List<LicenseEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<LicenseEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the license entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of license entries.
	 *
	 * @return the number of license entries
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}