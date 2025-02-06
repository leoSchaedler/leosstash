#include "FreeRTOS.h"
#include "task.h"
#include "basic_io.h"

// handler creation
xTaskHandle temperatureHandler;
xTaskHandle rateHandler;
xTaskHandle saturationHandler;


void myTaskTemperature(void* pvParameters) {

	volatile long temperature_percentage;  // var to handle vital values regarding body temperature
	for (;; )
	{
		// between 34% & 41%
		temperature_percentage = random_number(34, 41);   //  generate vital values between 34% & 41%
		vPrintStringAndNumber(pvParameters, temperature_percentage);// show vital values and text on screen
		if (temperature_percentage < 35) { // in case values are below 35%
			vPrintString("HIPOTERMIA\n");    // show hypothermia warning
		}
		else if (temperature_percentage > 37.5) {    // in case values are above 37.5% 
			vPrintString("FEBRE\n");  // show fever warning 
		}
		vTaskDelay(1000);   // add delay

	}

	// When a task is not needed anymore, it needs to be explicitly disposed of.
	vTaskDelete(NULL);
}

void myTaskRates(void* pvParameters) {

	volatile long rates_percentage;  // var to handle vital values regarding heart rates
	for (;; )
	{
		// between 20% & 140%
		rates_percentage = random_number(20, 140);   //  generate vital values between 20% & 140%
		vPrintStringAndNumber(pvParameters, rates_percentage);  // show vital values and text on screen
		if (rates_percentage < 50) {  // if values are below 50% 
			vPrintString("BATIMENTOS CARDIACOS BAIXOS\n");  // show low heartbeat warning
		}
		else if (rates_percentage > 90) { // if values are above 90% 
			vPrintString("BATIMENTOS CARDIACOS ALTOS\n");// show high heartbeat warning
		}
		vTaskDelay(1000);

	}

	// When a task is not needed anymore, it needs to be explicitly disposed of.
	vTaskDelete(NULL);

}

void myTaskSaturation(void* pvParameters) {

	volatile long saturation_percentage;  // var to handle vital values regarding oxygen saturation
	for (;; )
	{
		// between 80% & 100%
		saturation_percentage = random_number(80, 100);  //  generate vital values between 80% & 100%
		vPrintStringAndNumber(pvParameters, saturation_percentage); // show vital values and text on screen
		if (saturation_percentage < 90) {   //  in case vital values are below 90%
			vPrintString("OXIGENACAO BAIXA\n");  // show low oxygenation warning
		}
		vTaskDelay(1000); // add delay

	}

	// When a task is not needed anymore, it needs to be explicitly disposed of.
	vTaskDelete(NULL);
}

int random_number(int min_num, int max_num)   // random function that generates random numbers between a minimum and maximum given values
{
	int result = 0, low_num = 0, hi_num = 0;

	if (min_num < max_num)
	{
		low_num = min_num;
		hi_num = max_num + 1;
	}
	else {
		low_num = max_num + 1;
		hi_num = min_num;
	}

	srand(time(NULL));
	result = (rand() % (hi_num - low_num)) + low_num;
	return result;
}


int main_(void)
{
	// message to be used as parameter
	static const char* msg1 = "Temperatura:";
	static const char* msg2 = "Batimentos:";
	static const char* msg3 = "Saturacao:";

	// tasks creation
	xTaskCreate(myTaskTemperature, "Task 1", 1000, (void*)msg1, 1, &temperatureHandler);
	xTaskCreate(myTaskRates, "Task 2", 1000, (void*)msg2, 1, &rateHandler);
	xTaskCreate(myTaskSaturation, "Task 3", 1000, (void*)msg3, 1, &saturationHandler);

	// start tasks scheduler
	vTaskStartScheduler();

	for (;; );
	return 0;
}