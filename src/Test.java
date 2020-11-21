import model.Guy;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main (String [] args){
        System.out.println("Hello");
        System.out.println(1+2);
        Guy first = new Guy("Petya","+37533666654");
        Guy second = new Guy("Kolya","+37533666654");
        List<Guy> list = new ArrayList<Guy>();

        list.add(first);
        list.add(second);
        for (int i = 0;i<list.size();i++){
            System.out.println(list.get(i).getName()+" "+ list.get(i).getPhone());

        }
        for (Guy item: list) {
            System.out.println(item.getName()+" "+ item.getPhone());
        }
    }
}
