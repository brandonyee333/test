import dom from 'metal-dom';

import CommentLikeCounter from '../CommentLikeCounter';

describe(
	'CommentLikeCounter',
	() => {
		let component;

		afterEach(
			() => {
				if (component) {
					component.dispose();
				}
			}
		);

		it(
			'renders',
			() => {
				component = new CommentLikeCounter(
					{
						count: 5,
						liked: false,
						onClick: jest.fn(),
						onCountClick: jest.fn()
					}
				);

				expect(component).toMatchSnapshot();

				expect(component).toMatchSnapshot();
			}
		);

		it(
			'should call onClick and onCountClick',
			() => {
				const onClickSpy = jest.fn();
				const onCountClickSpy = jest.fn();

				component = new CommentLikeCounter(
					{
						count: 5,
						liked: false,
						onClick: onClickSpy,
						onCountClick: onCountClickSpy
					}
				);

				expect(onClickSpy).not.toBeCalled();
				expect(onCountClickSpy).not.toBeCalled();

				dom.triggerEvent(component.element.querySelector('.like-action'), 'click');
				dom.triggerEvent(component.element.querySelector('.counter'), 'click');

				expect(onClickSpy).toBeCalled();
				expect(onCountClickSpy).toBeCalled();
			}
		);
	}
);