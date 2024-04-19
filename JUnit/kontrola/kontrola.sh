#!/bin/bash
clear

javac -cp apiguardian-api-1.0.0.jar:junit-jupiter-api-5.3.2.jar:junit-jupiter-engine-5.3.2.jar:junit-platform-commons-1.3.2.jar:junit-platform-engine-1.3.2.jar:junit-platform-launcher-1.3.2.jar:opentest4j-1.1.1.jar:. -d . -encoding UTF-8 test/oks02/*.java
java -cp apiguardian-api-1.0.0.jar:junit-jupiter-api-5.3.2.jar:junit-jupiter-engine-5.3.2.jar:junit-platform-commons-1.3.2.jar:junit-platform-engine-1.3.2.jar:junit-platform-launcher-1.3.2.jar:opentest4j-1.1.1.jar:. oks02.Kontrola_Prj_02
