# Learn2day

## Required Libraries
  - JavaFX SDK (Can be downloaded here: https://gluonhq.com/products/javafx/)
  - MySQL Connector J (Can be downloaded here: https://dev.mysql.com/downloads/connector/j/)
  - JUnit4 (Which should be possible to import directly from the IDE)

Make sure these libraries are imported into your project.
 
 ## Configure VM-options
   Before you can run the application you must also configure the VM-options in Run/Debug configurations,
   The VM options should include: --module-path "PATH\TO\JAVAFX\LIB\javafx-sdk-11.0.2\lib" --add-modules=javafx.controls,javafx.fxml.
   
## Import Database with MySQL Workbench

  To run the application you also need to setup the database which can be done by 
  going into MySQL Workbench -> Go into your localhost ->
  Go into the administration tab -> Click on Data Import/Restore -> 
  Choose "Import from Self-contained file" option -> then find the path to the learn2day.sql file inside the project folder ->
  Make sure it gets imported to a new schema called "Learn2day" -> Press Start Import
  
  
 Now if everything is imported correctly and VM-options are configured you should be able to run the program.
 
