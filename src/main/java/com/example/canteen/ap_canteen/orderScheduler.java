package com.example.canteen.ap_canteen;

import java.io.Serializable;
import java.util.Comparator;
public class orderScheduler implements Comparator<order>, Serializable {
    private static final long serialVersionUID = 1L;
    public int compare(order o1, order o2) {
        if (o1.getPriority() && !o2.getPriority()) {
            return -1;
        } else if (!o1.getPriority() && o2.getPriority()) {
            return 1;
        } else {
            return Integer.compare(o1.count, o2.count);
        }
    }
}
