import java.util.HashMap;

/**
 * @author shihh
 * @version 1.0
 * @date 2020/6/15 11:04
 */
public class M_HashMap {
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main(String[] args) {
        HashMap hashMap=new HashMap(18,0.75f);
        int q=tableSizeFor(18);
        System.out.println(1<<32);
    }
    private static void printInfo(int num){
        System.out.println(Integer.toBinaryString(num));
    }

    static int indexFor(int h, int length) {
        return h & (length-1);
    }

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        if(n>=1<<32){
            return 1<<32;
        }else{
            return n+1;
        }
//        return (n < 0) ? 1 : (n >= 1<<32) ? 1<<32 : n + 1;
    }
}
