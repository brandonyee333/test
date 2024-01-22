/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import {fetch} from 'frontend-js-web';

import ClayForm from '@clayui/form';
import ClayIcon from '@clayui/icon';
import ClayMultiSelect from '@clayui/multi-select';
import classnames from 'classnames';
import React, {useCallback, useEffect, useState} from 'react';

import ErrorMessage from '../ErrorMessage';
import {TGenericFieldProps} from '../FieldsWrapper';
import {Item} from '@clayui/multi-select/lib/types';

type PartialListTypeEntry = {
    key: string;
    name_i18n: {
        [localeCode: string]: string;
    }
}

type MultiselectPicklistItem = {
    label: string;
    key: string;
    value: string;
};

const MULTISELECT_PICKLIST_SEPARATOR = ', ';

const _itemsToValue = (items: MultiselectPicklistItem[], i18n = false) => items
    .reduce((valueAsString: string, {key, label}: MultiselectPicklistItem): string => {
        const stringChunk = i18n ? label : key;

        return valueAsString ?
            `${valueAsString}${MULTISELECT_PICKLIST_SEPARATOR}${stringChunk}`
            : stringChunk
    }, '');

const _valueToItems = (
    value: string | undefined,
    sourceItems: MultiselectPicklistItem[],
): MultiselectPicklistItem[] =>
    sourceItems.filter((sourceItem) => value?.includes(sourceItem.key))

const composeListTypeEntriesEndpoint = (id: number): string =>
    `/o/headless-admin-list-type/v1.0/list-type-definitions/${id}/list-type-entries`

const MultiselectPicklist = ({
    disabled,
    id,
    label,
    mode = 'view',
    name,
    namespace,
    onChange,
    originalField: {
        listTypeDefinitionId,
    },
    readOnly,
    required = false,
    value: keysAsValue
}: TGenericFieldProps) => {

    const [commaSeparatedKeys] = useState<string | null>(keysAsValue);
    const [internalValue, setInternalValue] = useState<string | null>(null);
    const [items, setItems] = useState<MultiselectPicklistItem[]>([]);
    const [error, setError] = useState<string | null>(null);
    const [sourceItems, setSourceItems] = useState<MultiselectPicklistItem[]>([]);

    const fetchSourceItems = useCallback(() => {
        fetch(composeListTypeEntriesEndpoint(listTypeDefinitionId))
            .then((response) => response.json())
            .then(({items: listTypeEntries}) => {
                const languageId = Liferay.ThemeDisplay
                    .getLanguageId().replace('_', '-');

                setSourceItems(
                    listTypeEntries.map((entry: PartialListTypeEntry): MultiselectPicklistItem => ({
                        label: entry.name_i18n[languageId],
                        key: entry.key,
                        value: entry.key,
                    }))
                );
            })
    }, []);

    const onItemsChange = useCallback((updatedItems: Item[] = []) => {
        if (updatedItems.length) {
            const [lastAdded]: Item[] = updatedItems.slice(-1);

            const isSelectable: boolean = -1 !== sourceItems.findIndex(
                ({key}) => key === lastAdded.key);
            const isAlreadyAdded: boolean = -1 !== items.findIndex(
                ({key}) => key === lastAdded.key);

            if (isAlreadyAdded || !isSelectable) {
                updatedItems.pop();
            }
        }

        setItems(updatedItems as MultiselectPicklistItem[]);
    }, [items, sourceItems]);

    useEffect(() => {
        if (sourceItems.length) {
            const selectedItems= _valueToItems(commaSeparatedKeys as string, sourceItems);

            setItems(selectedItems);
            setInternalValue(_itemsToValue(selectedItems, true));
        } else {
            fetchSourceItems();
        }
    }, [
        fetchSourceItems,
        sourceItems,
        commaSeparatedKeys,
    ]);

    useEffect(() => {
        if (mode === 'edit') {
            const hasError = required && !items.length;

            onChange({
                hasError,
                name,
                value: _itemsToValue(items),
            });

            setError(
                hasError
                    ? Liferay.Language.get('this-field-is-required')
                    : null
            );
        }
    }, [items]);

    return mode === 'edit' ? (
        <ClayForm.Group
            className={classnames({
                'has-error': !!error,
            })}
        >
            <label htmlFor={`${namespace}${name}`}>
                {label}

                {required && (
                    <ClayIcon
                        className="c-ml-1 reference-mark"
                        symbol="asterisk"
                    />
                )}
            </label>

            {(sourceItems.length || items.length) && <ClayMultiSelect
                disabled={disabled || readOnly}
                id={`${namespace}${id}`}
				inputName={`${namespace}${name}`}
                items={items}
                aria-required={required}
                placeholder={Liferay.Language.get('select-items')}
                onItemsChange={onItemsChange}
                sourceItems={sourceItems}
            />}

            <ErrorMessage error={error} />
        </ClayForm.Group>
    ) : (
        <div key={`${namespace}_${id}`}>
            <div className="sidebar-dt">{label}</div>

            <div className="sidebar-dd text-wrap">
                {internalValue || '-'}
            </div>
        </div>
    );
};

export default MultiselectPicklist;
