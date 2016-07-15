package swtTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeopleFactory {

    /**
     * PeopleFactory�������������People��Ķ���.
     * ����Ŀ��Ӧ����,��Щ��People��Ķ��󶼶�Ӧ�����ݿ���People���е�һ����¼.
     * �ֶ���װ��People����,���뵽һ��List������.
     */
    public static List<People> getPeoples() {
        //����һ��list��������People��Ķ���
        List<People> list = new ArrayList<People>();
            // ��1��ʵ�������
            People people1 = new People();
            people1.setId(new Long(1));
            people1.setName("����");
            people1.setSex(true);//��
            people1.setAge(28);
            people1.setCreateDate(new Date()); // ��ǰ����
            list.add(people1);
            
            // ��2��ʵ�������
            People people2 = new People();
            people2.setId(new Long(2));
            people2.setName("����");
            people2.setSex(false);
            people2.setAge(18);
            people2.setCreateDate(new Date());
            list.add(people2);
            
            // ��3��ʵ�������
            People people3 = new People();
            people3.setId(new Long(3));
            people3.setName("����");
            people3.setSex(true);
            people3.setAge(27);
            people3.setCreateDate(new Date());
            list.add(people3);
            
            return list;
    }
}
