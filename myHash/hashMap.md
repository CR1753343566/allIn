# HashMap

- [HashMap数据结构](#hashmapstruct)
- [HashMap工作原理](#hashmapwork)
- [HashMap结合源码](#hashmapsource)

## <span id="hashmapstruct">HashMap</span>

HashMap是哈希表结构，采用数组+链表的方式实现，结合数组和链表的优点，当链表的长度超过8时，链表转换为红黑树

## <span id="hashmapwork">HashMap工作原理</span>

HashMap底层是hash数组和单向链表实现，数组中的每个元素都是链表，由Node内部类(Node内部类实现了Map.Entry接口)实现,HashMap使用put&get方法进行存储和获取

### 存储

HashMap 存储对象时，将K/V键值对传给put()方法

1. 调用hash(K)方法计算K的hash值，结合数组长度，定位到数组下标
    
        static final int hash(Object key) {
            int h;
            return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        }
        
    为什么计算hash值时  `(h = key.hashCode()) ^ (h >>> 16)` ([保证散列性](#>>>16))      

2. 调整数组大小(当容器中元素的个数大于容量*装载因子时，容器将扩容为当前容量的2倍)
    
    HashMap的初始容量为16，装载因子为0.75，也就是说，在未指定初始容量大小及装载因子的情况下，当添加的元素个数大与12，容量将变为32
    
3. 添加到map
    
    - 如果K的hash值在HashMap中不存在，则执行插入，若存在，则发生碰撞
    - 如果K的hash值在HashMap中存在，且二者的equals返回true，则更新键值对
    - 如果K的hash值在HashMap中存在，且二者的equals返回false，则[插入](#insert)链表尾部
    
4. 如果链表长度超过阀值( TREEIFY THRESHOLD==8)，就把链表转成红黑树，链表长度低于6，就把红黑树转回链表;


#### hash(k) 如何保证散列性


***Jdk 1.7 实现数组下标定位的方法***

    static int indexFor(int h, int length) {
        return h & (length-1);
    }
HashTable 的下标都是用hashCode%length 计算出来的，HashMap使用位运算

假设HashMap的length为16(HashMap初始值确实定义为16),那么当hashCode值为7时

    7&(16-1)
    二进制运算
    0111
    &
    1111
     111

在进行二进制运算后结果为 111 转换为10进制为7 ，与 7%16的结果一致

HashMap中保证了map的长度永远是2的n次方，2的n次方-1转换为2进制，每一位都是1，当使用hashCode值进行计算时，如果hashCode二进制位数小于length-1的二进制位数，那么计算结果就是hashCode的值

如果hashCode的二进制位数大于length-1的二进制位数，那么多出来的位数将与0相与，结果为0，得到的结果也就是18%16的值

    18&(16-1)
    二进制运算
    10010
    01111
     0010    
    
不使用 hashCode%length 的原因在于位运算计算速度快于模运算

***Jdk 1.8 实现数组下标定位的方法***
    
    (h = key.hashCode()) ^ (h >>> 16)
    
h>>>16取到h的高16位 >>> 无符号右移

Jdk 1.8根据hashCode值获取下标的方法与Jdk1.7的不同在于 绝大多数情况下length小于2的16次方

1.7 的结果始终是h的低16位与length-1进行与运算

1.8 先得到hashCode值，让hashCode值先与自己的高16位进行异或，使得到的值更加散列，在进行数组下标定位时更加随机

使用& 或 | 都会时结果偏向0或者1，使用^得到的结果更均匀




 
 
#### <span id="insert">插入链表</span>

1. Jdk1.7头插法

2. Jdk1.8尾插法

#### 数组扩容

创建一个新的数组，其容量为旧数组的两倍，并重新计算旧数组中结点的存储位置。结点在新数组中的位置只有两种，原下标位置或原下标+旧数组的大小





