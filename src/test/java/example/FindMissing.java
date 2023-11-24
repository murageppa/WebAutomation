package example;

import java.util.ArrayList;

public class FindMissing {
    public static void main(String[] args) {
        int n=5;
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(4);
        arrayList.add(5);

        for (int i = 0; i < arrayList.size(); i++) {
            if(!arrayList.contains(n)){
                System.out.println(arrayList.get(i));
            }
            n--;
        }
    }
}
