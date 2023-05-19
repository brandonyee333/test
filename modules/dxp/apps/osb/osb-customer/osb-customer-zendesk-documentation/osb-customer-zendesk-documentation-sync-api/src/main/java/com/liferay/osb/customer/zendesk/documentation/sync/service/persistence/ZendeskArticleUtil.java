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

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticle;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the zendesk article service. This utility wraps <code>com.liferay.osb.customer.zendesk.documentation.sync.service.persistence.impl.ZendeskArticlePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticlePersistence
 * @generated
 */
public class ZendeskArticleUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(ZendeskArticle zendeskArticle) {
		getPersistence().clearCache(zendeskArticle);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, ZendeskArticle> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ZendeskArticle> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ZendeskArticle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ZendeskArticle> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ZendeskArticle> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ZendeskArticle update(ZendeskArticle zendeskArticle) {
		return getPersistence().update(zendeskArticle);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ZendeskArticle update(
		ZendeskArticle zendeskArticle, ServiceContext serviceContext) {

		return getPersistence().update(zendeskArticle, serviceContext);
	}

	/**
	 * Returns all the zendesk articles where zendeskCategoryId = &#63;.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @return the matching zendesk articles
	 */
	public static List<ZendeskArticle> findByZendeskCategoryId(
		long zendeskCategoryId) {

		return getPersistence().findByZendeskCategoryId(zendeskCategoryId);
	}

	/**
	 * Returns a range of all the zendesk articles where zendeskCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ZendeskArticleModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param start the lower bound of the range of zendesk articles
	 * @param end the upper bound of the range of zendesk articles (not inclusive)
	 * @return the range of matching zendesk articles
	 */
	public static List<ZendeskArticle> findByZendeskCategoryId(
		long zendeskCategoryId, int start, int end) {

		return getPersistence().findByZendeskCategoryId(
			zendeskCategoryId, start, end);
	}

	/**
	 * Returns an ordered range of all the zendesk articles where zendeskCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ZendeskArticleModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param start the lower bound of the range of zendesk articles
	 * @param end the upper bound of the range of zendesk articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching zendesk articles
	 */
	public static List<ZendeskArticle> findByZendeskCategoryId(
		long zendeskCategoryId, int start, int end,
		OrderByComparator<ZendeskArticle> orderByComparator) {

		return getPersistence().findByZendeskCategoryId(
			zendeskCategoryId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the zendesk articles where zendeskCategoryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ZendeskArticleModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param start the lower bound of the range of zendesk articles
	 * @param end the upper bound of the range of zendesk articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching zendesk articles
	 */
	public static List<ZendeskArticle> findByZendeskCategoryId(
		long zendeskCategoryId, int start, int end,
		OrderByComparator<ZendeskArticle> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByZendeskCategoryId(
			zendeskCategoryId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first zendesk article in the ordered set where zendeskCategoryId = &#63;.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching zendesk article
	 * @throws NoSuchZendeskArticleException if a matching zendesk article could not be found
	 */
	public static ZendeskArticle findByZendeskCategoryId_First(
			long zendeskCategoryId,
			OrderByComparator<ZendeskArticle> orderByComparator)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleException {

		return getPersistence().findByZendeskCategoryId_First(
			zendeskCategoryId, orderByComparator);
	}

	/**
	 * Returns the first zendesk article in the ordered set where zendeskCategoryId = &#63;.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	 */
	public static ZendeskArticle fetchByZendeskCategoryId_First(
		long zendeskCategoryId,
		OrderByComparator<ZendeskArticle> orderByComparator) {

		return getPersistence().fetchByZendeskCategoryId_First(
			zendeskCategoryId, orderByComparator);
	}

	/**
	 * Returns the last zendesk article in the ordered set where zendeskCategoryId = &#63;.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching zendesk article
	 * @throws NoSuchZendeskArticleException if a matching zendesk article could not be found
	 */
	public static ZendeskArticle findByZendeskCategoryId_Last(
			long zendeskCategoryId,
			OrderByComparator<ZendeskArticle> orderByComparator)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleException {

		return getPersistence().findByZendeskCategoryId_Last(
			zendeskCategoryId, orderByComparator);
	}

	/**
	 * Returns the last zendesk article in the ordered set where zendeskCategoryId = &#63;.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	 */
	public static ZendeskArticle fetchByZendeskCategoryId_Last(
		long zendeskCategoryId,
		OrderByComparator<ZendeskArticle> orderByComparator) {

		return getPersistence().fetchByZendeskCategoryId_Last(
			zendeskCategoryId, orderByComparator);
	}

	/**
	 * Returns the zendesk articles before and after the current zendesk article in the ordered set where zendeskCategoryId = &#63;.
	 *
	 * @param zendeskArticleId the primary key of the current zendesk article
	 * @param zendeskCategoryId the zendesk category ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next zendesk article
	 * @throws NoSuchZendeskArticleException if a zendesk article with the primary key could not be found
	 */
	public static ZendeskArticle[] findByZendeskCategoryId_PrevAndNext(
			long zendeskArticleId, long zendeskCategoryId,
			OrderByComparator<ZendeskArticle> orderByComparator)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleException {

		return getPersistence().findByZendeskCategoryId_PrevAndNext(
			zendeskArticleId, zendeskCategoryId, orderByComparator);
	}

	/**
	 * Removes all the zendesk articles where zendeskCategoryId = &#63; from the database.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 */
	public static void removeByZendeskCategoryId(long zendeskCategoryId) {
		getPersistence().removeByZendeskCategoryId(zendeskCategoryId);
	}

	/**
	 * Returns the number of zendesk articles where zendeskCategoryId = &#63;.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @return the number of matching zendesk articles
	 */
	public static int countByZendeskCategoryId(long zendeskCategoryId) {
		return getPersistence().countByZendeskCategoryId(zendeskCategoryId);
	}

	/**
	 * Returns all the zendesk articles where zendeskSectionId = &#63;.
	 *
	 * @param zendeskSectionId the zendesk section ID
	 * @return the matching zendesk articles
	 */
	public static List<ZendeskArticle> findByZendeskSectionId(
		long zendeskSectionId) {

		return getPersistence().findByZendeskSectionId(zendeskSectionId);
	}

	/**
	 * Returns a range of all the zendesk articles where zendeskSectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ZendeskArticleModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskSectionId the zendesk section ID
	 * @param start the lower bound of the range of zendesk articles
	 * @param end the upper bound of the range of zendesk articles (not inclusive)
	 * @return the range of matching zendesk articles
	 */
	public static List<ZendeskArticle> findByZendeskSectionId(
		long zendeskSectionId, int start, int end) {

		return getPersistence().findByZendeskSectionId(
			zendeskSectionId, start, end);
	}

	/**
	 * Returns an ordered range of all the zendesk articles where zendeskSectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ZendeskArticleModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskSectionId the zendesk section ID
	 * @param start the lower bound of the range of zendesk articles
	 * @param end the upper bound of the range of zendesk articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching zendesk articles
	 */
	public static List<ZendeskArticle> findByZendeskSectionId(
		long zendeskSectionId, int start, int end,
		OrderByComparator<ZendeskArticle> orderByComparator) {

		return getPersistence().findByZendeskSectionId(
			zendeskSectionId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the zendesk articles where zendeskSectionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ZendeskArticleModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskSectionId the zendesk section ID
	 * @param start the lower bound of the range of zendesk articles
	 * @param end the upper bound of the range of zendesk articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching zendesk articles
	 */
	public static List<ZendeskArticle> findByZendeskSectionId(
		long zendeskSectionId, int start, int end,
		OrderByComparator<ZendeskArticle> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByZendeskSectionId(
			zendeskSectionId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first zendesk article in the ordered set where zendeskSectionId = &#63;.
	 *
	 * @param zendeskSectionId the zendesk section ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching zendesk article
	 * @throws NoSuchZendeskArticleException if a matching zendesk article could not be found
	 */
	public static ZendeskArticle findByZendeskSectionId_First(
			long zendeskSectionId,
			OrderByComparator<ZendeskArticle> orderByComparator)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleException {

		return getPersistence().findByZendeskSectionId_First(
			zendeskSectionId, orderByComparator);
	}

	/**
	 * Returns the first zendesk article in the ordered set where zendeskSectionId = &#63;.
	 *
	 * @param zendeskSectionId the zendesk section ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	 */
	public static ZendeskArticle fetchByZendeskSectionId_First(
		long zendeskSectionId,
		OrderByComparator<ZendeskArticle> orderByComparator) {

		return getPersistence().fetchByZendeskSectionId_First(
			zendeskSectionId, orderByComparator);
	}

	/**
	 * Returns the last zendesk article in the ordered set where zendeskSectionId = &#63;.
	 *
	 * @param zendeskSectionId the zendesk section ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching zendesk article
	 * @throws NoSuchZendeskArticleException if a matching zendesk article could not be found
	 */
	public static ZendeskArticle findByZendeskSectionId_Last(
			long zendeskSectionId,
			OrderByComparator<ZendeskArticle> orderByComparator)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleException {

		return getPersistence().findByZendeskSectionId_Last(
			zendeskSectionId, orderByComparator);
	}

	/**
	 * Returns the last zendesk article in the ordered set where zendeskSectionId = &#63;.
	 *
	 * @param zendeskSectionId the zendesk section ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	 */
	public static ZendeskArticle fetchByZendeskSectionId_Last(
		long zendeskSectionId,
		OrderByComparator<ZendeskArticle> orderByComparator) {

		return getPersistence().fetchByZendeskSectionId_Last(
			zendeskSectionId, orderByComparator);
	}

	/**
	 * Returns the zendesk articles before and after the current zendesk article in the ordered set where zendeskSectionId = &#63;.
	 *
	 * @param zendeskArticleId the primary key of the current zendesk article
	 * @param zendeskSectionId the zendesk section ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next zendesk article
	 * @throws NoSuchZendeskArticleException if a zendesk article with the primary key could not be found
	 */
	public static ZendeskArticle[] findByZendeskSectionId_PrevAndNext(
			long zendeskArticleId, long zendeskSectionId,
			OrderByComparator<ZendeskArticle> orderByComparator)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleException {

		return getPersistence().findByZendeskSectionId_PrevAndNext(
			zendeskArticleId, zendeskSectionId, orderByComparator);
	}

	/**
	 * Removes all the zendesk articles where zendeskSectionId = &#63; from the database.
	 *
	 * @param zendeskSectionId the zendesk section ID
	 */
	public static void removeByZendeskSectionId(long zendeskSectionId) {
		getPersistence().removeByZendeskSectionId(zendeskSectionId);
	}

	/**
	 * Returns the number of zendesk articles where zendeskSectionId = &#63;.
	 *
	 * @param zendeskSectionId the zendesk section ID
	 * @return the number of matching zendesk articles
	 */
	public static int countByZendeskSectionId(long zendeskSectionId) {
		return getPersistence().countByZendeskSectionId(zendeskSectionId);
	}

	/**
	 * Returns the zendesk article where documentationOriginalURL = &#63; or throws a <code>NoSuchZendeskArticleException</code> if it could not be found.
	 *
	 * @param documentationOriginalURL the documentation original url
	 * @return the matching zendesk article
	 * @throws NoSuchZendeskArticleException if a matching zendesk article could not be found
	 */
	public static ZendeskArticle findByDocumentationOriginalURL(
			String documentationOriginalURL)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleException {

		return getPersistence().findByDocumentationOriginalURL(
			documentationOriginalURL);
	}

	/**
	 * Returns the zendesk article where documentationOriginalURL = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param documentationOriginalURL the documentation original url
	 * @return the matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	 */
	public static ZendeskArticle fetchByDocumentationOriginalURL(
		String documentationOriginalURL) {

		return getPersistence().fetchByDocumentationOriginalURL(
			documentationOriginalURL);
	}

	/**
	 * Returns the zendesk article where documentationOriginalURL = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param documentationOriginalURL the documentation original url
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	 */
	public static ZendeskArticle fetchByDocumentationOriginalURL(
		String documentationOriginalURL, boolean useFinderCache) {

		return getPersistence().fetchByDocumentationOriginalURL(
			documentationOriginalURL, useFinderCache);
	}

	/**
	 * Removes the zendesk article where documentationOriginalURL = &#63; from the database.
	 *
	 * @param documentationOriginalURL the documentation original url
	 * @return the zendesk article that was removed
	 */
	public static ZendeskArticle removeByDocumentationOriginalURL(
			String documentationOriginalURL)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleException {

		return getPersistence().removeByDocumentationOriginalURL(
			documentationOriginalURL);
	}

	/**
	 * Returns the number of zendesk articles where documentationOriginalURL = &#63;.
	 *
	 * @param documentationOriginalURL the documentation original url
	 * @return the number of matching zendesk articles
	 */
	public static int countByDocumentationOriginalURL(
		String documentationOriginalURL) {

		return getPersistence().countByDocumentationOriginalURL(
			documentationOriginalURL);
	}

	/**
	 * Returns the zendesk article where zendeskCategoryId = &#63; and documentationKey = &#63; or throws a <code>NoSuchZendeskArticleException</code> if it could not be found.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param documentationKey the documentation key
	 * @return the matching zendesk article
	 * @throws NoSuchZendeskArticleException if a matching zendesk article could not be found
	 */
	public static ZendeskArticle findByZCI_DK(
			long zendeskCategoryId, String documentationKey)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleException {

		return getPersistence().findByZCI_DK(
			zendeskCategoryId, documentationKey);
	}

	/**
	 * Returns the zendesk article where zendeskCategoryId = &#63; and documentationKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param documentationKey the documentation key
	 * @return the matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	 */
	public static ZendeskArticle fetchByZCI_DK(
		long zendeskCategoryId, String documentationKey) {

		return getPersistence().fetchByZCI_DK(
			zendeskCategoryId, documentationKey);
	}

	/**
	 * Returns the zendesk article where zendeskCategoryId = &#63; and documentationKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param documentationKey the documentation key
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	 */
	public static ZendeskArticle fetchByZCI_DK(
		long zendeskCategoryId, String documentationKey,
		boolean useFinderCache) {

		return getPersistence().fetchByZCI_DK(
			zendeskCategoryId, documentationKey, useFinderCache);
	}

	/**
	 * Removes the zendesk article where zendeskCategoryId = &#63; and documentationKey = &#63; from the database.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param documentationKey the documentation key
	 * @return the zendesk article that was removed
	 */
	public static ZendeskArticle removeByZCI_DK(
			long zendeskCategoryId, String documentationKey)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleException {

		return getPersistence().removeByZCI_DK(
			zendeskCategoryId, documentationKey);
	}

	/**
	 * Returns the number of zendesk articles where zendeskCategoryId = &#63; and documentationKey = &#63;.
	 *
	 * @param zendeskCategoryId the zendesk category ID
	 * @param documentationKey the documentation key
	 * @return the number of matching zendesk articles
	 */
	public static int countByZCI_DK(
		long zendeskCategoryId, String documentationKey) {

		return getPersistence().countByZCI_DK(
			zendeskCategoryId, documentationKey);
	}

	/**
	 * Caches the zendesk article in the entity cache if it is enabled.
	 *
	 * @param zendeskArticle the zendesk article
	 */
	public static void cacheResult(ZendeskArticle zendeskArticle) {
		getPersistence().cacheResult(zendeskArticle);
	}

	/**
	 * Caches the zendesk articles in the entity cache if it is enabled.
	 *
	 * @param zendeskArticles the zendesk articles
	 */
	public static void cacheResult(List<ZendeskArticle> zendeskArticles) {
		getPersistence().cacheResult(zendeskArticles);
	}

	/**
	 * Creates a new zendesk article with the primary key. Does not add the zendesk article to the database.
	 *
	 * @param zendeskArticleId the primary key for the new zendesk article
	 * @return the new zendesk article
	 */
	public static ZendeskArticle create(long zendeskArticleId) {
		return getPersistence().create(zendeskArticleId);
	}

	/**
	 * Removes the zendesk article with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param zendeskArticleId the primary key of the zendesk article
	 * @return the zendesk article that was removed
	 * @throws NoSuchZendeskArticleException if a zendesk article with the primary key could not be found
	 */
	public static ZendeskArticle remove(long zendeskArticleId)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleException {

		return getPersistence().remove(zendeskArticleId);
	}

	public static ZendeskArticle updateImpl(ZendeskArticle zendeskArticle) {
		return getPersistence().updateImpl(zendeskArticle);
	}

	/**
	 * Returns the zendesk article with the primary key or throws a <code>NoSuchZendeskArticleException</code> if it could not be found.
	 *
	 * @param zendeskArticleId the primary key of the zendesk article
	 * @return the zendesk article
	 * @throws NoSuchZendeskArticleException if a zendesk article with the primary key could not be found
	 */
	public static ZendeskArticle findByPrimaryKey(long zendeskArticleId)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleException {

		return getPersistence().findByPrimaryKey(zendeskArticleId);
	}

	/**
	 * Returns the zendesk article with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param zendeskArticleId the primary key of the zendesk article
	 * @return the zendesk article, or <code>null</code> if a zendesk article with the primary key could not be found
	 */
	public static ZendeskArticle fetchByPrimaryKey(long zendeskArticleId) {
		return getPersistence().fetchByPrimaryKey(zendeskArticleId);
	}

	/**
	 * Returns all the zendesk articles.
	 *
	 * @return the zendesk articles
	 */
	public static List<ZendeskArticle> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the zendesk articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ZendeskArticleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk articles
	 * @param end the upper bound of the range of zendesk articles (not inclusive)
	 * @return the range of zendesk articles
	 */
	public static List<ZendeskArticle> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the zendesk articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ZendeskArticleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk articles
	 * @param end the upper bound of the range of zendesk articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of zendesk articles
	 */
	public static List<ZendeskArticle> findAll(
		int start, int end,
		OrderByComparator<ZendeskArticle> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the zendesk articles.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ZendeskArticleModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk articles
	 * @param end the upper bound of the range of zendesk articles (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of zendesk articles
	 */
	public static List<ZendeskArticle> findAll(
		int start, int end, OrderByComparator<ZendeskArticle> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the zendesk articles from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of zendesk articles.
	 *
	 * @return the number of zendesk articles
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ZendeskArticlePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(ZendeskArticlePersistence persistence) {
		_persistence = persistence;
	}

	private static volatile ZendeskArticlePersistence _persistence;

}