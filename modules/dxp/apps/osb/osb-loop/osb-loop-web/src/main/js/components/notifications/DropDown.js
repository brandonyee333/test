import Component, {Config} from 'metal-jsx';
import favico from 'favico.js';
import {bindAll, debounce} from 'lodash';

import Card from '../card';
import Icon from '../Icon';
import IconLabel from '../IconLabel';
import Feed from './Feed';
import Settings from './Settings';
import {hotKeyManager} from '../../lib/HotKeyManager';

class DropDown extends Component {
	created() {
		bindAll(
			this,
			'align_',
			'handleResize_',
			'onBackClick_',
			'onClickOutside_',
			'onDropDownToggle_',
			'onShowSettingsClick_',
			'setDropDownMaxHeight_'
		);

		this.align_ = debounce(this.align_, 200);

		this._favico = new favico(
			{
				animation: 'none',
				position: 'up'
			}
		);

		this._scrollPosition = 0;
		this.setDropDownMaxHeight_();
	}

	align_() {
		const {content} = this.refs;

		if (content) {
			const {element} = content;

			const padding = 20;

			const offsetRight = window.innerWidth - element.getBoundingClientRect().right;

			if (offsetRight < padding) {
				element.setAttribute(
					'style',
					`left: -${Math.abs(element.offsetLeft) + Math.abs(offsetRight) + padding}px;`
				);
			}
			else {
				element.removeAttribute('style');
			}
		}
	}

	attached() {
		window.addEventListener('resize', this.align_);
		window.addEventListener('resize', this.handleResize_);

		hotKeyManager.bind(
			{
				action: this.onDropDownToggle_,
				definition: Liferay.Language.get('toggle-notifications-dropdown'),
				keys: 'n'
			}
		);
	}

	detached() {
		hotKeyManager.unbind('n');

		document.removeEventListener('mousedown', this.onClickOutside_);

		window.removeEventListener('resize', this.handleResize_);
	}

	handleResize_() {
		this.setDropDownMaxHeight_();
	}

	setDropDownMaxHeight_() {
		this.state.dropDownMaxHeight_ = window.innerHeight - 156;
	}

	getPageTitle_(count) {
		let title = document.title.replace(/^\(\d+\)\s*/, '');

		if (count > 0) {
			title = `(${count}) ${title}`;
		}

		return title;
	}

	onBackClick_() {
		this.setState(
			{showSettings_: false},
			() => {
				this.refs.feed.element.scrollTop = this._scrollPosition;
			}
		);
	}

	onClickOutside_(event) {
		if (this.state.open_ && !this.components.content.element.contains(event.target) && !this.element.contains(event.target)) {
			this.onDropDownToggle_();
		}
	}

	onDropDownToggle_() {
		const {
			refs: {feed},
			state: {open_}
		} = this;

		if (feed && open_) {
			this._scrollPosition = feed.element.scrollTop;
		}

		const newOpen = !open_;

		this.setState(
			{open_: newOpen},
			this.toggleCallback_(newOpen)
		);
	}

	onShowSettingsClick_() {
		this._scrollPosition = this.refs.feed.element.scrollTop;

		this.state.showSettings_ = true;
	}

	syncCount(newVal, oldVal) {
		if (newVal !== oldVal) {
			if (this._favico && this._favico.badge) {
				this._favico.badge(newVal);
			}

			document.title = this.getPageTitle_(newVal);
		}
	}

	toggleCallback_(open) {
		return () => {
			if (open) {
				const {count, onLoadNew} = this.props;

				document.addEventListener('mousedown', this.onClickOutside_);

				this.align_();

				onLoadNew();

				if (this.refs.feed && !count) {
					this.refs.feed.element.scrollTop = this._scrollPosition;
				}
			}
			else {
				document.removeEventListener('mousedown', this.onClickOutside_);
			}
		};
	}

	render() {
		const {
			count,
			hasMoreItems,
			items,
			loading,
			onItemClick,
			onLoadMore
		} = this.props;

		const {open_, showSettings_} = this.state;

		const style = `max-height: ${this.state.dropDownMaxHeight_}px; overflow-y: auto`;

		return (
			<div class="notifications-drop-down-container">
				<a
					class="toggle-icon"
					data-tooltip
					href="javascript:;"
					onClick={this.onDropDownToggle_}
					title={Liferay.Language.get('notifications')}
				>
					<Icon display="secondary" name="bell" />

					{!!count &&
						<div class="count">{count}</div>
					}
				</a>

				{open_ &&
					<Card floating={true} ref="content">
						<div style={style}>
							{!showSettings_ &&
								<div>
									<Card.Header>
										{Liferay.Language.get('notifications')}

										<a class="settings-toggle" href="javascript:;" onClick={this.onShowSettingsClick_}>
											<Icon name="gear" />
										</a>
									</Card.Header>

									<Feed
										hasMoreItems={hasMoreItems}
										items={items}
										loading={loading}
										onItemClick={onItemClick}
										onLoadMore={onLoadMore}
										ref="feed"
									/>
								</div>
							}

							{showSettings_ &&
								<div>
									<Card.Header>
										<a href="javascript:;" onClick={this.onBackClick_}>
											<IconLabel label={Liferay.Language.get('notification-settings')} name="arrow-left-rod" />
										</a>
									</Card.Header>

									<Settings />
								</div>
							}
						</div>
					</Card>
				}
			</div>
		);
	}
}

DropDown.PROPS = {
	count: Config.number().required(),
	hasMoreItems: Config.bool(),
	items: Config.array().required(),
	loading: Config.bool(),
	onItemClick: Config.func().required(),
	onLoadMore: Config.func().required(),
	onLoadNew: Config.func().required()
};

DropDown.STATE = {
	dropDownMaxHeight_: Config.value(100),
	open_: Config.value(false),
	showSettings_: Config.value(false)
};

export default DropDown;