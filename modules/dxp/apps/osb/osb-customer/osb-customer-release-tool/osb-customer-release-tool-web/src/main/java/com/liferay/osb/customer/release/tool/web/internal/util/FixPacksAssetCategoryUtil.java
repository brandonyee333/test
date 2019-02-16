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

package com.liferay.osb.customer.release.tool.web.internal.util;

import com.liferay.asset.kernel.exception.NoSuchCategoryPropertyException;
import com.liferay.asset.kernel.model.AssetCategoryProperty;
import com.liferay.asset.kernel.model.AssetVocabulary;
import com.liferay.asset.kernel.service.AssetCategoryPropertyLocalService;
import com.liferay.asset.kernel.service.AssetVocabularyLocalService;
import com.liferay.osb.customer.release.tool.web.internal.constants.FixPackAssetCategoryConstants;
import com.liferay.portal.instances.service.PortalInstancesLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.module.framework.ModuleServiceLifecycle;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.util.StringPool;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(immediate = true, service = FixPacksAssetCategoryUtil.class)
public class FixPacksAssetCategoryUtil {

	public long getFixPacksAssetVocabularyId() {
		return _fixPacksAssetVocabulary.getVocabularyId();
	}

	public String getPropertyValue(long assetCategoryId, String key)
		throws PortalException {

		try {
			AssetCategoryProperty assetCategoryProperty =
				_assetCategoryPropertyLocalService.getCategoryProperty(
					assetCategoryId, key);

			return assetCategoryProperty.getValue();
		}
		catch (NoSuchCategoryPropertyException nscpe) {
		}

		return StringPool.BLANK;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties)
		throws PortalException {

		long companyId = _portalInstancesLocalService.getDefaultCompanyId();

		Group group = _groupLocalService.getCompanyGroup(companyId);

		_fixPacksAssetVocabulary =
			_assetVocabularyLocalService.fetchGroupVocabulary(
				group.getGroupId(),
				FixPackAssetCategoryConstants.VOCABULARY_FIX_PACKS_NAME);
	}

	@Reference(target = ModuleServiceLifecycle.PORTAL_INITIALIZED, unbind = "-")
	protected void setModuleServiceLifecycle(
		ModuleServiceLifecycle moduleServiceLifecycle) {
	}

	@Reference
	private AssetCategoryPropertyLocalService
		_assetCategoryPropertyLocalService;

	@Reference
	private AssetVocabularyLocalService _assetVocabularyLocalService;

	private AssetVocabulary _fixPacksAssetVocabulary;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private PortalInstancesLocalService _portalInstancesLocalService;

}