import Component, {Config} from 'metal-jsx';
import moment from 'moment';
import {fromJS} from 'immutable';

import Avatar from './Avatar';
import Icon from './Icon';
import SharedToSummary from './SharedToSummary';
import {formatTime} from '../lib/util';
import {lang} from '../lib/lang-util';

class AnnouncementHeader extends Component {
	render() {
		const {
			contentModifiedTime,
			createTime,
			creator,
			displayURL,
			id,
			location,
			sharedTo,
			stickyTime,
			title
		} = this.props;

		const stillSticky = stickyTime > Date.now();

		const stickyTill = stillSticky ? lang(Liferay.Language.get('this-announcement-will-remain-pinned-until-x'), [moment(stickyTime).format('MMM Do')], true) : Liferay.Language.get('this-announcement-is-not-pinned');

		return (
			<div class="announcement-header-container">
				<span class="sticky-time" data-tooltip title={stickyTill}>
					<Icon name="bullhorn" />
				</span>

				<div class="header-content text-center">
					<div class="title">
						{title}
					</div>

					<Avatar entity={creator} size="small" summary={true} />

					<SharedToSummary
						creator={creator}
						entitiesIList={sharedTo && fromJS(sharedTo)}
						id={id}
						verbiage={Liferay.Language.get('x-shared-an-announcement-with-x')}
					/>

					<a class="secondary-info" href={displayURL}>
						<span data-tooltip title={formatTime(createTime, true)}>{formatTime(createTime)}</span>

						{!!contentModifiedTime &&
							<span>{` - ${Liferay.Language.get('edited')}`}</span>
						}

						{location &&
							<span>{` · ${location}`}</span>
						}
					</a>
				</div>
			</div>
		);
	}
}

AnnouncementHeader.PROPS = {
	contentModifiedTime: Config.number(),
	createTime: Config.number(),
	creator: Config.object(),
	displayURL: Config.string(),
	id: Config.number(),
	location: Config.string(),
	sharedTo: Config.array(),
	stickyTime: Config.number(),
	title: Config.string
};

export default AnnouncementHeader;