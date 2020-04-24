jest.unmock('../../lib/selectors');

import Component from 'metal-jsx';
import dom from 'metal-dom';
import {fromJS, List, Map} from 'immutable';
import {Provider} from 'metal-redux';
import {range} from 'lodash';

import LikeCounter from '../LikeCounter';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';

const store = fromJS(
	{
		people: range(5).reduce(
			(result, i) => result.setIn([i, 'data'], fromJS(LoopAssets.getPerson(i))),
			Map()
		)
	}
);

class LikeCounterExample extends Component {
	render() {
		return (
			<Provider store={mockStore(store)}>
				<LikeCounter {...this.otherProps()} />
			</Provider>
		);
	}
}

describe(
	'LikeCounter',
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
			'renders',
			() => {
				component = new LikeCounterExample(
					{
						count: 5,
						participantsIList: List(range(5))
					}
				);

				expect(component.element).not.toBeNull();

				const name = store.getIn(['people', 0, 'data', 'name']);

				expect(component.element.innerHTML).toContain(name);
			}
		);

		it(
			'calls onOtherClick when it\'s button is clicked',
			() => {
				const spy = jest.fn();

				component = new LikeCounterExample(
					{
						count: 10,
						onOtherClick: spy,
						participantsIList: List(range(5))
					}
				);

				dom.triggerEvent(component.element.querySelector('button'), 'click');

				jest.runAllTimers();

				expect(spy).toBeCalled();
			}
		);
	}
);