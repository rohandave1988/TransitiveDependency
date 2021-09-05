Problem:-

The exercise is based on dependency analysis, a useful process for evaluating the complexity and inter-connectedness of source code. For simplicity, simple tokens are used instead of class names.

The Exercise Write some code that determines the full set of transitive dependencies for a group of items. The code takes as input a set of lines where the first token is the name of an item. The remaining tokens are the names of things that this first item depends on. Given the following input, we know that A directly depends on B and C, B depends on C and E, and so on.
A B C
B C E
C G
D A F
E F
F H

The program should use this data to calculate the full set of dependencies.
The output of the program for the above input should look like:
A B C E F G H
B C E F G H
C G
D A B C E F G H
E F H
F H

Solution:-

To solve the problem of transitive dependency i have used graph consisting of nodes that have children or dependencies. With this approach we can prevent adding a child if that can create a circular dependency.

Also for quick access to the nodes, have used the hashmap so that it retrieves the results faster.

Created three classes in the code namely :- 

1.) Node:- The character or token and the dependencies are stored in the node
           which is a graph data structure, which signifies the character or token                               		   as name (String) and Hashset which contains the dependencies of the  	
           current node. 
        
2.) DependencyCalculator:- The class contains logic for finding transitive  
                           dependency.

3.) CyclicDependencyException:- Custom exception class that has been created for  
                                catching the use case in which there can be cyclic 
                                dependency.
                                
e.g.: 

A B

B A

This scenario can create a cyclic dependency.
