import cv2 as cv
import numpy as np

import math

def get_board(file):

    img = cv.imread(file)

    """ #percent by which the image is resizedre
    scale_percent = 250

    #calculate the 50 percent of original dimensions
    width = int(img.shape[1] * scale_percent / 100)
    height = int(img.shape[0] * scale_percent / 100)

    # dsize
    dsize = (width, height)

    # resize image
    img = cv.resize(img, dsize)
 """
    gris = cv.cvtColor(img,cv.COLOR_BGR2GRAY)
    
    # Aplicar suavizado Gaussiano
    gauss = cv.GaussianBlur(gris, (5,5), 0)
    
    #cv.imshow("suavizado", gauss)
    
    # Detectamos los bordes con Canny
    canny = cv.Canny(gauss, 50, 150)

    """ output = img.copy()
    circles = cv.HoughCircles(gauss, cv.HOUGH_GRADIENT, 1.2, 100)
    # ensure at least some circles were found
    if circles is not None:
        # convert the (x, y) coordinates and radius of the circles to integers
        circles = np.round(circles[0, :]).astype("int")
        # loop over the (x, y) coordinates and radius of the circles
        for (x, y, r) in circles:
            # draw the circle in the output image, then draw a rectangle
            # corresponding to the center of the circle
            cv.circle(output, (x, y), r, (0, 255, 0), 4)
            cv.rectangle(output, (x - 5, y - 5), (x + 5, y + 5), (0, 128, 255), -1)
    
    cv.namedWindow('output',cv.WINDOW_NORMAL)
    cv.namedWindow('output',600)
    cv.imshow("output", output)
     """
    #cv.imshow("canny", canny)
    
    # Buscamos los contornos
    (contornos,_) = cv.findContours(canny.copy(), cv.RETR_EXTERNAL, cv.CHAIN_APPROX_NONE)

    cont = []

    for contorno in contornos:
        if cv.contourArea(contorno) > 1500:
            cont.append(contorno)

    contornos = cont

    board = []
    found_map = False
    first_pixel = (0,0)
    last_pixel = (0,0)
    size = img.shape
    for i in range(size[0]):
        if i > 0 and len(board[i-1]) > 0 and max(board[i-1]) == 0:
            board.pop()
            print('Entro')
            break
        board.append([])
        for j in range(size[1]):
            is_in = [cv.pointPolygonTest(contorno, (j, i), False) for contorno in contornos]
            is_in = max(is_in)
            if not found_map:
                if is_in == 1.0:
                    found_map = True
                    first_pixel = (i, j - 4)
                    board[i].append(3)
            else:
                if i == first_pixel[0]:
                    if is_in != 1.0:
                        last_pixel = (i, j + 4)
                        break
                if j < first_pixel[1] or j > last_pixel[1]:
                    pass
                board[i].append(3 if is_in == 1.0 else 0)

    # print_board(board)

    # Reduce width
    rows = len(board)
    cont_wall = 0
    cont_free = 0
    _board = []
    for i in range(rows):
        cols = len(board[i])
        _board.append([])
        for j in range(cols):
            if board[i][j] == 3:
                if cont_free != 0 or j == cols - 1:
                    _board[i].extend([0 for k in range(int(math.ceil(cont_free / 32)))])
                    cont_free = 0
                else:
                    cont_wall += 1
                    cont_free = 0
            else:
                if cont_wall != 0:
                    _board[i].extend([3 for k in range(int(math.ceil(cont_wall / 32)))])
                    cont_wall = 0
                else:
                    cont_wall = 0
                    cont_free += 1

    # Reduce width
    """ rows = len(board)
    cont_wall = 0
    cont_free = 0
    _board = []
    for i in range(rows):
        cols = len(board[i])
        _board.append([])
        for j in range(cols):
            if board[i][j] == 3:
                if cont_free != 0:
                    _board[i].extend([0 for k in range(int(math.ceil(cont_free / 31)))])
                    cont_free = 0
                else:
                    cont_wall += 1
                    cont_free = 0
            else:
                if cont_wall != 0:
                    _board[i].extend([3 for k in range(int(math.ceil(cont_wall / 31)))])
                    cont_wall = 0
                else:
                    cont_wall = 0
                    cont_free += 1 """
    
    print(cont_wall, cont_free)
    print_board(_board)


    """ board = []

    _i = 0
    size = img.shape
    for i in range(size[0]):
        board.append([])
        for j in range(size[1]):
            is_in = [cv.pointPolygonTest(contorno, (j, i), False) for contorno in contornos]
            board[i].append(1 if max(is_in) == 1.0 else 0)
        _i += 1

    _board = []
    for i in range(len(board)):
        board.append([])
        for j in range(len(board[0])):
            is_in = [cv.pointPolygonTest(contorno, (j, i), False) for contorno in contornos]
            _board[i].append(1 if max(is_in) == 1.0 else 0)
        _i += 1

    __board = []
    for i in range(len(_board)):
        board.append([])
        for j in range(len(_board[0])):
            is_in = [cv.pointPolygonTest(contorno, (j, i), False) for contorno in contornos]
            __board[i].append(1 if max(is_in) == 1.0 else 0)
        _i += 1
    
    

    #return board
    
    # Mostramos el numero de monedas por consola
    print("He encontrado {} objetos".format(len(contornos))) """
    
    cv.drawContours(img,contornos,-1,(255,255,255), 2)
    cv.namedWindow('contornos',cv.WINDOW_NORMAL)
    cv.namedWindow('contornos',600)
    cv.imshow("contornos", img)
    
    cv.waitKey(0)

# get_board('Fondo01.png')
def print_board(board):
    print('\n')
    for _list in board:
        print([value for value in _list])
    print('\n')