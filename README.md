#############################################
SUPERMARKET SUPPORT SYSTEM IN JAVA
#############################################


The sources file are categorised placed in three different folder: model, view and controller. This simplify the program layout and
reduce the complexity of the program. K.I.S.S (Keep it Simple, Stupid)

A view usually consist of one controller. Eg. A Login_View where users are prompt with Login GUI to enter their credentials, the listeners 
and various logic lies in the Login_Controller.java. This applies to other controllers, and they all works together by connecting to the Main_Controller.java.
This made the program very modules, and also very low coupling and high cohesive at the same time. If there arise the need to add a new functions to the
program. A controller and a view is all it takes to plug it into the program.

The files are also named with consistent naming conventions. Every file are end with either _model or _view suffix so we know what are the class doing. 
Every functions and classes are also named meaningfully, Eg. initializeStaffManageView() in Main_controller.java (Line 234). We know that the function is 
initializing the view for the Staff Management GUI at first glance.

DbC code are written on most of the functions, though they are commented just so the program will run. Most of them are ensuring they all return a valid value
instead of null value, and some requires the parameter to meet certain requirement. Eg. Function public Boolean AddItem(...,..,..., int quantity) in 
model ItemSQL.java (Line 58), a pre-condition is written to ensure the quantity will not be a negative value. 

Lastly, most of the specific functions that connect to the database are documented and commented properly so people will know how to use that functions. 
Eg. All functions in model EmployeeSQL.java. It is a class where it contains all the hibernate initializing functions to connect to the database . All the 
functions parameters and return value are all well explained, people will know what to expect when trying to use the functions.

Invoice generator - Decorator pattern.
1. Decorator pattern classes are under controller package, names start with DEC.
2. Invoice objects are created using the decorator in Pay_Controller.java. (From line 193 to 224)
3. With this pattern, invoice can be generated or printed in many different ways depending on how you call
   the decorator methods.
4. Can be extended with more kind of decorators.