## Integrated Development Environment (IDE) Usage

This document contains basic instructions on essential IDE usage for VSCode, IntelliJ, and Eclipse.

---
**Importing (or opening a project) into your IDE**

VSCode, IntelliJ, and Eclipse (as well as other less-commonly known IDEs) understand the general "Maven-type" project structure, as well as how to work directly with projects based on Maven or Gradle.

* **VSCode**

1. Open a project folder by using the File menu and select "Open Folder". Select the root of the folder where your project files are located.  This is the folder containing your Maven `pom.xml` or `build.gradle` files.
    * Give VS Code a moment to initialize its workspace, especially if this is the first time importing a Gradle/Maven project.
    * If you see a message about enabling null analysis for the project, you can select either enable or disable.
    * If you see a message about installing _Extension Pack for Java_, take the install option.
    * If you see a message _Do you trust the authors of the files in this folder_, check the checkbox and click the "trust" button..

1. ***VS Code should*** recognize the project as a Maven or Gradle project and automatically import it. If it does not and you are using Gradle, you may need to manually import it:

    1. Open the Command Palette (Ctrl+Shift+P or Cmd+Shift+P on macOS).
    1. Type "Gradle: Import Gradle Project" and select it.
    1. Navigate to the project directory and select the build.gradle file.
    1. Click "Open".

1. If you get an error such as “invalid source release ” you may need to lower the Java version specified in your `build.gradle` or Maven  `pom.xml` file. 

    1. Open build.gradle or pom.xml.
    1. In Gradle, find the section on java / sourceCompatibility. In Maven, find the properties section.
    1. Lower the Java version number. For example, if 21 is too high, try 17.

    The project should be free of compiler errors at this point.  Address any issues you see before continuing.

* **Eclipse**

1. Launch Eclipse.
1. In the *Workspace Launcher* dialog, create a new namespace in some location you will remember, like **C:\workspace**. Click *Launch*.
1. Close the *Welcome* panel if open.
1. If using Gradle, you probably need to update it:
    1. From the menu, select *Help > Eclipse Marketplace*.
    1. In the Find box, type *gradle* and hit enter.
    1. Locate **Buildship Gradle Integration 3.0** and click the __Installed__ button.
        * This will take you to the *Installed* tab.
    1. Locate **Buildship Gradle Integration 3.0** and click the *Update* button.
    1. Accept the license agreement and click *Finish*.
    1. Wait for the installation to be completed.
        * You may see a Security warning, if so, click *Install anyway*
    1. A dialog will open when the updates are installed, click Restart Now.
        * We are ready to work with Gradle.
1. From the main menu, select File / Import.
1. In the wizard select either Maven or Gradle depending on the project type.  Then select either Existing Gradle Project or Existing Maven project. Next.
1. Use “Browse” and navigate to the folder containing your project files.  This is the folder containing your Maven `pom.xml` or `build.gradle` files.  Finish.
1. Give Eclipse a few moments to digest this project, especially if this is the first project worked with since a fresh installation.
1. Verify that there are no compilation problems.

* **IntelliJ**

1. Launch IntelliJ.
1. Use “Open” to open the project.
1. Select thefolder containing your project files.  This is the folder containing your Maven `pom.xml` or `build.gradle` files.
1. If prompted about which configuration to use, select either Gradle or Maven, depending on your preference.
1. Give IntelliJ a moment to digest this project, especially if this is the first project worked with since a fresh installation.


---
**Running an Application**

1. Find the main application class.  It will be located within a package in `src/main/java/`.  It will contain a `public static void main()` method and is often named `Application.java`.

* **VSCode**
    * Run the application by right-clicking on the file in the folder structure and selecting _Run Java_.  Or, open the file and click the “Run” option hovering over the main() method.

* **IntelliJ**
    * Right-click, select _Run 'Application.main()'_.  Or, click the green triangle in the 'gutter' next to the `public static void main()` method. 

* **Eclipse**: 
    * Right-click, Select Run As / Java Application.

---
**Finding TODOs**

Most of the lab projects contain TODO instructions that will tell you how do complete the exercise.  

* **Using VS Code:** Select "Edit" / "Find in Files..." and search for "TODO".
* **Using IntelliJ:**  Select "Edit" / "Find" / "Find in Files..." and search for "TODO".  Use the "Open in Find Window" button to place the results in a tab for easy navigation.
* **Using Eclipse:** Select "Window" / "Show View" / "Tasks".  Use the "Search" view to search for "TODO".  Find a filter icon on the top of this tab.  Check "Show all items", "TODOs".  For scope select "on elements in the selected projects".  The list of TODOs will appear in order!

IMPORTANT: Work through the TODO instructions in order!   


---
**Running Tests**

IDEs consider classes within the `src/test/java/` folder to be automated tests.  Running these test classes is slightly different than running a Java application.

* **VS Code**: 
    * In the "explorer" view on the left, Right-click on the class, Select "Run Tests".  Or, find the green triangle in the editor’s “gutter”. Click on this to run either an individual test method or all tests in the class.
* **IntelliJ**: 
    * Right-click on the class. Select Run <name-of-test-class>.
* **Eclipse**: 
    * Right-click on the class. Select Run As / Junit Test.

With Eclipse and IntelliJ, you can run all tests in all classes in a folder by right-clicking on the folder and running tests.


---
**Organizing Imports**

Usually when copy code examples into the IDE's, compilation errors result do to missing `import` statements at the top of the class / interface.  All IDEs have various ways to automatically set these imports.

(Typically, in any IDE, when you are typing the name of a Java class, clicking Ctrl-Space invokes automatic completion, producing a list of fully-qualified classnames from which to select.)

* **VS Code**: 
    * Type Alt-Shift-O.
* **IntelliJ**: 
    * Type Ctrl-Alt-O.
* **Eclipse**: 
    * Type Ctrl-Shift-O.

---
**Saving your work**

In any IDE, or any editor at all, save by clicking Ctrl-S.

---
**Environment Variables**

Anytime you change an environment variable on your system, you will need to restart your IDE.  This is because the existing process space of your IDE is unaware of the change.  Restarting your IDE establishes a new space with the current set of variables.





static imports