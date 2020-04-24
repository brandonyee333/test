import Component, {Config} from 'metal-jsx';
import getCN from 'classnames';
import {bindAll} from 'lodash';
import {connect} from 'metal-redux';

import Button from './Button';
import Card from './card';
import IconLabel from './IconLabel';
import MarkdownContent from './MarkdownContent';
import MarkdownEditorHeader from './MarkdownEditorHeader';
import MarkdownSupportedIcon from './MarkdownSupportedIcon';
import sendRequest from '../lib/request';
import {addAlert, alertTypes, updateAlert} from '../actions/alerts';
import {ALL_PAGES} from '../lib/router-constants';
import {lang} from '../lib/lang-util';

import {
	addPage,
	clearPages,
	fetchPage,
	updatePage
} from '../actions/pages';

const MAX_TITLE_LENGTH = 75;

class PageEditor extends Component {
	created() {
		bindAll(
			this,
			'handleAddPage_',
			'handleContentChange_',
			'handleEditClick_',
			'handlePreviewClick_',
			'handleSubmit_',
			'handleTitleChange_',
			'handleUpdatePage_'
		);

		if (this.props.id) {
			this.handleFetchPage_();
		}
	}

	disposed() {
		if (this._request) {
			this._request.cancel();
		}
	}

	getMarkdown_() {
		this._request = sendRequest(
			{
				controller: 'home',
				controllerMethod: 'viewMarkdownHTML.json',
				data: {
					markdownSource: this.refs.content.value
				}
			}
		).then(
			markdown =>	{
				this.state.markdownContent_ = markdown;
			}
		);

		return this._request;
	}

	handleAddPage_(content) {
		const {addPage, ownerClassNameId, ownerId} = this.props;

		return addPage({...content, ownerClassNameId, ownerId});
	}

	handleContentChange_() {
		const {content} = this.refs;

		this.state.rawMessage_ = content.value;

		content.style.height = `${content.scrollHeight}px`;
	}

	handleEditClick_() {
		this.setState(
			{preview_: false},
			this.handleContentChange_
		);
	}

	handleFetchPage_() {
		const {
			addAlert,
			displayURL,
			fetchPage,
			id
		} = this.props;

		return fetchPage(id).then(
			({data}) => {
				const {
					payload: {rawMessage},
					title
				} = data;

				this.setState(
					{
						rawMessage_: rawMessage,
						title_: title
					},
					this.handleContentChange_
				);
			}
		).catch(
			({message}) => {
				Liferay.Loop.SPA.navigate(`${displayURL}/${ALL_PAGES}`);

				addAlert(
					{
						alertType: alertTypes.ERROR,
						message: message || Liferay.Language.get('unable-to-fetch-page')
					}
				);
			}
		);
	}

	handlePreviewClick_() {
		if (!this.state.preview_) {
			this.getMarkdown_().then(
				() => {
					this.state.preview_ = true;
				}
			);
		}
	}

	handleSubmit_() {
		const {
			props: {
				addAlert,
				clearPages,
				displayURL,
				id,
				ownerId,
				updateAlert
			},
			state: {rawMessage_, title_}
		} = this;

		const submitFn = id ? this.handleUpdatePage_ : this.handleAddPage_;

		const alert = addAlert(
			{
				alertType: alertTypes.PENDING,
				message: id ? Liferay.Language.get('updating-page') : Liferay.Language.get('adding-page'),
				timeout: false
			}
		);

		return submitFn(
			{
				payload: JSON.stringify(
					{
						message: rawMessage_,
						type: 'text'
					}
				),
				title: title_
			}
		).then(
			({data}) => {
				clearPages(ownerId);

				Liferay.Loop.SPA.navigate(`${displayURL}/${ALL_PAGES}`);

				updateAlert(
					{
						alertType: alertTypes.SUCCESS,
						id: alert.id,
						message: id ? Liferay.Language.get('page-was-successfully-updated') : Liferay.Language.get('page-was-successfully-added')
					}
				);
			}
		).catch(
			({message}) => {
				let errorMessage = message;

				if (!message) {
					errorMessage = id ? Liferay.Language.get('page-could-not-be-updated-at-this-time') : Liferay.Language.get('page-could-not-be-added-at-this-time');
				}

				updateAlert(
					{
						alertType: alertTypes.ERROR,
						id: alert.id,
						message: errorMessage
					}
				);
			}
		);
	}

	handleTitleChange_() {
		this.state.title_ = this.refs.title.value;
	}

	handleUpdatePage_(content) {
		const {id, updatePage} = this.props;

		return updatePage({...content, id});
	}

	render() {
		const {
			props: {
				contentPlaceholder,
				displayURL,
				id,
				titlePlaceholder
			},
			state: {
				markdownContent_,
				preview_,
				rawMessage_,
				title_
			}
		} = this;

		const titleTooLong = title_.length > MAX_TITLE_LENGTH;

		return (
			<Card elementClasses="page-editor-container">
				<Card.Body>
					<MarkdownEditorHeader
						onEditClick={this.handleEditClick_}
						onPreviewClick={this.handlePreviewClick_}
						preview={preview_}
					/>

					{!preview_ &&
						<div class={getCN({warning: titleTooLong})}>
							<input
								class="title"
								onInput={this.handleTitleChange_}
								placeholder={titlePlaceholder}
								ref="title"
								type="text"
								value={title_}
							/>

							{titleTooLong &&
								<div class="length-warning">
									<IconLabel
										label={lang(
											Liferay.Language.get('the-title-must-have-less-than-x-characters'),
											[MAX_TITLE_LENGTH + 1]
										)}
										name="alert"
										size="small"
									/>
								</div>
							}

							<textarea
								class="content"
								onInput={this.handleContentChange_}
								placeholder={contentPlaceholder}
								ref="content"
								resize={false}
								type="text"
								value={rawMessage_}
							/>
						</div>
					}

					{preview_ &&
						<div>
							<h1 class="title">{title_}</h1>

							<MarkdownContent message={markdownContent_} />
						</div>
					}

					<MarkdownSupportedIcon />
				</Card.Body>

				<Card.Footer>
					<Button href={id ? `${displayURL}/${id}` : displayURL} role="secondary" useAnchor={true}>{Liferay.Language.get('cancel')}</Button>

					<Button disabled={titleTooLong || !rawMessage_ || !title_} onClick={this.handleSubmit_} role="primary">{Liferay.Language.get('publish')}</Button>
				</Card.Footer>
			</Card>
		);
	}
}

const STORE = {
	addAlert: Config.func(),
	addPage: Config.func(),
	clearPages: Config.func(),
	fetchPage: Config.func(),
	updatePage: Config.func()
};

PageEditor.PROPS = {
	...STORE,
	contentPlaceholder: Config.string().value(''),
	displayURL: Config.string(),
	id: Config.number(),
	ownerClassNameId: Config.number(),
	ownerId: Config.number(),
	titlePlaceholder: Config.string().value('')
};

PageEditor.STATE = {
	markdownContent_: Config.string().value(''),
	preview_: Config.bool().value(false),
	rawMessage_: Config.string().value(''),
	title_: Config.string().value('')
};

export default connect(
	null,
	{
		addAlert,
		addPage,
		clearPages,
		fetchPage,
		updateAlert,
		updatePage
	}
)(PageEditor);