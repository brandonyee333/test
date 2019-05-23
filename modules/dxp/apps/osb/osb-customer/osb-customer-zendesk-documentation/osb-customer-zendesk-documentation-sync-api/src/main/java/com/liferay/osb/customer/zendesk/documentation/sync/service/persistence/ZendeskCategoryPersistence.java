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

package com.liferay.osb.customer.zendesk.documentation.sync.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.zendesk.documentation.sync.exception.NoSuchZendeskCategoryException;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the zendesk category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskCategoryUtil
 * @generated
 */
@ProviderType
public interface ZendeskCategoryPersistence
	extends BasePersistence<ZendeskCategory> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ZendeskCategoryUtil} to access the zendesk category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, ZendeskCategory> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns the zendesk category where documentationKey = &#63; or throws a <code>NoSuchZendeskCategoryException</code> if it could not be found.
	 *
	 * @param documentationKey the documentation key
	 * @return the matching zendesk category
	 * @throws NoSuchZendeskCategoryException if a matching zendesk category could not be found
	 */
	public ZendeskCategory findByDocumentationKey(String documentationKey)
		throws NoSuchZendeskCategoryException;

	/**
	 * Returns the zendesk category where documentationKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param documentationKey the documentation key
	 * @return the matching zendesk category, or <code>null</code> if a matching zendesk category could not be found
	 */
	public ZendeskCategory fetchByDocumentationKey(String documentationKey);

	/**
	 * Returns the zendesk category where documentationKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param documentationKey the documentation key
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the matching zendesk category, or <code>null</code> if a matching zendesk category could not be found
	 */
	public ZendeskCategory fetchByDocumentationKey(
		String documentationKey, boolean retrieveFromCache);

	/**
	 * Removes the zendesk category where documentationKey = &#63; from the database.
	 *
	 * @param documentationKey the documentation key
	 * @return the zendesk category that was removed
	 */
	public ZendeskCategory removeByDocumentationKey(String documentationKey)
		throws NoSuchZendeskCategoryException;

	/**
	 * Returns the number of zendesk categories where documentationKey = &#63;.
	 *
	 * @param documentationKey the documentation key
	 * @return the number of matching zendesk categories
	 */
	public int countByDocumentationKey(String documentationKey);

	/**
	 * Caches the zendesk category in the entity cache if it is enabled.
	 *
	 * @param zendeskCategory the zendesk category
	 */
	public void cacheResult(ZendeskCategory zendeskCategory);

	/**
	 * Caches the zendesk categories in the entity cache if it is enabled.
	 *
	 * @param zendeskCategories the zendesk categories
	 */
	public void cacheResult(java.util.List<ZendeskCategory> zendeskCategories);

	/**
	 * Creates a new zendesk category with the primary key. Does not add the zendesk category to the database.
	 *
	 * @param zendeskCategoryId the primary key for the new zendesk category
	 * @return the new zendesk category
	 */
	public ZendeskCategory create(long zendeskCategoryId);

	/**
	 * Removes the zendesk category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param zendeskCategoryId the primary key of the zendesk category
	 * @return the zendesk category that was removed
	 * @throws NoSuchZendeskCategoryException if a zendesk category with the primary key could not be found
	 */
	public ZendeskCategory remove(long zendeskCategoryId)
		throws NoSuchZendeskCategoryException;

	public ZendeskCategory updateImpl(ZendeskCategory zendeskCategory);

	/**
	 * Returns the zendesk category with the primary key or throws a <code>NoSuchZendeskCategoryException</code> if it could not be found.
	 *
	 * @param zendeskCategoryId the primary key of the zendesk category
	 * @return the zendesk category
	 * @throws NoSuchZendeskCategoryException if a zendesk category with the primary key could not be found
	 */
	public ZendeskCategory findByPrimaryKey(long zendeskCategoryId)
		throws NoSuchZendeskCategoryException;

	/**
	 * Returns the zendesk category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param zendeskCategoryId the primary key of the zendesk category
	 * @return the zendesk category, or <code>null</code> if a zendesk category with the primary key could not be found
	 */
	public ZendeskCategory fetchByPrimaryKey(long zendeskCategoryId);

	/**
	 * Returns all the zendesk categories.
	 *
	 * @return the zendesk categories
	 */
	public java.util.List<ZendeskCategory> findAll();

	/**
	 * Returns a range of all the zendesk categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ZendeskCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk categories
	 * @param end the upper bound of the range of zendesk categories (not inclusive)
	 * @return the range of zendesk categories
	 */
	public java.util.List<ZendeskCategory> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the zendesk categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ZendeskCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk categories
	 * @param end the upper bound of the range of zendesk categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of zendesk categories
	 */
	public java.util.List<ZendeskCategory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskCategory>
			orderByComparator);

	/**
	 * Returns an ordered range of all the zendesk categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>ZendeskCategoryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk categories
	 * @param end the upper bound of the range of zendesk categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of zendesk categories
	 */
	public java.util.List<ZendeskCategory> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskCategory>
			orderByComparator,
		boolean retrieveFromCache);

	/**
	 * Removes all the zendesk categories from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of zendesk categories.
	 *
	 * @return the number of zendesk categories
	 */
	public int countAll();

}