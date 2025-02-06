def bubbleSort_recursivo(array):
    count = 0
    #print("array is currently",array)
    for idx in range(len(array)-1):
        if array[idx] > array[idx + 1]:
            array[idx],array[idx + 1] = array[idx + 1],array[idx]
            count += 1
            #print("swaped and count is currently",count)
            #print("array is currently",array)
    if count == 0:
        #print("Count is zero")
        #print("array is currently",array)
        return array
    else:
        #print("Count is not zero")
        return bubbleSort_recursivo(array)
