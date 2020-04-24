import Component, {Config} from 'metal-jsx';

import Card from './card';
import MarkdownContent from './MarkdownContent';

class MarkdownContentCard extends Component {
	created() {
		this.expandContent_ = this.expandContent_.bind(this);
	}

	expandContent_() {
		this.state.expanded_ = true;
	}

	render() {
		const {
			content,
			editURL,
			headerTitle,
			noContentMessage,
			truncatedContent
		} = this.props;

		const showTruncated = !!truncatedContent && !this.state.expanded_;

		const message = showTruncated ? truncatedContent : content;

		return (
			<Card {...this.otherProps()} elementClasses="markdown-content-card-container">
				<Card.Header>{headerTitle}</Card.Header>

				<div class="content">
					<MarkdownContent message={message} />

					{!message &&
						<span>
							<i>{noContentMessage}</i>

							{editURL &&
								<a class="edit" href={editURL}>{Liferay.Language.get('edit')}</a>
							}
						</span>
					}

					{showTruncated &&
						<a class="see-more" href="javascript:;" onClick={this.expandContent_}>
							{Liferay.Language.get('see-more')}
						</a>
					}
				</div>
			</Card>
		);
	}
}

MarkdownContentCard.PROPS = {
	content: Config.string(),
	editURL: Config.string(),
	headerTitle: Config.string(),
	noContentMessage: Config.string(),
	truncatedContent: Config.string()
};

MarkdownContentCard.STATE = {
	expanded_: Config.value(false)
};

export default MarkdownContentCard;