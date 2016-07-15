package com.ssj.util;

public class SILS {
	/**
	 * ��ʶcheck��ť(ѡ��)
	 */
	public static final Long YES = 1L;

	/**
	 * ��ʶcheck��ť(δѡ��)
	 */
	public static final Long NO = 0L;

	public static final Long PART = 6L;

	public static final Long COMPONENT = 5L;

	public static final Long EQUIPMENT = 4L;

	public static final Long SUBSYSTEM = 3L;

	public static final Long MAINSYSTEM = 2L;

	/**
	 * 1-��ʼ���ü���״ָ̬��
	 */
	public static final Long INITIALIZE = 1L;

	/**
	 * 0-�ǳ�ʼ���ü���״ָ̬��
	 */
	public static final Long NOINITIALIZE = 0L;

	/**
	 * ���������־ 1-��GUI�ļ����ֶ�(�����б�)���г�
	 */
	public static final Long TAG = 1L;

	/**
	 * ���������־ 0-����GUI�ļ����ֶ�(�����б�)���г�
	 */
	public static final Long NOTAG = 0L;

	/**
	 * �����б�ɸѡ���ݴ���ȫ����ʾ�ķ���
	 */
	public static final String COMBO_TITTLE = "-";

	/**
	 * ���ö��������루�����������
	 */
	public static final String CAGETYPE = "CAGEType";

	/**
	 * ���ö��������루�������
	 */
	public static final String BASETYPE = "BaseType";

	/**
	 * ���ö��������루�������
	 */
	public static final String FLEETTYPE = "FleetType";

	/**
	 * ���ö��������루�������
	 */
	public static final String SHIPTYPE = "ShipType";

	/**
	 * ���ö��������루������
	 */
	public static final String PARTTYPE = "PartType";

	/**
	 * ���ö��������루�������
	 */
	public static final String COMPONENTTYPE = "ComponentType";

	/**
	 * ���ö��������루װ/�豸���
	 */
	public static final String EQUIPMENTTYPE = "EquipmentType";

	/**
	 * ���ö��������루��ϵͳ���
	 */
	public static final String SUBSYSTEMTYPE = "SubSystemType";

	/**
	 * ���ö��������루��ϵͳ���
	 */
	public static final String MAINSYSTEMTYPE = "MainSystemType";

	/**
	 * ���ö��������루ά���þ����
	 */
	public static final String TOOLEQUIPTYPE = "ToolEquipType";

	/**
	 * ���ö��������루������λ��
	 */
	public static final String COUNTUNIT = "CountUnit";

	/**
	 * ���ö��������루����/����������λ��
	 */
	public static final String PERIODLIFEMEASUREUNIT = "PeriodLifeMeasureUnit";

	/**
	 * ���ö��������루�������
	 */
	public static final String FAILURETYPE = "FailureType";

	/**
	 * ���ö��������루ά�����
	 */
	public static final String MNTNTYPE = "MntnType";

	/**
	 * ���ö��������루ά��������
	 */
	public static final String MNTNCONDITION = "MntnCondition";

	/**
	 * ���ö��������루רҵ���ܵȼ���
	 */
	public static final String SPECIALITYGRADE = "SpecialityGrade";

	/**
	 * ���ö��������루AEL�������
	 */
	public static final String APLOBJECTTYPE = "APLObjectType";

	/**
	 * ���ö��������루���Σ�
	 */
	public static final String MILITARYRANK = "MilitaryRank";

	/**
	 * ���ö��������루ְ��/ͷ�Σ�
	 */
	public static final String DUTYTILE = "DutyTile";

	/**
	 * ���ö��������루ֵ�ذ������
	 */
	public static final String DUTYARRANGETYPE = "DutyArrangeType";

	/**
	 * ���ö��������루��ٰ������
	 */
	public static final String LEAVEARRANGETYPE = "LeaveArrangeType";

	/**
	 * ���ö��������루�����������
	 */
	public static final String TECHFILETYPE = "TechFileType";

	/**
	 * ���ö��������루����������Դ��
	 */
	public static final String TECHFILESOURCE = "TechFileSource";

	/**
	 * ���ö��������루�ܼ���
	 */
	public static final String SECURITYGRADE = "SecurityGrade";

	/**
	 * ���ö��������루��Ա���
	 */
	public static final String STAFFTYPE = "StaffType";

	/**
	 * ���ö��������루�ⷿ���
	 */
	public static final String STOREHOUSETYPE = "StoreHouseType";

	/**
	 * ���ö��������루�������
	 */
	public static final String STORESHELFTYPE = "StoreShelfType";

	/**
	 * ���ö��������루�ڲ���λ���
	 */
	public static final String INTERNALDEPTTYPE = "InternalDeptTYpe";

	/**
	 * ���ö��������루����״ָ̬�������λ��
	 */
	public static final String TECHSTATUSINDEXMEASUREUNIT = "TechStatusIndexMeasureUnit";

	/**
	 * ����ö������-���϶������(���)
	 */
	public static final String FAILUREOBJECTTYPE_PART = "FailureObjectType_Part";

	/**
	 * ����ö������-���϶������(����)
	 */
	public static final String FAILUREOBJECTTYPE_COMPONENT = "FailureObjectType_Component";

	/**
	 * ����ö������-���϶������(װ/�豸)
	 */
	public static final String FAILUREOBJECTTYPE_EQUIPMENT = "FailureObjectType_Equipment";

	/**
	 * ����ö������-���϶������(��ϵͳ)
	 */
	public static final String FAILUREOBJECTTYPE_SUBSYSTEM = "FailureObjectType_SubSystem";

	/**
	 * ����ö������-���϶������(��ϵͳ)
	 */
	public static final String FAILUREOBJECTTYPE_MAINSYSTEM = "FailureObjectType_MainSystem";

	/**
	 * ����ö������-���϶������(ά���þ�)
	 */
	public static final String FAILUREOBJECTTYPE_TOOLEQUIP = "FailureObjectType_ToolEquip";

	/**
	 * ����ö������-���϶������(������)
	 */
	public static final String FAILUREOBJECTTYPE_CONFIGITEM = "FailureObjectType_ConfigItem";

	/**
	 * ����ö������-��������Ӧ�ö������(���)
	 */
	public static final String TECHFILEAPPOBJTYPE_PART = "TechFileAppObjType_Part";

	/**
	 * ����ö������-��������Ӧ�ö������(����)
	 */
	public static final String TECHFILEAPPOBJTYPE_COMPONENT = "TechFileAppObjType_Component";

	/**
	 * ����ö������-��������Ӧ�ö������(װ/�豸)
	 */
	public static final String TECHFILEAPPOBJTYPE_EQUIPMENT = "TechFileAppObjType_Equipment";

	/**
	 * ����ö������-��������Ӧ�ö������(��ϵͳ)
	 */
	public static final String TECHFILEAPPOBJTYPE_SUBSYSTEM = "TechFileAppObjType_SubSystem";

	/**
	 * ����ö������-��������Ӧ�ö������(��ϵͳ)
	 */
	public static final String TECHFILEAPPOBJTYPE_MAINSYSTEM = "TechFileAppObjType_MainSystem";

	/**
	 * ����ö������-��������Ӧ�ö������(ά���þ�)
	 */

	public static final String TECHFILEAPPOBJTYPE_TOOLEQUIP = "TechFileAppObjType_ToolEquip";

	/**
	 * ����ö������-��������Ӧ�ö������(��������)
	 */
	public static final String TECHFILEAPPOBJTYPE_FAILURE = "TechFileAppObjType_Failure";

	/**
	 * ����ö������-��������Ӧ�ö������(������)
	 */
	public static final String TECHFILEAPPOBJTYPE_CONFIGITEM = "TechFileAppObjType_ConfigItem";

	/**
	 * ����ö������-��������Ӧ�ö������(ά����Ŀ)
	 */
	public static final String TECHFILEAPPOBJTYPE_MAINTNITEM = "TechFileAppObjType_MaintnItem";

	/**
	 * ����ö������-����״ָ̬��Ӧ�ö������(������)
	 */
	public static final String APPOBJTYPE_CONFIGITEM = "AppObjType_ConfigItem";

	/**
	 * ����ö������-����״ָ̬��Ӧ�ö������(ά���þ�)
	 */
	public static final String APPOBJTYPE_TOOLEQUIP = "AppObjType_ToolEquip";

	/**
	 * ����ö������-����״ָ̬��Ӧ�ö������(��ϵͳ)
	 */
	public static final String APPOBJTYPE_MAINSYSTEM = "AppObjType_MainSystem";

	/**
	 * ����ö������-����״ָ̬��Ӧ�ö������(��ϵͳ)
	 */
	public static final String APPOBJTYPE_SUBSYSTEM = "AppObjType_SubSystem";

	/**
	 * ����ö������-����״ָ̬��Ӧ�ö������(װ/�豸)
	 */
	public static final String APPOBJTYPE_EQUIPMENT = "AppObjType_Equipment";

	/**
	 * ����ö������-����״ָ̬��Ӧ�ö������(����)
	 */
	public static final String APPOBJTYPE_COMPONENT = "AppObjType_Component";

	/**
	 * ����ö������-����״ָ̬��Ӧ�ö������(���)
	 */
	public static final String APPOBJTYPE_PART = "AppObjType_Part";

	/**
	 * ����ö������-��������(���)
	 */
	public static final String STOCKITEMTYPE_PART = "StockItemType_Part";

	/**
	 * ����ö������-��������(����)
	 */
	public static final String STOCKITEMTYPE_COMPONENT = "StockItemType_Component";

	/**
	 * ����ö������-��������(װ/�豸)
	 */
	public static final String STOCKITEMTYPE_EQUIPMENT = "StockItemType_Equipment";

	/**
	 * ����ö������-��������(��ϵͳ)
	 */
	public static final String STOCKITEMTYPE_SUBSYSTEM = "StockItemType_SubSystem";

	/**
	 * ����ö������-��������(��ϵͳ)
	 */
	public static final String STOCKITEMTYPE_MAINSYSTEM = "StockItemType_MainSystem";

	/**
	 * ����ö������-��������(ά���þ�)
	 */
	public static final String STOCKITEMTYPE_TOOLEQUIP = "StockItemType_ToolEquip";

	/**
	 * ����ö������-��������(�������ã�)
	 */
	public static final String STOCKITEMTYPE_NONE = "StockItemType_None";

	/**
	 * ����ö���������-��ʱ����
	 */
	public static final String TIMINGREFERENCE = "TimingReference";

	/**
	 * ����ö������-��ʱ����(����Ȼʱ��)
	 */
	public static final String TIMINGREFERENCE_BYCLDTIME = "TimingReference_ByCldTime";

	/**
	 * ����ö������-��ʱ����(������ʱ��)
	 */
	public static final String TIMINGREFERENCE_BYOPRTIME = "TimingReference_ByOprTime";

}
