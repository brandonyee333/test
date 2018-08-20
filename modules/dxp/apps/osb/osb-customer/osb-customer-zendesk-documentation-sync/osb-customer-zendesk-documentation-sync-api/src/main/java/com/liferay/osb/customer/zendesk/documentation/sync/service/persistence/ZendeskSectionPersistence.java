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

import com.liferay.osb.customer.zendesk.documentation.sync.exception.NoSuchZendeskSectionException;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the zendesk section service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.customer.zendesk.documentation.sync.service.persistence.impl.ZendeskSectionPersistenceImpl
 * @see ZendeskSectionUtil
 * @generated
 */
@ProviderType
public interface ZendeskSectionPersistence extends BasePersistence<ZendeskSection> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ZendeskSectionUtil} to access the zendesk section persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the zendesk sections where zendeskCategoryId = &#63;.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @return the matching zendesk sections
	*/
	public java.util.List<ZendeskSection> findByZendeskCategoryId(
		long zendeskCategoryId);

	/**
	* Returns a range of all the zendesk sections where zendeskCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskSectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param start the lower bound of the range of zendesk sections
	* @param end the upper bound of the range of zendesk sections (not inclusive)
	* @return the range of matching zendesk sections
	*/
	public java.util.List<ZendeskSection> findByZendeskCategoryId(
		long zendeskCategoryId, int start, int end);

	/**
	* Returns an ordered range of all the zendesk sections where zendeskCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskSectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param start the lower bound of the range of zendesk sections
	* @param end the upper bound of the range of zendesk sections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching zendesk sections
	*/
	public java.util.List<ZendeskSection> findByZendeskCategoryId(
		long zendeskCategoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskSection> orderByComparator);

	/**
	* Returns an ordered range of all the zendesk sections where zendeskCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskSectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param start the lower bound of the range of zendesk sections
	* @param end the upper bound of the range of zendesk sections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching zendesk sections
	*/
	public java.util.List<ZendeskSection> findByZendeskCategoryId(
		long zendeskCategoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskSection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first zendesk section in the ordered set where zendeskCategoryId = &#63;.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching zendesk section
	* @throws NoSuchZendeskSectionException if a matching zendesk section could not be found
	*/
	public ZendeskSection findByZendeskCategoryId_First(
		long zendeskCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskSection> orderByComparator)
		throws NoSuchZendeskSectionException;

	/**
	* Returns the first zendesk section in the ordered set where zendeskCategoryId = &#63;.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching zendesk section, or <code>null</code> if a matching zendesk section could not be found
	*/
	public ZendeskSection fetchByZendeskCategoryId_First(
		long zendeskCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskSection> orderByComparator);

	/**
	* Returns the last zendesk section in the ordered set where zendeskCategoryId = &#63;.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching zendesk section
	* @throws NoSuchZendeskSectionException if a matching zendesk section could not be found
	*/
	public ZendeskSection findByZendeskCategoryId_Last(long zendeskCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskSection> orderByComparator)
		throws NoSuchZendeskSectionException;

	/**
	* Returns the last zendesk section in the ordered set where zendeskCategoryId = &#63;.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching zendesk section, or <code>null</code> if a matching zendesk section could not be found
	*/
	public ZendeskSection fetchByZendeskCategoryId_Last(
		long zendeskCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskSection> orderByComparator);

	/**
	* Returns the zendesk sections before and after the current zendesk section in the ordered set where zendeskCategoryId = &#63;.
	*
	* @param zendeskSectionId the primary key of the current zendesk section
	* @param zendeskCategoryId the zendesk category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next zendesk section
	* @throws NoSuchZendeskSectionException if a zendesk section with the primary key could not be found
	*/
	public ZendeskSection[] findByZendeskCategoryId_PrevAndNext(
		long zendeskSectionId, long zendeskCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskSection> orderByComparator)
		throws NoSuchZendeskSectionException;

	/**
	* Removes all the zendesk sections where zendeskCategoryId = &#63; from the database.
	*
	* @param zendeskCategoryId the zendesk category ID
	*/
	public void removeByZendeskCategoryId(long zendeskCategoryId);

	/**
	* Returns the number of zendesk sections where zendeskCategoryId = &#63;.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @return the number of matching zendesk sections
	*/
	public int countByZendeskCategoryId(long zendeskCategoryId);

	/**
	* Returns the zendesk section where zendeskCategoryId = &#63; and documentationKey = &#63; or throws a {@link NoSuchZendeskSectionException} if it could not be found.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param documentationKey the documentation key
	* @return the matching zendesk section
	* @throws NoSuchZendeskSectionException if a matching zendesk section could not be found
	*/
	public ZendeskSection findByZCI_DK(long zendeskCategoryId,
		String documentationKey) throws NoSuchZendeskSectionException;

	/**
	* Returns the zendesk section where zendeskCategoryId = &#63; and documentationKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param documentationKey the documentation key
	* @return the matching zendesk section, or <code>null</code> if a matching zendesk section could not be found
	*/
	public ZendeskSection fetchByZCI_DK(long zendeskCategoryId,
		String documentationKey);

	/**
	* Returns the zendesk section where zendeskCategoryId = &#63; and documentationKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param documentationKey the documentation key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching zendesk section, or <code>null</code> if a matching zendesk section could not be found
	*/
	public ZendeskSection fetchByZCI_DK(long zendeskCategoryId,
		String documentationKey, boolean retrieveFromCache);

	/**
	* Removes the zendesk section where zendeskCategoryId = &#63; and documentationKey = &#63; from the database.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param documentationKey the documentation key
	* @return the zendesk section that was removed
	*/
	public ZendeskSection removeByZCI_DK(long zendeskCategoryId,
		String documentationKey) throws NoSuchZendeskSectionException;

	/**
	* Returns the number of zendesk sections where zendeskCategoryId = &#63; and documentationKey = &#63;.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param documentationKey the documentation key
	* @return the number of matching zendesk sections
	*/
	public int countByZCI_DK(long zendeskCategoryId, String documentationKey);

	/**
	* Caches the zendesk section in the entity cache if it is enabled.
	*
	* @param zendeskSection the zendesk section
	*/
	public void cacheResult(ZendeskSection zendeskSection);

	/**
	* Caches the zendesk sections in the entity cache if it is enabled.
	*
	* @param zendeskSections the zendesk sections
	*/
	public void cacheResult(java.util.List<ZendeskSection> zendeskSections);

	/**
	* Creates a new zendesk section with the primary key. Does not add the zendesk section to the database.
	*
	* @param zendeskSectionId the primary key for the new zendesk section
	* @return the new zendesk section
	*/
	public ZendeskSection create(long zendeskSectionId);

	/**
	* Removes the zendesk section with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskSectionId the primary key of the zendesk section
	* @return the zendesk section that was removed
	* @throws NoSuchZendeskSectionException if a zendesk section with the primary key could not be found
	*/
	public ZendeskSection remove(long zendeskSectionId)
		throws NoSuchZendeskSectionException;

	public ZendeskSection updateImpl(ZendeskSection zendeskSection);

	/**
	* Returns the zendesk section with the primary key or throws a {@link NoSuchZendeskSectionException} if it could not be found.
	*
	* @param zendeskSectionId the primary key of the zendesk section
	* @return the zendesk section
	* @throws NoSuchZendeskSectionException if a zendesk section with the primary key could not be found
	*/
	public ZendeskSection findByPrimaryKey(long zendeskSectionId)
		throws NoSuchZendeskSectionException;

	/**
	* Returns the zendesk section with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param zendeskSectionId the primary key of the zendesk section
	* @return the zendesk section, or <code>null</code> if a zendesk section with the primary key could not be found
	*/
	public ZendeskSection fetchByPrimaryKey(long zendeskSectionId);

	@Override
	public java.util.Map<java.io.Serializable, ZendeskSection> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the zendesk sections.
	*
	* @return the zendesk sections
	*/
	public java.util.List<ZendeskSection> findAll();

	/**
	* Returns a range of all the zendesk sections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskSectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zendesk sections
	* @param end the upper bound of the range of zendesk sections (not inclusive)
	* @return the range of zendesk sections
	*/
	public java.util.List<ZendeskSection> findAll(int start, int end);

	/**
	* Returns an ordered range of all the zendesk sections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskSectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zendesk sections
	* @param end the upper bound of the range of zendesk sections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of zendesk sections
	*/
	public java.util.List<ZendeskSection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskSection> orderByComparator);

	/**
	* Returns an ordered range of all the zendesk sections.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskSectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zendesk sections
	* @param end the upper bound of the range of zendesk sections (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of zendesk sections
	*/
	public java.util.List<ZendeskSection> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskSection> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the zendesk sections from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of zendesk sections.
	*
	* @return the number of zendesk sections
	*/
	public int countAll();
}