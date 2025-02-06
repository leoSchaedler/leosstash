//Leonardo Ribeiro Schaedler e Patrick Rizzo Correa Caetano
#include "FreeRTOS.h"
#include "task.h"
#include "basic_io.h"
#include "semphr.h"
#include "time.h"
#include <stdio.h>

time_t seconds;
char display[100];

void vTask1(void* pvParameters);
void vTask2(void* pvParameters);
void vTask3(void* pvParameters);

SemaphoreHandle_t xSemaphore = NULL;

void vTask1(void* pvParameters) 
{
	int taskID = (int)pvParameters;
	for (;; ) {
		if (xSemaphore != NULL) {
			if (xSemaphoreTake(xSemaphore, 10) == pdTRUE) {
				time(&seconds);
				struct tm * dateTimeNow = localtime(&seconds);
				int ano = dateTimeNow->tm_year + 1900;
				int nms  = dateTimeNow->tm_mon + 1;
				int dia = dateTimeNow->tm_mday;
				sprintf(display, "Task: %d Dia: %d Mes: %d Ano: %d \n", taskID,dia,nms,ano);
				vPrintString(display);
				xSemaphoreGive(xSemaphore);
			}
			else {
			}
		}
		vTaskDelay(1000);

	}
	vTaskDelete(NULL);
}

void vTask2(void* pvParameters) 
{
	int taskID = (int)pvParameters;
	for (;; ) {
		if (xSemaphore != NULL) {
			if (xSemaphoreTake(xSemaphore, 10) == pdTRUE) {
				time(&seconds);
				struct tm* dateTimeNow = localtime(&seconds);
				int sec = dateTimeNow->tm_sec;
				int min = dateTimeNow->tm_min;
				int hour = dateTimeNow->tm_hour;
				sprintf(display, "Task: %d Hora: %d Minutos: %d Segundos: %d\n", taskID,hour, min, sec);
				vPrintString(display);
				xSemaphoreGive(xSemaphore);
			}
			else {
			}
		}
		vTaskDelay(1000);
	}
	vTaskDelete(NULL);
}

void vTask3(void* pvParameters) {

	int taskID = (int)pvParameters;

	for (;;) {
		if (xSemaphore != NULL) {
			if (xSemaphoreTake(xSemaphore, (TickType_t)10) == pdTRUE) {
				float temp = ((float)rand() / (float)(RAND_MAX)) * 30;
				sprintf(display,"Task: %d - Curitiba %.2fºC\n", taskID, temp);
				vPrintString(display);
				xSemaphoreGive(xSemaphore);
			}
			else {
			}
		}
		vTaskDelay(1000);
	}
	vTaskDelete(NULL);
}

int main_(void)
{	

	int id1 = 1;
	int id2 = 2;
	int id3 = 3;

	xSemaphore = xSemaphoreCreateMutex();

	xTaskCreate(vTask1, "Task1", 1000, (void*)id1, 1, NULL);
	xTaskCreate(vTask2, "Task2", 1000, (void*)id2, 1, NULL);
	xTaskCreate(vTask3, "Task3", 1000, (void*)id3, 1, NULL);
	
	vTaskStartScheduler();
	
	for (;;);
}
