import numpy as np
from keras.models import Sequential
from keras.layers.core import Dense
import cv2 as cv

img1 = cv.imread('test1.png')
img2 = cv.imread('MSPACII2--Ms PacMan II Orca bootleg set 2_Aug14 0_07_17.png')
img3 = cv.imread('test2.png')
img4 = cv.imread('test3.png')
img5 = cv.imread('test4.png')
img6 = cv.imread('test5.png')

img1 = cv.cvtColor(img1,cv.COLOR_BGR2RGB)
img2 = cv.cvtColor(img2,cv.COLOR_BGR2RGB)
img3 = cv.cvtColor(img3,cv.COLOR_BGR2RGB)
img4 = cv.cvtColor(img4,cv.COLOR_BGR2RGB)
img5 = cv.cvtColor(img5,cv.COLOR_BGR2RGB)
img6 = cv.cvtColor(img6,cv.COLOR_BGR2RGB)

img1 = [(x[0]*x[0]+x[1]*x[1])**0.5 for x in img1]
img2 = [(x[0]*x[0]+x[1]*x[1])**0.5 for x in img2]
img3 = [(x[0]*x[0]+x[1]*x[1])**0.5 for x in img3]
img4 = [(x[0]*x[0]+x[1]*x[1])**0.5 for x in img4]
img5 = [(x[0]*x[0]+x[1]*x[1])**0.5 for x in img5]
img6 = [(x[0]*x[0]+x[1]*x[1])**0.5 for x in img6]

print(' \n\nhere\n\n')
print(img1)

# [img1,img2,img3,img4,img5,img6]

# cargamos las 4 combinaciones de las compuertas XOR2e
training_data = np.array([[img1],[img2],[img3],[img4],[img5],[img6]])
 
# y estos son los resultados que se obtienen, en el mismo orden
target_data = np.array([[0],[1],[1],[0],[0],[0]])

""" # cargamos las 4 combinaciones de las compuertas XOR2e
training_data = np.array([[0,0],[0,1],[1,0],[1,1]], "float32")
 
# y estos son los resultados que se obtienen, en el mismo orden
target_data = np.array([[0],[1],[1],[0]], "float32") """
 
model = Sequential()
model.add(Dense(300, input_dim=10, activation='relu'))
model.add(Dense(1, activation='sigmoid'))
 
model.compile(loss='mean_squared_error',
              optimizer='adam',
              metrics=['binary_accuracy'])
 
model.fit(training_data, target_data, epochs=1000)
 
# evaluamos el modelo
scores = model.evaluate(training_data, target_data)