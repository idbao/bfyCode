package com.ssj.util;

public class SILS {
	/**
	 * 标识check按钮(选中)
	 */
	public static final Long YES = 1L;

	/**
	 * 标识check按钮(未选中)
	 */
	public static final Long NO = 0L;

	public static final Long PART = 6L;

	public static final Long COMPONENT = 5L;

	public static final Long EQUIPMENT = 4L;

	public static final Long SUBSYSTEM = 3L;

	public static final Long MAINSYSTEM = 2L;

	/**
	 * 1-初始配置技术状态指标
	 */
	public static final Long INITIALIZE = 1L;

	/**
	 * 0-非初始配置技术状态指标
	 */
	public static final Long NOINITIALIZE = 0L;

	/**
	 * 表格检索项标志 1-在GUI的检索字段(下拉列表)中列出
	 */
	public static final Long TAG = 1L;

	/**
	 * 表格检索项标志 0-不在GUI的检索字段(下拉列表)中列出
	 */
	public static final Long NOTAG = 0L;

	/**
	 * 下拉列表筛选数据代表全部显示的符号
	 */
	public static final String COMBO_TITTLE = "-";

	/**
	 * 常用短语类别代码（商政机构类别）
	 */
	public static final String CAGETYPE = "CAGEType";

	/**
	 * 常用短语类别代码（基地类别）
	 */
	public static final String BASETYPE = "BaseType";

	/**
	 * 常用短语类别代码（舰队类别）
	 */
	public static final String FLEETTYPE = "FleetType";

	/**
	 * 常用短语类别代码（舰船类别）
	 */
	public static final String SHIPTYPE = "ShipType";

	/**
	 * 常用短语类别代码（零件类别）
	 */
	public static final String PARTTYPE = "PartType";

	/**
	 * 常用短语类别代码（部件类别）
	 */
	public static final String COMPONENTTYPE = "ComponentType";

	/**
	 * 常用短语类别代码（装/设备类别）
	 */
	public static final String EQUIPMENTTYPE = "EquipmentType";

	/**
	 * 常用短语类别代码（分系统类别）
	 */
	public static final String SUBSYSTEMTYPE = "SubSystemType";

	/**
	 * 常用短语类别代码（主系统类别）
	 */
	public static final String MAINSYSTEMTYPE = "MainSystemType";

	/**
	 * 常用短语类别代码（维修用具类别）
	 */
	public static final String TOOLEQUIPTYPE = "ToolEquipType";

	/**
	 * 常用短语类别代码（计量单位）
	 */
	public static final String COUNTUNIT = "CountUnit";

	/**
	 * 常用短语类别代码（周期/寿命计量单位）
	 */
	public static final String PERIODLIFEMEASUREUNIT = "PeriodLifeMeasureUnit";

	/**
	 * 常用短语类别代码（故障类别）
	 */
	public static final String FAILURETYPE = "FailureType";

	/**
	 * 常用短语类别代码（维修类别）
	 */
	public static final String MNTNTYPE = "MntnType";

	/**
	 * 常用短语类别代码（维修条件）
	 */
	public static final String MNTNCONDITION = "MntnCondition";

	/**
	 * 常用短语类别代码（专业技能等级）
	 */
	public static final String SPECIALITYGRADE = "SpecialityGrade";

	/**
	 * 常用短语类别代码（AEL对象类别）
	 */
	public static final String APLOBJECTTYPE = "APLObjectType";

	/**
	 * 常用短语类别代码（军衔）
	 */
	public static final String MILITARYRANK = "MilitaryRank";

	/**
	 * 常用短语类别代码（职务/头衔）
	 */
	public static final String DUTYTILE = "DutyTile";

	/**
	 * 常用短语类别代码（值守安排类别）
	 */
	public static final String DUTYARRANGETYPE = "DutyArrangeType";

	/**
	 * 常用短语类别代码（请假安排类别）
	 */
	public static final String LEAVEARRANGETYPE = "LeaveArrangeType";

	/**
	 * 常用短语类别代码（技术资料类别）
	 */
	public static final String TECHFILETYPE = "TechFileType";

	/**
	 * 常用短语类别代码（技术资料来源）
	 */
	public static final String TECHFILESOURCE = "TechFileSource";

	/**
	 * 常用短语类别代码（密级）
	 */
	public static final String SECURITYGRADE = "SecurityGrade";

	/**
	 * 常用短语类别代码（人员类别）
	 */
	public static final String STAFFTYPE = "StaffType";

	/**
	 * 常用短语类别代码（库房类别）
	 */
	public static final String STOREHOUSETYPE = "StoreHouseType";

	/**
	 * 常用短语类别代码（货架类别）
	 */
	public static final String STORESHELFTYPE = "StoreShelfType";

	/**
	 * 常用短语类别代码（内部单位类别）
	 */
	public static final String INTERNALDEPTTYPE = "InternalDeptTYpe";

	/**
	 * 常用短语类别代码（技术状态指标计量单位）
	 */
	public static final String TECHSTATUSINDEXMEASUREUNIT = "TechStatusIndexMeasureUnit";

	/**
	 * 编程用短语代码-故障对象类别(零件)
	 */
	public static final String FAILUREOBJECTTYPE_PART = "FailureObjectType_Part";

	/**
	 * 编程用短语代码-故障对象类别(部件)
	 */
	public static final String FAILUREOBJECTTYPE_COMPONENT = "FailureObjectType_Component";

	/**
	 * 编程用短语代码-故障对象类别(装/设备)
	 */
	public static final String FAILUREOBJECTTYPE_EQUIPMENT = "FailureObjectType_Equipment";

	/**
	 * 编程用短语代码-故障对象类别(分系统)
	 */
	public static final String FAILUREOBJECTTYPE_SUBSYSTEM = "FailureObjectType_SubSystem";

	/**
	 * 编程用短语代码-故障对象类别(主系统)
	 */
	public static final String FAILUREOBJECTTYPE_MAINSYSTEM = "FailureObjectType_MainSystem";

	/**
	 * 编程用短语代码-故障对象类别(维修用具)
	 */
	public static final String FAILUREOBJECTTYPE_TOOLEQUIP = "FailureObjectType_ToolEquip";

	/**
	 * 编程用短语代码-故障对象类别(配置项)
	 */
	public static final String FAILUREOBJECTTYPE_CONFIGITEM = "FailureObjectType_ConfigItem";

	/**
	 * 编程用短语代码-技术资料应用对象类别(零件)
	 */
	public static final String TECHFILEAPPOBJTYPE_PART = "TechFileAppObjType_Part";

	/**
	 * 编程用短语代码-技术资料应用对象类别(部件)
	 */
	public static final String TECHFILEAPPOBJTYPE_COMPONENT = "TechFileAppObjType_Component";

	/**
	 * 编程用短语代码-技术资料应用对象类别(装/设备)
	 */
	public static final String TECHFILEAPPOBJTYPE_EQUIPMENT = "TechFileAppObjType_Equipment";

	/**
	 * 编程用短语代码-技术资料应用对象类别(分系统)
	 */
	public static final String TECHFILEAPPOBJTYPE_SUBSYSTEM = "TechFileAppObjType_SubSystem";

	/**
	 * 编程用短语代码-技术资料应用对象类别(主系统)
	 */
	public static final String TECHFILEAPPOBJTYPE_MAINSYSTEM = "TechFileAppObjType_MainSystem";

	/**
	 * 编程用短语代码-技术资料应用对象类别(维修用具)
	 */

	public static final String TECHFILEAPPOBJTYPE_TOOLEQUIP = "TechFileAppObjType_ToolEquip";

	/**
	 * 编程用短语代码-技术资料应用对象类别(常见故障)
	 */
	public static final String TECHFILEAPPOBJTYPE_FAILURE = "TechFileAppObjType_Failure";

	/**
	 * 编程用短语代码-技术资料应用对象类别(配置项)
	 */
	public static final String TECHFILEAPPOBJTYPE_CONFIGITEM = "TechFileAppObjType_ConfigItem";

	/**
	 * 编程用短语代码-技术资料应用对象类别(维修项目)
	 */
	public static final String TECHFILEAPPOBJTYPE_MAINTNITEM = "TechFileAppObjType_MaintnItem";

	/**
	 * 编程用短语代码-技术状态指标应用对象类别(配置项)
	 */
	public static final String APPOBJTYPE_CONFIGITEM = "AppObjType_ConfigItem";

	/**
	 * 编程用短语代码-技术状态指标应用对象类别(维修用具)
	 */
	public static final String APPOBJTYPE_TOOLEQUIP = "AppObjType_ToolEquip";

	/**
	 * 编程用短语代码-技术状态指标应用对象类别(主系统)
	 */
	public static final String APPOBJTYPE_MAINSYSTEM = "AppObjType_MainSystem";

	/**
	 * 编程用短语代码-技术状态指标应用对象类别(分系统)
	 */
	public static final String APPOBJTYPE_SUBSYSTEM = "AppObjType_SubSystem";

	/**
	 * 编程用短语代码-技术状态指标应用对象类别(装/设备)
	 */
	public static final String APPOBJTYPE_EQUIPMENT = "AppObjType_Equipment";

	/**
	 * 编程用短语代码-技术状态指标应用对象类别(部件)
	 */
	public static final String APPOBJTYPE_COMPONENT = "AppObjType_Component";

	/**
	 * 编程用短语代码-技术状态指标应用对象类别(零件)
	 */
	public static final String APPOBJTYPE_PART = "AppObjType_Part";

	/**
	 * 编程用短语代码-库存项类别(零件)
	 */
	public static final String STOCKITEMTYPE_PART = "StockItemType_Part";

	/**
	 * 编程用短语代码-库存项类别(部件)
	 */
	public static final String STOCKITEMTYPE_COMPONENT = "StockItemType_Component";

	/**
	 * 编程用短语代码-库存项类别(装/设备)
	 */
	public static final String STOCKITEMTYPE_EQUIPMENT = "StockItemType_Equipment";

	/**
	 * 编程用短语代码-库存项类别(分系统)
	 */
	public static final String STOCKITEMTYPE_SUBSYSTEM = "StockItemType_SubSystem";

	/**
	 * 编程用短语代码-库存项类别(主系统)
	 */
	public static final String STOCKITEMTYPE_MAINSYSTEM = "StockItemType_MainSystem";

	/**
	 * 编程用短语代码-库存项类别(维修用具)
	 */
	public static final String STOCKITEMTYPE_TOOLEQUIP = "StockItemType_ToolEquip";

	/**
	 * 编程用短语代码-库存项类别(（不可用）)
	 */
	public static final String STOCKITEMTYPE_NONE = "StockItemType_None";

	/**
	 * 编程用短语类别代码-计时依据
	 */
	public static final String TIMINGREFERENCE = "TimingReference";

	/**
	 * 编程用短语代码-计时依据(按自然时间)
	 */
	public static final String TIMINGREFERENCE_BYCLDTIME = "TimingReference_ByCldTime";

	/**
	 * 编程用短语代码-计时依据(按运行时间)
	 */
	public static final String TIMINGREFERENCE_BYOPRTIME = "TimingReference_ByOprTime";

}
