This Program reads the input from file as matrix and store into data structure. The user prescribed arithmetic operations are performed on data and then written to the file.
This Program reads all the elements added by user through commands and add them to levels of linked list based of tossing the coin.
Several operations are conducted on data as the user enters various commands. Here are the functions written to deal with that scenarios:
•	add() Method : This method adds all the elements to levels of linked lists.
•	find() Method : This method finds the key value in the level of lists searching by levels replicating the search methodology as tree.
•	findLowLevel() Method : This method searches the key value in lower list which is called in find() method.
•	print() Method : This method is written for the use of printing the values in linked lists and showing the connections with each list.
Files and External data: 
	There are no external files used while executing the program.
Main Class:
	This program has a main file named as “SkipUI.java” which open ups a command interface to interact with user. This main method calls an internal class designed to perform all the operations.
Classes used in Program:
	LowList.java : This contains the implementation of linked list and its connections.it also contains all the operations like adding and finding in the linked list.
	There are more classes and interfaces named Coin, ArrayCoin, RandomCoin which is used to get the implementation of flipping the coin.

Data structures Used:
	The data given by the user is stored in linked list which is implemented in different way by adding features of top and down.
Assumptions :
	no string to store will be more than 15 characters
