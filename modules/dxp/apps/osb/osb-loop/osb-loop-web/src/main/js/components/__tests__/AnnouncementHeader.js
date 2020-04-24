import Component from 'metal-jsx';
import {Provider} from 'metal-redux';

import AnnouncementHeader from '../AnnouncementHeader';
import mockStore from '../../tests/mock-store';

class AnnouncementHeaderExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<AnnouncementHeader
					{...this.otherProps()}
					creator={LoopConstants.currentPerson}
					displayURL="/web/guest/home/-/loop/feed/0"
					id={0}
					sharedTo={[]}
				/>
			</Provider>
		);
	}
}

describe(
	'AnnouncementHeader',
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
				component = new AnnouncementHeaderExample();

				expect(component).toMatchSnapshot();
			}
		);
	}
);