function changeSearchPlaceholder() {
	const capabilityTitle = document.querySelector('.capability-title h1');
	const input = document.querySelector('.search-bar-keywords-input');

	if (input && capabilityTitle) {
		input.placeholder = `Search ${capabilityTitle.textContent}`;
		clearInterval(intervalId);
	}
}

const intervalId = setInterval(changeSearchPlaceholder, 300);

document.addEventListener('DOMContentLoaded', changeSearchPlaceholder);
