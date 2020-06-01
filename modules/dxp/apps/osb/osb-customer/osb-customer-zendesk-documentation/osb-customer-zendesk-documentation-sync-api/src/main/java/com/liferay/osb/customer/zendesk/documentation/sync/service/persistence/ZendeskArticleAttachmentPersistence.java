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

import com.liferay.osb.customer.zendesk.documentation.sync.exception.NoSuchZendeskArticleAttachmentException;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskArticleAttachment;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the zendesk article attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ZendeskArticleAttachmentUtil
 * @generated
 */
@ProviderType
public interface ZendeskArticleAttachmentPersistence
	extends BasePersistence<ZendeskArticleAttachment> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link ZendeskArticleAttachmentUtil} to access the zendesk article attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, ZendeskArticleAttachment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the zendesk article attachments where zendeskArticleId = &#63;.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @return the matching zendesk article attachments
	 */
	public java.util.List<ZendeskArticleAttachment> findByZendeskArticleId(
		long zendeskArticleId);

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
	public java.util.List<ZendeskArticleAttachment> findByZendeskArticleId(
		long zendeskArticleId, int start, int end);

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
	public java.util.List<ZendeskArticleAttachment> findByZendeskArticleId(
		long zendeskArticleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<ZendeskArticleAttachment> orderByComparator);

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
	public java.util.List<ZendeskArticleAttachment> findByZendeskArticleId(
		long zendeskArticleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<ZendeskArticleAttachment> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first zendesk article attachment in the ordered set where zendeskArticleId = &#63;.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching zendesk article attachment
	 * @throws NoSuchZendeskArticleAttachmentException if a matching zendesk article attachment could not be found
	 */
	public ZendeskArticleAttachment findByZendeskArticleId_First(
			long zendeskArticleId,
			com.liferay.portal.kernel.util.OrderByComparator
				<ZendeskArticleAttachment> orderByComparator)
		throws NoSuchZendeskArticleAttachmentException;

	/**
	 * Returns the first zendesk article attachment in the ordered set where zendeskArticleId = &#63;.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching zendesk article attachment, or <code>null</code> if a matching zendesk article attachment could not be found
	 */
	public ZendeskArticleAttachment fetchByZendeskArticleId_First(
		long zendeskArticleId,
		com.liferay.portal.kernel.util.OrderByComparator
			<ZendeskArticleAttachment> orderByComparator);

	/**
	 * Returns the last zendesk article attachment in the ordered set where zendeskArticleId = &#63;.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching zendesk article attachment
	 * @throws NoSuchZendeskArticleAttachmentException if a matching zendesk article attachment could not be found
	 */
	public ZendeskArticleAttachment findByZendeskArticleId_Last(
			long zendeskArticleId,
			com.liferay.portal.kernel.util.OrderByComparator
				<ZendeskArticleAttachment> orderByComparator)
		throws NoSuchZendeskArticleAttachmentException;

	/**
	 * Returns the last zendesk article attachment in the ordered set where zendeskArticleId = &#63;.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching zendesk article attachment, or <code>null</code> if a matching zendesk article attachment could not be found
	 */
	public ZendeskArticleAttachment fetchByZendeskArticleId_Last(
		long zendeskArticleId,
		com.liferay.portal.kernel.util.OrderByComparator
			<ZendeskArticleAttachment> orderByComparator);

	/**
	 * Returns the zendesk article attachments before and after the current zendesk article attachment in the ordered set where zendeskArticleId = &#63;.
	 *
	 * @param zendeskArticleAttachmentId the primary key of the current zendesk article attachment
	 * @param zendeskArticleId the zendesk article ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next zendesk article attachment
	 * @throws NoSuchZendeskArticleAttachmentException if a zendesk article attachment with the primary key could not be found
	 */
	public ZendeskArticleAttachment[] findByZendeskArticleId_PrevAndNext(
			long zendeskArticleAttachmentId, long zendeskArticleId,
			com.liferay.portal.kernel.util.OrderByComparator
				<ZendeskArticleAttachment> orderByComparator)
		throws NoSuchZendeskArticleAttachmentException;

	/**
	 * Removes all the zendesk article attachments where zendeskArticleId = &#63; from the database.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 */
	public void removeByZendeskArticleId(long zendeskArticleId);

	/**
	 * Returns the number of zendesk article attachments where zendeskArticleId = &#63;.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @return the number of matching zendesk article attachments
	 */
	public int countByZendeskArticleId(long zendeskArticleId);

	/**
	 * Returns the zendesk article attachment where zendeskArticleId = &#63; and filePath = &#63; or throws a <code>NoSuchZendeskArticleAttachmentException</code> if it could not be found.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param filePath the file path
	 * @return the matching zendesk article attachment
	 * @throws NoSuchZendeskArticleAttachmentException if a matching zendesk article attachment could not be found
	 */
	public ZendeskArticleAttachment findByZAI_FP(
			long zendeskArticleId, String filePath)
		throws NoSuchZendeskArticleAttachmentException;

	/**
	 * Returns the zendesk article attachment where zendeskArticleId = &#63; and filePath = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param filePath the file path
	 * @return the matching zendesk article attachment, or <code>null</code> if a matching zendesk article attachment could not be found
	 */
	public ZendeskArticleAttachment fetchByZAI_FP(
		long zendeskArticleId, String filePath);

	/**
	 * Returns the zendesk article attachment where zendeskArticleId = &#63; and filePath = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param filePath the file path
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching zendesk article attachment, or <code>null</code> if a matching zendesk article attachment could not be found
	 */
	public ZendeskArticleAttachment fetchByZAI_FP(
		long zendeskArticleId, String filePath, boolean useFinderCache);

	/**
	 * Removes the zendesk article attachment where zendeskArticleId = &#63; and filePath = &#63; from the database.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param filePath the file path
	 * @return the zendesk article attachment that was removed
	 */
	public ZendeskArticleAttachment removeByZAI_FP(
			long zendeskArticleId, String filePath)
		throws NoSuchZendeskArticleAttachmentException;

	/**
	 * Returns the number of zendesk article attachments where zendeskArticleId = &#63; and filePath = &#63;.
	 *
	 * @param zendeskArticleId the zendesk article ID
	 * @param filePath the file path
	 * @return the number of matching zendesk article attachments
	 */
	public int countByZAI_FP(long zendeskArticleId, String filePath);

	/**
	 * Caches the zendesk article attachment in the entity cache if it is enabled.
	 *
	 * @param zendeskArticleAttachment the zendesk article attachment
	 */
	public void cacheResult(ZendeskArticleAttachment zendeskArticleAttachment);

	/**
	 * Caches the zendesk article attachments in the entity cache if it is enabled.
	 *
	 * @param zendeskArticleAttachments the zendesk article attachments
	 */
	public void cacheResult(
		java.util.List<ZendeskArticleAttachment> zendeskArticleAttachments);

	/**
	 * Creates a new zendesk article attachment with the primary key. Does not add the zendesk article attachment to the database.
	 *
	 * @param zendeskArticleAttachmentId the primary key for the new zendesk article attachment
	 * @return the new zendesk article attachment
	 */
	public ZendeskArticleAttachment create(long zendeskArticleAttachmentId);

	/**
	 * Removes the zendesk article attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param zendeskArticleAttachmentId the primary key of the zendesk article attachment
	 * @return the zendesk article attachment that was removed
	 * @throws NoSuchZendeskArticleAttachmentException if a zendesk article attachment with the primary key could not be found
	 */
	public ZendeskArticleAttachment remove(long zendeskArticleAttachmentId)
		throws NoSuchZendeskArticleAttachmentException;

	public ZendeskArticleAttachment updateImpl(
		ZendeskArticleAttachment zendeskArticleAttachment);

	/**
	 * Returns the zendesk article attachment with the primary key or throws a <code>NoSuchZendeskArticleAttachmentException</code> if it could not be found.
	 *
	 * @param zendeskArticleAttachmentId the primary key of the zendesk article attachment
	 * @return the zendesk article attachment
	 * @throws NoSuchZendeskArticleAttachmentException if a zendesk article attachment with the primary key could not be found
	 */
	public ZendeskArticleAttachment findByPrimaryKey(
			long zendeskArticleAttachmentId)
		throws NoSuchZendeskArticleAttachmentException;

	/**
	 * Returns the zendesk article attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param zendeskArticleAttachmentId the primary key of the zendesk article attachment
	 * @return the zendesk article attachment, or <code>null</code> if a zendesk article attachment with the primary key could not be found
	 */
	public ZendeskArticleAttachment fetchByPrimaryKey(
		long zendeskArticleAttachmentId);

	/**
	 * Returns all the zendesk article attachments.
	 *
	 * @return the zendesk article attachments
	 */
	public java.util.List<ZendeskArticleAttachment> findAll();

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
	public java.util.List<ZendeskArticleAttachment> findAll(int start, int end);

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
	public java.util.List<ZendeskArticleAttachment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<ZendeskArticleAttachment> orderByComparator);

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
	public java.util.List<ZendeskArticleAttachment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<ZendeskArticleAttachment> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the zendesk article attachments from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of zendesk article attachments.
	 *
	 * @return the number of zendesk article attachments
	 */
	public int countAll();

}