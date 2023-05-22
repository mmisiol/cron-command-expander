
- JDK of version 1.17 is required to run the project.
- Since no reference was given. Cron syntax definition was taken from [https://www.ibm.com/docs/en/db2oc?topic=task-unix-cron-format](https://github.com/mmisiol/cron-command-expander/blob/master/readme.md)

### Usage 

Instruction assumes that you are using a linux based system. For Windows use _gradlew.bat_ wrapper.


Download or clone the code from [https://github.com/mmisiol/cron-command-expander](https://github.com/mmisiol/cron-command-expander/blob/master/readme.md)

### Run from terminal

Enter project root directory and build the project
```
./gradlew build
```

Run the jar. Feel free to modify the cron string.
```
java -jar ./build/libs/cron-command-expander-1.0-SNAPSHOT.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
```

### Intellij

Alternatively you can run the project from intellij IDE

1. Import to IDE using _File -> New -> Project from existing sources._ Select the _build.gradle_ file in file browser.
2. Navigate to _org.mmi.Main_ class and right-click on the _main_ method. Pick _Modify run configuration..._ from the menu.
3. Paste your cron string in _Program arguments_ field. Wrap the cron string in double quotes.
4. Save the run configuration using _OK_ button. Run the program using green _Run_ icon in the top right corner.


