package com.gjsyoung;

import com.gjsyoung.test.AOPTest.Boss;
import com.gjsyoung.test.AOPTest.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author cairuojin
 * @create 2019-01-08 23:00
 */

@Configuration
@ComponentScan("com.gjsyoung.service.Impl")
public class TestConfig {

}