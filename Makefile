VERSION = 1.0.0
all: fine-$(VERSION).jar

fine-1.0.0.jar:
	mvn package -f pom.xml