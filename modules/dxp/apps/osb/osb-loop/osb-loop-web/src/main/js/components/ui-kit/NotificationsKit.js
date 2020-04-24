import Component from 'metal-jsx';
import {noop, range} from 'lodash';

import DropDown from '../notifications/DropDown';
import ElementContainer from './ElementContainer';
import Kit from './Kit';
import LoopAssets from '../../tests/loop-assets';

const itemsArray = range(6).map(item => LoopAssets.getNotification(item));

const styles = {
	backgroundColor: '#1F7AFF',
	padding: '20px 20px 20px 80%'
};

const request = () => Promise.resolve({data: []});

class NotificationsKit extends Component {
	render() {
		return (
			<Kit card={false} header="Notifications">
				<ElementContainer header="Notifications" style={styles}>
					<DropDown
						clearCount={noop}
						clearFeed={noop}
						count={0}
						hasMoreItems={false}
						items={itemsArray}
						loading={false}
						newFeedRequest={request}
						onItemClick={noop}
						onLoadMore={noop}
					/>
				</ElementContainer>

				<ElementContainer header="Notifications with 4 new notifications" style={styles}>
					<DropDown
						clearCount={noop}
						clearFeed={noop}
						count={4}
						hasMoreItems={true}
						items={itemsArray}
						loading={true}
						newFeedRequest={request}
						onItemClick={noop}
						onLoadMore={noop}
					/>
				</ElementContainer>

				<ElementContainer header="Notifications  with load older notifications" style={styles}>
					<DropDown
						clearCount={noop}
						clearFeed={noop}
						count={0}
						hasMoreItems={true}
						items={itemsArray}
						loading={false}
						newFeedRequest={request}
						onItemClick={noop}
						onLoadMore={noop}
					/>
				</ElementContainer>

				<ElementContainer header="Notifications loading icon for load more notifications" style={styles}>
					<DropDown
						clearCount={noop}
						clearFeed={noop}
						count={0}
						hasMoreItems={true}
						items={itemsArray}
						loading={true}
						newFeedRequest={request}
						onItemClick={noop}
						onLoadMore={noop}
					/>
				</ElementContainer>
			</Kit>
		);
	}
}

export default NotificationsKit;