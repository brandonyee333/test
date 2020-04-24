jest.unmock('../../lib/asset-entry-set-utils');

import InputList from '../InputList';
import LoopAssets from '../../tests/loop-assets';

describe(
	'InputList',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'should render',
			() => {
				component = new InputList();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call onChange when an item is added',
			done => {
				component = new InputList(
					{
						items: [],
						onChange: items => {
							expect(items.length).toBe(1);

							done();
						}
					}
				);

				expect(component.props.items.length).toBe(0);

				component.state.open_ = true;
				component.handleAdd_(LoopAssets.getPerson());
			}
		);
	}
);