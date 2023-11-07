package deep_clone;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dlf
 * @date 2023/9/15 17:01
 */
@Data
public class CaptureStatisticsCompany  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long ID;

    /**
     * 数据权限
     */
    private String deptCode;

    /**
     * 生成时间
     */
    private Date calculationDate;

    /**
     * 0-可用,1-删除
     */
    private Integer deleteFlag;

    /**
     * 备注
     */
    private String REMARK;

    /**
     * 已审核燃气企业基本信息数据数量
     */
    private Long entInfoCount;

    /**
     * 已审核从业人员其他证书数据数量
     */
    private Long perCertOtherCount;

    /**
     * 已审核小区/村管道燃气情况数据数
     * 量
     */
    private Long raPlStatCount;

    /**
     * 已审核小区/村瓶装燃气情况数据数
     * 量
     */
    private Long raBtStatCount;

    /**
     * 已审核管道燃气居民用户数据数量
     */
    private Long gcResPlCount;

    /**
     * 已审核瓶装燃气居民用户数据数量
     */
    private Long gcResBtCount;

    /**
     * 已审核单位用户数量数据数量
     */
    private Long gcComCount;

    /**
     * 已审核管线基本信息数据数量
     */
    private Long plBaseCount;

    /**
     * 已审核管线分段信息数据数量
     */
    private Long plSectionCount;

    /**
     * 已审核管线分段节点信息数据数量
     */
    private Long plNodeCount;

    /**
     * 已审核城镇管道统计数据数量
     */
    private Long plUrbanStatCount;

    /**
     * 已审核农村管道统计数据数量
     */
    private Long plRuralStatCount;

    /**
     * 已审核厂站基本信息数据数量
     */
    private Long stationCount;

    /**
     * 已审核管道附属设施信息数据数量
     */
    private Long plDeviceCount;

    /**
     * 已审核企业应急预案数据数量
     */
    private Long emgPlanECount;

    /**
     * 已审核政府应急预案数据数量
     */
    private Long emgPlanGCount;

    /**
     * 已审核抢修队伍数据数量
     */
    private Long emgTeamCount;

    /**
     * 已审核应急车辆数据数量
     */
    private Long emgVehicleCount;

    /**
     * 已审核应急物资库数据数量
     */
    private Long emgDepotCount;

    /**
     * 已审核应急物资数据数量
     */
    private Long emgMaterialCount;

    /**
     * 待修改燃气企业基本信息数据数量
     */
    private Long apmEntInfoCount;

    /**
     * 待修改从业人员其他证书数据数量
     */
    private Long apmPerCertOtherCount;

    /**
     * 待修改小区/村管道燃气情况数据数
     * 量
     */
    private Long apmRaPlStatCount;

    /**
     * 待修改小区/村瓶装燃气情况数据数
     * 量
     */
    private Long apmRaBtStatCount;

    /**
     * 待修改管道燃气居民用户数据数量
     */
    private Long apmGcResPlCount;

    /**
     * 待修改瓶装燃气居民用户数据数量
     */
    private Long apmGcResBtCount;

    /**
     * 待修改单位用户数量数据数量
     */
    private Long apmGcComCount;

    /**
     * 待修改管线基本信息数据数量
     */
    private Long apmPlBaseCount;

    /**
     * 待修改管线分段信息数据数量
     */
    private Long apmPlSectionCount;

    /**
     * 待修改管线分段节点信息数据数量
     */
    private Long apmPlNodeCount;

    /**
     * 待修改城镇管道统计数据数量
     */
    private Long apmPlUrbanStatCount;

    /**
     * 待修改农村管道统计数据数量
     */
    private Long apmPlRuralStatCount;

    /**
     * 待修改厂站基本信息数据数量
     */
    private Long apmStationCount;

    /**
     * 待修改管道附属设施信息数据数量
     */
    private Long apmPlDeviceCount;

    /**
     * 待修改企业应急预案数据数量
     */
    private Long apmEmgPlanECount;

    /**
     * 待修改政府应急预案数据数量
     */
    private Long apmEmgPlanGCount;

    /**
     * 待修改抢修队伍数据数量
     */
    private Long apmEmgTeamCount;

    /**
     * 待修改应急车辆数据数量
     */
    private Long apmEmgVehicleCount;

    /**
     * 待修改应急物资库数据数量
     */
    private Long apmEmgDepotCount;

    /**
     * 待修改应急物资数据数量
     */
    private Long apmEmgMaterialCount;

    /**
     * 待审核燃气企业基本信息数据数量
     */
    private Long aprEntInfoCount;

    /**
     * 待审核从业人员其他证书数据数量
     */
    private Long aprPerCertOtherCount;

    /**
     * 待审核小区/村管道燃气情况数据数
     * 量
     */
    private Long aprRaPlStatCount;

    /**
     * 待审核小区/村瓶装燃气情况数据数
     * 量
     */
    private Long aprRaBtStatCount;

    /**
     * 待审核管道燃气居民用户数据数量
     */
    private Long aprGcResPlCount;

    /**
     * 待审核瓶装燃气居民用户数据数量
     */
    private Long aprGcResBtCount;

    /**
     * 待审核单位用户数量数据数量
     */
    private Long aprGcComCount;

    /**
     * 待审核管线基本信息数据数量
     */
    private Long aprPlBaseCount;

    /**
     * 待审核管线分段信息数据数量
     */
    private Long aprPlSectionCount;

    /**
     * 待审核管线分段节点信息数据数量
     */
    private Long aprPlNodeCount;

    /**
     * 待审核城镇管道统计数据数量
     */
    private Long aprPlUrbanStatCount;

    /**
     * 待审核农村管道统计数据数量
     */
    private Long aprPlRuralStatCount;

    /**
     * 待审核厂站基本信息数据数量
     */
    private Long aprStationCount;

    /**
     * 待审核管道附属设施信息数据数量
     */
    private Long aprPlDeviceCount;

    /**
     * 待审核企业应急预案数据数量
     */
    private Long aprEmgPlanECount;

    /**
     * 待审核政府应急预案数据数量
     */
    private Long aprEmgPlanGCount;

    /**
     * 待审核抢修队伍数据数量
     */
    private Long aprEmgTeamCount;

    /**
     * 待审核应急车辆数据数量
     */
    private Long aprEmgVehicleCount;

    /**
     * 待审核应急物资库数据数量
     */
    private Long aprEmgDepotCount;

    /**
     * 待审核应急物资数据数量
     */
    private Long aprEmgMaterialCount;

    /**
     * 所属企业名称
     */
    private String entName;

    /**
     * 所属企业uuid
     */
    private String entUuid;

}