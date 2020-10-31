#   IME 2022 - LabProg II
#
#       Script just testing ploting on python
#       This is not working propertly :p

import seaborn as sns
df = sns.load_dataset('iris')
 
# Usual boxplot
ax = sns.boxplot(x='species', y='sepal_length', data=df)
 
# Add jitter with the swarmplot function.
ax = sns.swarmplot(x='species', y='sepal_length', data=df, color="grey")
