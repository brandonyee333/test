import Component from 'metal-jsx';
import {Provider} from 'metal-redux';
import {List} from 'immutable';

import mockStore from '../../tests/mock-store';
import PostHeaderText from '../PostHeaderText';
import {formatTime} from '../../lib/util';

class PostHeaderTextExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<PostHeaderText
					{...this.otherProps()}
					creator={LoopConstants.currentPerson}
					entitiesIList={List()}
				/>
			</Provider>
		);
	}
}

describe(
	'PostHeaderText',
	() => {
		let component;

		formatTime.mockImplementation(
			(time, abs) => {
				return abs ? 'January 9, 2017 9:28 AM' : '3 days ago';
			}
		);

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
				component = new PostHeaderTextExample();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should render edited',
			() => {
				component = new PostHeaderTextExample(
					{
						contentModifiedTime: 1,
						createTime: 0
					}
				);

				expect(component.element.querySelector('.secondary-info').innerHTML).toContain(' - edited');
			}
		);

		it(
			'should not render edited',
			() => {
				component = new PostHeaderTextExample();

				expect(component.element.querySelector('.secondary-info').innerHTML).not.toContain(' - edited');
			}
		);

		it(
			'should render location',
			() => {
				component = new PostHeaderTextExample({location: 'Diamond Bar, USA'});

				expect(component.element.querySelector('.secondary-info').innerHTML).toContain('Diamond Bar, USA');
			}
		);

		it(
			'should not render location',
			() => {
				component = new PostHeaderTextExample();

				expect(component.element.querySelector('.secondary-info').innerHTML).not.toContain('Diamond Bar, USA');
			}
		);
	}
);