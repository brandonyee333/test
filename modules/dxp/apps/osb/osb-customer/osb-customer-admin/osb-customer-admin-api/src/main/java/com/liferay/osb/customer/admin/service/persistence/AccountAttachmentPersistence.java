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

import com.liferay.osb.customer.admin.exception.NoSuchAccountAttachmentException;
import com.liferay.osb.customer.admin.model.AccountAttachment;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import java.io.Serializable;

import java.util.Map;
import java.util.Set;

/**
 * The persistence interface for the account attachment service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AccountAttachmentUtil
 * @generated
 */
@ProviderType
public interface AccountAttachmentPersistence
	extends BasePersistence<AccountAttachment> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link AccountAttachmentUtil} to access the account attachment persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */
	@Override
	public Map<Serializable, AccountAttachment> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys);

	/**
	 * Returns all the account attachments where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching account attachments
	 */
	public java.util.List<AccountAttachment> findByAccountEntryId(
		long accountEntryId);

	/**
	 * Returns a range of all the account attachments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of matching account attachments
	 */
	public java.util.List<AccountAttachment> findByAccountEntryId(
		long accountEntryId, int start, int end);

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account attachments
	 */
	public java.util.List<AccountAttachment> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
			orderByComparator);

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account attachments
	 */
	public java.util.List<AccountAttachment> findByAccountEntryId(
		long accountEntryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	public AccountAttachment findByAccountEntryId_First(
			long accountEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
				orderByComparator)
		throws NoSuchAccountAttachmentException;

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	public AccountAttachment fetchByAccountEntryId_First(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
			orderByComparator);

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	public AccountAttachment findByAccountEntryId_Last(
			long accountEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
				orderByComparator)
		throws NoSuchAccountAttachmentException;

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	public AccountAttachment fetchByAccountEntryId_Last(
		long accountEntryId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
			orderByComparator);

	/**
	 * Returns the account attachments before and after the current account attachment in the ordered set where accountEntryId = &#63;.
	 *
	 * @param accountAttachmentId the primary key of the current account attachment
	 * @param accountEntryId the account entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account attachment
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	public AccountAttachment[] findByAccountEntryId_PrevAndNext(
			long accountAttachmentId, long accountEntryId,
			com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
				orderByComparator)
		throws NoSuchAccountAttachmentException;

	/**
	 * Removes all the account attachments where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 */
	public void removeByAccountEntryId(long accountEntryId);

	/**
	 * Returns the number of account attachments where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching account attachments
	 */
	public int countByAccountEntryId(long accountEntryId);

	/**
	 * Returns all the account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @return the matching account attachments
	 */
	public java.util.List<AccountAttachment> findByAEI_API(
		long accountEntryId, long accountProjectId);

	/**
	 * Returns a range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of matching account attachments
	 */
	public java.util.List<AccountAttachment> findByAEI_API(
		long accountEntryId, long accountProjectId, int start, int end);

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account attachments
	 */
	public java.util.List<AccountAttachment> findByAEI_API(
		long accountEntryId, long accountProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
			orderByComparator);

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account attachments
	 */
	public java.util.List<AccountAttachment> findByAEI_API(
		long accountEntryId, long accountProjectId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	public AccountAttachment findByAEI_API_First(
			long accountEntryId, long accountProjectId,
			com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
				orderByComparator)
		throws NoSuchAccountAttachmentException;

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	public AccountAttachment fetchByAEI_API_First(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
			orderByComparator);

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	public AccountAttachment findByAEI_API_Last(
			long accountEntryId, long accountProjectId,
			com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
				orderByComparator)
		throws NoSuchAccountAttachmentException;

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	public AccountAttachment fetchByAEI_API_Last(
		long accountEntryId, long accountProjectId,
		com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
			orderByComparator);

	/**
	 * Returns the account attachments before and after the current account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountAttachmentId the primary key of the current account attachment
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account attachment
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	public AccountAttachment[] findByAEI_API_PrevAndNext(
			long accountAttachmentId, long accountEntryId,
			long accountProjectId,
			com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
				orderByComparator)
		throws NoSuchAccountAttachmentException;

	/**
	 * Removes all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 */
	public void removeByAEI_API(long accountEntryId, long accountProjectId);

	/**
	 * Returns the number of account attachments where accountEntryId = &#63; and accountProjectId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @return the number of matching account attachments
	 */
	public int countByAEI_API(long accountEntryId, long accountProjectId);

	/**
	 * Returns all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @return the matching account attachments
	 */
	public java.util.List<AccountAttachment> findByAEI_API_T(
		long accountEntryId, long accountProjectId, int type);

	/**
	 * Returns a range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of matching account attachments
	 */
	public java.util.List<AccountAttachment> findByAEI_API_T(
		long accountEntryId, long accountProjectId, int type, int start,
		int end);

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching account attachments
	 */
	public java.util.List<AccountAttachment> findByAEI_API_T(
		long accountEntryId, long accountProjectId, int type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
			orderByComparator);

	/**
	 * Returns an ordered range of all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching account attachments
	 */
	public java.util.List<AccountAttachment> findByAEI_API_T(
		long accountEntryId, long accountProjectId, int type, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	public AccountAttachment findByAEI_API_T_First(
			long accountEntryId, long accountProjectId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
				orderByComparator)
		throws NoSuchAccountAttachmentException;

	/**
	 * Returns the first account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	public AccountAttachment fetchByAEI_API_T_First(
		long accountEntryId, long accountProjectId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
			orderByComparator);

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	public AccountAttachment findByAEI_API_T_Last(
			long accountEntryId, long accountProjectId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
				orderByComparator)
		throws NoSuchAccountAttachmentException;

	/**
	 * Returns the last account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	public AccountAttachment fetchByAEI_API_T_Last(
		long accountEntryId, long accountProjectId, int type,
		com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
			orderByComparator);

	/**
	 * Returns the account attachments before and after the current account attachment in the ordered set where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountAttachmentId the primary key of the current account attachment
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next account attachment
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	public AccountAttachment[] findByAEI_API_T_PrevAndNext(
			long accountAttachmentId, long accountEntryId,
			long accountProjectId, int type,
			com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
				orderByComparator)
		throws NoSuchAccountAttachmentException;

	/**
	 * Removes all the account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 */
	public void removeByAEI_API_T(
		long accountEntryId, long accountProjectId, int type);

	/**
	 * Returns the number of account attachments where accountEntryId = &#63; and accountProjectId = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param type the type
	 * @return the number of matching account attachments
	 */
	public int countByAEI_API_T(
		long accountEntryId, long accountProjectId, int type);

	/**
	 * Returns the account attachment where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63; or throws a <code>NoSuchAccountAttachmentException</code> if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @return the matching account attachment
	 * @throws NoSuchAccountAttachmentException if a matching account attachment could not be found
	 */
	public AccountAttachment findByAEI_API_FN_T(
			long accountEntryId, long accountProjectId, String fileName,
			int type)
		throws NoSuchAccountAttachmentException;

	/**
	 * Returns the account attachment where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @return the matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	public AccountAttachment fetchByAEI_API_FN_T(
		long accountEntryId, long accountProjectId, String fileName, int type);

	/**
	 * Returns the account attachment where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching account attachment, or <code>null</code> if a matching account attachment could not be found
	 */
	public AccountAttachment fetchByAEI_API_FN_T(
		long accountEntryId, long accountProjectId, String fileName, int type,
		boolean useFinderCache);

	/**
	 * Removes the account attachment where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @return the account attachment that was removed
	 */
	public AccountAttachment removeByAEI_API_FN_T(
			long accountEntryId, long accountProjectId, String fileName,
			int type)
		throws NoSuchAccountAttachmentException;

	/**
	 * Returns the number of account attachments where accountEntryId = &#63; and accountProjectId = &#63; and fileName = &#63; and type = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @param accountProjectId the account project ID
	 * @param fileName the file name
	 * @param type the type
	 * @return the number of matching account attachments
	 */
	public int countByAEI_API_FN_T(
		long accountEntryId, long accountProjectId, String fileName, int type);

	/**
	 * Caches the account attachment in the entity cache if it is enabled.
	 *
	 * @param accountAttachment the account attachment
	 */
	public void cacheResult(AccountAttachment accountAttachment);

	/**
	 * Caches the account attachments in the entity cache if it is enabled.
	 *
	 * @param accountAttachments the account attachments
	 */
	public void cacheResult(
		java.util.List<AccountAttachment> accountAttachments);

	/**
	 * Creates a new account attachment with the primary key. Does not add the account attachment to the database.
	 *
	 * @param accountAttachmentId the primary key for the new account attachment
	 * @return the new account attachment
	 */
	public AccountAttachment create(long accountAttachmentId);

	/**
	 * Removes the account attachment with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accountAttachmentId the primary key of the account attachment
	 * @return the account attachment that was removed
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	public AccountAttachment remove(long accountAttachmentId)
		throws NoSuchAccountAttachmentException;

	public AccountAttachment updateImpl(AccountAttachment accountAttachment);

	/**
	 * Returns the account attachment with the primary key or throws a <code>NoSuchAccountAttachmentException</code> if it could not be found.
	 *
	 * @param accountAttachmentId the primary key of the account attachment
	 * @return the account attachment
	 * @throws NoSuchAccountAttachmentException if a account attachment with the primary key could not be found
	 */
	public AccountAttachment findByPrimaryKey(long accountAttachmentId)
		throws NoSuchAccountAttachmentException;

	/**
	 * Returns the account attachment with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accountAttachmentId the primary key of the account attachment
	 * @return the account attachment, or <code>null</code> if a account attachment with the primary key could not be found
	 */
	public AccountAttachment fetchByPrimaryKey(long accountAttachmentId);

	/**
	 * Returns all the account attachments.
	 *
	 * @return the account attachments
	 */
	public java.util.List<AccountAttachment> findAll();

	/**
	 * Returns a range of all the account attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @return the range of account attachments
	 */
	public java.util.List<AccountAttachment> findAll(int start, int end);

	/**
	 * Returns an ordered range of all the account attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of account attachments
	 */
	public java.util.List<AccountAttachment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
			orderByComparator);

	/**
	 * Returns an ordered range of all the account attachments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>AccountAttachmentModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of account attachments
	 * @param end the upper bound of the range of account attachments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of account attachments
	 */
	public java.util.List<AccountAttachment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<AccountAttachment>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the account attachments from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of account attachments.
	 *
	 * @return the number of account attachments
	 */
	public int countAll();

	@Override
	public Set<String> getBadColumnNames();

}