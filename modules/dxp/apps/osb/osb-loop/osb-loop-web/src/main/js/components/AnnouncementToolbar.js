import Component, {Config} from 'metal-jsx';
import {bindAll, uniqueId} from 'lodash';
import {connect} from 'metal-redux';
import {Map} from 'immutable';

import Checkbox from './Checkbox';
import SelectInput from './SelectInput';
import SelectInputEntityItem from './SelectInputEntityItem';
import {getCurrentPerson, getRel} from '../lib/selectors';
import {classNameIdToSchema, getEntityId} from '../lib/util';
import {getPluralMessage} from '../lib/lang-util';

const DAY_SELECT_VALUES = [0, 1, 2, 3, 4, 5];

class AnnouncementToolbar extends Component {
	created() {
		bindAll(
			this,
			'handleEmailCheckboxChange_',
			'handlePostAsChange_',
			'handleTimeChange_'
		);

		this.handleDataRequest_();

		this._id = uniqueId();
	}

	dispose() {
		this._request.cancel();
	}

	handleDataRequest_() {
		this._request = this.props.dataRequest().then(
			response => {
				this.state.items_ = response.data;
			}
		);
	}

	handleEmailCheckboxChange_() {
		this.props.onChange({sendEmailNotifications: !this.props.sendEmailNotifications});
	}

	handlePostAsChange_({entityClassNameId, entityClassPK}) {
		this.props.onChange(
			{
				creatorClassNameId: entityClassNameId,
				creatorClassPK: entityClassPK
			}
		);
	}

	handleTimeChange_(event) {
		this.props.onChange({stickyTime: Number(event.target.value)});
	}

	renderItem_(item, selected) {
		return [<SelectInputEntityItem entity={item} key={getEntityId(item)} selected={selected} />];
	}

	render() {
		const {
			currentPersonIMap,
			editing,
			postAsIMap,
			sendEmailNotifications,
			stickyTime
		} = this.props;

		const daysSelectId = `${this._id}_days`;
		const postAsSelectId = `${this._id}_postAs`;

		return (
			<div class="announcement-toolbar-container">
				<div class="select-wrapper">
					<div class="days">
						<label htmlFor={daysSelectId}>{Liferay.Language.get('remain-pinned-on-following-tab-for')}</label>

						<select id={daysSelectId} onChange={this.handleTimeChange_} value={stickyTime}>
							{
								DAY_SELECT_VALUES.map(
									days => (
										<option key={days} selected={days === stickyTime} value={days}>
											{days === 0 &&
												Liferay.Language.get('not-pinned')
											}

											{days > 0 &&
												getPluralMessage(
													Liferay.Language.get('x-day'),
													Liferay.Language.get('x-days'),
													days
												)
											}
										</option>
									)
								)
							}
						</select>
					</div>

					<div class="post-as">
						<label htmlFor={postAsSelectId}>{Liferay.Language.get('post-as')}</label>

						<SelectInput
							disabled={editing}
							disabledTitle={Liferay.Language.get('sorry-you-cannot-edit-the-author-of-an-announcement')}
							getId={getEntityId}
							itemRenderer={this.renderItem_}
							items={[currentPersonIMap.toJS(), ...this.state.items_]}
							onSelect={this.handlePostAsChange_}
							selectedItem={postAsIMap.toJS()}
							selectId={postAsSelectId}
						/>
					</div>
				</div>

				<div class="send-email">
					<Checkbox
						checked={sendEmailNotifications}
						data-tooltip
						label={editing ? Liferay.Language.get('send-new-email-notification') : Liferay.Language.get('send-email-notification')}
						onChange={this.handleEmailCheckboxChange_}
						title={Liferay.Language.get('emails-only-notify-mentions')}
					/>
				</div>
			</div>
		);
	}
}

const STORE = {
	currentPersonIMap: Config.instanceOf(Map),
	postAsIMap: Config.instanceOf(Map)
};

AnnouncementToolbar.PROPS = {
	...STORE,
	dataRequest: Config.func(),
	editing: Config.bool(),
	onChange: Config.func(),
	postAsClassNameId: Config.number(),
	postAsId: Config.number(),
	sendEmailNotifications: Config.bool(),
	stickyTime: Config.number().value(1)
};

AnnouncementToolbar.STATE = {
	items_: Config.value([])
};

export default connect(
	(state, {postAsClassNameId, postAsId}) => (
		{
			currentPersonIMap: getCurrentPerson(state),
			postAsIMap: getRel(classNameIdToSchema(postAsClassNameId), state, postAsId, Map())
		}
	)
)(AnnouncementToolbar);