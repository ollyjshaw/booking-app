.PHONY: build test lint format run clean

build:
	./gradlew assemble

test:
	./gradlew test

lint:
	./gradlew ktlintCheck

format:
	./gradlew ktlintFormat

run:
	./gradlew run

clean:
	./gradlew clean
