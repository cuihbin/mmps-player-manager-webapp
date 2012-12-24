package com.zzvc.mmps.service.impl;

import org.springframework.stereotype.Service;

import com.zzvc.mmps.service.DemoRemotingServiceManager;

@Service("demoRemotingServiceManager")
public class DemoRemotingServiceManagerImpl implements DemoRemotingServiceManager {

	@Override
	public String hello() {
		return "Hello Hessian";
	}

}
