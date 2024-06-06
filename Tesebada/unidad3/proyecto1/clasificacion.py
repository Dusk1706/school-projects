import pandas as pd
import matplotlib.pyplot as plt
from sklearn.tree import DecisionTreeClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
from sklearn import tree
from sklearn.preprocessing import LabelEncoder
from scipy.io import arff

# Cargar los datos
data = arff.loadarff('creditosPython.arff')
df = pd.DataFrame(data[0])

# Preprocesamiento de los datos
# Convertir las columnas de categorías a números
le = LabelEncoder()
for column in df.columns:
    if df[column].dtype == type(object):
        df[column] = le.fit_transform(df[column])

# Dividir los datos en conjuntos de entrenamiento y prueba
X = df.drop(['nivelRenta'], axis = 1)
y = df['nivelRenta']
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.2, random_state = 13)

# Crear y entrenar el modelo
model = DecisionTreeClassifier(random_state = 0, max_depth = 3)
model.fit(X_train, y_train)

# Crear un gráfico del árbol de decisión
fig = plt.figure(figsize = (24, 16))
tree.plot_tree(model, 
               feature_names = df.columns[:-1], 
               class_names = ['ALTO', 'MEDIO', 'BAJO'], 
               filled = True)
# Hacer interactivo el gráfico
plt.savefig('tree.png',facecolor='white',bbox_inches="tight")