# Concurrent Implementations of Merge Sort
This project aims to analyze different concurrent implementations of the Merge Sort algorithm and see how they compare to the standard Merge Sort algorithm. To do so, it utilizes java to implement these concurrent algorithms and python to analyze and visualize the data. As a result, it is necessary to first compilee and run thejava files so as to generate the CSV file that is then utilized to analyze the data using python.

## Dependencies
In order to appropriately compile the python script needed to visualize data, the widely-popular libraries pandas, numpy, and matplotlib are necessary. There is plenty of documentation on how to acquire these libraries, but if necessary use this [link.](http://www.google.com)
## Compilation of Algorithms
In order to compile the java code that will run different algorithms and generate a CSV file with all of the empirical data acquired, all that is needed to do is utilize the following commands:
```
javac MergeSortImplementation/MergeSortTester.java
java MergeSortImplementation/MergeSortTester [number_of_trials] [maximum_array_size]
```
If no arguments are entered for number of trials and maximum array size, the program will conduct a total of 5000 trials, each with a maximum array size of 1000000 elements.

## Compilation of Data Analysis
To compile the python script that generates the summary statistics and plot corresponding to the empirical data, it is first necessary to run the java commands above so that a CSV file is generated. Then, all that must be one is run the following commands:
```
python Data/dataVisualization.py
```
## Expected Behavior
Upon running the java commands, a CSV file will be generated which contains information regarding the execution time of different algorithms.
Upon running the python command, this CSV file will be utilize to calculate summary statistics about the data and saving a plot showcasing the data.
