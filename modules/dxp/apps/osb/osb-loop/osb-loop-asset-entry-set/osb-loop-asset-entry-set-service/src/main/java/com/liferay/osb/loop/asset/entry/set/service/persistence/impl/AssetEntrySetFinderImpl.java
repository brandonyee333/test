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

import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySet;
import com.liferay.osb.loop.asset.entry.set.model.AssetEntrySetReference;
import com.liferay.osb.loop.asset.entry.set.service.persistence.AssetEntrySetFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Calvin Keum
 * @author Sherry Yang
 */
public class AssetEntrySetFinderImpl
	extends AssetEntrySetFinderBaseImpl implements AssetEntrySetFinder {

	public static final String FIND_ASSET_ENTRY_SET_REFERENCE_BY_PAESI_CNI =
		AssetEntrySetFinder.class.getName() +
			".findAssetEntrySetReferenceByPAESI_CNI";

	@Override
	public List<AssetEntrySetReference> findAssetEntrySetReferenceByPAESI_CNI(
		long parentAssetEntrySetId) {

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), FIND_ASSET_ENTRY_SET_REFERENCE_BY_PAESI_CNI);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(parentAssetEntrySetId);
			qPos.add(_portal.getClassNameId(AssetEntrySet.class));

			List<AssetEntrySetReference> assetEntrySetReferences =
				new ArrayList<>();

			Iterator<Object[]> itr = q.iterate();

			while (itr.hasNext()) {
				Object[] array = itr.next();

				long sharedToClassNameId = GetterUtil.getLong(array[0]);
				long sharedToClassPK = GetterUtil.getLong(array[1]);

				assetEntrySetReferences.add(
					new AssetEntrySetReference(
						sharedToClassNameId, sharedToClassPK));
			}

			return assetEntrySetReferences;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@ServiceReference(type = Portal.class)
	private Portal _portal;

}