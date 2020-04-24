jest.unmock('../../../lib/selectors');

import Promise from 'metal-promise';
import {fromJS, Map} from 'immutable';

import DivisionProfileMenu from '../DivisionProfileMenu';
import LoopAssets from '../../../tests/loop-assets';
import mockStore from '../../../tests/mock-store';
import {showModal} from '../../../actions/modals';

const ID = 0;

const store = mockStore(
	Map().mergeIn(
		['divisions', ID, 'data'],
		fromJS(LoopAssets.getDivision())
	)
);

describe(
	'DivisionProfileMenu',
	() => {
		let component;

		showModal.mockReturnValue(Promise.resolve());

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}

				showModal.mockClear();
			}
		);

		it(
			'renders',
			() => {
				component = new DivisionProfileMenu({store});

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'receives divisionIMap from the store',
			() => {

				component = new DivisionProfileMenu({id: ID, store});

				const divisionProfileMenu = component.components.child;

				const divisionIMap = divisionProfileMenu.props.divisionIMap;

				expect(divisionIMap.get('entityClassPK')).toEqual(ID);
			}
		);

		it(
			'should call showModal on handleConvertDivisionTypeModal_',
			() => {

				component = new DivisionProfileMenu({id: ID, store});

				const divisionProfileMenu = component.components.child;

				divisionProfileMenu.handleConvertDivisionTypeModal_();

				expect(showModal).toBeCalled();
			}
		);

		it(
			'should call showModal on handleChildrenModal_',
			() => {

				component = new DivisionProfileMenu({id: ID, store});

				const divisionProfileMenu = component.components.child;

				divisionProfileMenu.handleChildrenModal_();

				expect(showModal).toBeCalled();
			}
		);

		it(
			'should call showModal on handleParentModal_',
			() => {

				component = new DivisionProfileMenu({id: ID, store});

				const divisionProfileMenu = component.components.child;

				divisionProfileMenu.handleParentModal_();

				expect(showModal).toBeCalled();
			}
		);
	}
);