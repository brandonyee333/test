import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import IconLabel from '../IconLabel';
import {lang} from '../../lib/lang-util';

class AnnouncementTitle extends Component {
	created() {
		this.handleTitleChange_ = this.handleTitleChange_.bind(this);
	}

	handleTitleChange_(event) {
		this.props.onTitleChange(event.target.value);
	}

	render() {
		const {
			requireTitleHighlight,
			title,
			titleCharacterLimit,
			titleTooLong
		} = this.props;

		return (
			<div
				class={
					getCN(
						'announcement-title-container',
						{
							'required-highlight': requireTitleHighlight && !title,
							warn: titleTooLong
						}
					)
				}
			>
				<input
					onInput={this.handleTitleChange_}
					placeholder={Liferay.Language.get('title-required')}
					type="text"
					value={title}
				/>

				{titleTooLong &&
					<div class="length-warning">
						<IconLabel
							label={lang(Liferay.Language.get('the-title-must-have-less-than-x-characters'), [titleCharacterLimit])}
							name="alert"
							size="small"
						/>
					</div>
				}
			</div>
		);
	}
}

AnnouncementTitle.PROPS = {
	onTitleChange: Config.func(),
	requireTitleHighlight: Config.bool(),
	title: Config.string(),
	titleCharacterLimit: Config.number(),
	titleTooLong: Config.bool()
};

export default AnnouncementTitle;