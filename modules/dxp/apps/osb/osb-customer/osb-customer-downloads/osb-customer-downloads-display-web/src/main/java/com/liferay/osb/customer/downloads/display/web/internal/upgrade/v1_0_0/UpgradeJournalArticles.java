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

package com.liferay.osb.customer.downloads.display.web.internal.upgrade.v1_0_0;

import com.liferay.dynamic.data.mapping.storage.Fields;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.journal.util.JournalConverter;
import com.liferay.osb.customer.downloads.display.constants.DownloadsDDMStructureConstants;
import com.liferay.osb.customer.downloads.display.web.internal.util.DDMFieldsUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Amos Fong
 */
public class UpgradeJournalArticles extends UpgradeProcess {

	public static final String FILE_TYPE_DEVELOPER_STUDIO = "developerStudio";

	public static final String FILE_TYPE_FACES = "faces";

	public static final String FILE_TYPE_FIX_PACKS = "fixPacks";

	public static final String FILE_TYPE_PLUGINS = "plugins";

	public static final String FILE_TYPE_PLUGINS_SDK = "pluginsSDK";

	public static final String FILE_TYPE_PRODUCT = "product";

	public static final String FILE_TYPE_PROJECT_SDK = "projectSDK";

	public static final String FILE_TYPE_SECURITY = "security";

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

	public UpgradeJournalArticles(
		JournalArticleLocalService journalArticleLocalService,
		JournalConverter journalConverter) {

		_journalArticleLocalService = journalArticleLocalService;
		_journalConverter = journalConverter;
	}

	@Override
	protected void doUpgrade() throws Exception {
		List<JournalArticle> journalArticles =
			_journalArticleLocalService.getStructureArticles(
				new String[] {DownloadsDDMStructureConstants.KEY_DOWNLOAD});

		for (JournalArticle journalArticle : journalArticles) {
			if (!isHead(journalArticle)) {
				continue;
			}

			Fields ddmFields = _journalConverter.getDDMFields(
				journalArticle.getDDMStructure(), journalArticle.getContent());

			String product = DDMFieldsUtil.getSelectOption(
				ddmFields, "product");
			String[] fileTypes = DDMFieldsUtil.getSelectOptions(
				ddmFields, "fileType");

			long[] assetCategoryIds = getAssetCategoryIds(
				journalArticle, product, fileTypes);

			_journalArticleLocalService.updateAsset(
				journalArticle.getUserId(), journalArticle, assetCategoryIds,
				new String[0], new long[0], 0.0);
		}

		journalArticles = _journalArticleLocalService.getStructureArticles(
			new String[] {DownloadsDDMStructureConstants.KEY_TRIAL_DOWNLOAD});

		for (JournalArticle journalArticle : journalArticles) {
			if (!isHead(journalArticle)) {
				continue;
			}

			Fields ddmFields = _journalConverter.getDDMFields(
				journalArticle.getDDMStructure(), journalArticle.getContent());

			String product = DDMFieldsUtil.getSelectOption(
				ddmFields, "product");
			String[] fileTypes = DDMFieldsUtil.getSelectOptions(
				ddmFields, "fileType");

			long[] assetCategoryIds = getAssetCategoryIds(
				journalArticle, product, fileTypes);

			_journalArticleLocalService.updateAsset(
				journalArticle.getUserId(), journalArticle, assetCategoryIds,
				new String[0], new long[0], 0.0);
		}
	}

	protected long[] getAssetCategoryIds(
		JournalArticle journalArticle, String product, String[] fileTypes) {

		Set<Long> assetCategoryIds = new HashSet<>();

		if (product.equals(PRODUCT_ANALYTICS_CLOUD)) {
			assetCategoryIds.add(118191382L);

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PRODUCT)) {
				assetCategoryIds.add(118191384L);
			}
		}
		else if (product.equals(PRODUCT_COMMERCE)) {
			assetCategoryIds.add(118190997L);

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PRODUCT)) {
				assetCategoryIds.add(118191001L);
			}
		}
		else if (product.equals(PRODUCT_COMMERCE_CONNECTORS)) {
			assetCategoryIds.add(118191003L);

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PRODUCT)) {
				assetCategoryIds.add(118191032L);
			}
		}
		else if (product.equals(PRODUCT_CONNECTED_SERVICES)) {
			assetCategoryIds.add(118191005L);

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PRODUCT)) {
				assetCategoryIds.add(118191035L);
			}
		}
		else if (product.equals(PRODUCT_DEVELOPER_TOOLS)) {
			assetCategoryIds.add(118191007L);

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_DEVELOPER_STUDIO)) {
				assetCategoryIds.add(118191038L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_FACES)) {
				assetCategoryIds.add(118191040L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PLUGINS_SDK)) {
				assetCategoryIds.add(118191042L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PROJECT_SDK)) {
				assetCategoryIds.add(118204722L);
			}
		}
		else if (product.equals(PRODUCT_DXP_70)) {
			assetCategoryIds.add(118191009L);

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_FIX_PACKS)) {
				assetCategoryIds.add(118191046L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PRODUCT)) {
				assetCategoryIds.add(118191044L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_SECURITY)) {
				assetCategoryIds.add(118191048L);
			}
		}
		else if (product.equals(PRODUCT_DXP_71)) {
			assetCategoryIds.add(118191011L);

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_FIX_PACKS)) {
				assetCategoryIds.add(118191054L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PRODUCT)) {
				assetCategoryIds.add(118191052L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_SECURITY)) {
				assetCategoryIds.add(118191056L);
			}
		}
		else if (product.equals(PRODUCT_ENTERPRISE_SEARCH_PREMIUM)) {
			assetCategoryIds.add(118191013L);

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PRODUCT)) {
				assetCategoryIds.add(118191060L);
			}
		}
		else if (product.equals(PRODUCT_ENTERPRISE_SEARCH_STANDARD)) {
			assetCategoryIds.add(118191015L);

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PRODUCT)) {
				assetCategoryIds.add(118191062L);
			}
		}
		else if (product.equals(PRODUCT_MOBILE_EXPERIENCE_PLATFORM)) {
			assetCategoryIds.add(118191017L);

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PRODUCT)) {
				assetCategoryIds.add(118191064L);
			}
		}
		else if (product.equals(PRODUCT_PATCHING_TOOL)) {
			assetCategoryIds.add(118191019L);

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PRODUCT)) {
				assetCategoryIds.add(118191066L);
			}
		}
		else if (product.equals(PRODUCT_PORTAL_52)) {
			assetCategoryIds.add(118191021L);

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_FIX_PACKS)) {
				assetCategoryIds.add(118191072L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PLUGINS)) {
				assetCategoryIds.add(118191074L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PRODUCT)) {
				assetCategoryIds.add(118191070L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_SECURITY)) {
				assetCategoryIds.add(118191076L);
			}
		}
		else if (product.equals(PRODUCT_PORTAL_60)) {
			assetCategoryIds.add(118191023L);

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_FIX_PACKS)) {
				assetCategoryIds.add(118191080L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PLUGINS)) {
				assetCategoryIds.add(118191082L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PRODUCT)) {
				assetCategoryIds.add(118191078L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_SECURITY)) {
				assetCategoryIds.add(118191084L);
			}
		}
		else if (product.equals(PRODUCT_PORTAL_61)) {
			assetCategoryIds.add(118191025L);

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_FIX_PACKS)) {
				assetCategoryIds.add(118191088L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PLUGINS)) {
				assetCategoryIds.add(118191091L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PRODUCT)) {
				assetCategoryIds.add(118191086L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_SECURITY)) {
				assetCategoryIds.add(118191093L);
			}
		}
		else if (product.equals(PRODUCT_PORTAL_62)) {
			assetCategoryIds.add(118191027L);

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_FIX_PACKS)) {
				assetCategoryIds.add(118191097L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PLUGINS)) {
				assetCategoryIds.add(118191099L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PRODUCT)) {
				assetCategoryIds.add(118191095L);
			}

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_SECURITY)) {
				assetCategoryIds.add(118191101L);
			}
		}
		else if (product.equals(PRODUCT_SYNC)) {
			assetCategoryIds.add(118191029L);

			if (ArrayUtil.contains(fileTypes, FILE_TYPE_PRODUCT)) {
				assetCategoryIds.add(118191068L);
			}
		}

		return ArrayUtil.toArray(
			assetCategoryIds.toArray(new Long[assetCategoryIds.size()]));
	}

	protected boolean isHead(JournalArticle journalArticle) throws Exception {
		JournalArticle latestJournalArticle =
			_journalArticleLocalService.fetchLatestArticle(
				journalArticle.getResourcePrimKey(),
				new int[] {
					WorkflowConstants.STATUS_APPROVED,
					WorkflowConstants.STATUS_IN_TRASH
				});

		if ((latestJournalArticle != null) &&
			(journalArticle.getId() == latestJournalArticle.getId())) {

			return true;
		}

		return false;
	}

	private final JournalArticleLocalService _journalArticleLocalService;
	private final JournalConverter _journalConverter;

}