/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.search.solr.internal.document;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.DocumentImpl;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.geolocation.GeoLocationPoint;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.solr.document.SolrDocumentFactory;

import java.util.Locale;
import java.util.Map;

import org.apache.solr.common.SolrInputDocument;

import org.osgi.service.component.annotations.Component;

/**
 * @author Michael C. Han
 */
@Component(immediate = true, service = SolrDocumentFactory.class)
public class DefaultSolrDocumentFactory implements SolrDocumentFactory {

	@Override
	public SolrInputDocument getSolrInputDocument(Document document) {
		SolrInputDocument solrInputDocument = new SolrInputDocument();

		Map<String, Field> fields = document.getFields();

		for (Field field : fields.values()) {
			String name = field.getName();

			if (!field.isLocalized()) {
				for (String value : field.getValues()) {
					if (value == null) {
						continue;
					}

					value = value.trim();

					addField(solrInputDocument, field, value, name);
				}
			}
			else {
				Map<Locale, String> localizedValues =
					field.getLocalizedValues();

				for (Map.Entry<Locale, String> entry :
						localizedValues.entrySet()) {

					String value = entry.getValue();

					if (Validator.isNull(value)) {
						continue;
					}

					value = value.trim();

					Locale locale = entry.getKey();

					String languageId = LocaleUtil.toLanguageId(locale);

					String defaultLanguageId = LocaleUtil.toLanguageId(
						LocaleUtil.getDefault());

					if (languageId.equals(defaultLanguageId)) {
						solrInputDocument.addField(name, value);
					}

					String localizedName = DocumentImpl.getLocalizedName(
						locale, name);

					addField(solrInputDocument, field, value, localizedName);
				}
			}
		}

		return solrInputDocument;
	}

	protected void addField(
		SolrInputDocument solrInputDocument, Field field, String value,
		String localizedName) {

		GeoLocationPoint geoLocationPoint = field.getGeoLocationPoint();

		if (geoLocationPoint != null) {
			value =
				geoLocationPoint.getLatitude() + StringPool.COMMA +
					geoLocationPoint.getLongitude();
		}

		solrInputDocument.addField(localizedName, value);

		if (field.isSortable()) {
			String sortableFieldName = DocumentImpl.getSortableFieldName(
				localizedName);

			solrInputDocument.addField(sortableFieldName, value);
		}
	}

}