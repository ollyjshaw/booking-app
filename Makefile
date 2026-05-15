.PHONY: all build test lint format run clean check-updates

all: build test lint

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

check-updates:
	./gradlew dependencyUpdates
