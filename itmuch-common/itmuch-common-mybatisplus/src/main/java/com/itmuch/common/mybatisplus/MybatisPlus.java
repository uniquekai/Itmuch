package com.itmuch.common.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * Created with IntelliJ IDEA.
 * User: uniquek
 * Date: 2019/12/27
 * Time: 4:14 下午
 * Description:
 */
public class MybatisPlus {
    public static void main(String[] args) {

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/itmuch-common/itmuch-common-mybatisplus/src/main/java");
        gc.setFileOverride(true);
        gc.setSwagger2(true);
        gc.setAuthor("uniquek");
        gc.setOpen(false);

        AutoGenerator mpg = new AutoGenerator();
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.setGlobalConfig(gc);


        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl("jdbc:mysql://localhost:3306/user_center?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setEntityLombokModel(true);
        strategy.setEntityBuilderModel(true);
        strategy.setCapitalMode(true);
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[]{"user", "bonus_event_log"}); // 需要生成的表

        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.itmuch.common.mybatisplus.user");


        mpg.setPackageInfo(packageConfig);

        // 执行生成
        mpg.execute();
    }
}