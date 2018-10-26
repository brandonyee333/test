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

import com.liferay.osb.customer.zendesk.documentation.sync.exception.NoSuchZendeskArticleException;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * The persistence interface for the zendesk article service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.osb.customer.zendesk.documentation.sync.service.persistence.impl.ZendeskArticlePersistenceImpl
 * @see ZendeskArticleUtil
 * @generated
 */
@ProviderType
public interface ZendeskArticlePersistence extends BasePersistence<ZendeskArticle> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ZendeskArticleUtil} to access the zendesk article persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the zendesk articles where zendeskCategoryId = &#63;.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @return the matching zendesk articles
	*/
	public java.util.List<ZendeskArticle> findByZendeskCategoryId(
		long zendeskCategoryId);

	/**
	* Returns a range of all the zendesk articles where zendeskCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param start the lower bound of the range of zendesk articles
	* @param end the upper bound of the range of zendesk articles (not inclusive)
	* @return the range of matching zendesk articles
	*/
	public java.util.List<ZendeskArticle> findByZendeskCategoryId(
		long zendeskCategoryId, int start, int end);

	/**
	* Returns an ordered range of all the zendesk articles where zendeskCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param start the lower bound of the range of zendesk articles
	* @param end the upper bound of the range of zendesk articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching zendesk articles
	*/
	public java.util.List<ZendeskArticle> findByZendeskCategoryId(
		long zendeskCategoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskArticle> orderByComparator);

	/**
	* Returns an ordered range of all the zendesk articles where zendeskCategoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param start the lower bound of the range of zendesk articles
	* @param end the upper bound of the range of zendesk articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching zendesk articles
	*/
	public java.util.List<ZendeskArticle> findByZendeskCategoryId(
		long zendeskCategoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskArticle> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first zendesk article in the ordered set where zendeskCategoryId = &#63;.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching zendesk article
	* @throws NoSuchZendeskArticleException if a matching zendesk article could not be found
	*/
	public ZendeskArticle findByZendeskCategoryId_First(
		long zendeskCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskArticle> orderByComparator)
		throws NoSuchZendeskArticleException;

	/**
	* Returns the first zendesk article in the ordered set where zendeskCategoryId = &#63;.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	*/
	public ZendeskArticle fetchByZendeskCategoryId_First(
		long zendeskCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskArticle> orderByComparator);

	/**
	* Returns the last zendesk article in the ordered set where zendeskCategoryId = &#63;.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching zendesk article
	* @throws NoSuchZendeskArticleException if a matching zendesk article could not be found
	*/
	public ZendeskArticle findByZendeskCategoryId_Last(long zendeskCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskArticle> orderByComparator)
		throws NoSuchZendeskArticleException;

	/**
	* Returns the last zendesk article in the ordered set where zendeskCategoryId = &#63;.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	*/
	public ZendeskArticle fetchByZendeskCategoryId_Last(
		long zendeskCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskArticle> orderByComparator);

	/**
	* Returns the zendesk articles before and after the current zendesk article in the ordered set where zendeskCategoryId = &#63;.
	*
	* @param zendeskArticleId the primary key of the current zendesk article
	* @param zendeskCategoryId the zendesk category ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next zendesk article
	* @throws NoSuchZendeskArticleException if a zendesk article with the primary key could not be found
	*/
	public ZendeskArticle[] findByZendeskCategoryId_PrevAndNext(
		long zendeskArticleId, long zendeskCategoryId,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskArticle> orderByComparator)
		throws NoSuchZendeskArticleException;

	/**
	* Removes all the zendesk articles where zendeskCategoryId = &#63; from the database.
	*
	* @param zendeskCategoryId the zendesk category ID
	*/
	public void removeByZendeskCategoryId(long zendeskCategoryId);

	/**
	* Returns the number of zendesk articles where zendeskCategoryId = &#63;.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @return the number of matching zendesk articles
	*/
	public int countByZendeskCategoryId(long zendeskCategoryId);

	/**
	* Returns all the zendesk articles where zendeskSectionId = &#63;.
	*
	* @param zendeskSectionId the zendesk section ID
	* @return the matching zendesk articles
	*/
	public java.util.List<ZendeskArticle> findByZendeskSectionId(
		long zendeskSectionId);

	/**
	* Returns a range of all the zendesk articles where zendeskSectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zendeskSectionId the zendesk section ID
	* @param start the lower bound of the range of zendesk articles
	* @param end the upper bound of the range of zendesk articles (not inclusive)
	* @return the range of matching zendesk articles
	*/
	public java.util.List<ZendeskArticle> findByZendeskSectionId(
		long zendeskSectionId, int start, int end);

	/**
	* Returns an ordered range of all the zendesk articles where zendeskSectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zendeskSectionId the zendesk section ID
	* @param start the lower bound of the range of zendesk articles
	* @param end the upper bound of the range of zendesk articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching zendesk articles
	*/
	public java.util.List<ZendeskArticle> findByZendeskSectionId(
		long zendeskSectionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskArticle> orderByComparator);

	/**
	* Returns an ordered range of all the zendesk articles where zendeskSectionId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zendeskSectionId the zendesk section ID
	* @param start the lower bound of the range of zendesk articles
	* @param end the upper bound of the range of zendesk articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching zendesk articles
	*/
	public java.util.List<ZendeskArticle> findByZendeskSectionId(
		long zendeskSectionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskArticle> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first zendesk article in the ordered set where zendeskSectionId = &#63;.
	*
	* @param zendeskSectionId the zendesk section ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching zendesk article
	* @throws NoSuchZendeskArticleException if a matching zendesk article could not be found
	*/
	public ZendeskArticle findByZendeskSectionId_First(long zendeskSectionId,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskArticle> orderByComparator)
		throws NoSuchZendeskArticleException;

	/**
	* Returns the first zendesk article in the ordered set where zendeskSectionId = &#63;.
	*
	* @param zendeskSectionId the zendesk section ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	*/
	public ZendeskArticle fetchByZendeskSectionId_First(long zendeskSectionId,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskArticle> orderByComparator);

	/**
	* Returns the last zendesk article in the ordered set where zendeskSectionId = &#63;.
	*
	* @param zendeskSectionId the zendesk section ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching zendesk article
	* @throws NoSuchZendeskArticleException if a matching zendesk article could not be found
	*/
	public ZendeskArticle findByZendeskSectionId_Last(long zendeskSectionId,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskArticle> orderByComparator)
		throws NoSuchZendeskArticleException;

	/**
	* Returns the last zendesk article in the ordered set where zendeskSectionId = &#63;.
	*
	* @param zendeskSectionId the zendesk section ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	*/
	public ZendeskArticle fetchByZendeskSectionId_Last(long zendeskSectionId,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskArticle> orderByComparator);

	/**
	* Returns the zendesk articles before and after the current zendesk article in the ordered set where zendeskSectionId = &#63;.
	*
	* @param zendeskArticleId the primary key of the current zendesk article
	* @param zendeskSectionId the zendesk section ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next zendesk article
	* @throws NoSuchZendeskArticleException if a zendesk article with the primary key could not be found
	*/
	public ZendeskArticle[] findByZendeskSectionId_PrevAndNext(
		long zendeskArticleId, long zendeskSectionId,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskArticle> orderByComparator)
		throws NoSuchZendeskArticleException;

	/**
	* Removes all the zendesk articles where zendeskSectionId = &#63; from the database.
	*
	* @param zendeskSectionId the zendesk section ID
	*/
	public void removeByZendeskSectionId(long zendeskSectionId);

	/**
	* Returns the number of zendesk articles where zendeskSectionId = &#63;.
	*
	* @param zendeskSectionId the zendesk section ID
	* @return the number of matching zendesk articles
	*/
	public int countByZendeskSectionId(long zendeskSectionId);

	/**
	* Returns the zendesk article where documentationOriginalURL = &#63; or throws a {@link NoSuchZendeskArticleException} if it could not be found.
	*
	* @param documentationOriginalURL the documentation original url
	* @return the matching zendesk article
	* @throws NoSuchZendeskArticleException if a matching zendesk article could not be found
	*/
	public ZendeskArticle findByDocumentationOriginalURL(
		String documentationOriginalURL) throws NoSuchZendeskArticleException;

	/**
	* Returns the zendesk article where documentationOriginalURL = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param documentationOriginalURL the documentation original url
	* @return the matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	*/
	public ZendeskArticle fetchByDocumentationOriginalURL(
		String documentationOriginalURL);

	/**
	* Returns the zendesk article where documentationOriginalURL = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param documentationOriginalURL the documentation original url
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	*/
	public ZendeskArticle fetchByDocumentationOriginalURL(
		String documentationOriginalURL, boolean retrieveFromCache);

	/**
	* Removes the zendesk article where documentationOriginalURL = &#63; from the database.
	*
	* @param documentationOriginalURL the documentation original url
	* @return the zendesk article that was removed
	*/
	public ZendeskArticle removeByDocumentationOriginalURL(
		String documentationOriginalURL) throws NoSuchZendeskArticleException;

	/**
	* Returns the number of zendesk articles where documentationOriginalURL = &#63;.
	*
	* @param documentationOriginalURL the documentation original url
	* @return the number of matching zendesk articles
	*/
	public int countByDocumentationOriginalURL(String documentationOriginalURL);

	/**
	* Returns the zendesk article where zendeskCategoryId = &#63; and documentationKey = &#63; or throws a {@link NoSuchZendeskArticleException} if it could not be found.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param documentationKey the documentation key
	* @return the matching zendesk article
	* @throws NoSuchZendeskArticleException if a matching zendesk article could not be found
	*/
	public ZendeskArticle findByZCI_DK(long zendeskCategoryId,
		String documentationKey) throws NoSuchZendeskArticleException;

	/**
	* Returns the zendesk article where zendeskCategoryId = &#63; and documentationKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param documentationKey the documentation key
	* @return the matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	*/
	public ZendeskArticle fetchByZCI_DK(long zendeskCategoryId,
		String documentationKey);

	/**
	* Returns the zendesk article where zendeskCategoryId = &#63; and documentationKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param documentationKey the documentation key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	*/
	public ZendeskArticle fetchByZCI_DK(long zendeskCategoryId,
		String documentationKey, boolean retrieveFromCache);

	/**
	* Removes the zendesk article where zendeskCategoryId = &#63; and documentationKey = &#63; from the database.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param documentationKey the documentation key
	* @return the zendesk article that was removed
	*/
	public ZendeskArticle removeByZCI_DK(long zendeskCategoryId,
		String documentationKey) throws NoSuchZendeskArticleException;

	/**
	* Returns the number of zendesk articles where zendeskCategoryId = &#63; and documentationKey = &#63;.
	*
	* @param zendeskCategoryId the zendesk category ID
	* @param documentationKey the documentation key
	* @return the number of matching zendesk articles
	*/
	public int countByZCI_DK(long zendeskCategoryId, String documentationKey);

	/**
	* Caches the zendesk article in the entity cache if it is enabled.
	*
	* @param zendeskArticle the zendesk article
	*/
	public void cacheResult(ZendeskArticle zendeskArticle);

	/**
	* Caches the zendesk articles in the entity cache if it is enabled.
	*
	* @param zendeskArticles the zendesk articles
	*/
	public void cacheResult(java.util.List<ZendeskArticle> zendeskArticles);

	/**
	* Creates a new zendesk article with the primary key. Does not add the zendesk article to the database.
	*
	* @param zendeskArticleId the primary key for the new zendesk article
	* @return the new zendesk article
	*/
	public ZendeskArticle create(long zendeskArticleId);

	/**
	* Removes the zendesk article with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param zendeskArticleId the primary key of the zendesk article
	* @return the zendesk article that was removed
	* @throws NoSuchZendeskArticleException if a zendesk article with the primary key could not be found
	*/
	public ZendeskArticle remove(long zendeskArticleId)
		throws NoSuchZendeskArticleException;

	public ZendeskArticle updateImpl(ZendeskArticle zendeskArticle);

	/**
	* Returns the zendesk article with the primary key or throws a {@link NoSuchZendeskArticleException} if it could not be found.
	*
	* @param zendeskArticleId the primary key of the zendesk article
	* @return the zendesk article
	* @throws NoSuchZendeskArticleException if a zendesk article with the primary key could not be found
	*/
	public ZendeskArticle findByPrimaryKey(long zendeskArticleId)
		throws NoSuchZendeskArticleException;

	/**
	* Returns the zendesk article with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param zendeskArticleId the primary key of the zendesk article
	* @return the zendesk article, or <code>null</code> if a zendesk article with the primary key could not be found
	*/
	public ZendeskArticle fetchByPrimaryKey(long zendeskArticleId);

	@Override
	public java.util.Map<java.io.Serializable, ZendeskArticle> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the zendesk articles.
	*
	* @return the zendesk articles
	*/
	public java.util.List<ZendeskArticle> findAll();

	/**
	* Returns a range of all the zendesk articles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zendesk articles
	* @param end the upper bound of the range of zendesk articles (not inclusive)
	* @return the range of zendesk articles
	*/
	public java.util.List<ZendeskArticle> findAll(int start, int end);

	/**
	* Returns an ordered range of all the zendesk articles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zendesk articles
	* @param end the upper bound of the range of zendesk articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of zendesk articles
	*/
	public java.util.List<ZendeskArticle> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskArticle> orderByComparator);

	/**
	* Returns an ordered range of all the zendesk articles.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zendesk articles
	* @param end the upper bound of the range of zendesk articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of zendesk articles
	*/
	public java.util.List<ZendeskArticle> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<ZendeskArticle> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the zendesk articles from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of zendesk articles.
	*
	* @return the number of zendesk articles
	*/
	public int countAll();
}