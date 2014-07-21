package com.onedeveloperstudio.core.server.service;

/**
 * User: y.zakharov
 * Date: 18.07.14
 */

import com.onedeveloperstudio.core.common.dto.BaseDto;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("simpleService")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SimpleServiceImpl extends BaseServiceImpl<BaseDto> implements SimpleService {


}
