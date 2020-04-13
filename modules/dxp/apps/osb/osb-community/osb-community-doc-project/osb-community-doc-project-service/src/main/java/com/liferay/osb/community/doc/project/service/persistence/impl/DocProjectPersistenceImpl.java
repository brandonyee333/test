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

package com.liferay.osb.community.doc.project.service.persistence.impl;

import com.liferay.osb.community.doc.project.exception.NoSuchDocProjectException;
import com.liferay.osb.community.doc.project.model.DocProject;
import com.liferay.osb.community.doc.project.model.impl.DocProjectImpl;
import com.liferay.osb.community.doc.project.model.impl.DocProjectModelImpl;
import com.liferay.osb.community.doc.project.service.persistence.DocProjectPersistence;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the doc project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Ryan Park
 * @generated
 */
public class DocProjectPersistenceImpl
	extends BasePersistenceImpl<DocProject> implements DocProjectPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>DocProjectUtil</code> to access the doc project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		DocProjectImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the doc projects where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching doc projects
	 */
	@Override
	public List<DocProject> findByUuid(String uuid) {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the doc projects where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @return the range of matching doc projects
	 */
	@Override
	public List<DocProject> findByUuid(String uuid, int start, int end) {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the doc projects where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching doc projects
	 */
	@Override
	public List<DocProject> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<DocProject> orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the doc projects where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching doc projects
	 */
	@Override
	public List<DocProject> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<DocProject> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<DocProject> list = null;

		if (useFinderCache) {
			list = (List<DocProject>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DocProject docProject : list) {
					if (!uuid.equals(docProject.getUuid())) {
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

			query.append(_SQL_SELECT_DOCPROJECT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(DocProjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				list = (List<DocProject>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first doc project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	@Override
	public DocProject findByUuid_First(
			String uuid, OrderByComparator<DocProject> orderByComparator)
		throws NoSuchDocProjectException {

		DocProject docProject = fetchByUuid_First(uuid, orderByComparator);

		if (docProject != null) {
			return docProject;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDocProjectException(msg.toString());
	}

	/**
	 * Returns the first doc project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	@Override
	public DocProject fetchByUuid_First(
		String uuid, OrderByComparator<DocProject> orderByComparator) {

		List<DocProject> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last doc project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	@Override
	public DocProject findByUuid_Last(
			String uuid, OrderByComparator<DocProject> orderByComparator)
		throws NoSuchDocProjectException {

		DocProject docProject = fetchByUuid_Last(uuid, orderByComparator);

		if (docProject != null) {
			return docProject;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append("}");

		throw new NoSuchDocProjectException(msg.toString());
	}

	/**
	 * Returns the last doc project in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	@Override
	public DocProject fetchByUuid_Last(
		String uuid, OrderByComparator<DocProject> orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<DocProject> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the doc projects before and after the current doc project in the ordered set where uuid = &#63;.
	 *
	 * @param docProjectId the primary key of the current doc project
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next doc project
	 * @throws NoSuchDocProjectException if a doc project with the primary key could not be found
	 */
	@Override
	public DocProject[] findByUuid_PrevAndNext(
			long docProjectId, String uuid,
			OrderByComparator<DocProject> orderByComparator)
		throws NoSuchDocProjectException {

		uuid = Objects.toString(uuid, "");

		DocProject docProject = findByPrimaryKey(docProjectId);

		Session session = null;

		try {
			session = openSession();

			DocProject[] array = new DocProjectImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, docProject, uuid, orderByComparator, true);

			array[1] = docProject;

			array[2] = getByUuid_PrevAndNext(
				session, docProject, uuid, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DocProject getByUuid_PrevAndNext(
		Session session, DocProject docProject, String uuid,
		OrderByComparator<DocProject> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DOCPROJECT_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_UUID_2);
		}

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
			query.append(DocProjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(docProject)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<DocProject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the doc projects where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (DocProject docProject :
				findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(docProject);
		}
	}

	/**
	 * Returns the number of doc projects where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching doc projects
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOCPROJECT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"docProject.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(docProject.uuid IS NULL OR docProject.uuid = '')";

	private FinderPath _finderPathFetchByUUID_G;
	private FinderPath _finderPathCountByUUID_G;

	/**
	 * Returns the doc project where uuid = &#63; and groupId = &#63; or throws a <code>NoSuchDocProjectException</code> if it could not be found.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	@Override
	public DocProject findByUUID_G(String uuid, long groupId)
		throws NoSuchDocProjectException {

		DocProject docProject = fetchByUUID_G(uuid, groupId);

		if (docProject == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("uuid=");
			msg.append(uuid);

			msg.append(", groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDocProjectException(msg.toString());
		}

		return docProject;
	}

	/**
	 * Returns the doc project where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	@Override
	public DocProject fetchByUUID_G(String uuid, long groupId) {
		return fetchByUUID_G(uuid, groupId, true);
	}

	/**
	 * Returns the doc project where uuid = &#63; and groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	@Override
	public DocProject fetchByUUID_G(
		String uuid, long groupId, boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {uuid, groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByUUID_G, finderArgs, this);
		}

		if (result instanceof DocProject) {
			DocProject docProject = (DocProject)result;

			if (!Objects.equals(uuid, docProject.getUuid()) ||
				(groupId != docProject.getGroupId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DOCPROJECT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				List<DocProject> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByUUID_G, finderArgs, list);
					}
				}
				else {
					DocProject docProject = list.get(0);

					result = docProject;

					cacheResult(docProject);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByUUID_G, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (DocProject)result;
		}
	}

	/**
	 * Removes the doc project where uuid = &#63; and groupId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the doc project that was removed
	 */
	@Override
	public DocProject removeByUUID_G(String uuid, long groupId)
		throws NoSuchDocProjectException {

		DocProject docProject = findByUUID_G(uuid, groupId);

		return remove(docProject);
	}

	/**
	 * Returns the number of doc projects where uuid = &#63; and groupId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param groupId the group ID
	 * @return the number of matching doc projects
	 */
	@Override
	public int countByUUID_G(String uuid, long groupId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUUID_G;

		Object[] finderArgs = new Object[] {uuid, groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOCPROJECT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_G_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_G_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_G_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_G_UUID_2 =
		"docProject.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_G_UUID_3 =
		"(docProject.uuid IS NULL OR docProject.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_G_GROUPID_2 =
		"docProject.groupId = ?";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the doc projects where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching doc projects
	 */
	@Override
	public List<DocProject> findByUuid_C(String uuid, long companyId) {
		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the doc projects where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @return the range of matching doc projects
	 */
	@Override
	public List<DocProject> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the doc projects where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching doc projects
	 */
	@Override
	public List<DocProject> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DocProject> orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the doc projects where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching doc projects
	 */
	@Override
	public List<DocProject> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<DocProject> orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<DocProject> list = null;

		if (useFinderCache) {
			list = (List<DocProject>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DocProject docProject : list) {
					if (!uuid.equals(docProject.getUuid()) ||
						(companyId != docProject.getCompanyId())) {

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
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DOCPROJECT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(DocProjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				list = (List<DocProject>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first doc project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	@Override
	public DocProject findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<DocProject> orderByComparator)
		throws NoSuchDocProjectException {

		DocProject docProject = fetchByUuid_C_First(
			uuid, companyId, orderByComparator);

		if (docProject != null) {
			return docProject;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDocProjectException(msg.toString());
	}

	/**
	 * Returns the first doc project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	@Override
	public DocProject fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<DocProject> orderByComparator) {

		List<DocProject> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last doc project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	@Override
	public DocProject findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<DocProject> orderByComparator)
		throws NoSuchDocProjectException {

		DocProject docProject = fetchByUuid_C_Last(
			uuid, companyId, orderByComparator);

		if (docProject != null) {
			return docProject;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(", companyId=");
		msg.append(companyId);

		msg.append("}");

		throw new NoSuchDocProjectException(msg.toString());
	}

	/**
	 * Returns the last doc project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	@Override
	public DocProject fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<DocProject> orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<DocProject> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the doc projects before and after the current doc project in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param docProjectId the primary key of the current doc project
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next doc project
	 * @throws NoSuchDocProjectException if a doc project with the primary key could not be found
	 */
	@Override
	public DocProject[] findByUuid_C_PrevAndNext(
			long docProjectId, String uuid, long companyId,
			OrderByComparator<DocProject> orderByComparator)
		throws NoSuchDocProjectException {

		uuid = Objects.toString(uuid, "");

		DocProject docProject = findByPrimaryKey(docProjectId);

		Session session = null;

		try {
			session = openSession();

			DocProject[] array = new DocProjectImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, docProject, uuid, companyId, orderByComparator, true);

			array[1] = docProject;

			array[2] = getByUuid_C_PrevAndNext(
				session, docProject, uuid, companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DocProject getByUuid_C_PrevAndNext(
		Session session, DocProject docProject, String uuid, long companyId,
		OrderByComparator<DocProject> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOCPROJECT_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			query.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			query.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			query.append(DocProjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindUuid) {
			qPos.add(uuid);
		}

		qPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(docProject)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<DocProject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the doc projects where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (DocProject docProject :
				findByUuid_C(
					uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(docProject);
		}
	}

	/**
	 * Returns the number of doc projects where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching doc projects
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOCPROJECT_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				query.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				query.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			query.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindUuid) {
					qPos.add(uuid);
				}

				qPos.add(companyId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"docProject.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(docProject.uuid IS NULL OR docProject.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"docProject.companyId = ?";

	private FinderPath _finderPathFetchByGroupId;
	private FinderPath _finderPathCountByGroupId;

	/**
	 * Returns the doc project where groupId = &#63; or throws a <code>NoSuchDocProjectException</code> if it could not be found.
	 *
	 * @param groupId the group ID
	 * @return the matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	@Override
	public DocProject findByGroupId(long groupId)
		throws NoSuchDocProjectException {

		DocProject docProject = fetchByGroupId(groupId);

		if (docProject == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("groupId=");
			msg.append(groupId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDocProjectException(msg.toString());
		}

		return docProject;
	}

	/**
	 * Returns the doc project where groupId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param groupId the group ID
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	@Override
	public DocProject fetchByGroupId(long groupId) {
		return fetchByGroupId(groupId, true);
	}

	/**
	 * Returns the doc project where groupId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param groupId the group ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	@Override
	public DocProject fetchByGroupId(long groupId, boolean useFinderCache) {
		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {groupId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByGroupId, finderArgs, this);
		}

		if (result instanceof DocProject) {
			DocProject docProject = (DocProject)result;

			if (groupId != docProject.getGroupId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DOCPROJECT_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				List<DocProject> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByGroupId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {groupId};
							}

							_log.warn(
								"DocProjectPersistenceImpl.fetchByGroupId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DocProject docProject = list.get(0);

					result = docProject;

					cacheResult(docProject);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByGroupId, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (DocProject)result;
		}
	}

	/**
	 * Removes the doc project where groupId = &#63; from the database.
	 *
	 * @param groupId the group ID
	 * @return the doc project that was removed
	 */
	@Override
	public DocProject removeByGroupId(long groupId)
		throws NoSuchDocProjectException {

		DocProject docProject = findByGroupId(groupId);

		return remove(docProject);
	}

	/**
	 * Returns the number of doc projects where groupId = &#63;.
	 *
	 * @param groupId the group ID
	 * @return the number of matching doc projects
	 */
	@Override
	public int countByGroupId(long groupId) {
		FinderPath finderPath = _finderPathCountByGroupId;

		Object[] finderArgs = new Object[] {groupId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOCPROJECT_WHERE);

			query.append(_FINDER_COLUMN_GROUPID_GROUPID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(groupId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_GROUPID_GROUPID_2 =
		"docProject.groupId = ?";

	private FinderPath _finderPathFetchByName;
	private FinderPath _finderPathCountByName;

	/**
	 * Returns the doc project where name = &#63; or throws a <code>NoSuchDocProjectException</code> if it could not be found.
	 *
	 * @param name the name
	 * @return the matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	@Override
	public DocProject findByName(String name) throws NoSuchDocProjectException {
		DocProject docProject = fetchByName(name);

		if (docProject == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("name=");
			msg.append(name);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchDocProjectException(msg.toString());
		}

		return docProject;
	}

	/**
	 * Returns the doc project where name = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param name the name
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	@Override
	public DocProject fetchByName(String name) {
		return fetchByName(name, true);
	}

	/**
	 * Returns the doc project where name = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param name the name
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	@Override
	public DocProject fetchByName(String name, boolean useFinderCache) {
		name = Objects.toString(name, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {name};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByName, finderArgs, this);
		}

		if (result instanceof DocProject) {
			DocProject docProject = (DocProject)result;

			if (!Objects.equals(name, docProject.getName())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DOCPROJECT_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				List<DocProject> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByName, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {name};
							}

							_log.warn(
								"DocProjectPersistenceImpl.fetchByName(String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					DocProject docProject = list.get(0);

					result = docProject;

					cacheResult(docProject);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByName, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (DocProject)result;
		}
	}

	/**
	 * Removes the doc project where name = &#63; from the database.
	 *
	 * @param name the name
	 * @return the doc project that was removed
	 */
	@Override
	public DocProject removeByName(String name)
		throws NoSuchDocProjectException {

		DocProject docProject = findByName(name);

		return remove(docProject);
	}

	/**
	 * Returns the number of doc projects where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching doc projects
	 */
	@Override
	public int countByName(String name) {
		name = Objects.toString(name, "");

		FinderPath finderPath = _finderPathCountByName;

		Object[] finderArgs = new Object[] {name};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DOCPROJECT_WHERE);

			boolean bindName = false;

			if (name.isEmpty()) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_NAME_NAME_2 =
		"docProject.name = ?";

	private static final String _FINDER_COLUMN_NAME_NAME_3 =
		"(docProject.name IS NULL OR docProject.name = '')";

	private FinderPath _finderPathWithPaginationFindByU_S;
	private FinderPath _finderPathWithoutPaginationFindByU_S;
	private FinderPath _finderPathCountByU_S;

	/**
	 * Returns all the doc projects where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @return the matching doc projects
	 */
	@Override
	public List<DocProject> findByU_S(boolean unlisted, int status) {
		return findByU_S(
			unlisted, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the doc projects where unlisted = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @return the range of matching doc projects
	 */
	@Override
	public List<DocProject> findByU_S(
		boolean unlisted, int status, int start, int end) {

		return findByU_S(unlisted, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the doc projects where unlisted = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching doc projects
	 */
	@Override
	public List<DocProject> findByU_S(
		boolean unlisted, int status, int start, int end,
		OrderByComparator<DocProject> orderByComparator) {

		return findByU_S(unlisted, status, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the doc projects where unlisted = &#63; and status = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching doc projects
	 */
	@Override
	public List<DocProject> findByU_S(
		boolean unlisted, int status, int start, int end,
		OrderByComparator<DocProject> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByU_S;
				finderArgs = new Object[] {unlisted, status};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByU_S;
			finderArgs = new Object[] {
				unlisted, status, start, end, orderByComparator
			};
		}

		List<DocProject> list = null;

		if (useFinderCache) {
			list = (List<DocProject>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (DocProject docProject : list) {
					if ((unlisted != docProject.isUnlisted()) ||
						(status != docProject.getStatus())) {

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
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DOCPROJECT_WHERE);

			query.append(_FINDER_COLUMN_U_S_UNLISTED_2);

			query.append(_FINDER_COLUMN_U_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				query.append(DocProjectModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(unlisted);

				qPos.add(status);

				list = (List<DocProject>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first doc project in the ordered set where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	@Override
	public DocProject findByU_S_First(
			boolean unlisted, int status,
			OrderByComparator<DocProject> orderByComparator)
		throws NoSuchDocProjectException {

		DocProject docProject = fetchByU_S_First(
			unlisted, status, orderByComparator);

		if (docProject != null) {
			return docProject;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("unlisted=");
		msg.append(unlisted);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchDocProjectException(msg.toString());
	}

	/**
	 * Returns the first doc project in the ordered set where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	@Override
	public DocProject fetchByU_S_First(
		boolean unlisted, int status,
		OrderByComparator<DocProject> orderByComparator) {

		List<DocProject> list = findByU_S(
			unlisted, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last doc project in the ordered set where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project
	 * @throws NoSuchDocProjectException if a matching doc project could not be found
	 */
	@Override
	public DocProject findByU_S_Last(
			boolean unlisted, int status,
			OrderByComparator<DocProject> orderByComparator)
		throws NoSuchDocProjectException {

		DocProject docProject = fetchByU_S_Last(
			unlisted, status, orderByComparator);

		if (docProject != null) {
			return docProject;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("unlisted=");
		msg.append(unlisted);

		msg.append(", status=");
		msg.append(status);

		msg.append("}");

		throw new NoSuchDocProjectException(msg.toString());
	}

	/**
	 * Returns the last doc project in the ordered set where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching doc project, or <code>null</code> if a matching doc project could not be found
	 */
	@Override
	public DocProject fetchByU_S_Last(
		boolean unlisted, int status,
		OrderByComparator<DocProject> orderByComparator) {

		int count = countByU_S(unlisted, status);

		if (count == 0) {
			return null;
		}

		List<DocProject> list = findByU_S(
			unlisted, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the doc projects before and after the current doc project in the ordered set where unlisted = &#63; and status = &#63;.
	 *
	 * @param docProjectId the primary key of the current doc project
	 * @param unlisted the unlisted
	 * @param status the status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next doc project
	 * @throws NoSuchDocProjectException if a doc project with the primary key could not be found
	 */
	@Override
	public DocProject[] findByU_S_PrevAndNext(
			long docProjectId, boolean unlisted, int status,
			OrderByComparator<DocProject> orderByComparator)
		throws NoSuchDocProjectException {

		DocProject docProject = findByPrimaryKey(docProjectId);

		Session session = null;

		try {
			session = openSession();

			DocProject[] array = new DocProjectImpl[3];

			array[0] = getByU_S_PrevAndNext(
				session, docProject, unlisted, status, orderByComparator, true);

			array[1] = docProject;

			array[2] = getByU_S_PrevAndNext(
				session, docProject, unlisted, status, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected DocProject getByU_S_PrevAndNext(
		Session session, DocProject docProject, boolean unlisted, int status,
		OrderByComparator<DocProject> orderByComparator, boolean previous) {

		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			query = new StringBundler(4);
		}

		query.append(_SQL_SELECT_DOCPROJECT_WHERE);

		query.append(_FINDER_COLUMN_U_S_UNLISTED_2);

		query.append(_FINDER_COLUMN_U_S_STATUS_2);

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
			query.append(DocProjectModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(unlisted);

		qPos.add(status);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(docProject)) {

				qPos.add(orderByConditionValue);
			}
		}

		List<DocProject> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the doc projects where unlisted = &#63; and status = &#63; from the database.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 */
	@Override
	public void removeByU_S(boolean unlisted, int status) {
		for (DocProject docProject :
				findByU_S(
					unlisted, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(docProject);
		}
	}

	/**
	 * Returns the number of doc projects where unlisted = &#63; and status = &#63;.
	 *
	 * @param unlisted the unlisted
	 * @param status the status
	 * @return the number of matching doc projects
	 */
	@Override
	public int countByU_S(boolean unlisted, int status) {
		FinderPath finderPath = _finderPathCountByU_S;

		Object[] finderArgs = new Object[] {unlisted, status};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DOCPROJECT_WHERE);

			query.append(_FINDER_COLUMN_U_S_UNLISTED_2);

			query.append(_FINDER_COLUMN_U_S_STATUS_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(unlisted);

				qPos.add(status);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_U_S_UNLISTED_2 =
		"docProject.unlisted = ? AND ";

	private static final String _FINDER_COLUMN_U_S_STATUS_2 =
		"docProject.status = ?";

	public DocProjectPersistenceImpl() {
		setModelClass(DocProject.class);

		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put("type", "type_");

		try {
			Field field = BasePersistenceImpl.class.getDeclaredField(
				"_dbColumnNames");

			field.setAccessible(true);

			field.set(this, dbColumnNames);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}
	}

	/**
	 * Caches the doc project in the entity cache if it is enabled.
	 *
	 * @param docProject the doc project
	 */
	@Override
	public void cacheResult(DocProject docProject) {
		entityCache.putResult(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED, DocProjectImpl.class,
			docProject.getPrimaryKey(), docProject);

		finderCache.putResult(
			_finderPathFetchByUUID_G,
			new Object[] {docProject.getUuid(), docProject.getGroupId()},
			docProject);

		finderCache.putResult(
			_finderPathFetchByGroupId, new Object[] {docProject.getGroupId()},
			docProject);

		finderCache.putResult(
			_finderPathFetchByName, new Object[] {docProject.getName()},
			docProject);

		docProject.resetOriginalValues();
	}

	/**
	 * Caches the doc projects in the entity cache if it is enabled.
	 *
	 * @param docProjects the doc projects
	 */
	@Override
	public void cacheResult(List<DocProject> docProjects) {
		for (DocProject docProject : docProjects) {
			if (entityCache.getResult(
					DocProjectModelImpl.ENTITY_CACHE_ENABLED,
					DocProjectImpl.class, docProject.getPrimaryKey()) == null) {

				cacheResult(docProject);
			}
			else {
				docProject.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all doc projects.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(DocProjectImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the doc project.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(DocProject docProject) {
		entityCache.removeResult(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED, DocProjectImpl.class,
			docProject.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache((DocProjectModelImpl)docProject, true);
	}

	@Override
	public void clearCache(List<DocProject> docProjects) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (DocProject docProject : docProjects) {
			entityCache.removeResult(
				DocProjectModelImpl.ENTITY_CACHE_ENABLED, DocProjectImpl.class,
				docProject.getPrimaryKey());

			clearUniqueFindersCache((DocProjectModelImpl)docProject, true);
		}
	}

	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				DocProjectModelImpl.ENTITY_CACHE_ENABLED, DocProjectImpl.class,
				primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		DocProjectModelImpl docProjectModelImpl) {

		Object[] args = new Object[] {
			docProjectModelImpl.getUuid(), docProjectModelImpl.getGroupId()
		};

		finderCache.putResult(
			_finderPathCountByUUID_G, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByUUID_G, args, docProjectModelImpl, false);

		args = new Object[] {docProjectModelImpl.getGroupId()};

		finderCache.putResult(
			_finderPathCountByGroupId, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByGroupId, args, docProjectModelImpl, false);

		args = new Object[] {docProjectModelImpl.getName()};

		finderCache.putResult(
			_finderPathCountByName, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByName, args, docProjectModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		DocProjectModelImpl docProjectModelImpl, boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				docProjectModelImpl.getUuid(), docProjectModelImpl.getGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if ((docProjectModelImpl.getColumnBitmask() &
			 _finderPathFetchByUUID_G.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				docProjectModelImpl.getOriginalUuid(),
				docProjectModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByUUID_G, args);
			finderCache.removeResult(_finderPathFetchByUUID_G, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {docProjectModelImpl.getGroupId()};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(_finderPathFetchByGroupId, args);
		}

		if ((docProjectModelImpl.getColumnBitmask() &
			 _finderPathFetchByGroupId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				docProjectModelImpl.getOriginalGroupId()
			};

			finderCache.removeResult(_finderPathCountByGroupId, args);
			finderCache.removeResult(_finderPathFetchByGroupId, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {docProjectModelImpl.getName()};

			finderCache.removeResult(_finderPathCountByName, args);
			finderCache.removeResult(_finderPathFetchByName, args);
		}

		if ((docProjectModelImpl.getColumnBitmask() &
			 _finderPathFetchByName.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				docProjectModelImpl.getOriginalName()
			};

			finderCache.removeResult(_finderPathCountByName, args);
			finderCache.removeResult(_finderPathFetchByName, args);
		}
	}

	/**
	 * Creates a new doc project with the primary key. Does not add the doc project to the database.
	 *
	 * @param docProjectId the primary key for the new doc project
	 * @return the new doc project
	 */
	@Override
	public DocProject create(long docProjectId) {
		DocProject docProject = new DocProjectImpl();

		docProject.setNew(true);
		docProject.setPrimaryKey(docProjectId);

		String uuid = PortalUUIDUtil.generate();

		docProject.setUuid(uuid);

		docProject.setCompanyId(CompanyThreadLocal.getCompanyId());

		return docProject;
	}

	/**
	 * Removes the doc project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project that was removed
	 * @throws NoSuchDocProjectException if a doc project with the primary key could not be found
	 */
	@Override
	public DocProject remove(long docProjectId)
		throws NoSuchDocProjectException {

		return remove((Serializable)docProjectId);
	}

	/**
	 * Removes the doc project with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the doc project
	 * @return the doc project that was removed
	 * @throws NoSuchDocProjectException if a doc project with the primary key could not be found
	 */
	@Override
	public DocProject remove(Serializable primaryKey)
		throws NoSuchDocProjectException {

		Session session = null;

		try {
			session = openSession();

			DocProject docProject = (DocProject)session.get(
				DocProjectImpl.class, primaryKey);

			if (docProject == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDocProjectException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(docProject);
		}
		catch (NoSuchDocProjectException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected DocProject removeImpl(DocProject docProject) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(docProject)) {
				docProject = (DocProject)session.get(
					DocProjectImpl.class, docProject.getPrimaryKeyObj());
			}

			if (docProject != null) {
				session.delete(docProject);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (docProject != null) {
			clearCache(docProject);
		}

		return docProject;
	}

	@Override
	public DocProject updateImpl(DocProject docProject) {
		boolean isNew = docProject.isNew();

		if (!(docProject instanceof DocProjectModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(docProject.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(docProject);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in docProject proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom DocProject implementation " +
					docProject.getClass());
		}

		DocProjectModelImpl docProjectModelImpl =
			(DocProjectModelImpl)docProject;

		if (Validator.isNull(docProject.getUuid())) {
			String uuid = PortalUUIDUtil.generate();

			docProject.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date now = new Date();

		if (isNew && (docProject.getCreateDate() == null)) {
			if (serviceContext == null) {
				docProject.setCreateDate(now);
			}
			else {
				docProject.setCreateDate(serviceContext.getCreateDate(now));
			}
		}

		if (!docProjectModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				docProject.setModifiedDate(now);
			}
			else {
				docProject.setModifiedDate(serviceContext.getModifiedDate(now));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (docProject.isNew()) {
				session.save(docProject);

				docProject.setNew(false);
			}
			else {
				docProject = (DocProject)session.merge(docProject);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!DocProjectModelImpl.COLUMN_BITMASK_ENABLED) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			Object[] args = new Object[] {docProjectModelImpl.getUuid()};

			finderCache.removeResult(_finderPathCountByUuid, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid, args);

			args = new Object[] {
				docProjectModelImpl.getUuid(),
				docProjectModelImpl.getCompanyId()
			};

			finderCache.removeResult(_finderPathCountByUuid_C, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByUuid_C, args);

			args = new Object[] {
				docProjectModelImpl.isUnlisted(),
				docProjectModelImpl.getStatus()
			};

			finderCache.removeResult(_finderPathCountByU_S, args);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindByU_S, args);

			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}
		else {
			if ((docProjectModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					docProjectModelImpl.getOriginalUuid()
				};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);

				args = new Object[] {docProjectModelImpl.getUuid()};

				finderCache.removeResult(_finderPathCountByUuid, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid, args);
			}

			if ((docProjectModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByUuid_C.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					docProjectModelImpl.getOriginalUuid(),
					docProjectModelImpl.getOriginalCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);

				args = new Object[] {
					docProjectModelImpl.getUuid(),
					docProjectModelImpl.getCompanyId()
				};

				finderCache.removeResult(_finderPathCountByUuid_C, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByUuid_C, args);
			}

			if ((docProjectModelImpl.getColumnBitmask() &
				 _finderPathWithoutPaginationFindByU_S.getColumnBitmask()) !=
					 0) {

				Object[] args = new Object[] {
					docProjectModelImpl.getOriginalUnlisted(),
					docProjectModelImpl.getOriginalStatus()
				};

				finderCache.removeResult(_finderPathCountByU_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_S, args);

				args = new Object[] {
					docProjectModelImpl.isUnlisted(),
					docProjectModelImpl.getStatus()
				};

				finderCache.removeResult(_finderPathCountByU_S, args);
				finderCache.removeResult(
					_finderPathWithoutPaginationFindByU_S, args);
			}
		}

		entityCache.putResult(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED, DocProjectImpl.class,
			docProject.getPrimaryKey(), docProject, false);

		clearUniqueFindersCache(docProjectModelImpl, false);
		cacheUniqueFindersCache(docProjectModelImpl);

		docProject.resetOriginalValues();

		return docProject;
	}

	/**
	 * Returns the doc project with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the doc project
	 * @return the doc project
	 * @throws NoSuchDocProjectException if a doc project with the primary key could not be found
	 */
	@Override
	public DocProject findByPrimaryKey(Serializable primaryKey)
		throws NoSuchDocProjectException {

		DocProject docProject = fetchByPrimaryKey(primaryKey);

		if (docProject == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchDocProjectException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return docProject;
	}

	/**
	 * Returns the doc project with the primary key or throws a <code>NoSuchDocProjectException</code> if it could not be found.
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project
	 * @throws NoSuchDocProjectException if a doc project with the primary key could not be found
	 */
	@Override
	public DocProject findByPrimaryKey(long docProjectId)
		throws NoSuchDocProjectException {

		return findByPrimaryKey((Serializable)docProjectId);
	}

	/**
	 * Returns the doc project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the doc project
	 * @return the doc project, or <code>null</code> if a doc project with the primary key could not be found
	 */
	@Override
	public DocProject fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED, DocProjectImpl.class,
			primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		DocProject docProject = (DocProject)serializable;

		if (docProject == null) {
			Session session = null;

			try {
				session = openSession();

				docProject = (DocProject)session.get(
					DocProjectImpl.class, primaryKey);

				if (docProject != null) {
					cacheResult(docProject);
				}
				else {
					entityCache.putResult(
						DocProjectModelImpl.ENTITY_CACHE_ENABLED,
						DocProjectImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception exception) {
				entityCache.removeResult(
					DocProjectModelImpl.ENTITY_CACHE_ENABLED,
					DocProjectImpl.class, primaryKey);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return docProject;
	}

	/**
	 * Returns the doc project with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param docProjectId the primary key of the doc project
	 * @return the doc project, or <code>null</code> if a doc project with the primary key could not be found
	 */
	@Override
	public DocProject fetchByPrimaryKey(long docProjectId) {
		return fetchByPrimaryKey((Serializable)docProjectId);
	}

	@Override
	public Map<Serializable, DocProject> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, DocProject> map =
			new HashMap<Serializable, DocProject>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			DocProject docProject = fetchByPrimaryKey(primaryKey);

			if (docProject != null) {
				map.put(primaryKey, docProject);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(
				DocProjectModelImpl.ENTITY_CACHE_ENABLED, DocProjectImpl.class,
				primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (DocProject)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler(
			uncachedPrimaryKeys.size() * 2 + 1);

		query.append(_SQL_SELECT_DOCPROJECT_WHERE_PKS_IN);

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

			for (DocProject docProject : (List<DocProject>)q.list()) {
				map.put(docProject.getPrimaryKeyObj(), docProject);

				cacheResult(docProject);

				uncachedPrimaryKeys.remove(docProject.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(
					DocProjectModelImpl.ENTITY_CACHE_ENABLED,
					DocProjectImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the doc projects.
	 *
	 * @return the doc projects
	 */
	@Override
	public List<DocProject> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the doc projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @return the range of doc projects
	 */
	@Override
	public List<DocProject> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the doc projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of doc projects
	 */
	@Override
	public List<DocProject> findAll(
		int start, int end, OrderByComparator<DocProject> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the doc projects.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>DocProjectModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of doc projects
	 * @param end the upper bound of the range of doc projects (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of doc projects
	 */
	@Override
	public List<DocProject> findAll(
		int start, int end, OrderByComparator<DocProject> orderByComparator,
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

		List<DocProject> list = null;

		if (useFinderCache) {
			list = (List<DocProject>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_DOCPROJECT);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DOCPROJECT;

				sql = sql.concat(DocProjectModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				list = (List<DocProject>)QueryUtil.list(
					q, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the doc projects from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (DocProject docProject : findAll()) {
			remove(docProject);
		}
	}

	/**
	 * Returns the number of doc projects.
	 *
	 * @return the number of doc projects
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_DOCPROJECT);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return DocProjectModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the doc project persistence.
	 */
	public void afterPropertiesSet() {
		_finderPathWithPaginationFindAll = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, DocProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, DocProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathWithPaginationFindByUuid = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, DocProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, DocProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()},
			DocProjectModelImpl.UUID_COLUMN_BITMASK |
			DocProjectModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByUuid = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()});

		_finderPathFetchByUUID_G = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, DocProjectImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()},
			DocProjectModelImpl.UUID_COLUMN_BITMASK |
			DocProjectModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByUUID_G = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUUID_G",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, DocProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, DocProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			DocProjectModelImpl.UUID_COLUMN_BITMASK |
			DocProjectModelImpl.COMPANYID_COLUMN_BITMASK |
			DocProjectModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByUuid_C = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()});

		_finderPathFetchByGroupId = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, DocProjectImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByGroupId",
			new String[] {Long.class.getName()},
			DocProjectModelImpl.GROUPID_COLUMN_BITMASK);

		_finderPathCountByGroupId = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByGroupId",
			new String[] {Long.class.getName()});

		_finderPathFetchByName = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, DocProjectImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByName",
			new String[] {String.class.getName()},
			DocProjectModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByName = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] {String.class.getName()});

		_finderPathWithPaginationFindByU_S = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, DocProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByU_S",
			new String[] {
				Boolean.class.getName(), Integer.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});

		_finderPathWithoutPaginationFindByU_S = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, DocProjectImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByU_S",
			new String[] {Boolean.class.getName(), Integer.class.getName()},
			DocProjectModelImpl.UNLISTED_COLUMN_BITMASK |
			DocProjectModelImpl.STATUS_COLUMN_BITMASK |
			DocProjectModelImpl.NAME_COLUMN_BITMASK);

		_finderPathCountByU_S = new FinderPath(
			DocProjectModelImpl.ENTITY_CACHE_ENABLED,
			DocProjectModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByU_S",
			new String[] {Boolean.class.getName(), Integer.class.getName()});
	}

	public void destroy() {
		entityCache.removeCache(DocProjectImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_DOCPROJECT =
		"SELECT docProject FROM DocProject docProject";

	private static final String _SQL_SELECT_DOCPROJECT_WHERE_PKS_IN =
		"SELECT docProject FROM DocProject docProject WHERE docProjectId IN (";

	private static final String _SQL_SELECT_DOCPROJECT_WHERE =
		"SELECT docProject FROM DocProject docProject WHERE ";

	private static final String _SQL_COUNT_DOCPROJECT =
		"SELECT COUNT(docProject) FROM DocProject docProject";

	private static final String _SQL_COUNT_DOCPROJECT_WHERE =
		"SELECT COUNT(docProject) FROM DocProject docProject WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "docProject.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No DocProject exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No DocProject exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		DocProjectPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "type"});

}