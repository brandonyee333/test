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

package com.liferay.osb.customer.downloads.display.web.internal.constants;

import com.liferay.portal.kernel.util.StringPool;

/**
 * @author Amos Fong
 */
public class DDMStructureConstants {

	public static final String FILE_TYPE_DEVELOPER_STUDIO = "developerStudio";

	public static final String FILE_TYPE_FACES = "faces";

	public static final String FILE_TYPE_FIX_PACKS = "fixPacks";

	public static final String FILE_TYPE_PLUGINS = "plugins";

	public static final String FILE_TYPE_PLUGINS_SDK = "pluginsSDK";

	public static final String FILE_TYPE_PRODUCT = "product";

	public static final String FILE_TYPE_SECURITY = "security";

	public static final String KEY_DOWNLOAD = "OSB-CUSTOMER-THEME---DOWNLOAD";

	public static final String KEY_TRIAL_DOWNLOAD =
		"OSB-CUSTOMER-THEME---TRIAL-DOWNLOAD";

	public static final String[] KEYS = {KEY_DOWNLOAD, KEY_TRIAL_DOWNLOAD};

	public static final String PRODUCT_ANALYTICS_CLOUD = "analyticsCloud";

	public static final String PRODUCT_COMMERCE = "commerce";

	public static final String PRODUCT_COMMERCE_CONNECTORS =
		"commerceConnectors";

	public static final String PRODUCT_CONNECTED_SERVICES = "connectedServices";

	public static final String PRODUCT_DEVELOPER_TOOLS = "developerTools";

	public static final String PRODUCT_DXP_70 = "dxp_70";

	public static final String PRODUCT_DXP_71 = "dxp_71";

	public static final String PRODUCT_ENTERPRISE_SEARCH_PREMIUM =
		"enterpriseSearchPremium";

	public static final String PRODUCT_ENTERPRISE_SEARCH_STANDARD =
		"enterpriseSearchStandard";

	public static final String PRODUCT_MOBILE_EXPERIENCE_PLATFORM =
		"mobileExperiencePlatform";

	public static final String PRODUCT_PATCHING_TOOL = "patchingTool";

	public static final String PRODUCT_PORTAL_52 = "portal_52";

	public static final String PRODUCT_PORTAL_60 = "portal_60";

	public static final String PRODUCT_PORTAL_61 = "portal_61";

	public static final String PRODUCT_PORTAL_62 = "portal_62";

	public static final String PRODUCT_SYNC = "sync";

	public static final String REQUIRED_AGREEMENT_ESA = "esa";

	public static final String REQUIRED_AGREEMENT_EVALUATION_EULA =
		"evaluationEula";

	public static final String REQUIRED_AGREEMENT_STUDIO_EULA = "studioEula";

	public static String getFileTypeLabel(String fileType) {
		if (fileType.equals(FILE_TYPE_DEVELOPER_STUDIO)) {
			return "Developer Studio";
		}
		else if (fileType.equals(FILE_TYPE_FACES)) {
			return "Faces";
		}
		else if (fileType.equals(FILE_TYPE_FIX_PACKS)) {
			return "Fix Packs";
		}
		else if (fileType.equals(FILE_TYPE_PLUGINS)) {
			return "Plugins";
		}
		else if (fileType.equals(FILE_TYPE_PLUGINS_SDK)) {
			return "Plugins SDK";
		}
		else if (fileType.equals(FILE_TYPE_PRODUCT)) {
			return "Product";
		}
		else if (fileType.equals(FILE_TYPE_SECURITY)) {
			return "Security";
		}
		else {
			return StringPool.BLANK;
		}
	}

	public static String[] getFileTypes(String product) {
		if (product.equals(PRODUCT_DEVELOPER_TOOLS)) {
			return _FILE_TYPES_DEVELOPER_TOOLS;
		}
		else if (product.equals(PRODUCT_DXP_70) ||
				 product.equals(PRODUCT_DXP_71)) {

			return _FILE_TYPES_DXP;
		}
		else if (product.equals(PRODUCT_PORTAL_52) ||
				 product.equals(PRODUCT_PORTAL_60) ||
				 product.equals(PRODUCT_PORTAL_61) ||
				 product.equals(PRODUCT_PORTAL_62)) {

			return _FILE_TYPES_PORTAL;
		}
		else {
			return _FILE_TYPES_DEFAULT;
		}
	}

	public static String getProductLabel(String product) {
		if (product.equals(PRODUCT_ANALYTICS_CLOUD)) {
			return "Analytics Cloud";
		}
		else if (product.equals(PRODUCT_COMMERCE)) {
			return "Commerce";
		}
		else if (product.equals(PRODUCT_COMMERCE_CONNECTORS)) {
			return "Commerce Connectors";
		}
		else if (product.equals(PRODUCT_CONNECTED_SERVICES)) {
			return "Connected Services";
		}
		else if (product.equals(PRODUCT_DEVELOPER_TOOLS)) {
			return "Developer Tools";
		}
		else if (product.equals(PRODUCT_DXP_70)) {
			return "DXP 7.0";
		}
		else if (product.equals(PRODUCT_DXP_71)) {
			return "DXP 7.1";
		}
		else if (product.equals(PRODUCT_ENTERPRISE_SEARCH_PREMIUM)) {
			return "Enterprise Search Premium";
		}
		else if (product.equals(PRODUCT_ENTERPRISE_SEARCH_STANDARD)) {
			return "Enterprise Search Standard";
		}
		else if (product.equals(PRODUCT_MOBILE_EXPERIENCE_PLATFORM)) {
			return "Mobile Experience Platform";
		}
		else if (product.equals(PRODUCT_PATCHING_TOOL)) {
			return "Patching Tool";
		}
		else if (product.equals(PRODUCT_PORTAL_52)) {
			return "Portal 5.2";
		}
		else if (product.equals(PRODUCT_PORTAL_60)) {
			return "Portal 6.0";
		}
		else if (product.equals(PRODUCT_PORTAL_61)) {
			return "Portal 6.1";
		}
		else if (product.equals(PRODUCT_PORTAL_62)) {
			return "Portal 6.2";
		}
		else if (product.equals(PRODUCT_SYNC)) {
			return "Sync";
		}
		else {
			return StringPool.BLANK;
		}
	}

	private static final String[] _FILE_TYPES_DEFAULT = {FILE_TYPE_PRODUCT};

	private static final String[] _FILE_TYPES_DEVELOPER_TOOLS =
		{FILE_TYPE_DEVELOPER_STUDIO, FILE_TYPE_FACES, FILE_TYPE_PLUGINS_SDK};

	private static final String[] _FILE_TYPES_DXP =
		{FILE_TYPE_PRODUCT, FILE_TYPE_FIX_PACKS, FILE_TYPE_SECURITY};

	private static final String[] _FILE_TYPES_PORTAL = {
		FILE_TYPE_PRODUCT, FILE_TYPE_FIX_PACKS, FILE_TYPE_PLUGINS,
		FILE_TYPE_SECURITY
	};

}