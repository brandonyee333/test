import Component from 'metal-jsx';
import {noop} from 'lodash';
import {Map} from 'immutable';
import {Provider} from 'metal-redux';

import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import mockStore from '../../tests/mock-store';
import Page from '../pages/Page';

const {getPage, getPerson} = LoopAssets;

class PageKit extends Component {
	render() {
		return (
			<Kit header="PageContent">
				<Provider store={mockStore()}>
					<Page
						creatorIMap={Map(getPerson(0))}
						onDelete={noop}
						onEdit={noop}
						pageIMap={Map(getPage(0))}
					/>
				</Provider>
			</Kit>
		);
	}
}

export default PageKit;