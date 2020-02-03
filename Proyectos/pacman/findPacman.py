import cv2 as cv
import numpy as np

print(cv.__version__)

""" img = cv.imread('Fondo01.png',0)
img = cv.medianBlur(img,5)
cimg = cv.cv2tColor(img,cv.COLOR_GRAY2BGR)

circles = cv.HoughCircles(img,cv.HOUGH_GRADIENT,1,20,
                            param1=50,param2=30,minRadius=0,maxRadius=0)

# find Harris corners
gray = np.float32(cimg)
dst = cv.cornerHarris(gray,2,3,0.04)
dst = cv.dilate(dst,None)
ret, dst = cv.threshold(dst,0.01*dst.max(),255,0)
dst = np.uint8(dst)

circles = np.uint16(np.around(circles))
for i in circles[0,:]:
    # draw the outer circle
    # cv.circle(cimg,(i[0],i[1]),i[2],(0,255,0),2)
    # draw the center of the circle
    cv.circle(cimg,(i[0],i[1]),2,(0,0,255),3)

cv.imshow('detected circles',cimg)
cv.waitKey(0)
cv.destroyAllWindows() """

""" img = cv.imread('Fondo01.png')
gray = cv.cv2tColor(img,cv.COLOR_BGR2GRAY)
# find Harris corners
gray = np.float32(gray)
dst = cv.cornerHarris(gray,2,3,0.04)
dst = cv.dilate(dst,None)
ret, dst = cv.threshold(dst,0.01*dst.max(),255,0)
dst = np.uint8(dst)
# find centroids
ret, labels, stats, centroids = cv.connectedComponentsWithStats(dst)
# define the criteria to stop and refine the corners
criteria = (cv.TERM_CRITERIA_EPS + cv.TERM_CRITERIA_MAX_ITER, 100, 0.001)
corners = cv.cornerSubPix(gray,np.float32(centroids),(5,5),(-1,-1),criteria)
# Now draw them
res = np.hstack((centroids,corners))
res = np.int0(res)
img[res[:,1],res[:,0]]=[0,0,255]
img[res[:,3],res[:,2]] = [0,255,0]
cv.imshow('detected circles',gray)
cv.waitKey(0)
cv.destroyAllWindows()
# cv.imwrite('subpixel5.png',img) """

""" img = cv.imread('Fondo01.png')
gray = cv.cv2tColor(img,cv.COLOR_BGR2GRAY)
gray = np.float32(gray)
dst = cv.cornerHarris(gray,2,3,0.04)
#result is dilated for marking the corners, not important
dst = cv.dilate(dst,None)
# Threshold for an optimal value, it may vary depending on the image.
img[dst>0.01*dst.max()]=[0,0,255]
cv.imshow('dst',img)
if cv.waitKey(0) & 0xff == 27:
    cv.destroyAllWindows() """

img = cv.imread('Fondo01.png')
gris = cv.cvtColor(img,cv.COLOR_BGR2GRAY)
 
# Aplicar suavizado Gaussiano
gauss = cv.GaussianBlur(gris, (5,5), 0)
 
cv.imshow("suavizado", gauss)
 
# Detectamos los bordes con Canny
canny = cv.Canny(gauss, 50, 150)
 
cv.imshow("canny", canny)
 
# Buscamos los contornos
(contornos,_) = cv.findContours(canny.copy(), cv.RETR_EXTERNAL, cv.CHAIN_APPROX_SIMPLE)


 
# Mostramos el numero de monedas por consola
print("He encontrado {} objetos".format(len(contornos)))
 
cv.drawContours(img,contornos,-1,(0,0,255), 2)
cv.imshow("contornos", img)
 
cv.waitKey(0)