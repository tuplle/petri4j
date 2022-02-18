# Petri4j

[![GitHub](https://img.shields.io/github/license/mladoniczky/petri4j)](http://www.opensource.org/licenses/mit-license.php)
[![Java](https://img.shields.io/badge/Java-11-red)](https://openjdk.java.net/projects/jdk/11/)
[![GitHub release (latest SemVer)](https://img.shields.io/github/v/release/mladoniczky/petri4j?sort=semver&display_name=tag)](https://github.com/mladoniczky/petri4j/releases)
[![build](https://github.com/mladoniczky/petri4j/actions/workflows/master-build.yml/badge.svg)](https://github.com/mladoniczky/petri4j/actions/workflows/release-build.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=mladoniczky_petri4j&metric=alert_status)](https://sonarcloud.io/dashboard?id=mladoniczky_petri4j)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=mladoniczky_petri4j&metric=coverage)](https://sonarcloud.io/dashboard?id=mladoniczky_petri4j)
[![Known Vulnerabilities](https://snyk.io/test/github/mladoniczky/petri4j/badge.svg)](https://snyk.io/test/github/mladoniczky/petri4j)

> The simplest way to import Petri net

Petri4J is a lightweight java library to import Petri nets into your project/application. The library consists of basic
structure and objects to represent general definition of Petri nets. It provides import function with support of several
popular formats like Petriflow and JSON format.

## Installation

The library is available on Maven Central repository:

```xml
<dependency>
  <groupId>com.mladoniczky</groupId>
  <artifactId>petri4j</artifactId>
</dependency>
```

Or you can download release package from [here](https://github.com/mladoniczky/petri4j/releases/latest).

## Usage

To import your Petri net into application simply retrieve parser for desired format and load the file with the net.

```java
ParserFactory factory = ParserFactory.getInstance();
Parser parser = factory.getParser(ParsingFormat.PETRIFLOW);
Net net = parser.parse("my-petri-net.xml");
```

Net object is loaded and constructed synchronously. Only the actual petri net is read from the loaded format.

## Reporting issues

If you find a bug, let us know. First, please read our [Contribution guide](https://github.com/netgrif/petriflow.js/blob/master/CONTRIBUTING.md)

## License

Licensed under the MIT License; you may not use these files except in compliance with the License. You may obtain a copy of the License at

[http://www.opensource.org/licenses/mit-license.php](http://www.opensource.org/licenses/mit-license.php)

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
either express or implied. See the License for the specific language governing permissions and limitations under the License.
