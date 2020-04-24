const TIME = 1483982880145;

const OriginalDate = window.Date;

class FakeDate {
	getDate() {
		return new OriginalDate(TIME).getDate();
	}

	getFullYear() {
		return new OriginalDate(TIME).getFullYear();
	}

	getMonth() {
		return new OriginalDate(TIME).getMonth();
	}

	toLocaleDateString() {
		return new OriginalDate(TIME).toLocaleDateString();
	}

	static now() {
		return TIME;
	}
}

export function useFakeDate() {
	window.Date = FakeDate;
}

export function useRealDate() {
	window.Date = OriginalDate;
}