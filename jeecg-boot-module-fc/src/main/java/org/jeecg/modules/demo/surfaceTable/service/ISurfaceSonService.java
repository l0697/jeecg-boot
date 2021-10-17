package org.jeecg.modules.demo.surfaceTable.service;

import org.jeecg.modules.demo.surfaceTable.entity.SurfaceSon;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Description: 金属界面数据附表
 * @Author: jeecg-boot
 * @Date:   2021-08-26
 * @Version: V1.0
 */
public interface ISurfaceSonService extends IService<SurfaceSon> {

	public List<SurfaceSon> selectByMainId(String mainId);
}
