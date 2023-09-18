package com.m2p.at.ftbtests;

import com.m2p.at.ftbtests.config.TestDataConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@Slf4j
public class BaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    protected TestDataConfig testData;
}
