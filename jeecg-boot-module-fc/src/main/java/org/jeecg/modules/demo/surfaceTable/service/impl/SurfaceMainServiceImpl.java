package org.jeecg.modules.demo.surfaceTable.service.impl;

import org.jeecg.modules.demo.surfaceTable.entity.SurfaceMain;
import org.jeecg.modules.demo.surfaceTable.entity.SurfaceSon;
import org.jeecg.modules.demo.surfaceTable.mapper.SurfaceSonMapper;
import org.jeecg.modules.demo.surfaceTable.mapper.SurfaceMainMapper;
import org.jeecg.modules.demo.surfaceTable.service.ISurfaceMainService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Collection;

/**
 * @Description: 金属界面数据主表
 * @Author: jeecg-boot
 * @Date:   2021-08-26
 * @Version: V1.0
 */
@Service
public class SurfaceMainServiceImpl extends ServiceImpl<SurfaceMainMapper, SurfaceMain> implements ISurfaceMainService {

	@Autowired
	private SurfaceMainMapper surfaceMainMapper;
	@Autowired
	private SurfaceSonMapper surfaceSonMapper;
	
	@Override
	@Transactional
	public void saveMain(SurfaceMain surfaceMain, List<SurfaceSon> surfaceSonList) {
		surfaceMainMapper.insert(surfaceMain);
		if(surfaceSonList!=null && surfaceSonList.size()>0) {
			for(SurfaceSon entity:surfaceSonList) {
				//外键设置
				entity.setMainId(surfaceMain.getId());
				surfaceSonMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void updateMain(SurfaceMain surfaceMain,List<SurfaceSon> surfaceSonList) {
		surfaceMainMapper.updateById(surfaceMain);
		
		//1.先删除子表数据
		surfaceSonMapper.deleteByMainId(surfaceMain.getId());
		
		//2.子表数据重新插入
		if(surfaceSonList!=null && surfaceSonList.size()>0) {
			for(SurfaceSon entity:surfaceSonList) {
				//外键设置
				entity.setMainId(surfaceMain.getId());
				surfaceSonMapper.insert(entity);
			}
		}
	}

	@Override
	@Transactional
	public void delMain(String id) {
		surfaceSonMapper.deleteByMainId(id);
		surfaceMainMapper.deleteById(id);
	}

	@Override
	@Transactional
	public void delBatchMain(Collection<? extends Serializable> idList) {
		for(Serializable id:idList) {
			surfaceSonMapper.deleteByMainId(id.toString());
			surfaceMainMapper.deleteById(id);
		}
	}
	
}
