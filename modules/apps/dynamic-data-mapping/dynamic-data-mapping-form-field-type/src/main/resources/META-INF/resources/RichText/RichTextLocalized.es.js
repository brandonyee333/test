/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

import ClayButton from '@clayui/button';
import ClayDropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayLabel from '@clayui/label';
import ClayLayout from '@clayui/layout';

// @ts-ignore

import React, {useState} from 'react';

import './RichTextLocalized.scss';

const defaultLanguageId = Liferay.ThemeDisplay.getDefaultLanguageId();

const availableLocales = Object.keys(Liferay.Language.available)
	.sort((languageId) => (languageId === defaultLanguageId ? -1 : 1))
	.map((language) => ({
		label: language,
		symbol: language.replace('_', '-').toLowerCase(),
	}));

export function RichTextLocalized ({
	ariaLabels = {
		default: 'Default',
		openLocalizations: 'Open Localizations',
		translated: 'Translated',
		untranslated: 'Untranslated',
	},
	label,
	onSelectedLocaleChange,
	selectedLocale,
	translations,
}) {
	const [active, setActive] = useState(false);

	const defaultLanguage = availableLocales[0];

	return (
		<ClayDropDown
			active={active}
			className="lfr-notification__rich-text-localized-flag"
			onActiveChange={setActive}
			trigger={
				<ClayButton
					displayType="secondary"
					monospaced
					onClick={() => setActive(!active)}
					title={ariaLabels.openLocalizations}
				>
					<span className="inline-item">
						<ClayIcon
							symbol={selectedLocale
								.replace('_', '-')
								.toLowerCase()}
						/>
					</span>

					<span className="btn-section">{selectedLocale}</span>
				</ClayButton>
			}
		>
			<ClayDropDown.ItemList>
				{availableLocales.map((locale) => {
					const value = translations["en_US"];

					return (
						<ClayDropDown.Item
							key={"en_US"}
							onClick={() => {
								onSelectedLocaleChange(locale);
								setActive(false);
							}}
						>
							<ClayLayout.ContentRow containerElement="span">
								<ClayLayout.ContentCol
									containerElement="span"
									expand
								>
									<ClayLayout.ContentSection>
										<ClayIcon
											className="inline-item inline-item-before"
											symbol={locale.symbol}
										/>

										{"en_US"}
									</ClayLayout.ContentSection>
								</ClayLayout.ContentCol>

								<ClayLayout.ContentCol containerElement="span">
									<ClayLayout.ContentSection>
										<ClayLabel
											displayType={
												"en_US" ===
												defaultLanguage.label
													? 'info'
													: value
													? 'success'
													: 'warning'
											}
										>
											{"en_US" ===
											defaultLanguage.label
												? ariaLabels.default
												: value
												? ariaLabels.translated
												: ariaLabels.untranslated}
										</ClayLabel>
									</ClayLayout.ContentSection>
								</ClayLayout.ContentCol>
							</ClayLayout.ContentRow>
						</ClayDropDown.Item>
					);
				})}
			</ClayDropDown.ItemList>
		</ClayDropDown>
	);
}