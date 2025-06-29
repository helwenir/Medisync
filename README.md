
# Medisync Project

**Medisync** is an application project for managing first aid workers. The goal was to allow management of first responders as an **administrator** (manage their skills, view assignments and availability, see all existing medical coverage deployments, create new ones if needed, add/edit/delete rescuers), and also allow management as a **first responder** (manage availability, manage skills, view assignments).  
This is a **university project** only; there is nothing concrete or official, and it was designed with the **Paris 2030 Olympics** in mind.

## USING MEDISYNC

Note: Some features are currently missing, and the application is not fully functional.

To use Medisync:

1. Download the following dependencies:
   - `mysql-connector-j-9.3.0` and its associated binary (for Windows users)
   - `org.abego-treelayout.core-1.0.3`
   - `fxgraph-0.0.3`
   - `javafx`
   - `jfxtras-all-10.0-r1`

2. Create the following folder structure:
   - At the same level as the `class` and `images` folders, create a folder named `lib`
   - Inside `lib`, create the following directories:
     - `abego-treelayout`
     - `fxgraph`
     - `javafx`
     - `jfxtras`
   - Place the corresponding `.jar` files in each folder
   - Then, place the `mysql` jar and its associated binary directly inside the `/lib` directory

## Compilation and Execution Commands

From your terminal, in the `ws` directory:

**Compilation command:**
```sh
javac -d ..\class -sourcepath ..\src --module-path ..\lib\javafx --add-modules javafx.controls -cp "..\lib\jfxtras\*;..\lib\fxgraph\fxgraph-0.0.3.jar;..\lib\abego-treelayout\org.abego.treelayout.core-1.0.3.jar" (Get-ChildItem -Recurse ..\src\*.java)
```

**Execution command:**
```sh
java --module-path ..\lib\javafx\ --add-modules javafx.controls -cp "..\class;..\lib\mysql-connector-j-9.3.0.jar;..\lib\jfxtras\jfxtras-all-10.0-r1.jar;..\lib\fxgraph\*;..\lib\abego-treelayout" application.App
```

## Database

This application requires a database.

- The database structure is provided in the `/base de donée` folder
- Create it using the **creation script**
- Populate it using the **data population script**
- Then, create a user named `admin` with the password `admin_hash` and grant them access to the entire database

Once all this is done, you can use the application!
