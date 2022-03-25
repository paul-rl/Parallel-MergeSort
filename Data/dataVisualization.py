import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

# Set up pandas display to see summary statistics
pd.options.display.max_rows = 30
pd.options.display.max_columns = 30

# Read from file and clean up data frame
df = pd.read_csv('Data/data.csv')
df = df.loc[df['Number of Elements'] != 0]

# Convert Columns of data fram to numpy array
x = df['Number of Elements'].to_numpy()
yPartiallyParallel = df['Partially Parallel Merge Sort Time (in ns)'].to_numpy()
yStandard = df['Standard Merge Sort Time (in ns)'].to_numpy()
yParallel = df['Parallel Merge Sort Time (in ns)'].to_numpy()
yJava = df['Java Parallel Merge Sort Time (in ns)'].to_numpy()

# Plot Standard Merge Sort
aS, bS = np.polyfit(x, yStandard, 1)
plt.plot(x, aS*x + bS, color='purple', label="Merge Sort")

# Plot Parallel Merge Sort
aP, bP = np.polyfit(x, yParallel, 1)
plt.plot(x, aP*x + bP, color='green', label="Parallel Merge Sort")

# Plot Partially Parallel Merge Sort
aPP, bPP = np.polyfit(x, yPartiallyParallel, 1)
plt.plot(x, aPP*x + bPP, color='red', label="Partially Parallel Merge Sort")

# Plot Arrays.ParallelSort()
aJ, bJ = np.polyfit(x, yJava, 1)
plt.plot(x, aJ * x + bJ, color='orange', label='Arrays.ParallelSort()')

# Label plot and save it
plt.xlabel("Number of Elements, N")
plt.ylabel("Time to sort (in ns)")
plt.savefig("Data/results.png")

# Show plot and summary statistics
plt.legend()
plt.show()
print(df.describe())