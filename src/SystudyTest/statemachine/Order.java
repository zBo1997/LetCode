package SystudyTest.statemachine;

import java.util.Date;
import lombok.Data;

@Data
public class Order {

    private int id = 3;
    private String order_code = "我是orderCode";
    private int status = 1;
    private String name = "我是名字";
    private double price = 2.0D;
    private int delete_flag = 0;
    private Date create_time = new Date();
    private Date update_time = new Date();;
    private String create_user_code = "1231232131";
    private String update_user_code = "1231232132";
    private int version;
    private String remark = "备注";
}
