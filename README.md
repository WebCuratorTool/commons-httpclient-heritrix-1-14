# commons-httpclient-heritrix-1-14
Version 3.1 of commons-httpclient that incorporates the class changes found in heritrix 1.14.1.

## Synopsis

The heritrix-1.14.1.jar includes overrides of certain Apache commons-httpclient-3.1 code in its jar. These changes include
adjusted and additional methods and classes. These changes and subclass are included in the `heritrix-1.14.1.jar`. Only
the changed classes are included in the `heritrix-1.14.1.jar`, which means that the original `commons-httpclient-3.1.jar` must
also be included in the classpath. Potential problems occur when the class loader loads the unmodified classes from
`commons-httpclient-3.1.jar`instead of the modified classes from `heritrix-1.14.1.jar`. This project incorporates all the
changes together and produces a `commons-httpclient.jar` that eliminates issues with class loader variable ordering.

The whole purpose of this project to enable the Web Curator Tool (https://github.com/DIA-NZ/webcurator) to work with the Heritrix 1
agent.

## Warning

These changes only work if the dependencies have not been superseded by a more recent version. For Web Curator Tool it means that
the Heritrix 1 agent must be packaged in a war with this version of `commons-httpclient`.

## Motivation

Provide a non-conflicting set of dependencies so the heritrix 1.14.1 crawler can work without classpath conflicts.

## Code changes

The github source repository for these heritrix modifications can be found at
https://github.com/WebCuratorTool/heritrix-1-14-adjust. That version of Heritrix 1.14 works with this version of
`commons-httpclient`.

1. The heritrix 1.14 code that appeared in `org/apache/commons/httpclient` has been merged into the this codebase.
*NOTE* that these code changes ignored code differences that were not delimited by the comment `IA/HERITRIX CHANGE`
(we suppose that these other differences are due to a slight difference in the commons-httpclient source used by the
heritrix team).

2. The classes `LaxURI`, `LaxURLCodec` and `HttpRecorderMarker` have been moved from the heritrix 1.14 codebase into
this codebase. This is because these classes are used in the commons-httpclient modified code and maven builds do not
allow cyclic dependencies. Their package is now `org.apache.commons.httpclient.heritrix`.

3. This codebase has added an interface `org.apache.commons.httpclient.heritrix.HttpRecorder` that allows classes in
this codebase to reference the heritrix 1.14 `HttpRecorder` without having a dependency on the heritrix
`HttpRecorder` class. The heritrix `HttpRecorder` class implements the
`org.apache.commons.httpclient.heritrix.HttpRecorder` interface.

4. The static method `HttpRecorder#getHttpRecorder` has been replaced with
`org.apache.commons.httpclient.heritrix.HttpRecorderRetriever#getHttpRecorder`. This method returns the
interface `org.apache.commons.httpclient.heritrix.HttpRecorder`. To use non-interface methods, the returned object must
be cast to `org.archive.util.HttpRecorder`.

## Versioning

See the `pom.xml` file for the current jar version that will be generated. Previous versions have been tagged in the
git repository. To list the tags, use:
```
git tag -l
```

### Versioned artifacts

The original source files can be found in the subfolder `original_source`.

Pre-built artifacts can be found in the subfolder `release_archive`.

### Running a maven 2.x/3.x/gradle build with a commons-httpclient 3.1.x dependency

When running a maven or gradle build that requires this version of commons-pool, the following commands can be used to
install the dependency in a local maven 2 repository (run these commands from the root folder of this project):
```
mkdir -pv ./target/
git clone https://github.com/WebCuratorTool/commons-httpclient-heritrix-1-14.git ./target/commons-httpclient-heritrix-1-14
mvn install:install-file -Dfile=./target/commons-httpclient-heritrix-1-14/commons-httpclient-<version>.jar \
  -DpomFile=./target/commons-httpclient-heritrix-1-14/commons-httpclient-<version>.pom
```
Note that the version needs to be chosen for this script to work and the `mvn` in the command above is version 2.x or 3.x.

## Installation

The artifacts are built using maven and will deploy to a local maven version 2 repository.

### Java version for build

We recommended that Java 5 (version 1.5) be used for the build, as this is the version that was used to create the original
jar.

Java 1.5 can be downloaded and installed from Oracle (https://www.oracle.com/technetwork/java/javasebusiness/downloads/java-archive-downloads-javase5-419410.html).
This JVM can be run simply by setting `JAVA_HOME` to the unpacked location and including `JAVA_HOME/bin` in the shell
path.

### Maven version for build

We recommend that Maven 2.x (latest stable release 2.2.1) be used for the build (this is the version that was used to
create the jar artifacts). Maven 2.2.1 can be downloaded from
https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/2.2.1/. This version of maven can be run by setting
`MAVEN_HOME` to the path of the unpacked distribution and executing (on linux) `$MAVEN_HOME/bin/mvn clean install`.

### Missing unit tests

No unit tests were included in the source jar provided through Maven Central.

### Complete build

Simply put, execute the following on linux:
```
$MAVEN_HOME/bin/mvn [clean] install
```
or for Windows:
```
%MAVEN_HOME%/bin/mvn.bat [clean] install
```

## Updating the release_archive

When updating the release_archive with a newer version, include the `pom.xml` for that version of the project. This makes
it easier for other dependent project (especially the heritrix-1-14-adjust project) to populate its local maven
repository with the pom associated with the jar. This does involving copying the `pom.xml` into `release-archive` and
renaming it to `commons-httpclient-<version>.pom`.

## Contributors

The original commits are attributed to the people or organisations named in the `pom.xml` file. Issues related to the changes made
here are tracked through the github repository issue tracker. Note that there is no history prior to version 3.1 (which is when
the source was imported into this repository). See the git commits after the tagged 3.1 version for those contributors.

## License

&copy; 2019 National Library of New Zealand for all project changes past version 3.1. All rights reserved.
GNU Lesser General Public License (LGPL) version 2.1 *and* Apache License Version 2.0.

For code prior up to and including version 3.1, Apache License Version 2.0. The actual code changes from the heritrix-1.14.1
codebase are LGPL and are attributed to the heritrix team.

### License incompatability

Note that the LGPL and Apache License 2.0 are incompatible. There's not really any way to resolve this. The Heritrix 1.14.1
developers made changes incompatible with the Apache license (since they released their code under the LGPL).
