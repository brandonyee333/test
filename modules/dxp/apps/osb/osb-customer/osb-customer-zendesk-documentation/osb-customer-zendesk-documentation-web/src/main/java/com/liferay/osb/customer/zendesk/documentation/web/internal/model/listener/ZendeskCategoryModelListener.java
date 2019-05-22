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

package com.liferay.osb.customer.zendesk.documentation.web.internal.model.listener;

import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskSection;
import com.liferay.osb.customer.zendesk.documentation.sync.service.ZendeskCategoryLocalService;
import com.liferay.osb.customer.zendesk.documentation.sync.service.ZendeskSectionLocalService;
import com.liferay.petra.lang.CentralizedThreadLocal;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Jenny Chen
 */
@Component(immediate = true, service = ModelListener.class)
public class ZendeskCategoryModelListener
	extends BaseModelListener<ZendeskCategory> {

	@Override
	public void onAfterUpdate(ZendeskCategory zendeskCategory)
		throws ModelListenerException {

		try {
			ZendeskCategory oldZendeskCategory = _oldZendeskCategory.get();

			long oldRemoteUserSegmentId =
				oldZendeskCategory.getRemoteUserSegmentId();

			if (oldRemoteUserSegmentId !=
					zendeskCategory.getRemoteUserSegmentId()) {

				List<ZendeskSection> zendeskSections =
					_zendeskSectionLocalService.getZendeskSections(
						zendeskCategory.getZendeskCategoryId());

				for (ZendeskSection zendeskSection : zendeskSections) {
					_zendeskSectionLocalService.
						updateRemoteZendeskSectionUserSegmentId(
							zendeskSection.getRemoteId(),
							zendeskCategory.getRemoteUserSegmentId());
				}
			}
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	@Override
	public void onBeforeUpdate(ZendeskCategory zendeskCategory)
		throws ModelListenerException {

		try {
			ZendeskCategory oldZendeskCategory =
				_zendeskCategoryLocalService.getZendeskCategory(
					zendeskCategory.getZendeskCategoryId());

			_oldZendeskCategory.set(oldZendeskCategory);
		}
		catch (Exception e) {
			_log.error(e);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ZendeskCategoryModelListener.class);

	private static final ThreadLocal<ZendeskCategory> _oldZendeskCategory =
		new CentralizedThreadLocal<>(
			ZendeskCategoryModelListener.class + "._oldZendeskCategory");

	@Reference
	private ZendeskCategoryLocalService _zendeskCategoryLocalService;

	@Reference
	private ZendeskSectionLocalService _zendeskSectionLocalService;

}