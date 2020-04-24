import Component from 'metal-jsx';
import Promise from 'metal-promise';
import {fromJS, Map} from 'immutable';

import AnnouncementToolbar from '../AnnouncementToolbar';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';
import LoopConstants from '../../lib/loop-constants';
import mockStore from '../../tests/mock-store';

const {
	entityClassNameId,
	entityClassPK: id
} = LoopConstants.currentPerson;

const store = mockStore(
	Map().mergeIn(
		['people', id, 'data'],
		fromJS(
			LoopAssets.getPerson(id)
		)
	)
);

const dataRequest = () => Promise.resolve({data: []});

const style = {
	display: 'block',
	width: '616px'
};

class AnnouncementToolbarKit extends Component {
	created() {
		this.handleAnnouncementChange_ = this.handleAnnouncementChange_.bind(this);
	}

	handleAnnouncementChange_(entity) {
		this.setState(entity);
	}

	render() {
		return (
			<Kit header="AnnouncementToolbar">
				<ElementContainer header="editing is true" style={style}>
					<AnnouncementToolbar
						dataRequest={dataRequest}
						editing={true}
						onChange={this.handleAnnouncementChange_}
						postAsClassNameId={entityClassNameId}
						postAsId={id}
						sendEmailNotifications={true}
						stickyTime={1}
						store={store}
					/>
				</ElementContainer>

				<ElementContainer header="editing is false" style={style}>
					<AnnouncementToolbar
						dataRequest={dataRequest}
						editing={false}
						onChange={this.handleAnnouncementChange_}
						postAsClassNameId={entityClassNameId}
						postAsId={id}
						sendEmailNotifications={true}
						stickyTime={1}
						store={store}
					/>
				</ElementContainer>

				<ElementContainer header="send email notification is false" style={style}>
					<AnnouncementToolbar
						dataRequest={dataRequest}
						editing={false}
						onChange={this.handleAnnouncementChange_}
						postAsClassNameId={entityClassNameId}
						postAsId={id}
						sendEmailNotifications={false}
						stickyTime={1}
						store={store}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default AnnouncementToolbarKit;