int get_representation_length(int base, int value) {
    int chars = 0;
    while (value > base) {
        chars++;
        value /= base;
    }
    return chars + 1;
}

char get_character(int base, int value, int charIndex) {

    int chars = 0;


    while (charIndex > chars) {
        chars++;
        value /= base;
    }
    int modulus = value % base;
    if (modulus < 10) {
        char r = '0' + modulus;
        return r;
    }

    return 'A' + (modulus - 10);
}

int strlen(char *str) {
    for (int i = 0; i < INT_MAX; ++i) {
        if (str[i] == 0) {
            return i;
        }
    }
    return -1;
}

int val(char c) {
    if (c >= '0' && c <= '9') { return (int) c - '0'; }
    else {
        return (int) c - 'A' + 10;
    }
}

int toDeci(char *str, int base) {
    int len = strlen(str);
    int power = 1; // Initialize power of base
    int num = 0;  // Initialize result
    int i;

    // Decimal equivalent is str[len-1]*1 +
    // str[len-1]*base + str[len-1]*(base^2) + ...
    for (i = len - 1; i >= 0; i--) {
        // A digit in input number must be
        // less than number's base
        if (val(str[i]) >= base) {
            printf("Invalid Number");
            return -1;
        }

        num += val(str[i]) * power;
        power = power * base;
    }

    return num;
}