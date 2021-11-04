package org.jeecg.modules.demo.surfaceTable.service.impl;

import org.jeecg.modules.demo.surfaceTable.entity.SurfaceSon;
import org.jeecg.modules.demo.surfaceTable.mapper.SurfaceSonMapper;
import org.jeecg.modules.demo.surfaceTable.service.ISurfaceSonService;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: 金属界面数据附表
 * @Author: jeecg-boot
 * @Date:   2021-08-26
 * @Version: V1.0
 */
@Service
public class SurfaceSonServiceImpl extends ServiceImpl<SurfaceSonMapper, SurfaceSon> implements ISurfaceSonService {
	
	@Autowired
	private SurfaceSonMapper surfaceSonMapper;
	
	@Override
	public List<SurfaceSon> selectByMainId(String mainId) {
		return surfaceSonMapper.selectByMainId(mainId);
	}
}
