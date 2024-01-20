SOURCES_JAVA = src/src_class/*.java src/src_exception/*.java src/src_main/*.java src/src_test/*.java src/src_client_serveur/*.java src/src_class/src_class_modele/*.java src/src_class/src_class_commande/*.java src/src_class/src_class_modeleBDD/*.java
BIN_JAVA_SERVER = src_test.MainTestServeur
BIN_JAVA_CLIENT = src_test.MainTestClient
DOC_DIR = doc
SOURCES_TEST = src/src_test/*.java

.PHONY: compile
compile:
	javac -d bin $(SOURCES_JAVA)

.PHONY: open_serveur
open_serveur:
	java -cp bin $(BIN_JAVA_SERVER)

.PHONY: open_client
open_client:
	java -cp bin $(BIN_JAVA_CLIENT)

.PHONY: javadoc
javadoc:
	mkdir -p $(DOC_DIR)
	javadoc -d $(DOC_DIR) -quiet -Xdoclint:none $(SOURCES_JAVA)

.PHONY: compile_test
compile_test:
	javac -cp bin:$(CLASSPATH) -d bin $(SOURCES_JAVA) $(SOURCES_TEST)


.PHONY: run_client_test
run_client_test:
	java -cp bin:$(CLASSPATH):./mariadb-java-client.jar src_test.ClientTest

.PHONY: run_message_test
run_message_test:
	java -cp bin:$(CLASSPATH):./mariadb-java-client.jar src_test.MessageTest

.PHONY: run_session_test
run_session_test:
	java -cp bin:$(CLASSPATH):./mariadb-java-client.jar src_test.SessionTest

.PHONY: run_serveur_test
run_serveur_test:
	java -cp bin:$(CLASSPATH):./mariadb-java-client.jar src_test.ServerTest



.PHONY: run_tests
run_tests:
	java -cp bin:$(CLASSPATH) src_test.ClientTest
	java -cp bin:$(CLASSPATH) src_test.MessageTest

