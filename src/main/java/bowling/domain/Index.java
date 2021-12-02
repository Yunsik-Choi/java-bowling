package bowling.domain;

import bowling.exception.IndexRangeException;

public class Index {
	public static final int MIN_OF_INDEX = 1;
	public static final int MAX_OF_INDEX = 10;

	private static final String NEXT_MESSAGE = String.format("다음 인덱스는 최대 값을 초과 합니다. (%d)", MAX_OF_INDEX);

	private final int value;

	private Index(int value) {
		validateRange(value);
		this.value = value;
	}

	private void validateRange(int value) {
		if (value < MIN_OF_INDEX || value > MAX_OF_INDEX) {
			throw new IndexRangeException();
		}
	}

	public static Index create(int value) {
		return new Index(value);
	}

	public static Index first() {
		return new Index(MIN_OF_INDEX);
	}

	public Index next() {
		if (value + 1 > MAX_OF_INDEX) {
			throw new IllegalStateException(NEXT_MESSAGE);
		}
		return Index.create(value + 1);
	}

	public int getValue() {
		return value;
	}

	public boolean isMax() {
		return value == MAX_OF_INDEX;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		Index index = (Index)obj;

		return value == index.value;
	}

	@Override
	public int hashCode() {
		return value;
	}
}