import Component, {Config} from 'metal-jsx';
import {List} from 'immutable';

import Icon from './Icon';
import SharedToSummary from './SharedToSummary';
import {formatTime} from '../lib/util';

class PostHeaderText extends Component {
	render() {
		const {
			contentModifiedTime,
			createTime,
			creator,
			entitiesIList,
			id,
			location,
			nowrap,
			privateAnnouncement,
			url,
			verbiage
		} = this.props;

		const time = contentModifiedTime ? contentModifiedTime : createTime;

		return (
			<div class="header-text-container">
				<SharedToSummary
					creator={creator}
					entitiesIList={entitiesIList}
					id={id}
					nowrap={nowrap}
					verbiage={verbiage}
				/>

				{privateAnnouncement &&
					<Icon
						data-tooltip
						display="default"
						name="lock"
						size="small"
						title={Liferay.Language.get('private-announcement')}
					/>
				}

				<a class="secondary-info" href={url}>
					<span data-tooltip title={formatTime(time, true)}>{formatTime(time)}</span>

					{contentModifiedTime > createTime &&
						` - ${Liferay.Language.get('edited')}`
					}

					{location &&
						` • ${location}`
					}
				</a>
			</div>
		);
	}
}

PostHeaderText.PROPS = {
	contentModifiedTime: Config.number(),
	createTime: Config.number(),
	creator: Config.object(),
	entitiesIList: Config.instanceOf(List),
	id: Config.number(),
	location: Config.string(),
	nowrap: Config.bool(),
	privateAnnouncement: Config.bool(),
	url: Config.string(),
	verbiage: Config.string().value(Liferay.Language.get('x-shared-with-x'))
};

export default PostHeaderText;