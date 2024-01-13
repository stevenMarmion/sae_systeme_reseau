SOURCES_JAVA = src/src_class/*.java src/src_exception/*.java src/src_test/*.java src/src_client_serveur/*.java src/src_class/src_class_modele/*.java src/src_class/src_class_commande/*.java src/src_class/src_class_modeleBDD/*.java
BIN_JAVA_SERVER = src_test.MainTestServeur
BIN_JAVA_CLIENT = src_test.MainTestClient

.PHONY: compile
compile: 
	javac -d bin $(SOURCES_JAVA)

.PHONY: open_serveur
open_serveur:
	java -cp bin $(BIN_JAVA_SERVER)

.PHONY: open_client
open_client:
	java -cp bin $(BIN_JAVA_CLIENT)