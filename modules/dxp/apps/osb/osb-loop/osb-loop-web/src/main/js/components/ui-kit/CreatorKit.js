import Component, {Config} from 'metal-jsx';
import Promise from 'metal-promise';
import {fromJS, Map} from 'immutable';
import {noop} from 'lodash';
import {Provider} from 'metal-redux';

import Card from '../card';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';
import mockStore from '../../tests/mock-store';
import {createEmptyPostData, Creator} from '../creator';

const emptyPromise = () => Promise.resolve({data: []});

const currentPersonClassPK = LoopConstants.currentPerson.entityClassPK;

const state = Map().setIn(
	['people', currentPersonClassPK, 'data'],
	fromJS(LoopAssets.getPerson(currentPersonClassPK))
);

class CreatorKit extends Component {
	created() {
		this.handleChange_ = this.handleChange_.bind(this);
	}

	handleAlert_() {
		alert('test function');
	}

	handleChange_(postData) {
		this.state.postData = postData;
	}

	render() {
		return (
			<Kit card={false} header="Creator">
				<Provider store={mockStore(state)}>
					<Card>
						<Creator
							addDirtyState={noop}
							dirtyState={Map()}
							fetchLeadingDivisions={emptyPromise}
							onPostDataChange={this.handleChange_}
							onPreview={this.handleAlert_}
							onSubmit={this.handleAlert_}
							placeholder={'Foo Placeholder...'}
							postData={this.state.postData}
							removeDirtyState={noop}
							submitButtonTitle={'Submit'}
						/>
					</Card>
				</Provider>
			</Kit>
		);
	}
}

CreatorKit.STATE = {
	postData: Config.value(createEmptyPostData())
};

CreatorKit.SYNC_UPDATES = true;

export default CreatorKit;