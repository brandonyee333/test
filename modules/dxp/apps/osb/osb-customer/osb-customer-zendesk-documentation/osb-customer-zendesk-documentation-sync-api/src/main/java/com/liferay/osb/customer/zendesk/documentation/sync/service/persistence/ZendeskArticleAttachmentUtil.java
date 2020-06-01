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

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the zendesk article attachment service. This utility wraps <code>com.liferay.osb.customer.zendesk.documentation.sync.service.persistence.impl.ZendeskArticleAttachmentPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticleAttachmentPersistence
 * @generated
 */
public class ZendeskArticleAttachmentUtil {

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
	public static void clearCache(
		ZendeskArticleAttachment zendeskArticleAttachment) {

		getPersistence().clearCache(zendeskArticleAttachment);
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
	public static Map<Serializable, ZendeskArticleAttachment>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ZendeskArticleAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ZendeskArticleAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ZendeskArticleAttachment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ZendeskArticleAttachment> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static ZendeskArticleAttachment update(
		ZendeskArticleAttachment zendeskArticleAttachment) {

		return getPersistence().update(zendeskArticleAttachment);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static ZendeskArticleAttachment update(
		ZendeskArticleAttachment zendeskArticleAttachment,
		ServiceContext serviceContext) {

		return getPersistence().update(
			zendeskArticleAttachment, serviceContext);
	}

	/**
	 * Returns all the zendesk article attachments where zendeskArticleId = &#63;.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @return the matching zendesk article attachments
	 */
	public static List<ZendeskArticleAttachment> findByZendeskArticleId(
		long zendeskArticleId) {

		return getPersistence().findByZendeskArticleId(zendeskArticleId);
	}

	/**
	 * Returns a range of all the zendesk article attachments where zendeskArticleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ZendeskArticleAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param start the lower bound of the range of zendesk article attachments
	 * @param end the upper bound of the range of zendesk article attachments (not inclusive)
	 * @return the range of matching zendesk article attachments
	 */
	public static List<ZendeskArticleAttachment> findByZendeskArticleId(
		long zendeskArticleId, int start, int end) {

		return getPersistence().findByZendeskArticleId(
			zendeskArticleId, start, end);
	}

	/**
	 * Returns an ordered range of all the zendesk article attachments where zendeskArticleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ZendeskArticleAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param start the lower bound of the range of zendesk article attachments
	 * @param end the upper bound of the range of zendesk article attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching zendesk article attachments
	 */
	public static List<ZendeskArticleAttachment> findByZendeskArticleId(
		long zendeskArticleId, int start, int end,
		OrderByComparator<ZendeskArticleAttachment> orderByComparator) {

		return getPersistence().findByZendeskArticleId(
			zendeskArticleId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the zendesk article attachments where zendeskArticleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ZendeskArticleAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param start the lower bound of the range of zendesk article attachments
	 * @param end the upper bound of the range of zendesk article attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching zendesk article attachments
	 */
	public static List<ZendeskArticleAttachment> findByZendeskArticleId(
		long zendeskArticleId, int start, int end,
		OrderByComparator<ZendeskArticleAttachment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByZendeskArticleId(
			zendeskArticleId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first zendesk article attachment in the ordered set where zendeskArticleId = &#63;.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching zendesk article attachment
	 * @throws NoSuchZendeskArticleAttachmentException if a matching zendesk article attachment could not be found
	 */
	public static ZendeskArticleAttachment findByZendeskArticleId_First(
			long zendeskArticleId,
			OrderByComparator<ZendeskArticleAttachment> orderByComparator)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleAttachmentException {

		return getPersistence().findByZendeskArticleId_First(
			zendeskArticleId, orderByComparator);
	}

	/**
	 * Returns the first zendesk article attachment in the ordered set where zendeskArticleId = &#63;.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching zendesk article attachment, or <code>null</code> if a matching zendesk article attachment could not be found
	 */
	public static ZendeskArticleAttachment fetchByZendeskArticleId_First(
		long zendeskArticleId,
		OrderByComparator<ZendeskArticleAttachment> orderByComparator) {

		return getPersistence().fetchByZendeskArticleId_First(
			zendeskArticleId, orderByComparator);
	}

	/**
	 * Returns the last zendesk article attachment in the ordered set where zendeskArticleId = &#63;.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching zendesk article attachment
	 * @throws NoSuchZendeskArticleAttachmentException if a matching zendesk article attachment could not be found
	 */
	public static ZendeskArticleAttachment findByZendeskArticleId_Last(
			long zendeskArticleId,
			OrderByComparator<ZendeskArticleAttachment> orderByComparator)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleAttachmentException {

		return getPersistence().findByZendeskArticleId_Last(
			zendeskArticleId, orderByComparator);
	}

	/**
	 * Returns the last zendesk article attachment in the ordered set where zendeskArticleId = &#63;.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching zendesk article attachment, or <code>null</code> if a matching zendesk article attachment could not be found
	 */
	public static ZendeskArticleAttachment fetchByZendeskArticleId_Last(
		long zendeskArticleId,
		OrderByComparator<ZendeskArticleAttachment> orderByComparator) {

		return getPersistence().fetchByZendeskArticleId_Last(
			zendeskArticleId, orderByComparator);
	}

	/**
	 * Returns the zendesk article attachments before and after the current zendesk article attachment in the ordered set where zendeskArticleId = &#63;.
	 *
	 * @param zendeskArticleAttachmentId the primary key of the current zendesk article attachment
	 * @param zendeskArticleId the zendesk article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next zendesk article attachment
	 * @throws NoSuchZendeskArticleAttachmentException if a zendesk article attachment with the primary key could not be found
	 */
	public static ZendeskArticleAttachment[] findByZendeskArticleId_PrevAndNext(
			long zendeskArticleAttachmentId, long zendeskArticleId,
			OrderByComparator<ZendeskArticleAttachment> orderByComparator)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleAttachmentException {

		return getPersistence().findByZendeskArticleId_PrevAndNext(
			zendeskArticleAttachmentId, zendeskArticleId, orderByComparator);
	}

	/**
	 * Removes all the zendesk article attachments where zendeskArticleId = &#63; from the database.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 */
	public static void removeByZendeskArticleId(long zendeskArticleId) {
		getPersistence().removeByZendeskArticleId(zendeskArticleId);
	}

	/**
	 * Returns the number of zendesk article attachments where zendeskArticleId = &#63;.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @return the number of matching zendesk article attachments
	 */
	public static int countByZendeskArticleId(long zendeskArticleId) {
		return getPersistence().countByZendeskArticleId(zendeskArticleId);
	}

	/**
	 * Returns the zendesk article attachment where zendeskArticleId = &#63; and filePath = &#63; or throws a <code>NoSuchZendeskArticleAttachmentException</code> if it could not be found.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param filePath the file path
	 * @return the matching zendesk article attachment
	 * @throws NoSuchZendeskArticleAttachmentException if a matching zendesk article attachment could not be found
	 */
	public static ZendeskArticleAttachment findByZAI_FP(
			long zendeskArticleId, String filePath)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleAttachmentException {

		return getPersistence().findByZAI_FP(zendeskArticleId, filePath);
	}

	/**
	 * Returns the zendesk article attachment where zendeskArticleId = &#63; and filePath = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param filePath the file path
	 * @return the matching zendesk article attachment, or <code>null</code> if a matching zendesk article attachment could not be found
	 */
	public static ZendeskArticleAttachment fetchByZAI_FP(
		long zendeskArticleId, String filePath) {

		return getPersistence().fetchByZAI_FP(zendeskArticleId, filePath);
	}

	/**
	 * Returns the zendesk article attachment where zendeskArticleId = &#63; and filePath = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param filePath the file path
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching zendesk article attachment, or <code>null</code> if a matching zendesk article attachment could not be found
	 */
	public static ZendeskArticleAttachment fetchByZAI_FP(
		long zendeskArticleId, String filePath, boolean useFinderCache) {

		return getPersistence().fetchByZAI_FP(
			zendeskArticleId, filePath, useFinderCache);
	}

	/**
	 * Removes the zendesk article attachment where zendeskArticleId = &#63; and filePath = &#63; from the database.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param filePath the file path
	 * @return the zendesk article attachment that was removed
	 */
	public static ZendeskArticleAttachment removeByZAI_FP(
			long zendeskArticleId, String filePath)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleAttachmentException {

		return getPersistence().removeByZAI_FP(zendeskArticleId, filePath);
	}

	/**
	 * Returns the number of zendesk article attachments where zendeskArticleId = &#63; and filePath = &#63;.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param filePath the file path
	 * @return the number of matching zendesk article attachments
	 */
	public static int countByZAI_FP(long zendeskArticleId, String filePath) {
		return getPersistence().countByZAI_FP(zendeskArticleId, filePath);
	}

	/**
	 * Caches the zendesk article attachment in the entity cache if it is enabled.
	 *
	 * @param zendeskArticleAttachment the zendesk article attachment
	 */
	public static void cacheResult(
		ZendeskArticleAttachment zendeskArticleAttachment) {

		getPersistence().cacheResult(zendeskArticleAttachment);
	}

	/**
	 * Caches the zendesk article attachments in the entity cache if it is enabled.
	 *
	 * @param zendeskArticleAttachments the zendesk article attachments
	 */
	public static void cacheResult(
		List<ZendeskArticleAttachment> zendeskArticleAttachments) {

		getPersistence().cacheResult(zendeskArticleAttachments);
	}

	/**
	 * Creates a new zendesk article attachment with the primary key. Does not add the zendesk article attachment to the database.
	 *
	 * @param zendeskArticleAttachmentId the primary key for the new zendesk article attachment
	 * @return the new zendesk article attachment
	 */
	public static ZendeskArticleAttachment create(
		long zendeskArticleAttachmentId) {

		return getPersistence().create(zendeskArticleAttachmentId);
	}

	/**
	 * Removes the zendesk article attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param zendeskArticleAttachmentId the primary key of the zendesk article attachment
	 * @return the zendesk article attachment that was removed
	 * @throws NoSuchZendeskArticleAttachmentException if a zendesk article attachment with the primary key could not be found
	 */
	public static ZendeskArticleAttachment remove(
			long zendeskArticleAttachmentId)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleAttachmentException {

		return getPersistence().remove(zendeskArticleAttachmentId);
	}

	public static ZendeskArticleAttachment updateImpl(
		ZendeskArticleAttachment zendeskArticleAttachment) {

		return getPersistence().updateImpl(zendeskArticleAttachment);
	}

	/**
	 * Returns the zendesk article attachment with the primary key or throws a <code>NoSuchZendeskArticleAttachmentException</code> if it could not be found.
	 *
	 * @param zendeskArticleAttachmentId the primary key of the zendesk article attachment
	 * @return the zendesk article attachment
	 * @throws NoSuchZendeskArticleAttachmentException if a zendesk article attachment with the primary key could not be found
	 */
	public static ZendeskArticleAttachment findByPrimaryKey(
			long zendeskArticleAttachmentId)
		throws com.liferay.osb.customer.zendesk.documentation.sync.exception.
			NoSuchZendeskArticleAttachmentException {

		return getPersistence().findByPrimaryKey(zendeskArticleAttachmentId);
	}

	/**
	 * Returns the zendesk article attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param zendeskArticleAttachmentId the primary key of the zendesk article attachment
	 * @return the zendesk article attachment, or <code>null</code> if a zendesk article attachment with the primary key could not be found
	 */
	public static ZendeskArticleAttachment fetchByPrimaryKey(
		long zendeskArticleAttachmentId) {

		return getPersistence().fetchByPrimaryKey(zendeskArticleAttachmentId);
	}

	/**
	 * Returns all the zendesk article attachments.
	 *
	 * @return the zendesk article attachments
	 */
	public static List<ZendeskArticleAttachment> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the zendesk article attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ZendeskArticleAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk article attachments
	 * @param end the upper bound of the range of zendesk article attachments (not inclusive)
	 * @return the range of zendesk article attachments
	 */
	public static List<ZendeskArticleAttachment> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the zendesk article attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ZendeskArticleAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk article attachments
	 * @param end the upper bound of the range of zendesk article attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of zendesk article attachments
	 */
	public static List<ZendeskArticleAttachment> findAll(
		int start, int end,
		OrderByComparator<ZendeskArticleAttachment> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the zendesk article attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>ZendeskArticleAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of zendesk article attachments
	 * @param end the upper bound of the range of zendesk article attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of zendesk article attachments
	 */
	public static List<ZendeskArticleAttachment> findAll(
		int start, int end,
		OrderByComparator<ZendeskArticleAttachment> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the zendesk article attachments from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of zendesk article attachments.
	 *
	 * @return the number of zendesk article attachments
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static ZendeskArticleAttachmentPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<ZendeskArticleAttachmentPersistence,
		 ZendeskArticleAttachmentPersistence> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			ZendeskArticleAttachmentPersistence.class);

		ServiceTracker
			<ZendeskArticleAttachmentPersistence,
			 ZendeskArticleAttachmentPersistence> serviceTracker =
				new ServiceTracker
					<ZendeskArticleAttachmentPersistence,
					 ZendeskArticleAttachmentPersistence>(
						 bundle.getBundleContext(),
						 ZendeskArticleAttachmentPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}