![Image](docs/Logo.png)

## About OMNI

###### This framework is developed by [Testonics](https://www.testonics.in) 
###### Visit [Testonics](https://www.testonics.in) for more details.

***OMNI*** a single stop test automation framework to test mobile (ios & android) or web application and API testing. Tech stack of the framework is:

## Features

- PDF comparison (Text, Font Name, Font Style, Font Size validation for Single PDF or all pdf in a folder)
- Dead links identification
- Keep the session alive by mouse movement
- API/Mobile/UI/Desktop Applications Testing

## Tech Stack

- Mobile Testing: Appium
- UI Testing: Selenium & Playwright
- API Testing: Rest Assured
- Langauge: Java
- Framework: TestNG/Cucumber
- Report: Extent
- CI/CD: Github Actions

## Installation Steps
```markdown
## Install 1/2: Add this to pom.xml:

<dependency>
  <groupId>in.testonics</groupId>
  <artifactId>omni</artifactId>
  <version>1.0.2</version>
</dependency>

## Install 2/2: Run via command line
mvn install
```

## [Released Package](https://github.com/testonics/Omni/packages/)

## Contact Us

Send your queries to support@testonics.in to know more.

## WinAppDriver Setup Instructions

Windows 10: I use Windows 10 x64. 
You might be running a 32-bit version of Windows, but the only difference might be the location of some components.
**Test Application:** For our tests, we will be using a default "Notepad" app available on any Windows computer.
**Location:** C:\Windows\System32\notepad.exe
**Windows Application Driver:** The main component that we need for Windows Automation (Note: Enable developer mode on Windows machine)
**Windows 10 SDK:** To be able to inspect elements inside the application. We will use the “inspect.exe” tool for that purpose
**IntelliJ IDEA:** IDE that we will use for writing our tests
**Java JDK:** Since we are writing our tests in Java we would need this component for our tests to function.

_Note: Java JDK could be installed automatically by using the IntelliJ IDEA._

## Appendix

###### For more details see [Basic writing and formatting syntax](https://docs.github.com/en/github/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax).
