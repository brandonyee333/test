import Component from 'metal-jsx';
import {Provider} from 'metal-redux';

import mockStore from '../../tests/mock-store';
import PostHeader from '../PostHeader';

class PostHeaderExample extends Component {
	render() {
		return (
			<Provider store={mockStore()}>
				<PostHeader
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
	'PostHeader',
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
				component = new PostHeaderExample();

				expect(component).toMatchSnapshot();
			}
		);
	}
);