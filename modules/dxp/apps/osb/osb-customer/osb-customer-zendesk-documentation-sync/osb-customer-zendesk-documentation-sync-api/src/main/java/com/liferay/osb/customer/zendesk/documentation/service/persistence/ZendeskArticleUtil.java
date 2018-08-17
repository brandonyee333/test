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

package com.liferay.osb.customer.zendesk.documentation.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.osb.customer.zendesk.documentation.model.ZendeskArticle;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

import java.util.List;

/**
 * The persistence utility for the zendesk article service. This utility wraps {@link com.liferay.osb.customer.zendesk.documentation.service.persistence.impl.ZendeskArticlePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticlePersistence
 * @see com.liferay.osb.customer.zendesk.documentation.service.persistence.impl.ZendeskArticlePersistenceImpl
 * @generated
 */
@ProviderType
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
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
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
	public static ZendeskArticle update(ZendeskArticle zendeskArticle,
		ServiceContext serviceContext) {
		return getPersistence().update(zendeskArticle, serviceContext);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param zendeskSectionId the zendesk section ID
	* @param start the lower bound of the range of zendesk articles
	* @param end the upper bound of the range of zendesk articles (not inclusive)
	* @return the range of matching zendesk articles
	*/
	public static List<ZendeskArticle> findByZendeskSectionId(
		long zendeskSectionId, int start, int end) {
		return getPersistence()
				   .findByZendeskSectionId(zendeskSectionId, start, end);
	}

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
	public static List<ZendeskArticle> findByZendeskSectionId(
		long zendeskSectionId, int start, int end,
		OrderByComparator<ZendeskArticle> orderByComparator) {
		return getPersistence()
				   .findByZendeskSectionId(zendeskSectionId, start, end,
			orderByComparator);
	}

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
	public static List<ZendeskArticle> findByZendeskSectionId(
		long zendeskSectionId, int start, int end,
		OrderByComparator<ZendeskArticle> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findByZendeskSectionId(zendeskSectionId, start, end,
			orderByComparator, retrieveFromCache);
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
		throws com.liferay.osb.customer.zendesk.documentation.exception.NoSuchZendeskArticleException {
		return getPersistence()
				   .findByZendeskSectionId_First(zendeskSectionId,
			orderByComparator);
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
		return getPersistence()
				   .fetchByZendeskSectionId_First(zendeskSectionId,
			orderByComparator);
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
		throws com.liferay.osb.customer.zendesk.documentation.exception.NoSuchZendeskArticleException {
		return getPersistence()
				   .findByZendeskSectionId_Last(zendeskSectionId,
			orderByComparator);
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
		return getPersistence()
				   .fetchByZendeskSectionId_Last(zendeskSectionId,
			orderByComparator);
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
		throws com.liferay.osb.customer.zendesk.documentation.exception.NoSuchZendeskArticleException {
		return getPersistence()
				   .findByZendeskSectionId_PrevAndNext(zendeskArticleId,
			zendeskSectionId, orderByComparator);
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
	* Returns the zendesk article where zendeskSectionId = &#63; and documentationKey = &#63; or throws a {@link NoSuchZendeskArticleException} if it could not be found.
	*
	* @param zendeskSectionId the zendesk section ID
	* @param documentationKey the documentation key
	* @return the matching zendesk article
	* @throws NoSuchZendeskArticleException if a matching zendesk article could not be found
	*/
	public static ZendeskArticle findByZSI_DK(long zendeskSectionId,
		String documentationKey)
		throws com.liferay.osb.customer.zendesk.documentation.exception.NoSuchZendeskArticleException {
		return getPersistence().findByZSI_DK(zendeskSectionId, documentationKey);
	}

	/**
	* Returns the zendesk article where zendeskSectionId = &#63; and documentationKey = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param zendeskSectionId the zendesk section ID
	* @param documentationKey the documentation key
	* @return the matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	*/
	public static ZendeskArticle fetchByZSI_DK(long zendeskSectionId,
		String documentationKey) {
		return getPersistence().fetchByZSI_DK(zendeskSectionId, documentationKey);
	}

	/**
	* Returns the zendesk article where zendeskSectionId = &#63; and documentationKey = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param zendeskSectionId the zendesk section ID
	* @param documentationKey the documentation key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching zendesk article, or <code>null</code> if a matching zendesk article could not be found
	*/
	public static ZendeskArticle fetchByZSI_DK(long zendeskSectionId,
		String documentationKey, boolean retrieveFromCache) {
		return getPersistence()
				   .fetchByZSI_DK(zendeskSectionId, documentationKey,
			retrieveFromCache);
	}

	/**
	* Removes the zendesk article where zendeskSectionId = &#63; and documentationKey = &#63; from the database.
	*
	* @param zendeskSectionId the zendesk section ID
	* @param documentationKey the documentation key
	* @return the zendesk article that was removed
	*/
	public static ZendeskArticle removeByZSI_DK(long zendeskSectionId,
		String documentationKey)
		throws com.liferay.osb.customer.zendesk.documentation.exception.NoSuchZendeskArticleException {
		return getPersistence()
				   .removeByZSI_DK(zendeskSectionId, documentationKey);
	}

	/**
	* Returns the number of zendesk articles where zendeskSectionId = &#63; and documentationKey = &#63;.
	*
	* @param zendeskSectionId the zendesk section ID
	* @param documentationKey the documentation key
	* @return the number of matching zendesk articles
	*/
	public static int countByZSI_DK(long zendeskSectionId,
		String documentationKey) {
		return getPersistence().countByZSI_DK(zendeskSectionId, documentationKey);
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
		throws com.liferay.osb.customer.zendesk.documentation.exception.NoSuchZendeskArticleException {
		return getPersistence().remove(zendeskArticleId);
	}

	public static ZendeskArticle updateImpl(ZendeskArticle zendeskArticle) {
		return getPersistence().updateImpl(zendeskArticle);
	}

	/**
	* Returns the zendesk article with the primary key or throws a {@link NoSuchZendeskArticleException} if it could not be found.
	*
	* @param zendeskArticleId the primary key of the zendesk article
	* @return the zendesk article
	* @throws NoSuchZendeskArticleException if a zendesk article with the primary key could not be found
	*/
	public static ZendeskArticle findByPrimaryKey(long zendeskArticleId)
		throws com.liferay.osb.customer.zendesk.documentation.exception.NoSuchZendeskArticleException {
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

	public static java.util.Map<java.io.Serializable, ZendeskArticle> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ZendeskArticleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of zendesk articles
	* @param end the upper bound of the range of zendesk articles (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of zendesk articles
	*/
	public static List<ZendeskArticle> findAll(int start, int end,
		OrderByComparator<ZendeskArticle> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	public static List<ZendeskArticle> findAll(int start, int end,
		OrderByComparator<ZendeskArticle> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
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
		return _serviceTracker.getService();
	}

	private static ServiceTracker<ZendeskArticlePersistence, ZendeskArticlePersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(ZendeskArticlePersistence.class);

		ServiceTracker<ZendeskArticlePersistence, ZendeskArticlePersistence> serviceTracker =
			new ServiceTracker<ZendeskArticlePersistence, ZendeskArticlePersistence>(bundle.getBundleContext(),
				ZendeskArticlePersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}