import Component, {Config} from 'metal-jsx';
import {bindAll, findIndex, unionWith} from 'lodash';

import DropDown from './DropDown';
import LoopConstants from '../../lib/loop-constants';
import request from '../../lib/request';

const {portletId} = LoopConstants;

function removeDuplicateEntries(combinedArray) {
	return unionWith(
		combinedArray,
		(newItem, item) => newItem.id === item.id
	);
}

class Notifications extends Component {
	created() {
		bindAll(
			this,
			'handleItemClick_',
			'handleLoadMore_',
			'handleLoadNew_',
			'onPollerUpdate_',
			'slowPolling_',
			'startPolling_'
		);

		this._pollerTimestamp = 0;
		this._pollingDisabled = false;
	}

	attached() {
		this.bindPolling_();
	}

	detached() {
		window.removeEventListener('blur', this.slowPolling_);
		window.removeEventListener('focus', this.startPolling_);
	}

	bindPolling_() {
		this.initializePoller_();

		window.addEventListener('blur', this.slowPolling_);
		window.addEventListener('focus', this.startPolling_);

		Liferay.on(
			'sessionExpired',
			() => {
				this._pollingDisabled = true;

				Liferay.Poller.removeListener(portletId);
			}
		);
	}

	clearCount_() {
		request(
			{
				controller: 'notifications',
				controllerMethod: 'clearCount.json'
			}
		).then(
			response => {
				this.state.count_ = 0;
			}
		);
	}

	fetchNotifications(end, start = 0) {
		this.state.loading_ = true;

		if (start === 0) {
			this.state.feedTimeStamp_ = Date.now();
		}

		return request(
			{
				controller: 'notifications',
				controllerMethod: 'viewOldNotifications.json',
				data: {
					end,
					start,
					timestamp: this.state.feedTimeStamp_
				},
				method: 'GET'
			}
		).then(
			response => {
				let newItems = [];

				if (response.groupedUserNotificationEventsJSONArray) {
					newItems = response.groupedUserNotificationEventsJSONArray;
				}

				this.state.loading_ = false;

				return newItems;
			}
		).catch(
			() => {
				this.state.loading_ = false;
			}
		);
	}

	handleItemClick_(id) {
		const {items_} = this.state;

		const index = findIndex(
			items_,
			item => item.id == id
		);

		const newItems = items_;

		newItems[index].read = true;

		this.state.items_ = newItems;

		Liferay.Loop.SPA.navigate(newItems[index].displayURL);
	}

	handleLoadMore_() {
		const {hasMoreItems_, items_} = this.state;

		if (hasMoreItems_) {
			this.state.loading_ = true;

			const {itemsPerPage} = this.props;

			const start = items_.length;

			this.fetchNotifications(start + itemsPerPage, start).then(
				newItems => {
					this.setState(
						{
							hasMoreItems_: newItems.length >= itemsPerPage,
							items_: removeDuplicateEntries([...items_, ...newItems])
						}
					);
				}
			);
		}
	}

	handleLoadNew_() {
		const {count_, items_} = this.state;

		let end = count_;

		if (!items_.length) {
			end = this.props.itemsPerPage;
		}

		if (end) {
			this.fetchNotifications(end).then(
				newItems => {
					this.state.items_ = removeDuplicateEntries([...newItems, ...items_]);

					this.clearCount_();
				}
			);
		}
	}

	initializePoller_() {

		// LOOP-3963

		AUI().on(
			'domready',
			() => {
				Liferay.Poller.addListener(portletId, this.onPollerUpdate_, this);

				this.startPolling_();
			}
		);
	}

	onPollerUpdate_(event) {
		const newTimestamp = parseInt(event.timestamp, 10);

		if (newTimestamp > this._pollerTimestamp) {
			this._pollerTimestamp = newTimestamp;

			const newCount = parseInt(event.groupedUserNotificationEventsCount, 10);

			if (newCount !== this.state.count_) {
				this.state.count_ = newCount;
			}
		}
	}

	slowPolling_() {
		if (!this._pollingDisabled) {
			Liferay.Poller.cancelCustomDelay();
		}
	}

	startPolling_() {
		if (!this._pollingDisabled) {
			Liferay.Poller.setCustomDelay(1000);

			Liferay.Poller.resume();
		}
	}

	render() {
		const {
			count_,
			hasMoreItems_,
			items_,
			loading_
		} = this.state;

		return (
			<DropDown
				count={count_}
				hasMoreItems={hasMoreItems_}
				items={items_}
				loading={loading_}
				onItemClick={this.handleItemClick_}
				onLoadMore={this.handleLoadMore_}
				onLoadNew={this.handleLoadNew_}
			/>
		);
	}
}

Notifications.PROPS = {
	itemsPerPage: Config.number().required()
};

Notifications.STATE = {
	count_: Config.value(0),
	feedTimeStamp_: Config.number().value(0),
	hasMoreItems_: Config.value(true),
	items_: Config.array().value([]),
	loading_: Config.value(false)
};

export default Notifications;