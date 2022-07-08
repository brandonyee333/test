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

import ClayLocalizedInput from '@clayui/localized-input';
import classNames from 'classnames';
import React, {useState} from 'react';

import {FieldBase} from './FieldBase';

import './InputLocalized.scss';

const defaultLanguageId = Liferay.ThemeDisplay.getDefaultLanguageId();

const availableLocales = Object.keys(Liferay.Language.available)
	.sort((languageId) => (languageId === defaultLanguageId ? -1 : 1))
	.map((language) => ({
		label: language,
		symbol: language.replace('_', '-').toLowerCase(),
	}));

export function InputLocalized({
	disabled,
	error,
	id,
	label,
	name,
	onChange,
	placeholder,
	required,
	translations,
	...otherProps
}: IProps) {
	const [selectedLocale, setSelectedLocale] = useState(availableLocales[0]);

	return (
		<FieldBase
			className="lfr-objects__input-localized"
			disabled={disabled}
			errorMessage={error}
			id={id}
			label={label}
			required={required}
		>
			<ClayLocalizedInput
				{...otherProps}
				className={classNames({
					'lfr-objects__input-localized--rtl':
						Liferay.Language.direction[
							selectedLocale.label as Locale
						] === 'rtl',
				})}
				disabled={disabled}
				id={id}
				label=""
				locales={availableLocales}
				name={name}
				onSelectedLocaleChange={setSelectedLocale}
				onTranslationsChange={(value) =>
					onChange(value, selectedLocale)
				}
				placeholder={placeholder}
				selectedLocale={selectedLocale}
				translations={translations}
			/>
		</FieldBase>
	);
}

interface IProps {
	className?: string;
	disabled?: boolean;
	error?: string;
	id?: string;
	label: string;
	name?: string;
	onChange: (
		value: LocalizedValue<string>,
		selectedLocale: {
			label: string;
			symbol: string;
		}
	) => void;
	placeholder?: string;
	required?: boolean;
	translations: LocalizedValue<string>;
}
