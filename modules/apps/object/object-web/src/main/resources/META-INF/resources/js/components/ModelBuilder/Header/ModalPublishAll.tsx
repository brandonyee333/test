/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

import ClayButton from '@clayui/button';
import ClayModal from '@clayui/modal';
import ClayCard from '@clayui/card';
import { ClayCheckbox } from '@clayui/form';
import ClayIcon from '@clayui/icon';
import { Observer } from '@clayui/modal/lib/types';
import {
    API,
    RadioField
} from '@liferay/object-js-components-web';
import React, {
    FormEvent,
    useCallback,
    useEffect,
    useMemo,
    useState,
} from 'react';


import './ModalPublishAll.scss';
interface IProps {
    disableAutoClose: boolean;
    observer: Observer;
    onClose: () => void;
}

interface IItem {
    id: number;
    name: string;
}


export function ModalPublishAll({ disableAutoClose, observer, onClose }: IProps) {
    const [items, setItems] = useState<IItem[]>([]);
    const [selectedItems, setSelectedItems] = useState<number[]>([]);

    useEffect(() => {
        const getObjectDefinition = async () => {
            const list = (await API.getObjectDefinitions()).filter(object => object.status.code === 2);
            setItems(list);
        }
        getObjectDefinition();
    }, []);

    const handleCheckboxChange = (itemId: number) => {
        if (selectedItems.includes(itemId)) {
            setSelectedItems(selectedItems.filter(id => id !== itemId));
        } else {
            setSelectedItems([...selectedItems, itemId]);
        }
    };

    return (
        <ClayModal disableAutoClose={disableAutoClose} observer={observer} status="warning">
            <ClayModal.Header>Confirm Publishing</ClayModal.Header>

            <ClayModal.Body>
                <p>The following Objects contain changes that will be published and may affect your production environment. Please check before confirming:</p>

                <>
                    {items.map(obj => {
                        return (
                            <ClayCard key={obj.id} className={`lfr-object__object-view-modal-publish-all-card ${selectedItems.includes(obj.id) ? 'active' : ''}`}>
                                <ClayCard.Body>
                                    <ClayCheckbox checked={selectedItems.includes(obj.id)} onChange={() => handleCheckboxChange(obj.id)} />
                                    <ClayIcon symbol="catalog" />
                                    <div>
                                        <div>{obj.name}</div>
                                        <span className="label label-info">
                                            <span className="label-item label-item-expand">draft</span>
                                        </span>
                                    </div>
                                </ClayCard.Body>
                            </ClayCard>
                        )
                    })}
                </>
            </ClayModal.Body>

            <ClayModal.Footer
                last={
                    <ClayButton.Group key={1} spaced>
                        <ClayButton
                            displayType="secondary"
                            onClick={onClose}
                        >
                            {Liferay.Language.get('Cancel')}
                        </ClayButton>

                        <ClayButton
                            displayType="primary"
                        >
                            {Liferay.Language.get('publish')}
                        </ClayButton>
                    </ClayButton.Group>
                }>
            </ClayModal.Footer>
        </ClayModal>
    );
}
