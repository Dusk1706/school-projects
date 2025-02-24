import cv2
import numpy as np
from PIL import Image, ImageTk


class ProcesadorFrame:
    @staticmethod
    def preprocesar_frame(frame):
        gray = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
        thresh = cv2.adaptiveThreshold(
            gray, 255, cv2.ADAPTIVE_THRESH_GAUSSIAN_C, cv2.THRESH_BINARY_INV, 11, 2
        )
        contours, _ = cv2.findContours(thresh, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)
        if contours:
            max_contour = max(contours, key=cv2.contourArea)
            x, y, w, h = cv2.boundingRect(max_contour)
            padding = 10
            x, y = max(0, x - padding), max(0, y - padding)
            w, h = w + 2 * padding, h + 2 * padding
            digit_roi = thresh[y:y+h, x:x+w]

            aspect_ratio = w / h
            if aspect_ratio > 1:
                new_w, new_h = 20, int(20 / aspect_ratio)
            else:
                new_h, new_w = 20, int(20 * aspect_ratio)

            resized = cv2.resize(digit_roi, (new_w, new_h), interpolation=cv2.INTER_AREA)
            canvas = np.zeros((28, 28), dtype=np.uint8)
            start_x, start_y = (28 - new_w) // 2, (28 - new_h) // 2
            canvas[start_y:start_y+new_h, start_x:start_x+new_w] = resized
            normalized = canvas / 255.0
            input_data = normalized.reshape(1, 28, 28, 1)
            processed_pil = Image.fromarray((canvas * 255).astype(np.uint8))
            processed_photo = ImageTk.PhotoImage(processed_pil.resize((200, 200), Image.LANCZOS))
            return input_data, processed_photo
        return None, None
