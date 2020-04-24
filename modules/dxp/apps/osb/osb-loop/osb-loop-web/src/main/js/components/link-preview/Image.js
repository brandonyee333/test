import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';

import ExternalLink from '../ExternalLink';
import Icon from '../Icon';
import {addParams} from '../../lib/request';

class Image extends Component {
	created(props) {
		this.handlePlay_ = this.handlePlay_.bind(this);
	}

	handlePlay_() {
		this.state.play_ = true;
	}

	render() {
		const {play_} = this.state;

		const {
			imageURL,
			title,
			url,
			videoURL
		} = this.props.linkData;

		const classNames = getCN(
			'link-preview-image-container',
			{
				'embedded-video': play_
			}
		);

		return (
			<div class={classNames}>
				{videoURL && !play_ &&
					<div class="play-overlay play-video" onClick={this.handlePlay_}>
						<Icon multiplier={3} name="play" />
					</div>
				}

				{!play_ &&
					<ExternalLink elementClasses="image-link" href={url}>
						<img alt={title} src={imageURL} />
					</ExternalLink>
				}

				{play_ &&
					<iframe
						allowFullScreen
						frameBorder="0"
						src={
							addParams(
								videoURL,
								{
									autoplay: 1
								}
							)
						}
						type="text/html"
					/>
				}
			</div>
		);
	}
}

Image.PROPS = {
	linkData: Config.object()
};

Image.STATE = {
	play_: Config.value(false)
};

export default Image;