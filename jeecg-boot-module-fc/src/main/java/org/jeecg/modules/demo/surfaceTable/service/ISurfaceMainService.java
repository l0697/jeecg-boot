package org.jeecg.modules.demo.surfaceTable.service;

import org.jeecg.modules.demo.surfaceTable.entity.SurfaceSon;
import org.jeecg.modules.demo.surfaceTable.entity.SurfaceMain;
import com.baomidou.mybatisplus.extension.service.IService;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Description: 金属界面数据主表
 * @Author: jeecg-boot
 * @Date:   2021-08-26
 * @Version: V1.0
 */
public interface ISurfaceMainService extends IService<SurfaceMain> {

	/**
	 * 添加一对多
	 * 
	 */
	public void saveMain(SurfaceMain surfaceMain,List<SurfaceSon> surfaceSonList) ;
	
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(SurfaceMain surfaceMain,List<SurfaceSon> surfaceSonList);
	
	/**
	 * 删除一对多
	 */
	public void delMain (String id);
	
	/**
	 * 批量删除一对多
	 */
	public void delBatchMain (Collection<? extends Serializable> idList);
	
}
