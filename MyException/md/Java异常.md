# Java异常

- [异常](#throwable)
- [异常类继承体系](#extend)
- [运行时异常](#runtimeexception)
- [检查型异常](#checkexception)


## <span id='throwable'>异常</span>

- 异常是class ，本身带有类型信息
- 异常可以在任何地方抛出
- 异常只需要在上层捕获，和方法调用分离

## <span id='extend'>异常继承体系</span>

所有异常的父类是`Throwable`类

Throwable 有两个大的子类 `Error`和`Exception`

`Exception`分为`RuntimeException`（运行时异常）和其他异常


- Error

    1. OutOfMemoryError 内存溢出
    2. NoClassDefFoundError 未找到类
    3. StackOverflowError 堆栈溢出

- Exception 

    - RuntimeException
        1. ClassCastException 类转化异常
        2. IndexOutOfBoundException 数组越界
        3. NullPointerException 空指针
        4. ArrayStoreException 数据存储异常
        5. IO操作的BufferOverflowException 异常 
    - 其他异常
        1. IOException IO异常
        2. SqlException Sql异常
        3. EncodeException 编码异常
        4. ServletException Servlet异常

#### <span id='runtimeexception'>运行时异常</span>

Java语言规定，运行时异常（`RuntimeException`）的子类，不需要捕获，还有Error的子类也不需要捕获(Error不是运行时异常)

#### <span id='checkexception'>检查型异常</span>

Java语言规定，检查型异常必须[捕获](#trycatch)或者[声明(抛出)](#throws)

#### <span id='trycatch'>捕获异常</span>

`try catch`

把可能发生异常的语句放在try中，使用catch捕获对应的Exception及其子类

    try{
        ··· (可能发生异常的语句)
    }catech(IOException e){
        ··· (处理异常)
    }

#### <span id='throws'>声明异常</span>
`throws`

对可能发生异常的代码块不捕获，但是通过throws声明，通过throws声明仍需上层捕获并处理(可继续抛出不处理)

main方法是异常最后处理的地方，如果main方法不对异常进行捕获，程序将直接退出

    //声明一个sql异常不处理
    public void function() thrwos SqlException{

    }


    //调用方进行处理
    public static void main(String [] args){
        try{
            function()
        }catch(Exception){
            ···  (处理)
        }
    }
