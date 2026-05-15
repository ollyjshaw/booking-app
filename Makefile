.PHONY: build test run clean

build:
	./gradlew assemble

test:
	./gradlew test

run:
	./gradlew run

clean:
	./gradlew clean
