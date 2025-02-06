#include iostream

class Dummy
{
private
    int myByte;

    int myByteA, myByteB, myByteC;

public
    void setMyByte(const int &newValue)
    {
        myByte = newValue;
    }
    int getMyByte()
    {
        return myByte;
    }
};

 Cria nova váriavel dummy, que tem o endereço de memória da myDummy
 32bits 4 bytes
 64bits 8 bytes
void setOtherDummyPtr(Dummy dummy2)
{
    dummy2-setMyByte(5);
    Dummy copyOfStuffThatWasInDummy2 = dummy2;
    copyOfStuffThatWasInDummy2.setMyByte(3);
}

void setOtherDummy(Dummy &dummy)
{
    dummy.setMyByte(5);
}

int main()
{
    Dummy myDummy;
    myDummy.setMyByte(9);


    
    Dummy ptrToMyDummy = &myDummy;

    stdcout  ptrToMyDummy  stdendl;
    ptrToMyDummy += 1;
    stdcout  ptrToMyDummy  stdendl;
    ptrToMyDummy -= 1;
    stdcout  ptrToMyDummy  stdendl;
    stdcout  ptrToMyDummy-getMyByte()  stdendl;
    setOtherDummyPtr(&myDummy);
    stdcout  myDummy.getMyByte()  stdendl;
    stdcout  &myDummy  stdendl;
}