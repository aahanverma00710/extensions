## How to import into Project

### Step 1. Add the JitPack repository to your build file
```groovy
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
### Step 2. Add the dependency
```groovy
	dependencies {
	         implementation 'com.github.aahanverma00710:extensions:Tag'
	}
```
Or Maven:

```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>

	<dependency>
	    <groupId>com.github.aahanverma00710</groupId>
	    <artifactId>extensions</artifactId>
	    <version>Tag</version>
	</dependency>
