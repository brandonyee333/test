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

package com.liferay.osb.customer.release.tool.constants;

import com.liferay.portal.kernel.util.CharPool;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Amos Fong
 */
public class ArtifactVersionConstants {

	public static final int OWNER_LIFERAY = 1;

	public static final int OWNER_THIRD_PARTY = 2;

	public static final int[] OWNERS = {OWNER_LIFERAY, OWNER_THIRD_PARTY};

	public static final String PACKAGING_JAR = "jar";

	public static final int REPOSITORY_PRIVATE = 1;

	public static final int REPOSITORY_PUBLIC = 2;

	public static final int REPOSITORY_THIRD_PARTY = 3;

	public static int getRepository(String repositoryLabel) {
		if (repositoryLabel.equals("private")) {
			return REPOSITORY_PRIVATE;
		}
		else if (repositoryLabel.equals("public")) {
			return REPOSITORY_PUBLIC;
		}
		else if (repositoryLabel.equals("third-party")) {
			return REPOSITORY_THIRD_PARTY;
		}
		else {
			return 0;
		}
	}

	public static String getRepositoryURL(
		int repository, String group, String name, String version,
		String packaging) {

		if (repository <= 0) {
			return StringPool.BLANK;
		}

		if (Validator.isNull(version) || version.contains("-SNAPSHOT")) {
			return StringPool.BLANK;
		}

		String plainVersion = version;
		String classifier = null;

		int pos = version.indexOf(StringPool.COLON);

		if (pos != -1) {
			plainVersion = version.substring(0, pos);
			classifier = version.substring(pos + 1);
		}

		StringBundler sb = new StringBundler(14);

		if (repository == REPOSITORY_PRIVATE) {
			sb.append(_REPOSITORY_URL_PRIVATE);
		}
		else if (repository == REPOSITORY_PUBLIC) {
			sb.append(_REPOSITORY_URL_PUBLIC);
		}
		else if (repository == REPOSITORY_THIRD_PARTY) {
			sb.append(_REPOSITORY_URL_THIRD_PARTY);
		}

		sb.append(StringUtil.replace(group, CharPool.PERIOD, CharPool.SLASH));
		sb.append(StringPool.SLASH);
		sb.append(name);
		sb.append(StringPool.SLASH);
		sb.append(plainVersion);
		sb.append(StringPool.SLASH);
		sb.append(name);
		sb.append(StringPool.DASH);
		sb.append(plainVersion);

		if (Validator.isNotNull(classifier)) {
			sb.append(StringPool.DASH);
			sb.append(classifier);
		}

		sb.append(StringPool.PERIOD);
		sb.append(packaging);

		return sb.toString();
	}

	private static final String _REPOSITORY_URL_PRIVATE =
		"https://repository-cdn.liferay.com/nexus/content/repositories" +
			"/liferay-private-releases/";

	private static final String _REPOSITORY_URL_PUBLIC =
		"https://repository-cdn.liferay.com/nexus/content/repositories" +
			"/liferay-public-releases/";

	private static final String _REPOSITORY_URL_THIRD_PARTY =
		"https://repository-cdn.liferay.com/nexus/content/repositories/public/";

}