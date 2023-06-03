#!/bin/bash

# Init a submodule. Creates subfolder.
echo "Import into IntelliJ using File -> New -> Module from exitsing Sources"

mvn archetype:generate -DarchetypeGroupId=io.github.manedev79 \
                       -DarchetypeArtifactId=archetype-java-junit \
                       -DarchetypeVersion=1.0.5 \
                       -DjavaVersion=17
                       -DgitInit=false
