VERSION = 1.0.0
all: target/fine-$(VERSION).jar

target/fine-$(VERSION).jar:
	mvn package -f pom.xml