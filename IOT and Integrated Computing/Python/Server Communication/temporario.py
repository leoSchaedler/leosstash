import numpy as np
import cv2

color_bgr=np.uint8([[[0,255,0]]])
color_hsv = cv2.cvtColor(color_bgr,cv2.COLOR_BGR2HSV)
print(color_hsv)