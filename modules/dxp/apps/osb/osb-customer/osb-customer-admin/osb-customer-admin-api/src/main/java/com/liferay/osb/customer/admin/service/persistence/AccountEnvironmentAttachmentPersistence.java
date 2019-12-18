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

import com.liferay.osb.customer.admin.exception.NoSuchAccountEnvironmentAttachmentException;
import com.liferay.osb.customer.admin.model.AccountEnvironmentAttachment;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the account environment attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountEnvironmentAttachmentUtil
 * @generated
 */
@ProviderType
public interface AccountEnvironmentAttachmentPersistence
	extends BasePersistence<AccountEnvironmentAttachment> {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountEnvironmentAttachmentUtil} to access the account environment attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, AccountEnvironmentAttachment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @return the matching account environment attachments
	 */
	public java.util.List<AccountEnvironmentAttachment>
		findByAccountEnvironmentId(long accountEnvironmentId);

	/**
	 * Returns a range of all the account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @return the range of matching account environment attachments
	 */
	public java.util.List<AccountEnvironmentAttachment>
		findByAccountEnvironmentId(
			long accountEnvironmentId, int start, int end);

	/**
	 * Returns an ordered range of all the account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account environment attachments
	 */
	public java.util.List<AccountEnvironmentAttachment>
		findByAccountEnvironmentId(
			long accountEnvironmentId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<AccountEnvironmentAttachment> orderByComparator);

	/**
	 * Returns an ordered range of all the account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account environment attachments
	 */
	public java.util.List<AccountEnvironmentAttachment>
		findByAccountEnvironmentId(
			long accountEnvironmentId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<AccountEnvironmentAttachment> orderByComparator,
			boolean useFinderCache);

	/**
	 * Returns the first account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	 */
	public AccountEnvironmentAttachment findByAccountEnvironmentId_First(
			long accountEnvironmentId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AccountEnvironmentAttachment> orderByComparator)
		throws NoSuchAccountEnvironmentAttachmentException;

	/**
	 * Returns the first account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	public AccountEnvironmentAttachment fetchByAccountEnvironmentId_First(
		long accountEnvironmentId,
		com.liferay.portal.kernel.util.OrderByComparator
			<AccountEnvironmentAttachment> orderByComparator);

	/**
	 * Returns the last account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	 */
	public AccountEnvironmentAttachment findByAccountEnvironmentId_Last(
			long accountEnvironmentId,
			com.liferay.portal.kernel.util.OrderByComparator
				<AccountEnvironmentAttachment> orderByComparator)
		throws NoSuchAccountEnvironmentAttachmentException;

	/**
	 * Returns the last account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	public AccountEnvironmentAttachment fetchByAccountEnvironmentId_Last(
		long accountEnvironmentId,
		com.liferay.portal.kernel.util.OrderByComparator
			<AccountEnvironmentAttachment> orderByComparator);

	/**
	 * Returns the account environment attachments before and after the current account environment attachment in the ordered set where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the current account environment attachment
	 * @param accountEnvironmentId the account environment ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	 */
	public AccountEnvironmentAttachment[]
			findByAccountEnvironmentId_PrevAndNext(
				long accountEnvironmentAttachmentId, long accountEnvironmentId,
				com.liferay.portal.kernel.util.OrderByComparator
					<AccountEnvironmentAttachment> orderByComparator)
		throws NoSuchAccountEnvironmentAttachmentException;

	/**
	 * Removes all the account environment attachments where accountEnvironmentId = &#63; from the database.
	 *
	 * @param accountEnvironmentId the account environment ID
	 */
	public void removeByAccountEnvironmentId(long accountEnvironmentId);

	/**
	 * Returns the number of account environment attachments where accountEnvironmentId = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @return the number of matching account environment attachments
	 */
	public int countByAccountEnvironmentId(long accountEnvironmentId);

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or throws a <code>NoSuchAccountEnvironmentAttachmentException</code> if it could not be found.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @return the matching account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	 */
	public AccountEnvironmentAttachment findByAEI_FN(
			long accountEnvironmentId, String fileName)
		throws NoSuchAccountEnvironmentAttachmentException;

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	public AccountEnvironmentAttachment fetchByAEI_FN(
		long accountEnvironmentId, String fileName);

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	public AccountEnvironmentAttachment fetchByAEI_FN(
		long accountEnvironmentId, String fileName, boolean useFinderCache);

	/**
	 * Removes the account environment attachment where accountEnvironmentId = &#63; and fileName = &#63; from the database.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @return the account environment attachment that was removed
	 */
	public AccountEnvironmentAttachment removeByAEI_FN(
			long accountEnvironmentId, String fileName)
		throws NoSuchAccountEnvironmentAttachmentException;

	/**
	 * Returns the number of account environment attachments where accountEnvironmentId = &#63; and fileName = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param fileName the file name
	 * @return the number of matching account environment attachments
	 */
	public int countByAEI_FN(long accountEnvironmentId, String fileName);

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and type = &#63; or throws a <code>NoSuchAccountEnvironmentAttachmentException</code> if it could not be found.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @return the matching account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a matching account environment attachment could not be found
	 */
	public AccountEnvironmentAttachment findByAEI_T(
			long accountEnvironmentId, int type)
		throws NoSuchAccountEnvironmentAttachmentException;

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	public AccountEnvironmentAttachment fetchByAEI_T(
		long accountEnvironmentId, int type);

	/**
	 * Returns the account environment attachment where accountEnvironmentId = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account environment attachment, or <code>null</code> if a matching account environment attachment could not be found
	 */
	public AccountEnvironmentAttachment fetchByAEI_T(
		long accountEnvironmentId, int type, boolean useFinderCache);

	/**
	 * Removes the account environment attachment where accountEnvironmentId = &#63; and type = &#63; from the database.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @return the account environment attachment that was removed
	 */
	public AccountEnvironmentAttachment removeByAEI_T(
			long accountEnvironmentId, int type)
		throws NoSuchAccountEnvironmentAttachmentException;

	/**
	 * Returns the number of account environment attachments where accountEnvironmentId = &#63; and type = &#63;.
	 *
	 * @param accountEnvironmentId the account environment ID
	 * @param type the type
	 * @return the number of matching account environment attachments
	 */
	public int countByAEI_T(long accountEnvironmentId, int type);

	/**
	 * Caches the account environment attachment in the entity cache if it is enabled.
	 *
	 * @param accountEnvironmentAttachment the account environment attachment
	 */
	public void cacheResult(
		AccountEnvironmentAttachment accountEnvironmentAttachment);

	/**
	 * Caches the account environment attachments in the entity cache if it is enabled.
	 *
	 * @param accountEnvironmentAttachments the account environment attachments
	 */
	public void cacheResult(
		java.util.List<AccountEnvironmentAttachment>
			accountEnvironmentAttachments);

	/**
	 * Creates a new account environment attachment with the primary key. Does not add the account environment attachment to the database.
	 *
	 * @param accountEnvironmentAttachmentId the primary key for the new account environment attachment
	 * @return the new account environment attachment
	 */
	public AccountEnvironmentAttachment create(
		long accountEnvironmentAttachmentId);

	/**
	 * Removes the account environment attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	 * @return the account environment attachment that was removed
	 * @throws NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	 */
	public AccountEnvironmentAttachment remove(
			long accountEnvironmentAttachmentId)
		throws NoSuchAccountEnvironmentAttachmentException;

	public AccountEnvironmentAttachment updateImpl(
		AccountEnvironmentAttachment accountEnvironmentAttachment);

	/**
	 * Returns the account environment attachment with the primary key or throws a <code>NoSuchAccountEnvironmentAttachmentException</code> if it could not be found.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	 * @return the account environment attachment
	 * @throws NoSuchAccountEnvironmentAttachmentException if a account environment attachment with the primary key could not be found
	 */
	public AccountEnvironmentAttachment findByPrimaryKey(
			long accountEnvironmentAttachmentId)
		throws NoSuchAccountEnvironmentAttachmentException;

	/**
	 * Returns the account environment attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountEnvironmentAttachmentId the primary key of the account environment attachment
	 * @return the account environment attachment, or <code>null</code> if a account environment attachment with the primary key could not be found
	 */
	public AccountEnvironmentAttachment fetchByPrimaryKey(
		long accountEnvironmentAttachmentId);

	/**
	 * Returns all the account environment attachments.
	 *
	 * @return the account environment attachments
	 */
	public java.util.List<AccountEnvironmentAttachment> findAll();

	/**
	 * Returns a range of all the account environment attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @return the range of account environment attachments
	 */
	public java.util.List<AccountEnvironmentAttachment> findAll(
		int start, int end);

	/**
	 * Returns an ordered range of all the account environment attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account environment attachments
	 */
	public java.util.List<AccountEnvironmentAttachment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<AccountEnvironmentAttachment> orderByComparator);

	/**
	 * Returns an ordered range of all the account environment attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountEnvironmentAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account environment attachments
	 * @param end the upper bound of the range of account environment attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of account environment attachments
	 */
	public java.util.List<AccountEnvironmentAttachment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<AccountEnvironmentAttachment> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the account environment attachments from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of account environment attachments.
	 *
	 * @return the number of account environment attachments
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}