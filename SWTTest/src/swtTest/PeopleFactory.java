package swtTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeopleFactory {

    /**
     * PeopleFactory这个类用于生成People类的对象.
     * 在项目和应用中,这些个People类的对象都对应着数据库中People表中的一条记录.
     * 手动封装的People对象,放入到一个List集合中.
     */
    public static List<People> getPeoples() {
        //声明一个list对象来放People类的对象
        List<People> list = new ArrayList<People>();
            // 第1个实体类对象
            People people1 = new People();
            people1.setId(new Long(1));
            people1.setName("张三");
            people1.setSex(true);//男
            people1.setAge(28);
            people1.setCreateDate(new Date()); // 当前日期
            list.add(people1);
            
            // 第2个实体类对象
            People people2 = new People();
            people2.setId(new Long(2));
            people2.setName("李四");
            people2.setSex(false);
            people2.setAge(18);
            people2.setCreateDate(new Date());
            list.add(people2);
            
            // 第3个实体类对象
            People people3 = new People();
            people3.setId(new Long(3));
            people3.setName("王五");
            people3.setSex(true);
            people3.setAge(27);
            people3.setCreateDate(new Date());
            list.add(people3);
            
            return list;
    }
}
