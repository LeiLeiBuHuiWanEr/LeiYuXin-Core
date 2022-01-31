package com.iloveleiyuxin.websitmanager.service;

import com.iloveleiyuxin.websitmanager.entity.SysHouse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LeiYuXin's Boyfriend
 * @since 2022-01-23
 */
public interface ISysHouseService extends IService<SysHouse> {
    /**
     * 添加房屋
     * @param buildingNo 楼号
     * @param unitNo 单元
     * @param houseNo 门牌号
     * 以上三个组合之后，就是10406402，108011101这样的门牌号，作为房间主键
     * @param area
     * @param fee
     * @return
     */
    boolean addHouse(String buildingNo,String unitNo,String houseNo,Double area,Double fee);

    /**
     * 删除一间房屋
     * @param id 房间具体id
     * @return
     */
    boolean removeHouse(String id);

    /**
     * 修改房屋信息
     */
    boolean updateHouse(String id,Double fee,Double area,String description);

}
