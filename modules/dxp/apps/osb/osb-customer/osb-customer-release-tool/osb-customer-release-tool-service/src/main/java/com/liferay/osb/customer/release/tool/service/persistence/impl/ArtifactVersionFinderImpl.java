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

package com.liferay.osb.customer.release.tool.service.persistence.impl;

import com.liferay.osb.customer.release.tool.model.ArtifactVersionRange;
import com.liferay.osb.customer.release.tool.service.persistence.ArtifactVersionFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Amos Fong
 */
public class ArtifactVersionFinderImpl
	extends ArtifactVersionFinderBaseImpl implements ArtifactVersionFinder {

	public static final String FIND_ARTIFACT_VERSION_RANGES_BY_RACI_RACI_O_K =
		ArtifactVersionFinder.class.getName() +
			".findArtifactVersionRangesByRACI_RACI_O_K";

	public List<ArtifactVersionRange> findArtifactVersionRangesByRACI_RACI_O_K(
		long fromReleaseAssetCategoryId, long toReleaseAssetCategoryId,
		int[] owners, String keywords, boolean changesOnly) {

		keywords = StringUtil.quote(keywords, StringPool.PERCENT);

		Session session = null;

		try {
			session = openSession();

			String sql = CustomSQLUtil.get(
				getClass(), FIND_ARTIFACT_VERSION_RANGES_BY_RACI_RACI_O_K);

			sql = CustomSQLUtil.replaceKeywords(
				sql, "OSBCustomer_ArtifactVersion.owner", false, owners);

			if (changesOnly) {
				sql = StringUtil.replace(
					sql, "[$CHANGES_ONLY$]", _CHANGES_ONLY_SQL);
			}
			else {
				sql = StringUtil.replace(
					sql, "[$CHANGES_ONLY$]", StringPool.BLANK);
			}

			sql = CustomSQLUtil.replaceAndOperator(sql, true);

			SQLQuery q = session.createSynchronizedSQLQuery(sql);

			q.addScalar("repository", Type.INTEGER);
			q.addScalar("group_", Type.STRING);
			q.addScalar("name", Type.STRING);
			q.addScalar("fromVersion", Type.STRING);
			q.addScalar("toVersion", Type.STRING);
			q.addScalar("packaging", Type.STRING);

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(fromReleaseAssetCategoryId);
			qPos.add(owners);
			qPos.add(keywords);
			qPos.add(keywords);
			qPos.add(toReleaseAssetCategoryId);
			qPos.add(owners);
			qPos.add(keywords);
			qPos.add(keywords);
			qPos.add(fromReleaseAssetCategoryId);
			qPos.add(owners);
			qPos.add(keywords);
			qPos.add(keywords);
			qPos.add(toReleaseAssetCategoryId);
			qPos.add(owners);
			qPos.add(keywords);
			qPos.add(keywords);

			List<ArtifactVersionRange> artifactVersionRanges =
				new ArrayList<>();

			Iterator<Object[]> itr = (Iterator<Object[]>)QueryUtil.iterate(
				q, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			while (itr.hasNext()) {
				Object[] array = itr.next();

				ArtifactVersionRange artifactVersionRange =
					new ArtifactVersionRange();

				artifactVersionRange.setRepository((Integer)array[0]);
				artifactVersionRange.setGroup((String)array[1]);
				artifactVersionRange.setName((String)array[2]);
				artifactVersionRange.setFromVersion((String)array[3]);
				artifactVersionRange.setToVersion((String)array[4]);
				artifactVersionRange.setPackaging((String)array[5]);

				artifactVersionRanges.add(artifactVersionRange);
			}

			return artifactVersionRanges;
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _CHANGES_ONLY_SQL =
		"WHERE OSBCustomer_ArtifactVersion1.version != " +
			"OSBCustomer_ArtifactVersion2.version";

}