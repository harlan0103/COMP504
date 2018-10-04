Composite Design Decision
The idea for composite shape design is similar to composite strategies
Create the shape based on the type param, then add to a APaintObject array
The APaintObject array contains all selected shape
Then creates the composite shape with the APaintObject array as param
Therefore, we could call compositeShpe.getChildren to get the APaintObject array
Then could call internal method to get information we want to use.