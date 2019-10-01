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

package com.liferay.osb.customer.zendesk.documentation.sync.internal.importer;

import com.liferay.osb.customer.zendesk.documentation.sync.configuration.ZendeskDocumentationSyncConfiguration;
import com.liferay.osb.customer.zendesk.documentation.sync.importer.DocumentationImporter;
import com.liferay.osb.customer.zendesk.documentation.sync.importer.DocumentationImporterFactory;
import com.liferay.osb.customer.zendesk.documentation.sync.model.ZendeskCategory;
import com.liferay.osb.customer.zendesk.util.ZendeskLocaleUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.zip.ZipReader;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Amos Fong
 */
@Component(
	configurationPid = "com.liferay.osb.customer.zendesk.documentation.sync.configuration.ZendeskDocumentationSyncConfiguration",
	configurationPolicy = ConfigurationPolicy.OPTIONAL, immediate = true,
	service = DocumentationImporterFactory.class
)
public class DocumentationImporterFactoryImpl
	implements DocumentationImporterFactory {

	@Override
	public DocumentationImporter createDocumentationImporter(
			ZipReader zipReader, ZendeskCategory zendeskCategory, Locale locale)
		throws PortalException {

		if (!locale.equals(LocaleUtil.US)) {
			return new DocumentationTranslationArchiveImporter(
				_zendeskLocaleUtil, zipReader, zendeskCategory, locale,
				_zendeskDocumentationSyncConfiguration.
					markdownImporterArticleExtensions(),
				_zendeskDocumentationSyncConfiguration.
					markdownImporterArticleIntro());
		}

		return new DocumentationArchiveImporter(
			_zendeskLocaleUtil, zipReader, zendeskCategory, locale,
			_zendeskDocumentationSyncConfiguration.
				markdownImporterArticleExtensions(),
			_zendeskDocumentationSyncConfiguration.
				markdownImporterArticleIntro());
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_zendeskDocumentationSyncConfiguration =
			ConfigurableUtil.createConfigurable(
				ZendeskDocumentationSyncConfiguration.class, properties);
	}

	private volatile ZendeskDocumentationSyncConfiguration
		_zendeskDocumentationSyncConfiguration;

	@Reference
	private ZendeskLocaleUtil _zendeskLocaleUtil;

}