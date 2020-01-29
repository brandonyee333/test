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

import com.liferay.osb.customer.admin.exception.NoSuchProductEntryException;
import com.liferay.osb.customer.admin.model.ProductEntry;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the product entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProductEntryUtil
 * @generated
 */
@ProviderType
public interface ProductEntryPersistence extends BasePersistence<ProductEntry> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ProductEntryUtil} to access the product entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, ProductEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the product entry where koroneikiProductKey = &#63; or throws a <code>NoSuchProductEntryException</code> if it could not be found.
	 *
	 * @param koroneikiProductKey the koroneiki product key
	 * @return the matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	public ProductEntry findByKoroneikiProductKey(String koroneikiProductKey)
		throws NoSuchProductEntryException;

	/**
	 * Returns the product entry where koroneikiProductKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param koroneikiProductKey the koroneiki product key
	 * @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public ProductEntry fetchByKoroneikiProductKey(String koroneikiProductKey);

	/**
	 * Returns the product entry where koroneikiProductKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param koroneikiProductKey the koroneiki product key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public ProductEntry fetchByKoroneikiProductKey(
		String koroneikiProductKey, boolean useFinderCache);

	/**
	 * Removes the product entry where koroneikiProductKey = &#63; from the database.
	 *
	 * @param koroneikiProductKey the koroneiki product key
	 * @return the product entry that was removed
	 */
	public ProductEntry removeByKoroneikiProductKey(String koroneikiProductKey)
		throws NoSuchProductEntryException;

	/**
	 * Returns the number of product entries where koroneikiProductKey = &#63;.
	 *
	 * @param koroneikiProductKey the koroneiki product key
	 * @return the number of matching product entries
	 */
	public int countByKoroneikiProductKey(String koroneikiProductKey);

	/**
	 * Returns the product entry where name = &#63; or throws a <code>NoSuchProductEntryException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	public ProductEntry findByName(String name)
		throws NoSuchProductEntryException;

	/**
	 * Returns the product entry where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public ProductEntry fetchByName(String name);

	/**
	 * Returns the product entry where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public ProductEntry fetchByName(String name, boolean useFinderCache);

	/**
	 * Removes the product entry where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the product entry that was removed
	 */
	public ProductEntry removeByName(String name)
		throws NoSuchProductEntryException;

	/**
	 * Returns the number of product entries where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching product entries
	 */
	public int countByName(String name);

	/**
	 * Returns all the product entries where environment = &#63;.
	 *
	 * @param environment the environment
	 * @return the matching product entries
	 */
	public java.util.List<ProductEntry> findByEnvironment(int environment);

	/**
	 * Returns a range of all the product entries where environment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param environment the environment
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @return the range of matching product entries
	 */
	public java.util.List<ProductEntry> findByEnvironment(
		int environment, int start, int end);

	/**
	 * Returns an ordered range of all the product entries where environment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param environment the environment
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching product entries
	 */
	public java.util.List<ProductEntry> findByEnvironment(
		int environment, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the product entries where environment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param environment the environment
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching product entries
	 */
	public java.util.List<ProductEntry> findByEnvironment(
		int environment, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first product entry in the ordered set where environment = &#63;.
	 *
	 * @param environment the environment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	public ProductEntry findByEnvironment_First(
			int environment,
			com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
				orderByComparator)
		throws NoSuchProductEntryException;

	/**
	 * Returns the first product entry in the ordered set where environment = &#63;.
	 *
	 * @param environment the environment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public ProductEntry fetchByEnvironment_First(
		int environment,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator);

	/**
	 * Returns the last product entry in the ordered set where environment = &#63;.
	 *
	 * @param environment the environment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product entry
	 * @throws NoSuchProductEntryException if a matching product entry could not be found
	 */
	public ProductEntry findByEnvironment_Last(
			int environment,
			com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
				orderByComparator)
		throws NoSuchProductEntryException;

	/**
	 * Returns the last product entry in the ordered set where environment = &#63;.
	 *
	 * @param environment the environment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching product entry, or <code>null</code> if a matching product entry could not be found
	 */
	public ProductEntry fetchByEnvironment_Last(
		int environment,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator);

	/**
	 * Returns the product entries before and after the current product entry in the ordered set where environment = &#63;.
	 *
	 * @param productEntryId the primary key of the current product entry
	 * @param environment the environment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next product entry
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	public ProductEntry[] findByEnvironment_PrevAndNext(
			long productEntryId, int environment,
			com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
				orderByComparator)
		throws NoSuchProductEntryException;

	/**
	 * Removes all the product entries where environment = &#63; from the database.
	 *
	 * @param environment the environment
	 */
	public void removeByEnvironment(int environment);

	/**
	 * Returns the number of product entries where environment = &#63;.
	 *
	 * @param environment the environment
	 * @return the number of matching product entries
	 */
	public int countByEnvironment(int environment);

	/**
	 * Caches the product entry in the entity cache if it is enabled.
	 *
	 * @param productEntry the product entry
	 */
	public void cacheResult(ProductEntry productEntry);

	/**
	 * Caches the product entries in the entity cache if it is enabled.
	 *
	 * @param productEntries the product entries
	 */
	public void cacheResult(java.util.List<ProductEntry> productEntries);

	/**
	 * Creates a new product entry with the primary key. Does not add the product entry to the database.
	 *
	 * @param productEntryId the primary key for the new product entry
	 * @return the new product entry
	 */
	public ProductEntry create(long productEntryId);

	/**
	 * Removes the product entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param productEntryId the primary key of the product entry
	 * @return the product entry that was removed
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	public ProductEntry remove(long productEntryId)
		throws NoSuchProductEntryException;

	public ProductEntry updateImpl(ProductEntry productEntry);

	/**
	 * Returns the product entry with the primary key or throws a <code>NoSuchProductEntryException</code> if it could not be found.
	 *
	 * @param productEntryId the primary key of the product entry
	 * @return the product entry
	 * @throws NoSuchProductEntryException if a product entry with the primary key could not be found
	 */
	public ProductEntry findByPrimaryKey(long productEntryId)
		throws NoSuchProductEntryException;

	/**
	 * Returns the product entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param productEntryId the primary key of the product entry
	 * @return the product entry, or <code>null</code> if a product entry with the primary key could not be found
	 */
	public ProductEntry fetchByPrimaryKey(long productEntryId);

	/**
	 * Returns all the product entries.
	 *
	 * @return the product entries
	 */
	public java.util.List<ProductEntry> findAll();

	/**
	 * Returns a range of all the product entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @return the range of product entries
	 */
	public java.util.List<ProductEntry> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the product entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of product entries
	 */
	public java.util.List<ProductEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator);

	/**
	 * Returns an ordered range of all the product entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ProductEntryModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of product entries
	 * @param end the upper bound of the range of product entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of product entries
	 */
	public java.util.List<ProductEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ProductEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the product entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of product entries.
	 *
	 * @return the number of product entries
	 */
	public int countAll();

}