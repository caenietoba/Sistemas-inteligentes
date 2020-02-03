import cv2 as cv
import numpy as np

def get_board(file):

    img = cv.imread(file)
    gris = cv.cvtColor(img,cv.COLOR_BGR2GRAY)
    
    # Aplicar suavizado Gaussiano
    gauss = cv.GaussianBlur(gris, (5,5), 0)
    
    cv.imshow("suavizado", gauss)
    
    # Detectamos los bordes con Canny
    canny = cv.Canny(gauss, 50, 150)
    
    cv.imshow("canny", canny)
    
    # Buscamos los contornos
    (contornos,_) = cv.findContours(canny.copy(), cv.RETR_EXTERNAL, cv.CHAIN_APPROX_SIMPLE)

    cont = []

    for contorno in contornos:
        if cv.contourArea(contorno) > 1000:
            cont.append(contorno)

    contornos = cont

    board = []

    _i = 0
    size = img.shape
    for i in range(87, 940, 28):
        board.append([])
        for j in range(60, 935, 28):
            is_in = [cv.pointPolygonTest(contorno, (i, j), False) for contorno in contornos]
            board[_i].append(1 if max(is_in) == 1.0 else 0)
        _i += 1

    return board

    """ print('\n')
    for _list in board:
        print([value for value in _list])
    print('\n')
    
    # Mostramos el numero de monedas por consola
    print("He encontrado {} objetos".format(len(contornos)))
    
    cv.drawContours(img,contornos,-1,(0,0,255), 2)
    cv.imshow("contornos", img)
    
    cv.waitKey(0) """

# get_board('Fondo01.png')