/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.document.library.service.persistence.impl;

import com.liferay.document.library.exception.NoSuchFileVersionPreviewException;
import com.liferay.document.library.model.FileVersionPreview;
import com.liferay.document.library.model.impl.FileVersionPreviewImpl;
import com.liferay.document.library.model.impl.FileVersionPreviewModelImpl;
import com.liferay.document.library.service.persistence.FileVersionPreviewPersistence;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the file version preview service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class FileVersionPreviewPersistenceImpl
	extends BasePersistenceImpl<FileVersionPreview>
	implements FileVersionPreviewPersistence {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FileVersionPreviewUtil</code> to access the file version preview persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FileVersionPreviewImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByFileEntryId;
	private FinderPath _finderPathWithoutPaginationFindByFileEntryId;
	private FinderPath _finderPathCountByFileEntryId;

	/**
	 * Returns all the file version previews where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the matching file version previews
	 */
	@Override
	public List<FileVersionPreview> findByFileEntryId(long fileEntryId) {
		return findByFileEntryId(
			fileEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the file version previews where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FileVersionPreviewModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param start the lower bound of the range of file version previews
	 * @param end the upper bound of the range of file version previews (not inclusive)
	 * @return the range of matching file version previews
	 */
	@Override
	public List<FileVersionPreview> findByFileEntryId(
		long fileEntryId, int start, int end) {

		return findByFileEntryId(fileEntryId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the file version previews where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FileVersionPreviewModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param start the lower bound of the range of file version previews
	 * @param end the upper bound of the range of file version previews (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching file version previews
	 */
	@Override
	public List<FileVersionPreview> findByFileEntryId(
		long fileEntryId, int start, int end,
		OrderByComparator<FileVersionPreview> orderByComparator) {

		return findByFileEntryId(
			fileEntryId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the file version previews where fileEntryId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FileVersionPreviewModelImpl</code>.
	 * </p>
	 *
	 * @param fileEntryId the file entry ID
	 * @param start the lower bound of the range of file version previews
	 * @param end the upper bound of the range of file version previews (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching file version previews
	 */
	@Override
	public List<FileVersionPreview> findByFileEntryId(
		long fileEntryId, int start, int end,
		OrderByComparator<FileVersionPreview> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByFileEntryId;
				finderArgs = new Object[] {fileEntryId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByFileEntryId;
			finderArgs = new Object[] {
				fileEntryId, start, end, orderByComparator
			};
		}

		List<FileVersionPreview> list = null;

		if (useFinderCache) {
			list = (List<FileVersionPreview>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FileVersionPreview fileVersionPreview : list) {
					if (fileEntryId != fileVersionPreview.getFileEntryId()) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_FILEVERSIONPREVIEW_WHERE);

			query.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(FileVersionPreviewModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fileEntryId);

				list = (List<FileVersionPreview>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first file version preview in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file version preview
	 * @throws NoSuchFileVersionPreviewException if a matching file version preview could not be found
	 */
	@Override
	public FileVersionPreview findByFileEntryId_First(
			long fileEntryId,
			OrderByComparator<FileVersionPreview> orderByComparator)
		throws NoSuchFileVersionPreviewException {

		FileVersionPreview fileVersionPreview = fetchByFileEntryId_First(
			fileEntryId, orderByComparator);

		if (fileVersionPreview != null) {
			return fileVersionPreview;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fileEntryId=");
		msg.append(fileEntryId);

		msg.append("}");

		throw new NoSuchFileVersionPreviewException(msg.toString());
	}

	/**
	 * Returns the first file version preview in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file version preview, or <code>null</code> if a matching file version preview could not be found
	 */
	@Override
	public FileVersionPreview fetchByFileEntryId_First(
		long fileEntryId,
		OrderByComparator<FileVersionPreview> orderByComparator) {

		List<FileVersionPreview> list = findByFileEntryId(
			fileEntryId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last file version preview in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file version preview
	 * @throws NoSuchFileVersionPreviewException if a matching file version preview could not be found
	 */
	@Override
	public FileVersionPreview findByFileEntryId_Last(
			long fileEntryId,
			OrderByComparator<FileVersionPreview> orderByComparator)
		throws NoSuchFileVersionPreviewException {

		FileVersionPreview fileVersionPreview = fetchByFileEntryId_Last(
			fileEntryId, orderByComparator);

		if (fileVersionPreview != null) {
			return fileVersionPreview;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fileEntryId=");
		msg.append(fileEntryId);

		msg.append("}");

		throw new NoSuchFileVersionPreviewException(msg.toString());
	}

	/**
	 * Returns the last file version preview in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file version preview, or <code>null</code> if a matching file version preview could not be found
	 */
	@Override
	public FileVersionPreview fetchByFileEntryId_Last(
		long fileEntryId,
		OrderByComparator<FileVersionPreview> orderByComparator) {

		int count = countByFileEntryId(fileEntryId);

		if (count == 0) {
			return null;
		}

		List<FileVersionPreview> list = findByFileEntryId(
			fileEntryId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the file version previews before and after the current file version preview in the ordered set where fileEntryId = &#63;.
	 *
	 * @param fileVersionPreviewId the primary key of the current file version preview
	 * @param fileEntryId the file entry ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next file version preview
	 * @throws NoSuchFileVersionPreviewException if a file version preview with the primary key could not be found
	 */
	@Override
	public FileVersionPreview[] findByFileEntryId_PrevAndNext(
			long fileVersionPreviewId, long fileEntryId,
			OrderByComparator<FileVersionPreview> orderByComparator)
		throws NoSuchFileVersionPreviewException {

		FileVersionPreview fileVersionPreview = findByPrimaryKey(
			fileVersionPreviewId);

		Session session = null;

		try {
			session = openSession();

			FileVersionPreview[] array = new FileVersionPreviewImpl[3];

			array[0] = getByFileEntryId_PrevAndNext(
				session, fileVersionPreview, fileEntryId, orderByComparator,
				true);

			array[1] = fileVersionPreview;

			array[2] = getByFileEntryId_PrevAndNext(
				session, fileVersionPreview, fileEntryId, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FileVersionPreview getByFileEntryId_PrevAndNext(
		Session session, FileVersionPreview fileVersionPreview,
		long fileEntryId,
		OrderByComparator<FileVersionPreview> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FILEVERSIONPREVIEW_WHERE);

		query.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(FileVersionPreviewModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(fileEntryId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						fileVersionPreview)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<FileVersionPreview> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the file version previews where fileEntryId = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 */
	@Override
	public void removeByFileEntryId(long fileEntryId) {
		for (FileVersionPreview fileVersionPreview :
				findByFileEntryId(
					fileEntryId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(fileVersionPreview);
		}
	}

	/**
	 * Returns the number of file version previews where fileEntryId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @return the number of matching file version previews
	 */
	@Override
	public int countByFileEntryId(long fileEntryId) {
		FinderPath finderPath = _finderPathCountByFileEntryId;

		Object[] finderArgs = new Object[] {fileEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FILEVERSIONPREVIEW_WHERE);

			query.append(_FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fileEntryId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_FILEENTRYID_FILEENTRYID_2 =
		"fileVersionPreview.fileEntryId = ?";

	private FinderPath _finderPathWithPaginationFindByFileVersionId;
	private FinderPath _finderPathWithoutPaginationFindByFileVersionId;
	private FinderPath _finderPathCountByFileVersionId;

	/**
	 * Returns all the file version previews where fileVersionId = &#63;.
	 *
	 * @param fileVersionId the file version ID
	 * @return the matching file version previews
	 */
	@Override
	public List<FileVersionPreview> findByFileVersionId(long fileVersionId) {
		return findByFileVersionId(
			fileVersionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the file version previews where fileVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FileVersionPreviewModelImpl</code>.
	 * </p>
	 *
	 * @param fileVersionId the file version ID
	 * @param start the lower bound of the range of file version previews
	 * @param end the upper bound of the range of file version previews (not inclusive)
	 * @return the range of matching file version previews
	 */
	@Override
	public List<FileVersionPreview> findByFileVersionId(
		long fileVersionId, int start, int end) {

		return findByFileVersionId(fileVersionId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the file version previews where fileVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FileVersionPreviewModelImpl</code>.
	 * </p>
	 *
	 * @param fileVersionId the file version ID
	 * @param start the lower bound of the range of file version previews
	 * @param end the upper bound of the range of file version previews (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching file version previews
	 */
	@Override
	public List<FileVersionPreview> findByFileVersionId(
		long fileVersionId, int start, int end,
		OrderByComparator<FileVersionPreview> orderByComparator) {

		return findByFileVersionId(
			fileVersionId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the file version previews where fileVersionId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FileVersionPreviewModelImpl</code>.
	 * </p>
	 *
	 * @param fileVersionId the file version ID
	 * @param start the lower bound of the range of file version previews
	 * @param end the upper bound of the range of file version previews (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching file version previews
	 */
	@Override
	public List<FileVersionPreview> findByFileVersionId(
		long fileVersionId, int start, int end,
		OrderByComparator<FileVersionPreview> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByFileVersionId;
				finderArgs = new Object[] {fileVersionId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByFileVersionId;
			finderArgs = new Object[] {
				fileVersionId, start, end, orderByComparator
			};
		}

		List<FileVersionPreview> list = null;

		if (useFinderCache) {
			list = (List<FileVersionPreview>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (FileVersionPreview fileVersionPreview : list) {
					if (fileVersionId !=
							fileVersionPreview.getFileVersionId()) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_FILEVERSIONPREVIEW_WHERE);

			query.append(_FINDER_COLUMN_FILEVERSIONID_FILEVERSIONID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(FileVersionPreviewModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fileVersionId);

				list = (List<FileVersionPreview>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first file version preview in the ordered set where fileVersionId = &#63;.
	 *
	 * @param fileVersionId the file version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file version preview
	 * @throws NoSuchFileVersionPreviewException if a matching file version preview could not be found
	 */
	@Override
	public FileVersionPreview findByFileVersionId_First(
			long fileVersionId,
			OrderByComparator<FileVersionPreview> orderByComparator)
		throws NoSuchFileVersionPreviewException {

		FileVersionPreview fileVersionPreview = fetchByFileVersionId_First(
			fileVersionId, orderByComparator);

		if (fileVersionPreview != null) {
			return fileVersionPreview;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fileVersionId=");
		msg.append(fileVersionId);

		msg.append("}");

		throw new NoSuchFileVersionPreviewException(msg.toString());
	}

	/**
	 * Returns the first file version preview in the ordered set where fileVersionId = &#63;.
	 *
	 * @param fileVersionId the file version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching file version preview, or <code>null</code> if a matching file version preview could not be found
	 */
	@Override
	public FileVersionPreview fetchByFileVersionId_First(
		long fileVersionId,
		OrderByComparator<FileVersionPreview> orderByComparator) {

		List<FileVersionPreview> list = findByFileVersionId(
			fileVersionId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last file version preview in the ordered set where fileVersionId = &#63;.
	 *
	 * @param fileVersionId the file version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file version preview
	 * @throws NoSuchFileVersionPreviewException if a matching file version preview could not be found
	 */
	@Override
	public FileVersionPreview findByFileVersionId_Last(
			long fileVersionId,
			OrderByComparator<FileVersionPreview> orderByComparator)
		throws NoSuchFileVersionPreviewException {

		FileVersionPreview fileVersionPreview = fetchByFileVersionId_Last(
			fileVersionId, orderByComparator);

		if (fileVersionPreview != null) {
			return fileVersionPreview;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("fileVersionId=");
		msg.append(fileVersionId);

		msg.append("}");

		throw new NoSuchFileVersionPreviewException(msg.toString());
	}

	/**
	 * Returns the last file version preview in the ordered set where fileVersionId = &#63;.
	 *
	 * @param fileVersionId the file version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching file version preview, or <code>null</code> if a matching file version preview could not be found
	 */
	@Override
	public FileVersionPreview fetchByFileVersionId_Last(
		long fileVersionId,
		OrderByComparator<FileVersionPreview> orderByComparator) {

		int count = countByFileVersionId(fileVersionId);

		if (count == 0) {
			return null;
		}

		List<FileVersionPreview> list = findByFileVersionId(
			fileVersionId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the file version previews before and after the current file version preview in the ordered set where fileVersionId = &#63;.
	 *
	 * @param fileVersionPreviewId the primary key of the current file version preview
	 * @param fileVersionId the file version ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next file version preview
	 * @throws NoSuchFileVersionPreviewException if a file version preview with the primary key could not be found
	 */
	@Override
	public FileVersionPreview[] findByFileVersionId_PrevAndNext(
			long fileVersionPreviewId, long fileVersionId,
			OrderByComparator<FileVersionPreview> orderByComparator)
		throws NoSuchFileVersionPreviewException {

		FileVersionPreview fileVersionPreview = findByPrimaryKey(
			fileVersionPreviewId);

		Session session = null;

		try {
			session = openSession();

			FileVersionPreview[] array = new FileVersionPreviewImpl[3];

			array[0] = getByFileVersionId_PrevAndNext(
				session, fileVersionPreview, fileVersionId, orderByComparator,
				true);

			array[1] = fileVersionPreview;

			array[2] = getByFileVersionId_PrevAndNext(
				session, fileVersionPreview, fileVersionId, orderByComparator,
				false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected FileVersionPreview getByFileVersionId_PrevAndNext(
		Session session, FileVersionPreview fileVersionPreview,
		long fileVersionId,
		OrderByComparator<FileVersionPreview> orderByComparator,
		boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_FILEVERSIONPREVIEW_WHERE);

		query.append(_FINDER_COLUMN_FILEVERSIONID_FILEVERSIONID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(FileVersionPreviewModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(fileVersionId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						fileVersionPreview)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<FileVersionPreview> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the file version previews where fileVersionId = &#63; from the database.
	 *
	 * @param fileVersionId the file version ID
	 */
	@Override
	public void removeByFileVersionId(long fileVersionId) {
		for (FileVersionPreview fileVersionPreview :
				findByFileVersionId(
					fileVersionId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(fileVersionPreview);
		}
	}

	/**
	 * Returns the number of file version previews where fileVersionId = &#63;.
	 *
	 * @param fileVersionId the file version ID
	 * @return the number of matching file version previews
	 */
	@Override
	public int countByFileVersionId(long fileVersionId) {
		FinderPath finderPath = _finderPathCountByFileVersionId;

		Object[] finderArgs = new Object[] {fileVersionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_FILEVERSIONPREVIEW_WHERE);

			query.append(_FINDER_COLUMN_FILEVERSIONID_FILEVERSIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fileVersionId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_FILEVERSIONID_FILEVERSIONID_2 =
		"fileVersionPreview.fileVersionId = ?";

	private FinderPath _finderPathFetchByF_F;
	private FinderPath _finderPathCountByF_F;

	/**
	 * Returns the file version preview where fileEntryId = &#63; and fileVersionId = &#63; or throws a <code>NoSuchFileVersionPreviewException</code> if it could not be found.
	 *
	 * @param fileEntryId the file entry ID
	 * @param fileVersionId the file version ID
	 * @return the matching file version preview
	 * @throws NoSuchFileVersionPreviewException if a matching file version preview could not be found
	 */
	@Override
	public FileVersionPreview findByF_F(long fileEntryId, long fileVersionId)
		throws NoSuchFileVersionPreviewException {

		FileVersionPreview fileVersionPreview = fetchByF_F(
			fileEntryId, fileVersionId);

		if (fileVersionPreview == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("fileEntryId=");
			msg.append(fileEntryId);

			msg.append(", fileVersionId=");
			msg.append(fileVersionId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchFileVersionPreviewException(msg.toString());
		}

		return fileVersionPreview;
	}

	/**
	 * Returns the file version preview where fileEntryId = &#63; and fileVersionId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param fileEntryId the file entry ID
	 * @param fileVersionId the file version ID
	 * @return the matching file version preview, or <code>null</code> if a matching file version preview could not be found
	 */
	@Override
	public FileVersionPreview fetchByF_F(long fileEntryId, long fileVersionId) {
		return fetchByF_F(fileEntryId, fileVersionId, true);
	}

	/**
	 * Returns the file version preview where fileEntryId = &#63; and fileVersionId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param fileEntryId the file entry ID
	 * @param fileVersionId the file version ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching file version preview, or <code>null</code> if a matching file version preview could not be found
	 */
	@Override
	public FileVersionPreview fetchByF_F(
		long fileEntryId, long fileVersionId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {fileEntryId, fileVersionId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByF_F, finderArgs, this);
		}

		if (result instanceof FileVersionPreview) {
			FileVersionPreview fileVersionPreview = (FileVersionPreview)result;

			if ((fileEntryId != fileVersionPreview.getFileEntryId()) ||
				(fileVersionId != fileVersionPreview.getFileVersionId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_FILEVERSIONPREVIEW_WHERE);

			query.append(_FINDER_COLUMN_F_F_FILEENTRYID_2);

			query.append(_FINDER_COLUMN_F_F_FILEVERSIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fileEntryId);

				qPos.add(fileVersionId);

				List<FileVersionPreview> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByF_F, finderArgs, list);
					}
				}
				else {
					FileVersionPreview fileVersionPreview = list.get(0);

					result = fileVersionPreview;

					cacheResult(fileVersionPreview);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(_finderPathFetchByF_F, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (FileVersionPreview)result;
		}
	}

	/**
	 * Removes the file version preview where fileEntryId = &#63; and fileVersionId = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 * @param fileVersionId the file version ID
	 * @return the file version preview that was removed
	 */
	@Override
	public FileVersionPreview removeByF_F(long fileEntryId, long fileVersionId)
		throws NoSuchFileVersionPreviewException {

		FileVersionPreview fileVersionPreview = findByF_F(
			fileEntryId, fileVersionId);

		return remove(fileVersionPreview);
	}

	/**
	 * Returns the number of file version previews where fileEntryId = &#63; and fileVersionId = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param fileVersionId the file version ID
	 * @return the number of matching file version previews
	 */
	@Override
	public int countByF_F(long fileEntryId, long fileVersionId) {
		FinderPath finderPath = _finderPathCountByF_F;

		Object[] finderArgs = new Object[] {fileEntryId, fileVersionId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_FILEVERSIONPREVIEW_WHERE);

			query.append(_FINDER_COLUMN_F_F_FILEENTRYID_2);

			query.append(_FINDER_COLUMN_F_F_FILEVERSIONID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fileEntryId);

				qPos.add(fileVersionId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_F_F_FILEENTRYID_2 =
		"fileVersionPreview.fileEntryId = ? AND ";

	private static final String _FINDER_COLUMN_F_F_FILEVERSIONID_2 =
		"fileVersionPreview.fileVersionId = ?";

	private FinderPath _finderPathFetchByF_F_P;
	private FinderPath _finderPathCountByF_F_P;

	/**
	 * Returns the file version preview where fileEntryId = &#63; and fileVersionId = &#63; and previewStatus = &#63; or throws a <code>NoSuchFileVersionPreviewException</code> if it could not be found.
	 *
	 * @param fileEntryId the file entry ID
	 * @param fileVersionId the file version ID
	 * @param previewStatus the preview status
	 * @return the matching file version preview
	 * @throws NoSuchFileVersionPreviewException if a matching file version preview could not be found
	 */
	@Override
	public FileVersionPreview findByF_F_P(
			long fileEntryId, long fileVersionId, int previewStatus)
		throws NoSuchFileVersionPreviewException {

		FileVersionPreview fileVersionPreview = fetchByF_F_P(
			fileEntryId, fileVersionId, previewStatus);

		if (fileVersionPreview == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("fileEntryId=");
			msg.append(fileEntryId);

			msg.append(", fileVersionId=");
			msg.append(fileVersionId);

			msg.append(", previewStatus=");
			msg.append(previewStatus);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchFileVersionPreviewException(msg.toString());
		}

		return fileVersionPreview;
	}

	/**
	 * Returns the file version preview where fileEntryId = &#63; and fileVersionId = &#63; and previewStatus = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param fileEntryId the file entry ID
	 * @param fileVersionId the file version ID
	 * @param previewStatus the preview status
	 * @return the matching file version preview, or <code>null</code> if a matching file version preview could not be found
	 */
	@Override
	public FileVersionPreview fetchByF_F_P(
		long fileEntryId, long fileVersionId, int previewStatus) {

		return fetchByF_F_P(fileEntryId, fileVersionId, previewStatus, true);
	}

	/**
	 * Returns the file version preview where fileEntryId = &#63; and fileVersionId = &#63; and previewStatus = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param fileEntryId the file entry ID
	 * @param fileVersionId the file version ID
	 * @param previewStatus the preview status
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching file version preview, or <code>null</code> if a matching file version preview could not be found
	 */
	@Override
	public FileVersionPreview fetchByF_F_P(
		long fileEntryId, long fileVersionId, int previewStatus,
		boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {
				fileEntryId, fileVersionId, previewStatus
			};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByF_F_P, finderArgs, this);
		}

		if (result instanceof FileVersionPreview) {
			FileVersionPreview fileVersionPreview = (FileVersionPreview)result;

			if ((fileEntryId != fileVersionPreview.getFileEntryId()) ||
				(fileVersionId != fileVersionPreview.getFileVersionId()) ||
				(previewStatus != fileVersionPreview.getPreviewStatus())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_FILEVERSIONPREVIEW_WHERE);

			query.append(_FINDER_COLUMN_F_F_P_FILEENTRYID_2);

			query.append(_FINDER_COLUMN_F_F_P_FILEVERSIONID_2);

			query.append(_FINDER_COLUMN_F_F_P_PREVIEWSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fileEntryId);

				qPos.add(fileVersionId);

				qPos.add(previewStatus);

				List<FileVersionPreview> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByF_F_P, finderArgs, list);
					}
				}
				else {
					FileVersionPreview fileVersionPreview = list.get(0);

					result = fileVersionPreview;

					cacheResult(fileVersionPreview);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByF_F_P, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (FileVersionPreview)result;
		}
	}

	/**
	 * Removes the file version preview where fileEntryId = &#63; and fileVersionId = &#63; and previewStatus = &#63; from the database.
	 *
	 * @param fileEntryId the file entry ID
	 * @param fileVersionId the file version ID
	 * @param previewStatus the preview status
	 * @return the file version preview that was removed
	 */
	@Override
	public FileVersionPreview removeByF_F_P(
			long fileEntryId, long fileVersionId, int previewStatus)
		throws NoSuchFileVersionPreviewException {

		FileVersionPreview fileVersionPreview = findByF_F_P(
			fileEntryId, fileVersionId, previewStatus);

		return remove(fileVersionPreview);
	}

	/**
	 * Returns the number of file version previews where fileEntryId = &#63; and fileVersionId = &#63; and previewStatus = &#63;.
	 *
	 * @param fileEntryId the file entry ID
	 * @param fileVersionId the file version ID
	 * @param previewStatus the preview status
	 * @return the number of matching file version previews
	 */
	@Override
	public int countByF_F_P(
		long fileEntryId, long fileVersionId, int previewStatus) {

		FinderPath finderPath = _finderPathCountByF_F_P;

		Object[] finderArgs = new Object[] {
			fileEntryId, fileVersionId, previewStatus
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_FILEVERSIONPREVIEW_WHERE);

			query.append(_FINDER_COLUMN_F_F_P_FILEENTRYID_2);

			query.append(_FINDER_COLUMN_F_F_P_FILEVERSIONID_2);

			query.append(_FINDER_COLUMN_F_F_P_PREVIEWSTATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(fileEntryId);

				qPos.add(fileVersionId);

				qPos.add(previewStatus);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_F_F_P_FILEENTRYID_2 =
		"fileVersionPreview.fileEntryId = ? AND ";

	private static final String _FINDER_COLUMN_F_F_P_FILEVERSIONID_2 =
		"fileVersionPreview.fileVersionId = ? AND ";

	private static final String _FINDER_COLUMN_F_F_P_PREVIEWSTATUS_2 =
		"fileVersionPreview.previewStatus = ?";

	public FileVersionPreviewPersistenceImpl() {
		setModelClass(FileVersionPreview.class);
	}

	/**
	 * Caches the file version preview in the entity cache if it is enabled.
	 *
	 * @param fileVersionPreview the file version preview
	 */
	@Override
	public void cacheResult(FileVersionPreview fileVersionPreview) {
		entityCache.putResult(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewImpl.class, fileVersionPreview.getPrimaryKey(),
			fileVersionPreview);

		finderCache.putResult(
			_finderPathFetchByF_F,
			new Object[] {
				fileVersionPreview.getFileEntryId(),
				fileVersionPreview.getFileVersionId()
			},
			fileVersionPreview);

		finderCache.putResult(
			_finderPathFetchByF_F_P,
			new Object[] {
				fileVersionPreview.getFileEntryId(),
				fileVersionPreview.getFileVersionId(),
				fileVersionPreview.getPreviewStatus()
			},
			fileVersionPreview);

		fileVersionPreview.resetOriginalValues();
	}

	/**
	 * Caches the file version previews in the entity cache if it is enabled.
	 *
	 * @param fileVersionPreviews the file version previews
	 */
	@Override
	public void cacheResult(List<FileVersionPreview> fileVersionPreviews) {
		for (FileVersionPreview fileVersionPreview : fileVersionPreviews) {
			if (entityCache.getResult(
					FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
					FileVersionPreviewImpl.class,
					fileVersionPreview.getPrimaryKey()) == null) {

				cacheResult(fileVersionPreview);
			}
			else {
				fileVersionPreview.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all file version previews.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FileVersionPreviewImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the file version preview.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FileVersionPreview fileVersionPreview) {
		entityCache.removeResult(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewImpl.class, fileVersionPreview.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(FileVersionPreviewModelImpl)fileVersionPreview, true);
	}

	@Override
	public void clearCache(List<FileVersionPreview> fileVersionPreviews) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (FileVersionPreview fileVersionPreview : fileVersionPreviews) {
			entityCache.removeResult(
				FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
				FileVersionPreviewImpl.class,
				fileVersionPreview.getPrimaryKey());

			clearUniqueFindersCache(
				(FileVersionPreviewModelImpl)fileVersionPreview, true);
		}
	}

	protected void cacheUniqueFindersCache(
		FileVersionPreviewModelImpl fileVersionPreviewModelImpl) {

		Object[] args = new Object[] {
			fileVersionPreviewModelImpl.getFileEntryId(),
			fileVersionPreviewModelImpl.getFileVersionId()
		};

		finderCache.putResult(
			_finderPathCountByF_F, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByF_F, args, fileVersionPreviewModelImpl, false);

		args = new Object[] {
			fileVersionPreviewModelImpl.getFileEntryId(),
			fileVersionPreviewModelImpl.getFileVersionId(),
			fileVersionPreviewModelImpl.getPreviewStatus()
		};

		finderCache.putResult(
			_finderPathCountByF_F_P, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByF_F_P, args, fileVersionPreviewModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		FileVersionPreviewModelImpl fileVersionPreviewModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				fileVersionPreviewModelImpl.getFileEntryId(),
				fileVersionPreviewModelImpl.getFileVersionId()
			};

			finderCache.removeResult(_finderPathCountByF_F, args);
			finderCache.removeResult(_finderPathFetchByF_F, args);
		}

		if ((fileVersionPreviewModelImpl.getColumnBitmask() &
			 _finderPathFetchByF_F.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				fileVersionPreviewModelImpl.getOriginalFileEntryId(),
				fileVersionPreviewModelImpl.getOriginalFileVersionId()
			};

			finderCache.removeResult(_finderPathCountByF_F, args);
			finderCache.removeResult(_finderPathFetchByF_F, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				fileVersionPreviewModelImpl.getFileEntryId(),
				fileVersionPreviewModelImpl.getFileVersionId(),
				fileVersionPreviewModelImpl.getPreviewStatus()
			};

			finderCache.removeResult(_finderPathCountByF_F_P, args);
			finderCache.removeResult(_finderPathFetchByF_F_P, args);
		}

		if ((fileVersionPreviewModelImpl.getColumnBitmask() &
			 _finderPathFetchByF_F_P.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				fileVersionPreviewModelImpl.getOriginalFileEntryId(),
				fileVersionPreviewModelImpl.getOriginalFileVersionId(),
				fileVersionPreviewModelImpl.getOriginalPreviewStatus()
			};

			finderCache.removeResult(_finderPathCountByF_F_P, args);
			finderCache.removeResult(_finderPathFetchByF_F_P, args);
		}
	}

	/**
	 * Creates a new file version preview with the primary key. Does not add the file version preview to the database.
	 *
	 * @param fileVersionPreviewId the primary key for the new file version preview
	 * @return the new file version preview
	 */
	@Override
	public FileVersionPreview create(long fileVersionPreviewId) {
		FileVersionPreview fileVersionPreview = new FileVersionPreviewImpl();

		fileVersionPreview.setNew(true);
		fileVersionPreview.setPrimaryKey(fileVersionPreviewId);

		return fileVersionPreview;
	}

	/**
	 * Removes the file version preview with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fileVersionPreviewId the primary key of the file version preview
	 * @return the file version preview that was removed
	 * @throws NoSuchFileVersionPreviewException if a file version preview with the primary key could not be found
	 */
	@Override
	public FileVersionPreview remove(long fileVersionPreviewId)
		throws NoSuchFileVersionPreviewException {

		return remove((Serializable)fileVersionPreviewId);
	}

	/**
	 * Removes the file version preview with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the file version preview
	 * @return the file version preview that was removed
	 * @throws NoSuchFileVersionPreviewException if a file version preview with the primary key could not be found
	 */
	@Override
	public FileVersionPreview remove(Serializable primaryKey)
		throws NoSuchFileVersionPreviewException {

		Session session = null;

		try {
			session = openSession();

			FileVersionPreview fileVersionPreview =
				(FileVersionPreview)session.get(
					FileVersionPreviewImpl.class, primaryKey);

			if (fileVersionPreview == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchFileVersionPreviewException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(fileVersionPreview);
		}
		catch (NoSuchFileVersionPreviewException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected FileVersionPreview removeImpl(
		FileVersionPreview fileVersionPreview) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(fileVersionPreview)) {
				fileVersionPreview = (FileVersionPreview)session.get(
					FileVersionPreviewImpl.class,
					fileVersionPreview.getPrimaryKeyObj());
			}

			if (fileVersionPreview != null) {
				session.delete(fileVersionPreview);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (fileVersionPreview != null) {
			clearCache(fileVersionPreview);
		}

		return fileVersionPreview;
	}

	@Override
	public FileVersionPreview updateImpl(
		FileVersionPreview fileVersionPreview) {

		boolean isNew = fileVersionPreview.isNew();

		if (!(fileVersionPreview instanceof FileVersionPreviewModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(fileVersionPreview.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					fileVersionPreview);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in fileVersionPreview proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom FileVersionPreview implementation " +
					fileVersionPreview.getClass());
		}

		FileVersionPreviewModelImpl fileVersionPreviewModelImpl =
			(FileVersionPreviewModelImpl)fileVersionPreview;

		Session session = null;

		try {
			session = openSession();

			if (fileVersionPreview.isNew()) {
				session.save(fileVersionPreview);

				fileVersionPreview.setNew(false);
			}
			else {
				fileVersionPreview = (FileVersionPreview)session.merge(
					fileVersionPreview);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!FileVersionPreviewModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {
				fileVersionPreviewModelImpl.getFileEntryId()
			};

			finderCache.removeResult(_finderPathCountByFileEntryId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByFileEntryId, args);

			args = new Object[] {
				fileVersionPreviewModelImpl.getFileVersionId()
			};

			finderCache.removeResult(_finderPathCountByFileVersionId, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByFileVersionId, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((fileVersionPreviewModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByFileEntryId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					fileVersionPreviewModelImpl.getOriginalFileEntryId()
				};

				finderCache.removeResult(_finderPathCountByFileEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByFileEntryId, args);

				args = new Object[] {
					fileVersionPreviewModelImpl.getFileEntryId()
				};

				finderCache.removeResult(_finderPathCountByFileEntryId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByFileEntryId, args);
			}

			if ((fileVersionPreviewModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByFileVersionId.
					 getColumnBitmask()) != 0) {

				Object[] args = new Object[] {
					fileVersionPreviewModelImpl.getOriginalFileVersionId()
				};

				finderCache.removeResult(_finderPathCountByFileVersionId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByFileVersionId, args);

				args = new Object[] {
					fileVersionPreviewModelImpl.getFileVersionId()
				};

				finderCache.removeResult(_finderPathCountByFileVersionId, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByFileVersionId, args);
			}
		}

		entityCache.putResult(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewImpl.class, fileVersionPreview.getPrimaryKey(),
			fileVersionPreview, false);

		clearUniqueFindersCache(fileVersionPreviewModelImpl, false);
		cacheUniqueFindersCache(fileVersionPreviewModelImpl);

		fileVersionPreview.resetOriginalValues();

		return fileVersionPreview;
	}

	/**
	 * Returns the file version preview with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the file version preview
	 * @return the file version preview
	 * @throws NoSuchFileVersionPreviewException if a file version preview with the primary key could not be found
	 */
	@Override
	public FileVersionPreview findByPrimaryKey(Serializable primaryKey)
		throws NoSuchFileVersionPreviewException {

		FileVersionPreview fileVersionPreview = fetchByPrimaryKey(primaryKey);

		if (fileVersionPreview == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchFileVersionPreviewException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return fileVersionPreview;
	}

	/**
	 * Returns the file version preview with the primary key or throws a <code>NoSuchFileVersionPreviewException</code> if it could not be found.
	 *
	 * @param fileVersionPreviewId the primary key of the file version preview
	 * @return the file version preview
	 * @throws NoSuchFileVersionPreviewException if a file version preview with the primary key could not be found
	 */
	@Override
	public FileVersionPreview findByPrimaryKey(long fileVersionPreviewId)
		throws NoSuchFileVersionPreviewException {

		return findByPrimaryKey((Serializable)fileVersionPreviewId);
	}

	/**
	 * Returns the file version preview with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the file version preview
	 * @return the file version preview, or <code>null</code> if a file version preview with the primary key could not be found
	 */
	@Override
	public FileVersionPreview fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		FileVersionPreview fileVersionPreview =
			(FileVersionPreview)serializable;

		if (fileVersionPreview == null) {
			Session session = null;

			try {
				session = openSession();

				fileVersionPreview = (FileVersionPreview)session.get(
					FileVersionPreviewImpl.class, primaryKey);

				if (fileVersionPreview != null) {
					cacheResult(fileVersionPreview);
				}
				else {
					entityCache.putResult(
						FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
						FileVersionPreviewImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(
					FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
					FileVersionPreviewImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return fileVersionPreview;
	}

	/**
	 * Returns the file version preview with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fileVersionPreviewId the primary key of the file version preview
	 * @return the file version preview, or <code>null</code> if a file version preview with the primary key could not be found
	 */
	@Override
	public FileVersionPreview fetchByPrimaryKey(long fileVersionPreviewId) {
		return fetchByPrimaryKey((Serializable)fileVersionPreviewId);
	}

	@Override
	public Map<Serializable, FileVersionPreview> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, FileVersionPreview> map =
			new HashMap<Serializable, FileVersionPreview>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			FileVersionPreview fileVersionPreview = fetchByPrimaryKey(
				primaryKey);

			if (fileVersionPreview != null) {
				map.put(primaryKey, fileVersionPreview);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
				FileVersionPreviewImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (FileVersionPreview)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_FILEVERSIONPREVIEW_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(",");
		}

		query.setIndex(query.index() - 1);

		query.append(")");

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (FileVersionPreview fileVersionPreview :
					(List<FileVersionPreview>)q.list()) {

				map.put(
					fileVersionPreview.getPrimaryKeyObj(), fileVersionPreview);

				cacheResult(fileVersionPreview);

				uncachedPrimaryKeys.remove(
					fileVersionPreview.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
					FileVersionPreviewImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the file version previews.
	 *
	 * @return the file version previews
	 */
	@Override
	public List<FileVersionPreview> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the file version previews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FileVersionPreviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of file version previews
	 * @param end the upper bound of the range of file version previews (not inclusive)
	 * @return the range of file version previews
	 */
	@Override
	public List<FileVersionPreview> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the file version previews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FileVersionPreviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of file version previews
	 * @param end the upper bound of the range of file version previews (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of file version previews
	 */
	@Override
	public List<FileVersionPreview> findAll(
		int start, int end,
		OrderByComparator<FileVersionPreview> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the file version previews.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FileVersionPreviewModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of file version previews
	 * @param end the upper bound of the range of file version previews (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of file version previews
	 */
	@Override
	public List<FileVersionPreview> findAll(
		int start, int end,
		OrderByComparator<FileVersionPreview> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<FileVersionPreview> list = null;

		if (useFinderCache) {
			list = (List<FileVersionPreview>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_FILEVERSIONPREVIEW);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_FILEVERSIONPREVIEW;

				sql = sql.concat(FileVersionPreviewModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				list = (List<FileVersionPreview>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the file version previews from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FileVersionPreview fileVersionPreview : findAll()) {
			remove(fileVersionPreview);
		}
	}

	/**
	 * Returns the number of file version previews.
	 *
	 * @return the number of file version previews
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_FILEVERSIONPREVIEW);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FileVersionPreviewModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the file version preview persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewModelImpl.FINDER_CACHE_ENABLED,
			FileVersionPreviewImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewModelImpl.FINDER_CACHE_ENABLED,
			FileVersionPreviewImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByFileEntryId = new FinderPath(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewModelImpl.FINDER_CACHE_ENABLED,
			FileVersionPreviewImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFileEntryId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByFileEntryId = new FinderPath(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewModelImpl.FINDER_CACHE_ENABLED,
			FileVersionPreviewImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFileEntryId",
			new String[] {Long.class.getName()},
			FileVersionPreviewModelImpl.FILEENTRYID_COLUMN_BITMASK);

		_finderPathCountByFileEntryId = new FinderPath(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFileEntryId",
			new String[] {Long.class.getName()});

		_finderPathWithPaginationFindByFileVersionId = new FinderPath(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewModelImpl.FINDER_CACHE_ENABLED,
			FileVersionPreviewImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFileVersionId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByFileVersionId = new FinderPath(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewModelImpl.FINDER_CACHE_ENABLED,
			FileVersionPreviewImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByFileVersionId",
			new String[] {Long.class.getName()},
			FileVersionPreviewModelImpl.FILEVERSIONID_COLUMN_BITMASK);

		_finderPathCountByFileVersionId = new FinderPath(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFileVersionId",
			new String[] {Long.class.getName()});

		_finderPathFetchByF_F = new FinderPath(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewModelImpl.FINDER_CACHE_ENABLED,
			FileVersionPreviewImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_F",
			new String[] {Long.class.getName(), Long.class.getName()},
			FileVersionPreviewModelImpl.FILEENTRYID_COLUMN_BITMASK |
			FileVersionPreviewModelImpl.FILEVERSIONID_COLUMN_BITMASK);

		_finderPathCountByF_F = new FinderPath(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_F",
			new String[] {Long.class.getName(), Long.class.getName()});

		_finderPathFetchByF_F_P = new FinderPath(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewModelImpl.FINDER_CACHE_ENABLED,
			FileVersionPreviewImpl.class, FINDER_CLASS_NAME_ENTITY,
			"fetchByF_F_P",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			},
			FileVersionPreviewModelImpl.FILEENTRYID_COLUMN_BITMASK |
			FileVersionPreviewModelImpl.FILEVERSIONID_COLUMN_BITMASK |
			FileVersionPreviewModelImpl.PREVIEWSTATUS_COLUMN_BITMASK);

		_finderPathCountByF_F_P = new FinderPath(
			FileVersionPreviewModelImpl.ENTITY_CACHE_ENABLED,
			FileVersionPreviewModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByF_F_P",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName()
			});
	}

	public void destroy() {
		entityCache.removeCache(FileVersionPreviewImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_FILEVERSIONPREVIEW =
		"SELECT fileVersionPreview FROM FileVersionPreview fileVersionPreview";

	private static final String _SQL_SELECT_FILEVERSIONPREVIEW_WHERE_PKS_IN =
		"SELECT fileVersionPreview FROM FileVersionPreview fileVersionPreview WHERE fileVersionPreviewId IN (";

	private static final String _SQL_SELECT_FILEVERSIONPREVIEW_WHERE =
		"SELECT fileVersionPreview FROM FileVersionPreview fileVersionPreview WHERE ";

	private static final String _SQL_COUNT_FILEVERSIONPREVIEW =
		"SELECT COUNT(fileVersionPreview) FROM FileVersionPreview fileVersionPreview";

	private static final String _SQL_COUNT_FILEVERSIONPREVIEW_WHERE =
		"SELECT COUNT(fileVersionPreview) FROM FileVersionPreview fileVersionPreview WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "fileVersionPreview.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No FileVersionPreview exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No FileVersionPreview exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		FileVersionPreviewPersistenceImpl.class);

}