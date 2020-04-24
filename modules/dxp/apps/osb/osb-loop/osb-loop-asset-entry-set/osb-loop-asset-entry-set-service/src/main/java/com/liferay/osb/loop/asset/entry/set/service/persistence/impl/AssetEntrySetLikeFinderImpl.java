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

package com.liferay.osb.loop.asset.entry.set.service.persistence.impl;

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetLike;
import com.liferay.osb.loop.asset.entry.set.model.impl.AssetEntrySetLikeImpl;
import com.liferay.osb.loop.asset.entry.set.service.persistence.AssetEntrySetLikeFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * @author Sherry Yang
 */
public class AssetEntrySetLikeFinderImpl
	extends AssetEntrySetLikeFinderBaseImpl implements AssetEntrySetLikeFinder {

	public static final String FIND_BY_AESI_NOTC_C =
		AssetEntrySetLikeFinder.class.getName() + ".findByAESI_NotC_C";

	public List<AssetEntrySetLike> findByAESI_NotC_C(
		long assetEntrySetId, long classNameId, long classPK, int start,
		int end) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(getClass(), FIND_BY_AESI_NOTC_C);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addEntity("AssetEntrySetLike", AssetEntrySetLikeImpl.class);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(assetEntrySetId);
			qPos.add(classNameId);
			qPos.add(classPK);

			return (List<AssetEntrySetLike>)QueryUtil.list(
				q, getDialect(), start, end);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

}